/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.prjstntt.BedManager;
import com.mycompany.prjstntt.Inpatient;
import com.mycompany.prjstntt.PatientCategory;
import com.mycompany.prjstntt.PatientManager;
import com.mycompany.prjstntt.ReportManager;
import com.mycompany.prjstntt.SortPatients;
import com.mycompany.prjstntt.patient;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author emeris
 */

public class HospitalSystem {

    @Test
    public void testRegisterPatient() {

        PatientManager manager = new PatientManager();

        patient p = new patient(
                "P001",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT);

        boolean expected = true;
        boolean actual = manager.registerPatient(p);

        assertEquals(expected, actual,
                "Patient should be registered successfully");
    }

    @Test
    public void testSearchPatient() {

        PatientManager manager = new PatientManager();

        patient p = new patient(
                "P002",
                "Sarah",
                "Jones",
                30,
                "Female",
                "Fever",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(p);

        patient expected = p;
        patient actual = manager.findPatient("P002");

        assertEquals(expected, actual,
                "Correct patient should be found");
    }

    @Test
    public void testSearchMissingPatient() {

        PatientManager manager = new PatientManager();

        patient expected = null;
        patient actual = manager.findPatient("P999");

        assertEquals(expected, actual,
                "Missing patient should return null");
    }

    @Test
    public void testUpdatePatient() {

        PatientManager manager = new PatientManager();

        patient p = new patient(
                "P003",
                "Mike",
                "Brown",
                20,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(p);

        boolean expected = true;
        boolean actual = manager.updatePatient(
                "P003",
                "Michael",
                "Brown",
                21,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT);

        assertEquals(expected, actual,
                "Patient should be updated successfully");

        String expectedName = "Michael";
        String actualName =
                manager.findPatient("P003").getFirstName();

        assertEquals(expectedName, actualName,
                "Updated first name should match");
    }

    @Test
    public void testDeletePatient() {

        PatientManager manager = new PatientManager();

        patient p = new patient(
                "P004",
                "David",
                "White",
                40,
                "Male",
                "Headache",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(p);

        boolean expected = true;
        boolean actual = manager.deletePatient("P004");

        assertEquals(expected, actual,
                "Patient should be deleted successfully");
    }

    @Test
    public void testDuplicatePatientID() {

        PatientManager manager = new PatientManager();

        patient firstPatient = new patient(
                "P005",
                "James",
                "Green",
                35,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT);

        patient secondPatient = new patient(
                "P005",
                "Peter",
                "Black",
                28,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(firstPatient);

        boolean expected = false;
        boolean actual =
                manager.registerPatient(secondPatient);

        assertEquals(expected, actual,
                "Duplicate patient ID should be rejected");
    }

    @Test
    public void testPatientCount() {

        PatientManager manager = new PatientManager();

        patient p = new patient(
                "P006",
                "Anna",
                "Taylor",
                22,
                "Female",
                "Flu",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(p);

        int expected = 1;
        int actual = manager.getPatientCount();

        assertEquals(expected, actual,
                "Patient count should be one");
    }

    @Test
    public void testAllocateBed() {

        BedManager manager = new BedManager();

        boolean expected = true;
        boolean actual =
                manager.allocateBed("B01", "P007");

        assertEquals(expected, actual,
                "Bed should be allocated successfully");

        boolean expectedOccupied = true;
        boolean actualOccupied =
                manager.isBedOccupied("B01");

        assertEquals(expectedOccupied, actualOccupied,
                "Allocated bed should be occupied");
    }

    @Test
    public void testAllocateOccupiedBed() {

        BedManager manager = new BedManager();

        manager.allocateBed("B02", "P008");

        boolean expected = false;
        boolean actual =
                manager.allocateBed("B02", "P009");

        assertEquals(expected, actual,
                "An occupied bed should not be allocated again");
    }

    @Test
    public void testReleaseBed() {

        BedManager manager = new BedManager();

        manager.allocateBed("B03", "P010");

        boolean expected = true;
        boolean actual =
                manager.releaseBed("B03");

        assertEquals(expected, actual,
                "Occupied bed should be released");

        boolean expectedAvailable = false;
        boolean actualAvailable =
                manager.isBedOccupied("B03");

        assertEquals(expectedAvailable, actualAvailable,
                "Released bed should no longer be occupied");
    }

    @Test
    public void testReleaseAvailableBed() {

        BedManager manager = new BedManager();

        boolean expected = false;
        boolean actual =
                manager.releaseBed("B04");

        assertEquals(expected, actual,
                "An available bed should not be released");
    }

    @Test
    public void testInvalidBedNumber() {

        BedManager manager = new BedManager();

        boolean expected = false;
        boolean actual =
                manager.isValidBed("B99");

        assertEquals(expected, actual,
                "Invalid bed number should be rejected");
    }

    @Test
    public void testTotalBeds() {

        BedManager manager = new BedManager();

        int expected = 20;
        int actual = manager.getTotalBeds();

        assertEquals(expected, actual,
                "Ward should contain exactly 20 beds");
    }

    @Test
    public void testAvailableBedsInitially() {

        BedManager manager = new BedManager();

        int expected = 20;
        int actual = manager.getAvailableBedCount();

        assertEquals(expected, actual,
                "All 20 beds should initially be available");
    }

    @Test
    public void testOccupiedBedCount() {

        BedManager manager = new BedManager();

        manager.allocateBed("B05", "P011");
        manager.allocateBed("B06", "P012");

        int expected = 2;
        int actual = manager.getOccupiedBedCount();

        assertEquals(expected, actual,
                "Occupied bed count should be two");
    }

    @Test
    public void testFullWard() {

        BedManager manager = new BedManager();

        for (int i = 1; i <= 20; i++) {

            String bedNumber =
                    String.format("B%02d", i);

            String patientID =
                    "P" + i;

            manager.allocateBed(
                    bedNumber,
                    patientID);
        }

        boolean expected = false;
        boolean actual =
                manager.allocateBed("B01", "P021");

        assertEquals(expected, actual,
                "A bed should not be allocated when the ward is full");
    }

    @Test
    public void testSortByPatientID() {

        PatientManager manager = new PatientManager();

        patient patientOne = new patient(
                "P003",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT);

        patient patientTwo = new patient(
                "P001",
                "Sarah",
                "Jones",
                30,
                "Female",
                "Fever",
                PatientCategory.OUTPATIENT);

        patient patientThree = new patient(
                "P002",
                "David",
                "Brown",
                40,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(patientOne);
        manager.registerPatient(patientTwo);
        manager.registerPatient(patientThree);

        ArrayList<patient> patients =
                manager.getPatients();

        SortPatients.sortByPatientID(patients);

        String expected = "P001";
        String actual =
                patients.get(0).getPatientID();

        assertEquals(expected, actual,
                "Patients should be sorted by Patient ID");
    }

    @Test
    public void testSortBySurname() {

        PatientManager manager = new PatientManager();

        patient patientOne = new patient(
                "P014",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT);

        patient patientTwo = new patient(
                "P015",
                "Sarah",
                "Anderson",
                30,
                "Female",
                "Fever",
                PatientCategory.OUTPATIENT);

        patient patientThree = new patient(
                "P016",
                "David",
                "Brown",
                40,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT);

        manager.registerPatient(patientOne);
        manager.registerPatient(patientTwo);
        manager.registerPatient(patientThree);

        ArrayList<patient> patients =
                manager.getPatients();

        SortPatients.sortBySurname(patients);

        String expected = "Anderson";
        String actual =
                patients.get(0).getLastName();

        assertEquals(expected, actual,
                "Patients should be sorted by surname");
    }

    @Test
    public void testInpatientInheritance() {

        Inpatient inpatient =
                new Inpatient(
                        "P017",
                        "Tom",
                        "Wilson",
                        45,
                        "Male",
                        "Injury",
                        PatientCategory.INPATIENT,
                        "Ward 1",
                        "B10");

        boolean expected = true;
        boolean actual = inpatient instanceof patient;

        assertEquals(expected, actual,
                "Inpatient should inherit from patient");
    }

    @Test
    public void testInpatientWardNumber() {

        Inpatient inpatient =
                new Inpatient(
                        "P018",
                        "Lisa",
                        "Miller",
                        32,
                        "Female",
                        "Asthma",
                        PatientCategory.INPATIENT,
                        "Ward 1",
                        "B11");

        String expected = "Ward 1";
        String actual = inpatient.getWardNumber();

        assertEquals(expected, actual,
                "Inpatient ward number should match");
    }

    @Test
    public void testPatientCategoryEnum() {

        patient p = new patient(
                "P019",
                "Robert",
                "Davis",
                50,
                "Male",
                "Diabetes",
                PatientCategory.EMERGENCY);

        PatientCategory expected =
                PatientCategory.EMERGENCY;

        PatientCategory actual =
                p.getCategory();

        assertEquals(expected, actual,
                "Patient category should be Emergency");
    }

    @Test
    public void testOccupancyPercentage() {

        BedManager bedManager =
                new BedManager();

        bedManager.allocateBed("B12", "P020");
        bedManager.allocateBed("B13", "P021");

        PatientManager patientManager =
                new PatientManager();

        ReportManager reports =
                new ReportManager(
                        patientManager,
                        bedManager);

        double expected = 10.0;
        double actual =
                reports.calculateOccupancyPercentage();

        assertEquals(expected, actual,
                "Occupancy should be 10 percent");
    }
}  