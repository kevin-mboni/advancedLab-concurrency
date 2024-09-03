package lms.Main;

import java.util.HashMap;
import java.util.Map;

public class Lthread extends Thread {
    int threadsNumber;
    private Map<String, Integer> map = new HashMap<String, Integer>();
    public Lthread(Map<String, Integer> map ,int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            map.put("key " + threadsNumber+ " _ " +i, i);
            System.out.println("key "+ i+ " added on thread number " + threadsNumber);

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
