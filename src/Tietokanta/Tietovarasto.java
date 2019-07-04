package Tietokanta;

import com.mysql.jdbc.Statement;
import data.Asiakas;
import data.Palvelutapahtuma;
import data.Tyontekija;
import data.Tyoskentely;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class Tietovarasto {

    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/asiakastyo";
    private String kayttajatunnus = "root";
    private String salasana = "";

    private String sqlLisaaAsiakas = "INSERT INTO asiakas(etunimi, sukunimi, sukupuoli, asuinalue) VALUES (?,?,?,?)";

    private String sqlHaeAsiakas = "SELECT * FROM asiakas WHERE asiakasID = ?";

    private String sqlLisaaTyontekija = "INSERT INTO tyontekija(tyontekijanro, yksikko, tyontekijaNimeke, etunimi, sukunimi) VALUES (?,?,?,?,?)";

    private String sqlLisaaPalvelutapahtuma = "INSERT INTO palvelutapahtuma(asiakasID, palvelunLaji, ajankohta, kesto, kuvaus) VALUES (?,?,?,?,?)";

    private String sqlLisaaTyoskentely = "INSERT INTO tyoskentely(tyontekijanro, palvelutapahtumaID) VALUES (?,?)";

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
            asiakkaanLisays.setString(1, uusiAsiakas.getEtunimi());// ensimmäinen kysymysmerkki
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

    public void lisaaTyontekija(Tyontekija uusiTyontekija) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement TyontekijanLisays = null;
        try {
            TyontekijanLisays = yhteys.prepareStatement(sqlLisaaTyontekija);
            TyontekijanLisays.setString(1, uusiTyontekija.getTyontekijaNro());// ensimmäinen kysymysmerkki
            TyontekijanLisays.setString(2, uusiTyontekija.getYksikko()); // Toinen kysymysmerkki
            TyontekijanLisays.setString(3, uusiTyontekija.getNimike());
            TyontekijanLisays.setString(4, uusiTyontekija.getEtunimi());
            TyontekijanLisays.setString(5, uusiTyontekija.getSukunimi());
            TyontekijanLisays.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Asiakkaan lisäys ei onnistu.", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }

    }

    public void lisaaPalvelutapahtuma(Palvelutapahtuma uusiTapahtuma) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement TapahtumanLisays = null;
        try {
            TapahtumanLisays = yhteys.prepareStatement(sqlLisaaPalvelutapahtuma, Statement.RETURN_GENERATED_KEYS);
            TapahtumanLisays.setString(1, uusiTapahtuma.getAsiakasId());// ensimmäinen kysymysmerkki
            TapahtumanLisays.setString(2, uusiTapahtuma.getPalvelunLaji()); // Toinen kysymysmerkki
            TapahtumanLisays.setTimestamp(3, Timestamp.valueOf(uusiTapahtuma.getAjankohta()));
            TapahtumanLisays.setTime(4, Time.valueOf(uusiTapahtuma.getKesto()));
            TapahtumanLisays.setString(5, uusiTapahtuma.getKuvaus());
            TapahtumanLisays.executeUpdate();
            ResultSet key = TapahtumanLisays.getGeneratedKeys();
            if(key.next()) {
                int tapahtumaId = key.getInt(1);
                uusiTapahtuma.setTapahtumaId(tapahtumaId);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Tapahtuman lisäys ei onnistu.", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
    }

    public void lisaaTyoskentely(Tyoskentely uusiTyoskentely) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement TyonLisays = null;
        try {
            TyonLisays = yhteys.prepareStatement(sqlLisaaTyoskentely);
            TyonLisays.setString(1, uusiTyoskentely.getTyontekijaId());// ensimmäinen kysymysmerkki
            TyonLisays.setInt(2, uusiTyoskentely.getPalvelutapahtumaId()); // Toinen kysymysmerkki
            TyonLisays.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Tapahtuman lisäys ei onnistu.", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
    }

}
