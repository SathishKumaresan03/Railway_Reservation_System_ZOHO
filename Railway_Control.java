package Railway_Reservation_Application;

import java.util.Scanner;

public class Railway_Control {

	public static void main(String[] args) {
		System.out.println("-------------Railway Reservation System---------------");
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		// loop to get choices from user until he stops
		while (flag) {
			System.out.println(
					" 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Tickets \n 4. Booked Tickets \n 5. Exit");
			int choice = sc.nextInt();
			switch (choice) {
			//book tickets
			case 1: {
                //get details from passenger
				System.out.println("Enter your Name");
				String name=sc.next();
				System.out.println("Enter Your Age");
				int age=sc.nextInt();
				System.out.println("Enter your Berth Preference (U,M,L)");
				String berthPreference=sc.next();
				Passenger p=new Passenger(name, age, berthPreference);
				Ticket_Booker.bookTickets(p);
				break;
			}
			//cancel the ticket
			case 2: {
				//get passenger id to cancel
				System.out.println("Enter passenger ID to Cancel the ticket");
				int id=sc.nextInt();
				Ticket_Booker.cancelTicktetChecking(id);
				break;
			}
			case 3: {
				//print available tickets
				Ticket_Booker.printAvailable();
				break;
			}
			case 4: {
				//occupied tickets prints
                Ticket_Booker.printPassengers();
				break;
			}
			case 5: {
                System.out.println("Thank you for your Valuable time, Existing");
                flag=false;
				break;
			}
			default:
				System.out.println("Enter valid choices 1, 2, 3, 4");
				break;
			}
		}
		sc.close();
	}

}
