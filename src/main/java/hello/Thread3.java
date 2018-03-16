package hello;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface Foo{
	
	
	public int add(int x,int y);
	default int sell(int x,int y) {
		return x+y;
	};
}



public class Thread3 {

	
	public static void main(String[] args) {
		
		
//		Ticket ticket = new Ticket();
//		
//		
//		
//		
//		new Thread(() -> {for(int i=1;i<90;i++) ticket.sell();},"bb")  .start();
//		new Thread(() -> {for(int i=1;i<90;i++) ticket.sell();},"dd")  .start();
//		
//		new Thread(() -> {for(int i=1;i<90;i++) ticket.sell();},"cc")  .start();
		
	
		
		Foo foo=( x, y)->{return x+y;};
		
		Foo foo1 = new Foo() {
			
			@Override
			public int add(int x, int y) {
				
				return x+y;
			}
		};
		
		System.out.println(foo.add(2, 3));
		System.out.println(foo.sell(6, 7));
		
		
		


	
	}
		
	
	
		
	
}


 class Multiply {
	public static int multiply(int num) {
		if (num < 0) {
			System.out.println("请输入大于0的数！");
			return -1;
		} else if (num == 0 || num == 1) {
			return 1;
		} else {
			return multiply(num - 1) * num;
		}
	}
 }
class Ticket {

	int num = 80;
	
	Lock lock = new ReentrantLock();
	
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
