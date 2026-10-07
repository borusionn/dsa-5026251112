import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<String> registeredStudents = new HashSet<>();
        Map<String, String> studentStatus = new HashMap<>();
        List<String> checkinResults = new ArrayList<>();

        int successfulCheckins = 0;
        int rejectedAttempts = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("registrations.txt"));

        while (scanner.hasNextLine()) {
            String studentId = scanner.nextLine();
            registeredStudents.add(studentId);
            studentStatus.put(studentId, "not checked in");
        }

        scanner.close();

        scanner = new Scanner(
            Main.class.getResourceAsStream("checkins.txt"));
        while (scanner.hasNextLine()) {

            String studentId = scanner.nextLine();
            if (!registeredStudents.contains(studentId)) {
                checkinResults.add(studentId + ": Rejected (not registered)");
                rejectedAttempts++;

            } else if (studentStatus.get(studentId).equals("checked in")) {
                checkinResults.add(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;

            } else {
                studentStatus.put(studentId, "checked in");
                checkinResults.add(studentId + ": Checked in");
                successfulCheckins++;
            }
        }

        scanner.close();

        System.out.println("===== Event Check-In Results =====");
        for (String result : checkinResults) {
            System.out.println(result);
        }
        int absentStudents = registeredStudents.size() - successfulCheckins;

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());

        System.out.println("Successful check-ins: " + successfulCheckins);
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}