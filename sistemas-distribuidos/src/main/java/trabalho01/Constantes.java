package trabalho01;

public enum Constantes {

    BASEPATH("C:\\TEMP\\SD\\"),
    PORTA("5000"),
    BASEIP("224.1.1.1");

    String s;

    Constantes(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    public Integer toNumeric(){
        return Integer.parseInt(s);
    }
}
