
package data;

public class Asiakas {
    private String etunimi;
    private String sukunimi;
    private String asiakasId;
    private String sukupuoli;
    private String asuinalue;
    
    public Asiakas(String etunimi, String sukunimi, String sukupuoli, String asuinalue) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.sukupuoli = sukupuoli;
        this.asuinalue = asuinalue;
    }
    
    public Asiakas(String asiakasId, String etunimi, String sukunimi, String sukupuoli, String asuinalue) {
        this(etunimi, sukunimi, sukupuoli, asuinalue);
        this.asiakasId = asiakasId;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getAsiakasId() {
        return asiakasId;
    }

    public void setAsiakasId(String asiakasId) {
        this.asiakasId = asiakasId;
    }

    public String getSukupuoli() {
        return sukupuoli;
    }

    public void setSukupuoli(String sukupuoli) {
        this.sukupuoli = sukupuoli;
    }

    public String getAsuinalue() {
        return asuinalue;
    }

    public void setAsuinalue(String asuinalue) {
        this.asuinalue = asuinalue;
    }

    @Override
    public String toString() {
        return "Asiakas{" + "etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", asiakasId=" + asiakasId + ", sukupuoli=" + sukupuoli + ", asuinalue=" + asuinalue + '}';
    }
    
}
