package cl.app.arg.compra.util;

public class AppUtils {
    private static AppUtils instance;

    public static AppUtils getInstance() {
        if( instance == null)
            instance = new AppUtils();
        return instance;
    }

    private AppUtils(){}

    public static String getUUID(){
        return java.util.UUID.randomUUID().toString();
    }
}
