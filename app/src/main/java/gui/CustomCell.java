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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import logic.Article;
import logic.Book;
import logic.Bookmark;


public class CustomCell extends ListCell<Bookmark> {
    private final Button deleteButton;
    private Bookmark bookmark;
    private final Label bookmarkLabel;
    private final Hyperlink hyperlink;
    private final GridPane pane;
    private final ListView<Bookmark> listView;
    private final ObservableList<Bookmark> bookmarkList;
    private final ArrayList<Bookmark> fullBookmarkList;
 
    
    public CustomCell(BookmarkDao service, ListView<Bookmark> listView, 
        ObservableList<Bookmark> bookmarkList, ArrayList<Bookmark> fullBookmarkList) {
        super();
        this.listView = listView;
        this.bookmarkList = bookmarkList;
        this.fullBookmarkList = fullBookmarkList;

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
                
                if (bookmark.getClass().getName().equals(Article.class.getName())) {
                    service.deleteArticle((Article) bookmark);
                } else {
                    service.deleteBook((Book) bookmark);
                }
                
            } catch (SQLException e) {
                return;
            }
            this.fullBookmarkList.remove(bookmark);
            this.bookmarkList.remove(bookmark);
            updateListView(listView);
            updateItem(bookmark, true);
        });
        
        pane = new GridPane();
        bookmarkLabel = new Label();
        HBox deleteButtonBox = new HBox();
        deleteButtonBox.getChildren().add(deleteButton);
        HBox labelBox = new HBox();
        pane.add(labelBox, 0, 0);
        hyperlink = new Hyperlink();
        ColumnConstraints contraints = new ColumnConstraints();
        contraints.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().add(contraints);
        labelBox.getChildren().add(bookmarkLabel);
        pane.add(deleteButtonBox, 2, 0);
    }
    
    @Override
    public void updateItem(Bookmark bookmark, boolean empty) {
        super.updateItem(bookmark, empty);
        pane.getChildren().remove(hyperlink);
        this.bookmark = bookmark;
        if (empty || bookmark == null || bookmark.getTitle() == null) {
            setText(null);
            setGraphic(null);
            updateListView(listView);
        } else {
            deleteButton.setId("delete" + bookmark.getId());
            if (bookmark.getClass().getName().equals(Book.class.getName())) {
                bookmarkLabel.setText(bookmark.getTitle() + ", " + ((Book) bookmark).getAuthor() + ", " + 
                        ((Book) bookmark).getPages() + " sivua");
            } else {
                pane.add(hyperlink, 1, 0);
                bookmarkLabel.setText(((Article) bookmark).getTitle());
                hyperlink.setText(((Article) bookmark).getHyperlink());
                hyperlink.setOnAction(e -> {
                    try {
                        new ProcessBuilder("x-www-browser", hyperlink.getText()).start();
                    } catch (Exception ex) {
                        System.out.println("Exception in hyperlink.setOnAction: " 
                                + ex.getMessage());
                    }
                });
            }
            setGraphic(pane);
            updateListView(listView);
            
        }
    }
}
