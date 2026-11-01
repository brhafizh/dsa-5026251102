package lw04.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Membaca input dari branches.txt menggunakan Scanner[cite: 3]
            Scanner scanner = new Scanner(new File("branches.txt"));

            // Baris 1: Jumlah cabang (N)[cite: 3]
            int n = scanner.nextInt();
            int[] branches = new int[n]; // Menyimpan nilai stabilitas dalam int[][cite: 3]

            // Baris 2: Menyimpan nilai stabilitas sesuai urutan aslinya[cite: 3]
            for (int i = 0; i < n; i++) {
                branches[i] = scanner.nextInt();
            }

            // Baris 3: Jumlah target stabilitas (M)[cite: 3]
            int m = scanner.nextInt();
            int[] targets = new int[m];

            // Baris 4: Menyimpan nilai target[cite: 3]
            for (int i = 0; i < m; i++) {
                targets[i] = scanner.nextInt();
            }
            scanner.close();

            // INSERTION SORT DESCENDING (Tepat 3 Passes)[cite: 2, 3]
            // Satu pass adalah satu iterasi dari outer loop[cite: 1]
            for (int i = 1; i <= 3; i++) {
                int key = branches[i]; // Menyimpan elemen yang akan disisipkan[cite: 2]
                int j = i - 1;

                // Menggeser setiap elemen di sebelah kiri yang lebih kecil dari key 
                // satu posisi ke kanan[cite: 2]
                while (j >= 0 && branches[j] < key) {
                    branches[j + 1] = branches[j];
                    j--;
                }
                // Meletakkan key di posisi yang kosong[cite: 2]
                branches[j + 1] = key;
            }

            // Menampilkan array setelah tepat 3 pass[cite: 3]
            for (int i = 0; i < n; i++) {
                System.out.print(branches[i] + (i == n - 1 ? "" : " "));
            }
            System.out.println();

            // LINEAR SEARCH[cite: 1, 3]
            for (int i = 0; i < m; i++) {
                int target = targets[i];
                boolean found = false;
                
                // Memeriksa elemen satu per satu dari index 0[cite: 1, 3]
                for (int j = 0; j < n; j++) {
                    if (branches[j] == target) {
                        System.out.println(target + ": FOUND at index " + j); // Melaporkan FOUND diikuti dengan indeksnya[cite: 3]
                        found = true;
                        break;
                    }
                }
                
                // Jika target tidak ditemukan di dalam array[cite: 3]
                if (!found) {
                    System.out.println(target + ": NOT FOUND"); // Melaporkan NOT FOUND[cite: 3]
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File branches.txt tidak ditemukan.");
        }
    }
}
