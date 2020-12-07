package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.Article;
import logic.Book;
import logic.Bookmark;

/**
 * Lukuvinkin oma näkymä.
 */
public class BookmarkView extends VBox {

    public BookmarkView(Bookmark bookmark, Button back) {
        super(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setId("bookmarkview");

        Label header = new Label("Lukuvinkin tiedot:");
        header.setStyle("-fx-font-weight: bold");
        Label titleLabel = new Label("Otsikko: " + bookmark.getTitle());
        this.getChildren().addAll(header, titleLabel);

        // lisätään eri lukuvinkeille uniikit tiedot
        if (bookmark.getClass().getName().equals(Article.class.getName())) {
            articleView((Article) bookmark);
        } else {
            bookView((Book) bookmark);
        }

        this.getChildren().add(back);
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
