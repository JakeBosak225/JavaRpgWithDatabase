package com.javarpg.client;

import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.javarpg.model.Player;
import com.javarpg.model.jdbc.JDBCPlayerDAO;

public class RpgCLI {

	BasicDataSource dataSource;
	JDBCPlayerDAO jdbcPlayerDao;
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		RpgCLI game = new RpgCLI();
		game.runMainMenu();
	}

	public RpgCLI() {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/javarpg");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		jdbcPlayerDao = new JDBCPlayerDAO(dataSource);
	}

	private void runMainMenu() {
		boolean stillProcess = true;

		while (stillProcess) {
			System.out.println("~~~~ Java RPG Main Menu ~~~~");
			System.out.println("[N]ew Game");
			System.out.println("[L]oad Game");
			System.out.println("[D]elete File");
			System.out.println("[E]xit");
			String userInput = scanner.nextLine();

			Player player = null;
			Player playerMethods = new Player(dataSource);
			RpgApp app = new RpgApp(dataSource);

			switch (userInput.toUpperCase()) {
			case "N":
				player = playerMethods.createNewPlayer();

				if (player != null) {
					System.out.println("Starting Game....");
					app.playGame(player);
					stillProcess = false;
				}
				break;

			case "L":
				player = playerMethods.loadPlayer();

				if (player != null) {
					System.out.println("Starting Game....");
					app.playGame(player);
					stillProcess = false;
				}
				break;

			case "D":
				playerMethods.deleteSaveFile();
				break;

			case "E":
				System.out.println("You rest at the bondfire... things seem safe, for now......");
				System.exit(0);
				break;

			default:
				System.out.println("Please enter a valid input!");
				break;
			}
		}
	}

}
