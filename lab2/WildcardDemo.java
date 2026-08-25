package lab2;

import java.util.Arrays;
import java.util.List;

public class WildcardDemo {

    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("One", "Two", "Three");
        System.out.println("Printing string list:");
        printList(stringList);

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("Integer List Sum: " + sumNumbers(intList));
        System.out.println("Double List Sum: " + sumNumbers(doubleList));
    }
}