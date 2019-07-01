package Tietokanta;

import data.Asiakas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tietovarasto {

    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/asiakastyo";
    private String kayttajatunnus = "root";
    private String salasana = "";

    private String sqlLisaaAsiakas = "INSERT INTO asiakas(etunimi, sukunimi, sukupuoli, asuinalue) VALUES (?,?,?,?)";

    private String sqlHaeAsiakas = "SELECT * FROM asiakas WHERE asiakasID = ?";

    private String sqlLisaaTyontekija = "INSERT INTO tyontekija(tyontekijanro, yksikko, tyontekijaNimeke, etunimi, sukunimi) VALUES (?,?,?,?,?)";

    private String sqlLisaaPalvelutapahtuma = "INSERT INTO palvelutapahtuma(palvelutapahtumaID, asiakasID, palvelunLaji, ajankohta, kesto, kuvaus) VALUES (?,?,?,?,?,?)";

    private String sqlLisaaTyoskentely = "INSERT INTO tyoskentely(tyoskentelyID, tyontekijanro, palvelutapahtumaID) VALUES (?,?,?)";

    public void lisaaAsiakas(Asiakas uusiAsiakas) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement asiakkaanLisays = null;
        try {
            asiakkaanLisays = yhteys.prepareStatement(sqlLisaaAsiakas);           
            asiakkaanLisays.setString(1, uusiAsiakas.getEtunimi());// // ensimmäinen kysymysmerkki
            asiakkaanLisays.setString(2, uusiAsiakas.getSukunimi());// toinen kysymysmerkki
            asiakkaanLisays.setString(3, uusiAsiakas.getSukupuoli());// jne
            asiakkaanLisays.setString(4, uusiAsiakas.getAsuinalue());
            asiakkaanLisays.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Asiakkaan lisäys ei onnistu.", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
    }

}
