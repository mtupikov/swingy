package com.swingy.controller.battle;

import com.swingy.*;
import com.swingy.database.GameDB;
import com.swingy.model.Creature;
import com.swingy.model.InventoryObjectInterface;
import com.swingy.model.PlayerClasses;
import com.swingy.model.armor.*;
import com.swingy.controller.Controller;
import com.swingy.model.enemy.Enemy;
import com.swingy.model.helmets.*;
import com.swingy.model.map.Map;
import com.swingy.model.weapons.*;

import java.util.Random;

public class BattleHandler {

	static private boolean						checkPlayerWantsToBattle() {
		while (true) {
			if (!Main.gameMode)
				Game.view.getShellGui().drawCheckPlayerWantsToBattle();
			int check = Controller.checkPlayerYesNo();
			if (check == 0)
				return true;
			else if (check == 1)
				return new Random().nextBoolean();
		}
	}

	static private InventoryObjectInterface getWeaponPrize(int lvl, PlayerClasses pclass) {
		switch (lvl) {
			case 0:
				if (pclass == PlayerClasses.WARRIOR)
					return new WoodSword();
				else if (pclass == PlayerClasses.MAGE)
					return new WoodStaff();
				else if (pclass == PlayerClasses.THIEF)
					return new WoodKnife();
				else if (pclass == PlayerClasses.HUNTER)
					return new WoodBow();
				break;
			case 1:
				if (pclass == PlayerClasses.WARRIOR)
					return new BronzeSword();
				else if (pclass == PlayerClasses.MAGE)
					return new BronzeShell();
				else if (pclass == PlayerClasses.THIEF)
					return new BronzeShell();
				else if (pclass == PlayerClasses.HUNTER)
					return new BronzeShell();
				break;
			case 2:
				if (pclass == PlayerClasses.WARRIOR)
					return new SteelSword();
				else if (pclass == PlayerClasses.MAGE)
					return new SteelShell();
				else if (pclass == PlayerClasses.THIEF)
					return new SteelShell();
				else if (pclass == PlayerClasses.HUNTER)
					return new SteelShell();
				break;
			case 3:
				if (pclass == PlayerClasses.WARRIOR)
					return new DragonSword();
				else if (pclass == PlayerClasses.MAGE)
					return new DragonShell();
				else if (pclass == PlayerClasses.THIEF)
					return new DragonShell();
				else if (pclass == PlayerClasses.HUNTER)
					return new DragonShell();
				break;
			case 4:
				if (pclass == PlayerClasses.WARRIOR)
					return new DiamondSword();
				else if (pclass == PlayerClasses.MAGE)
					return new DiamondShell();
				else if (pclass == PlayerClasses.THIEF)
					return new DiamondShell();
				else if (pclass == PlayerClasses.HUNTER)
					return new DiamondShell();
				break;
			case 5:
				if (pclass == PlayerClasses.WARRIOR)
					return new SuperSword();
				else if (pclass == PlayerClasses.MAGE)
					return new SuperShell();
				else if (pclass == PlayerClasses.THIEF)
					return new SuperShell();
				else if (pclass == PlayerClasses.HUNTER)
					return new SuperShell();
				break;
		}
		return null;
	}

	static private void							getPrize(Creature player, Creature enemy) {
		InventoryObjectInterface artifact = null;
		Random random = new Random();
		if (random.nextInt(20) == 0) {
			if (Main.gameMode)
				Game.view.getSwingGui().drawGetNothing();
			if (!Main.gameMode) {
				Game.view.getShellGui().drawGetNothing();
				Controller.waitForInput();
			}
			return;
		}
		int rand = random.nextInt(6);
		switch (enemy.getLevel()) {
			case 0:
				if (rand == 0)
					artifact = getWeaponPrize(0, player.getPlayerClass());
				else if (rand == 1)
					artifact = new WoodShell();
				else
					artifact = new WoodHelmet();
				break;
			case 1:
				if (rand == 0)
					artifact = getWeaponPrize(1, player.getPlayerClass());
				else if (rand == 1)
					artifact = new BronzeShell();
				else
					artifact = new BronzeHelmet();
				break;
			case 2:
				if (rand == 0)
					artifact = getWeaponPrize(2, player.getPlayerClass());
				else if (rand == 1)
					artifact = new SteelShell();
				else
					artifact = new SteelHelmet();
				break;
			case 3:
				if (rand == 0)
					artifact = getWeaponPrize(3, player.getPlayerClass());
				else if (rand == 1)
					artifact = new DragonShell();
				else
					artifact = new DragonHelmet();
				break;
			case 4:
				if (rand == 0)
					artifact = getWeaponPrize(4, player.getPlayerClass());
				else if (rand == 1)
					artifact = new DiamondShell();
				else
					artifact = new DiamondHelmet();
				break;
			case 5:
				if (rand == 0)
					artifact = getWeaponPrize(5, player.getPlayerClass());
				else if (rand == 1)
					artifact = new SuperShell();
				else
					artifact = new SuperHelmet();
				break;
		}
		if (artifact != null) {
			while (true) {
				int check;
				if (!Main.gameMode) {
					Game.view.getShellGui().drawGetPrize(artifact.returnThis().toString());
					check = Controller.checkPlayerYesNo();
				} else
					check = Game.view.getSwingGui().drawGetPrize(artifact.returnThis().toString());
				if (check == 0)
					switch (artifact.returnThis()) {
						case ARMOR:
							player.setArmor((AbstractArmor) artifact);
							return;
						case HELMET:
							player.setHelmet((AbstractHelmet) artifact);
							return;
						case WEAPON:
							player.setWeapon((AbstractWeapon) artifact);
							return;
					}
				else if (check == 1)
					return;
			}
		}
	}

	static private boolean						greatRandomizer(Creature player, Map map) {
		Creature enemy = new Enemy(player);
		while (true) {
			if (player.getHealthPoints() == 0)
				return false;
			player.attack(enemy);
			if (enemy.getHealthPoints() == 0) {
				player.setHealthPoints(player.getMaxHealthPoints());
				player.addExpPoints(enemy.getLevel() * 300 + 200, map);
				getPrize(player, enemy);
				GameDB.updateHero(player);
				return true;
			}
			enemy.attack(player);
		}
	}

	static public boolean						startBattle(Creature player, Map map) {
		Game.view.getSwingGui().drawCheckPlayerWantsToBattle();
		if (!checkPlayerWantsToBattle()) {
			player.setPosX(player.getLastPosX());
			player.setPosY(player.getLastPosY());
			Game.view.getSwingGui().drawMap();
			return true;
		}
		Game.view.getSwingGui().drawMap();
		return greatRandomizer(player, map);
	}
}
