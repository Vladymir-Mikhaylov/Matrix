package lesson28;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class DemoComparator {
    public static void main(String[] args) {
        Capability capability1 = new Capability(1001, "test", "zzz", true, new Date());
        Capability capability2 = new Capability(1005, "test", "zzz", false, new Date());
        Capability capability3 = new Capability(900, "test", "zzz", true, new Date());
        Capability capability4 = new Capability(900, "test", "zzz", false, new Date());

        ArrayList<Capability> capabilities = new ArrayList<>();
        capabilities.add(capability1);
        capabilities.add(capability2);
        capabilities.add(capability3);
        capabilities.add(capability4);

        capabilities.sort(new IsActiveComparator());

        System.out.println(capabilities);

        Comparator<Capability> comparator = new Comparator<Capability>() {
            @Override
            public int compare(Capability o1, Capability o2) {
                if(!o1.getChannelName().equals(o2.getChannelName())){
                    return o1.getChannelName().compareTo(o2.getChannelName());
                }
                return 0;
            }
        };
    }
}
