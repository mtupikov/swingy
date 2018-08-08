package com.swingy.view.shell;

import com.swingy.model.Creature;
import com.swingy.model.map.Map;
import com.swingy.view.GUI;

import java.util.ArrayList;

public class ShellGUI implements GUI {
	private Creature			player;
	private Map					map;

	public ShellGUI() {}

	public void setPlayer(Creature player) {
		this.player = player;
	}

	public void drawChoosePlayer(ArrayList<Creature> players) {
		System.out.print("\033[H\033[2J");
		String playerNames[] = new String[4];
		for (int i = 0; i < 4; ++i) {
			if (players.get(i) == null)
				playerNames[i] = "Empty";
			else
				playerNames[i] = players.get(i).getName();
		}
		System.out.printf(    "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                    CHOOSE PLAYER                                   │\n"
							+ "│ ┍━━━━━━━━━━━━┑         ┍━━━━━━━━━━━━┑         ┍━━━━━━━━━━━━┑        ┍━━━━━━━━━━━━┑ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │ %10s │         │ %10s │         │ %10s │        │ %10s │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ ┕━━━━━━━━━━━━┙         ┕━━━━━━━━━━━━┙         ┕━━━━━━━━━━━━┙        ┕━━━━━━━━━━━━┙ │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│     Options:                                                                       │\n"
							+ "│     -> Choose player                                                               │\n"
							+ "│     -> Delete player                                                               │\n"
							+ "│     -> Create new player                                                           │\n"
							+ "│                                                                                    │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙\n",
							playerNames[0], playerNames[1], playerNames[2], playerNames[3]);
	}

	public void drawMenu() {
			System.out.print("\033[H\033[2J");
			System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│          _______.____    __    ____  __  .__   __.   ___________    ____           │\n"
								+ "│         /       |\\   \\  /  \\  /   / |  | |  \\ |  |  /  _____\\   \\  /   /           │\n"
								+ "│        |   (----` \\   \\/    \\/   /  |  | |   \\|  | |  |  __  \\   \\/   /            │\n"
								+ "│         \\   \\      \\            /   |  | |  . `  | |  | |_ |  \\_    _/             │\n"
								+ "│     .----)   |      \\    /\\    /    |  | |  |\\   | |  |__| |    |  |               │\n"
								+ "│     |_______/        \\__/  \\__/     |__| |__| \\__|  \\______|    |__|               │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                              PRESS ENTER TO CONTINUE                               │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "│                                                                                    │\n"
								+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
	}

	public void drawChooseRace() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                     CHOOSE RACE                                    │\n"
							+ "│ ┍━━━━━━━━━━━━┑         ┍━━━━━━━━━━━━┑         ┍━━━━━━━━━━━━┑        ┍━━━━━━━━━━━━┑ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │       ╭ ╭╮ │         │        ⟆ ⟆⟆│         │       ‖‖‖ ‖│        │          __│ │\n"
							+ "│ │     ╭╭╭ ╭ ╮│         │       ⟆ ⟆ ⟆│         │      ‖‖  ‖ │        │         ⎛  │ │\n"
							+ "│ │    ╭ ╭╮╭ ╮╮│         │      ⟆ ⟆ ⟆⟆│         │      ‖ ‖‖ ‖│        │        ⎛   │ │\n"
							+ "│ │   ╭╭ ╭╭ ╭ ╮│         │     ⟆ ⟆ ⟆⟆ │         │     ‖ ‖  ‖ │        │       ⎛    │ │\n"
							+ "│ │   ╭╭ ╭ ╭ ╭╮│         │    ⟆ ⟆ ◡⟆ ⟆│         │     ‖‖ ‖‖ ‖│        │      ⎛     │ │\n"
							+ "│ │  ╭╭│  @    │         │   ⟆ ⟆ ⟆ ☼  │         │    ‖ ‖ ⊙   │        │     ⎛  O   │ │\n"
							+ "│ │   ╭│     /\\│         │   ⟆⟆⟆     ⩢│         │     \\     ⌵│        │      \\    .│ │\n"
							+ "│ │    \\       │         │  ⟆ ⟆\\      │         │     \\  ╭╭╭╮│        │      \\     │ │\n"
							+ "│ │     \\  ----│         │   ⟆⟆ \\  ---│         │      \\  ---│        │       \\ ⌊__│ │\n"
							+ "│ │      \\     │         │  ⟆  ⟆ \\    │         │       \\____│        │        \\___│ │\n"
							+ "│ │       \\╴╴╴╴│         │         ◟◡◡│         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ │            │         │            │         │            │        │            │ │\n"
							+ "│ ┕━━━━━━━━━━━━┙         ┕━━━━━━━━━━━━┙         ┕━━━━━━━━━━━━┙        ┕━━━━━━━━━━━━┙ │\n"
							+ "│      HUMAN                   ELF                   DWARF                  ORC      │\n"
							+ "│                                                                                    │\n"
							+ "│    Attack +2                HP +5               Defence +5              Attack +5  │\n"
							+ "│    Defence +1                                                                      │\n"
							+ "│      HP +2                                                                         │\n"
							+ "│                                                                                    │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
	}

	public void drawChooseClass() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                    CHOOSE CLASS                                    │\n"
							+ "│ ┍━━━━━━━━━━━━┑         ┍━━━━━━━━━━━━┑         ┍━━━━━━━━━━━━┑        ┍━━━━━━━━━━━━┑ │\n"
							+ "│ │            │         │            │         │            │        │         /  │ │\n"
							+ "│ │            │         │            │         │            │        │        / | │ │\n"
							+ "│ │            │         │            │         │            │        │       /  | │ │\n"
							+ "│ │            │         │       ` ` `│         │            │        │      /   | │ │\n"
							+ "│ │           /│         │      ` ` ` │         │            │        │     /    | │ │\n"
							+ "│ │          / │         │      ~ `---│         │            │        │    /     | │ │\n"
							+ "│ │         /  │         │    ~ ~ /  .│         │            │        │   /      | │ │\n"
							+ "│ │        /   │         │     ~ /. . │         │            │        │  /       | │ │\n"
							+ "│ │       |    │         │   ~  |  . .│         │            │        │ /        | │ │\n"
							+ "│ │       |    │         │     ~ \\  . │         │            │        │|         | │ │\n"
							+ "│ │       |    │         │    ~ ~ \\___│         │            │        │|         | │ │\n"
							+ "│ │       |    │         │       ` ` |│         │           /│        │|         | │ │\n"
							+ "│ │       |    │         │         ` |│         │          / │        │|         | │ │\n"
							+ "│ │       |    │         │           |│         │         /  │        │|         | │ │\n"
							+ "│ │       |    │         │           |│         │        /  |│        │|         | │ │\n"
							+ "│ │       |    │         │           |│         │        |  |│        │ \\        | │ │\n"
							+ "│ │       |    │         │           |│         │        |  |│        │  \\       | │ │\n"
							+ "│ │       |    │         │           |│         │        \\  |│        │   \\      | │ │\n"
							+ "│ │       |    │         │           |│         │         \\  │        │    \\     | │ │\n"
							+ "│ │       |    │         │           |│         │          | │        │     \\    | │ │\n"
							+ "│ │    c=======│         │           |│         │          | │        │      \\   | │ │\n"
							+ "│ │         |  │         │           |│         │          | │        │       \\  | │ │\n"
							+ "│ │         |  │         │           |│         │         ===│        │        \\ | │ │\n"
							+ "│ │         |  │         │           |│         │           |│        │         \\  │ │\n"
							+ "│ ┕━━━━━━━━━━━━┙         ┕━━━━━━━━━━━━┙         ┕━━━━━━━━━━━━┙        ┕━━━━━━━━━━━━┙ │\n"
							+ "│     WARRIOR                 MAGE                  THIEF                 HUNTER     │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
	}

	public void drawChooseName() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                 ENTER PLAYER NAME :                                │\n"
							+ "│                                 (3 - 10 characters)                                │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "│                                                                                    │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
	}

	public void drawMap() {
		System.out.print("\033[H\033[2J");
		System.out.print("┍");
		for (int i = 0; i < map.getSize() * 2; ++i)
			System.out.print("━");
		System.out.println("┑");
		for (int i = 0; i < map.getSize(); ++i) {
			System.out.print("│");
			for (int j = 0; j < map.getSize(); ++j) {
				if (player.getPosX() == j && player.getPosY() == i)
					System.out.print("P ");
				else
					System.out.print(map.getMapCell(i, j) + " ");
			}
			System.out.println("│");
		}
		System.out.print("┕");
		for (int i = 0; i < map.getSize() * 2; ++i)
			System.out.print("━");
		System.out.println("┙");
		System.out.println("Type commands to move: 'right', 'left', 'up', 'down', or 'info' for player info.");
	}

	public void drawCheckPlayerWantsToBattle() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "│  Do you want to start battle?  │\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
		System.out.println("Type commands to select: yes, no.");
	}

	public void drawDie() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "│            YOU DIED            │\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
		System.out.println("Press enter to continue");
	}

	public void drawWin() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "│            YOU WIN             │\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
		System.out.println("Press enter to continue");
	}

	public void drawPlayer() {
		System.out.print("\033[H\033[2J");
		int nextexp = ((player.getLevel() + 1) * 1000) + (int)Math.pow(player.getLevel(), 2) * 450;
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑");
		System.out.printf(    "│  Name:     %20s             │\n", player.getName());
		System.out.printf(    "│  Race:     %20s             │\n", player.getPlayerRace().getName());
		System.out.printf(    "│  Class:    %20s             │\n", player.getPlayerClass());
		System.out.printf(    "│  HP:       %20s / %3s       │\n", player.getHealthPoints(), player.getMaxHealthPoints());
		System.out.printf(    "│  Level:    %20s             │\n", player.getLevel());
		System.out.printf(    "│  Exp:      %20s /%5s      │\n", player.getExpPoints(), player.getLevel() == 5 ? "inf" : nextexp);
		System.out.printf(    "│  Attack:   %20s             │\n", player.getAttack());
		System.out.printf(    "│  Defence:  %20s             │\n", player.getDefence());
		System.out.printf(    "│  Weapon:   %20s             │\n", player.getWeapon() != null ? player.getWeapon().getName() : "-");
		System.out.printf(    "│  Armor:    %20s             │\n", player.getArmor() != null ? player.getArmor().getName() : "-");
		System.out.printf(    "│  Helmet:   %20s             │\n", player.getHelmet() != null ? player.getHelmet().getName() : "-");
		System.out.println(   "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
		System.out.println("Press enter to continue");
	}

	public int drawGetPrize(String type) {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑");
		System.out.printf(    "│      Do you want to keep:      │\n");
		System.out.printf(    "│            %10s?         │\n", type);
		System.out.printf(    "│                                │\n");
		System.out.printf(    "│                                │\n");
		System.out.println(   "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
		System.out.println("Type commands to select: yes, no.");
		return 0;
	}

	public void drawGetNothing() {
		System.out.print("\033[H\033[2J");
		System.out.println(   "┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┑\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "│       You got nothing :(       │\n"
							+ "│                                │\n"
							+ "│                                │\n"
							+ "┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┙");
		System.out.println("Press enter to continue");
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
