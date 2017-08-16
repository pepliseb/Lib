import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    public static void saveBooksToFile(Path path, List<Book> books){
        String[] elementsArray = new String[5];
        StringBuilder builder;
        List<String> lines = new ArrayList<>();

        for (Book book : books) {
            builder = new StringBuilder();
        elementsArray[0] = book.getName() + ":";
        elementsArray[1] = book.getAuthor() + ":";
        elementsArray[2] = book.getPages() + ":";
        elementsArray[3] = book.getProduceYear() + ":";
        elementsArray[4] = book.getRentStatus() + ":" ;
            for (String s : elementsArray) {
            builder.append(s);
        }
        builder.append("\n");
            lines.add(builder.toString());

        }
            try {
                Files.write(path,lines, StandardOpenOption.TRUNCATE_EXISTING);
            }catch (IOException e) {
            e.printStackTrace();
            }
    }
}
