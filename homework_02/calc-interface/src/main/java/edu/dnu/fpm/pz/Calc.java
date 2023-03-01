/**
 * Denys Hudymov
 * Copyright (c) 2023-2024. All Rights Reserved.
 */
package edu.dnu.fpm.pz;

/**
 * This is an interface Calc
 *
 *  @version 1.0 28 Feb 2023
 *  @author Denys Hudymov
 */
public interface Calc
{
    /** method add used for adding 2 arguments  */
    double add(double first_argument, double second_argument);

    /** method subtract used for subtract 2 argument from 1  */
    double subtract(double first_argument, double second_argument);

    /** method multiply used for multiplying 2 arguments  */
    double multiply(double first_argument, double second_argument);

    /** method divide used for dividing 1 argument for 2  */
    double divide(double first_argument, double second_argument);
}
