import java.util.*;

public class SimpleATM {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initial account setup
        int accountNumber = 12345;
        String password = "pass123";
        double balance = 10000; // starting balance

        System.out.println("=== Welcome to ATM ===");
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        // Authenticate user
        if (accNo != accountNumber || !pass.equals(password)) {
            System.out.println("❌ Invalid Account Number or Password!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> System.out.println("💰 Current Balance: " + balance);

                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    double deposit = sc.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("✅ Deposited " + deposit + ". New Balance: " + balance);
                    } else {
                        System.out.println("❌ Invalid amount.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    if (withdraw > balance) {
                        System.out.println("❌ Insufficient balance!");
                    } else if (withdraw <= 0) {
                        System.out.println("❌ Invalid amount.");
                    } else {
                        balance -= withdraw;
                        System.out.println("✅ Withdrawn " + withdraw + ". Remaining Balance: " + balance);
                    }
                }

                case 4 -> System.out.println("Thank you for using ATM!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }
}
