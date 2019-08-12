
package data;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Kysely {
    private String tyontekijaNimeke;
    private String yksikko;
    private LocalDateTime ajankohta;
    private String palvelunLaji;
    private LocalTime kesto;
    private String kuvaus;

    public Kysely(String tyontekijaNimeke, String yksikko, LocalDateTime ajankohta, String palvelunLaji, LocalTime kesto, String kuvaus) {
        this.tyontekijaNimeke = tyontekijaNimeke;
        this.yksikko = yksikko;
        this.ajankohta = ajankohta;
        this.palvelunLaji = palvelunLaji;
        this.kesto = kesto;
        this.kuvaus = kuvaus;
    }

    public LocalTime getKesto() {
        return kesto;
    }

    public String getTyontekijaNimeke() {
        return tyontekijaNimeke;
    }

    public void setTyontekijaNimeke(String tyontekijaNimeke) {
        this.tyontekijaNimeke = tyontekijaNimeke;
    }

    public String getYksikko() {
        return yksikko;
    }

    public void setYksikko(String yksikko) {
        this.yksikko = yksikko;
    }

    public LocalDateTime getAjankohta() {
        return ajankohta;
    }

    public void setAjankohta(LocalDateTime ajankohta) {
        this.ajankohta = ajankohta;
    }

    public String getPalvelunLaji() {
        return palvelunLaji;
    }

    public void setPalvelunLaji(String palvelunLaji) {
        this.palvelunLaji = palvelunLaji;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    
    

    @Override
    public String toString() {
        return "Tyontekijänimeke: " + tyontekijaNimeke + ". Yksikkö: " + yksikko + ".\n Ajankohta: " + ajankohta + ". Palvelu: " + palvelunLaji + ". Kesto: " + kesto + ". Kuvaus: " + kuvaus;
    }
    
    
}
