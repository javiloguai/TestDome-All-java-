import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.*;

public class Adder {
    public static int sum(List<Callable<Integer>> functions, Consumer<Integer> onSumChanged) throws Exception {
        final int[] sum = {0};
        List<Integer> toConsume = new ArrayList<Integer>();

        functions.parallelStream().forEach(funct ->
        {
            try {
                int number = funct.call();
                sum[0] = sum[0] + number;
                onSumChanged.accept(number++);
                onSumChanged.andThen(onSumChanged);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        return sum[0];
    }

    public static void main(String[] args) throws Exception {
        Callable<Integer> expensiveFunction = () -> (int) IntStream.range(0, 200000000).count();
        Callable<Integer> cheapFunction = () -> (int) IntStream.range(0, 10000000).count();
        List<Callable<Integer>> functionList = Arrays.asList(expensiveFunction, cheapFunction);
        Consumer<Integer> onSumChanged = (sum) -> System.out.println("Current result: " + sum);



        // Computationally expensive functions need more time than cheaper functions.
        // Because of this, computationally cheaper functions, when run in parallel, 
        // should be summed up before more expensive functions.
        // Expected output:
        // Current result: 10000000
        // Current result: 210000000
        // Final result: 210000000
        int result = sum(functionList, onSumChanged);
        System.out.println("Final result: " + result);
    }
}