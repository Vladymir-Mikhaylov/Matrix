package lesson34;

import java.io.*;

public class Practise {

    public static void main(String[] args)throws Exception {
        String pathFrom = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test3.txt");
        String pathTo = new String("E:\\MEGA\\PT\\java-core-grom_fixed\\test1.txt");
        copyFileContent(pathFrom, pathTo);
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
        File fileTo = new File(fileToPath);
        File fileFrom = new File(fileFromPath);

        if(!fileTo.exists()){
            throw new FileNotFoundException("File " + fileToPath + " doesn't exist");
        }

        if(!fileFrom.exists()){
            throw new FileNotFoundException("File " + fileFromPath + " doesn't exist");
        }

        if(!fileFrom.canRead()){
            throw new Exception("File " + fileFromPath + " doesn't have permission for reading");
        }

        if(!fileTo.canWrite()){
            throw new Exception("File " + fileToPath + " doesn't have permission for writing");
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

}
