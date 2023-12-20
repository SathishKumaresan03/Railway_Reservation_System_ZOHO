package Railway_Reservation_Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Ticket_Booker {

	// 63 berth(upper,middle and lower) +(18 RAC passengers)
	// 10 waiting list tickets ->21 U, 21 M, 21 L, !8 RAC, 10 WL
	static int availableLowerBerth = 1;
	static int availableUpperBerth = 1;
	static int availableMiddleBerth = 1;
	static int availableRacTickets = 1;
	static int availableWaitingList = 1;

	static Queue<Integer> waitingList = new LinkedList<>();
	static Queue<Integer> racList = new LinkedList<>();
	static List<Integer> booketTicketsList = new ArrayList<>();

	static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,3,4,---21
	static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,3,4,---21
	static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,3,4,---21
	static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,3,4,---18
	static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));// normally 1,2,3,4,---10

	static Map<Integer, Passenger> passengers = new HashMap<>();// map of passengers id and passenger details

	public static void bookTickets(Passenger p) {
		Ticket_Booker booker = new Ticket_Booker();
		// if WL is not available means no ticket there
		if (availableWaitingList == 0) {
			System.out.println("No ticktes available");
			return;
		}
		// Checking prefered berth is available or not
		if ((p.berthPreference.equals("L") && availableLowerBerth > 0)
				|| (p.berthPreference.equals("M") && Ticket_Booker.availableMiddleBerth > 0)
				|| (p.berthPreference.equals("U") && Ticket_Booker.availableUpperBerth > 0)) {
			System.out.println("Preferred berth is Available");
			if (p.berthPreference.equals("L")) {
				System.out.println("Lower Berth given");
				// Call booking function in the ticket booker class
				booker.addToPreferredBerth(p, (Ticket_Booker.lowerBerthsPositions.get(0)), "L");
				// Remove the booket position from available position and also decrece available
				// seats of that particular berth
				Ticket_Booker.lowerBerthsPositions.remove(0);
				Ticket_Booker.availableLowerBerth--;
			} else if (p.berthPreference.equals("M")) {
				System.out.println("Middle berth given");
				// Call booking function in the ticket booker class
				booker.addToPreferredBerth(p, (Ticket_Booker.middleBerthsPositions.get(0)), "M");
				// Remove the booket position from available position and also decrece available
				// seats of that particular berth
				Ticket_Booker.middleBerthsPositions.remove(0);
				Ticket_Booker.availableMiddleBerth--;
			} else if (p.berthPreference.equals("U")) {
				System.out.println("Upper berth given");
				// Call booking function in the ticket booker class
				booker.addToPreferredBerth(p, (Ticket_Booker.upperBerthsPositions.get(0)), "U");
				// Remove the booket position from available position and also decrece available
				// seats of that particular berth
				Ticket_Booker.upperBerthsPositions.remove(0);
				Ticket_Booker.availableUpperBerth--;
			}
			// ---------------------------------------------------------
		}

		// preference if not available - > book available berth
		else if (Ticket_Booker.availableLowerBerth > 0) {
			System.out.println("Lower Berth given");
			// Call booking function in the ticket booker class
			booker.addToPreferredBerth(p, (Ticket_Booker.lowerBerthsPositions.get(0)), "L");
			// Remove the booket position from available position and also decrece available
			// seats of that particular berth
			Ticket_Booker.lowerBerthsPositions.remove(0);
			Ticket_Booker.availableLowerBerth--;
		} else if (Ticket_Booker.availableMiddleBerth > 0) {
			System.out.println("Middle berth given");
			// Call booking function in the ticket booker class
			booker.addToPreferredBerth(p, (Ticket_Booker.middleBerthsPositions.get(0)), "M");
			// Remove the booket position from available position and also decrece available
			// seats of that particular berth
			Ticket_Booker.middleBerthsPositions.remove(0);
			Ticket_Booker.availableMiddleBerth--;
		} else if (Ticket_Booker.availableUpperBerth > 0) {
			System.out.println("Upper berth given");
			// Call booking function in the ticket booker class
			booker.addToPreferredBerth(p, (Ticket_Booker.upperBerthsPositions.get(0)), "U");
			// Remove the booket position from available position and also decrece available
			// seats of that particular berth
			Ticket_Booker.upperBerthsPositions.remove(0);
			Ticket_Booker.availableUpperBerth--;
		}
		// if no berth if available go to RAC Ticktes
		else if (Ticket_Booker.availableRacTickets > 0) {
			System.out.println("RAC Given");
			booker.addToRAC(p, Ticket_Booker.racPositions.get(0), "RAC");
		}
		// if no RAC is available go to waiting list
		else if (Ticket_Booker.availableWaitingList > 0) {
			System.out.println("Added to waiting list");
			booker.addToWaitingList(p, Ticket_Booker.waitingListPositions.get(0), "WL");
		}
	}

	public void addToPreferredBerth(Passenger p, int berthInfo, String allotedBerth) {
		// Assign the seat number and type of berth(L,U,M)
		p.number = berthInfo;
		p.alloted = allotedBerth;
		// add passenger to the map
		passengers.put(p.passengerId, p);
		// add passenger id to the list of booked tickets
		booketTicketsList.add(p.passengerId);
		System.out.println("---------------------Ticket Booket succesfully");
	}

	public void addToRAC(Passenger p, int racInfo, String allotedRAC) {
		// Assign seat number and type(RAC)
		p.number = racInfo;
		p.alloted = allotedRAC;
		// add passenger to the map
		passengers.put(p.passengerId, p);
		// add passenger id to the queue of RAC ticktes
		racList.add(p.passengerId);
		// decrease the available RAC ticktes by 1
		availableRacTickets--;
		// remove the position that was allocated to the passenger
		racPositions.remove(0);

		System.out.println("-----------------------Added to RAC succesfully");

	}

	public void addToWaitingList(Passenger p, int waitingListInfo, String allotedWaitingList) {
		// Assign seat number and type(WL)
		p.number = waitingListInfo;
		p.alloted = allotedWaitingList;
		// add passenger to the map
		passengers.put(p.passengerId, p);
		// add passenger id to the queue of WL ticktes
		waitingList.add(p.passengerId);
		// decrease the available WL ticktes by 1
		availableWaitingList--;
		// remove the position that was allocated to the passenger
		waitingListPositions.remove(0);

		System.out.println("-----------------------Added to WaitingList succesfully");
	}

	public static void cancelTicktetChecking(int id) {
		// check if passenger id is valid
		if (passengers.containsKey(id)) {
			cancelTicktet(id);
		} else {
			System.out.println("Passenger details unknown");

		}

	}

	public static void cancelTicktet(int id) {
		// remove the passenge from map
		Passenger p = passengers.get(id);
		passengers.remove(id);
		// remove the booked tickets from the list
		booketTicketsList.remove(Integer.valueOf(id));

		// take the booked position which is now free
		int positionBooked = p.number;

		System.out.println("------------------Cancelled Succesfully");

		// add the free position to the coressponding type of list (Either L,M,U)
		if (p.alloted.equals("L")) {
			availableLowerBerth++;
			lowerBerthsPositions.add(positionBooked);
		} else if (p.alloted.equals("M")) {
			availableMiddleBerth++;
			middleBerthsPositions.add(positionBooked);
		}
		if (p.alloted.equals("U")) {
			availableUpperBerth++;
			upperBerthsPositions.add(positionBooked);
		}

		// Check if any RAC is there
		if (racList.size() > 0) {
			// take passenger from RAC and increse the free in RAC list and increase
			// available RAC ticktets
			Passenger passengerFromRAC = passengers.get(racList.poll());
			int positionRAC = passengerFromRAC.number;
			racPositions.add(positionRAC);
			racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
			availableRacTickets++;

			// check is any WL is there
			if (waitingList.size() > 0) {
				// take the passenger from wl and add to RAC, Increasehe free space in waiting
				// list and increase availabe WL and decrease availabe in RAC
				Passenger passengerFromWaitingList = passengers.get(waitingList.poll());
				int positionWL = passengerFromWaitingList.number;
				waitingListPositions.add(positionWL);
				waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

				passengerFromWaitingList.number = racPositions.get(0);
				passengerFromWaitingList.alloted = "RAC";
				racPositions.remove(0);
				racList.add(passengerFromWaitingList.passengerId);

				availableWaitingList++;
				availableRacTickets--;
			}
			// now we have a passenger from rac to whom we can book a ticktet
			// so book the cancelled ticket to the RAC passenger
			bookTickets(passengerFromRAC);
		}
	}

	// print available seats
	public static void printAvailable() {
		System.out.println("Available Lower berths - " + availableLowerBerth);
		System.out.println("Available Middle berths - " + availableMiddleBerth);
		System.out.println("Available Upper berths - " + availableUpperBerth);
		System.out.println("Available RACs - " + availableRacTickets);
		System.out.println("Available Waiting List - " + availableWaitingList);
		System.out.println("-------------------------------------------------");
	}

	public static void printPassengers() {
		if (passengers.size() == 0) {
			System.out.println("No details of passengers");
		}
		for (Passenger p : passengers.values()) {
			System.out.println("Passenger ID - " + p.passengerId);
			System.out.println("Name - " + p.name);
			System.out.println("Age - " + p.age);
			System.out.println("Status - " + p.number + p.alloted);
			System.out.println("----------------------------------------------");

		}
	}
}
