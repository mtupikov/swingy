package com.swingy.database;

import com.swingy.Main;
import com.swingy.model.Creature;
import com.swingy.model.PlayerClasses;
import com.swingy.model.armor.*;
import com.swingy.model.helmets.*;
import com.swingy.model.races.DwarfRace;
import com.swingy.model.races.ElfRace;
import com.swingy.model.races.HumanRace;
import com.swingy.model.races.OrkRace;
import com.swingy.model.weapons.*;
import java.sql.*;
import java.util.ArrayList;

public class GameDB {
	private static final String 		DB_NAME =		"swingy.db";
	private static final String 		PLAYERS =		"players";
	private static final String 		K_ID =			"id";
	private static final String 		K_NAME = 		"name";
	private static final String 		K_RACE =		"race";
	private static final String 		K_CLASS =		"class";
	private static final String 		K_LEVEL =		"level";
	private static final String 		K_HP =			"hp";
	private static final String 		K_MAX_HP =		"max_hp";
	private static final String 		K_XP =			"xp";
	private static final String 		K_ATTACK =		"attack";
	private static final String 		K_WEAPON =		"weapon";
	private static final String 		K_ARMOR =		"armor";
	private static final String 		K_HELMET =		"helmet";
	private static final String 		CREATE_TABLE =	"CREATE TABLE IF NOT EXISTS " + PLAYERS + " (" + K_ID +
														" INTEGER PRIMARY KEY, " + K_NAME + " TEXT, " +
														K_RACE + " TEXT, " + K_CLASS + " TEXT, " +
														K_LEVEL + " INTEGER, " + K_HP + " INTEGER, " +
														K_MAX_HP + " INTEGER, " + K_XP + " INTEGER, " +
														K_ATTACK + " INTEGER, " + K_WEAPON + " TEXT, " +
														K_ARMOR + " TEXT, " + K_HELMET + " TEXT)";
	private static final String 		INSERT_PLAYER = "INSERT INTO " + PLAYERS + " (" + K_NAME + ", " +
														K_RACE + ", " + K_CLASS + ", " +
														K_LEVEL + ", " + K_HP + ", " +
														K_MAX_HP + ", " + K_XP + ", " +
														K_ATTACK + ", " + K_WEAPON + ", " +
														K_ARMOR + ", " + K_HELMET + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String 		GET_PLAYERS =	"SELECT * FROM " + PLAYERS;
	private static final String 		UPDATE_PLAYER =	"UPDATE " + PLAYERS + " SET " + K_LEVEL + " = ?, " +
														K_HP + " = ?, " + K_MAX_HP + " = ?, " +
														K_XP +" = ?, " + K_ATTACK + " = ?, " +
														K_WEAPON + " = ?, " + K_ARMOR + " = ?, " +
														K_HELMET + " = ? WHERE " + K_NAME + " = ?";
	private static final String 		DELETE_PLAYER =	"DELETE FROM " + PLAYERS + " WHERE " + K_NAME + " = ?";
	private static final String			DELETE_TABLE =	"DROP TABLE IF EXISTS " + PLAYERS;
	private static final String 		SQLITE_DR =		"org.sqlite.JDBC";
	private static final String 		SQLITE_URL =	"jdbc:sqlite:" + DB_NAME;

	private static Connection			connection;
	private static Statement			statement;
	private static ResultSet			resultSet;
	private static PreparedStatement	preparedStatement;

	private static Connection connectToDB() {
		try {
			Class.forName(SQLITE_DR);
			connection = DriverManager.getConnection(SQLITE_URL);
			statement = connection.createStatement();
			statement.executeUpdate(CREATE_TABLE);
		} catch (SQLException | ClassNotFoundException e) {
			Main.printError("Connection to DB failure: " + e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					Main.printError(e.getMessage());
				}
			}
		}
		return connection;
	}

	private static boolean isUniquePlayer(Creature player) throws SQLException {
		statement = connection.createStatement();
		resultSet = statement.executeQuery(GET_PLAYERS);
		while (resultSet.next())
			if (player.getName().equals(resultSet.getString(K_NAME)))
				return false;
		return true;
	}

	public static void insertHero(Creature player) {
		try {
			connection = connectToDB();
			if (!isUniquePlayer(player))
				Main.printError("Player already exist.");
			else {
				preparedStatement = connection.prepareStatement(INSERT_PLAYER);
				preparedStatement.setString(1, player.getName());
				preparedStatement.setString(2, player.getPlayerRace().getName());
				preparedStatement.setString(3, player.getPlayerClass().name());
				preparedStatement.setInt(4, player.getLevel());
				preparedStatement.setInt(5, player.getHealthPoints());
				preparedStatement.setInt(6, player.getMaxHealthPoints());

				preparedStatement.setInt(7, player.getExpPoints());
				preparedStatement.setInt(8, player.getAttack());
				if (player.getWeapon() != null)
					preparedStatement.setString(9, player.getWeapon().getName());
				else
					preparedStatement.setString(9, "-");
				if (player.getArmor() != null)
					preparedStatement.setString(10, player.getArmor().getName());
				else
					preparedStatement.setString(10, "-");
				if (player.getHelmet() != null)
					preparedStatement.setString(11, player.getHelmet().getName());
				else
					preparedStatement.setString(11, "-");
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			Main.printError(e.getMessage());
		} finally {
			closeDB();
		}
	}

	public static ArrayList<Creature> getDB() {
		try {
			ArrayList<Creature> players = new ArrayList<>();
			connection = connectToDB();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_PLAYERS);
			while (resultSet.next()) {
				Creature player = new Creature();
				switch (resultSet.getString(K_RACE).toLowerCase()) {
					case "human":
						player.setPlayerRaceDefault(new HumanRace());
						break;
					case "elf":
						player.setPlayerRaceDefault(new ElfRace());
						break;
					case "dwarf":
						player.setPlayerRaceDefault(new DwarfRace());
						break;
					case "orc":
						player.setPlayerRaceDefault(new OrkRace());
						break;
				}
				switch (resultSet.getString(K_CLASS).toLowerCase()) {
					case "warrior":
						player.setPlayerClass(PlayerClasses.WARRIOR);
						break;
					case "mage":
						player.setPlayerClass(PlayerClasses.MAGE);
						break;
					case "thief":
						player.setPlayerClass(PlayerClasses.THIEF);
						break;
					case "hunter":
						player.setPlayerClass(PlayerClasses.HUNTER);
						break;
				}
				player.setName(resultSet.getString(K_NAME));
				player.setLevel(resultSet.getInt(K_LEVEL));
				player.setHealthPoints(resultSet.getInt(K_HP));
				player.setMaxHealthPoints(resultSet.getInt(K_MAX_HP));
				player.setExpPoints(resultSet.getInt(K_XP));
				player.setAttack(resultSet.getInt(K_ATTACK));
				switch (resultSet.getString(K_WEAPON).toLowerCase()) {
					case "-":
						player.setWeapon(new Fists());
						break;
					case "fists":
						player.setWeapon(new Fists());
						break;
					case "bronze sword":
						player.setWeapon(new BronzeSword());
						break;
					case "bronze staff":
						player.setWeapon(new BronzeStaff());
						break;
					case "bronze knife":
						player.setWeapon(new BronzeKnife());
						break;
					case "bronze bow":
						player.setWeapon(new BronzeBow());
						break;
					case "wood sword":
						player.setWeapon(new WoodSword());
						break;
					case "wood staff":
						player.setWeapon(new WoodStaff());
						break;
					case "wood knife":
						player.setWeapon(new WoodKnife());
						break;
					case "wood bow":
						player.setWeapon(new WoodBow());
						break;
					case "steel sword":
						player.setWeapon(new SteelSword());
						break;
					case "steel staff":
						player.setWeapon(new SteelStaff());
						break;
					case "steel knife":
						player.setWeapon(new SteelKnife());
						break;
					case "steel bow":
						player.setWeapon(new SteelBow());
						break;
					case "dragon sword":
						player.setWeapon(new DragonSword());
						break;
					case "dragon staff":
						player.setWeapon(new DragonStaff());
						break;
					case "dragon knife":
						player.setWeapon(new DragonKnife());
						break;
					case "dragon bow":
						player.setWeapon(new DragonBow());
						break;
					case "diamond sword":
						player.setWeapon(new DiamondSword());
						break;
					case "diamond staff":
						player.setWeapon(new DiamondStaff());
						break;
					case "diamond knife":
						player.setWeapon(new DiamondKnife());
						break;
					case "diamond bow":
						player.setWeapon(new DiamondBow());
						break;
					case "super sword":
						player.setWeapon(new SuperSword());
						break;
					case "super staff":
						player.setWeapon(new SuperStaff());
						break;
					case "super knife":
						player.setWeapon(new SuperKnife());
						break;
					case "super bow":
						player.setWeapon(new SuperBow());
						break;
					default:
						player.setWeapon(new Fists());
						break;
				}
				switch (resultSet.getString(K_ARMOR).toLowerCase()) {
					case "-":
						player.setArmor(null);
						break;
					case "wood shell":
						player.setArmor(new WoodShell());
						break;
					case "bronze shell":
						player.setArmor(new BronzeShell());
						break;
					case "steel shell":
						player.setArmor(new SteelShell());
						break;
					case "dragon shell":
						player.setArmor(new DragonShell());
						break;
					case "diamond shell":
						player.setArmor(new DiamondShell());
						break;
					case "super shell":
						player.setArmor(new SuperShell());
						break;
					default:
						player.setArmor(null);
						break;
				}
				switch (resultSet.getString(K_HELMET).toLowerCase()) {
					case "-":
						player.setHelmetDefault(null);
						break;
					case "wood helmet":
						player.setHelmetDefault(new WoodHelmet());
						break;
					case "bronze helmet":
						player.setHelmetDefault(new BronzeHelmet());
						break;
					case "steel helmet":
						player.setHelmetDefault(new SteelHelmet());
						break;
					case "dragon helmet":
						player.setHelmetDefault(new DragonHelmet());
						break;
					case "diamond helmet":
						player.setHelmetDefault(new DiamondHelmet());
						break;
					case "super helmet":
						player.setHelmetDefault(new SuperHelmet());
						break;
					default:
						player.setHelmetDefault(null);
					break;
				}
				players.add(player);
			}
			for (int i = players.size(); i < 4; ++i)
				players.add(null);
			return players;
		} catch (SQLException e) {
			Main.printError(e.getMessage());
		} finally {
			closeDB();
		}
		return null;
	}

	public static void updateHero(Creature player) {
		try {
			connection = connectToDB();
			preparedStatement = connection.prepareStatement(UPDATE_PLAYER);
			preparedStatement.setInt(1, player.getLevel());
			preparedStatement.setInt(2, player.getHealthPoints());
			preparedStatement.setInt(3, player.getMaxHealthPoints());
			preparedStatement.setInt(4, player.getExpPoints());
			preparedStatement.setInt(5, player.getAttack());
			if (player.getWeapon() != null)
				preparedStatement.setString(6, player.getWeapon().getName());
			else
				preparedStatement.setString(6, "-");
			if (player.getArmor() != null)
				preparedStatement.setString(7, player.getArmor().getName());
			else
				preparedStatement.setString(7, "-");
			if (player.getHelmet() != null)
				preparedStatement.setString(8, player.getHelmet().getName());
			else
				preparedStatement.setString(8, "-");
			preparedStatement.setString(9, player.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Main.printError(e.getMessage());
		} finally {
			closeDB();
		}
	}

	public static void deleteHero(String name) {
		try {
			connection = connectToDB();
			preparedStatement = connection.prepareStatement(DELETE_PLAYER);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Main.printError(e.getMessage());
		} finally {
			closeDB();
		}
	}

	public static void deleteTable() {
		try {
			connection = connectToDB();
			statement = connection.createStatement();
			statement.executeUpdate(DELETE_TABLE);
		} catch (SQLException e) {
			Main.printError(e.getMessage());
		} finally {
			closeDB();
		}
	}

	private static void closeDB() {
		try {
			if (statement != null && !statement.isClosed())
				statement.close();
			if (resultSet != null && !resultSet.isClosed())
				resultSet.close();
			if (preparedStatement != null && !preparedStatement.isClosed())
				preparedStatement.close();
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (Exception e) {
			Main.printError(e.getMessage());
		}
	}
}
