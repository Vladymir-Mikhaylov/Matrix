package lesson33;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ReadWriteFile {
    public static void main(String[] args) {
        String path1 = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test1.txt");
        String path2 = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test3.txt");
        //String path = ".\\src\\";
        readFile(path1);
        writeFile(path2);
    }

    private static void readFile (String path) {

        FileReader reader;
        try {
            reader = new FileReader(path);

        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist!");
            return;
        }

        BufferedReader br = new BufferedReader(reader);
        try {
            String line;
            while ( (line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Reading from file " + "E:\\MEGA\\PT\\test.txt" + " failed");
        }
        finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(br);
        }
    }

    private static void writeFile(String path){
        FileWriter writer = null;
        BufferedWriter bufferedWriterr = null;
        try {
            //todo use other constructor
            writer = new FileWriter(path, true);

            bufferedWriterr = new BufferedWriter(writer);

            bufferedWriterr.append("tra ta ta ....");
            bufferedWriterr.append("zzzzzzzzz");
        } catch (IOException e) {
            System.err.println("We can't write to file!");
            return;
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(bufferedWriterr);
        }
    }
}
