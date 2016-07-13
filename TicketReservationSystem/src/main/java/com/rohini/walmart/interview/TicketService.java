package com.rohini.walmart.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/api")
public class TicketService implements ITicketService {

	@Override
	@GET
	@Path("/getFreeSeats")
	public Integer numSeatsAvailable(@DefaultValue("0") @QueryParam("venueLevel") Integer venueLevel) {
		LevelData level = LevelsInfo.getLevel(venueLevel);
		return (level.getRows() * level.getSeatsPerRow()) - getTotalSeatsHoldedOrReserved();
	}

	@Override
	@POST
	@Path("/holdSeats")
	public SeatHold findAndHoldSeats(@QueryParam("numSeats") int numSeats,@QueryParam("minLevel") Integer minLevel,@QueryParam("maxLevel") Integer maxLevel,@QueryParam("customerEmail") String customerEmail) {
		int rows = LevelsInfo.getLevel(minLevel).getRows();
		int currentLevel = minLevel;
		SeatHold seatHold = null;
		do {
			synchronized (this) {
				for (int row = 1; row <= rows; row++) {
					int freeSeats = Seats.getAvailableSeatsByLevelAndRow(currentLevel + "L" + row + "R");
					if (freeSeats - numSeats <= 0) {
						continue;
					} else {
						Seats.resetFreeSeats(currentLevel + "L" + row + "R", freeSeats - numSeats);
						List<Seat> seats = new ArrayList<Seat>();
						for (int i = 1; i <= numSeats; i++)
							seats.add(
									new Seat(currentLevel + "L" + row + "R" + (freeSeats--) + "S", false, Status.HOLD));
						seatHold = new SeatHold(UUID.randomUUID().toString(), seats, customerEmail);
						HoldedSeats.addSeatHolded(seatHold);
						break;
					}

				}
				currentLevel++;
			}
		} while (currentLevel <= maxLevel);
		return seatHold;
	}

	@Override
	@POST
	@Path("/reserveSeats")
	public String reserveSeats(@QueryParam("seatHoldId") String seatHoldId,@QueryParam("customerEmail") String customerEmail) {
		SeatHold seatHold = HoldedSeats.getSeatHolded(seatHoldId);
		for (Seat seat : seatHold.getSeats()) {
			seat.setStatus(Status.RESERVED);
		}
		return "success";
	}

	private int getTotalSeatsHoldedOrReserved() {
		int seatsHoldedOrReserved = 0;
		Map<String, SeatHold> seats = HoldedSeats.getInstance().getAllSeatsHolded();
		if (seats.size() > 0) {
			for (String key : seats.keySet()) {
				SeatHold seat = seats.get(key);
				seatsHoldedOrReserved += seat.getSeats().size();
			}
		}
		return seatsHoldedOrReserved;
	}
}
