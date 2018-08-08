package com.swingy.controller;

import com.swingy.database.GameDB;
import com.swingy.model.Creature;
import com.swingy.Game;
import com.swingy.Main;
import com.swingy.model.PlayerClasses;
import com.swingy.model.map.Map;
import com.swingy.model.races.DwarfRace;
import com.swingy.model.races.ElfRace;
import com.swingy.model.races.HumanRace;
import com.swingy.model.races.OrkRace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
	static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static int		checkPlayerYesNo() {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("y"))
					return 0;
				else if (s.toLowerCase().equals("no") || s.toLowerCase().equals("n"))
					return 1;
				else if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
					return 2;
				}
			}
		} else
			return Game.view.getSwingGui().getCheckLoop();
		return 2;
	}

	private static void		switchCase() {
		Main.gameMode = !Main.gameMode;
		Game.view.getSwingGui().setVisibility(Main.gameMode);
		System.out.print("\033[H\033[2J");
	}

	public static boolean	waitForInput() {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
					return false;
				} else
					return true;
			}
		} else {
			return Game.view.getSwingGui().isExitLoop();
		}
		return false;
	}

	public static Creature	chooseCreature(ArrayList<Creature> players) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				for (Creature player : players) {
					if (player != null) {
						if (player.getName().toLowerCase().equals(s))
							return player;
					}
				}
				if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
					return null;
				} else if (s.toLowerCase().startsWith("delete")) {
					String split[] = s.toLowerCase().split(" ");
					if (split.length != 2)
						return null;
					for (Creature player : players) {
						if (player != null) {
							if (player.getName().toLowerCase().equals(split[1])) {
								GameDB.deleteHero(player.getName());
							}
						}
					}
					players.clear();
					players.addAll(GameDB.getDB());
					return null;
				} else if (s.toLowerCase().equals("create")) {
					return new Creature();
				}
			}
		} else {
			switch (Game.view.getSwingGui().getCheckLoop()) {
				case 0:
					return null;
				case 1:
					return players.get(0);
				case 2:
					return players.get(1);
				case 3:
					return players.get(2);
				case 4:
					return players.get(3);
				case 5:
					return new Creature();
			}
		}
		return null;
	}

	public static boolean	enterRace(Creature player) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("human")) {
					player.setPlayerRace(new HumanRace());
					return true;
				} else if (s.toLowerCase().equals("elf")) {
					player.setPlayerRace(new ElfRace());
					return true;
				} else if (s.toLowerCase().equals("dwarf")) {
					player.setPlayerRace(new DwarfRace());
					return true;
				} else if (s.toLowerCase().equals("ork")) {
					player.setPlayerRace(new OrkRace());
					return true;
				} else if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
					return false;
				}
			}
		} else {
			return Game.view.getSwingGui().isExitLoop();
		}
		return false;
	}

	public static boolean	enterClass(Creature player) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("warrior")) {
					player.setPlayerClass(PlayerClasses.WARRIOR);
					return true;
				} else if (s.toLowerCase().equals("mage")) {
					player.setPlayerClass(PlayerClasses.MAGE);
					return true;
				} else if (s.toLowerCase().equals("thief")) {
					player.setPlayerClass(PlayerClasses.THIEF);
					return true;
				} else if (s.toLowerCase().equals("hunter")) {
					player.setPlayerClass(PlayerClasses.HUNTER);
					return true;
				} else if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
					return false;
				}
			}
		} else {
			return Game.view.getSwingGui().isExitLoop();
		}
		return false;
	}

	public static boolean	playerMovement(Creature player, Map map) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("right") || s.toLowerCase().equals("r")) {
					player.incPosX(map);
				} else if (s.toLowerCase().equals("left") || s.toLowerCase().equals("l")) {
					player.decPosX();
				} else if (s.toLowerCase().equals("up") || s.toLowerCase().equals("u")) {
					player.decPosY();
				} else if (s.toLowerCase().equals("down") || s.toLowerCase().equals("d")) {
					player.incPosY(map);
				} else if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
				} else return !s.toLowerCase().equals("info") && !s.toLowerCase().equals("i");
			}
		} else
			return Game.view.getSwingGui().isExitLoop();
		return true;
	}

	public static boolean	enterName(Creature player) {
		if (!Main.gameMode) {
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.getMessage();
			}
			if (s != null) {
				if (s.toLowerCase().equals("switch") || s.toLowerCase().equals("s")) {
					switchCase();
					return false;
				} else
					player.setName(s);
			}
		}
		return true;
	}
}
