package gui;

import dao.BookmarkDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Article;
import logic.Book;
import logic.Bookmark;
import logic.BookmarkType;


public class CustomCell extends ListCell<Bookmark> {
    private final Button detailsButton;
    private final Button deleteButton;
    private Bookmark bookmark;
    private final Label bookmarkLabel;
    private final Hyperlink hyperlink;
    private final HBox pane;
    private final ListView<Bookmark> listView;
    private final ObservableList<Bookmark> bookmarkList;
    private final ArrayList<Bookmark> fullBookmarkList;
 
    
    public CustomCell(Stage stage, BookmarkDao service, ListView<Bookmark> listView,
                      ObservableList<Bookmark> bookmarkList, ArrayList<Bookmark> fullBookmarkList, Button back) {
        super();
        this.listView = listView;
        this.bookmarkList = bookmarkList;
        this.fullBookmarkList = fullBookmarkList;

        detailsButton = new Button("Tiedot");
        deleteButton = new Button("Poista");
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Haluatko varmasti poistaa kyseisen lukuvinkin?");

        detailsButton.setOnMouseClicked(event -> {
            VBox layout = new BookmarkView(bookmark, back, service);
            stage.setScene(new Scene(layout, stage.getWidth(), stage.getHeight()));
        });

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
        
        pane = new HBox();
        pane.setSpacing(10);
        pane.setMaxWidth(880);

        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bookmarkLabel = new Label();
        hyperlink = new Hyperlink();
        detailsButton.setMinWidth(60);
        deleteButton.setMinWidth(60);
        pane.getChildren().addAll(bookmarkLabel, hyperlink, spacer, detailsButton, deleteButton);
    }
    
    @Override
    public void updateItem(Bookmark bookmark, boolean empty) {
        super.updateItem(bookmark, empty);
        pane.getChildren().remove(hyperlink);
        pane.getChildren().remove(detailsButton);
        pane.getChildren().remove(deleteButton);
        this.bookmark = bookmark;
        if (empty || bookmark == null || bookmark.getTitle() == null) {
            setText(null);
            setGraphic(null);
            updateListView(listView);
        } else {
            deleteButton.setId("delete" + bookmark.getId());
            detailsButton.setId("item" + bookmark.getId());
            if (bookmark.getType() == BookmarkType.BOOK) {
                String title = bookmark.getTitle() + ", " + ((Book) bookmark).getAuthor() + ", " + 
                        ((Book) bookmark).getPages() + " sivua";
                bookmarkLabel.setText(title);
            } else {
                pane.getChildren().add(hyperlink);
                String title = ((Article) bookmark).getTitle();
                bookmarkLabel.setText(title);
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
            pane.getChildren().add(detailsButton);
            pane.getChildren().add(deleteButton);
            setGraphic(pane);
            updateListView(listView);
            
        }
    }
}
