package hello;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadAndWrite {

	public static void main(String[] args) {
		RAW rwl = new RAW();
		new Thread(() -> {
			rwl.write("**********1018");
		}, "write").start();

		for (int i = 0; i < 10; i++) {

			new Thread(() -> {
				rwl.read();
			}, String.valueOf(i)).start();
		}

	}

}

class RAW {
	private Object obj;
	private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

	public void write(Object obj) {
		rwlock.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t "+obj);
		} finally {
			rwlock.writeLock().unlock();
		}
	}

	public void read() {
		rwlock.readLock().lock();
		try {

			System.out.println(Thread.currentThread().getName()+"\t "+obj);
		} finally {

			rwlock.readLock().unlock();
		}
	}

}
