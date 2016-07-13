package com.rohini.walmart.interview;

import java.util.Optional;

public interface ITicketService {
	Integer numSeatsAvailable(Integer venueLevel);

	SeatHold findAndHoldSeats(int numSeats, Integer minLevel, Integer maxLevel, String customerEmail);

	String reserveSeats(String seatHoldId, String customerEmail);
}
