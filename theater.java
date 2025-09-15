import java.util.*;

class Show {
    int id;
    String name;
    String time;
    double price;
    boolean[][] seats; // true = booked, false = available

    public Show(int id, String name, String time, double price, int rows, int cols) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.price = price;
        this.seats = new boolean[rows][cols];
    }

    public void displaySeats() {
        System.out.println("Seating Chart (X = booked, O = available):");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col) {
        if (row < seats.length && col < seats[0].length && !seats[row][col]) {
            seats[row][col] = true;
            return true;
        }
        return false;
    }

    public int getAvailableSeats() {
        int count = 0;
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) count++;
            }
        }
        return count;
    }
}

public class TheaterApp {
    static Scanner sc = new Scanner(System.in);
    static List<Show> shows = new ArrayList<>();
    static int showCounter = 1;
    static double totalEarnings = 0;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== THEATER TICKET BOOKING SYSTEM ===");
            System.out.println("1. Add Show");
            System.out.println("2. View Shows");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Reports");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addShow();
                case 2 -> viewShows();
                case 3 -> bookTicket();
                case 4 -> viewReports();
                case 5 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    static void addShow() {
        sc.nextLine(); // consume newline
        System.out.print("Enter Show Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Show Time (e.g. 7:30 PM): ");
        String time = sc.nextLine();
        System.out.print("Enter Ticket Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter rows of seats: ");
        int rows = sc.nextInt();
        System.out.print("Enter columns of seats: ");
        int cols = sc.nextInt();

        shows.add(new Show(showCounter++, name, time, price, rows, cols));
        System.out.println("✅ Show added successfully!");
    }

    static void viewShows() {
        if (shows.isEmpty()) {
            System.out.println("No shows available!");
            return;
        }
        for (Show s : shows) {
            System.out.println("ID: " + s.id + " | " + s.name + " | Time: " + s.time +
                    " | Price: " + s.price + " | Available Seats: " + s.getAvailableSeats());
        }
    }

    static void bookTicket() {
        if (shows.isEmpty()) {
            System.out.println("No shows available to book!");
            return;
        }
        viewShows();
        System.out.print("Enter Show ID to book: ");
        int id = sc.nextInt();

        Show selected = null;
        for (Show s : shows) {
            if (s.id == id) selected = s;
        }

        if (selected == null) {
            System.out.println("Invalid Show ID!");
            return;
        }

        selected.displaySeats();
        System.out.print("Enter Row number: ");
        int row = sc.nextInt();
        System.out.print("Enter Column number: ");
        int col = sc.nextInt();

        if (selected.bookSeat(row, col)) {
            System.out.println("🎟 Ticket booked successfully for " + selected.name);
            totalEarnings += selected.price;
        } else {
            System.out.println("❌ Seat not available! Try another one.");
        }
    }

    static void viewReports() {
        System.out.println("📊 REPORTS");
        System.out.println("Total Earnings: " + totalEarnings);
        for (Show s : shows) {
            System.out.println("Show: " + s.name + " | Available Seats: " + s.getAvailableSeats());
        }
    }
}
