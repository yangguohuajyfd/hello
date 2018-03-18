package hello;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	public static void main(String[] args) {
		ScheduledExecutorService exs = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result=null;
		
		try {

			for (int i = 0; i < 10; i++) {

				result = exs.schedule(() ->{
					 
					 System.out.print(Thread.currentThread().getName());
					 return new Random().nextInt(10);
				 }, 2, TimeUnit.SECONDS);
				System.out.println("  ***result: " +result.get());

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			exs.shutdown();
		}
	}

	private static void threadPool() {
		ExecutorService exs = Executors.newFixedThreadPool(5);
		ExecutorService exs1 = Executors.newSingleThreadExecutor();
		ExecutorService exs2 = Executors.newCachedThreadPool();

		Future<Integer> result = null;
		try {

			for (int i = 0; i < 10; i++) {

				result = exs2.submit(() -> {
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(10);
				});

				System.out.println(" *******result: " + result.get());

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			exs.shutdown();
		}
	}

}
