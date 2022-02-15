package UI;

import StreamProvider.Provider;

import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Provider.getWorkingDays(LocalDate.now());
    }
}
