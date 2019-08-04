package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Asiakas;
import data.Kysely;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class Haku {

    private BorderPane nakyma;
    Label lbHakukentta = new Label("Hae asiakkaan saamat palvelut henkilötunnuksella: ");
    TextField tfHetu = new TextField();
    Button haeNappi = new Button("Hae");
    TextArea hakutulos = new TextArea("Tähän tulee tulokset");
    HBox hakukentat = new HBox();

    //Konstruktori
    public Haku(BorderPane nakyma) {
        this.nakyma = nakyma;
        asetteleKomponentit();
    }

    private void asetteleKomponentit() {
        hakukentat.getChildren().addAll(lbHakukentta, tfHetu, haeNappi);
        this.nakyma.setTop(hakukentat);
        this.nakyma.setCenter(hakutulos);
        hakutulos.setWrapText(true);
        hakutulos.setEditable(false);

        //Tallennuspainikkeen toiminto:
        haeNappi.setOnAction((event) -> {
            Tietovarasto rekisteri = new Tietovarasto();
            try {
                String asiakasId = tfHetu.getText();
                if (asiakasId.isEmpty()) {
                    Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                    virheviesti.setContentText("Tiedoissa puutteita");
                    virheviesti.show();

                } else {
                    Asiakas haettu = rekisteri.haeAsiakas(asiakasId);
                    StringBuilder msg = new StringBuilder();
                    if (haettu == null) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Asiakasta ei löytynyt");
                        virheviesti.show();
                    } else {
                        List<Kysely> kyselytulos = rekisteri.haeTapahtumat(haettu);
                        for (Kysely tapahtuma : kyselytulos) {
                            msg.append(tapahtuma.toString());
                            msg.append("\n");
                        }
                        if (msg.length() == 0) {
                            hakutulos.setText("Ei tapahtumia");
                        } else {
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
        );
    }

}
