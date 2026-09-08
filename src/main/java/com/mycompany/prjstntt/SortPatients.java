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
public class SortPatients {

    public static void sortByPatientID(
            ArrayList<patient> patients) {

        if (patients == null) {
            return;
        }

        // Bubble Sort - ascending Patient ID
        for (int pass = 0;
             pass < patients.size() - 1;
             pass++) {

            boolean swapped = false;

            for (int i = 0;
                 i < patients.size() - 1 - pass;
                 i++) {

                String current =
                        patients.get(i)
                                .getPatientID();

                String next =
                        patients.get(i + 1)
                                .getPatientID();

                if (current.compareToIgnoreCase(next) > 0) {

                    patient temporary =
                            patients.get(i);

                    patients.set(
                            i,
                            patients.get(i + 1));

                    patients.set(
                            i + 1,
                            temporary);

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static void sortBySurname(
            ArrayList<patient> patients) {

        if (patients == null) {
            return;
        }

        // Bubble Sort - ascending surname
        for (int pass = 0;
             pass < patients.size() - 1;
             pass++) {

            boolean swapped = false;

            for (int i = 0;
                 i < patients.size() - 1 - pass;
                 i++) {

                String current =
                        patients.get(i)
                                .getLastName();

                String next =
                        patients.get(i + 1)
                                .getLastName();

                if (current.compareToIgnoreCase(next) > 0) {

                    patient temporary =
                            patients.get(i);

                    patients.set(
                            i,
                            patients.get(i + 1));

                    patients.set(
                            i + 1,
                            temporary);

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
