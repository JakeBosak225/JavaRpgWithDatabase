package com.javarpg.model;

import java.util.List;

public class Location {

	private int id;
	private String name;
	private String description;
	private int locationToNorthId;
	private int locationToEastId;
	private int locationToSouthId;
	private int locationToWestId;
	private int requiredItemId;
	private int enemyLivingHereId;

	public Location() {
	};

	public Location getLocationById(List<Location> locations, int locationId) {
		for (Location location : locations) {
			if (location.getId() == locationId) {
				return location;
			}
		}

		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLocationToNorthId() {
		return locationToNorthId;
	}

	public void setLocationToNorthId(int locationToNorthId) {
		this.locationToNorthId = locationToNorthId;
	}

	public int getLocationToEastId() {
		return locationToEastId;
	}

	public void setLocationToEastId(int locationToEastId) {
		this.locationToEastId = locationToEastId;
	}

	public int getLocationToSouthId() {
		return locationToSouthId;
	}

	public void setLocationToSouthId(int locationToSouthId) {
		this.locationToSouthId = locationToSouthId;
	}

	public int getLocationToWestId() {
		return locationToWestId;
	}

	public void setLocationToWestId(int locationToWestId) {
		this.locationToWestId = locationToWestId;
	}

	public int getRequiredItemId() {
		return requiredItemId;
	}

	public void setRequiredItemId(int requiredItemId) {
		this.requiredItemId = requiredItemId;
	}

	public int getEnemyLivingHereId() {
		return enemyLivingHereId;
	}

	public void setEnemyLivingHereId(int enemyLivingHereId) {
		this.enemyLivingHereId = enemyLivingHereId;
	}

}
