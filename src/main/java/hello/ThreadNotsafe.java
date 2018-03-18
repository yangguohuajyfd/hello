package hello;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadNotsafe {
	
	public static void main(String[] args) {
		Set set = new CopyOnWriteArraySet(); 
		
		
		for (int i = 0; i < 30; i++) {
		new Thread(() -> {

			set.add(UUID.randomUUID().toString().substring(0, 4));
			
			System.out.println(set);
		}, String.valueOf(i)).start();;
		}
		
		
	}

	private static void mapsafe() {
		Map<String,String> map = new ConcurrentHashMap();
		
		
		for (int i = 0; i < 30; i++) {
		new Thread(() -> {

			map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 4));
			
			System.out.println(map);
		}, String.valueOf(i)).start();;
		}
	}

	private static void ArrayListsafe() {
		List list = new CopyOnWriteArrayList<>();
		
		
		for (int i = 0; i < 30; i++) {
		new Thread(() -> {

			list.add(UUID.randomUUID().toString().substring(0, 4));
			
			System.out.println(list);
		}, "this thread name").start();;
		}
	}

}
