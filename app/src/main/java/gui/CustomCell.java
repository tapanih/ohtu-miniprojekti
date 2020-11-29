package gui;


import dao.BookmarkDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import logic.Book;


public class CustomCell extends ListCell<Book> {
    private final Button deleteButton;
    private Book book;
    private final Label bookLabel;
    private final GridPane pane;
    private final ListView<Book> listView;
    private final ObservableList<Book> bookList;
    private final ArrayList<Book> fullBookList;
 
    
    public CustomCell(BookmarkDao service, ListView<Book> listView, 
        ObservableList<Book> xbookList, ArrayList<Book> xfullBookList) {
        super();
        this.listView = listView;
        this.bookList = xbookList;
        this.fullBookList = xfullBookList;

        deleteButton = new Button("Poista");
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Haluatko varmasti poistaa kyseisen kirjan?");
        
        
        deleteButton.setOnAction(event -> {
            try {
                Optional<ButtonType> result = alert.showAndWait();
                // check if "X" or "Cancel" is pressed
                if (result.isEmpty() || result.get() == ButtonType.CANCEL)  {
                    return;
                }
                
                service.deleteBook(book);
                
            } catch (SQLException e) {
                return;
            }
            fullBookList.remove(book);
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
