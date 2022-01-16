
public class Airport {

	private String name;
	private int numOfRunways;
	private int runways[][]; // runways array. first row is a flag 0=runway clear 1=runway used. second row
								// is the flight number of the flight that uses the runway.

	// constructors
	public Airport(String name, int numOfRunways) {
		this.name = name;
		this.numOfRunways = numOfRunways;
		runways = new int[2][numOfRunways];
		for (int i = 0; i < 2; ++i) {//initializing the array
			for (int j = 0; j < numOfRunways; ++j) {
				runways[i][j] = 0;
			}
		}
	}

	// methods
	public synchronized int depart(int flightNum) {
		while (true) {
			for (int i = 0; i < numOfRunways; ++i) {//check if there is a free runway if so take it
				if (runways[0][i] == 0) {
					runways[0][i] = 1;
					runways[1][i] = flightNum;
					System.out.println("Flight numbr: " + flightNum + " is departing " + name
							+ " airport from runway number: " + (i + 1));
					return i;
				}
			}
			try {
				wait();// if there is no free runway wait
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	public synchronized int land(int flightNum) {
		while (true) {
			for (int i = 0; i < numOfRunways; ++i) {//check if there is a free runway if so take it
				if (runways[0][i] == 0) {
					runways[0][i] = 1;
					runways[1][i] = flightNum;
					System.out.println("Flight numbr: " + flightNum + " is landing in " + name
							+ " airport on runway number: " + (i + 1));
					return i;
				}
			}
			try {
				wait();// if there is no free runway wait
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	public synchronized void freeRunway(int flightNum, int runway) {//free the runway and notify the next waiting flight
			runways[0][runway] = 0;
			runways[1][runway] = 0;
			notify();
		}

}
