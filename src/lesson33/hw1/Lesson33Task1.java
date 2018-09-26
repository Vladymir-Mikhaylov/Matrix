package lesson33.hw1;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class Lesson33Task1 {

    public static void writeToFileFromConsole(String path) throws FileNotFoundException {
        //check if file exists
        validate(path);

        System.out.println("Enter file content to write: ");

        String content = read();
        write(content, path);
    }

    private static String read(){
        BufferedReader inputStreamReader = new BufferedReader( new InputStreamReader(System.in));
        String consoleData = null;
        try {
            String enteredText = inputStreamReader.readLine();

            //collect printed data
            while(!enteredText.equals("wr")) {
                consoleData += "\r\n" + enteredText;
                enteredText = inputStreamReader.readLine();
            }

        } catch (IOException e) {
            System.err.println("Reading from keyboard failed");
        } finally {
            IOUtils.closeQuietly(inputStreamReader);
        }
        return consoleData;
    }

    private static void write(String consoleData, String path){
        FileWriter fileWriter = null;
        PrintWriter writer = null;
        try {
            fileWriter = new FileWriter(path, true);
            writer = new PrintWriter(fileWriter);
            writer.append("\r\n" + consoleData);
        } catch (IOException e) {
            System.err.println("We can't write to file!");
        } finally {
            IOUtils.closeQuietly(fileWriter);
            IOUtils.closeQuietly(writer);
        }
    }


    private static void validate(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()){
            System.out.println("File with path " + path + " not found");
            throw new FileNotFoundException();
        }
    }
}
