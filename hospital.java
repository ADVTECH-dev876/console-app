import java.util.*;

class Patient {
    int id;
    String name;
    int age;
    String disease;
    String doctorAssigned;
    boolean admitted;

    public Patient(int id, String name, int age, String disease, String doctorAssigned) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.doctorAssigned = doctorAssigned;
        this.admitted = true; // by default admitted when registered
    }
}

public class HospitalApp {
    static Scanner sc = new Scanner(System.in);
    static List<Patient> patients = new ArrayList<>();
    static int patientCounter = 1;

    // Predefined doctors
    static String[] doctors = {"Dr. Smith (Cardiologist)", "Dr. Johnson (Neurologist)", 
                               "Dr. Brown (General)", "Dr. Taylor (Orthopedic)"};

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
            System.out.println("1. Admit Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Discharge Patient");
            System.out.println("4. Search Patient by ID");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> admitPatient();
                case 2 -> viewPatients();
                case 3 -> dischargePatient();
                case 4 -> searchPatient();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("❌ Invalid choice!");
            }
        } while (choice != 5);
    }

    static void admitPatient() {
        sc.nextLine(); // consume newline
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Disease/Problem: ");
        String disease = sc.nextLine();

        // Assign a random doctor
        String doctor = doctors[new Random().nextInt(doctors.length)];

        patients.add(new Patient(patientCounter++, name, age, disease, doctor));
        System.out.println("✅ Patient admitted successfully and assigned to " + doctor);
    }

    static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients admitted yet.");
            return;
        }
        System.out.println("\n--- Patient List ---");
        for (Patient p : patients) {
            System.out.println("ID: " + p.id + " | Name: " + p.name + " | Age: " + p.age +
                               " | Disease: " + p.disease + " | Doctor: " + p.doctorAssigned +
                               " | Status: " + (p.admitted ? "Admitted" : "Discharged"));
        }
    }

    static void dischargePatient() {
        System.out.print("Enter Patient ID to discharge: ");
        int id = sc.nextInt();

        for (Patient p : patients) {
            if (p.id == id && p.admitted) {
                p.admitted = false;
                System.out.println("✅ Patient " + p.name + " discharged successfully.");
                return;
            }
        }
        System.out.println("❌ Patient not found or already discharged.");
    }

    static void searchPatient() {
        System.out.print("Enter Patient ID to search: ");
        int id = sc.nextInt();

        for (Patient p : patients) {
            if (p.id == id) {
                System.out.println("🔎 Patient Found: " + p.name + " | Age: " + p.age +
                                   " | Disease: " + p.disease + " | Doctor: " + p.doctorAssigned +
                                   " | Status: " + (p.admitted ? "Admitted" : "Discharged"));
                return;
            }
        }
        System.out.println("❌ Patient not found.");
    }
}
