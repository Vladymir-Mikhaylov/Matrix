package lessons.lesson10.hw;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Customer client1 = new Customer("Nick", "Киев", "Мужской");
        Customer client2 = new Customer("Olga", "Одесса", "Женский");
        Customer client3 = new Customer("Тест", "Львов", "Мужской");

        ElectronicsOrder e1 = new ElectronicsOrder("Phone", new Date(), "Харьков", "Киев", 10000, client2, 3);
        ElectronicsOrder e2 = new ElectronicsOrder("Tablet", new Date(), "Киев", "Львов", 8000, client3, 3);

        FurnitureOrder f1 = new FurnitureOrder("Chair", new Date(), "Львов", "Одесса", 10000, client1, "x1");
        FurnitureOrder f2 = new FurnitureOrder("Sofa", new Date(), "Одесса", "Харьков", 20000, client1, "xz1");

        e1.validateOrder();
        e1.calculatePrice();
        e2.validateOrder();
        e2.calculatePrice();

        f1.validateOrder();
        f1.calculatePrice();
        f2.validateOrder();
        f2.calculatePrice();
        System.out.println(f1.getDateConfirmed());
        System.out.println(f1.getTotalPrice());
        //System.out.println(e2.getDateConfirmed());
        //System.out.println(f1.getDateConfirmed());
        //System.out.println(f2.getDateConfirmed());
    }
}
