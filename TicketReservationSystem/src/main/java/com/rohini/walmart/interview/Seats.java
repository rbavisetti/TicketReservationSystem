package com.rohini.walmart.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Seats {
	// private static Map<Integer, List<Seat>> seatsMap = new HashMap<Integer,
	// List<Seat>>();
	private static Map<String, Integer> availableSeatsByLevelRow = new HashMap<String, Integer>();

	public static int getAvailableSeatsByLevelAndRow(String key) {
		return availableSeatsByLevelRow.get(key);
	}

	public static void resetFreeSeats(String key, int noOfSeats) {
		availableSeatsByLevelRow.put(key, noOfSeats);
	}

	public static void initAvailableSeatsByLevelRow(String key, int seats) {
		availableSeatsByLevelRow.put(key, seats);
	}

	/*
	 * public static void addSeatByLevel(int levelId, Seat seat) {
	 * seatsMap.get(levelId).add(seat); }
	 * 
	 * public static void initLevel(int levelId) { seatsMap.put(levelId, new
	 * ArrayList<Seat>()); }
	 * 
	 * public static List<Seat> getSeatsByLevel(int levelId) { return
	 * seatsMap.get(levelId); }
	 */

	private Seats() {
	}
}

class Seat {
	private String seatId;
	private boolean isFree = true;
	private Status status;

	public Seat(String seatId, boolean isFree, Status status) {
		super();
		this.seatId = seatId;
		this.isFree = isFree;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

}

enum Status {
	HOLD, RESERVED
}