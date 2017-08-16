import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Book> parseBooksFromFile(Path p) {
        List<Book> bookList = new ArrayList<>();

        try {
            Book book;
            for (String line : Files.readAllLines(p)) {
                String[] elements = line.split(":");
                book = new Book(elements[0],
                        elements[1],
                        Integer.parseInt(elements[2]),
                        Integer.parseInt(elements[3]),
                        Integer.parseInt(elements[4]));
                bookList.add(book);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
