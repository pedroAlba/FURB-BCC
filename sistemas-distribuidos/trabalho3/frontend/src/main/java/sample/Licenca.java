package sample;

import java.time.Instant;

public class Licenca {
    private int id;
    private boolean inUse;
    private String obtido;

    public Licenca(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

    public boolean isInUse() {
        return inUse;
    }

    public String getObtido() {
        return obtido;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public void setObtido(String obtido) {
        this.obtido = obtido;
    }

    public void setId(int id) {
        this.id = id;
    }
}
