package gui;

import dao.BookmarkDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.Article;
import logic.Book;
import logic.Bookmark;

/**
 * Lukuvinkin oma näkymä.
 */
public class BookmarkView extends VBox {
    private ObservableList<String> tags;
    private BookmarkDao service;

    public BookmarkView(Bookmark bookmark, Button back, BookmarkDao service) {
        super(10);
        this.service = service;
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setId("bookmarkview");

        Label header = new Label("Lukuvinkin tiedot:");
        header.setStyle("-fx-font-weight: bold");
        Label titleLabel = new Label("Otsikko: " + bookmark.getTitle());
        this.getChildren().addAll(back, header, titleLabel);

        // lisätään eri lukuvinkeille uniikit tiedot
        if (bookmark.getClass().getName().equals(Article.class.getName())) {
            articleView((Article) bookmark);
        } else {
            bookView((Book) bookmark);
        }
        HBox addTagBox = new HBox();
        TextField addTagField = new TextField();
        Button addTagButton = new Button("Lisää tagi");
        addTagButton.setOnAction(event -> {
            String newTag = addTagField.getText();
            if (tags.stream().noneMatch(tag -> tag.equals(newTag))) {
                // TODO: tietokannan päivitys
                tags.add(newTag);
            }
        });
        addTagBox.getChildren().addAll(addTagField, addTagButton);
        this.getChildren().addAll(addTagBox, tagListView(bookmark));
    }

    private ListView<String> tagListView(Bookmark bookmark) {
        tags = FXCollections.observableArrayList(bookmark.getTags());
        ListView<String> listView = new ListView<>(tags);
        listView.setId("taglistview");
        listView.setCellFactory(param -> new TagCell(bookmark, listView, tags));
        return listView;
    }

    private void bookView(Book bookmark) {
        Label authorLabel = new Label("Kirjailija: " + bookmark.getAuthor());
        this.getChildren().add(authorLabel);
    }

    private void articleView(Article bookmark) {
        Hyperlink hyperlink = new Hyperlink(bookmark.getHyperlink());
        hyperlink.setOnAction(e -> {
            try {
                new ProcessBuilder("x-www-browser", hyperlink.getText()).start();
            } catch (Exception ex) {
                System.out.println("Exception in hyperlink.setOnAction: "
                    + ex.getMessage());
            }
        });
        this.getChildren().add(hyperlink);
    }
}
