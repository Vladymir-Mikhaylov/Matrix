package lessons.lesson11.hw;

public class Demo {
    //some notes for second commit
    public static void main(String[] args) {
        Room[] rooms1 = new Room[5];

        int [] arr1 = {0, 5, 65, 1};
        int [] arr2 = {0, 5, 6535, 1};

        int [] result = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);

        for(int a : result){
            int counter = 0;
            for(int b : result){
                if(a == b){
                    result[counter] = 0;
                    counter ++;
                }
            }
        }
    }
}
