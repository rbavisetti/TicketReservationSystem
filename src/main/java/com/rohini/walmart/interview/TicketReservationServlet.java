package com.rohini.walmart.interview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;

public class TicketReservationServlet extends ServletContainer {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ClassLoader classLoader = getClass().getClassLoader();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(classLoader.getResource("leveldata.csv").getFile()));
			int totalSeats = 0;
			while ((line = br.readLine()) != null) {
				String[] level = line.split(cvsSplitBy);
				int levelId = Integer.parseInt(level[0]);
				int rows = Integer.parseInt(level[3]);
				int seatsPerRow = Integer.parseInt(level[4]);
				LevelsInfo.addLevel(
						new LevelData(levelId, Level.get(levelId), Double.parseDouble(level[2]), rows, seatsPerRow));
				totalSeats += rows * seatsPerRow;
				/*Seats.initLevel(levelId);
				for (int row = 0; row < rows; row++) {
					for (int seatNo = 0; seatNo < seatsPerRow; seatNo++) {
						Seats.addSeatByLevel(levelId, new Seat(levelId + "L" + (row + 1) + "R" + (seatNo + 1) + "S"));
					}
					Seats.initAvailableSeatsByLevelRow(levelId + "L" + (row + 1) + "R" , seatsPerRow );
				}*/
			}
			LevelsInfo.addLevel(new LevelData(0, Level.get(0), 0, totalSeats, 1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		super.init();
	}

}
