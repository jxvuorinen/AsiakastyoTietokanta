package Kayttoliittyma;

import Tietokanta.Yhteydenhallinta;
import data.Kayttaja;
import java.sql.Connection;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Paaikkuna extends Application {

    private Kayttaja kayttaja;
    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/asiakastyo";

    @Override
    public void start(Stage ikkuna) throws Exception {
        ikkuna.setTitle("Asiakastyön tilastointi");

        //Luodaan ylävalikon välilehdet
        HBox napit = new HBox();
        napit.getStyleClass().add("napit");
        napit.setSpacing(10);

        Button etusivu = new Button("Etusivulle");
        Button lisaaAsiakas = new Button("Lisää asiakas");
        Button lisaaTyontekija = new Button("Lisää työntekijä");
        Button haku = new Button("Haku");
        Button lisaaPalvelutapahtuma = new Button("Lisää palvelutapahtuma");

        napit.getChildren().addAll(etusivu, lisaaAsiakas, lisaaTyontekija, lisaaPalvelutapahtuma, haku);

        //BorderPanen yläosaan napit
        BorderPane asettelu = new BorderPane();
        asettelu.getStyleClass().add("asettelu");
        asettelu.setTop(napit);
        asettelu.setPrefSize(960, 515);

        //Näytetään aluksi aloitusnäkymä (uusi BorderPane sisäikkuna) asettelu-BorderPanen keskellä
        BorderPane sisaikkuna = new BorderPane();
        sisaikkuna.getStyleClass().add("lomake");
        sisaikkuna.styleProperty().bind(Bindings.concat("-fx-font-size: 15px;"));

        Text ohjeteksti = new Text("Tervetuloa tallentamaan asiakastietoja! \n"
                + "Valitse ylävalikosta, mitä tietoa haluat tallentaa. \n Päästäksesi syöttämään tietoja "
                + "sinun tulee antaa käyttäjätunnus ja salasana alla oleviin kenttiin.\n");
        ohjeteksti.setFont(Font.font("Calibri", 20));
        ohjeteksti.setTextAlignment(TextAlignment.CENTER);
        sisaikkuna.setTop(ohjeteksti);

        //kirjautumisikkuna
        GridPane kirjaudu = new GridPane();
        Label lbKayttajatunnus = new Label("Käyttäjätunnus: \t");
        TextField tfTunnus = new TextField();
        Label salasana = new Label("Salasana: \t");
        PasswordField tfSalasana = new PasswordField();
        Button btKirjaudu = new Button("Kirjaudu");

        kirjaudu.add(lbKayttajatunnus, 0, 1);
        kirjaudu.add(tfTunnus, 1, 1);
        kirjaudu.add(salasana, 0, 2);
        kirjaudu.add(tfSalasana, 1, 2);
        kirjaudu.add(btKirjaudu, 1, 3);
        Label kirjautunutKayttaja = new Label("");

        sisaikkuna.setCenter(kirjaudu);
        asettelu.setBottom(kirjautunutKayttaja);

        Image slasi = new Image(getClass().getResourceAsStream("magnifying-glass.png"));
        asettelu.setRight(new ImageView(slasi));

        asettelu.setCenter(sisaikkuna);

        Scene aloitusNakyma = new Scene(asettelu);
        aloitusNakyma.getStylesheets().add("/Kayttoliittyma/styles.css");
        ikkuna.setScene(aloitusNakyma);
        etusivu.setOnAction((event) -> asettelu.setCenter(sisaikkuna));

        //Kirjaudu-napin toiminto
        btKirjaudu.setOnAction((event) -> {
            String kayttajatunnus = tfTunnus.getText();
            String kayttajansalasana = tfSalasana.getText();
            this.kayttaja = new Kayttaja(kayttajatunnus, kayttajansalasana);
            try {
                Connection yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, kayttajansalasana);
                if (yhteys != null) {
                    Alert vahvistus = new Alert(Alert.AlertType.INFORMATION);
                    vahvistus.setContentText("Kirjautuminen onnistui!");
                    vahvistus.show();
                    Yhteydenhallinta.suljeYhteys(yhteys);
                    kirjautunutKayttaja.setText("Kirjautunut käyttäjä: " + kayttajatunnus);
                }
            } catch (Exception ex) {
                Alert vahvistus = new Alert(Alert.AlertType.INFORMATION);
                vahvistus.setContentText("Kirjautuminen epäonnistui!");
                kirjautunutKayttaja.setText("");
                vahvistus.show();
            }
            tfTunnus.clear();
            tfSalasana.clear();

        });

        //Liitetään asiakastietonäkymä välilehden nappiin
        lisaaAsiakas.setOnAction((event) -> {
            AsiakkaanLisays asiakasnakyma = new AsiakkaanLisays(asettelu, this.kayttaja);
        });

        //Liitetään työntekijänlisäysnäkymä välilehden nappiin
        lisaaTyontekija.setOnAction((event) -> {
            TyontekijanLisays tyontekijanakyma = new TyontekijanLisays(asettelu, this.kayttaja);
        });

        haku.setOnAction((event) -> {
            Haku hakunakyma = new Haku(asettelu, this.kayttaja);
        });

        lisaaPalvelutapahtuma.setOnAction((event) -> {
            PalvelutapahtumanLisays palvelutapahtumanakyma = new PalvelutapahtumanLisays(asettelu, this.kayttaja);
        });

        ikkuna.show();
    }

    public static void main(String[] args) {
        launch(Paaikkuna.class);
    }
}
