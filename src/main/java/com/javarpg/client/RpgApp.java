package com.javarpg.client;

import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import com.javarpg.model.Enemy;
import com.javarpg.model.Location;
import com.javarpg.model.Player;
import com.javarpg.model.jdbc.JDBCEnemyDAO;
import com.javarpg.model.jdbc.JDBCLocationDAO;
import com.javarpg.model.jdbc.JDBCPlayerDAO;

public class RpgApp {

	List<Enemy> enimies;
	List<Location> locations;

	JDBCPlayerDAO jdbcPlayerDao;
	JDBCEnemyDAO jdbcEnemyDao;
	JDBCLocationDAO jdbcLocationDao;

	Scanner scanner = new Scanner(System.in);

	public RpgApp(DataSource dataSource) {
		jdbcEnemyDao = new JDBCEnemyDAO(dataSource);
		jdbcLocationDao = new JDBCLocationDAO(dataSource);
		jdbcPlayerDao = new JDBCPlayerDAO(dataSource);

		enimies = jdbcEnemyDao.getAllEnemies();
		locations = jdbcLocationDao.getAllLocation();
	}

	public void playGame(Player player) {
		Scanner scanner = new Scanner(System.in);

		boolean continueProcess = true;
		while (continueProcess) {
			// Display Player Main Menu
			player.checkPlayerLevel();
			displayStats(player);

			System.out.println("Please make a selection on what you would like to do next: ");
			System.out.println("[M]ove");
			System.out.println("[I]nventory");
			System.out.println("[S]ave and Exit");

			String userInput = scanner.nextLine();

			switch (userInput.toUpperCase()) {
			case "M":
				movePlayer(player);
				break;

			case "I":
				// viewInventory();
				break;

			case "S":
				jdbcPlayerDao.savePlayerData(player);
				continueProcess = false;
				break;

			default:
				System.out.println("Please enter a valid input!");
				break;
			}
		}
	}

	private void displayStats(Player player) {
		Location location = new Location();
		location = location.getLocationById(locations, player.getCurrentLocationId());

		System.out.println("----Player Stats----");
		System.out.println(player.getPlayerName() + " - HP: " + player.getCurrentHP() + "/" + player.getMaxHP());
		System.out.println(
				"Level: " + player.getPlayerLevel() + " - Exp till level-up: " + (100 - (player.getPlayerExp() % 100)));
		System.out.println("Current Location: " + location.getName());
		System.out.println("Equipped Weapon:  ");
	}

	private void movePlayer(Player player) {
		Location currentLocation = new Location();
		currentLocation = currentLocation.getLocationById(locations, player.getCurrentLocationId());

		Location locationToNorth = new Location();
		Location locationToEast = new Location();
		Location locationToSouth = new Location();
		Location locationToWest = new Location();

		System.out.println("Please pick a direction to move");

		if (currentLocation.getLocationToNorthId() != 0) {
			locationToNorth = locationToNorth.getLocationById(locations, currentLocation.getLocationToNorthId());
			System.out.println("[N]orth: " + locationToNorth.getName());
		}

		if (currentLocation.getLocationToEastId() != 0) {
			locationToEast = locationToEast.getLocationById(locations, currentLocation.getLocationToEastId());
			System.out.println("[E]ast: " + locationToEast.getName());
		}

		if (currentLocation.getLocationToSouthId() != 0) {
			locationToSouth = locationToSouth.getLocationById(locations, currentLocation.getLocationToSouthId());
			System.out.println("[S]outh: " + locationToSouth.getName());
		}

		if (currentLocation.getLocationToWestId() != 0) {
			locationToWest = locationToWest.getLocationById(locations, currentLocation.getLocationToWestId());
			System.out.println("[W]est " + locationToWest.getName());
		}

		System.out.println("Or -B- to go back");
		String userInput = scanner.nextLine();

		switch (userInput.toUpperCase()) {
		case "N":
			if (currentLocation.getLocationToNorthId() != 0) {
				newLocationCheck(player, locationToNorth);

			} else {
				System.out.println("You path is block to the North. Returning to Player Menu");
			}
			break;

		case "E":
			if (currentLocation.getLocationToEastId() != 0) {
				newLocationCheck(player, locationToEast);
			} else {
				System.out.println("You path is block to the East. Returning to Player Menu");
			}
			break;

		case "S":
			if (currentLocation.getLocationToSouthId() != 0) {
				newLocationCheck(player, locationToSouth);
			} else {
				System.out.println("You path is block to the South. Returning to Player Menu");
			}
			break;

		case "W":
			if (currentLocation.getLocationToWestId() != 0) {
				newLocationCheck(player, locationToWest);
			} else {
				System.out.println("You path is block to the West. Returning to Player Menu");
			}
			break;

		case "B":
			System.out.println("Returning to Player Menu...");
			break;

		default:
			System.out.println("Please enter a valid input");
		}
	}

	public void newLocationCheck(Player player, Location newLocation) {
		System.out.println("You travel to " + newLocation.getName());
		System.out.println(newLocation.getDescription());
		
		
		// Check if item has an item required To Enter
		if (newLocation.getRequiredItemId() != 0) {
			// TODO: Item Required To Enter Check
		}

		// Check if location is populated by Enemy
		if (newLocation.getEnemyLivingHereId() != 0) {
			// TODO: Enemy Battle
		}

		player.setCurrentLocationId(newLocation.getId());
	}

}
