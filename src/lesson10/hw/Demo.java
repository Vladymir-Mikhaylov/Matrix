package lesson10.hw;

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



        //***********testing****************


        Customer c1 = new Customer("Nick", "Киев", "Женский");
        ElectronicsOrder ee1 = new ElectronicsOrder("Phone", new Date(), "Харьков", "Киев", 10000, c1, 3);
        ElectronicsOrder ee2 = new ElectronicsOrder("Phone", new Date(), "Иркутск", "Киев", 1000, client2, 3);
        ElectronicsOrder ee3 = new ElectronicsOrder("Phone", new Date(), "Харьков", "Киев", 10, client2, 3);
        ElectronicsOrder ee4 = new ElectronicsOrder("Phone", new Date(), "Харьков", "Киев", 100000, client2, 3);
        //validate
        ee1.validateOrder();//validate with all good fields
        ee2.validateOrder();//validate with unaceptable city shipedFrom
        ee3.validateOrder();//validate with unaceptable basePrice

        //calculate price
        ee1.calculatePrice();//delivery from kiev, delivery 15% from gross
        ee2.calculatePrice();//delivery from another cities, delivery 10% from gross
        ee4.calculatePrice();//sale 5% price more than 1000
        String end = "";
    }
}
