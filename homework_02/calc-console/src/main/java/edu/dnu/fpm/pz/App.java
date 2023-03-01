/**
 * Denys Hudymov
 *
 * Copyright (c) 2023-2024. All Rights Reserved.
 */
package edu.dnu.fpm.pz;

/**
 * App
 *
 * This is the class with main method
 *
 *  @version 1.0 28 Feb 2023
 *  @author Denys Hudymov
 */
public class App 
{
    public static void main( String[] args )
    {
        /** first_argument represent argument for calculation */
        double first_argument;
        /** second_argument represent argument for calculation */
        double second_argument;
        /** result represent result of calculation */
        double result = 0;
        /** calc instance of class CalcImpl*/
        Calc calc= new CalcImpl();
        if (args.length < 3)  {
            System.err.println("Not enought parameters!");
            return;
        }

        try {
            first_argument= Double.parseDouble(args[0]);
        } catch (Exception e) {
            System.err.println("Invalid first argument!");
            return;
        }

        try {
            second_argument= Double.parseDouble(args[1]);
        } catch (Exception e) {
            System.err.println("Invalid second argument!");
            return;
        }

        /** switch used to determine what sign is used */
        switch (args[2]) {
            case "+":
                result = calc.add(first_argument, second_argument);
                break;
            case "-":
                result = calc.subtract(first_argument, second_argument);
                break;
            case "*":
                result = calc.multiply(first_argument, second_argument);
                break;
            case "/":
                result = calc.divide(first_argument, second_argument);
                break;
            default: {
                System.err.println("Invalid operator!");
                return;
            }
        }

        System.out.println("number1= " + first_argument + " number2= " + second_argument + " operator= " + args[2]+ " result= " + result);
    }
}
