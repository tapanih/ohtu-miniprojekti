
package gui;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.lukuvinkkiDatabase;
import java.sql.SQLException;

import javafx.util.converter.IntegerStringConverter;
import logiikka.Kirja;
import logiikka.lukuvinkkiService;

public class GUI extends Application {
    
    private int ikkunanLeveys = 500;
    private int ikkunanKorkeus = 500;
    
    private Scene paavalikko;
    private Scene lukuvinkinLisays;
    private Scene lukuvinkkienListaus;
    
    private Stage nayttamo;
    private lukuvinkkiDatabase db;
    private lukuvinkkiService service;

    @Override
    public void init() throws SQLException, Exception {
        db = new lukuvinkkiDatabase("lukuvinkki.db");
        service = new lukuvinkkiService(db);

    }
    @Override
    public void start(Stage stage) {
        
        nayttamo = stage;
        nayttamo.setTitle("Lukuvinkkikirjanpito");
        paavalikko = paavalikko();
        lukuvinkinLisays = lukuvinkinLisays();
        lukuvinkkienListaus = lukuvinkkienListaus();
        
        nayttamo.setScene(paavalikko);
        nayttamo.show();
    }
    
    private Scene paavalikko() {

        Button listaaLukuvinkit = new Button();
        listaaLukuvinkit.setText("Listaa kirjat");

        Button lisaaLukuvinkki = new Button();
        lisaaLukuvinkki.setText("Lisää kirja");

        
        VBox valikko = new VBox(10);
        valikko.getChildren().addAll(listaaLukuvinkit, lisaaLukuvinkki);
        valikko.setAlignment(Pos.CENTER);

        listaaLukuvinkit.setOnAction(e -> nayttamo.setScene(lukuvinkkienListaus));
        lisaaLukuvinkki.setOnAction(e -> nayttamo.setScene(lukuvinkinLisays));
        
        return new Scene(valikko, ikkunanLeveys, ikkunanKorkeus);
    }

    private Scene lukuvinkkienListaus() {
        ObservableList<Kirja> kirjaLista = FXCollections.observableArrayList(service.getBooks());
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
        return new Scene(kirjaListaus);
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
        
        lisaaAsettelu.getChildren().addAll(luoUusiLukuvinkki, otsikkoLabel, otsikkoInput, kirjailijaLabel,
            kirjailijaInput, sivumaaraLabel, sivumaaraInput, lisaa);
        
        lisaa.setOnAction(e -> {
            String otsikko = otsikkoInput.getText();
            String kirjailija = kirjailijaInput.getText();
            int sivumaara = Integer.parseInt(sivumaaraInput.getText());
            try {
                service.addBook(otsikko, kirjailija, sivumaara);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            nayttamo.setScene(paavalikko);
        });
        
        return new Scene(lisaaAsettelu, ikkunanLeveys, ikkunanKorkeus);
    }

}