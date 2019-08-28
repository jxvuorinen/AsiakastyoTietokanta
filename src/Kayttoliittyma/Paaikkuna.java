package Kayttoliittyma;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Paaikkuna extends Application {

    @Override
    public void start(Stage ikkuna) throws Exception {
        ikkuna.setTitle("Asiakastyön tilastointi");
        
        //Luodaan ylävalikon välilehdet
        HBox napit = new HBox();
        napit.setSpacing(10);

        Button etusivu = new Button("Etusivulle");
        Button lisaaAsiakas = new Button("Lisää asiakas");
        Button lisaaTyontekija = new Button("Lisää työntekijä");
        Button haku = new Button("Haku");
        Button lisaaPalvelutapahtuma = new Button("Lisää palvelutapahtuma");

        napit.getChildren().addAll(etusivu, lisaaAsiakas, lisaaTyontekija, lisaaPalvelutapahtuma, haku);

        //BorderPanen yläosaan napit
        BorderPane asettelu = new BorderPane();
        asettelu.setTop(napit);
        asettelu.setPrefSize(900, 400);

        //Näytetään aluksi aloitusnäkymä BorderPanen keskellä
        Text ohjeteksti = new Text("Tervetuloa hallinnoimaan asiakastyön tallennusjärjestelmää! \n"
                + "Valitse ylävalikosta, mitä tietoa haluat tallentaa.");
        ohjeteksti.setFont(Font.font ("Calibri", 20));
        asettelu.setCenter(ohjeteksti);

        Scene aloitusNakyma = new Scene(asettelu);
        ikkuna.setScene(aloitusNakyma);
        etusivu.setOnAction((event) -> asettelu.setCenter(ohjeteksti));

        //Liitetään asiakastietonäkymä välilehden nappiin
        lisaaAsiakas.setOnAction((event) -> {
            AsiakkaanLisays asiakasnakyma = new AsiakkaanLisays(asettelu);
        });

        //Liitetään työntekijänlisäysnäkymä välilehden nappiin
        lisaaTyontekija.setOnAction((event) -> {
            TyontekijanLisays tyontekijanakyma = new TyontekijanLisays(asettelu);
        });
        
        haku.setOnAction((event) -> {
            Haku hakunakyma = new Haku(asettelu);
        });

        lisaaPalvelutapahtuma.setOnAction((event) -> {
            PalvelutapahtumanLisays palvelutapahtumanakyma = new PalvelutapahtumanLisays(asettelu);
        });

        ikkuna.show();
    }

    public static void main(String[] args) {
        launch(Paaikkuna.class);
    }
}
