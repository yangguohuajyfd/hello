package hello;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumAndEng {

	public static void main(String[] args) {

		NE ne = new NE();
		
		new Thread(() -> {

			
			

			for (int i = 0; i < 52; i++) {

				try {
					ne.addNum();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}, "aa").start();
		new Thread(() -> {

			

				for (int i = 0; i < 52; i++) {

					try {
						ne.addEng();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		}, "aa").start();
		
		
		

	}
}

class NE {
	int i = 0;
	int num=1;
	
	char aa = 'a';
	
	private Lock lock = new ReentrantLock();
	private Condition cd = lock.newCondition();
//12a34b56c78d9
	public void addNum() throws InterruptedException {
		lock.lock();
		try {
			
			while(num%3==0) {
			
				cd.await();
			}
			
			num++;
			System.out.print(++i);
			cd.signalAll();
		

		} finally {
			lock.unlock();
		}

	}

	public void addEng() throws InterruptedException {

		lock.lock();
		try {

			
			
			while(num%3!=0) {
				cd.await();
			}
			num++;
			System.out.print((char)(aa+=1));
			
		
			
			cd.signalAll();

		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}

}
