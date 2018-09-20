package lesson34;

import java.io.*;

public class ReadWriteFile {
    public static void main(String[] args) {
        String path1 = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test1.txt");
        String path2 = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test3.txt");
        /**
        File file = new File("test3.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        readFile(path1);
        writeFile(path2);
    }

    private static void readFile (String path) {

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ( (line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (FileNotFoundException fEx){
            System.err.println("File doesn't exist!");
        }catch (IOException ioEx){
            System.err.println("Reading from file " + path + " failed");
        }
    }

    private static void writeFile(String path){

        try(PrintWriter printWriter =  new PrintWriter(new FileWriter(path, true))){
            printWriter.append("\r\n" + " - tra ta ta ....22222222");
        }catch (IOException IOex){
            System.err.println("We can't write to file!");
        }
    }

}
