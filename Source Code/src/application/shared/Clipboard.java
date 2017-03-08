package application.shared;

import java.lang.reflect.InvocationTargetException;

public class Clipboard {
    private static Object[] data;

    public static Object[] getData() {
        return data;
    }

    public static void setData(Object[] data) {
        Clipboard.data = data;
    }

    public static Object clone(Object item) {
        try {
            return item.getClass().getMethod("clone").invoke(item);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
