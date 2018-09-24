package lesson33.hw2;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws Exception{
        //String path = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test3.txt");
        readFileByConsolePath();
    }

    public static void readFileByConsolePath()throws Exception{
        String path = readPath();
        validate(path);
        readFileAndWrite(path);
    }

    private static void validate(String path) throws Exception {
        validateIfExist(path);
        validateIfCanRead(path);
    }

    private static void validateIfExist(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()){
            System.out.println("File with path " + path + " not found");
            throw new FileNotFoundException();
        }
    }

    private static void validateIfCanRead(String path) throws Exception {
        File file = new File(path);
        if(!file.canRead()){
            System.out.println("File with path " + path + " can't be read");
            throw new Exception();
        }
    }

    private static String readPath(){
        BufferedReader inputStreamReader = new BufferedReader( new InputStreamReader(System.in));
        String path = "";
        try {
            path = inputStreamReader.readLine();
        } catch (IOException e) {
            System.err.println("Reading from keyboard failed");
        } finally {
            IOUtils.closeQuietly(inputStreamReader);
        }
        return path;
    }

    private static void readFileAndWrite(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            String line;
            while ( (line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Can't read file by path " + path);
        }
        finally {
            IOUtils.closeQuietly(br);
        }
    }
}
