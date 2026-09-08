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
public class PatientManager {

    private ArrayList<patient> patients;

    public PatientManager() {

        patients = new ArrayList<>();
    }

    public boolean registerPatient(patient patient) {

        if (patient == null) {
            return false;
        }

        if (patient.getPatientID() == null
                || patient.getPatientID().trim().isEmpty()) {

            return false;
        }

        if (findPatient(patient.getPatientID()) != null) {
            return false;
        }

        patients.add(patient);

        return true;
    }

    public patient findPatient(String patientID) {

        if (patientID == null
                || patientID.trim().isEmpty()) {

            return null;
        }

        for (patient p : patients) {

            if (p.getPatientID()
                    .equalsIgnoreCase(patientID.trim())) {

                return p;
            }
        }

        return null;
    }

    public boolean updatePatient(
            String patientID,
            String firstName,
            String lastName,
            int age,
            String gender,
            String medicalCondition,
            PatientCategory category) {

        patient p = findPatient(patientID);

        if (p == null) {
            return false;
        }

        if (firstName == null
                || firstName.trim().isEmpty()
                || lastName == null
                || lastName.trim().isEmpty()
                || gender == null
                || gender.trim().isEmpty()
                || medicalCondition == null
                || medicalCondition.trim().isEmpty()
                || category == null
                || age < 0
                || age > 120) {

            return false;
        }

        p.setFirstName(firstName.trim());
        p.setLastName(lastName.trim());
        p.setAge(age);
        p.setGender(gender.trim());
        p.setMedicalCondition(
                medicalCondition.trim());
        p.setCategory(category);

        return true;
    }

    public boolean deletePatient(String patientID) {

        patient p = findPatient(patientID);

        if (p == null) {
            return false;
        }

        return patients.remove(p);
    }

    public ArrayList<patient> getPatients() {

        return patients;
    }

    public int getPatientCount() {

        return patients.size();
    }

    public void displayAllPatients() {

        if (patients.isEmpty()) {

            System.out.println(
                    "No patients are registered.");

            return;
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              ALL PATIENTS");
        System.out.println("==============================================");

        for (patient p : patients) {

            p.displayDetails();
        }
    }
}
