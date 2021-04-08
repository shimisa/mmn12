/**
 * The BigInt class take care of
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */

import java.lang.Math;
import java.lang.ArithmeticException;
import java.util.ArrayList;


public class BigInt implements Comparale {

    private static final int ZERO_ASCII = 48;
    private static final int NINE_ASCII = 57;
    private static final int PLUS_ASCII = 43;
    private static final int MINUS_ASCII = 45;
    private static final int MIN_LENGTH_SIGN = 2;
    private static final int MAX_VAL_IN_LIST = 10;
    private static final int RADIX = 10;

    private ArrayList<Integer> bigIntList; // the list preserved the digits of the BigInt
    private char sign = PLUS_ASCII;


    /* constructors for BigInt */
    public BigInt(String strNumber){
        bigIntList = new ArrayList<Integer>(); // initialized a new arrayList
        int i = 0;
        /* validation conditions for the sign if exist - if not it's plus by default */
        if(strNumber.length() > 0){
            if(strNumber.charAt(0) == MINUS_ASCII && strNumber.length() >= MIN_LENGTH_SIGN) {
                sign = MINUS_ASCII;
                i++;
            }
            if(strNumber.charAt(0) == PLUS_ASCII && strNumber.length() >= MIN_LENGTH_SIGN) {
                sign = PLUS_ASCII;
                i++;
            }
            /* convert each char and append the array list  */
            for (int j = strNumber.length() - 1 ; j >= i ; j--) { // iterate over the string strNumber
                /* validation conditions for the string number*/
                if(strNumber.charAt(j) < ZERO_ASCII || strNumber.charAt(j) > NINE_ASCII) {
                    throw new IllegalArgumentException("The string is not a legal Integer");
                }
                int digit = Character.getNumericValue(strNumber.charAt(j)); // convert each char in the strNumber to int
                bigIntList.add(digit); // append the bigNumber list with the that digit
            }
        }
        else
            throw new IllegalArgumentException("The string is empty");

    }
    /* constructor for building a new empty arrayList */
    private BigInt(){
        bigIntList = new ArrayList<Integer>();
    }

    /* constructor for building a copy of the bigInt */
    public BigInt(BigInt bigInt){
        bigIntList = new ArrayList<Integer>(bigInt.bigIntList);
    }

    /* first take care of the sign of the two lists and suitable the right action to private method */
    public BigInt plus(BigInt bigInt){
        if(this.sign == PLUS_ASCII && bigInt.sign == MINUS_ASCII)
            return this.privateMinus(bigInt);
        else if(bigInt.sign == PLUS_ASCII && this.sign == MINUS_ASCII)
            return bigInt.privateMinus(this);

        else
            return this.privatePlus(bigInt);
    }

    /* first take care of the sign of the two lists and suitable the right action to private method */
    public BigInt minus(BigInt bigInt){
        if(this.sign == PLUS_ASCII && bigInt.sign == MINUS_ASCII) {
            bigInt.sign = PLUS_ASCII;
            return this.privatePlus(bigInt);
        }
        else if(this.sign == MINUS_ASCII && bigInt.sign == PLUS_ASCII){
            bigInt.sign = MINUS_ASCII;
            return bigInt.privatePlus(this);
        }
        else if(this.sign == PLUS_ASCII && bigInt.sign == PLUS_ASCII)
            return this.privateMinus(bigInt);
        else
            return bigInt.privateMinus(this);

    }

    /* create a new BigInt instance and update it with the sum action  */
    private BigInt privatePlus(BigInt bigInt){
        int tempSum;    // temp int to calculate the sum of 3 digits
        boolean thisIsLonger = isThisLonger(bigInt);
        final int longerSize = longerSize(thisIsLonger, bigInt); // returns the longer size
        final int shorterSize = shorterSize(thisIsLonger, bigInt); // returns the shorter size
        int remainder = 0;
        /* creates instance of BigInt to hold the sum */
        BigInt sumBigInt= new BigInt();  // creates new BigInt with empty list
        sumBigInt.sign = bigInt.sign; // the sign is remaining the same as the parameter

        /* addition till the shorter size of the list */
        for (int i = 0 ; i < shorterSize; i++) {
            tempSum = bigInt.bigIntList.get(i) + bigIntList.get(i) + remainder;
            sumBigInt.bigIntList.add(i, tempSum);
            if(tempSum >= MAX_VAL_IN_LIST){
                remainder = 1;
                sumBigInt.bigIntList.set(i, tempSum - MAX_VAL_IN_LIST);
            }
            else
                remainder = 0;
        }

        /* addition from the shorter size list till the longer size list */
        if(thisIsLonger) {
            for (int i = shorterSize ; i < longerSize; i++) {
                tempSum =  bigIntList.get(i) + remainder;
                sumBigInt.bigIntList.add(i, tempSum);
                if(tempSum >= MAX_VAL_IN_LIST){
                    remainder = 1;
                    sumBigInt.bigIntList.set(i, tempSum - MAX_VAL_IN_LIST);
                }
                else
                    remainder = 0;

            }
        }
        else {
            for (int i = shorterSize ; i < longerSize; i++) {
                tempSum =  bigInt.bigIntList.get(i) + remainder;
                sumBigInt.bigIntList.add(i, tempSum);
                if(tempSum >= MAX_VAL_IN_LIST){
                    remainder = 1;
                    sumBigInt.bigIntList.set(i, tempSum - MAX_VAL_IN_LIST);
                }
                else
                    remainder = 0;
            }
        }
        /* if needed, add another cell to the list and add 1 in there */
        if (remainder == 1)
            sumBigInt.bigIntList.add(1);

        return sumBigInt; // returns the new sum list
    }

    private BigInt privateMinus(BigInt bigInt) {
        boolean thisIsLonger = isThisLonger(bigInt);
        boolean sameSize = isSameSize(bigInt);
        final int longerSize = longerSize(thisIsLonger, bigInt); // returns the longer size
        final int shorterSize = shorterSize(thisIsLonger, bigInt); // returns the shorter size
        boolean thisIsGreater = isThisGreater(longerSize, thisIsLonger, sameSize, bigInt); // is this is greater returns true
        BigInt greaterBigInt = greaterBigInt(thisIsGreater, bigInt); // copy of the Greater bigInt
        BigInt smallerBigInt = smallerBigInt(thisIsGreater, bigInt); // copy of the Greater bigInt
        BigInt minusBigInt = new BigInt();  // creates new BigInt with empty list


        /* determine the sign of the result minusBigInt */
        if (thisIsGreater)
            minusBigInt.sign = PLUS_ASCII;
        else
            minusBigInt.sign = MINUS_ASCII;

        /* the long minus algorithm */
        for (int i = 0; i < shorterSize ; i++) {
            if (greaterBigInt.bigIntList.get(i) >= smallerBigInt.bigIntList.get(i)) {
                minusBigInt.bigIntList.add(greaterBigInt.bigIntList.get(i) - smallerBigInt.bigIntList.get(i));
            }
            else{ // if needed go to take from from the neighbor
                for(int j = i + 1 ; j < longerSize ; j++){
                    if(greaterBigInt.bigIntList.get(j) >= 1){
                        greaterBigInt.bigIntList.set(j, greaterBigInt.bigIntList.get(j) - 1);
                        for(int k = j - 1 ; k >= i ; k--){
                            if(k != i)
                                greaterBigInt.bigIntList.set(k,9);
                            else
                                minusBigInt.bigIntList.add( MAX_VAL_IN_LIST + greaterBigInt.bigIntList.get(i) - smallerBigInt.bigIntList.get(i) );
                        }
                        break;
                    }

                }
            }
        }
        for (int i = shorterSize ; i < longerSize ; i++) { // complete the number
            minusBigInt.bigIntList.add(greaterBigInt.bigIntList.get(i));
        }

        /* if there are zeros on the end so remove them */
        for (int i = minusBigInt.bigIntList.size() - 1 ; i >= 1 ; i--) {
            if(minusBigInt.bigIntList.get(i) == 0)
                minusBigInt.bigIntList.remove(i);
            else
                break;
        }
            minusBigInt.sign = zeroSign(minusBigInt); // if it's 0 so change the sign to plus

        return minusBigInt;
    }


    public BigInt multiply(BigInt bigInt){
        boolean thisIsLonger = isThisLonger(bigInt);
        final int longerSize = longerSize(thisIsLonger, bigInt); // returns the longer size
        final int shorterSize = shorterSize(thisIsLonger, bigInt); // returns the shorter size
        BigInt multBigInt = new BigInt(); // creates new BigInt of the multiplication with empty list
        BigInt longerBigInt; // copy of the Greater bigInt
        BigInt shorterBigInt; // copy of the Greater bigInt
        int result;
        int reminder;

        /* determine the longer and shorter BigInt */
        if(thisIsLonger){
            longerBigInt = new BigInt(this);
            shorterBigInt = new BigInt(bigInt);
        }
        else {
            longerBigInt = new BigInt(bigInt);
            shorterBigInt = new BigInt(this);
        }
        /* the multiplication algorithm */
        for(int i = 0 ; i < longerSize ; i++){
            BigInt temp = new BigInt(); // creates temp BigInt to hold the number of each iteration
            reminder = 0; // zero the remainder each iteration
            for(int k = 0 ; k < i ; k++) // add zeros for the temp, hence multiply the sums
                temp.bigIntList.add(0);
            for (int j = 0 ; j < shorterSize ; j++) {
                result = longerBigInt.bigIntList.get(i) * shorterBigInt.bigIntList.get(j) + reminder; // calculate each result for each iteration
                if (result >= MAX_VAL_IN_LIST){ // take care of the remainders
                    if(j != shorterSize - 1){
                        reminder = result / MAX_VAL_IN_LIST ;
                        result = result % MAX_VAL_IN_LIST;
                        temp.bigIntList.add(result);
                    }
                    else{
                        reminder = result / MAX_VAL_IN_LIST ; // if it's the end of the longer list so add the remainder as well
                        result = result % MAX_VAL_IN_LIST;
                        temp.bigIntList.add(result);
                        temp.bigIntList.add(reminder);
                    }

                }
                else
                temp.bigIntList.add(result); // if it's less then 10 add the result as it is
            }
            multBigInt = multBigInt.plus(temp); // add all the sums together for the final result
        }
        multBigInt.sign = signOfMultDivide(bigInt); // determine the sign of the result BigInt
        /* if there are zeros on the end so remove them */
        for (int i = multBigInt.bigIntList.size() - 1 ; i >= 1 ; i--) {
            if(multBigInt.bigIntList.get(i) == 0)
                multBigInt.bigIntList.remove(i);
            else
                break;
        }
        multBigInt.sign = zeroSign(multBigInt); // if it's 0 so change the sign to plus

        return multBigInt;
    }

    public BigInt divide(BigInt bigInt){
        boolean thisIsLonger = isThisLonger(bigInt);
        boolean sameSize = isSameSize(bigInt);
        final int longerSize = longerSize(thisIsLonger, bigInt); // returns the longer size
        final int shorterSize = shorterSize(thisIsLonger, bigInt); // returns the shorter size
        boolean thisIsGreater = isThisGreater(longerSize, thisIsLonger, sameSize, bigInt); // is this is greater returns true
        BigInt divideBigInt = new BigInt();  // creates new BigInt with empty list
        divideBigInt.sign = signOfMultDivide(bigInt); // determine the sign of the result BigInt
        BigInt greaterBigInt = greaterBigInt(thisIsGreater, bigInt); // copy of the greater bigInt
        BigInt smallerBigInt = smallerBigInt(thisIsGreater, bigInt); // copy of the Smaller bigInt
        double res = 0; // to hold the temporary result
        int divider = 0;

        if(shorterSize == 1 && smallerBigInt.bigIntList.get(0) == 0){ // divide by 0 is impossible
            throw new ArithmeticException("It is impossible to divide by 0 ");
        }
        if(!thisIsGreater && !equals(bigInt)){ // if this number is smaller them the parameter then the divide result is 0
            divideBigInt.bigIntList.add(0);
            return divideBigInt;
        }
        /* creates the divider frm the divider list */
        for(int i = 0 ; i < shorterSize ; i++){
            divider += smallerBigInt.bigIntList.get(i) * Math.pow(RADIX,(i));
        }
        /* divide each digit in the list and sum the results */
        for(int i = longerSize - 1 ; i >= 0 ; i--){
            res += ((greaterBigInt.bigIntList.get(i) * Math.pow(RADIX,(i))) / divider);
        }
        //System.out.println("res: "+res);
        /* insert the result into the new divideBigInt */
        while((int)res != 0){
            divideBigInt.bigIntList.add((int)res % RADIX);
            res = res / RADIX;
        }
        return divideBigInt;
    }

    /* returns the greater bigInt */
    private BigInt greaterBigInt(boolean thisIsGreater,BigInt bigInt){
        if (thisIsGreater)
            return new BigInt(this);

        else
            return new BigInt(bigInt);

    }

    /* returns the smaller bigInt */
    private BigInt smallerBigInt(boolean thisIsGreater,BigInt bigInt){
        if (thisIsGreater)
            return new BigInt(bigInt);

        else
            return new BigInt(this);

    }

    /* determine the sign of the BigInt for multiply and divide */
    private char signOfMultDivide(BigInt bigInt){
        if((this.sign == PLUS_ASCII && bigInt.sign == MINUS_ASCII) || (this.sign == MINUS_ASCII && bigInt.sign == PLUS_ASCII))
           return MINUS_ASCII;

        return PLUS_ASCII;
    }

    /* determine who is greater number */
    private boolean isThisGreater(int longerSize, boolean thisIsLonger, boolean sameSize, BigInt bigInt){
        if(sameSize){
            for(int i = longerSize - 1 ; i >= 0 ; i-- ){
                if(this.bigIntList.get(i) > bigInt.bigIntList.get(i)) {
                    return true;
                }
            }
        }
        else if(thisIsLonger)
            return true;
        return false;
    }

    /* return the longer list Size */
    private int longerSize(boolean thisIsLonger, BigInt bigInt){

            if(thisIsLonger)
                return this.bigIntList.size();
            else
                return bigInt.bigIntList.size();

    }

    /* return the shorter list Size */
    private int shorterSize(boolean thisIsLonger, BigInt bigInt){

        if(thisIsLonger)
            return bigInt.bigIntList.size();
        else
            return this.bigIntList.size();

    }

    /* determine which of the BigInts has the longer list */
    private boolean isThisLonger(BigInt bigInt){
        int sizeOfThisList = this.bigIntList.size();
        int sizeOfParameterList = bigInt.bigIntList.size();
        if (sizeOfThisList >= sizeOfParameterList){
            return true;
        }
        else
            return false;
    }
    /* determine if the BigInts has the same list size  */
    private boolean isSameSize(BigInt bigInt){
        int sizeOfThisList = this.bigIntList.size();
        int sizeOfParameterList = bigInt.bigIntList.size();
        if (sizeOfThisList == sizeOfParameterList)
            return true;
        else
            return false;
    }

    /* if the BigInt is zero change the sign to plus */
    private char zeroSign(BigInt bigInt){
        if(bigInt.bigIntList.size() == 1 && bigInt.bigIntList.get(0) == 0)
            return PLUS_ASCII;
        else
            return bigInt.sign;
    }

    /* returns a string representation of the BigInt */
    public String toString(){
        int size = bigIntList.size();
        String number = "";
        char c;
        if(this.sign == MINUS_ASCII)
            number += '-';
        for (int i = size - 1 ; i >= 0 ; i--) {
            c = Character.forDigit(bigIntList.get(i), RADIX);
            number += c;
        }
        return number;
    }

    public boolean equals(BigInt bigInt){
        boolean sameSize = isSameSize(bigInt);
        int size;
        if(!sameSize)
            return false;
        size = bigIntList.size();

        for(int i = 0 ; i < size ; i++){
            if(bigInt.bigIntList.get(i) != this.bigIntList.get(i))
                return false;
        }
        return true;
    }
    /* if the same number returns zero, otherwise returns 1 or -1 */
    @Override
    public int compareTo(BigInt bigInt) {
        boolean thisIsLonger = isThisLonger(bigInt);
        boolean sameSize = isSameSize(bigInt);
        final int longerSize = longerSize(thisIsLonger, bigInt); // returns the longer size
        boolean thisIsGreater = isThisGreater(longerSize, thisIsLonger, sameSize, bigInt); // is this is greater returns true
        if(this.equals(bigInt))
        return 0;
        else if(thisIsGreater)
            return 1;
        else
            return -1;
    }

    public static boolean stringValidation(String str){
        int i = 0;
        /* validation conditions for the sign if exist - if not it's plus by default */
        if(str.length() > 0){
            if(str.charAt(0) == MINUS_ASCII && str.length() >= MIN_LENGTH_SIGN) {
                i++;
            }
            if(str.charAt(0) == PLUS_ASCII && str.length() >= MIN_LENGTH_SIGN) {
                i++;
            }
            for (int j = i ; j < str.length() ; j++) { // iterate over the string strNumber
                /* validation conditions for the string number*/
                if(str.charAt(j) < ZERO_ASCII || str.charAt(j) > NINE_ASCII) {
                    System.out.println("The string is not a legal Integer");
                    return false;
                }
            }
        }
        else{
            System.out.println("The string is empty");
            return false;
        }
        return true;
    }
}
