
package gui;

import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import logiikka.Kirja;

public class GUI extends Application {
    
    private int ikkunanLeveys = 300;
    private int ikkunanKorkeus = 300;

    @Override
    public void start(Stage nayttamo) {
        
        Button lisaaLukuvinkki = new Button();
        lisaaLukuvinkki.setText("Lisää kirja");
        
        VBox valikko = new VBox(10);
        valikko.getChildren().add(lisaaLukuvinkki);
        valikko.setAlignment(Pos.CENTER);
        
        Scene paavalikko = new Scene(valikko, ikkunanLeveys, ikkunanKorkeus);
        
        VBox lisaaAsettelu = new VBox(10);
        lisaaAsettelu.setPadding(new Insets(10,10,10,10));
        
        Label luoUusiLukuvinkki = new Label("Luo uusi lukuvinkki");
        Button lisaa = new Button("Lisää!");
        Label otsikko = new Label("Otsikko: ");
        TextField otsikkoInput = new TextField();
        Label kirjailija = new Label("Kirjailija: ");
        TextField kirjailijaInput = new TextField();
        
        lisaaAsettelu.getChildren().addAll(luoUusiLukuvinkki, otsikko, otsikkoInput, kirjailija, kirjailijaInput, lisaa);
        
        Scene lukuvinkinLisays = new Scene(lisaaAsettelu, ikkunanLeveys, ikkunanKorkeus);
        
        lisaaLukuvinkki.setOnAction(e -> nayttamo.setScene(lukuvinkinLisays));
        
        lisaa.setOnAction(e -> nayttamo.setScene(paavalikko));
        
        nayttamo.setTitle("Lukuvinkkikirjanpito");
        nayttamo.setScene(paavalikko);
        nayttamo.show();
    }

}