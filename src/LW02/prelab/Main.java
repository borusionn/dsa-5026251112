import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();

        try {

            File file = new File("transactions.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                transactions.add(data);

                boolean exists = false;
                for (String[] customer : customers) {
                    if (customer[0].equals(data[0])) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    customers.add(
                        new String[]{data[0], "0"}
                    );
                }
            }
        
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
            return;
        }

        while (!transactions.isEmpty()) {
            queue.add(
                transactions.removeFirst()
            );
        }

        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    int balance = Integer.parseInt(customer[1]);


                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {
                            failedTransactions.push(transaction);
                        }

                        else {
                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }

    }
    
}

 