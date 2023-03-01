/**
 * Denys Hudymov
 *
 * Copyright (c) 2023-2024. All Rights Reserved.
 */
package edu.dnu.fpm.pz;

/**
 * This is implementation of CalcImp
 *
 *  @version 1.0 28 Feb 2023
 *  @author Denys Hudymov
 */
public class CalcImpl implements Calc
{
    /**
     * @param first_argument first adding argument
     * @param second_argument second adding argument
     */
    public double add(double first_argument, double second_argument) {
        return first_argument + second_argument;
    }

    /**
     * @param first_argument argument from which to subtract
     * @param second_argument subtractive argument
     */
    public double subtract(double first_argument, double second_argument) {
        return first_argument - second_argument;
    }

    /**
     * @param first_argument first multiplying argument
     * @param second_argument second multiplying argument
     */
    public double multiply(double first_argument, double second_argument) {
        return first_argument * second_argument;
    }

    /**
     * @param first_argument argument that is divided
     * @param second_argument argument divisor*/

    public double divide(double first_argument, double second_argument) {
        double tmp= 0;

        try {
            tmp= first_argument / second_argument;
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
            tmp= 0;
        }
        return tmp;
    }
}
