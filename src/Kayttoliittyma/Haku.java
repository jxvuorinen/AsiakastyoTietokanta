package Kayttoliittyma;

import Tietokanta.Tietovarasto;
import data.Asiakas;
import data.Kayttaja;
import data.Kysely;
import data.PalveluKestoKysely;
import data.PalvelumaaraKysely;
import data.Tyontekija;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Haku {

    private BorderPane nakyma;
    Text lbAsiakasHaku = new Text("Hae asiakkaan tiedot: ");
    TextField tfHetu1 = new TextField();
    Button haeAsiakas = new Button("Hae");
    Text lbTapahtumaHaku = new Text("Hae asiakkaan palvelut henkilötunnuksella: ");
    TextField tfHetu2 = new TextField();
    Button haeTapahtumat = new Button("Hae");
    Text lbTyontekijaHaku = new Text("Hae työntekijän tiedot työntekijänumerolla: ");
    TextField tfTyontekijaNro = new TextField();
    Button haeTyontekija = new Button("Hae");
    Text lbYksikonPalvelut = new Text("Hae palvelun tiedot yksiköittäin ajalta: ");
    ComboBox cbPalvelunlaji = new ComboBox();
    DatePicker paivyriAlkaen = new DatePicker();
    DatePicker paivyriPaattyen = new DatePicker();
    Button haeYksikonPalvelut = new Button("Hae");
    Button btKaavio = new Button("Näytä kaavio");

    TextArea hakutulos = new TextArea("Hakutulokset");
    GridPane hakukentat = new GridPane();
    BorderPane uusiPane = new BorderPane();
    ScrollPane scrollPane = new ScrollPane();

    private Kayttaja kayttaja;

    //Konstruktori
    public Haku(BorderPane nakyma, Kayttaja kayttaja) {
        this.nakyma = nakyma;
        this.kayttaja = kayttaja;
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
        hakukentat.add(cbPalvelunlaji, 1, 3);
        hakukentat.add(paivyriAlkaen, 2, 3);
        hakukentat.add(paivyriPaattyen, 3, 3);
        hakukentat.add(haeYksikonPalvelut, 4, 3);

        hakukentat.setHgap(5);
        hakukentat.setVgap(5);
        hakukentat.setPadding(new Insets(10, 10, 10, 10));

        cbPalvelunlaji.getItems().addAll(
                "kotikäynti", "puhelu", "dokumentointi", "saatto", "toimistokäynti",
                "selvittely", "arviointiryhmäkäsittely", "päätöksenteko",
                "verkostotyö", "muu");
        cbPalvelunlaji.setPromptText("Palvelu");
        paivyriAlkaen.setPromptText("Alkaen pvm");
        paivyriPaattyen.setPromptText("Päättyen pvm");

        uusiPane.setTop(hakukentat);
        uusiPane.setBottom(scrollPane);
        uusiPane.setRight(btKaavio);
        this.nakyma.setCenter(uusiPane);

        hakutulos.setWrapText(true);
        hakutulos.setEditable(false);
        hakutulos.setPrefWidth(600);
        hakutulos.setStyle("-fx-font-weight: bold;");
        scrollPane.setContent(hakutulos);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefWidth(600);
        scrollPane.getStylesheets().add("/Kayttoliittyma/styles.css");
        scrollPane.getStyleClass().add("edge-to-edge");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        //Tallennuspainikkeen toiminnot:
        haeAsiakas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfHetu2.setText("");
                tfTyontekijaNro.setText("");
                paivyriAlkaen.getEditor().clear();
                paivyriPaattyen.getEditor().clear();
                cbPalvelunlaji.setValue(null);
                Tietovarasto rekisteri = new Tietovarasto(kayttaja.getKayttajatunnus(), kayttaja.getSalasana());
                try {
                    String asiakasId = tfHetu1.getText();
                    if (asiakasId.isEmpty()) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Tiedoissa puutteita");
                        virheviesti.show();
                    } else {
                        Asiakas haettu = rekisteri.haeAsiakas(asiakasId);
                        hakutulos.setText(haettu.toString());
                    }
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Virhe" + e);
                    error.show();
                }
            }
        });

        haeTyontekija.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfHetu1.setText("");
                tfHetu2.setText("");
                paivyriAlkaen.getEditor().clear();
                paivyriPaattyen.getEditor().clear();
                cbPalvelunlaji.setValue(null);
                Tietovarasto rekisteri = new Tietovarasto(kayttaja.getKayttajatunnus(), kayttaja.getSalasana());
                try {
                    String tyontekijanro = tfTyontekijaNro.getText();
                    if (tyontekijanro.isEmpty()) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Tiedoissa puutteita");
                        virheviesti.show();
                    } else {
                        Tyontekija haettu = rekisteri.haeTyontekija(tyontekijanro);
                        hakutulos.setText(haettu.toString());
                    }
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Virhe" + e);
                    error.show();
                }
            }
        });

        haeTapahtumat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfHetu1.setText("");
                tfTyontekijaNro.setText("");
                paivyriAlkaen.getEditor().clear();
                paivyriPaattyen.getEditor().clear();
                cbPalvelunlaji.setValue(null);
                Tietovarasto rekisteri = new Tietovarasto(kayttaja.getKayttajatunnus(), kayttaja.getSalasana());
                try {
                    String asiakasId = tfHetu2.getText();
                    if (asiakasId.isEmpty()) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Tiedoissa puutteita");
                        virheviesti.show();

                    } else {
                        Asiakas haettu = rekisteri.haeAsiakas(asiakasId);
                        StringBuilder msg = new StringBuilder();
                        PalveluKestoKysely kestoYhteensa = null;
                        if (haettu == null) {
                            Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                            virheviesti.setContentText("Asiakasta ei löytynyt");
                            virheviesti.show();
                        } else {
                            List<Kysely> kyselytulos = rekisteri.haeTapahtumat(haettu);
                            msg.append("Asiakkaan ").append(haettu.getAsiakasId()).append(" ").append(haettu.getEtunimi()).append(" ").append(haettu.getSukunimi()).append(" saamat palvelut: \n");
                            for (Kysely tapahtuma : kyselytulos) {
                                msg.append(tapahtuma.toString());
                                msg.append("\n" + "" + "\n");
                            }

                            String maara = Integer.toString(kyselytulos.size());
                            kestoYhteensa = rekisteri.tapahtumienKestoYhteensa(asiakasId);
                            msg.append("Yhteensä ").append(maara).append(" palvelutapahtumaa.\n")
                                    .append("Asiakkaan saamien palvelutapahtumien kesto yhteensä: ")
                                    .append(kestoYhteensa.toString().substring(0, 5)).append(" tuntia. \n");
                            hakutulos.setText(msg.toString());
                        }

                    }
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Virhe" + e);
                    error.show();
                }
            }
        }
        );

        haeYksikonPalvelut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfHetu1.setText("");
                tfHetu2.setText("");
                tfTyontekijaNro.setText("");
                Tietovarasto rekisteri = new Tietovarasto(kayttaja.getKayttajatunnus(), kayttaja.getSalasana());
                try {
                    if (cbPalvelunlaji.getValue() == null || paivyriAlkaen.getValue() == null || paivyriPaattyen.getValue() == null) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Tiedoissa puutteita");
                        virheviesti.show();

                    } else {
                        String palvelunlaji = cbPalvelunlaji.getValue().toString();
                        LocalDate alkupvm = paivyriAlkaen.getValue();
                        LocalDate loppupvm = paivyriPaattyen.getValue();
                        StringBuilder msg = new StringBuilder();
                        List<PalvelumaaraKysely> palvelumaarat = rekisteri.haePalveluMaaratYksikoittain(palvelunlaji, alkupvm, loppupvm);
                        msg.append("Palvelutapahtumat yksiköittäin aikavälillä ").append(alkupvm.format(formatter)).append("-").append(loppupvm.format(formatter)).append(":\n\n");
                        for (PalvelumaaraKysely tapahtuma : palvelumaarat) {
                            msg.append(tapahtuma.toString());
                            PalveluKestoKysely kesto = rekisteri.tapahtumienKestoYhteensaAjalla(tapahtuma.getYksikko(), palvelunlaji, alkupvm, loppupvm);
                            msg.append("\n palvelua annettu yhteensä: ").append(kesto.toString().substring(0, 5)).append(" tuntia. \n" + "" + "\n");
                            hakutulos.setText(msg.toString());

                        }

                        if (palvelumaarat.isEmpty()) {
                            hakutulos.setText("Ei tapahtumia");
                        }
                    }
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText("Virhe" + e);
                    error.show();
                }

            }
        }
        );
        btKaavio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CategoryAxis xAkseli = new CategoryAxis();
                NumberAxis yAkseli = new NumberAxis(0, 10, 1);
                yAkseli.setMinorTickVisible(false);
                yAkseli.setLabel("kpl");
                xAkseli.setTickLabelRotation(0);
                BarChart<String, Number> pylvaskaavio = new BarChart<>(xAkseli, yAkseli);

                pylvaskaavio.setLegendVisible(false);

                XYChart.Series palvelukestot = new XYChart.Series();

                Tietovarasto rekisteri = new Tietovarasto(kayttaja.getKayttajatunnus(), kayttaja.getSalasana());
                try {
                    if (cbPalvelunlaji.getValue() == null || paivyriAlkaen.getValue() == null || paivyriPaattyen.getValue() == null) {
                        Alert virheviesti = new Alert(Alert.AlertType.WARNING);
                        virheviesti.setContentText("Tiedoissa puutteita");
                        virheviesti.show();
                    } else {
                        String palvelunlaji = cbPalvelunlaji.getValue().toString();
                        LocalDate alkupvm = paivyriAlkaen.getValue();
                        LocalDate loppupvm = paivyriPaattyen.getValue();
                        pylvaskaavio.setTitle("Palvelumäärät yksiköittäin: " + palvelunlaji);
                        List<PalvelumaaraKysely> palvelumaarat = rekisteri.haePalveluMaaratYksikoittain(palvelunlaji, alkupvm, loppupvm);
                        for (PalvelumaaraKysely tapahtuma : palvelumaarat) {
                            palvelukestot.getData().add(new XYChart.Data(tapahtuma.getYksikko(), tapahtuma.getTapahtumienMaara()));
                        }
                        pylvaskaavio.getData().add(palvelukestot);
                        pylvaskaavio.setCategoryGap(60);
                        Scene kaavioNakyma = new Scene(pylvaskaavio, 800, 400);
                        Stage kaavioIkkuna = new Stage();
                        kaavioIkkuna.setTitle("Kuvaaja");
                        kaavioIkkuna.setScene(kaavioNakyma);
                        kaavioIkkuna.show();
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
