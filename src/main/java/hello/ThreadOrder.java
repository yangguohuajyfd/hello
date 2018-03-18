package hello;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrder {

	public static void main(String[] args) {
		Order o = new Order();
		
			new Thread(() -> {
				for (int i = 0; i < 10; i++) {
				try {
					o.A();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}, "AA").start();;
		
			new Thread(() -> {
				for (int i = 0; i < 10; i++) {
				try {
					o.B();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}, "BB").start();;
			
			new Thread(() -> {
				for (int i = 0; i < 10; i++) {
				try {
					o.C();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}, "CC").start();;
		
		
		

	}
}

class Order {
	int num = 1;
	private Lock lock = new ReentrantLock();
	private Condition cd1 = lock.newCondition();
	private Condition cd2 = lock.newCondition();
	private Condition cd3 = lock.newCondition();

	public void A() throws Exception {
		lock.lock();
		try {
			while (num != 1) {
				cd1.await();
			}
			for (int i = 0; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t  AA");
			}
			num=2;
			cd2.signal();
		} finally {
			lock.unlock();
		}

	}
	public void B() throws Exception {
		lock.lock();
		try {
			while (num != 2) {
				cd2.await();
			}
			for (int i = 0; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t  BB");
			}
			num=3;
			cd3.signal();
		} finally {
			lock.unlock();
		}
		
	}
	public void C() throws Exception {
		lock.lock();
		try {
			while (num != 3) {
				cd3.await();
			}
			for (int i = 0; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t  CC");
			}
			num=1;
			cd1.signal();
		} finally {
			lock.unlock();
		}
		
	}

}