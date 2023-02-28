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
     * @param a first adding argument
     * @param b second adding argument
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * @param a argument from which to subtract
     * @param b subtractive argument
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * @param a first multiplying argument
     * @param b second multiplying argument
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * @param a argument that is divided
     * @param b argument divisor*/

    public double divide(double a, double b) {
        double tmp= 0;

        try {
            tmp= a / b;
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
            tmp= 0;
        }
        return tmp;
    }
}
