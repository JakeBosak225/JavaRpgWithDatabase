package com.javarpg.client;

import java.util.List;

import javax.sql.DataSource;

import com.javarpg.model.Enemy;
import com.javarpg.model.Location;
import com.javarpg.model.Player;
import com.javarpg.model.jdbc.JDBCEnemyDAO;
import com.javarpg.model.jdbc.JDBCLocationDAO;

public class RpgApp {

	List<Enemy> enimies;
	List<Location> locations;

	JDBCEnemyDAO jdbcEnemyDao;
	JDBCLocationDAO jdbcLocationDao;

	public RpgApp(DataSource dataSource) {
		jdbcEnemyDao = new JDBCEnemyDAO(dataSource);
		jdbcLocationDao = new JDBCLocationDAO(dataSource);

		enimies = jdbcEnemyDao.getAllEnemies();
		locations = jdbcLocationDao.getAllLocation();
	}

	public void playGame(Player player) {
		displayStats(player);

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

}
