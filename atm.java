import java.util.*;

class Account {
    private int accountNumber;
    private String password;
    private double balance;
    private List<String> transactions;

    public Account(int accountNumber, String password, double balance) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + balance);
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount + " | Balance: " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: " + amount + " | Balance: " + balance);
            return true;
        } else {
            return false;
        }
    }

    public void printMiniStatement() {
        System.out.println("\n=== Mini Statement ===");
        int start = Math.max(0, transactions.size() - 5);
        for (int i = start; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
        System.out.println("======================\n");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

public class ATMApp {
    static Scanner sc = new Scanner(System.in);
    static List<Account> accounts = new ArrayList<>();
    static int accountCounter = 100; // auto-generate account numbers

    public static void main(String[] args) {
        // Pre-loaded accounts
        accounts.add(new Account(accountCounter++, "pass123", 5000));
        accounts.add(new Account(accountCounter++, "hello456", 10000));

        int choice;
        do {
            System.out.println("\n=== ATM SYSTEM ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    static void createAccount() {
        sc.nextLine(); // consume newline
        System.out.print("Enter a password for your account: ");
        String password = sc.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = sc.nextDouble();

        Account newAcc = new Account(accountCounter++, password, deposit);
        accounts.add(newAcc);

        System.out.println("✅ Account created successfully!");
        System.out.println("Your Account Number is: " + newAcc.getAccountNumber());
    }

    static void login() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        Account user = authenticateUser(accNo, pass);

        if (user == null) {
            System.out.println("❌ Invalid account or password!");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Mini Statement");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Your Balance: " + user.getBalance());
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amt = sc.nextDouble();
                    user.deposit(amt);
                    System.out.println("✅ Deposited successfully.");
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amt = sc.nextDouble();
                    if (user.withdraw(amt)) {
                        System.out.println("✅ Withdrawal successful.");
                    } else {
                        System.out.println("❌ Insufficient balance.");
                    }
                }
                case 4 -> user.printMiniStatement();
                case 5 -> System.out.println("🔒 Logged out.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static Account authenticateUser(int accNo, String pass) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo && acc.authenticate(pass)) {
                return acc;
            }
        }
        return null;
    }
}
