/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjstntt;

import java.util.Scanner;

/**
 *
 * @author emeris
 */
public class InputValidator {//(Oracle, 2024).

    private Scanner scanner;

    // One-dimensional array
    private String[] genderOptions = {
        "Male",
        "Female",
        "Other"
    };

    public InputValidator(Scanner scanner) {

        this.scanner = scanner;
    }

    public String getRequiredString(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "ERROR: Input cannot be empty.");
        }
    }

    public String getName(String message) {

        while (true) {

            String input =
                    getRequiredString(message);

            boolean valid = true;

            for (int i = 0;
                 i < input.length();
                 i++) {

                char character =
                        input.charAt(i);

                if (!Character.isLetter(character)
                        && character != ' '
                        && character != '-'
                        && character != '\'') {

                    valid = false;
                    break;
                }
            }

            if (valid) {
                return input;
            }

            System.out.println(
                    "ERROR: Name may only contain "
                    + "letters, spaces, hyphens "
                    + "or apostrophes.");
        }
    }

    public String getPatientID() {

        while (true) {

            String id =
                    getRequiredString("Patient ID: ");

            if (id.length() > 20) {

                System.out.println(
                        "ERROR: Patient ID cannot "
                        + "exceed 20 characters.");

                continue;
            }

            boolean valid = true;

            for (int i = 0;
                 i < id.length();
                 i++) {

                char character =
                        id.charAt(i);

                if (!Character.isLetterOrDigit(character)
                        && character != '-') {

                    valid = false;
                    break;
                }
            }

            if (valid) {
                return id;
            }

            System.out.println(
                    "ERROR: Patient ID may only "
                    + "contain letters, numbers "
                    + "and hyphens.");
        }
    }

    public int getAge() {

        while (true) {

            System.out.print("Age: ");

            String input =
                    scanner.nextLine().trim();

            try {

                int age =
                        Integer.parseInt(input);

                if (age < 0 || age > 120) {

                    System.out.println(
                            "ERROR: Age must be "
                            + "between 0 and 120.");

                    continue;
                }

                return age;

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERROR: Please enter "
                        + "a whole number.");
            }
        }
    }

    public String getGender() {

        while (true) {

            System.out.println("1. " + genderOptions[0]);
            System.out.println("2. " + genderOptions[1]);
            System.out.println("3. " + genderOptions[2]);

            System.out.print("Select gender: ");

            String input =
                    scanner.nextLine().trim();

            try {

                int choice =
                        Integer.parseInt(input);

                if (choice >= 1
                        && choice <= genderOptions.length) {

                    return genderOptions[choice - 1];
                }

                System.out.println(
                        "ERROR: Select 1, 2 or 3.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERROR: Please enter a number.");
            }
        }
    }

    public PatientCategory getCategory() {

        while (true) {

            System.out.println();
            System.out.println("1. Inpatient");
            System.out.println("2. Outpatient");
            System.out.println("3. Emergency");

            System.out.print("Select category: ");

            String input =
                    scanner.nextLine().trim();

            try {

                int choice =
                        Integer.parseInt(input);

                switch (choice) {

                    case 1:
                        return PatientCategory.INPATIENT;

                    case 2:
                        return PatientCategory.OUTPATIENT;

                    case 3:
                        return PatientCategory.EMERGENCY;

                    default:
                        System.out.println(
                                "ERROR: Select 1, 2 or 3.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERROR: Please enter a number.");
            }
        }
    }

    public String getBedNumber() {

        while (true) {

            String input =
                    getRequiredString(
                            "Bed Number (B01-B20): ");

            input = input.toUpperCase();

            if (input.matches("B(0[1-9]|1[0-9]|20)")) {

                return input;
            }

            System.out.println(
                    "ERROR: Enter a valid bed "
                    + "from B01 to B20.");
        }
    }

    public int getMenuChoice(
            int minimum,
            int maximum) {

        while (true) {

            System.out.print("Enter choice: ");

            String input =
                    scanner.nextLine().trim();

            try {

                int choice =
                        Integer.parseInt(input);

                if (choice >= minimum
                        && choice <= maximum) {

                    return choice;
                }

                System.out.println(
                        "ERROR: Enter a number from "
                        + minimum + " to "
                        + maximum + ".");

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERROR: Invalid input. "
                        + "Please enter a number.");
            }
        }
    }

    public boolean getConfirmation(String message) {

        while (true) {

            System.out.print(
                    message + " (Y/N): ");

            String input =
                    scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            }

            if (input.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println(
                    "ERROR: Please enter Y or N.");
        }
    }
}
//Oracle. (2024). The Java Language Specification. https://docs.oracle.com/javase/specs/ [accessed 4 september 2026]