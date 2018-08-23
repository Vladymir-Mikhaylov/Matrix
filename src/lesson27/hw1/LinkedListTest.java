package lesson27.hw1;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        int [] test1= new int[5];
        int [] test2 = {2, 26, 0, 234};
        test1[0] = 10;
        //***

        LinkedList<Integer> list = new LinkedList<>();
        list.add(123);
        list.add(5);
        list.add(2);
        list.remove(1);
        //list.remove(list.indexOf(2));

        System.out.println(list.size());
        System.out.println(Arrays.deepToString(list.toArray()));
        list.add(321);
        list.add(321);
        System.out.println(Arrays.deepToString(list.toArray()));
        list.add(3, 333333);
        System.out.println(Arrays.deepToString(list.toArray()));

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(0);
        list1.add(777);
        list1.add(999);
        list.addAll(list1);
        System.out.println(list.toString());
        list.set(2, 6666666);
        System.out.println(list.toString());
        System.out.println(list.size());
        list.remove(6);
        //list.clear();
        System.out.println(list.size());
        System.out.println(list.toString());




    }
}
