package hello;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@FunctionalInterface
interface Foo11{
	void add();
	
}
public class threadtwo {
	
	public static void main(String[] args) {
		
		
		Demo demo= new Demo();
		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 10; i++) {

					
					try {
						TimeUnit.SECONDS.sleep(1);
						demo.addchar();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}, "aa").start();;
		
			new Thread(new Runnable() {
				
				public void run() {
					for (int i = 0; i < 10; i++) {
						
						
						try {
							TimeUnit.SECONDS.sleep(1);
							demo.addchar();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}, "cc").start();;
			new Thread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					
					
					try {
						TimeUnit.SECONDS.sleep(1);
						demo.addnum();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}, "bb").start();;
		new Thread(new Runnable() {
			
			public void run() {
				for (int i = 0; i < 10; i++) {
					
					
					try {
						TimeUnit.SECONDS.sleep(1);
						demo.addnum();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}, "dd").start();;
		
		
	}

}


class Demo{
	int num = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	public  void addchar() throws InterruptedException {
		lock.lock();
		
		try {
			while(num !=0) {
				condition.await();
			}
			++num;
			System.out.println(Thread.currentThread().getName()+"\t\t" +num);
			condition.signalAll();

		} finally {
			lock.unlock();
		}
		
		
		
	}
	public  void addnum() throws InterruptedException {
		lock.lock();
		
		try {
			while(num ==0) {
				condition.await();
			}
			--num;
			System.out.println(Thread.currentThread().getName()+"\t\t" +num);
          condition.signalAll();			

		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	
}
