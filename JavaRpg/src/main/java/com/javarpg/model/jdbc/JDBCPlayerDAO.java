package com.javarpg.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.javarpg.model.Player;

public class JDBCPlayerDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCPlayerDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Player> getAllSaveFiles() {
		String query = "SELECT * FROM player";
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(query);

		List<Player> result = new ArrayList<>();
		while (rowset.next()) {
			Player players = mapRowToPlayer(rowset);
			result.add(players);
		}

		return result;
	}

	public Player createSaveFile(Player player) {
		String query = "INSERT INTO player VALUES(?,?,?,?,?,?,?,?,?)";
		int newId = getNextPlayerId();
		jdbcTemplate.update(query, newId, player.getPlayerName(), player.getCurrentHP(), player.getMaxHP(),
				player.getPlayerExp(), player.getPlayerLevel(), newId, player.getCurrentLocationId(),
				player.getEqquipedWeaponId());
		player.setPlayerId(newId);
		player.setInventoryId(newId);

		return player;
	}

	public Player loadPlayerById(int id) {
		String query = "SELECT * FROM player WHERE player_id = ?";
		SqlRowSet playerToLoad = jdbcTemplate.queryForRowSet(query, id);

		if (playerToLoad.next()) {
			Player player = mapRowToPlayer(playerToLoad);
			return player;
		}

		System.out.println("Invalid ID, please try again.");
		return null;
	}

	public void deleteSaveFile(int id) {
		String query = "DELETE FROM player WHERE player_id = ?";
		jdbcTemplate.update(query, id);
	}

	private Player mapRowToPlayer(SqlRowSet row) {
		Player player = new Player();
		player.setPlayerId(row.getInt("player_id"));
		player.setPlayerName(row.getString("player_name"));
		player.setCurrentHP(row.getInt("current_HP"));
		player.setMaxHP(row.getInt("max_HP"));
		player.setPlayerExp(row.getInt("player_exp"));
		player.setPlayerLevel(row.getInt("player_level"));
		player.setInventoryId(row.getInt("inventory_id"));
		player.setCurrentLocationId(row.getInt("current_location_id"));
		player.setEqquipedWeaponId(row.getInt("currently_equipped_weapon"));

		return player;
	}

	private int getNextPlayerId() {
		String query = "SELECT nextval('player_player_id_seq')";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);

		if (rowSet.next()) {
			return rowSet.getInt(1);
		}

		return 0;
	}

}
