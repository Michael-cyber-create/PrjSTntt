/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjstntt;

/**
 *
 * @author emeris
 *///(Oracle, 2024).
public class Bed {

    private String bedNumber;
    private boolean occupied;
    private String patientID;

    public Bed(String bedNumber) {

        this.bedNumber = bedNumber;
        this.occupied = false;
        this.patientID = "";
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public String getPatientID() {
        return patientID;
    }

    public void occupy(String patientID) {

        occupied = true;
        this.patientID = patientID;
    }

    public void release() {

        occupied = false;
        patientID = "";
    }
}   

//Oracle. (2024). The Java Language Specification.https://docs.oracle.com/javase/specs/ [accessed 30 augest 2026]