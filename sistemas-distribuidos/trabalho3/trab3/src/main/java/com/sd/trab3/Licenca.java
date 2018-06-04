package com.sd.trab3;

import java.time.Instant;

public class Licenca {

    private int id;
    private boolean inUse;
    private Instant obtido;

    public Licenca(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

    public boolean isInUse() {
        return inUse;
    }

    public Instant getObtido() {
        return obtido;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
