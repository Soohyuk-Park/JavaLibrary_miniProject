import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class mainLibrary {
    static int userInput;
    static String realadminID = "soohyuk";
    static String realadminPassword = "1234";
    public static ArrayList<Book> bookList = new ArrayList<>();
    public static ArrayList<Member> memberList = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        bookManager mgr = new bookManager();
        memberManager Mmgr = new memberManager();
        workManager Wmgr = new workManager();
        Print.printStartLibrary();
        bookList = mgr.getBookInstanceList(bookList);
        memberList = Mmgr.getMemberInstanceList(memberList);

        while (true) {
            int mode = Print.modeSelection();
            if (mode == 1) {
                boolean keepGoing = true;
                while (keepGoing) {
                    bookList = mgr.getBookInstanceList(bookList);
                    Book B = new Book();
                    userInput = Print.printStartLibrary(B);
                    if (userInput == 1) {
                        mgr.add(B);
                    }
                    if (userInput == 2) {
                        mgr.viewAll(B);
                    }
                    if (userInput == 3) {
                        mgr.checkBookExistence(bookList);
                    }
                    if (userInput == 4) {
                        mgr.delete(bookList);
                    }
                    if (userInput == 5) {
                        mgr.searchBookInfo(bookList);
                    }
                    if (userInput == 0) {
                        System.out.println("도서관리 업무를 종료하도록 하겠습니다.");
                        keepGoing = false;
                    }
                    if (userInput != 0) {
                        System.out.println(" Press Any Key to keep Running on Library. ");
                        String waitMoment = sc.nextLine();
                    }
                }
            }
            if (mode == 2) {
                boolean keepGoing = true;
                while (keepGoing) {
                    Member M = new Member();
                    memberList = Mmgr.getMemberInstanceList(memberList);
                    userInput = Print.printStartLibrary(M);
                    if (userInput == 1) {
                        Mmgr.add(M);
                    }
                    if (userInput == 2) {
                        Mmgr.viewAll(M);
                    }
                    if (userInput == 3) {
                        Mmgr.delete(memberList);
                    }
                    if (userInput == 4) {
                        Mmgr.changePassword(memberList);
                    }
                    if (userInput == 0) {
                        System.out.println("회원관리 업무를 종료하도록 하겠습니다.");
                        keepGoing = false;
                    }
                    if (userInput != 0) {
                        System.out.println(" Press Any Key to keep Running on Library. ");
                        String waitMoment = sc.nextLine();
                    }
                }

                }
            if (mode == 3){
                System.out.println("# 책의 대출 / 반납 업무를 시작하겠습니다. #");
                System.out.println("## 대출을 하시려면 1번을, 반납은 2번을 눌러주세요. ##");
                int lendOrReturn = sc.nextInt();
                sc.nextLine();
                if( lendOrReturn == 1) {
                    System.out.println(" ### 회원님께서 책을 대출하려고 합니다. ### ");
                    Wmgr.lendBook(memberList, bookList);
                    ExcelUtil.realbookListToExcel(bookList);
                    ExcelUtil.realMemberListToExcel(memberList);
                }
                else if( lendOrReturn == 2) {
                    System.out.println(" ### 회원님께서 책을 반납하려고 합니다. ### ");
                    Wmgr.returnBook(memberList, bookList );
                    ExcelUtil.realbookListToExcel(bookList);
                    ExcelUtil.realMemberListToExcel(memberList);
                }
                else {
                    System.out.println("** 잘못된 입력으로 업무를 종료합니다. ** ");
                }
            }

            }
        }
}

