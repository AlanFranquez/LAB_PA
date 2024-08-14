package serverCentral;

public class Sistema implements ISistema {
    private static Sistema instance = null;

    private Sistema() {
        //  colecciones
    }

    public static synchronized Sistema getInstance() {
        if (instance == null) {
            instance = new Sistema();
        }
        return instance;
    }

    //DESTRUCTOR¿?
}
