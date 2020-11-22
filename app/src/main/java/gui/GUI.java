
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
import logic.LukuvinkkiService;

import dao.LukuvinkkiDAO;
import dao.DatabaseHelper;
import dao.Database;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GUI extends Application {
    
    private int windowWidth = 500;
    private int windowHeight = 500;
    
    private Scene mainMenu;
    private Scene addRecommendation;
    private BorderPane layout;
    
    private Stage stage;
    private LukuvinkkiDAO service;

    @Override
    public void init() throws SQLException, Exception {
        Database db = new Database("lukuvinkki.db");
        db.initializeDatabase();
        DatabaseHelper dbDAO = new DatabaseHelper(db);
        service = new LukuvinkkiService(dbDAO);

    }
    
    @Override
    public void start(Stage stage) throws SQLException {
        
        this.stage = stage;
        this.stage.setTitle("Lukuvinkkikirjanpito");
        mainMenu = mainMenu();
        addRecommendation = addRecommendation();
        
        this.stage.setScene(mainMenu);
        this.stage.show();
    }
    
    private Scene mainMenu() {
        
        layout = new BorderPane();

        Button addReco = new Button();
        addReco.setText("Lisää kirja");

        
        HBox menu = new HBox(10);
        menu.getChildren().addAll(addReco);
        menu.setAlignment(Pos.CENTER);
        
        layout.setTop(menu);

        try {
            layout.setCenter(listRecommendations());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        addReco.setOnAction(e -> stage.setScene(addRecommendation));
        
        return new Scene(layout, windowWidth, windowHeight);
    }

    private ListView listRecommendations() throws SQLException {
        ObservableList<Book> bookList = FXCollections.observableArrayList(service.getAllBooks());
        ListView<Book> bookListView = new ListView<>(bookList);
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
    
    private Scene addRecommendation() {
        VBox addLayout = new VBox(10);
        addLayout.setPadding(new Insets(10, 10, 10, 10));
        
        Label createNewRecommendation = new Label("Luo uusi lukuvinkki");
        Button add = new Button("Lisää!");
        Label titleLabel = new Label("Otsikko: ");
        TextField titleInput = new TextField();
        titleInput.setMaxWidth(350);
        Label authorLabel = new Label("Kirjailija: ");
        TextField authorInput = new TextField();
        authorInput.setMaxWidth(350);
        Label pageCountLabel = new Label("Sivumaara: ");
        TextField pageCountInput = new TextField();
        pageCountInput.setTextFormatter((new TextFormatter<>(new IntegerStringConverter())));
        pageCountInput.setMaxWidth(100);
        Button back = new Button("Takaisin");
        
        addLayout.getChildren().addAll(back, createNewRecommendation, titleLabel, titleInput, authorLabel,
            authorInput, pageCountLabel, pageCountInput, add);
        
        add.setOnAction(e -> {
            String title = titleInput.getText().trim();
            String author = authorInput.getText().trim();
            int pageCount = Integer.parseInt(pageCountInput.getText().trim());
            try {
                Book book = new Book(title, author, pageCount);
                service.addBook(book);
            } catch (Exception exception) {
                exception.printStackTrace();
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
            layout.setCenter(listRecommendations());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        stage.setScene(mainMenu);
    }

}