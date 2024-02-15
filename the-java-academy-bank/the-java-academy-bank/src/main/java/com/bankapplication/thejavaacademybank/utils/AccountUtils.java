package com.bankapplication.thejavaacademybank.utils;

import java.time.Year;

//Class that has method which generates account number for each user.
public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";

    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created!";
    public static final String ACCOUNT_CREATION_SUCCESS = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account has been successfully created!";

    public static String generateAccountNumber()
    {
         /*
      2024+randomSixDigits (concat 2024 with six random digits)
     */
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

        //generate random number between min and max
        int randomNumber = (int)Math.floor(Math.random() * (max - min + 1)+ min);
        //convert the current and randomNumber to strings, then concat them together

        String year = String.valueOf(currentYear);
        String randNumber = String.valueOf(randomNumber);
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append(year).append(randNumber).toString();
    }
}