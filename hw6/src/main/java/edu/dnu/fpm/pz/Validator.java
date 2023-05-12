package edu.dnu.fpm.pz;

public class Validator {
    final private static int MIN_PHONE_LENGTH = 8;
    final private static int MAX_PHONE_LENGTH = 13;
    final private static int MIN_NAME_LENGTH = 1;
    final private static int MAX_NAME_LENGTH = 25;
    final private static int MIN_POS_LENGTH = 3;
    final private static int MAX_POS_LENGTH = 100;
    final private static int MIN_SALARY = 1000;
    final private static int MAX_SALARY = 1000000;

    private static boolean validateLength(int length, int lowerBound, int upperBound) {
        return length > upperBound || length < lowerBound;
    }

    public static void validateNumber(Employer employer) throws MyException {
        if (validateLength(employer.getPhone().length(),
                MIN_PHONE_LENGTH, MAX_PHONE_LENGTH)) {
            throw new MyException("Incorrect phone number: " + employer.getPhone());
        }
    }

    public static void validateName(Employer employer) throws MyException {
        if (validateLength(employer.getName().length(),
                MIN_NAME_LENGTH, MAX_NAME_LENGTH)) {
            throw new MyException("Incorrect name: " + employer.getName());
        }
    }

    public static void validateSurname(Employer employer) throws MyException {
        if (validateLength(employer.getSurname().length(),
                MIN_NAME_LENGTH, MAX_NAME_LENGTH)) {
            throw new MyException("Incorrect surname: " + employer.getSurname());
        }
    }

    public static void validatePosition(Vacancy vacancy) throws MyException {
        if (validateLength(vacancy.getPosition().length(),
                MIN_POS_LENGTH, MAX_POS_LENGTH)) {
            throw new MyException("Incorrect position: " + vacancy.getPosition());
        }
    }

    public static void validateSalary(Vacancy vacancy) throws MyException {
        if (vacancy.getSalary() > MAX_SALARY || vacancy.getSalary() < MIN_SALARY) {
            throw new MyException("Incorrect salary: " + vacancy.getSalary());
        }
    }
}
