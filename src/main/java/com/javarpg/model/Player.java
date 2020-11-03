package com.javarpg.model;

import java.util.List;
import java.util.Scanner;
import javax.sql.DataSource;
import com.javarpg.model.jdbc.JDBCPlayerDAO;

public class Player {
	private int playerId;
	private String playerName;
	private int currentHP;
	private int maxHP;
	private int playerExp;
	private int playerLevel;
	private int inventoryId;
	private int currentLocationId;
	private int eqquipedWeaponId;

	private Scanner scanner;
	private JDBCPlayerDAO jdbcPlayerDao;

	public Player() {
	}

	public Player(DataSource dataSource) {
		this.scanner = new Scanner(System.in);
		this.jdbcPlayerDao = new JDBCPlayerDAO(dataSource);
	}

	public Player(String name, int currentHp, int maxHP, int playerExp, int playerLevel, int currentLocationId,
			int eqquipedWeaponId) {
		this.playerName = name;
		this.currentHP = currentHp;
		this.maxHP = maxHP;
		this.playerExp = playerExp;
		this.playerLevel = playerLevel;
		this.currentLocationId = currentLocationId;
		this.eqquipedWeaponId = eqquipedWeaponId;
	}

	public void checkPlayerLevel() {
		int levelBeforeCheck = playerLevel;

		setPlayerLevel((playerExp / 100) + 1);

		if (playerLevel > levelBeforeCheck) {
			System.out.println("You leveled up!!!");
			System.out.println("Max HP increased by 5!");

			setMaxHP(maxHP + 5);
			setCurrentHP(maxHP);
		}
	}

	public Player createNewPlayer() {
		boolean validNameEntered = false;
		String name = "";

		while (!validNameEntered) {
			System.out.println("Welcome Adventurer! Please enter the name of your new save file: ");
			System.out.println("Or type C to cancel");
			name = scanner.nextLine();
			if (name.isEmpty() || name.equals("")) {
				System.out.println("Please enter in a valid name!");
			} else if (name.equalsIgnoreCase("C")) {
				return null;
			} else {
				validNameEntered = true;
			}
		}
		Player player = new Player(name, 10, 10, 0, 1, 1, 0);
		jdbcPlayerDao.createSaveFile(player);

		return player;
	}

	public Player loadPlayer() {
		List<Player> allSaveFiles = jdbcPlayerDao.getAllSaveFiles();

		System.out.println("Which File Would you like to load[ID]: ");
		for (Player player : allSaveFiles) {
			System.out.println(
					player.getPlayerId() + ": " + player.getPlayerName() + " - LVL: " + player.getPlayerLevel());
		}

		String userInput = scanner.nextLine();
		int playerToLoadId;
		try {
			playerToLoadId = Integer.parseInt(userInput);
		} catch (NumberFormatException ex) {
			System.out.println("Please enter a valid input");
			return null;
		}

		Player player = jdbcPlayerDao.loadPlayerById(playerToLoadId);

		return player;
	}

	public void deleteSaveFile() {
		List<Player> allSaveFiles = jdbcPlayerDao.getAllSaveFiles();

		System.out.println("Which File Would you like to delete[ID]: ");
		for (Player player : allSaveFiles) {
			System.out.println(
					player.getPlayerId() + ": " + player.getPlayerName() + " - LVL: " + player.getPlayerLevel());
		}

		String userInput = scanner.nextLine();
		int playerToDeleteId;
		try {
			playerToDeleteId = Integer.parseInt(userInput);
		} catch (NumberFormatException ex) {
			System.out.println("Please enter a valid input");
			return;
		}

		System.out.println(
				"Are you sure you want to delete this save file? Once Deleted there is no way to recover: Y/N");
		String confrimInput = scanner.nextLine();

		if (confrimInput.equalsIgnoreCase("Y")) {
			System.out.println("Deleteing Save file....");
			jdbcPlayerDao.deleteSaveFile(playerToDeleteId);
			return;
		} else {
			System.out.println("Deleting Aborted");
		}
	}

	public String ToString() {
		return playerId + ": " + playerName;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getPlayerExp() {
		return playerExp;
	}

	public void setPlayerExp(int playerExp) {
		this.playerExp = playerExp;
	}

	public int getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getCurrentLocationId() {
		return currentLocationId;
	}

	public void setCurrentLocationId(int currentLocationId) {
		this.currentLocationId = currentLocationId;
	}

	public int getEqquipedWeaponId() {
		return eqquipedWeaponId;
	}

	public void setEqquipedWeaponId(int eqquipedWeaponId) {
		this.eqquipedWeaponId = eqquipedWeaponId;
	}

}
