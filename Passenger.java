package Railway_Reservation_Application;

public class Passenger {

	static int id = 1;// Static variable to give id for every new passenger
	int passengerId;
	String name;
	int age;
	String berthPreference;// U or L or M
	String alloted;// alloted type (L,M,U,RAC,WL)
	int number;// seat number

	public Passenger(String name, int age, String berthPerference) {
		this.passengerId = id++;
		this.name = name;
		this.age = age;
		this.berthPreference = berthPerference;
		alloted = "";
		number = -1;
	}
}
