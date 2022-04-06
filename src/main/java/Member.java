import java.util.ArrayList;

public class Member {
    private String name;
    private String userID;
    private String password;
    private ArrayList<String> nowBorrowBook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getNowBorrowBook() {
        return nowBorrowBook;
    }

    public void setNowBorrowBook( ArrayList<String> L){
        this.nowBorrowBook = L;
    }

}
