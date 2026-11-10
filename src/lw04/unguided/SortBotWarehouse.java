package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SortBotWarehouse {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/lw04/unguided/parcels.txt"));
            
            
            int n = scanner.nextInt();
            int[] parcels = new int[n];
            for (int i = 0; i < n; i++) {
                parcels[i] = scanner.nextInt();
            }
            
            
            int m = scanner.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = scanner.nextInt();
            }
            scanner.close();
            
           
            int[] after2Passes = new int[n];
            
            
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (parcels[j] < parcels[j + 1]) {
                        
                        int temp = parcels[j];
                        parcels[j] = parcels[j + 1];
                        parcels[j + 1] = temp;
                    }
                }
                
                
                if (i == 1) {
                    for (int k = 0; k < n; k++) {
                        after2Passes[k] = parcels[k];
                    }
                }
            }
            
            
            System.out.println("==== After 2 Passes ====");
            for (int i = 0; i < n; i++) {
                System.out.print(after2Passes[i] + (i == n - 1 ? "" : " "));
            }
            System.out.println();
            
            
            System.out.println("==== Fully Sorted ====");
            for (int i = 0; i < n; i++) {
                System.out.print(parcels[i] + (i == n - 1 ? "" : " "));
            }
            System.out.println();
            
            
            System.out.println("==== Search Results ====");
            for (int i = 0; i < m; i++) {
                int target = targets[i];
                int low = 0;
                int high = n - 1;
                int steps = 0;
                boolean isFound = false;
                int foundIndex = -1;
                
                while (low <= high) {
                    steps++;
                    int mid = (low + high) / 2; 
                    
                    if (parcels[mid] == target) {
                        isFound = true;
                        foundIndex = mid;
                        break;
                    } else if (target > parcels[mid]) {
                        
                        high = mid - 1;
                    } else {
                        
                        low = mid + 1;
                    }
                }
                
                if (isFound) {
                    System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
                } else {
                    System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File parcels.txt tidak ditemukan. Pastikan file berada di direktori yang tepat.");
        }
    }
}