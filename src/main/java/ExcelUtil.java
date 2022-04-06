import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelUtil {
    public static void bookListToExcel(ArrayList<Book> bookList) throws IOException {
        int rows = bookManager.getInstance().getBlankRow();
        System.out.println(rows);
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < rows ; i++) {
            HSSFRow row = sheet.createRow(i);
            if( i == rows - 1){
                for( int j = 0; j < 5; j++){
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue("");
                }
                break;
            }
            for (int j = 0; j < 5; j++) {
                HSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(bookList.get(i-1).getBookName()); //생성한 셀에 데이터 삽입
                }
                if (j == 1) {
                    cell.setCellValue(bookList.get(i-1).getBookNumber());
                }
                if (j == 2) {
                    cell.setCellValue(bookList.get(i-1).getAuthor());
                }
                if (j == 3) {
                    cell.setCellValue(bookList.get(i-1).getPrice());
                }
                if (j == 4) {
                    cell.setCellValue(bookList.get(i-1).getAvailable());
                }
            }
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("책이 삭제되고 리스트와 엑셀이 갱신되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void memberListToExcel(ArrayList<Member> memberList) throws IOException {
        int rows = memberManager.getBlankRowMember();
        System.out.println(rows);
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < rows ; i++) {
            HSSFRow row = sheet.createRow(i);
            if( i == rows - 1){
                for( int j = 0; j < 4; j++){
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue("");
                }
                break;
            }
            for (int j = 0; j < 4; j++) {
                HSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(memberList.get(i-1).getName()); //생성한 셀에 데이터 삽입
                }
                if (j == 1) {
                    cell.setCellValue(memberList.get(i-1).getUserID());
                }
                if (j == 2) {
                    cell.setCellValue(memberList.get(i-1).getPassword());
                }
                if (j == 3) {
                    cell.setCellValue(ExcelUtil.arrToStr(memberList.get(i-1).getNowBorrowBook()));
                }
            }
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("리스트와 엑셀이 갱신되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void realbookListToExcel(ArrayList<Book> bookList) throws IOException {
        int rows = bookManager.getInstance().getBlankRow();
        System.out.println(rows);
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < rows ; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                HSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(bookList.get(i-1).getBookName()); //생성한 셀에 데이터 삽입
                }
                if (j == 1) {
                    cell.setCellValue(bookList.get(i-1).getBookNumber());
                }
                if (j == 2) {
                    cell.setCellValue(bookList.get(i-1).getAuthor());
                }
                if (j == 3) {
                    cell.setCellValue(bookList.get(i-1).getPrice());
                }
                if (j == 4) {
                    cell.setCellValue(bookList.get(i-1).getAvailable());
                }
            }
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("### 책의 리스트와 엑셀이 갱신되었습니다. ###");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void realMemberListToExcel(ArrayList<Member> memberList) throws IOException {
        int rows = memberManager.getBlankRowMember();
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < rows ; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 4; j++) {
                HSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(memberList.get(i-1).getName()); //생성한 셀에 데이터 삽입
                }
                if (j == 1) {
                    cell.setCellValue(memberList.get(i-1).getUserID());
                }
                if (j == 2) {
                    cell.setCellValue(memberList.get(i-1).getPassword());
                }
                if (j == 3) {
                    cell.setCellValue(ExcelUtil.arrToStr(memberList.get(i-1).getNowBorrowBook()));
                }
            }
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/MemberList.xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("## 멤버 리스트와 엑셀이 갱신되었습니다. ###");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> strToArr(String nowBorrowBook) {
        ArrayList<String> L = new ArrayList<>();
        int N = nowBorrowBook.length();
        String[] temp = nowBorrowBook.substring( 1,N -1  ).split(",");
        for( int i = 0 ; i < temp.length; i++){
            L.add(temp[i]);
        }
        return L;
    }

    public static String arrToStr( ArrayList<String> L){
        String str = "[";
        for( String a : L){
            str = str + a +',';
        }
        str = str.substring(0,str.length() -1);
        str = str + "]";
        return str;
    }
}

