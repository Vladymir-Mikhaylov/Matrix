package lesson33.hw2;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class Demo {
    public static void main(String[] args) {
        //String path = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test3.txt");
        readFileByConsolePath();
    }

    public static void readFileByConsolePath(){
        BufferedReader inputStreamReader = new BufferedReader( new InputStreamReader(System.in));
        FileWriter fileWriter = null;
        PrintWriter writer = null;

        System.out.println("Please, enter file path for read: ");
        try {
            String path = inputStreamReader.readLine();
            //check if file exists
            FileReader reader;
            try {
                reader = new FileReader(path);
            }catch (FileNotFoundException ex){
                System.out.println("File with path " + path + " not found");
                return;
            }
            //print data from file by next @path@
            BufferedReader br = new BufferedReader(reader);
            try {
                String line;
                while ( (line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("Can't read file by path " + path);
            }
            finally {
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly(br);
            }
        } catch (IOException e) {
            System.err.println("Reading from keyboard failed");
        } finally {
            IOUtils.closeQuietly(inputStreamReader);
        }
    }
}
