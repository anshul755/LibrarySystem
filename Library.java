package DS_assignment;

import java.util.*;

public class Library {

    static class Book {
        long Isbn;
        String Title;
        String Author;
        int Publishedyear;

        public Book(long Isbn, String Title, String Author, int Publishedyear) {
            this.Isbn = Isbn;
            this.Title = Title;
            this.Author = Author;
            this.Publishedyear = Publishedyear;
        }

        @Override
        public String toString() {
            return String.format("%-15d %-20s %-20s %-15d", Isbn, Title, Author, Publishedyear);
        }
    }

    static class node {
        Book book;
        node left;
        node right;

        node(Book book) {
            this.book = book;
            this.left = null;
            this.right = null;
        }
    }

    private node root;

    public void toInsert(Book book) {
        root = InsertRec(root, book);
    }

    public static node InsertRec(node root, Book book) {
        if (root == null) {
            root = new node(book);
            return root;
        }

        if (book.Isbn < root.book.Isbn) {
            root.left = InsertRec(root.left, book);
        } else if (book.Isbn > root.book.Isbn) {
            root.right = InsertRec(root.right, book);
        }

        return root;
    }

    public Book toSearch(long Isbn) {
        node result = SearchRec(root, Isbn);
        return result != null ? result.book : null;
    }

    private node SearchRec(node root, long Isbn) {
        if (root == null) {
            return root;
        }

        if (root.book.Isbn == Isbn) {
            return root;
        }

        if (Isbn < root.book.Isbn) {
            return SearchRec(root.left, Isbn);
        } else {
            return SearchRec(root.right, Isbn);
        }
    }

    public void toDel(long Isbn) {
        root = Delrec(root, Isbn);
    }

    private node Delrec(node root, long Isbn) {
        if (root == null) {
            return root;
        }

        if (Isbn < root.book.Isbn) {
            root.left = Delrec(root.left, Isbn);
        } else if (Isbn > root.book.Isbn) {
            root.right = Delrec(root.right, Isbn);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.book = inordersuccessor(root.right).book;
            root.right = Delrec(root.right, root.book.Isbn);
        }
        return root;
    }

    public static node inordersuccessor(node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public void printInfoaboutBooks() {
        System.out.printf("%-15s %-20s %-20s %-15s%n", "Isbn", "Title", "Author", "Year");
        System.out.println("-------------------------------------------------------------");
        printInfoaboutBooksRec(root);
    }

    private void printInfoaboutBooksRec(node root) {
        if (root != null) {
            printInfoaboutBooksRec(root.left);
            System.out.println(root.book);
            printInfoaboutBooksRec(root.right);
        }
    }

    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Hi, Welcome to Library");
            System.out.println("Menu............");
            System.out.println();
            System.out.println("1.)Add a Book");
            System.out.println("2.)Search for a Book");
            System.out.println("3.)Remove a Book");
            System.out.println("4.)Print all Books");
            System.out.println("5.)Exit");

            System.out.println("Enter Your Choice:");
            int s = sc.nextInt();

            switch (s) {
                case 1:
                    System.out.println("Enter Isbn: ");
                    long Isbn = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.println("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.println("Enter Published Year: ");
                    int year = sc.nextInt();
                    Book newBook = new Book(Isbn, title, author, year);
                    lib.toInsert(newBook);
                    System.out.println();
                    System.out.println("Book Added to System......");
                    break;

                case 2:
                    System.out.print("Enter Isbn to search: ");
                    long searchIsbn = sc.nextInt();
                    Book book = lib.toSearch(searchIsbn);
                    if (book != null) {
                        System.out.println("Book found: " + book);
                    } else {
                        System.out.println("Book not found....");
                    }
                    break;

                case 3:
                    System.out.print("Enter Isbn to remove: ");
                    long deleteIsbn = sc.nextInt();
                    lib.toDel(deleteIsbn);
                    System.out.println("Book removed successfully!.....");
                    break;

                case 4:
                    lib.printInfoaboutBooks();
                    break;

                case 5:
                    System.out.println("Thanks for using our Program......");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice, Please Choose Again");
                    break;
            }
        }
    }
}