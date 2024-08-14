package serverCentral;

public class Factory {
    public static ISistema getSistema() {
        return Sistema.getInstance();
    }
}
