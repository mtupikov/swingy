package com.swingy;

import com.swingy.database.GameDB;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class Main {
	public static Configuration<?> config = Validation.byDefaultProvider().configure();
	public static ValidatorFactory factory = config.buildValidatorFactory();

	public static volatile boolean gameMode;

	private static void		printUsage() {
		System.out.println("Usage: java -jar swingy.jar [console || gui || delete]");
	}

	public static void		printError(String message) {
		System.err.println(message);
		System.exit(2);
	}

	public static void		main(String[] args) {
		System.out.print("\033[H\033[2J");
		if (args.length == 1 && args[0].equals("console")) {
			gameMode = false;
			Game.mainLoop();
		} else if (args.length == 1 && args[0].equals("gui")) {
			gameMode = true;
			Game.mainLoop();
		} else if (args.length == 1 && args[0].equals("delete")) {
			GameDB.deleteTable();
		} else
			printUsage();
		factory.close();
	}
}
