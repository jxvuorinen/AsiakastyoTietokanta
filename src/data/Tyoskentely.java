
package data;

public class Tyoskentely {
    private String tyoskentelyId;
    private Tyontekija tyontekija;
    private Palvelutapahtuma palvelutapahtuma;

    public Tyoskentely(String tyoskentelyId, Tyontekija tyontekija, Palvelutapahtuma palvelutapahtuma) {
        this.tyoskentelyId = tyoskentelyId;
        this.tyontekija = tyontekija;
        this.palvelutapahtuma = palvelutapahtuma;
    }

    public String getTyoskentelyId() {
        return tyoskentelyId;
    }

    public void setTyoskentelyId(String tyoskentelyId) {
        this.tyoskentelyId = tyoskentelyId;
    }

    public Tyontekija getTyontekija() {
        return tyontekija;
    }

    public void setTyontekija(Tyontekija tyontekija) {
        this.tyontekija = tyontekija;
    }

    public Palvelutapahtuma getPalvelutapahtuma() {
        return palvelutapahtuma;
    }

    public void setPalvelutapahtuma(Palvelutapahtuma palvelutapahtuma) {
        this.palvelutapahtuma = palvelutapahtuma;
    }
    
    
}
