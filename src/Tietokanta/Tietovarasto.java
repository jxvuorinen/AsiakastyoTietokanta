package Tietokanta;

import com.mysql.jdbc.Statement;
import data.Asiakas;
import data.Kysely;
import data.PalveluKestoKysely;
import data.PalvelumaaraKysely;
import data.Palvelutapahtuma;
import data.Tyontekija;
import data.Tyoskentely;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tietovarasto {

    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/asiakastyo";
    private String kayttajatunnus = "root";
    private String salasana = "";

    private String sqlLisaaAsiakas = "INSERT INTO asiakas VALUES (?,?,?,?,?)";

    private String sqlHaeAsiakas = "SELECT * FROM asiakas WHERE asiakasID = ?";

    private String sqlHaeTyontekija = "SELECT * FROM tyontekija WHERE tyontekijanro = ?";

    private String sqlLisaaTyontekija = "INSERT INTO tyontekija(tyontekijanro, yksikko, tyontekijaNimeke, etunimi, sukunimi) VALUES (?,?,?,?,?)";

    private String sqlLisaaPalvelutapahtuma = "INSERT INTO palvelutapahtuma(asiakasID, palvelunLaji, ajankohta, kesto, kuvaus) VALUES (?,?,?,?,?)";

    private String sqlLisaaTyoskentely = "INSERT INTO tyoskentely(tyontekijanro, palvelutapahtumaID) VALUES (?,?)";

    private String sqlTapahtumahaku = "SELECT tyontekija.tyontekijaNimeke, yksikko, ajankohta, palvelunLaji, kesto, kuvaus "
            + "FROM Palvelutapahtuma JOIN Tyoskentely "
            + "On tyoskentely.palvelutapahtumaID = palvelutapahtuma.palvelutapahtumaID "
            + "JOIN tyontekija "
            + "ON tyoskentely.tyontekijanro = tyontekija.tyontekijanro "
            + "WHERE palvelutapahtuma.asiakasID = ?"
            + "ORDER BY ajankohta ASC";

    private String sqlPalvelutYksikoittain = "SELECT yksikko, COUNT(palvelutapahtuma.palvelunLaji) "
            + "FROM Tyoskentely JOIN Palvelutapahtuma "
            + "ON tyoskentely.palvelutapahtumaID = palvelutapahtuma.palvelutapahtumaID "
            + "JOIN tyontekija "
            + "ON tyontekija.tyontekijanro = tyoskentely.tyontekijanro "
            + "WHERE palvelutapahtuma.palvelunLaji = ? AND ajankohta BETWEEN ? AND ? "
            + "GROUP BY yksikko";

    private String sqlPalveluidenKestoAjalla = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(palvelutapahtuma.kesto))) "
            + "FROM Tyoskentely JOIN Palvelutapahtuma "
            + "ON tyoskentely.palvelutapahtumaID = palvelutapahtuma.palvelutapahtumaID "
            + "JOIN tyontekija ON tyontekija.tyontekijanro = tyoskentely.tyontekijanro "
            + "WHERE palvelutapahtuma.palvelunLaji = ? AND tyontekija.yksikko = ? AND ajankohta BETWEEN ? AND ?";

    private String sqlPalveluidenKestoYhteensa = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(palvelutapahtuma.kesto))) "
            + "FROM Palvelutapahtuma JOIN Tyoskentely "
            + "ON palvelutapahtuma.palvelutapahtumaID = tyoskentely.palvelutapahtumaID "
            + "WHERE palvelutapahtuma.asiakasID = ?";

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
            asiakkaanLisays.setString(1, uusiAsiakas.getAsiakasId());// ensimmäinen kysymysmerkki
            asiakkaanLisays.setString(2, uusiAsiakas.getEtunimi());// toinen kysymysmerkki
            asiakkaanLisays.setString(3, uusiAsiakas.getSukunimi());// kolmas kysymysmerkki
            asiakkaanLisays.setString(4, uusiAsiakas.getSukupuoli());// jne
            asiakkaanLisays.setString(5, uusiAsiakas.getAsuinalue());
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
        PreparedStatement tyontekijanLisays = null;
        try {
            tyontekijanLisays = yhteys.prepareStatement(sqlLisaaTyontekija);
            tyontekijanLisays.setString(1, uusiTyontekija.getTyontekijaNro());// ensimmäinen kysymysmerkki
            tyontekijanLisays.setString(2, uusiTyontekija.getYksikko()); // Toinen kysymysmerkki
            tyontekijanLisays.setString(3, uusiTyontekija.getNimike());
            tyontekijanLisays.setString(4, uusiTyontekija.getEtunimi());
            tyontekijanLisays.setString(5, uusiTyontekija.getSukunimi());
            tyontekijanLisays.executeUpdate();
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
        PreparedStatement tapahtumanLisays = null;
        try {
            tapahtumanLisays = yhteys.prepareStatement(sqlLisaaPalvelutapahtuma, Statement.RETURN_GENERATED_KEYS);
            tapahtumanLisays.setString(1, uusiTapahtuma.getAsiakasId());// ensimmäinen kysymysmerkki
            tapahtumanLisays.setString(2, uusiTapahtuma.getPalvelunLaji()); // Toinen kysymysmerkki
            tapahtumanLisays.setTimestamp(3, Timestamp.valueOf(uusiTapahtuma.getAjankohta()));
            tapahtumanLisays.setTime(4, Time.valueOf(uusiTapahtuma.getKesto()));
            tapahtumanLisays.setString(5, uusiTapahtuma.getKuvaus());
            tapahtumanLisays.executeUpdate();
            ResultSet key = tapahtumanLisays.getGeneratedKeys();
            if (key.next()) {
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
        PreparedStatement tyonLisays = null;
        try {
            tyonLisays = yhteys.prepareStatement(sqlLisaaTyoskentely);
            tyonLisays.setString(1, uusiTyoskentely.getTyontekijaId());// ensimmäinen kysymysmerkki
            tyonLisays.setInt(2, uusiTyoskentely.getPalvelutapahtumaId()); // Toinen kysymysmerkki
            tyonLisays.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Tapahtuman lisäys ei onnistu.", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
    }

    public Asiakas haeAsiakas(String asiakasId) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement asiakkaanHaku = null;
        ResultSet tulos = null;
        try {
            asiakkaanHaku = yhteys.prepareStatement(sqlHaeAsiakas);
            asiakkaanHaku.setString(1, asiakasId);
            tulos = asiakkaanHaku.executeQuery();
            if (tulos.next()) {
                return new Asiakas(tulos.getString(1), tulos.getString(2), tulos.getString(3), tulos.getString(4), tulos.getString(5));
            } else {
                throw new Exception("Asiakasta ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }

    }

    public Tyontekija haeTyontekija(String tyontekijanro) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement tyontekijaHaku = null;
        ResultSet tulos = null;
        try {
            tyontekijaHaku = yhteys.prepareStatement(sqlHaeTyontekija);
            tyontekijaHaku.setString(1, tyontekijanro);
            tulos = tyontekijaHaku.executeQuery();
            if (tulos.next()) {
                return new Tyontekija(tulos.getString(1), tulos.getString(2), tulos.getString(3), tulos.getString(4), tulos.getString(5));
            } else {
                throw new Exception("Työntekijää ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }

    }

    public List<Kysely> haeTapahtumat(Asiakas haettu) throws Exception {
        String asiakasId = haettu.getAsiakasId();
        Connection yhteys = null;
        List<Kysely> tapahtumat = new ArrayList<>();
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement tapahtumienHaku = null;
        ResultSet tulos = null;
        try {
            tapahtumienHaku = yhteys.prepareStatement(sqlTapahtumahaku);
            tapahtumienHaku.setString(1, asiakasId);
            tulos = tapahtumienHaku.executeQuery();
            while (tulos.next()) {
                tapahtumat.add(new Kysely(tulos.getString(1), tulos.getString(2), tulos.getTimestamp(3).toLocalDateTime(), tulos.getString(4), tulos.getTime(5).toLocalTime(), tulos.getString(6)));
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
        return tapahtumat;
    }

    public List<PalvelumaaraKysely> haePalveluMaaratYksikoittain(String palvelunlaji, LocalDate alkupvm, LocalDate loppupvm) throws Exception {
        Connection yhteys = null;
        List<PalvelumaaraKysely> yksikonTapahtumamaarat = new ArrayList<>();
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement palvelumaarienHaku = null;
        ResultSet tulos = null;
        try {
            palvelumaarienHaku = yhteys.prepareStatement(sqlPalvelutYksikoittain);
            palvelumaarienHaku.setString(1, palvelunlaji);
            palvelumaarienHaku.setObject(2, alkupvm.format(DateTimeFormatter.ISO_DATE));
            palvelumaarienHaku.setObject(3, loppupvm.format(DateTimeFormatter.ISO_DATE));
            tulos = palvelumaarienHaku.executeQuery();
            while (tulos.next()) {
                yksikonTapahtumamaarat.add(new PalvelumaaraKysely(tulos.getString(1), tulos.getInt(2)));
            }
        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
        return yksikonTapahtumamaarat;
    }

    public PalveluKestoKysely tapahtumienKestoYhteensaAjalla(String yksikko, String palvelunLaji, LocalDate alkupvm, LocalDate loppupvm) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement palvelunkestohaku = null;
        ResultSet tulos = null;
        try {
            palvelunkestohaku = yhteys.prepareStatement(sqlPalveluidenKestoAjalla);
            palvelunkestohaku.setString(1, palvelunLaji);
            palvelunkestohaku.setString(2, yksikko);
            palvelunkestohaku.setObject(3, alkupvm.format(DateTimeFormatter.ISO_DATE));
            palvelunkestohaku.setObject(4, loppupvm.format(DateTimeFormatter.ISO_DATE));
            tulos = palvelunkestohaku.executeQuery();
            if (tulos.next()) {
                return new PalveluKestoKysely(tulos.getObject(1).toString());
            } else {
                throw new Exception("Kesto ei saatavilla");
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
    }

    public PalveluKestoKysely tapahtumienKestoYhteensa(String asiakasId) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement palvelunkestohaku = null;
        ResultSet tulos = null;
        try {
            palvelunkestohaku = yhteys.prepareStatement(sqlPalveluidenKestoYhteensa);
            palvelunkestohaku.setString(1, asiakasId);
            tulos = palvelunkestohaku.executeQuery();
            if (tulos.next()) {
                return new PalveluKestoKysely(tulos.getObject(1).toString());
            } else {
                throw new Exception("Kesto ei saatavilla");
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            Yhteydenhallinta.suljeYhteys(yhteys);
        }
    }
}
