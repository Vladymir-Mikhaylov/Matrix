package lesson32.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public void readNumbers() throws IOException {
        int attemtsAmount = 10;
        int errors = 3;
        int sum = 0;

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        while (attemtsAmount > 0){
            String inputData = br.readLine();

            if(!checkInput(inputData)){
                errors--;
                if(errors == 0){
                    System.out.println("Your numbers arewrog. Number of attempts exceeded");
                    System.exit(0);
                }
                System.out.println("Your numbers are wrong. You have " + errors + " attempts to try again");
                continue;
            }

            sum += Integer.parseInt(inputData);
            attemtsAmount--;
            if(attemtsAmount == 0){
                System.out.println(sum);
            }
        }

        reader.close();
        br.close();

    }

    private boolean checkInput (String data){
        try{
            int numb = Integer.parseInt(data);
            if(numb > 100){
                return false;
            }
        }catch (NumberFormatException numberFormatEx){
            return false;
        }
        return true;
    }
}
