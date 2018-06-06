package lessons.lesson11.bigInterfaceExample;

public class SimpleReader implements Readable {
/**
    static String test;
    public static void main(String[] args) {
        System.out.println(test.length());
    }
*/
    public void readFilesFromStorage(Storage storage) {
        for(File file : storage.getFiles()){
            if(file != null){
                System.out.println(file.getName());
            }else {
                System.out.println(file);
            }
        }
    }
}
