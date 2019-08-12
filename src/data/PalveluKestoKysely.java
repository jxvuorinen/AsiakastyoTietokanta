
package data;

import javafx.util.Duration;

public class PalveluKestoKysely {
    private Object kesto;


    public PalveluKestoKysely(Object kesto) {
        this.kesto = kesto;
    }

    public Object getKesto() {
        return kesto;
    }

    public void setKesto(Object kesto) {
        this.kesto = kesto;
    }

    @Override
    public String toString() {
        return "Palvelua annettu yhteensä: " + kesto + " tuntia.";
    }
    
    
    
}
