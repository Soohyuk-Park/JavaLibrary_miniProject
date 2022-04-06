public class Book {
    private int bookNumber;
    private String bookName;
    private String author;
    private int price;
    private int isAvailable;

    public Book(){
    }

    public Book( String name ){
        this.bookName = name;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailable() {
        return isAvailable;
    }

    public void setAvailable(int available) {
        isAvailable = available;
    }

    public Book(int bookNumber, String bookName, String author, int price, int isAvailable) {
        this.bookNumber = bookNumber;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "##   Book Information   ###\n" +
                "## bookNumber  =  " + bookNumber + '\n' +
                "## bookName  =  " + bookName + '\n' +
                "## author  =  " + author + '\n' +
                "## price  =  " + price + '\n' +
                "## available  =  " + isAvailable + '\n' +
                "## THIS IS THE INFORMATION OF THE BOOK YOU WANT TO KNOW";
    }
}
