package hello;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class asdasd {
	public static void main(String[] args) {
	// 线程    操作（实例方法） 资源类   高内聚  低耦合
		
		
		Ticket1 ticket = new Ticket1();
		
		new Thread(new Runnable() {

			public void run() {
				for(int i=1;i<60;i++) {
					ticket.sell();
				}

			}
		}, "aa").start();;
		
		new Thread(() -> {
			for(int i=1;i<60;i++) ticket.sell();
		}, "bb").start();;
		
		new Thread(() -> {
			for(int i=1;i<60;i++) ticket.sell();
		}, "this thread name").start();;
		
	}
}


class Ticket1{//  实例变量加实例方法
	
	
	int num = 30;
	
	private Lock lock = new ReentrantLock();
	public void sell() {
		lock.lock();
		try {

			if(num>0) {
				num--;
				System.out.println(Thread.currentThread().getName()+"还有"+num+"张票");
			}
		} finally {
           lock.unlock();
		}
		
	}
	
	
	
	
}
