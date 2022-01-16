import java.util.Random;

public class Flight extends Thread {

	private int flightNum;
	private Airport origin;
	private Airport destination;

	// constructor
	public Flight(int fn, Airport o, Airport d) {
		flightNum = fn;
		origin = o;
		destination = d;
	}

	// methods

	@Override
	public void run() {
		int runwayUsed;
		Random ran = new Random();
		runwayUsed = origin.depart(flightNum);//get a runway
		try {
			Thread.sleep((int) ((ran.nextDouble() * (5 - 2) + 2) * 1000));//"take off"
		} catch (InterruptedException e) {
		}
		origin.freeRunway(flightNum, runwayUsed);//free the runway
		try {
			Thread.sleep((int) ((ran.nextDouble() * (12 - 10) + 10) * 1000));//"fly" to destination
		} catch (InterruptedException e) {
		}
		runwayUsed = destination.land(flightNum);//get a runway
		try {
			Thread.sleep((int) ((ran.nextDouble() * (5 - 2) + 2) * 1000));//"land"
		} catch (InterruptedException e) {
		}
		destination.freeRunway(flightNum, runwayUsed);//free the runway

	}

}
