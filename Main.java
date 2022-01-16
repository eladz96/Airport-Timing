import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		int numOfFlights=10;
		Airport ap1 = new Airport("Ben Gurion", 3);
		Airport ap2 = new Airport("LAX", 3);
		Random ran = new Random();
		ExecutorService ex = Executors.newFixedThreadPool(numOfFlights);
		
		for (int i = 0; i < numOfFlights; ++i) {// lunching the threads
			if (ran.nextBoolean()) {//randomly decide the direction of the flight 
				ex.execute(new Flight((i + 100), ap1, ap2));
			} else {
				ex.execute(new Flight((i + 100), ap2, ap1));
			}
		}
		ex.shutdown();
		try {// waiting for all the threads to finish their work
			if (!ex.awaitTermination(10, TimeUnit.MINUTES)) {
				ex.shutdownNow();
			}
		} catch (InterruptedException e) {
			ex.shutdownNow();
			Thread.currentThread().interrupt();
		}
		System.out.println("All flights landed!\n" + "Bye bye!");
	}
}
