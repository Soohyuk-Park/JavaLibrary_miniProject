import java.util.ArrayList;

public interface workInterface {
    public static void lendBook(ArrayList<Member> memberList, ArrayList<Book> bookList){};

    public static void returnBook(ArrayList<Member> memberList, ArrayList<Book> bookList){};

    static Member getMemberByID(String ID, ArrayList<Member> L) {
        return null;
    }

    static Book getBookByName(String Name, ArrayList<Book> L) {
        return null;
    }
}
