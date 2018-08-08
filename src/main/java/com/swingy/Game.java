package com.swingy;

import com.swingy.controller.battle.BattleHandler;
import com.swingy.controller.Controller;
import com.swingy.database.GameDB;
import com.swingy.view.gui.SwingGUI;
import com.swingy.model.map.Map;
import com.swingy.model.Creature;
import com.swingy.view.shell.ShellGUI;
import com.swingy.view.View;

import javax.validation.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Set;

public class Game {
	static private Creature		player;
	static public View			view;

	static void				mainLoop() {
		menuLoop();
		if (gameLoop()) {
			if (!Main.gameMode) {
				view.getShellGui().drawWin();
			} else
				view.getSwingGui().drawWin();
		} else if (!Main.gameMode) {
			view.getShellGui().drawDie();
		} else
			view.getSwingGui().drawDie();
		if (!Main.gameMode)
			Controller.waitForInput();
		System.exit(0);
	}

	private static boolean	gameLoop() {
		Map map = new Map(player);
		view.getSwingGui().setMap(map);
		view.getShellGui().setMap(map);
		view.getSwingGui().drawMap();
		while (true) {
			map.checkEmptyMap();
			if (!Main.gameMode)
				view.getShellGui().drawMap();
			if (Controller.playerMovement(player, map)) {
				if (map.getMapCell(player.getPosY(), player.getPosX()) == 'E')
					if (!BattleHandler.startBattle(player, map))
						return false;
					else
						map.setMapCell(player.getPosY(), player.getPosX(), '.');
			}
			else {
				if (!Main.gameMode) {
					view.getShellGui().drawPlayer();
					Controller.waitForInput();
				}
			}
			if (player.getLevel() == 5)
				break;
			view.getSwingGui().updateMap(map);
		}
		return true;
	}

	private static void		menuLoop() {
		player = null;
		view = new View(new SwingGUI(), new ShellGUI());
		view.getSwingGui().drawMenu();
		view.getSwingGui().setVisibility(Main.gameMode);
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawMenu();
			if (Controller.waitForInput())
				break;
		}
		ArrayList<Creature> players = GameDB.getDB();
		view.getSwingGui().drawChoosePlayer(players);
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawChoosePlayer(players);
			Creature t = Controller.chooseCreature(players);
			if (t != null) {
				if (t.getName() == null) {
					player = t;
					view.getShellGui().setPlayer(player);
					view.getSwingGui().setPlayer(player);
					break;
				} else {
					player = t;
					view.getShellGui().setPlayer(player);
					view.getSwingGui().setPlayer(player);
					return;
				}
			}
		}
		view.getSwingGui().drawChooseRace();
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawChooseRace();
			if (Controller.enterRace(player))
				break;
		}
		view.getSwingGui().drawChooseClass();
		while (true) {
			if (!Main.gameMode)
				view.getShellGui().drawChooseClass();
			if (Controller.enterClass(player))
				break;
		}
		view.getSwingGui().drawChooseName();
		Set<ConstraintViolation<Creature>> violations = null;
		while (true) {
			Validator validator = Main.factory.getValidator();
			if (!Main.gameMode)
				view.getShellGui().drawChooseName();
			if (violations != null && !Main.gameMode)
				for (ConstraintViolation<Creature> violation : violations)
					System.out.println(violation.getMessage());
			Controller.enterName(player);
			validator.validate(player);
			violations = validator.validate(player);
			if (violations.size() == 0 || view.getSwingGui().isExitLoop())
				break;
		}
		GameDB.insertHero(player);
	}
}
