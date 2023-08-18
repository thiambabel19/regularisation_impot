package com.babel.helper;

import org.thymeleaf.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Helper {

    public static String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static boolean isDateValid(String dateString, String format) {
        if (StringUtils.isEmpty(dateString)) {
            return false;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(dateString, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

//    public static void main(String[] args) {
//        // Test convertDateToString method
//        Date currentDate = new Date();
//        String formattedDate = Helper.convertDateToString(currentDate);
//        System.out.println("Converted Date to String: " + formattedDate);
//
//        // Test isDateValid method
//        String dateString = "31/08/2023";
//        String dateFormat = "dd/MM/yyyy";
//        boolean isValid = Helper.isDateValid(dateString, dateFormat);
//        if (isValid) {
//            System.out.println("Date is valid.");
//        } else {
//            System.out.println("Date is not valid.");
//        }
//
//        String invalidDateString = "2023-08-31";
//        boolean isInvalid = Helper.isDateValid(invalidDateString, dateFormat);
//        if (isInvalid) {
//            System.out.println("Invalid date is mistakenly considered valid.");
//        } else {
//            System.out.println("Invalid date is correctly identified as not valid.");
//        }
//    }
}
