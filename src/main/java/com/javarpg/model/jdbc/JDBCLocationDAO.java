package com.javarpg.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.javarpg.model.Location;

public class JDBCLocationDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCLocationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Location> getAllLocation(){
		String query = "SELECT * FROM locations";
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(query);

		List<Location> locations = new ArrayList<>();
		while (rowset.next()) {
			Location location = mapRowToLocation(rowset);
			locations.add(location);
		}

		return locations;
	}
	
	private Location mapRowToLocation(SqlRowSet row) {
		Location location = new Location();
		location.setId(row.getInt("location_id"));
		location.setName(row.getString("location_name"));
		location.setDescription(row.getString("location_description"));
		location.setLocationToNorthId(row.getInt("location_to_north"));
		location.setLocationToEastId(row.getInt("location_to_east"));
		location.setLocationToSouthId(row.getInt("location_to_south"));
		location.setLocationToWestId(row.getInt("location_to_west"));
		location.setRequiredItemId(row.getInt("item_needed_to_enter"));
		location.setEnemyLivingHereId(row.getInt("enemy_living_here"));
		
		return location;
	}

}
