package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Asiakas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class AsiakkaanLisays {

    private BorderPane nakyma;
    private Label lbEtunimi = new Label("Etunimi: ");
    private TextField tfEtunimi = new TextField();
    private Label lbSukunimi = new Label("Sukunimi: ");
    private TextField tfSukunimi = new TextField();
    private Label lbTunnus = new Label("Henkilötunnus: ");
    private TextField tfTunnus = new TextField();
    private Label lbSukupuoli = new Label("Valitse sukupuoli: ");
    private ChoiceBox<String> CBsukupuoli = new ChoiceBox<>();
    private Label lbAsuinalue = new Label("Postinumeroalue: ");
    private TextField tfAsuinalue = new TextField();
    private Button talletaA = new Button("Talleta");
    private GridPane AsLomakekentat = new GridPane();

    //Konstruktori
    public AsiakkaanLisays(BorderPane aloitusnakyma) {
        this.nakyma = aloitusnakyma;
        asetteleKomponentit();
    }

    private void asetteleKomponentit() {
        //Luodaan asiakastallennusnäkymä ja tallennuspainike
        CBsukupuoli.getItems().addAll("mies", "nainen", "muu");
        AsLomakekentat.add(lbEtunimi, 0, 0);
        AsLomakekentat.add(tfEtunimi, 1, 0);
        AsLomakekentat.add(lbSukunimi, 0, 1);
        AsLomakekentat.add(tfSukunimi, 1, 1);
        AsLomakekentat.add(lbTunnus, 0, 2);
        AsLomakekentat.add(tfTunnus, 1, 2);
        AsLomakekentat.add(lbSukupuoli, 0, 3);
        AsLomakekentat.add(CBsukupuoli, 1, 3);
        AsLomakekentat.add(lbAsuinalue, 0, 4);
        AsLomakekentat.add(tfAsuinalue, 1, 4);
        AsLomakekentat.add(talletaA, 1, 5);

        //Näytetään ikkuna
        this.nakyma.setCenter(AsLomakekentat);

        //Tallennuspainikkeen toiminto:
        talletaA.setOnAction((event) -> {
            Tietovarasto rekisteri = new Tietovarasto();
            try {
                String etunimi = tfEtunimi.getText();
                String sukunimi = tfSukunimi.getText();
                String sukupuoli = CBsukupuoli.getValue();
                String henkilotunnus = tfTunnus.getText();
                String asuinalue = tfAsuinalue.getText();

                if (etunimi.isEmpty() || sukunimi.isEmpty() || henkilotunnus.isEmpty() || asuinalue.isEmpty() || sukupuoli.isEmpty()) {
                    Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                    virheviesti.setContentText("Tiedoissa puutteita");
                    virheviesti.show();

                } else {
                    Asiakas uusiAsiakas = new Asiakas(henkilotunnus, etunimi, sukunimi, sukupuoli, asuinalue);
                    rekisteri.lisaaAsiakas(uusiAsiakas);
                    Alert vahvistus = new Alert(Alert.AlertType.INFORMATION);
                    vahvistus.setContentText("Tiedot tallennettu");
                    vahvistus.show();
                }
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("Virhe! Asiakastietojen tallennus ei onnistunut, " + e);
                error.show();

            }

        });
    }
}
