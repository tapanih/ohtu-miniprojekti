package gui;


import dao.BookmarkDao;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import logic.Book;


public class CustomCell extends ListCell<Book> {
    private Button deleteButton;
    private Book book;
    private Label bookLabel;
    private BookmarkDao service;
    private GridPane pane;
    private ListView<Book> listView;
    private ObservableList<Book> bookList;
 
    
    public CustomCell(BookmarkDao service, ListView<Book> listView, 
            ObservableList<Book> bookList) {
        super();
        this.listView = listView;
        this.bookList = bookList;
        
        this.service = service;
        deleteButton = new Button("Poista");
        deleteButton.setOnAction(event -> {
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
        });
        
        pane = new GridPane();
        bookLabel = new Label();
        HBox deleteButtonBox = new HBox();
        deleteButtonBox.getChildren().add(deleteButton);
        HBox labelBox = new HBox();
        pane.add(labelBox, 0, 0);
        ColumnConstraints contraints = new ColumnConstraints();
        contraints.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().add(contraints);
        labelBox.getChildren().add(bookLabel);
        pane.add(deleteButtonBox, 2, 0);
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
            deleteButton.setId("delete" + book.getId());
            bookLabel.setText(book.getTitle() + ", " + book.getAuthor() + ", " + 
                    book.getPages() + " sivua");
            setGraphic(pane);
            updateListView(listView);
        }
    }


}
