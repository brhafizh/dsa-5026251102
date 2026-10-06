package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();
        int rejectedAttempts = 0;

        File regFile = new File("src/lw03/unguided/registrations.txt");
        
        try {
            Scanner regScanner = new Scanner(regFile);
            while (regScanner.hasNextLine()) {
                String id = regScanner.nextLine().trim();
                if (!id.isEmpty()) {
                    registeredStudents.add(id); 
                }
            }
            regScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File registrations.txt tidak ditemukan.");
            System.out.println("Lokasi pencarian Java: " + regFile.getAbsolutePath());
            return;
        }

        System.out.println("===== Event Check-In Results");

        File checkinFile = new File("src/lw03/unguided/checkins.txt");

        try {
            Scanner checkinScanner = new Scanner(checkinFile);
            
            while (checkinScanner.hasNextLine()) {
                String id = checkinScanner.nextLine().trim();
                if (id.isEmpty()) continue;

                if (registeredStudents.contains(id)) {
                    if (checkedInStudents.contains(id)) {
                        System.out.println(id + ": Rejected (already checked in)");
                        rejectedAttempts++; 
                    } else {
                        System.out.println(id + ": Checked in");
                        checkedInStudents.add(id);
                    }
                } else {
                    System.out.println(id + ": Rejected (not registered)");
                    rejectedAttempts++; 
                }
            }
            checkinScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File checkins.txt tidak ditemukan.");
            System.out.println("Lokasi pencarian Java: " + checkinFile.getAbsolutePath());
            return;
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        
        int absentStudents = registeredStudents.size() - checkedInStudents.size();
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}