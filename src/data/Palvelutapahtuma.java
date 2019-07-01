
package data;

import java.sql.Time;
import java.sql.Timestamp;


public class Palvelutapahtuma {
    private String tapahtumaId;
    private Asiakas asiakas;
    private String palvelunLaji;
    private Timestamp ajankohta;
    private String kuvaus;
    private Time kesto;
    
    public Palvelutapahtuma(Asiakas asiakas, String palvelunLaji, Timestamp ajankohta, Time kesto, String kuvaus) {
        this.asiakas = asiakas;
        this.palvelunLaji = palvelunLaji;
        this.ajankohta = ajankohta;
        this.kesto = kesto;
        this.kuvaus = kuvaus;
    }
    
    public Palvelutapahtuma(String tapahtumaId, Asiakas asiakas, String palvelunLaji, Timestamp ajankohta, Time kesto, String kuvaus) {
        this(asiakas, palvelunLaji, ajankohta, kesto, kuvaus);
        this.tapahtumaId = tapahtumaId;
    }

    public Time getKesto() {
        return kesto;
    }

    public void setKesto(Time kesto) {
        this.kesto = kesto;
    }

    public String getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(String tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public String getPalvelunLaji() {
        return palvelunLaji;
    }

    public void setPalvelunLaji(String palvelunLaji) {
        this.palvelunLaji = palvelunLaji;
    }

    public Timestamp getAjankohta() {
        return ajankohta;
    }

    public void setAjankohta(Timestamp ajankohta) {
        this.ajankohta = ajankohta;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    
    
}
