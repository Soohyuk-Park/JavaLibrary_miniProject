import java.util.ArrayList;
import java.util.Scanner;

public class workManager implements workInterface{
    public static void lendBook(ArrayList<Member> memberList, ArrayList<Book> bookList){
        Scanner sc = new Scanner(System.in);
        System.out.println("책을 빌리신다구요??");
        System.out.println("회원님의 ID를 입력해주세요 :: ");
        String ID = sc.nextLine();
        Member nowMem = getMemberByID(ID, memberList);
        System.out.println("비밀번호를 입력해주세요. ");
        String PassWord = sc.nextLine();
        if( nowMem.getPassword().equals(PassWord)){
            System.out.println("어떤 제목의 책을 빌리시겠습니까?");
            String BookName = sc.nextLine();
            Book searchNowBook = getBookByName(BookName, bookList);
            if( searchNowBook.getAvailable() == 1) {
                nowMem.getNowBorrowBook().add(searchNowBook.getBookName());
                searchNowBook.setAvailable(0);
            }
            else {
                System.out.println("그 책은 누군가 이미 대출중이네요... ㅜㅜㅜㅜ");
            }
        }
        else {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }
    }

    public static void returnBook(ArrayList<Member> memberList, ArrayList<Book> bookList){
        Scanner sc = new Scanner(System.in);
        System.out.println("책을 반납하신다구요??");
        System.out.println("회원님의 아이디를 입력해주세요.");
        String returnID = sc.nextLine();
        System.out.println("반납하실 책의 제목을 입력해주세요 :: ");
        String returnBook = sc.nextLine();
        Member nowMem = getMemberByID(returnID, memberList);
        ArrayList<String> nowMemberLentBookList = nowMem.getNowBorrowBook();
        for( int i = 0 ; i < nowMemberLentBookList.size(); i++){
            if( returnBook.equals(nowMemberLentBookList.get(i))){
                nowMemberLentBookList.remove(i);
                break;
            }
        }
        Book searchNowBook = getBookByName(returnBook, bookList);
        if( searchNowBook.getAvailable() == 0) {
            searchNowBook.setAvailable(1);
        }
    }


    public static Member getMemberByID(String ID, ArrayList<Member> L){
        Scanner sc = new Scanner(System.in);
        while(true) {
            for (int i = 0; i < L.size(); i++) {
                if (L.get(i).getUserID().equals(ID)) {
                    return L.get(i);
                }
            }
            System.out.println("그런 ID를 가진 사람은 없습니다.");
            System.out.println("ID를 다시 입력해보실래요? :: ");
            ID = sc.nextLine();
        }

    }

    public static Book getBookByName(String Name, ArrayList<Book> L){
        Scanner sc = new Scanner(System.in);
        while(true) {
            for (int i = 0; i < L.size(); i++) {
                if (L.get(i).getBookName().equals(Name)) {
                    return L.get(i);
                }
            }
            System.out.println("그런 제목을 가진 책은 없습니다.");
            System.out.println("제목을 다시 입력해보실래요? :: ");
            Name = sc.nextLine();
        }

    }

}
