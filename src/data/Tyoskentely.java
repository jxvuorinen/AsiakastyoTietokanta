package data;

public class Tyoskentely {

    private String tyoskentelyId;
    private String tyontekijaId;
    private int palvelutapahtumaId;

    public Tyoskentely(String tyontekijaId, int palvelutapahtumaId) {
        this.tyontekijaId = tyontekijaId;
        this.palvelutapahtumaId = palvelutapahtumaId;
    }

    public String getTyoskentelyId() {
        return tyoskentelyId;
    }

    public void setTyoskentelyId(String tyoskentelyId) {
        this.tyoskentelyId = tyoskentelyId;
    }

    public String getTyontekijaId() {
        return tyontekijaId;
    }

    public void setTyontekijaId(String tyontekijaId) {
        this.tyontekijaId = tyontekijaId;
    }

    public int getPalvelutapahtumaId() {
        return palvelutapahtumaId;
    }

    public void setPalvelutapahtumaId(int palvelutapahtumaId) {
        this.palvelutapahtumaId = palvelutapahtumaId;
    }
}
