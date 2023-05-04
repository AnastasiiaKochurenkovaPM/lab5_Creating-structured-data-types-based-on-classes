package check;

import java.util.Scanner;

public class checkint {
    public static int read_int(Scanner stdin, String prompt){
        int i = -1;
        while( i < 0 ){
            System.out.print(prompt);

            String input = stdin.nextLine();

            try{
                i = Integer.valueOf(input);
            } catch (NumberFormatException e) {
                i = -1;
            }
        }
        return i;
    }
}