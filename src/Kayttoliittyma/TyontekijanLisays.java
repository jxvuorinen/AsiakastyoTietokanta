package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Tyontekija;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TyontekijanLisays {

    private BorderPane nakyma;
    private Label lbEtunimi = new Label("Etunimi: ");
    private TextField tfEtunimi = new TextField();
    private Label lbSukunimi = new Label("Sukunimi: ");
    private TextField tfSukunimi = new TextField();
    private Label lbTunnus = new Label("Työntekijänro: ");
    private TextField tfTunnus = new TextField();
    private Label lbNimike = new Label("Työntekijänimike: ");
    private TextField tfNimike = new TextField();
    private Label lbYksikko = new Label("Yksikkö: ");
    private ChoiceBox<String> cbYksikko = new ChoiceBox<>(FXCollections.observableArrayList("Gerontologinen sosiaalityö/Pohjoinen",
            "Gerontologinen sosiaalityö/Etelä", "Gerontologinen sosiaalityö/Itä", 
            "Gerontologinen sosiaalityö/Länsi", "HelppiSeniori/Länsi", "HelppiSeniori/Etelä", "HelppiSeniori/Itä", 
            "HelppiSeniori/Pohjoinen"));
    private Button talleta = new Button("Talleta");
    GridPane TLomakekentat = new GridPane();

    //Konstruktori
    public TyontekijanLisays(BorderPane nakyma) {
        this.nakyma = nakyma;
        asetteleKomponentit();
    }

    private void asetteleKomponentit() {
        TLomakekentat.add(lbEtunimi, 0, 0);
        TLomakekentat.add(tfEtunimi, 1, 0);
        TLomakekentat.add(lbSukunimi, 0, 1);
        TLomakekentat.add(tfSukunimi, 1, 1);
        TLomakekentat.add(lbTunnus, 0, 2);
        TLomakekentat.add(tfTunnus, 1, 2);
        TLomakekentat.add(lbNimike, 0, 3);
        TLomakekentat.add(tfNimike, 1, 3);
        TLomakekentat.add(lbYksikko, 0, 4);
        TLomakekentat.add(cbYksikko, 1, 4);
        TLomakekentat.add(talleta, 1, 6);

        this.nakyma.setCenter(TLomakekentat);

        //Tallennuspainikkeen toiminto:
        talleta.setOnAction((event) -> {
            Tietovarasto rekisteri = new Tietovarasto();
            try {
                String etunimi = tfEtunimi.getText();
                String sukunimi = tfSukunimi.getText();
                String nimike = tfNimike.getText();
                String yksikko = cbYksikko.getValue();
                String tyontekijanro = tfTunnus.getText();

                if (etunimi.isEmpty() || sukunimi.isEmpty() || nimike.isEmpty() || yksikko.isEmpty() || tyontekijanro.isEmpty()) {
                    Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                    virheviesti.setContentText("Tiedoissa puutteita");
                    virheviesti.show();

                } else {
                    Tyontekija uusiTyontekija = new Tyontekija(tyontekijanro, yksikko, nimike, etunimi, sukunimi);
                    rekisteri.lisaaTyontekija(uusiTyontekija);
                    Alert vahvistus = new Alert(Alert.AlertType.INFORMATION);
                    vahvistus.setContentText("Tiedot tallennettu");
                    vahvistus.show();
                }
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Virhe! Työntekijätietojen tallennus ei onnistunut, " + e);
                error.show();

            }

        });
    }

}
