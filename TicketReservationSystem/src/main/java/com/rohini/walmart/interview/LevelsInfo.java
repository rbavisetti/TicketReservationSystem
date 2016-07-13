package com.rohini.walmart.interview;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public final class LevelsInfo {
	private static Map<Integer, LevelData> levels = new HashMap<Integer, LevelData>();

	public static LevelData getLevel(int levelId) {
		return levels.get(levelId);
	}

	public static Map<Integer, LevelData> getLevels() {
		return levels;
	}

	public static void addLevel(LevelData level) {
		LevelsInfo.levels.put(level.getLevelId(), level);
	}

	private LevelsInfo() {
	}
}

class LevelData {
	private int levelId;
	private Level level;
	private double price;
	private int rows;
	private int seatsPerRow;

	public LevelData(int levelId, Level level, double price, int rows, int seatsPerRow) {
		super();
		this.levelId = levelId;
		this.level = level;
		this.price = price;
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}
}

enum Level {
	ORCHESTRA(1, "Orchestra"), MAIN(2, "Main"), BALCONY_ONE(3, "Balcony 1"), BALCONY_TWO(4, "Balcony 2"), ALL(0, "");

	private Level(final int levelId, final String levelName) {
		this.levelId = levelId;

	}

	private int levelId;
	private String levelName;

	public int getLevelId() {
		return levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	private static final Map<Integer, Level> lookup = new HashMap<Integer, Level>();

	static {
		for (Level s : EnumSet.allOf(Level.class))
			lookup.put(s.getLevelId(), s);
	}

	public static Level get(int levelId) {
		return lookup.get(levelId);
	}
}
