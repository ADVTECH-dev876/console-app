import java.util.*;

class Book {
    int id;
    String title;
    String author;
    int copies;

    public Book(int id, String title, String author, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }
}

class Member {
    int id;
    String name;
    List<Book> borrowedBooks;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }
}

public class LibraryApp {
    static Scanner sc = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static List<Member> members = new ArrayList<>();
    static int bookCounter = 1;
    static int memberCounter = 1;

    public static void main(String[] args) {
        // 📚 Preloaded books
        books.add(new Book(bookCounter++, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 4));
        books.add(new Book(bookCounter++, "The Hobbit", "J.R.R. Tolkien", 3));
        books.add(new Book(bookCounter++, "1984", "George Orwell", 5));
        books.add(new Book(bookCounter++, "Pride and Prejudice", "Jane Austen", 2));
        books.add(new Book(bookCounter++, "The Da Vinci Code", "Dan Brown", 6));

        int choice;
        do {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register Member");
            System.out.println("4. View Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> registerMember();
                case 4 -> viewMembers();
                case 5 -> borrowBook();
                case 6 -> returnBook();
                case 7 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);
    }

    static void addBook() {
        sc.nextLine(); // consume newline
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        System.out.print("Enter Number of Copies: ");
        int copies = sc.nextInt();

        books.add(new Book(bookCounter++, title, author, copies));
        System.out.println("✅ Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Available Books ---");
        for (Book b : books) {
            System.out.println("ID: " + b.id + " | " + b.title + " by " + b.author + 
                               " | Copies: " + b.copies);
        }
    }

    static void registerMember() {
        sc.nextLine();
        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();

        members.add(new Member(memberCounter++, name));
        System.out.println("✅ Member registered successfully!");
    }

    static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        System.out.println("\n--- Members ---");
        for (Member m : members) {
            System.out.println("ID: " + m.id + " | Name: " + m.name);
        }
    }

    static void borrowBook() {
        if (books.isEmpty() || members.isEmpty()) {
            System.out.println("No books or members found!");
            return;
        }

        viewMembers();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        viewBooks();
        System.out.print("Enter Book ID to borrow: ");
        int bookId = sc.nextInt();

        Book book = findBookById(bookId);
        if (book == null || book.copies <= 0) {
            System.out.println("❌ Book not available!");
            return;
        }

        member.borrowedBooks.add(book);
        book.copies--;
        System.out.println("✅ " + member.name + " borrowed \"" + book.title + "\"");
    }

    static void returnBook() {
        viewMembers();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("❌ Member not found!");
            return;
        }

        if (member.borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books to return.");
            return;
        }

        System.out.println("\n--- Borrowed Books ---");
        for (int i = 0; i < member.borrowedBooks.size(); i++) {
            Book b = member.borrowedBooks.get(i);
            System.out.println((i + 1) + ". " + b.title);
        }

        System.out.print("Enter book number to return: ");
        int choice = sc.nextInt();

        if (choice > 0 && choice <= member.borrowedBooks.size()) {
            Book b = member.borrowedBooks.remove(choice - 1);
            b.copies++;
            System.out.println("✅ Returned \"" + b.title + "\" successfully.");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    static Book findBookById(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    static Member findMemberById(int id) {
        for (Member m : members) {
            if (m.id == id) return m;
        }
        return null;
    }
}
