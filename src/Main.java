import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

        private Scanner scanner;
        private List<Book> bookList = new ArrayList<>();
        public Main() {
            scanner = new Scanner(System.in);
            bookList = Utils.parseBooksFromFile(Paths.get("books.txt"));
            start();

        }

        private void start(){
            System.out.println("Witaj w mojej bibliotece");

            String command;
            do {
                System.out.println("------------------------------");
                System.out.println("1 - Dodawanie książki");
                System.out.println("2 - Wypożyczenie książki");
                System.out.println("3 - Oddawanie książki");
                System.out.println("4 - Wyświetl wolne ksiązki");
                System.out.println("Wpisz polecenie: ");
                System.out.println("-------------------------------");
                command = scanner.nextLine();
                parseChoice(command);
//
            }while (!command.equals("exit"));
        }

        private void parseChoice(String command){
            switch (command) {
                case "4":{
                    showFreeBooks();
                    break;
                    }
                case "1" : {
                    addBook();
                    break;
                }
                case "2" : {
                    rentBook();
                    break;
                }
                case "3" :{
                    bringBackBook();
                    break;
                }
                case "exit" : {
                    Utils.saveBooksToFile(Paths.get("books.txt"), );
                    break;
                }
                default: {
                    System.out.println("Nie ma takiej komendy");
                }
                }

            }

    private void bringBackBook() {
        System.out.println("Podaj nazwe ksiązki : ");
        String name = scanner.nextLine();
        for (Book book : bookList) {
            if (book.getName().equalsIgnoreCase(name) && book.getRentStatus() == 1) {
                book.setRentStatus(0);
                System.out.println("Oddano ksiazki" + book.getName());
                System.out.println("Zapraszamy ponownie");
                return;
            }
        }
        System.out.println("Taka ksiazka nie istnieje w bazie");
    }

    private void rentBook() {
        System.out.println("Podaj nazwe do wypozyczenia: ");
        String name = scanner.nextLine();
        for (Book book: bookList) {
            if (book.getName().equalsIgnoreCase(name) && book.getRentStatus()==0){
                book.setRentStatus(1);
                System.out.println("Wydano ksiazki" + book.getName());
                System.out.println("Oddaj w terminie");
                return;
            }

        }
        System.out.println("Brak takiej ksiazki!");
    }

    private void addBook() {
        System.out.println("Dodaj nową ksiązki!");

                String title, author;
                int pages, produceYear;
        System.out.println("Tytuł: ");
        title = scanner.nextLine();

        for (Book book : bookList) {
            if(book.getName().equalsIgnoreCase(title)){
                System.out.println("Taka ksiazka już istniej");
                return;
            }
        }

        System.out.println("Author: ");
        author = scanner.nextLine();

        System.out.println("Ilość stron: ");
        pages = Integer.parseInt(scanner.nextLine());

        System.out.println("Rok wydania: ");
        produceYear = Integer.parseInt(scanner.nextLine());

        bookList.add(new Book(title,author,pages,produceYear, 0));
        System.out.println("Dodano ksiązke " + title);
    }


    private void showFreeBooks() {
        for (Book book : bookList) {
            if (book.getRentStatus() == 0) {
                System.out.println("Wolna poycja: " + book.getName());
            }
        }
    }}



