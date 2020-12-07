
package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.util.ArrayList;
import logic.Article;
import logic.Bookmark;

public class GUI extends Application {
    
    private final int windowWidth = 500;
    private final int windowHeight = 500;
    
    private Scene mainMenu;
    private Scene addBookScene;
    private Scene addArticleScene;
    private BorderPane layout;
    private TextField searchField;
    
    private Stage stage;
    private BookmarkDao service;
    
    private ObservableList<Bookmark> bookmarkList;
    private ArrayList<Bookmark> fullBookmarkList;
    private ListView<Bookmark> listView;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws SQLException {
        Database helper;
        String isTest = System.getProperty("isTestEnvironment");
        if (isTest != null && isTest.equals("true")) {
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
    public void start(Stage stage) throws SQLException {
        
        this.stage = stage;
        this.stage.setTitle("Lukuvinkkikirjanpito");
        mainMenu = mainMenu();
        addBookScene = addBook();
        addArticleScene = addArticle();
        
        this.stage.setScene(mainMenu);
        this.stage.show();
    }
    
    private Scene mainMenu() throws SQLException {
        
        layout = new BorderPane();

        Button addBook = new Button();
        addBook.setText("Lisää kirja");
        addBook.setId("addbook");
        
        Button addArticle = new Button();
        addArticle.setText("Lisää artikkeli");
        addArticle.setId("addarticle");
        
        HBox menu = new HBox(10);
        menu.getChildren().addAll(addBook, addArticle);
        menu.setAlignment(Pos.CENTER);
        
        layout.setTop(menu);

        searchField = new TextField();
        listBookmarks();

        layout.setCenter(listView);

        searchField.setPromptText("Etsi nimellä");
        searchField.setId("search");
        menu.getChildren().add(searchField);
        menu.setSpacing(10);
        
        searchField.textProperty().addListener(obs -> {
            String filter = searchField.getText();
            bookmarkList.clear();
            bookmarkList.addAll(fullBookmarkList);
            if (filter != null && filter.length() > 0) {
                bookmarkList.removeIf(item -> !item.getTitle().toLowerCase().contains(filter.toLowerCase()));
            }
            listView.setItems(bookmarkList);
            listView.refresh();
            
        });
        
        addBook.setOnAction(e -> stage.setScene(addBookScene));
        addArticle.setOnAction(e -> stage.setScene(addArticleScene));

        return new Scene(layout, windowWidth, windowHeight);
    }

    private void listBookmarks() throws SQLException {
        fullBookmarkList = new ArrayList<>(service.getAllBookmarks());
        bookmarkList = FXCollections.observableArrayList(fullBookmarkList);
        String filter = searchField.getText();
        if (filter != null && filter.length() > 0) {
            bookmarkList.removeIf(item -> !item.getTitle().toLowerCase().contains(filter.toLowerCase()));
        }
        listView = new ListView<>(bookmarkList);
        listView.setId("listview");
        listView.setCellFactory(param -> new CustomCell(service, 
                listView, bookmarkList, fullBookmarkList));

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { //tuplaklikkaus
                Bookmark selected = listView.getSelectionModel()
                    .getSelectedItem();
                VBox layout = new BookmarkView(selected, backButton());
                this.stage.setScene(new Scene(layout, windowWidth, windowHeight));
            }
        });
    }
    
    private Scene addBook() {
        VBox addLayout = new VBox(10);
        addLayout.setPadding(new Insets(10, 10, 10, 10));
        addLayout.setId("addbookview");
        
        Label createNewRecommendation = new Label("Luo uusi lukuvinkki");
        Button add = new Button("Lisää!");
        Label error = new Label("");
        error.setId("errorMessage");
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
        Button back = backButton();
        
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
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            returnToMainPage();
        });
        
        return new Scene(addLayout, windowWidth, windowHeight);
    }
    
    private Scene addArticle() {
        VBox addLayout = new VBox(10);
        addLayout.setPadding(new Insets(10, 10, 10, 10));
        addLayout.setId("addarticleview");
        
        Label createNewRecommendation = new Label("Lisää artikkeli lukuvinkkeihin");
        Button add = new Button("Lisää!");
        Label error = new Label("");
        error.setId("errorMessage");
        add.setId("submit");
        Label titleLabel = new Label("Otsikko: ");
        TextField titleInput = new TextField();
        titleInput.setId("title");
        titleInput.setMaxWidth(350);
        Label hyperlinkLabel = new Label("Osoite: ");
        TextField hyperlinkInput = new TextField();
        hyperlinkInput.setId("hyperlink");
        hyperlinkInput.setMaxWidth(350);
        Button back = backButton();
        
        addLayout.getChildren().addAll(back, createNewRecommendation, titleLabel, titleInput, hyperlinkLabel,
            hyperlinkInput, error, add);
        
        add.setOnAction(e -> {
            String title = titleInput.getText().trim();
            String hyperlink = hyperlinkInput.getText().trim();
            if (title.isEmpty() || hyperlink.isEmpty()) {
                error.setText("Täytä kaikki tiedot.");
                return;
            }
            try {
                Article article = new Article(title, hyperlink);
                if (!service.addArticle(article)) {
                    return;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            returnToMainPage();
        });
        
        return new Scene(addLayout, windowWidth, windowHeight);
    }
    
    private void returnToMainPage() {
        try {
            listBookmarks();
        } catch (SQLException e) {
            System.out.println("returnToMainPage error: " + e.getMessage());
        }
        layout.setCenter(listView);

        stage.setScene(mainMenu);
    }

    private Button backButton() {
        Button back = new Button("Takaisin");
        back.setId("back");
        back.setOnAction(e -> {
            returnToMainPage();
        });
        return back;
    }

}