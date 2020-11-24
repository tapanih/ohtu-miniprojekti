package gui;


import dao.BookmarkDao;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
    private HBox hBox;
    private ListView<Book> listView;
    private ObservableList<Book> bookList;
    
    public CustomCell(BookmarkDao service, ListView<Book> listView, 
            ObservableList<Book> bookList) {
        super();
        this.listView = listView;
        this.bookList = bookList;
        
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
                    return;
                }
                bookList.remove(book);
                updateListView(listView);
                updateItem(book, true);
            }
            
        });
        
        hBox = new HBox();
        title = new Label();
        author = new Label();
        pages = new Label();
        HBox deleteButtonBox = new HBox();
        deleteButtonBox.getChildren().add(deleteButton);
        
        hBox.getChildren().add(deleteButtonBox);
        
        hBox.getChildren().add(title);
        hBox.getChildren().add(author);
        hBox.getChildren().add(pages);
        
    }
    @Override
    public void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);
        this.book = book;
        if (empty || book == null || book.getTitle() == null) {
            setText(null);
            setGraphic(null);
            updateListView(listView);
        } else {
            setText(book.toString());
            setGraphic(hBox);
            updateListView(listView);
        }
    }


}