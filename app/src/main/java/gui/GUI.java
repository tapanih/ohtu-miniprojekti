
package gui;

import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import logiikka.Kirja;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Lisää kirja");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        VBox toinenAsettelu = new VBox();
        Label addingBooksTekstikomponentti = new Label("Syötä uusi kirja ja sen kirjailija muodossa: Kirja, Kirjailija");
        Button addingBooks = new Button("Lisää!");
        TextArea teksti = new TextArea("");
        Label tekstikomponentti1 = new Label("Kirja: ");
        Label tekstikomponentti2 = new Label("Kirjailija: ");
        
        teksti.textProperty().addListener((muutos, vanhaArvo, uusiArvo) -> {
            String[] palat = uusiArvo.split(", ");
            tekstikomponentti1.setText("Kirja: " + palat[0]);
            tekstikomponentti2.setText("Kirjailija: " + palat[1]);
        });
        
        toinenAsettelu.getChildren().addAll(addingBooksTekstikomponentti, teksti, tekstikomponentti1, tekstikomponentti2, addingBooks);
                
        VBox kolmasAsettelu = new VBox();
        Button addingBooks2 = new Button("Lisää toinen");
        Button menu = new Button("Takaisin alkuun");
        Label LisääToinenkirja = new Label("Alkuun");
        Label takaisinAlkuun = new Label("Takaisin alkuun");
        kolmasAsettelu.getChildren().addAll(LisääToinenkirja, addingBooks2, takaisinAlkuun, menu);

        Scene scene = new Scene(root, 300, 250);
        Scene toinenScene = new Scene(toinenAsettelu);
        Scene kolmasScene = new Scene(kolmasAsettelu);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(toinenScene);
            }
        });
        
        addingBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Kirja kirja = new Kirja(tekstikomponentti1.getText(), tekstikomponentti1.getText());
                teksti.clear();
                tekstikomponentti1.setText("Kirja: ");
                tekstikomponentti2.setText("Kirjailija: ");
                primaryStage.setScene(kolmasScene);
            }
        });

        addingBooks2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(toinenScene);
            }
        });
        
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}