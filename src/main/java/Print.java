import java.io.IOException;
import java.util.Scanner;

public class Print implements PrinterInterface{

    public static void printStartLibrary(){
        Scanner sc = new Scanner(System.in);
        while( true ) {
            System.out.println(" ## 도서관 관리자 시스템을 시작합니다 ## ");
            System.out.println(" ## 관리자 ID를 입력해주세요. : ");
            String adminID = sc.nextLine();
            if (adminID.equals(mainLibrary.realadminID)) {
                System.out.println(" ## 관리자 passWord를 입력해주세요 : ");
                String adminPass = sc.nextLine();
                if (adminPass.equals(mainLibrary.realadminPassword)){
                    System.out.println(" 도서관 관리자 모드 접속에 성공했습니다. ");
                    break;
                }
                else{
                    System.out.println("passWord가 틀린 것 같습니다!!!!!!!");
                    continue;
                }
            }
            else{
                System.out.println("ID가 틀린 것 같습니다!!!!!!");
                continue;
            }
        }
    }

    public static int printStartLibrary(Book book) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to soohyuk's Library. We can make a better day.");
        System.out.println("[1]addNewBook\t[2]viewAllBooks" +
                "\t[3]checkBookExistence\t[4]deleteBook\n[5]searchBook\t[0]EndOfTheProgram");
        System.out.println(" Check the list you can choose. What do you want to do?");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("0~6까지의 선택지중 하나를 골라주세요. : ");
        }
        int nowNum = sc.nextInt();
        return nowNum;
    }

    public static int printStartLibrary(Member member) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to soohyuk's Library. We can make a better day.");
        System.out.println("[1]addNewMember\t[2]viewAllMember" +
                "\n[3]DeleteMember\t[4]ChangePassWord\t[0]EndOfTheProgram");
        System.out.println(" Check the list you can choose. What do you want to do?");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("0~4까지의 선택지중 하나를 골라주세요. : ");
        }
        int nowNum = sc.nextInt();
        return nowNum;
    }

    public static void printBookInfo(Book book){
        System.out.println(book.toString());
    }

    public static int modeSelection(){
        System.out.println("## 어떤 업무를 진행하시겠습니까? ##");
        System.out.println("1. 도서관리 업무를 시작합니다. ");
        System.out.println("2. 회원관리 업무를 시작합니다. ");
        System.out.println("3. 도서 대출/반납 관련 업무를 시작합니다.");
        Scanner sc = new Scanner(System.in);
        int selection = sc.nextInt();
        sc.nextLine();
        return selection;
    }
}
