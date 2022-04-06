import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface BookInterface extends Utils {

    void add(Book book) throws IOException;

    void delete(ArrayList<Book> bookList) throws IOException;

    void setting(Book book);

    void viewAll(Book book) throws IOException;

    ArrayList<Book> getBookInstanceList(ArrayList<Book> L ) throws IOException;

    boolean checkBookExistence( ArrayList<Book> bookList);

    int getBlankRow() throws IOException;

    void searchBookInfo(ArrayList<Book> bookList);
}
