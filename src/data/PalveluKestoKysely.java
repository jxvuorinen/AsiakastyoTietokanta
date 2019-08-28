
package data;


public class PalveluKestoKysely {
    private Object kesto;


    public PalveluKestoKysely(Object kesto) {
        this.kesto = kesto;
    }

    public Object getKesto() {
        return this.kesto;
    }

    public void setKesto(Object kesto) {
        this.kesto = kesto;
    }

    @Override
    public String toString() {
        return "" + kesto;
    }
}
