package com.rohini.walmart.interview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatHold {
	private String id;
	private List<Seat> seats;
	private String customerEmail;

	public SeatHold(String id, List<Seat> seats, String customerEmail) {
		super();
		this.id = id;
		this.seats = seats;
		this.customerEmail = customerEmail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

}

final class HoldedSeats {
	private static Map<String, SeatHold> allSeatsHolded = new HashMap<String, SeatHold>();
	private static HoldedSeats INSTANCE = new HoldedSeats();

	private HoldedSeats() {
	}

	public static HoldedSeats getInstance() {
		return INSTANCE;
	}

	public static Map<String, SeatHold> getAllSeatsHolded() {
		return allSeatsHolded;
	}

	public static void setAllSeatsHolded(Map<String, SeatHold> allSeatsHolded) {
		HoldedSeats.allSeatsHolded = allSeatsHolded;
	}

	public static void addSeatHolded(SeatHold seatHold) {
		allSeatsHolded.put(seatHold.getId(), seatHold);
	}

	public static SeatHold getSeatHolded(String key) {
		return allSeatsHolded.get(key);
	}
}
