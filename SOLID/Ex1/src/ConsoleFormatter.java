import java.util.List;

public class ConsoleFormatter {
    public static void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public static void printValidationErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }

    public static void printSuccess(String id, int totalStudents, StudentRecord record) {
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + totalStudents);
        System.out.println("CONFIRMATION:");
        System.out.println(record);
    }
}
