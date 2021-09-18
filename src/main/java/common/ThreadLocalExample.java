package common;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalExample {

    public static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }

    public static class TestRunnable implements Runnable {
        private ThreadLocal<Map<String, Integer>> threadLocal = new ThreadLocal();

        @Override
        public void run() {
            Map<String, Integer> map = new HashMap<>();
            map.put("key" + Thread.currentThread().getName(), (int) (Math.random() * 100));
            threadLocal.set(map);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println();
            System.out.println(threadLocal.get());


        }
    }

//    public static class MyRunnable implements Runnable {
//
//        private ThreadLocal threadLocal = new ThreadLocal();
//
//        @Override
//        public void run() {
//            threadLocal.set((int) (Math.random() * 100D));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//
//            }
//            System.out.println(threadLocal.get());
//        }
//    }

    public static void main(String[] args) {

        TestRunnable testRunnable = new TestRunnable();
        Thread thread1 = new Thread(testRunnable);
        Thread thread2 = new Thread(testRunnable);

        thread1.start();
        thread2.start();


        Thread thread3 = new Thread() {

        };
//        MyRunnable sharedRunnableInstance = new MyRunnable();
//        Thread thread1 = new Thread(sharedRunnableInstance);
//        Thread thread2 = new Thread(sharedRunnableInstance);
//        thread1.start();
//        thread2.start();
    }

}