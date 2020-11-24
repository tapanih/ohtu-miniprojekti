package gui;


import dao.BookmarkDao;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.Book;



public class CustomCell extends ListCell<Book> {
    private Button deleteButton;
    private Book book;
    private Label title;
    private Label author;
    private Label pages;
    private BookmarkDao service;
    private Book selectedBook;
    private GridPane gridPane;
    
    public CustomCell(BookmarkDao service) {
        super();
        
        
        
        this.service = service;
        deleteButton = new Button("Poista");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    service.deleteBook(book);
                } catch (SQLException e) {
                    System.out.println("CustomCell handle error: " + 
                            e.getMessage());
                }
                updateItem(book, true);
            }
            
        });
        
        gridPane = new GridPane();
        title = new Label();
        author = new Label();
        pages = new Label();
        gridPane.add(title, 0, 0);
        gridPane.add(author, 0, 1);
        gridPane.add(pages, 0, 2);
        gridPane.add(deleteButton, 0, 3);
        
    }
    @Override
    public void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);
        this.book = book;
        if (empty || book == null || book.getTitle() == null) {
            setText("");
            setGraphic(null);
        } else {
            setText(book.toString());
            setGraphic(gridPane);
        }
    }


}
