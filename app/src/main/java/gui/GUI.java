
package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.util.converter.IntegerStringConverter;

import logic.Book;
import logic.BookmarkService;

import dao.Database;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import dao.BookmarkDao;

public class GUI extends Application {
    
    private final int windowWidth = 500;
    private final int windowHeight = 500;
    
    private Scene mainMenu;
    private Scene addRecommendation;
    private BorderPane layout;
    
    private Stage stage;
    private BookmarkDao service;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws SQLException {
        Database helper;
        if (System.getProperty("isTestEnvironment").equals("true")) {
            helper = new Database(":memory:");
        } else {
            helper = new Database("lukuvinkki.db");
        }
        try {
            service = new BookmarkService(helper);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void start(Stage stage) {
        
        this.stage = stage;
        this.stage.setTitle("Lukuvinkkikirjanpito");
        mainMenu = mainMenu();
        addRecommendation = addBookmark();
        
        this.stage.setScene(mainMenu);
        this.stage.show();
    }
    
    private Scene mainMenu() {
        
        layout = new BorderPane();

        Button addBookmark = new Button();
        addBookmark.setText("Lisää kirja");
        addBookmark.setId("add");
        
        HBox menu = new HBox(10);
        menu.getChildren().addAll(addBookmark);
        menu.setAlignment(Pos.CENTER);
        
        layout.setTop(menu);

        try {
            layout.setCenter(listBookmarks());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        addBookmark.setOnAction(e -> stage.setScene(addRecommendation));
        
        return new Scene(layout, windowWidth, windowHeight);
    }

    private ListView listBookmarks() throws SQLException {
        ObservableList<Book> bookList;
        bookList = FXCollections.observableArrayList(service.getAllBooks());
        ListView<Book> bookListView = new ListView<>(bookList);
        bookListView.setId("listview");
        bookListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                
                super.updateItem(book, empty);
                
                if (empty || book == null || book.getTitle() == null) {
                    setText(null);
                } else {
                    setText(book.toString());
                }
            }
        });
        return bookListView;
    }
    
    private Scene addBookmark() {
        VBox addLayout = new VBox(10);
        addLayout.setPadding(new Insets(10, 10, 10, 10));
        addLayout.setId("addview");
        
        Label createNewRecommendation = new Label("Luo uusi lukuvinkki");
        Button add = new Button("Lisää!");
        Label error = new Label("");
        add.setId("submit");
        Label titleLabel = new Label("Otsikko: ");
        TextField titleInput = new TextField();
        titleInput.setId("name");
        titleInput.setMaxWidth(350);
        Label authorLabel = new Label("Kirjailija: ");
        TextField authorInput = new TextField();
        authorInput.setId("author");
        authorInput.setMaxWidth(350);
        Label pageCountLabel = new Label("Sivumaara: ");
        TextField pageCountInput = new TextField();
        pageCountInput.setId("pageCount");
        pageCountInput.setTextFormatter((new TextFormatter<>(new IntegerStringConverter())));
        pageCountInput.setMaxWidth(100);
        Button back = new Button("Takaisin");
        back.setId("back");
        
        addLayout.getChildren().addAll(back, createNewRecommendation, titleLabel, titleInput, authorLabel,
            authorInput, pageCountLabel, pageCountInput, error, add);
        
        add.setOnAction(e -> {
            String title = titleInput.getText().trim();
            String author = authorInput.getText().trim();
            String pageCount = pageCountInput.getText().trim();
            if (title.isEmpty() || author.isEmpty() || pageCount.isEmpty()) {
                error.setText("Täytä kaikki tiedot.");
                return;
            }
            int pageCountInt = Integer.parseInt(pageCount);
            try {
                Book book = new Book(title, author, pageCountInt);
                if (!service.addBook(book)) {
                    error.setText("Samanniminen kirja on jo lisätty sovellukseen.");
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            returnToMainPage();
        });
        
        back.setOnAction(e -> {
            returnToMainPage();
        });
        
        return new Scene(addLayout, windowWidth, windowHeight);
    }
    
    private void returnToMainPage() {
        try {
            layout.setCenter(listBookmarks());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(mainMenu);
    }

}