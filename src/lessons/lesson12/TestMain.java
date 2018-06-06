package lessons.lesson12;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        System.out.println(Currency.EUR);

        System.out.println(Arrays.toString(Currency.values()));

        String usa = "USD";
        Currency usdConverted = Currency.valueOf(usa);
        System.out.println();
    }
}
