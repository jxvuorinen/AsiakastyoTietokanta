package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Palvelutapahtuma;
import data.Tyoskentely;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PalvelutapahtumanLisays {
    
    private BorderPane nakyma;
    Label lbHetu = new Label("Asiakkaan henkilötunnus: ");
    TextField tfHetu = new TextField();
    Label lbPalvelunlaji = new Label("Valitse palvelunlaji: ");
    ComboBox cbPalvelunlaji = new ComboBox();    
    Label date = new Label("Valitse tapahtuman pvm: ");
    DatePicker paivyri = new DatePicker();
    Label time = new Label("Aloitusaika: (HH:MM) ");
    TextField tfTime = new TextField();
    Label kesto = new Label("Kesto: (HH:MM) ");
    TextField tfKesto = new TextField();
    Label lbKuvaus = new Label("Tapahtuman kuvaus: ");
    TextField tfKuvaus = new TextField();
    Button talleta = new Button("Talleta");
    Label tyontekijaId = new Label("Työntekijänrosi: ");
    TextField tfTyontekijaId = new TextField();
    
    GridPane palvelutapahtumakentat = new GridPane();

    //Konstruktori
    public PalvelutapahtumanLisays(BorderPane nakyma) {
        this.nakyma = nakyma;
        asetteleKomponentit();
    }
    
    private void asetteleKomponentit() {
        cbPalvelunlaji.getItems().addAll(
                "kotikäynti", "puhelu", "dokumentointi", "saatto", "toimistokäynti",
                "selvittely", "arviointiryhmän käsittely", "päätöksenteko",
                "verkostotyö", "muu");
        palvelutapahtumakentat.add(lbHetu, 0, 0);
        palvelutapahtumakentat.add(tfHetu, 1, 0);
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
        palvelutapahtumakentat.add(talleta, 1, 7);
        
        this.nakyma.setCenter(palvelutapahtumakentat);
        
        //Tallennuspainikkeen toiminto:
        talleta.setOnAction((event) -> {
            Tietovarasto rekisteri = new Tietovarasto();
            try {
                String asiakasId = tfHetu.getText();
                String palvelunLaji = cbPalvelunlaji.getValue().toString();
                LocalTime klo = LocalTime.parse(tfTime.getText());
                LocalDateTime ajankohta = LocalDateTime.of(paivyri.getValue(), klo);
                LocalTime kestotunnitjaminuutit = LocalTime.parse(tfKesto.getText());
                String kuvaus = tfKuvaus.getText();
                String tyontekijaId = tfTyontekijaId.getText();

                if (asiakasId.isEmpty() || palvelunLaji.isEmpty() || tyontekijaId.isEmpty()) {
                    Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                    virheviesti.setContentText("Tiedoissa puutteita");
                    virheviesti.show();

                } else {
                    Palvelutapahtuma uusiTapahtuma = new Palvelutapahtuma(asiakasId, palvelunLaji, ajankohta, kestotunnitjaminuutit, kuvaus);
                    rekisteri.lisaaPalvelutapahtuma(uusiTapahtuma);
                    int tapahtumaId = uusiTapahtuma.getTapahtumaId();
                    Tyoskentely uusiTyo = new Tyoskentely(tyontekijaId, tapahtumaId);
                    rekisteri.lisaaTyoskentely(uusiTyo);
                    Alert vahvistus = new Alert(Alert.AlertType.INFORMATION);
                    vahvistus.setContentText("Tiedot tallennettu");
                    vahvistus.show();
                }
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Virhe! Tapahtuman tallennus ei onnistunut, " + e);
                error.show();

            }

        });
    }
    
}
