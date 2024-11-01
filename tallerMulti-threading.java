import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimeCalculator {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(15485863, 15485867, 15485869);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<Boolean>> tasks = Arrays.asList(
                () -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " esta procesando el numero: " + numbers.get(0));
                    Thread.sleep(1000); // Delay for 1 second
                    boolean result = isPrime(numbers.get(0));
                    System.out.println("Thread: " + Thread.currentThread().getName() + " proceso completado del numero: " + numbers.get(0) + " con resultado: " + result);
                    return result;
                },
                () -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " esta procesando el numero: " + numbers.get(1));
                    Thread.sleep(7000); // Delay for 7 seconds
                    boolean result = isPrime(numbers.get(1));
                    System.out.println("Thread: " + Thread.currentThread().getName() + " proceso completado del numero: " + numbers.get(1) + " con resultado: " + result);
                    return result;
                },
                () -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " esta procesando el numero: " + numbers.get(2));
                    Thread.sleep(3000); // Delay for 3 seconds
                    boolean result = isPrime(numbers.get(2));
                    System.out.println("Thread: " + Thread.currentThread().getName() + " proceso completado del numero: " + numbers.get(2) + " con resultado: " + result);
                    return result;
                }
        );

        try {
            for (Callable<Boolean> task : tasks) {
                executor.submit(task);
            }
        } finally {
            executor.shutdown();
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
