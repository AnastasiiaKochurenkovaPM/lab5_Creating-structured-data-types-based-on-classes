package check;

import java.util.Scanner;
public class checkdouble {
    public static double read_double(Scanner stdin, String prompt){
        double i = -1;
        while( i < 0 ){
            System.out.print(prompt);

            String input = stdin.nextLine();

            try{
                i = Double.valueOf(input);
            } catch (NumberFormatException e) {
                i = -1;
            }
        }
        return i;
    }
}
