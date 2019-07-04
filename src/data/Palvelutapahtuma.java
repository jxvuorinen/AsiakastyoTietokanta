
package data;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Palvelutapahtuma {
    private int tapahtumaId;
    private String asiakasId;
    private String palvelunLaji;
    private LocalDateTime ajankohta;
    private String kuvaus;
    private LocalTime kesto;
    
    public Palvelutapahtuma(String asiakasId, String palvelunLaji, LocalDateTime ajankohta, LocalTime kesto, String kuvaus) {
        this.asiakasId = asiakasId;
        this.palvelunLaji = palvelunLaji;
        this.ajankohta = ajankohta;
        this.kesto = kesto;
        this.kuvaus = kuvaus;
    }
    
    public Palvelutapahtuma(int tapahtumaId, String asiakasId, String palvelunLaji, LocalDateTime ajankohta, LocalTime kesto, String kuvaus) {
        this(asiakasId, palvelunLaji, ajankohta, kesto, kuvaus);
        this.tapahtumaId = tapahtumaId;
    }

    public LocalTime getKesto() {
        return kesto;
    }

    public void setKesto(LocalTime kesto) {
        this.kesto = kesto;
    }

    public int getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(int tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public String getAsiakasId() {
        return this.asiakasId;
    }

    public String getPalvelunLaji() {
        return palvelunLaji;
    }

    public void setPalvelunLaji(String palvelunLaji) {
        this.palvelunLaji = palvelunLaji;
    }

    public LocalDateTime getAjankohta() {
        return ajankohta;
    }

    public void setAjankohta(LocalDateTime ajankohta) {
        this.ajankohta = ajankohta;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    
    
}
