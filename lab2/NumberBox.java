package lab2;

public class NumberBox<T extends Number> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public double addAndSum(T otherNumber) {
        if (this.item == null || otherNumber == null) {
            return 0.0;
        }
        return this.item.doubleValue() + otherNumber.doubleValue();
    }

    public static void main(String[] args) {
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.setItem(10);
        System.out.println("Integer Box Item: " + intBox.getItem());
        System.out.println("Sum with 20: " + intBox.addAndSum(20));

        NumberBox<Double> doubleBox = new NumberBox<>();
        doubleBox.setItem(5.5);
        System.out.println("Double Box Item: " + doubleBox.getItem());
        System.out.println("Sum with 4.5: " + doubleBox.addAndSum(4.5));
    }
}