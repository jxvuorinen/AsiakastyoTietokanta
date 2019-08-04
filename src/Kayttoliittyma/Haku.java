package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Asiakas;
import data.Kysely;
import java.time.LocalTime;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Haku {

    private BorderPane nakyma;
    Text lbAsiakasHaku = new Text("Hae asiakkaan tiedot: ");
    TextField tfHetu1 = new TextField();
    Button haeAsiakas = new Button("Hae");
    Text lbTapahtumaHaku = new Text("Hae asiakkaan saamat palvelut henkilötunnuksella: ");
    TextField tfHetu2 = new TextField();
    Button haeTapahtumat = new Button("Hae");
    Text lbTyontekijaHaku = new Text("Hae työntekijän tiedot työntekijänumerolla: ");
    TextField tfTyontekijaNro = new TextField();
    Button haeTyontekija = new Button("Hae");
    Text lbYksikonPalvelut = new Text("Hae yksikön palvelun määrä ja kesto aikavälillä: ");
    ComboBox cbYksikko = new ComboBox();
    ComboBox cbPalvelunlaji = new ComboBox();
    DatePicker paivyriAlkaen = new DatePicker();
    DatePicker paivyriPaattyen = new DatePicker();

    TextArea hakutulos = new TextArea("Hakutulokset");
    GridPane hakukentat = new GridPane();
    BorderPane uusiPane = new BorderPane();

    //Konstruktori
    public Haku(BorderPane nakyma) {
        this.nakyma = nakyma;
        asetteleKomponentit();
    }

    private void asetteleKomponentit() {
        hakukentat.add(lbAsiakasHaku, 0, 0);
        hakukentat.add(tfHetu1, 1, 0);
        hakukentat.add(haeAsiakas, 2, 0);
        hakukentat.add(lbTapahtumaHaku, 0, 1);
        hakukentat.add(tfHetu2, 1, 1);
        hakukentat.add(haeTapahtumat, 2, 1);
        hakukentat.add(lbTyontekijaHaku, 0, 2);
        hakukentat.add(tfTyontekijaNro, 1, 2);
        hakukentat.add(haeTyontekija, 2, 2);
        hakukentat.add(lbYksikonPalvelut, 0, 3);
        hakukentat.add(cbYksikko, 1, 3);
        hakukentat.add(cbPalvelunlaji, 2, 3);
        hakukentat.add(paivyriAlkaen, 3, 3);
        hakukentat.add(paivyriPaattyen, 4, 3);
        
        hakukentat.setHgap(5);
        hakukentat.setVgap(5);
        hakukentat.setPadding(new Insets(10, 10, 10, 10));
        
        cbPalvelunlaji.getItems().addAll(
                "kotikäynti", "puhelu", "dokumentointi", "saatto", "toimistokäynti",
                "selvittely", "arviointiryhmän käsittely", "päätöksenteko",
                "verkostotyö", "muu");
        cbYksikko.getItems().addAll("Asiakasohjaus", "Gerontologinen sosiaalityö");
        cbPalvelunlaji.setPromptText("Valitse palvelu");
        cbYksikko.setPromptText("Valitse yksikkö");
        paivyriAlkaen.setPromptText("Alkaen pvm");
        paivyriPaattyen.setPromptText("Päättyen pvm");
        
        uusiPane.setTop(hakukentat);
        uusiPane.setBottom(hakutulos);

        this.nakyma.setCenter(uusiPane);
        hakutulos.setWrapText(true);
        hakutulos.setEditable(false);
        hakutulos.setPrefHeight(400);
        hakukentat.setAlignment(Pos.TOP_CENTER);

        //Tallennuspainikkeen toiminto:
        haeTapahtumat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tietovarasto rekisteri = new Tietovarasto();
                try {
                    String asiakasId = tfHetu2.getText();
                    if (asiakasId.isEmpty()) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Tiedoissa puutteita");
                        virheviesti.show();

                    } else {
                        Asiakas haettu = rekisteri.haeAsiakas(asiakasId);
                        StringBuilder msg = new StringBuilder();
                        LocalTime kestoYhteensa = LocalTime.of(0, 0);
                        if (haettu == null) {
                            Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                            virheviesti.setContentText("Asiakasta ei löytynyt");
                            virheviesti.show();
                        } else {
                            List<Kysely> kyselytulos = rekisteri.haeTapahtumat(haettu);
                            for (Kysely tapahtuma : kyselytulos) {
                                msg.append(tapahtuma.toString());
                                msg.append("\n" + "" + "\n");
                                kestoYhteensa = kestoYhteensa.plusHours(tapahtuma.getKesto().getHour()).plusMinutes(tapahtuma.getKesto().getMinute());
                            }
                            if (msg.length() == 0) {
                                hakutulos.setText("Ei tapahtumia");
                            } else {
                                String maara = Integer.toString(kyselytulos.size());
                                msg.append("Yhteensä: ").append(maara).append(" tapahtumaa\n").append("Tapahtumien kesto yhteensä: ").append(kestoYhteensa);
                                hakutulos.setText(msg.toString());
                            }

                        }
                    }
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Virhe" + e);
                    error.show();
                }
            }
        });
    }

}
