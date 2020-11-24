
package logic;

import java.util.Objects;

public class Book {
    
    private String title;
    private String author;
    private int pageCount;
    public int id;

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
    
    public int getId() {
        return id;
    }

    public void setTitle(String otsikko) {
        this.title = otsikko;
    }

    public void setAuthor(String kirjailija) {
        this.author = kirjailija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pageCount);
    }

    public void setPages(int sivut) {
        this.pageCount = sivut;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + pageCount + " sivua";
    }
    
}
