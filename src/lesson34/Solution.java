package lesson34;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class Solution {

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception{
        validate(fileFromPath, fileToPath);

        String sentences = readFromFile(fileFromPath).toString();
        String[] arrSentences = sentences.split("\\.");
        LinkedList<String> sentencesFromFile = new LinkedList<>(Arrays.asList(arrSentences));

        writeToFile(fileToPath, searchAndGetSentences(sentencesFromFile, word), true);

        writeToFile(fileFromPath, prepareLeftSentences(sentencesFromFile), false);
    }

    public static void validate(String fileFromPath, String fileToPath)throws Exception{
        validateIfFileExist(fileFromPath);
        validateIfFileExist(fileToPath);

        validateReadingFromFile(fileFromPath);

        validateWritingToFile(fileFromPath);
        validateWritingToFile(fileToPath);
    }

    private static void validateIfFileExist(String path)throws FileNotFoundException {
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

    private static StringBuilder readFromFile(String path){
        StringBuilder res = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ( (line = br.readLine()) != null) {
                res.append(line);
            }
        }catch (FileNotFoundException fEx){
            System.err.println("File doesn't exist!");
        }catch (IOException ioEx){
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }

    //if @rewrite=true - we add some text to file, @rewrite=false - we rewrite content with new text
    private static void writeToFile(String path, StringBuilder content, boolean rewrite){
        try(PrintWriter printWriter =  new PrintWriter(new FileWriter(path, rewrite))){
            printWriter.append(content + "\r\n");
        }catch (IOException IOex){
            System.err.println("We can't write to file!");
        }
    }

    private static StringBuilder searchAndGetSentences(LinkedList<String> sentences, String word){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < sentences.size(); i++){
            String sentence_i = sentences.get(i);
            if(sentence_i.length() > 10 && sentence_i.contains(word)){
                result.append(sentence_i + "\r\n");
                sentences.remove(i);
                i--;
            }
        }
        return result;
    }

    private static StringBuilder prepareLeftSentences(LinkedList<String> leftSentences){
        StringBuilder result = new StringBuilder();

        for (String sentence : leftSentences){
            result.append(sentence + "\r\n");
        }
        return result;
    }
}
