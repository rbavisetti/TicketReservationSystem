TICKET SERVICE HOMEWORK:
The Application was implemented using Java and built using Maven. Implemented using Jersey Rest API. 
The Level information is stored in a csv - leveldata.csv.
ITicketService:
Interface with the following method declarations:
	numSeatsAvailable
	SeatHold findAndHoldSeats
	String reserveSeats
Level Information is stored in a Map<Integer,LevelData> levels:
	LevelData contains levelId, level, price, rows, seatsPerRow.
Level is of type enum where 1 indicates Orchestra, 2 indicates Main, 3 indicates Balcony 1, 4 indicates Balcony 2 and by default 0 indicates all.
Seats in a row are stored in a List.
Status is of type enum with values HOLD and RESERVED.
isFree() is used to check the status of the seat.
TicketReservationServlet loads the csv, reads the level data and adds seats by level and the level data.
TicketService contains the RESTful webservice implementation for all the methods declared in the interface. 
Import the project and build it using Maven and run it.