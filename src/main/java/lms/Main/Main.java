package lms.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Adding in map non-synchronization
        Map<Integer, String> map = new HashMap<>();

        long startTime = System.nanoTime();
        map.put(1, "Element 1");
        map.put(2, "Element 2");
        map.put(3, "Element 3");
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Time taken to add three elements to HashMap: " + duration + " nanoseconds");

        // Comparison of two collections
        Map<String, Integer> hashmap = new HashMap<>();
        Map<String, Integer> concurrencyMap = new ConcurrentHashMap<>();

        // non-thread safe
        long timeStartNonSafe = System.nanoTime();
        Thread[] nonSafethreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            nonSafethreads[i] = new Lthread(hashmap, i);
            nonSafethreads[i].start();
        }
        for(Thread thread : nonSafethreads) {
            thread.join();
        }
        long timeEndNonSafe = System.nanoTime();
        long timeUsedNonsafe = (timeEndNonSafe - timeStartNonSafe);
        System.out.println("Time used on thread-Non safe "+timeUsedNonsafe+" nanoseconds");

        // Thread-safe

        long timeStartSafe = System.nanoTime();
        Thread[] safeThreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            safeThreads[i] = new Lthread(concurrencyMap, i);
            safeThreads[i].start();
        }
        for(Thread thread : safeThreads) {
            thread.join();
        }
        long timeEndSafe = System.nanoTime();
        long timeUsedSafe = (timeEndSafe - timeStartSafe);
        System.out.println("Time used on Thread-safe "+timeUsedSafe+" nanoseconds");
    }
}