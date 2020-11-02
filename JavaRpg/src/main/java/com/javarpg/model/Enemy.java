package com.javarpg.model;

public class Enemy {
	private int emenyId;
	private String name;
	private int currentHp;
	private int maxHp;
	private int minAttack;
	private int maxAttack;
	private int minRewardGold;
	private int maxRewardGold;
	private int minRewardEXP;
	private int maxRewardEXP;

	public Enemy() {
	}

	public Enemy(String name, int currentHp, int maxHp, int minAttack, int maxAttack, int minRewardGold,
			int maxRewardGold, int minRewardEXP, int maxRewardEXP) {
		super();
		this.name = name;
		this.currentHp = currentHp;
		this.maxHp = maxHp;
		this.minAttack = minAttack;
		this.maxAttack = maxAttack;
		this.minRewardGold = minRewardGold;
		this.maxRewardGold = maxRewardGold;
		this.minRewardEXP = minRewardEXP;
		this.maxRewardEXP = maxRewardEXP;
	}

	public int getEmenyId() {
		return emenyId;
	}

	public void setEmenyId(int emenyId) {
		this.emenyId = emenyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMinAttack() {
		return minAttack;
	}

	public void setMinAttack(int minAttack) {
		this.minAttack = minAttack;
	}

	public int getMaxAttack() {
		return maxAttack;
	}

	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}

	public int getMinRewardGold() {
		return minRewardGold;
	}

	public void setMinRewardGold(int minRewardGold) {
		this.minRewardGold = minRewardGold;
	}

	public int getMaxRewardGold() {
		return maxRewardGold;
	}

	public void setMaxRewardGold(int maxRewardGold) {
		this.maxRewardGold = maxRewardGold;
	}

	public int getMinRewardEXP() {
		return minRewardEXP;
	}

	public void setMinRewardEXP(int minRewardEXP) {
		this.minRewardEXP = minRewardEXP;
	}

	public int getMaxRewardEXP() {
		return maxRewardEXP;
	}

	public void setMaxRewardEXP(int maxRewardEXP) {
		this.maxRewardEXP = maxRewardEXP;
	}

}
