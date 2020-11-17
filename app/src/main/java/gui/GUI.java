
package gui;

import dao.lukuvinkkiDatabase;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import dao.lukuvinkkiDatabase;
import logiikka.lukuvinkkiService;

public class GUI extends Application {
    
    private int ikkunanLeveys = 500;
    private int ikkunanKorkeus = 500;
    
    private Scene paavalikko;
    private Scene lukuvinkinLisays;
    
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
        
        nayttamo.setScene(paavalikko);
        nayttamo.show();
    }
    
    private Scene paavalikko() {
        
        Button lisaaLukuvinkki = new Button();
        lisaaLukuvinkki.setText("Lisää kirja");
        
        VBox valikko = new VBox(10);
        valikko.getChildren().add(lisaaLukuvinkki);
        valikko.setAlignment(Pos.CENTER);
        
        lisaaLukuvinkki.setOnAction(e -> nayttamo.setScene(lukuvinkinLisays));
        
        return new Scene(valikko, ikkunanLeveys, ikkunanKorkeus);
    }
    
    private Scene lukuvinkinLisays() {
        VBox lisaaAsettelu = new VBox(10);
        lisaaAsettelu.setPadding(new Insets(10,10,10,10));
        
        Label luoUusiLukuvinkki = new Label("Luo uusi lukuvinkki");
        Button lisaa = new Button("Lisää!");
        Label otsikko = new Label("Otsikko: ");
        TextField otsikkoInput = new TextField();
        otsikkoInput.setMaxWidth(350);
        Label kirjailija = new Label("Kirjailija: ");
        TextField kirjailijaInput = new TextField();
        kirjailijaInput.setMaxWidth(350);
        
        lisaaAsettelu.getChildren().addAll(luoUusiLukuvinkki, otsikko, otsikkoInput, kirjailija, kirjailijaInput, lisaa);
        
        lisaa.setOnAction(e -> nayttamo.setScene(paavalikko));
        
        return new Scene(lisaaAsettelu, ikkunanLeveys, ikkunanKorkeus);
    }

}