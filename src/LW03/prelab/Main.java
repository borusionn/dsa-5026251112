package LW03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Masalah Playlist
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (scanner.hasNext()) {
            String operation = scanner.next();
            if (operation.equals("ADD")) {
                String song = scanner.next();
                playlist.add(song);

            } else if (operation.equals("INSERT")) {
                int index = scanner.nextInt();
                String song = scanner.next();
                playlist.add(index, song);

            } else if (operation.equals("REMOVE")) {
                String song = scanner.next();
                playlist.remove(song);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs : " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        
        scanner.close();

        //Masalah Peserta Workshop
        Set<String> participants = new LinkedHashSet<>();

        int duplicate = 0;

        scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        
        while (scanner.hasNext()) {
            String name = scanner.next();
            if (participants.contains(name)) {
                duplicate++;
            } else {
                participants.add(name);
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants : " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations : " + duplicate);

        scanner.close();

        //Masalah Inventori Produk

        Map<String, Integer> inventory = new HashMap<>();
        List<String> productOrder = new ArrayList<>();

        int failedSales = 0;

        scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (scanner.hasNext()) {
            String type = scanner.next();
            String product = scanner.next();
            int quantity = scanner.nextInt();

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int stock = inventory.get(product);
                    inventory.put(product, stock + quantity);
                } else{
                    inventory.put(product, quantity);
                    productOrder.add(product);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)) {
                    int stock = inventory.get(product);
                    if (stock >= quantity) {
                        inventory.put(product, stock - quantity);

                    } else {
                        failedSales++;
                    }
                } else {
                    failedSales++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (String product : productOrder) {
            System.out.println(product + " : " + inventory.get(product));
        }

        System.out.println("Failed sales : " + failedSales);

        scanner.close();

    }
    
}