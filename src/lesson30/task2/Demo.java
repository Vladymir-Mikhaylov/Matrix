package lesson30.task2;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        strings.addAll(strings1);
        System.out.println(strings);


        ArrayList<String> search = new ArrayList<>();
        search.add("hello");
        search.remove("hello");
        System.out.println(search.contains("hello"));

        for (String s : search){
            System.out.println(s);
        }
    }
}
