package lesson34;

import java.io.*;

public class Practise {

    public static void main(String[] args)throws Exception {
        String pathFrom = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test3.txt");
        String pathTo = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test1.txt");

        //copyFileContent(pathFrom, pathTo);
        transferFileContent(pathFrom, pathTo);
    }

    public static void copyFileContent(String fileFromPath, String fileToPath)throws Exception{
        //check if file exist +
        //check rules +
        //read file's content from +
        //write file's content to +

        // check if exist and check rules
        validate(fileFromPath, fileToPath);
        //read and write
        writeToFile(fileToPath, readFromFile(fileFromPath));
    }

    private static void validate(String fileFromPath, String fileToPath)throws Exception{
        validateIfFileExist(fileFromPath);
        validateIfFileExist(fileToPath);

        validateReadingFromFile(fileFromPath);
        validateWritingToFile(fileToPath);
    }

    private static void validateIfFileExist(String path)throws FileNotFoundException{
        File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException("File " + path + " doesn't exist");
        }
    }

    private static void validateReadingFromFile(String path) throws Exception{
        File file = new File(path);
        if(!file.canRead()){
            throw new Exception("File " + path + " doesn't have permission for reading");
        }
    }

    private static void validateWritingToFile(String path)throws Exception {
        File file = new File(path);
        if(!file.canWrite()){
            throw new Exception("File " + path + " doesn't have permission for writing");
        }
    }

    private static StringBuffer readFromFile(String path){
        StringBuffer res = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ( (line = br.readLine()) != null) {
                res.append("\r\n" + line);
            }
        }catch (FileNotFoundException fEx){
            System.err.println("File doesn't exist!");
        }catch (IOException ioEx){
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }

    private static void writeToFile(String path, StringBuffer content){
        try(PrintWriter printWriter =  new PrintWriter(new FileWriter(path, true))){
            printWriter.append("\r\n" + content);
        }catch (IOException IOex){
            System.err.println("We can't write to file!");
        }
    }

    public static void transferFileContent(String fileFromPath, String fileToPath)throws Exception{
        //validate
        validateIfFileExist(fileFromPath);
        validateIfFileExist(fileToPath);

        validateReadingFromFile(fileFromPath);
        validateWritingToFile(fileFromPath);
        validateWritingToFile(fileToPath);
        //read and write
        writeToFile(fileToPath, readFromFile(fileFromPath));
        //clean up the file
        cleanUpFile(fileFromPath);
    }

    private static void cleanUpFile(String path){
        try(PrintWriter printWriter =  new PrintWriter(new FileWriter(path, false))){
            printWriter.append("");
        }catch (IOException IOex){
            System.err.println("We can't write to file!");
        }
    }

}
