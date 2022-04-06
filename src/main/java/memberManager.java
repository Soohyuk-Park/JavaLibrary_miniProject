import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class memberManager implements MemberInterface{

    public void add(Member member) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(":: 새로운 회원의 등록을 시작하겠습니다 :: ");
        Member nowMember = new Member();
        setting(nowMember);
        int rows = getBlankRowMember();
        for (int i = rows; i < rows + 1; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 4; j++) {
                HSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(nowMember.getName()); //생성한 셀에 데이터 삽입
                }
                if (j == 1) {
                    cell.setCellValue(nowMember.getUserID());
                }
                if (j == 2) {
                    cell.setCellValue(nowMember.getPassword());
                }
                if (j == 3) {
                    cell.setCellValue( ExcelUtil.arrToStr(nowMember.getNowBorrowBook()) );
                }
            }
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("회원이 성공적으로 추가되었습니다.");
            System.out.println("## 이제 " + nowMember.getName() + "은 우리 도서관의 회원입니다. ##");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(ArrayList<Member> memberList) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제하고싶은 회원의 아이디를 입력하세요 : ");
        String delMemberID = sc.nextLine();
        for( int i = 0 ; i< memberList.size(); i++){
            if( memberList.get(i).getUserID().equals(delMemberID)){
                memberList.remove(i);
                break;

            }
            else if( i == memberList.size() - 1){
                System.out.println(" 그런 회원이 없어요. 삭제가 불가능합니다. ");
                return;
            }
        }
        ExcelUtil.memberListToExcel(memberList);
    }

    public void setting(Member member) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the ## Name ## of the Person you want to add?? ");
        String memberName = sc.nextLine();
        member.setName(memberName);
        System.out.println("What is the ## ID ## of the book you want to add?? ");
        String memberID = sc.nextLine();
        member.setUserID(memberID);
        System.out.println("What is the ## PassWorD ## of the book you want to add?? ");
        String memberPassWord = sc.nextLine();
        member.setPassword(memberPassWord);
        String defaultBorrowBook = "[]";
        member.setNowBorrowBook(ExcelUtil.strToArr(defaultBorrowBook));
        return;
    }

    public void viewAll(Member member) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = getBlankRowMember();
        for (int i = 0; i < rows; i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 4; j++) {
                HSSFCell cell = row.getCell(j);
                String value = "";
                switch (cell.getCellType()) { // 각 셀에 담겨있는 데이터의 타입을 체크하고 해당 타입에 맞게 가져온다.
                    case NUMERIC:
                        value = cell.getNumericCellValue() + "";
                        int realValue = (int) Double.parseDouble(value);
                        value = Integer.toString(realValue);
                        break;
                    case STRING:
                        value = cell.getStringCellValue() + "";
                        break;
                }
                char[] printView = value.toCharArray();
                int N = printView.length;
                for( int k = 0; k < 25; k++){
                    if( k < N) System.out.print( printView[k]);
                    else System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void changePassword( ArrayList<Member> memberList ) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("비밀번호를 바꾸고 싶으시다면, 당신의 아이디를 입력해주세요.");
        String changerID = sc.nextLine();
        System.out.println("현재 비밀번호를 입력해주세요.");
        String beforePassword = sc.nextLine();
        Member changer = workManager.getMemberByID( changerID, memberList);
        if( changer.getPassword().equals(beforePassword)){
            System.out.println("변경하실 비밀번호를 입력해주세요.");
            String newPassword = sc.nextLine();
            changer.setPassword(newPassword);
        }
        else{
            System.out.println("잘못된 비밀번호를 입력하셨습니다.");
        }

        ExcelUtil.realMemberListToExcel(memberList);

    }

    public static int getBlankRowMember() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0); // 해당 엑셀파일의 시트(Sheet) 수

        int rows = sheet.getPhysicalNumberOfRows();
        for (int r = 1; r < rows; r++) {
            HSSFRow row = sheet.getRow(r);
            if (row == null ) {
                return r;
            }
            HSSFCell cell = row.getCell(0);
            if (cell == null ) {
                return r;
            }
            String value = "";
            value = cell.getStringCellValue() + "";
            if( value.equals("")){
                return r;
            }


        }
        return rows;
    }

    public ArrayList<Member> getMemberInstanceList( ArrayList<Member> L ) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = getBlankRowMember();
        L = new ArrayList<Member>();
        for (int i = 1; i < rows; i++) {
            Member nowmember = new Member();
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 4; j++) {
                HSSFCell cell = row.getCell(j);
                String value = "";
                value = cell.getStringCellValue() + "";
                if (j == 0) {
                    nowmember.setName(value);
                }
                if (j == 1) {
                    nowmember.setUserID(value);
                }
                if (j == 2) {
                    nowmember.setPassword(value);
                }
                if( j == 3) {
                    nowmember.setNowBorrowBook(ExcelUtil.strToArr(value) );
                }
            }
            L.add(nowmember);
        }
        System.out.println("## 현재 도서관에 가입한 회원은 총  "+ L.size() + "명 입니다. ##");
        return L;
    }

    @Override
    public void add() throws IOException {

    }

    @Override
    public void delete() throws IOException {

    }

    @Override
    public void setting() {

    }

    @Override
    public void viewAll() throws IOException {

    }
}
