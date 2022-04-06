import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class bookManager implements BookInterface {


    Scanner sc = new Scanner(System.in);
    private static bookManager bManger = new bookManager();

    public static bookManager getInstance() {
        return bManger;
    }

    public void add(Book book) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(":: 새로운 책의 등록을 시작하겠습니다 :: ");
        Book nowbook = new Book();
        setting(nowbook);
        int rows = getBlankRow();
        for (int i = rows; i < rows + 1; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                HSSFCell cell = row.createCell(j);
                if (j == 0) {
                    cell.setCellValue(nowbook.getBookName()); //생성한 셀에 데이터 삽입
                }
                if (j == 1) {
                    cell.setCellValue(nowbook.getBookNumber());
                }
                if (j == 2) {
                    cell.setCellValue(nowbook.getAuthor());
                }
                if (j == 3) {
                    cell.setCellValue(nowbook.getPrice());
                }
                if (j == 4) {
                    cell.setCellValue(nowbook.getAvailable());
                }
            }
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("책이 성공적으로 추가되었습니다.");
            System.out.println("## 이제 " + nowbook.getBookName() + "은 우리 도서관에서 이용할 수 있어요. ##");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(ArrayList<Book> bookList) throws IOException {
        System.out.println("삭제하고싶은 책의 제목을 입력하세요 : ");
        String delBook = sc.nextLine();
        for( int i = 0 ; i< bookList.size(); i++){
            if( bookList.get(i).getBookName().equals(delBook)){
                bookList.remove(i);
                break;

            }
            else if( i == bookList.size() - 1){
                System.out.println(" 그런 책이 없어요. 삭제가 불가능합니다. ");
                return;
            }
        }
        ExcelUtil.bookListToExcel(bookList);
    }

    public void setting(Book book) {
        System.out.println("What is the ## Name ## of the book you want to add?? ");
        String bookName = sc.nextLine();
        book.setBookName(bookName);
        System.out.println("What is the ## Number ## of the book you want to add?? ");
        int nowNum = sc.nextInt();
        sc.nextLine();
        book.setBookNumber(nowNum);
        System.out.println("What is the ## Author ## of the book you want to add?? ");
        String author = sc.nextLine();
        book.setAuthor(author);
        System.out.println("What is the ## Price ## of the book you want to add?? ");
        int nowPrice = sc.nextInt();
        sc.nextLine();
        book.setPrice(nowPrice);
        book.setAvailable(1);
        return;
    }

    public void viewAll(Book book) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 5; j++) {
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

    public boolean checkBookExistence(ArrayList<Book> bookList) {
        System.out.println(" 당신이 찾고싶은 책의 제목을 입력해주세요 : ");
        String bookName = sc.nextLine();
        for( int i = 0 ; i < bookList.size(); i++ )
            if( bookList.get(i).getBookName().equals(bookName)){
                System.out.print("당신이 찾는 책 " + bookName + " 은 도서관이 소장하고 있습니다.\n");
                if( bookList.get(i).getAvailable() == 1){
                    System.out.println("# 지금 당장 대출도 가능합니다 ~~ #");
                    return true;
                }
                else System.out.println("# 그런데 누군가 이미 대출중이네요..!! ㅠㅠ");
                return false;
            }
            else if( i == bookList.size() - 1){
                System.out.print("당신이 찾는 책 " + bookName + " 은 도서관이 없습니다......\n");
                return false;
            }
            return false;
    }

    public int getBlankRow() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
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

    public ArrayList<Book> getBookInstanceList(ArrayList<Book> L ) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/bagsuhyeog/Documents/excelPractice/src/main/java/assets/BookList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        int rows = getBlankRow();
        L = new ArrayList<Book>();
        for (int i = 1; i < rows; i++) {
            Book nowbook = new Book();
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 5; j++) {
                HSSFCell cell = row.getCell(j);
                String value = "";
                switch (cell.getCellType()) { // 각 셀에 담겨있는 데이터의 타입을 체크하고 해당 타입에 맞게 가져온다.
                    case NUMERIC:
                        value = cell.getNumericCellValue() + "";
                        int nowDouble = (int) Double.parseDouble(value);
                        if (j == 1) {
                            nowbook.setBookNumber(nowDouble);
                        }
                        if (j == 3) {
                            nowbook.setPrice(nowDouble);
                        }
                        if (j == 4) {
                            nowbook.setAvailable(nowDouble);
                        }
                        break;
                    case STRING:
                        value = cell.getStringCellValue() + "";
                        break;
                }
                if (j == 0) {
                    nowbook.setBookName(value);
                }
                if (j == 2) {
                    nowbook.setAuthor(value);
                }
            }
            L.add(nowbook);
        }
        System.out.println("## 현재 도서관이 소장하고 있는 책은 총  "+ L.size() + "권 입니다. ##");
        return L;
    }



    public void searchBookInfo(ArrayList<Book> bookList){
        System.out.println("당신이 찾고싶은 책의 이름을 입력하세요.");
        String str = sc.nextLine();
        for( int i = 0 ; i < bookList.size(); i++){
            if( bookList.get(i).getBookName().equals(str) ){
                System.out.println(bookList.get(i).toString());
                return;
            }
        }
        System.out.println("그런 책은 없는 것 같습니다!!!!! ㅜㅜㅜ");
        return;
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


