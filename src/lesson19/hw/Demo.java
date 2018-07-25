package lesson19.hw;

public class Demo {
    public static void main(String[] args) {
        File f1 = new File(1, "f1", ".txt", 10);
        File f2 = new File(2, "f2", ".txt", 10);
        File f3 = new File(3, "f3", ".txt", 10);

        File[] files1 = new File[5];
        files1[0] = f1;
        files1[3] = f2;
        files1[4] = f3;

        File f4 = new File(4, "f4", ".doc", 10);
        File f5 = new File(5, "f5", ".doc", 10);
        File f6 = new File(6, "f6", ".txt", 10);

        File[] files2 = new File[5];
        files2[1] = f5;
        files2[2] = f4;
        files2[3] = f6;

        String [] formats = {".txt", ".doc", ".docx"};

        Storage s1 = new Storage(1, files1, formats, "UA", 50);
        Storage s2 = new Storage(2, files2, formats, "USA", 50);

        Controller controller = new Controller();
        //check put method
        File file1 = new File(5, "file", ".txt", 8);//ok
        File file2 = new File(5, "fileeeeeeeeeeeeeeeeeeeee", ".txt", 8);//too long name;
        File file3 = new File(5, "file", ".prd", 8);//not aceptable format
        File file4 = new File(5, "file", ".doc", 60);//not enough space
        File file5 = null;//send file null to put method
        Storage s3 = null;//send storage null to put method

        try {
            //controller.put(s3, file4);
            //controller.put(s3, file4);
            controller.put(s2, file4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //check delete method

        try {
            controller.delete(s2, f5);//normaly removed
            //controller.delete(s2, f5);//File not found exception
            //controller.delete(s2, null);send nulls to method
            //controller.delete(null, null);//send nulls to method
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //check transfer all
        Storage s4 = new Storage(4, files1, new String []{".mp3", ".mp4"}, "Canada", 60);

        try {
            controller.transferAll(s1, s2);//normally transfer
            //controller.transferAll(null, null);//send nulls to method transferAll
            //controller.transferAll(s1, null);//send nulls to method transferAll
            //controller.transferAll(null, s2);//send nulls to method transferAll
            //controller.transferAll(s2, s4);//catch not format aceptable exception
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //check transfer file method
        File f7 = new File(12, "test7", ".txt", 150);
        File f8 = new File(13, "test8", ".java", 1);
        Storage s5 = new Storage(7, new File[]{f7}, formats, "Croatia", 1000);
        Storage s6 = new Storage(8, new File[]{f8}, formats, "Croatia", 1000);
        try {
            //controller.transferFile(s2, s1, 1);//catch file not found exception
            //controller.transferFile(s2, s1, 5);//normally transfer
            //controller.transferFile(s5, s1, 12);//catch there is not enough space in next storage
            controller.transferFile(s6, s1, 13);//catch Illeagal format exception
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
