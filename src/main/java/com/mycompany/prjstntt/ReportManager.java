/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjstntt;

import java.util.ArrayList;

/**
 *
 * @author emeris
 */
public class ReportManager {

    private PatientManager patientManager;
    private BedManager bedManager;

    public ReportManager(
            PatientManager patientManager,
            BedManager bedManager) {

        this.patientManager = patientManager;
        this.bedManager = bedManager;
    }

    public void displayAllPatientsReport() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("             PATIENT REPORT");
        System.out.println("----------------------------------------------");

        patientManager.displayAllPatients();

        System.out.println();
        System.out.println(
                "Total registered patients: "
                + patientManager.getPatientCount());
    }

    public void displayAvailableBedsReport() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("          AVAILABLE BEDS REPORT");
        System.out.println("----------------------------------------------");

        bedManager.displayAvailableBeds();

        System.out.println(
                "Available beds: "
                + bedManager.getAvailableBedCount());
    }

    public void displayOccupiedBedsReport() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("           OCCUPIED BEDS REPORT");
        System.out.println("----------------------------------------------");

        bedManager.displayOccupiedBeds();

        System.out.println(
                "Occupied beds: "
                + bedManager.getOccupiedBedCount());
    }

    public void displayTotalPatientsReport() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("          TOTAL PATIENTS REPORT");
        System.out.println("----------------------------------------------");

        System.out.println(
                "Total registered patients: "
                + patientManager.getPatientCount());
    }

    public void displayTotalOccupiedBedsReport() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("        TOTAL OCCUPIED BEDS REPORT");
        System.out.println("----------------------------------------------");

        System.out.println(
                "Total occupied beds: "
                + bedManager.getOccupiedBedCount());
    }

    public double calculateOccupancyPercentage() {

        if (bedManager.getTotalBeds() == 0) {
            return 0.0;
        }

        return (bedManager.getOccupiedBedCount()
                * 100.0)
                / bedManager.getTotalBeds();
    }

    public void displayOccupancyReport() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("          WARD OCCUPANCY REPORT");
        System.out.println("----------------------------------------------");

        System.out.println(
                "Total beds     : "
                + bedManager.getTotalBeds());

        System.out.println(
                "Occupied beds  : "
                + bedManager.getOccupiedBedCount());

        System.out.println(
                "Available beds : "
                + bedManager.getAvailableBedCount());

        System.out.printf(
                "Occupancy      : %.2f%%%n",
                calculateOccupancyPercentage());
    }

    public void sortByPatientIDReport() {

        ArrayList<patient> patients =
                patientManager.getPatients();

        if (patients.isEmpty()) {

            System.out.println(
                    "No patients are registered.");

            return;
        }

        SortPatients.sortByPatientID(patients);

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("        PATIENTS SORTED BY PATIENT ID");
        System.out.println("             ASCENDING ORDER");
        System.out.println("----------------------------------------------");

        for (patient p : patients) {

            p.displayDetails();
        }
    }

    public void sortBySurnameReport() {

        ArrayList<patient> patients =
                patientManager.getPatients();

        if (patients.isEmpty()) {

            System.out.println(
                    "No patients are registered.");

            return;
        }

        SortPatients.sortBySurname(patients);

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("          PATIENTS SORTED BY SURNAME");
        System.out.println("             ASCENDING ORDER");
        System.out.println("----------------------------------------------");

        for (patient p : patients) {

            p.displayDetails();
        }
    }
}
