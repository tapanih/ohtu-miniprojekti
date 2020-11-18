
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

import logiikka.Kirja;
import logiikka.LukuvinkkiService;

import dao.LukuvinkkiDAO;
import dao.DatabaseHelper;
import dao.Database;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GUI extends Application {
    
    private int ikkunanLeveys = 500;
    private int ikkunanKorkeus = 500;
    
    private Scene paavalikko;
    private Scene lukuvinkinLisays;
    private BorderPane asettelu;
    
    private Stage nayttamo;
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
        
        nayttamo = stage;
        nayttamo.setTitle("Lukuvinkkikirjanpito");
        paavalikko = paavalikko();
        lukuvinkinLisays = lukuvinkinLisays();
        
        nayttamo.setScene(paavalikko);
        nayttamo.show();
    }
    
    private Scene paavalikko() {
        
        asettelu = new BorderPane();

        Button lisaaLukuvinkki = new Button();
        lisaaLukuvinkki.setText("Lisää kirja");

        
        HBox valikko = new HBox(10);
        valikko.getChildren().addAll(lisaaLukuvinkki);
        valikko.setAlignment(Pos.CENTER);
        
        asettelu.setTop(valikko);

        try {
            asettelu.setCenter(lukuvinkkienListaus());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        lisaaLukuvinkki.setOnAction(e -> nayttamo.setScene(lukuvinkinLisays));
        
        return new Scene(asettelu, ikkunanLeveys, ikkunanKorkeus);
    }

    private ListView lukuvinkkienListaus() throws SQLException {
        ObservableList<Kirja> kirjaLista = FXCollections.observableArrayList(service.getAllBooks());
        ListView<Kirja> kirjaListaus = new ListView<>(kirjaLista);
        kirjaListaus.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Kirja kirja, boolean tyhja) {
                
                super.updateItem(kirja, tyhja);
                
                if (tyhja || kirja == null || kirja.getOtsikko() == null) {
                    setText(null);
                } else {
                    setText(kirja.getOtsikko());
                }
            }
        });
        return kirjaListaus;
    }
    
    private Scene lukuvinkinLisays() {
        VBox lisaaAsettelu = new VBox(10);
        lisaaAsettelu.setPadding(new Insets(10, 10, 10, 10));
        
        Label luoUusiLukuvinkki = new Label("Luo uusi lukuvinkki");
        Button lisaa = new Button("Lisää!");
        Label otsikkoLabel = new Label("Otsikko: ");
        TextField otsikkoInput = new TextField();
        otsikkoInput.setMaxWidth(350);
        Label kirjailijaLabel = new Label("Kirjailija: ");
        TextField kirjailijaInput = new TextField();
        kirjailijaInput.setMaxWidth(350);
        Label sivumaaraLabel = new Label("Sivumaara: ");
        TextField sivumaaraInput = new TextField();
        sivumaaraInput.setTextFormatter((new TextFormatter<>(new IntegerStringConverter())));
        sivumaaraInput.setMaxWidth(100);
        Button takaisin = new Button("Takaisin");
        
        lisaaAsettelu.getChildren().addAll(takaisin, luoUusiLukuvinkki, otsikkoLabel, otsikkoInput, kirjailijaLabel,
            kirjailijaInput, sivumaaraLabel, sivumaaraInput, lisaa);
        
        lisaa.setOnAction(e -> {
            String otsikko = otsikkoInput.getText().trim();
            String kirjailija = kirjailijaInput.getText().trim();
            int sivumaara = Integer.parseInt(sivumaaraInput.getText().trim());
            try {
                Kirja kirja = new Kirja(otsikko, kirjailija, sivumaara);
                service.addBook(kirja);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            returnToMainPage();
        });
        
        takaisin.setOnAction(e -> {
            returnToMainPage();
        });
        
        return new Scene(lisaaAsettelu, ikkunanLeveys, ikkunanKorkeus);
    }
    
    private void returnToMainPage() {
        try {
            asettelu.setCenter(lukuvinkkienListaus());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nayttamo.setScene(paavalikko);
    }

}