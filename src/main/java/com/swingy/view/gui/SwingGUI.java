package com.swingy.view.gui;

import com.swingy.database.GameDB;
import com.swingy.model.Creature;
import com.swingy.Main;
import com.swingy.model.PlayerClasses;
import com.swingy.model.map.Map;
import com.swingy.model.races.DwarfRace;
import com.swingy.model.races.ElfRace;
import com.swingy.model.races.HumanRace;
import com.swingy.model.races.OrkRace;
import com.swingy.view.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class SwingGUI extends JFrame implements GUI {
	private static final int	SCREEN_HEIGHT = 800;
	private static final int	SCREEN_WIDTH = 800;
	private Creature			player;
	private Map					map;
	private JTextArea			mapLabel;
	private boolean				exitLoop;
	private int					checkLoop;
    private JButton     		switchButton = new JButton("Switch game mode");
    private JLabel				errorOutput = new JLabel();
    private JTextField			textInput = new JTextField();
	private final JButton		select[] = new JButton[4];
	private final JButton		delete[] = new JButton[4];
	private final JTextArea		areas[] = new JTextArea[4];
	private void clearGUI() {
		this.getContentPane().removeAll();
		this.repaint();
		add(switchButton);
	}

	public void setPlayer(Creature player) {
		this.player = player;
	}

	public SwingGUI() {
        super("SWINGY");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		switchButton.addActionListener(new SwitchButtonListener());
		switchButton.setBounds(0, SCREEN_HEIGHT - 50, SCREEN_WIDTH, 30);
    }

	public boolean isExitLoop() {
		return exitLoop;
	}

	public void setVisibility(boolean b) {
    	this.setVisible(b);
	}

	class MyImage extends JPanel {
		String	path;
		int		x, y;

		MyImage(String path, int x, int y) {
			super();
			this.path = path;
			this.x = x;
			this.y = y;
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			try {
				BufferedImage img = ImageIO.read(new File(path));
				g.drawImage(img, x, y, null);
			} catch (IOException e) {
				Main.printError(e.getMessage());
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return (new Dimension(700, 400));
		}
	}

	public void drawMenu() {
    	exitLoop = false;
    	clearGUI();
		JButton buttonEnterScreen = new JButton("Press to continue");
		buttonEnterScreen.addActionListener(new EnterButtonListener());
		buttonEnterScreen.setBounds(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT - 100, 200, 30);
		add(buttonEnterScreen);
		MyImage image = new MyImage("../hello_screen.png", 0, 0);
		image.setBounds(50, 50, 700, 400);
		add(image);
	}

	private void updateCheck(Creature player, int i) {
		if (player != null) {
			areas[i].setText(player.getName());
			add(select[i]);
			add(delete[i]);
		} else {
			remove(select[i]);
			remove(delete[i]);
			areas[i].setText("Empty");
		}
	}

	private void updatePlayers(ArrayList<Creature> players) {
		for (int i = 0; i < players.size(); i++) {
			Creature player = players.get(i);
			updateCheck(player, i);
		}
	}

	public void drawChoosePlayer(final ArrayList<Creature> players) {
		checkLoop = 0;
		clearGUI();
		JButton createPlayer = new JButton("Create new player");
		for (int i = 0; i < 4; i++) {
			areas[i] = new JTextArea();
			select[i] = new JButton("Select");
			delete[i] = new JButton("Delete");
		}
		areas[0].setBounds(SCREEN_WIDTH / 5 - 75, 100, 150, 200);
		areas[1].setBounds(SCREEN_WIDTH * 2 / 5 - 75, 100, 150, 200);
		areas[2].setBounds(SCREEN_WIDTH * 3 / 5 - 75, 100, 150, 200);
		areas[3].setBounds(SCREEN_WIDTH * 4 / 5 - 75, 100, 150, 200);
		select[0].setBounds(SCREEN_WIDTH / 5 - 75, 400, 150, 30);
		select[1].setBounds(SCREEN_WIDTH * 2 / 5 - 75, 400, 150, 30);
		select[2].setBounds(SCREEN_WIDTH * 3 / 5 - 75, 400, 150, 30);
		select[3].setBounds(SCREEN_WIDTH * 4 / 5 - 75, 400, 150, 30);
		delete[0].setBounds(SCREEN_WIDTH / 5 - 75, 450, 150, 30);
		delete[1].setBounds(SCREEN_WIDTH * 2 / 5 - 75, 450, 150, 30);
		delete[2].setBounds(SCREEN_WIDTH * 3 / 5 - 75, 450, 150, 30);
		delete[3].setBounds(SCREEN_WIDTH * 4 / 5 - 75, 450, 150, 30);
		createPlayer.setBounds(SCREEN_WIDTH / 2 - 75, SCREEN_HEIGHT - 100, 150, 30);
		areas[0].setEditable(false);
		areas[1].setEditable(false);
		areas[2].setEditable(false);
		areas[3].setEditable(false);
		select[0].addActionListener(new ChooseFirstButtonListener());
		select[1].addActionListener(new ChooseSecondButtonListener());
		select[2].addActionListener(new ChooseThirdButtonListener());
		select[3].addActionListener(new ChooseFourthButtonListener());
		createPlayer.addActionListener(new ChooseNewButtonListener());
		delete[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameDB.deleteHero(players.get(0).getName());
				remove(select[0]);
				remove(delete[0]);
				players.clear();
				players.addAll(GameDB.getDB());
				updatePlayers(players);
				repaint();
			}
		});
		delete[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameDB.deleteHero(players.get(1).getName());
				remove(select[1]);
				remove(delete[1]);
				players.clear();
				players.addAll(GameDB.getDB());
				updatePlayers(players);
				repaint();
			}
		});
		delete[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameDB.deleteHero(players.get(2).getName());
				remove(select[2]);
				remove(delete[2]);
				players.clear();
				players.addAll(GameDB.getDB());
				updatePlayers(players);
				repaint();
			}
		});
		delete[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameDB.deleteHero(players.get(3).getName());
				remove(select[3]);
				remove(delete[3]);
				players.clear();
				players.addAll(GameDB.getDB());
				updatePlayers(players);
				repaint();
			}
		});
		for (int i = 0; i < players.size(); i++) {
			Creature player = players.get(i);
			updateCheck(player, i);
			add(areas[i]);
		}
		add(createPlayer);
		repaint();
	}

	public void drawChooseRace() {
		exitLoop = false;
		clearGUI();
		JButton chooseHuman = new JButton("Human");
		JButton chooseElf = new JButton("Elf");
		JButton chooseDwarf = new JButton("Dwarf");
		JButton chooseOrk = new JButton("Orc");
		chooseHuman.addActionListener(new ChooseHumanButtonListener());
		chooseElf.addActionListener(new ChooseElfButtonListener());
		chooseDwarf.addActionListener(new ChooseDwarfButtonListener());
		chooseOrk.addActionListener(new ChooseOrkButtonListener());
		chooseHuman.setBounds(SCREEN_WIDTH / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		chooseElf.setBounds(SCREEN_WIDTH * 2 / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		chooseDwarf.setBounds(SCREEN_WIDTH  * 3 / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		chooseOrk.setBounds(SCREEN_WIDTH * 4 / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		MyImage human = new MyImage("../human_face.png", 0, 0);
		MyImage elf = new MyImage("../elf_face.png", 0, 0);
		MyImage dwarf = new MyImage("../dwarf_face.png", 0, 0);
		MyImage orc = new MyImage("../orc_face.png", 0, 0);
		human.setBounds(SCREEN_WIDTH / 5 - 75, 200, 150, 400);
		elf.setBounds(SCREEN_WIDTH * 2 / 5 - 75, 200, 150, 400);
		dwarf.setBounds(SCREEN_WIDTH * 3 / 5 - 75, 200, 150, 400);
		orc.setBounds(SCREEN_WIDTH * 4 / 5 - 75, 200, 150, 400);
		JTextArea labelHuman = new JTextArea("Attack +2\nDefence +1\nHP +2");
		JTextArea labelElf = new JTextArea("HP +5");
		JTextArea labelDwarf = new JTextArea("Defence +5");
		JTextArea labelOrc = new JTextArea("Attack +5");
		labelHuman.setBounds(SCREEN_WIDTH / 5 - 75, SCREEN_HEIGHT - 200, 150, 100);
		labelElf.setBounds(SCREEN_WIDTH * 2 / 5 - 75, SCREEN_HEIGHT - 200, 150, 100);
		labelDwarf.setBounds(SCREEN_WIDTH * 3 / 5 - 75, SCREEN_HEIGHT - 200, 150, 100);
		labelOrc.setBounds(SCREEN_WIDTH * 4 / 5 - 75, SCREEN_HEIGHT - 200, 150, 100);
		labelHuman.setEditable(false);
		labelElf.setEditable(false);
		labelDwarf.setEditable(false);
		labelOrc.setEditable(false);
		add(labelHuman);
		add(labelElf);
		add(labelDwarf);
		add(labelOrc);
		add(human);
		add(elf);
		add(dwarf);
		add(orc);
		add(chooseHuman);
		add(chooseElf);
		add(chooseOrk);
		add(chooseDwarf);
		repaint();
	}

	public void drawChooseClass() {
		exitLoop = false;
		clearGUI();
		JButton chooseWarrior = new JButton("Warrior");
		JButton chooseMage = new JButton("Mage");
		JButton chooseThief = new JButton("Thief");
		JButton chooseHunter = new JButton("Hunter");
		chooseWarrior.addActionListener(new ChooseWarriorButtonListener());
		chooseMage.addActionListener(new ChooseMageButtonListener());
		chooseThief.addActionListener(new ChooseThiefButtonListener());
		chooseHunter.addActionListener(new ChooseHunterButtonListener());
		chooseWarrior.setBounds(SCREEN_WIDTH / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		chooseMage.setBounds(SCREEN_WIDTH * 2 / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		chooseThief.setBounds(SCREEN_WIDTH  * 3 / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		chooseHunter.setBounds(SCREEN_WIDTH * 4 / 5 - 75, SCREEN_HEIGHT - 100, 150, 30);
		MyImage sword = new MyImage("../sword_icon.png", 0, 0);
		MyImage staff = new MyImage("../staff_icon.png", 0, 0);
		MyImage knife = new MyImage("../knife_icon.png", 0, 0);
		MyImage bow = new MyImage("../bow_icon.png", 0, 0);
		sword.setBounds(SCREEN_WIDTH / 5 - 75, 200, 150, 400);
		staff.setBounds(SCREEN_WIDTH * 2 / 5 - 75, 200, 150, 400);
		knife.setBounds(SCREEN_WIDTH * 3 / 5 - 75, 200, 150, 400);
		bow.setBounds(SCREEN_WIDTH * 4 / 5 - 75, 200, 150, 400);
		add(sword);
		add(staff);
		add(knife);
		add(bow);
		add(chooseWarrior);
		add(chooseMage);
		add(chooseHunter);
		add(chooseThief);
		repaint();
	}

	public void drawChooseName() {
		exitLoop = false;
		clearGUI();
		JButton chooseName = new JButton("Choose name");
		chooseName.addActionListener(new ChooseNameButtonListener());
		chooseName.setBounds(SCREEN_WIDTH / 2 - 75, SCREEN_HEIGHT - 100, 150, 30);
		textInput.setBounds(SCREEN_WIDTH / 2 - 75, SCREEN_HEIGHT - 150, 150, 30);
		errorOutput.setBounds(SCREEN_WIDTH / 2 + 75, SCREEN_HEIGHT - 150, 300, 30);
		errorOutput.setForeground(Color.red);
		add(chooseName);
		add(textInput);
		add(errorOutput);
		repaint();
	}

	private String getParseMap(Map map) {
		String mapString = "";
		for (int i = 0; i < map.getSize(); ++i) {
			for (int j = 0; j < map.getSize(); ++j) {
				if (player.getPosX() == j && player.getPosY() == i)
					mapString = mapString.concat("P    ");
				else
					mapString = mapString.concat(map.getMapCell(i, j) + "    ");
			}
			mapString = mapString.concat("\n");
		}
		return mapString;
	}

	public void drawMap() {
		exitLoop = false;
		clearGUI();
		JButton moveUp = new JButton("Move up");
		JButton moveDown = new JButton("Move down");
		JButton moveLeft = new JButton("Move left");
		JButton moveRight = new JButton("Move right");
		JButton info = new JButton("Player info");
		mapLabel = new JTextArea(getParseMap(map));
		mapLabel.setEnabled(false);
		moveUp.setBounds(SCREEN_WIDTH / 2 - 65, SCREEN_HEIGHT - 150, 120, 30);
		moveDown.setBounds(SCREEN_WIDTH / 2 - 65, SCREEN_HEIGHT - 100, 120, 30);
		moveLeft.setBounds(SCREEN_WIDTH / 2 - 120, SCREEN_HEIGHT - 125, 120, 30);
		moveRight.setBounds(SCREEN_WIDTH / 2 - 10, SCREEN_HEIGHT - 125, 120, 30);
		info.setBounds(SCREEN_WIDTH - 200, SCREEN_HEIGHT - 125, 150, 30);
		moveUp.addActionListener(new MovePlayerUpButtonListener());
		moveDown.addActionListener(new MovePlayerDownButtonListener());
		moveLeft.addActionListener(new MovePlayerLeftButtonListener());
		moveRight.addActionListener(new MovePlayerRightButtonListener());
		mapLabel.setBounds(SCREEN_WIDTH / 2 - map.getSize() * 11, SCREEN_HEIGHT / 2 - map.getSize() * 11 - 100, map.getSize() * 22, map.getSize() * 22);
		info.addActionListener(new InfoButtonListener());
		add(info);
		add(mapLabel);
		add(moveUp);
		add(moveDown);
		add(moveLeft);
		add(moveRight);
		repaint();
	}

	public void updateMap(Map map) {
		mapLabel.setBounds(SCREEN_WIDTH / 2 - map.getSize() * 11, SCREEN_HEIGHT / 2 - map.getSize() * 11 - 100, map.getSize() * 22, map.getSize() * 22);
		mapLabel.setText(getParseMap(map));
		repaint();
	}

	public void drawPlayer() {
		JOptionPane.showMessageDialog(null,  ("Name: " + player.getName()
		+ "\nRace: " + player.getPlayerRace().getName()
		+ "\nClass: " + player.getPlayerClass()
		+ "\nHP: " + player.getHealthPoints()
		+ "\nLevel: " + player.getLevel()
		+ "\nExp: " + player.getExpPoints()
		+ "\nAttack: " + player.getAttack()
		+ "\nDefence: " + player.getDefence()
		+ "\nWeapon: " + ((player.getWeapon() != null) ? player.getWeapon().getName() : "-")
		+ "\nArmor: " + ((player.getArmor() != null) ? player.getArmor().getName() : "-")
		+ "\nHelmet: " + ((player.getHelmet() != null) ? player.getHelmet().getName() : "-")),
				"Player info",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void drawCheckPlayerWantsToBattle() {
		checkLoop = 2;
		exitLoop = false;
		clearGUI();
		JLabel label = new JLabel("Do you want to battle?");
		JButton yesButton = new JButton("Yes");
		JButton noButton = new JButton("No");
		yesButton.addActionListener(new YesButtonListener());
		noButton.addActionListener(new NoButtonListener());
		label.setBounds(SCREEN_WIDTH / 2 - 75, SCREEN_HEIGHT - 150, 150, 30);
		yesButton.setBounds(SCREEN_WIDTH / 2 - 75, SCREEN_HEIGHT - 100, 70, 30);
		noButton.setBounds(SCREEN_WIDTH / 2 - 15, SCREEN_HEIGHT - 100, 70, 30);
		add(label);
		add(yesButton);
		add(noButton);
		repaint();
	}

	public void drawDie() {
		JOptionPane.showMessageDialog(null,  "YOU DIED!", "Player info",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void drawWin() {
		JOptionPane.showMessageDialog(null,  "YOU WIN!", "Player info",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void drawGetNothing() {
		JOptionPane.showMessageDialog(null,  "You got nothing!", "Loot info",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public int drawGetPrize(String type) {
		return JOptionPane.showConfirmDialog(null,  "Do you want to keep :" + type + "?", "Loot info",
				JOptionPane.YES_NO_OPTION);
	}

	private class ChooseNameButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setName(textInput.getText());
			Set<ConstraintViolation<Creature>> violations;
			Validator validator = Main.factory.getValidator();
			validator.validate(player);
			violations = validator.validate(player);
			if (violations.size() == 0)
				exitLoop = true;
			else
				errorOutput.setText("Wrong name! Must be 3-10 characters.");
		}
	}

	private class ChooseFirstButtonListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 1;
		}
	}

	private class ChooseSecondButtonListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 2;
		}
	}

	private class ChooseThirdButtonListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 3;
		}
	}

	private class ChooseFourthButtonListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 4;
		}
	}

	private class ChooseNewButtonListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 5;
		}
	}

	private class InfoButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			drawPlayer();
		}
	}

	private class YesButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 0;
		}
	}

	private class NoButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLoop = 1;
		}
	}

	private class MovePlayerUpButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.decPosY();
			exitLoop = true;
		}
	}

	private class MovePlayerDownButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.incPosY(map);
			exitLoop = true;
		}
	}

	private class MovePlayerLeftButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.decPosX();
			exitLoop = true;
		}
	}

	private class MovePlayerRightButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.incPosX(map);
			exitLoop = true;
		}
	}

	private class ChooseHumanButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerRace(new HumanRace());
			exitLoop = true;
		}
	}

	private class ChooseElfButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerRace(new ElfRace());
			exitLoop = true;
		}
	}

	private class ChooseDwarfButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerRace(new DwarfRace());
			exitLoop = true;
		}
	}

	private class ChooseOrkButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerRace(new OrkRace());
			exitLoop = true;
		}
	}

	private class ChooseWarriorButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerClass(PlayerClasses.WARRIOR);
			exitLoop = true;
		}
	}

	private class ChooseMageButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerClass(PlayerClasses.MAGE);
			exitLoop = true;
		}
	}

	private class ChooseThiefButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerClass(PlayerClasses.THIEF);
			exitLoop = true;
		}
	}

	private class ChooseHunterButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.setPlayerClass(PlayerClasses.HUNTER);
			exitLoop = true;
		}
	}

	private class EnterButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			exitLoop = true;
		}
	}

    private class SwitchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Main.gameMode = !Main.gameMode;
			setVisibility(Main.gameMode);
		}
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getCheckLoop() {
		return checkLoop;
	}
}
