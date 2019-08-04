
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
    

    @Override
    public String toString() {
        return "Tyontekijänimeke: " + tyontekijaNimeke + ". Yksikkö: " + yksikko + ".\n Ajankohta: " + ajankohta + ". Palvelu: " + palvelunLaji + ". Kesto: " + kesto + ". Kuvaus: " + kuvaus;
    }
    
    
}
