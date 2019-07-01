package Tietokanta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Yhteydenhallinta {

    /**
     * @param args
     */
    public static Connection avaaYhteys(String ajuri, String url, String kayttajatunnus, String salasana) throws Exception {
        try {
            Class.forName(ajuri).newInstance();
            return DriverManager.getConnection(url, kayttajatunnus, salasana);
        } catch (SQLException sqle) {
            throw new Exception("Yhteyden avaaminen ei onnistu", sqle);
        } catch (ClassNotFoundException e) {
            throw new Exception("Ajuria ei löytynyt", e);
        }
    }

    public static boolean suljeYhteys(Connection yhteys) {
        if (yhteys != null) {
            try {
                yhteys.close();
                return true;
            } catch (SQLException sqle) {
                return false;
            }
        } else {
            return false;
        }
    }

}
