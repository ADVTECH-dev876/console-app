import java.util.*;

class Student {
    int id;
    String name;
    int totalClasses;
    int attendedClasses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.totalClasses = 0;
        this.attendedClasses = 0;
    }

    public double getAttendancePercentage() {
        if (totalClasses == 0) return 0.0;
        return (attendedClasses * 100.0) / totalClasses;
    }
}

public class StudentAttendanceApp {
    static Scanner sc = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();
    static int studentCounter = 1;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== STUDENT ATTENDANCE MANAGEMENT ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Mark Attendance");
            System.out.println("4. Show Attendance Report");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> markAttendance();
                case 4 -> showAttendanceReport();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("❌ Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    static void addStudent() {
        sc.nextLine(); // consume newline
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        students.add(new Student(studentCounter++, name));
        System.out.println("✅ Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println("ID: " + s.id + " | Name: " + s.name);
        }
    }

    static void markAttendance() {
        if (students.isEmpty()) {
            System.out.println("No students to mark attendance.");
            return;
        }

        System.out.println("\nMarking attendance (1 = Present, 0 = Absent):");
        for (Student s : students) {
            System.out.print("Student " + s.id + " (" + s.name + "): ");
            int status = sc.nextInt();
            s.totalClasses++;
            if (status == 1) s.attendedClasses++;
        }
        System.out.println("✅ Attendance marked successfully!");
    }

    static void showAttendanceReport() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n--- Attendance Report ---");
        for (Student s : students) {
            System.out.printf("ID: %d | Name: %s | Attendance: %d/%d (%.2f%%)\n",
                    s.id, s.name, s.attendedClasses, s.totalClasses, s.getAttendancePercentage());
        }
    }
}
