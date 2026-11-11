package LW04.Unguided;

import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("parcels.txt");

        if (input == null) {
            System.err.println("File parcels.txt tidak ditemukan.");
            return;
        }

        try (Scanner scanner = new Scanner(input)) {
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

            int[] afterTwoPasses = new int[n];

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
                        afterTwoPasses[k] = parcels[k];
                    }
                }
            }

            System.out.println("==== After 2 Passes ====");
            printArray(afterTwoPasses);
            System.out.println();

            System.out.println("==== Fully Sorted ====");
            printArray(parcels);
            System.out.println();

            System.out.println("==== Search Results ====");

            for (int i = 0; i < m; i++) {
                int[] result = binarySearch(parcels, targets[i]);
                int index = result[0];
                int steps = result[1];

                if (index != -1) {
                    System.out.println(targets[i] + ": FOUND at index "
                            + index + " (steps: " + steps + ")");
                } else {
                    System.out.println(targets[i] + ": NOT FOUND (steps: "
                            + steps + ")");
                }
            }
        }
    }

    private static int[] binarySearch(int[] parcels, int target) {
        int low = 0;
        int high = parcels.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (parcels[mid] == target) {
                return new int[] {mid, steps};
            } else if (parcels[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[] {-1, steps};
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }
}
