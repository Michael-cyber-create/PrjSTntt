/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjstntt;

/**
 *
 * @author emeris
 */
public class BedManager {

    private Ward ward;

    public BedManager() {

        ward = new Ward();
    }

    public Ward getWard() {

        return ward;
    }

    public boolean allocateBed(
            String bedNumber,
            String patientID) {

        if (bedNumber == null
                || bedNumber.trim().isEmpty()
                || patientID == null
                || patientID.trim().isEmpty()) {

            return false;
        }

        if (!ward.hasAvailableBed()) {
            return false;
        }

        Bed bed = ward.findBed(bedNumber);

        if (bed == null || bed.isOccupied()) {
            return false;
        }

        return ward.allocateBed(
                bedNumber,
                patientID);
    }

    public boolean releaseBed(String bedNumber) {

        return ward.releaseBed(bedNumber);
    }

    public boolean isBedOccupied(String bedNumber) {

        Bed bed = ward.findBed(bedNumber);

        return bed != null && bed.isOccupied();
    }

    public boolean isValidBed(String bedNumber) {

        return ward.findBed(bedNumber) != null;
    }

    public String getPatientInBed(String bedNumber) {

        Bed bed = ward.findBed(bedNumber);

        if (bed == null) {
            return null;
        }

        return bed.getPatientID();
    }

    public int getOccupiedBedCount() {

        return ward.getOccupiedBedCount();
    }

    public int getAvailableBedCount() {

        return ward.getAvailableBedCount();
    }

    public int getTotalBeds() {

        return ward.getTotalBeds();
    }

    public void displayWardLayout() {

        ward.displayLayout();
    }

    public void displayAvailableBeds() {

        ward.displayAvailableBeds();
    }

    public void displayOccupiedBeds() {

        ward.displayOccupiedBeds();
    }
}