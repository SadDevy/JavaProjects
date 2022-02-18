package UI;

import StreamProvider.Provider;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<Double> counter = Provider.createCounter();
        counter.accept(4.0);
        counter.accept(5.0);
    }
}
