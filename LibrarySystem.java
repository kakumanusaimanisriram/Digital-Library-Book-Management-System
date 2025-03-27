import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private String availability;

    public Book(int bookId, String title, String author, String genre, String availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getAvailability() { return availability; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setAvailability(String availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + availability;
    }
}

public class LibrarySystem {
    private static final Map<Integer, Book> library = new HashMap<>();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Remove Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = input.nextInt();
            input.nextLine();

            switch (option) 
            {
                case 1 : addBook();
                case 2 : displayBooks();
                case 3 : findBook();
                case 4 : modifyBook();
                case 5 : removeBook();
                case 6 :
                {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default : System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = input.nextInt();
        input.nextLine();
        if (library.containsKey(id)) {
            System.out.println("This ID already exists!");
            return;
        }
        System.out.print("Enter Title: ");
        String title = input.nextLine();
        System.out.print("Enter Author: ");
        String author = input.nextLine();
        System.out.print("Enter Genre: ");
        String genre = input.nextLine();
        System.out.print("Availability (Available/Checked Out): ");
        String status = input.nextLine();

        if (title.isEmpty() || author.isEmpty() || (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Checked Out"))) {
            System.out.println("Invalid input. Try again.");
            return;
        }

        library.put(id, new Book(id, title, author, genre, status));
        System.out.println("Book added successfully.");
    }

    private static void displayBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        library.values().forEach(System.out::println);
    }

    private static void findBook() {
        System.out.print("Enter Book ID or Title: ");
        String query = input.nextLine();
        boolean located = false;

        for (Book book : library.values()) {
            if (String.valueOf(book.getBookId()).equals(query) || book.getTitle().equalsIgnoreCase(query)) {
                System.out.println(book);
                located = true;
            }
        }
        if (!located) System.out.println("Book not found.");
    }

    private static void modifyBook() {
        System.out.print("Enter Book ID to modify: ");
        int id = input.nextInt();
        input.nextLine();
        if (!library.containsKey(id)) {
            System.out.println("Book not found!");
            return;
        }
        Book book = library.get(id);
        System.out.print("New Title (Leave blank to keep current): ");
        String title = input.nextLine();
        System.out.print("New Author (Leave blank to keep current): ");
        String author = input.nextLine();
        System.out.print("New Genre (Leave blank to keep current): ");
        String genre = input.nextLine();
        System.out.print("New Availability (Available/Checked Out): ");
        String status = input.nextLine();

        if (!title.isEmpty()) book.setTitle(title);
        if (!author.isEmpty()) book.setAuthor(author);
        if (!genre.isEmpty()) book.setGenre(genre);
        if (!status.isEmpty() && (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Checked Out"))) {
            book.setAvailability(status);
        }
        System.out.println("Book updated successfully!");
    }

    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = input.nextInt();
        input.nextLine();
        if (library.remove(id) != null) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found!");
        }
    }
}
