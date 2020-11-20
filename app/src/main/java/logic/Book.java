
package logic;

public class Book {
    
    private String title;
    private String author;
    private int pageCount;

    public Book(){

    }
    
    public Book(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    
    public int getPages() {
        return pageCount;
    }

    public void setTitle(String otsikko) {
        this.title = otsikko;
    }

    public void setAuthor(String kirjailija) {
        this.author = kirjailija;
    }
    

    public void setPages(int sivut) {
        this.pageCount = sivut;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + pageCount;
    }
    
}
