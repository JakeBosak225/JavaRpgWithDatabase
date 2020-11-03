package com.javarpg.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.javarpg.model.Enemy;

public class JDBCEnemyDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEnemyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Enemy> getAllEnemies() {
		String query = "SELECT * FROM enemy";
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(query);

		List<Enemy> enimies = new ArrayList<>();
		while (rowset.next()) {
			Enemy department = mapRowToEnemy(rowset);
			enimies.add(department);
		}

		return enimies;
	}

	private Enemy mapRowToEnemy(SqlRowSet row) {
		Enemy enemy = new Enemy();
		enemy.setEmenyId(row.getInt("enemy_id"));
		enemy.setName(row.getString("enemy_name"));
		enemy.setCurrentHp(row.getInt("enemy_current_hp"));
		enemy.setMaxAttack(row.getInt("enemy_max_hp"));
		enemy.setMinAttack(row.getInt("enemy_min_attack"));
		enemy.setMaxAttack(row.getInt("enemy_max_attack"));
		enemy.setMinRewardGold(row.getInt("enemy_min_reward_gold"));
		enemy.setMaxRewardGold(row.getInt("enemy_max_reward_gold"));
		enemy.setMinRewardEXP(row.getInt("enemy_min_reward_exp"));
		enemy.setMaxRewardEXP(row.getInt("enemy_max_reward_exp"));

		return enemy;
	}

}
