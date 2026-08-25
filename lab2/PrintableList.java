package lab2;

import java.util.Arrays;
import java.util.List;

public class PrintableList<T> {
    private List<T> list;

    public PrintableList(T[] array) {
        this.list = Arrays.asList(array);
    }

    public void printAll() {
        for (T item : list) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        String[] strArray = {"Java", "Generics", "Lab"};
        PrintableList<String> printableList = new PrintableList<>(strArray);
        printableList.printAll();
    }
}
