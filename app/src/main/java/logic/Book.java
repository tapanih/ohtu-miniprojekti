
package logic;

public class Book {
    
    private String title;
    private String author;
    private int pageCount;
    private int id;
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pageCount == book.pageCount &&
                title.equals(book.title) &&
                author.equals(book.author);
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
