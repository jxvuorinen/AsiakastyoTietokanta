package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Asiakas;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Paaikkuna extends Application {

    @Override
    public void start(Stage ikkuna) throws Exception {

        //Luodaan ylävalikon napit
        Button etusivu = new Button("Etusivulle");
        Button lisaaAsiakas = new Button("Lisää asiakas");
        Button lisaaTyontekija = new Button("Lisää työntekijä");
        Button lisaaPalvelutapahtuma = new Button("Lisää palvelutapahtuma");
        //Button lisaaTyo = new Button("Lisää työskentelytiedot");

        HBox napit = new HBox();
        napit.setSpacing(10);
        napit.getChildren().addAll(etusivu, lisaaAsiakas, lisaaTyontekija, lisaaPalvelutapahtuma);

        BorderPane asettelu = new BorderPane();
        asettelu.setTop(napit);
        asettelu.setPrefSize(600, 400);

        //Näytetään aluksi aloitusnäkymä
        Label ohjeteksti = new Label("Tervetuloa hallinnoimaan asiakastyön tallennusjärjestelmää! \n"
                + "Valitse ylävalikosta, mitä tietoa haluat tallentaa.");
        asettelu.setCenter(ohjeteksti);

        Scene aloitusNakyma = new Scene(asettelu);
        ikkuna.setScene(aloitusNakyma);
        etusivu.setOnAction((event) -> asettelu.setCenter(ohjeteksti));

        //Luodaan asiakastallennusnäkymä ja tallennuspainike
        Label lbEtunimi = new Label("Etunimi: ");
        TextField tfEtunimi = new TextField();
        Label lbSukunimi = new Label("Sukunimi: ");
        TextField tfSukunimi = new TextField();
//        Label lbTunnus = new Label("Tunnus: ");
//        TextField tfTunnus = new TextField();
        Label lbSukupuoli = new Label("Valitse sukupuoli: ");
        ChoiceBox<String> sukupuoli = new ChoiceBox<>();
        sukupuoli.getItems().addAll("mies", "nainen", "muu");
        Label lbAsuinalue = new Label("Postinumeroalue: ");
        TextField tfAsuinalue = new TextField();

        Button talletaA = new Button("Talleta");

        GridPane AsLomakekentat = new GridPane();
        AsLomakekentat.add(lbEtunimi, 0, 0);
        AsLomakekentat.add(tfEtunimi, 1, 0);
        AsLomakekentat.add(lbSukunimi, 0, 1);
        AsLomakekentat.add(tfSukunimi, 1, 1);
//        AsLomakekentat.add(lbTunnus, 0, 2);
//        AsLomakekentat.add(tfTunnus, 1, 2);
        AsLomakekentat.add(lbSukupuoli, 0, 3);
        AsLomakekentat.add(sukupuoli, 1, 3);
        AsLomakekentat.add(lbAsuinalue, 0, 4);
        AsLomakekentat.add(tfAsuinalue, 1, 4);
        AsLomakekentat.add(talletaA, 1, 5);

        //Liitetään asiakastietonäkymä nappiin
        lisaaAsiakas.setOnAction((event) -> asettelu.setCenter(AsLomakekentat));

        //Luodaan työntekijäntallennusnäkymä
        Label lbEtunimiT = new Label("Etunimi: ");
        TextField tfEtunimiT = new TextField();
        Label lbSukunimiT = new Label("Sukunimi: ");
        TextField tfSukunimiT = new TextField();
        Label lbTunnusT = new Label("Työntekijänro: ");
        TextField tfTunnusT = new TextField();
        Label lbNimike = new Label("Työntekijänimike: ");
        TextField tfNimike = new TextField();
        Label lbYksikko = new Label("Yksikkö: ");
        ChoiceBox<String> cbYksikko = new ChoiceBox<>(FXCollections.observableArrayList("Asiakasohjaus", "Gerontologinen sosiaalityö"));
        Button talletaT = new Button("Talleta");

        GridPane TLomakekentat = new GridPane();
        TLomakekentat.add(lbEtunimiT, 0, 0);
        TLomakekentat.add(tfEtunimiT, 1, 0);
        TLomakekentat.add(lbSukunimiT, 0, 1);
        TLomakekentat.add(tfSukunimiT, 1, 1);
        TLomakekentat.add(lbTunnusT, 0, 2);
        TLomakekentat.add(tfTunnusT, 1, 2);
        TLomakekentat.add(lbNimike, 0, 3);
        TLomakekentat.add(tfNimike, 1, 3);
        TLomakekentat.add(lbYksikko, 0, 4);
        TLomakekentat.add(cbYksikko, 1, 4);
        TLomakekentat.add(talletaT, 1, 5);

        //Liitetään työntekijätietonäkymä nappiin
        lisaaTyontekija.setOnAction((event) -> asettelu.setCenter(TLomakekentat));

        //Luodaan palvelutapahtuman näkymä
        Label lbHakukentta = new Label("Asiakkaan tunniste: ");
        TextField tfHakukentta = new TextField();
        Label lbPalvelunlaji = new Label("Valitse palvelunlaji: ");
        ComboBox cbPalvelunlaji = new ComboBox();
        cbPalvelunlaji.getItems().addAll(
                "kotikäynti", "puhelu", "dokumentointi", "saatto", "toimistokäynti",
                "selvittely", "arviointiryhmän käsittely", "päätöksenteko",
                "verkostotyö", "muu");
        Label date = new Label("Valitse tapahtuman pvm: ");
        DatePicker paivyri = new DatePicker();
        Label time = new Label("Aloitusaika: (HH:MM) ");
        TextField tfTime = new TextField();
        Label kesto = new Label("Kesto: (HH:MM:SS) ");
        TextField tfKesto = new TextField();
        Label lbKuvaus = new Label("Tapahtuman kuvaus: ");
        TextField tfKuvaus = new TextField();
        Button talletaPT = new Button("Talleta");
        Button haeAsiakas = new Button("Hae asiakas");
        Label haettu = new Label("");
        Label tyontekijaId = new Label("Työntekijänrosi: ");
        TextField tfTyontekijaId = new TextField();

        GridPane palvelutapahtumakentat = new GridPane();
        palvelutapahtumakentat.add(lbHakukentta, 0, 0);
        palvelutapahtumakentat.add(tfHakukentta, 1, 0);
        palvelutapahtumakentat.add(haeAsiakas, 2, 0);
        palvelutapahtumakentat.add(lbPalvelunlaji, 0, 1);
        palvelutapahtumakentat.add(cbPalvelunlaji, 1, 1);
        palvelutapahtumakentat.add(date, 0, 2);
        palvelutapahtumakentat.add(paivyri, 1, 2);
        palvelutapahtumakentat.add(time, 0, 3);
        palvelutapahtumakentat.add(tfTime, 1, 3);
        palvelutapahtumakentat.add(kesto, 0, 4);
        palvelutapahtumakentat.add(tfKesto, 1, 4);
        palvelutapahtumakentat.add(lbKuvaus, 0, 5);
        palvelutapahtumakentat.add(tfKuvaus, 1, 5);
        palvelutapahtumakentat.add(tyontekijaId, 0, 6);
        palvelutapahtumakentat.add(tfTyontekijaId, 1, 6);
        palvelutapahtumakentat.add(talletaPT, 1, 7);

        lisaaPalvelutapahtuma.setOnAction((event) -> asettelu.setCenter(palvelutapahtumakentat));

        //Asiakkaan lisäys tallennuspainikkeeseen:
        talletaA.setOnAction((event) -> {
            lisaaAsiakas(tfEtunimi, tfSukunimi, sukupuoli, tfAsuinalue);
        });

        //haeAsiakas.setOnAction(event);
        ikkuna.show();
    }

    public static void main(String[] args) {
        launch(Paaikkuna.class);
    }

    private void lisaaAsiakas(TextField etunimi, TextField sukunimi, ChoiceBox sukupuoli, TextField asuinalue) {
        Tietovarasto rekisteri = new Tietovarasto();
        try {
            String etunimiA = etunimi.getText();
            String sukunimiA = sukunimi.getText();
            String sukupuoliA = sukupuoli.getValue().toString();
            //String henkilotunnus = tunnus.getText();
            String asuinalueA = asuinalue.getText();

            if (etunimiA.isEmpty() || sukunimiA.isEmpty() || asuinalueA.isEmpty() || sukupuoliA.isEmpty()) {
                Alert virheviesti = new Alert(AlertType.WARNING);
                virheviesti.setContentText("Tiedoissa puutteita");
                virheviesti.show();

            } else {
                Asiakas uusiAsiakas = new Asiakas(etunimiA, sukunimiA, sukupuoliA, asuinalueA);
                rekisteri.lisaaAsiakas(uusiAsiakas);
                Alert vahvistus = new Alert(AlertType.INFORMATION);
                vahvistus.setContentText("Tiedot tallennettu");
                vahvistus.show();
            }
        } catch (Exception e) {
            Alert error = new Alert(AlertType.ERROR);
            error.setContentText("Virhe! Asiakastietojen tallennus ei onnistunut, " + e);
            error.show();

        }

    }

}
