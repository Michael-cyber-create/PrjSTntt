/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjstntt;

/**
 *
 * @author emeris
 */
public class Ward {

    private static final int ROWS = 4;
    private static final int COLUMNS = 5;
    private static final int TOTAL_BEDS = ROWS * COLUMNS;

    private Bed[][] beds;//(Oracle, 2024)

    public Ward() {

        beds = new Bed[ROWS][COLUMNS];

        int bedNumber = 1;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                String number =
                        String.format("B%02d", bedNumber);

                beds[row][column] =
                        new Bed(number);

                bedNumber++;
            }
        }
    }

    public Bed[][] getBeds() {

        return beds;
    }

    public Bed findBed(String bedNumber) {

        if (bedNumber == null) {
            return null;
        }

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                if (beds[row][column]
                        .getBedNumber()
                        .equalsIgnoreCase(bedNumber.trim())) {

                    return beds[row][column];
                }
            }
        }

        return null;
    }

    public boolean allocateBed(String bedNumber,
                               String patientID) {

        if (patientID == null
                || patientID.trim().isEmpty()) {

            return false;
        }

        Bed bed = findBed(bedNumber);

        if (bed == null || bed.isOccupied()) {
            return false;
        }

        bed.occupy(patientID.trim());

        return true;
    }

    public boolean releaseBed(String bedNumber) {

        Bed bed = findBed(bedNumber);

        if (bed == null || !bed.isOccupied()) {
            return false;
        }

        bed.release();

        return true;
    }

    public boolean hasAvailableBed() {

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                if (!beds[row][column].isOccupied()) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getOccupiedBedCount() {

        int count = 0;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                if (beds[row][column].isOccupied()) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getAvailableBedCount() {

        return TOTAL_BEDS - getOccupiedBedCount();
    }

    public int getTotalBeds() {

        return TOTAL_BEDS;
    }

    public void displayLayout() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("                 WARD LAYOUT");
        System.out.println("----------------------------------------------");

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                Bed bed = beds[row][column];

                if (bed.isOccupied()) {

                    System.out.printf(
                            "[%s:%s] ",
                            bed.getBedNumber(),
                            bed.getPatientID());

                } else {

                    System.out.printf(
                            "[%s:FREE] ",
                            bed.getBedNumber());
                }
            }

            System.out.println();
        }

        System.out.println("----------------------------------------------");
    }

    public void displayAvailableBeds() {

        System.out.println();
        System.out.println("-------- AVAILABLE BEDS --------");

        boolean found = false;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                Bed bed = beds[row][column];

                if (!bed.isOccupied()) {

                    System.out.print(
                            bed.getBedNumber() + "  ");

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No beds are available.");
        }

        System.out.println();
    }

    public void displayOccupiedBeds() {

        System.out.println();
        System.out.println("-------- OCCUPIED BEDS --------");

        boolean found = false;

        for (int row = 0; row < beds.length; row++) {

            for (int column = 0;
                 column < beds[row].length;
                 column++) {

                Bed bed = beds[row][column];

                if (bed.isOccupied()) {

                    System.out.println(
                            bed.getBedNumber()
                            + " -> Patient "
                            + bed.getPatientID());

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No beds are occupied.");
        }
    }
}   

//Oracle. (2024). The Java Language Specification. https://docs.oracle.com/javase/specs/ [accessed 30 august 2026]