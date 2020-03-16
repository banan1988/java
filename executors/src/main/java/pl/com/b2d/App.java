package pl.com.b2d;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        System.out.println("Hello World!");

        for (int i = 0; i < 10; i++) {
            execute(Thread.currentThread().getName(), Thread.currentThread().getStackTrace());
        }

        System.out.println("Goodbye World!");
        EXECUTOR_SERVICE.shutdown();
    }

    private static void execute(String name, final StackTraceElement[] stackTrace) {
        EXECUTOR_SERVICE.execute(log(name, stackTrace));
    }

    private static Runnable log(final String name, final StackTraceElement[] stackTrace) {
        return new Runnable() {
            public void run() {
                final Stopwatch started = Stopwatch.createStarted();
                Arrays.sort(stackTrace, new Comparator<StackTraceElement>() {
                    public int compare(StackTraceElement ste1, StackTraceElement ste2) {
                        return ste1.getClassName().compareTo(ste2.getClassName());
                    }
                });
                System.out.println("[" + name + "] sort[ms]: " + started.elapsed(TimeUnit.MILLISECONDS));
                final int main = Arrays.binarySearch(stackTrace, new StackTraceElement("App", "", "", 0), new Comparator<StackTraceElement>() {
                    public int compare(StackTraceElement ste1, StackTraceElement ste2) {
                        if (ste1.getClassName().contains(ste2.getClassName())) {
                            return -1;
                        }
                        System.out.println("ste1: " + ste1.getClassName());
                        System.out.println("ste2: " + ste2.getClassName());
                        return ste1.getClassName().compareTo(ste2.getClassName());
                    }
                });
                System.out.println("[" + name + "] search[ms]: " + started.elapsed(TimeUnit.MILLISECONDS) + " : " + main);

                sleep();
            }

            private void sleep() {
                try {
                    final String name = Thread.currentThread().getName();
                    final int i = new Random().nextInt(10);
                    System.out.println("[" + name + "] Waits " + i + " seconds");
                    Thread.sleep(i * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
