
package data;

public class PalvelumaaraKysely {
    private String yksikko;
    private int tapahtumienMaara;

    public PalvelumaaraKysely(String yksikko, int tapahtumienMaara) {
        this.yksikko = yksikko;
        this.tapahtumienMaara = tapahtumienMaara;
    }

    public String getYksikko() {
        return yksikko;
    }

    public void setYksikko(String yksikko) {
        this.yksikko = yksikko;
    }

    public int getTapahtumienMaara() {
        return tapahtumienMaara;
    }

    public void setTapahtumienMaara(int tapahtumienMaara) {
        this.tapahtumienMaara = tapahtumienMaara;
    }

    @Override
    public String toString() {
        return "Yksikkö: " + yksikko + "\n palvelutapahtumien määrä haetulla ajalla: " + tapahtumienMaara;
    }
}
