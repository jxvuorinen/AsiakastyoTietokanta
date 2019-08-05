
package data;

public class Tyontekija {
    private String tyontekijaNro;
    private String etunimi;
    private String sukunimi;
    private String nimike;
    private String yksikko;
    
    public Tyontekija(String tyontekijaNro, String yksikko, String nimike, String etunimi, String sukunimi) {
        this.tyontekijaNro = tyontekijaNro;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.nimike = nimike;
        this.yksikko = yksikko;
    }

    public String getTyontekijaNro() {
        return tyontekijaNro;
    }

    public void setTyontekijaNro(String tyontekijaNro) {
        this.tyontekijaNro = tyontekijaNro;
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

    public String getNimike() {
        return nimike;
    }

    public void setNimike(String nimike) {
        this.nimike = nimike;
    }

    public String getYksikko() {
        return yksikko;
    }

    public void setYksikko(String yksikko) {
        this.yksikko = yksikko;
    }

    @Override
    public String toString() {
        return "Työntekijän tiedot: \n" + " Työntekijänro: " + tyontekijaNro + "\n Etunimi: " + etunimi + "\n Sukunimi: " + sukunimi + "\n Nimike: " + nimike + "\n Yksikkö: " + yksikko;
    }
    
}
