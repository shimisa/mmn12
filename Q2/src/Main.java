/**
 *
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /* pointers for the BigInts and the Strings that they will made of */
        BigInt firstBigInt;
        BigInt secondBigInt;
        String firstNumber;
        String secondNumber;

        /* loop for the first number */
        while(true){
            System.out.println("Please enter the first number");
            firstNumber = scan.nextLine(); // read the first number from the user
            if(!BigInt.stringValidation(firstNumber))
                continue;
            firstBigInt = new BigInt(firstNumber);
            break;
        }
        /* loop for the second number */
        while(true){
            System.out.println("Please enter the second number");
            secondNumber = scan.nextLine(); // read the first number from the user
            if(!BigInt.stringValidation(secondNumber))
                continue;
            secondBigInt = new BigInt(secondNumber);
            break;
        }
        /* calculation and printing */
        BigInt plus = firstBigInt.plus(secondBigInt);
        System.out.println(firstBigInt.toString() + " + " + secondBigInt.toString() + " = " + plus.toString());
        BigInt minus = firstBigInt.minus(secondBigInt);
        System.out.println(firstBigInt.toString() + " - " + secondBigInt.toString() + " = " + minus.toString());
        BigInt multiply = firstBigInt.multiply(secondBigInt);
        System.out.println(firstBigInt.toString() + " * " + secondBigInt.toString() + " = " + multiply.toString());
        BigInt divide = firstBigInt.divide(secondBigInt);
        System.out.println(firstBigInt.toString() + " / " + secondBigInt.toString() + " = " + divide.toString());




    }

}
