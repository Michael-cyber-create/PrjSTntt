/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjstntt;

import com.mycompany.prjstntt.Bed;
import com.mycompany.prjstntt.BedManager;
import com.mycompany.prjstntt.Inpatient;
import com.mycompany.prjstntt.InputValidator;
import com.mycompany.prjstntt.PatientCategory;
import com.mycompany.prjstntt.PatientManager;
import com.mycompany.prjstntt.ReportManager;
import com.mycompany.prjstntt.patient;
import java.util.Scanner;

public class HospitalSystem {

    private Scanner scanner;
    private InputValidator validator;
    private PatientManager patientManager;
    private BedManager bedManager;
    private ReportManager reportManager;

    public HospitalSystem() {

        scanner = new Scanner(System.in);

        validator =
                new InputValidator(scanner);

        patientManager =
                new PatientManager();

        bedManager =
                new BedManager();

        reportManager =
                new ReportManager(
                        patientManager,
                        bedManager);
    }

    public void start() {

        boolean running = true;

        while (running) {

            displayMainMenu();

            int choice =
                    validator.getMenuChoice(1, 8);

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;

                case 5:
                    displayPatients();
                    break;

                case 6:
                    bedManagementMenu();
                    break;

                case 7:
                    reportsMenu();
                    break;

                case 8:
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid menu choice.");
            }
        }

        scanner.close();

        System.out.println();
        System.out.println(
                "Thank you for using MediCare Hospital "
                + "Admission System.");
    }

    private void displayMainMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       MEDICARE HOSPITAL ADMISSION SYSTEM");
        System.out.println("==============================================");

        System.out.println("PATIENT MANAGEMENT");
        System.out.println("----------------------------------------------");
        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println("5. Display All Patients");

        System.out.println();
        System.out.println("BED MANAGEMENT");
        System.out.println("----------------------------------------------");
        System.out.println("6. Bed Management");

        System.out.println();
        System.out.println("REPORTS");
        System.out.println("----------------------------------------------");
        System.out.println("7. Reports");

        System.out.println();
        System.out.println("8. Exit");

        System.out.println("==============================================");
    }

    private void registerPatient() {

        System.out.println();
        System.out.println("========== REGISTER PATIENT ==========");

        String id =
                validator.getPatientID();

        if (patientManager.findPatient(id) != null) {

            System.out.println(
                    "ERROR: Patient ID already exists.");

            return;
        }

        String firstName =
                validator.getName("First Name: ");

        String lastName =
                validator.getName("Last Name: ");

        int age =
                validator.getAge();

        String gender =
                validator.getGender();

        String condition =
                validator.getRequiredString(
                        "Medical Condition: ");

        PatientCategory category =
                validator.getCategory();

        patient p;

        if (category == PatientCategory.INPATIENT) {

            p =
                    new Inpatient(
                            id,
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition,
                            category,
                            "Ward 1",
                            "Not Allocated");

        } else {

            p =
                    new patient(
                            id,
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition,
                            category);
        }

        if (patientManager.registerPatient(p)) {

            System.out.println();
            System.out.println(
                    "Patient successfully registered.");

        } else {

            System.out.println();
            System.out.println(
                    "ERROR: Patient registration failed.");
        }
    }

    private void searchPatient() {

        System.out.println();
        System.out.println("========== SEARCH PATIENT ==========");

        String id =
                validator.getPatientID();

        patient p =
                patientManager.findPatient(id);

        if (p == null) {

            System.out.println(
                    "Patient not found.");

        } else {

            p.displayDetails();
        }
    }

    private void updatePatient() {

        System.out.println();
        System.out.println("========== UPDATE PATIENT ==========");

        String id =
                validator.getPatientID();

        patient p =
                patientManager.findPatient(id);

        if (p == null) {

            System.out.println(
                    "Patient not found.");

            return;
        }

        System.out.println(
                "Enter the patient's new information.");

        String firstName =
                validator.getName("First Name: ");

        String lastName =
                validator.getName("Last Name: ");

        int age =
                validator.getAge();

        String gender =
                validator.getGender();

        String condition =
                validator.getRequiredString(
                        "Medical Condition: ");

        PatientCategory newCategory =
                validator.getCategory();

        PatientCategory oldCategory =
                p.getCategory();

        // Inpatient changed to Outpatient/Emergency
        if (oldCategory == PatientCategory.INPATIENT
                && newCategory != PatientCategory.INPATIENT) {

            if (p instanceof Inpatient) {

                Inpatient inpatient =
                        (Inpatient) p;

                if (!inpatient.getBedNumber()
                        .equals("Not Allocated")) {

                    bedManager.releaseBed(
                            inpatient.getBedNumber());

                    inpatient.setBedNumber(
                            "Not Allocated");
                }
            }
        }

        // Outpatient/Emergency changed to Inpatient
        if (oldCategory != PatientCategory.INPATIENT
                && newCategory == PatientCategory.INPATIENT) {

            int index =
                    patientManager.getPatients()
                            .indexOf(p);

            Inpatient newInpatient =
                    new Inpatient(
                            p.getPatientID(),
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition,
                            newCategory,
                            "Ward 1",
                            "Not Allocated");

            patientManager.getPatients()
                    .set(index, newInpatient);

            System.out.println(
                    "Patient successfully updated.");

            return;
        }

        boolean updated =
                patientManager.updatePatient(
                        id,
                        firstName,
                        lastName,
                        age,
                        gender,
                        condition,
                        newCategory);

        if (updated) {

            System.out.println(
                    "Patient successfully updated.");

        } else {

            System.out.println(
                    "ERROR: Patient could not be updated.");
        }
    }

    private void deletePatient() {

        System.out.println();
        System.out.println("========== DELETE PATIENT ==========");

        String id =
                validator.getPatientID();

        patient p =
                patientManager.findPatient(id);

        if (p == null) {

            System.out.println(
                    "Patient not found.");

            return;
        }

        p.displayDetails();

        boolean confirmed =
                validator.getConfirmation(
                        "Are you sure you want to delete "
                        + "this patient?");

        if (!confirmed) {

            System.out.println(
                    "Delete operation cancelled.");

            return;
        }

        if (p instanceof Inpatient) {

            Inpatient inpatient =
                    (Inpatient) p;

            if (!inpatient.getBedNumber()
                    .equals("Not Allocated")) {

                bedManager.releaseBed(
                        inpatient.getBedNumber());
            }
        }

        if (patientManager.deletePatient(id)) {

            System.out.println(
                    "Patient successfully deleted.");

        } else {

            System.out.println(
                    "ERROR: Patient could not be deleted.");
        }
    }

    private void displayPatients() {

        patientManager.displayAllPatients();
    }

    private void bedManagementMenu() {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("==============================================");
            System.out.println("              BED MANAGEMENT");
            System.out.println("==============================================");

            System.out.println("1. Allocate Bed");
            System.out.println("2. Release Bed");
            System.out.println("3. Display Ward Layout");
            System.out.println("4. Display Available Beds");
            System.out.println("5. Display Occupied Beds");
            System.out.println("6. Return to Main Menu");

            System.out.println("==============================================");

            int choice =
                    validator.getMenuChoice(1, 6);

            switch (choice) {

                case 1:
                    allocateBed();
                    break;

                case 2:
                    releaseBed();
                    break;

                case 3:
                    bedManager.displayWardLayout();
                    break;

                case 4:
                    bedManager.displayAvailableBeds();
                    break;

                case 5:
                    bedManager.displayOccupiedBeds();
                    break;

                case 6:
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice.");
            }
        }
    }

    private void allocateBed() {

        System.out.println();
        System.out.println("========== ALLOCATE BED ==========");

        String patientID =
                validator.getPatientID();

        patient p =
                patientManager.findPatient(patientID);

        if (p == null) {

            System.out.println(
                    "ERROR: Patient not found.");

            return;
        }

        if (p.getCategory()
                != PatientCategory.INPATIENT) {

            System.out.println(
                    "ERROR: Only Inpatients can be "
                    + "allocated a bed.");

            return;
        }

        if (!(p instanceof Inpatient)) {

            System.out.println(
                    "ERROR: Invalid inpatient record.");

            return;
        }

        Inpatient inpatient =
                (Inpatient) p;

        if (!inpatient.getBedNumber()
                .equals("Not Allocated")) {

            System.out.println(
                    "ERROR: Patient already has bed "
                    + inpatient.getBedNumber() + ".");

            return;
        }

        if (bedManager.getAvailableBedCount() == 0) {

            System.out.println(
                    "ERROR: Ward is full. "
                    + "No beds are available.");

            return;
        }

        bedManager.displayAvailableBeds();

        String bedNumber =
                validator.getBedNumber();

        if (!bedManager.isValidBed(bedNumber)) {

            System.out.println(
                    "ERROR: Invalid bed number.");

            return;
        }

        if (bedManager.isBedOccupied(bedNumber)) {

            System.out.println(
                    "ERROR: Bed is already occupied.");

            return;
        }

        if (bedManager.allocateBed(
                bedNumber,
                patientID)) {

            inpatient.setBedNumber(bedNumber);

            System.out.println(
                    "Bed " + bedNumber
                    + " successfully allocated to patient "
                    + patientID + ".");

        } else {

            System.out.println(
                    "ERROR: Bed allocation failed.");
        }
    }

    private void releaseBed() {

        System.out.println();
        System.out.println("========== RELEASE BED ==========");

        String bedNumber =
                validator.getBedNumber();

        Bed bed =
                bedManager.getWard()
                        .findBed(bedNumber);

        if (bed == null) {

            System.out.println(
                    "ERROR: Invalid bed number.");

            return;
        }

        if (!bed.isOccupied()) {

            System.out.println(
                    "ERROR: Bed is already available.");

            return;
        }

        String patientID =
                bed.getPatientID();

        if (bedManager.releaseBed(bedNumber)) {

            patient p =
                    patientManager.findPatient(patientID);

            if (p instanceof Inpatient) {

                Inpatient inpatient =
                        (Inpatient) p;

                inpatient.setBedNumber(
                        "Not Allocated");
            }

            System.out.println(
                    "Bed " + bedNumber
                    + " successfully released.");

        } else {

            System.out.println(
                    "ERROR: Bed could not be released.");
        }
    }

    private void reportsMenu() {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("==============================================");
            System.out.println("                  REPORTS");
            System.out.println("==============================================");

            System.out.println("1. All Patients");
            System.out.println("2. Available Beds");
            System.out.println("3. Occupied Beds");
            System.out.println("4. Total Registered Patients");
            System.out.println("5. Total Occupied Beds");
            System.out.println("6. Ward Occupancy Percentage");
            System.out.println("7. Sort Patients by Patient ID");
            System.out.println("8. Sort Patients by Surname");
            System.out.println("9. Return to Main Menu");

            System.out.println("==============================================");

            int choice =
                    validator.getMenuChoice(1, 9);

            switch (choice) {

                case 1:
                    reportManager
                            .displayAllPatientsReport();
                    break;

                case 2:
                    reportManager
                            .displayAvailableBedsReport();
                    break;

                case 3:
                    reportManager
                            .displayOccupiedBedsReport();
                    break;

                case 4:
                    reportManager
                            .displayTotalPatientsReport();
                    break;

                case 5:
                    reportManager
                            .displayTotalOccupiedBedsReport();
                    break;

                case 6:
                    reportManager
                            .displayOccupancyReport();
                    break;

                case 7:
                    reportManager
                            .sortByPatientIDReport();
                    break;

                case 8:
                    reportManager
                            .sortBySurnameReport();
                    break;

                case 9:
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid choice.");
            }
        }
    }

    public PatientManager getPatientManager() {

        return patientManager;
    }

    public BedManager getBedManager() {

        return bedManager;
    }

    public ReportManager getReportManager() {

        return reportManager;
    }
}