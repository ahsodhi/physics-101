package application.utils;

import java.util.ArrayList;

/**
 * Created by:  Anirudh Sodhi
 * Date:        2016-01-17
 * File:        ${FILE_NAME}
 * Description:
 */
public class ListUtils {
    public static <T> T getLast(ArrayList<T> list) {
        return list.get(list.size() - 1);
    }

    private static <T> void swap(ArrayList<T> list, int indexa, int indexb) {
        T a = list.get(indexa);
        T b = list.get(indexb);

        list.set(indexa, b);
        list.set(indexb, a);
    }

    public static <T> void moveDown(ArrayList<T> list, int index) {
        if (!canMoveDown(list, index))
            return;

        ListUtils.swap(list, index, index + 1);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean canMoveDown(ArrayList list, int index) {
        return index < list.size() - 1;
    }

    public static <T> void moveUp(ArrayList<T> list, int index) {
        if (!canMoveUp(list, index))
            return;

        ListUtils.swap(list, index, index - 1);
    }

    @SuppressWarnings({"UnusedParameters", "BooleanMethodIsAlwaysInverted"})
    public static boolean canMoveUp(ArrayList list, int index) {
        return index > 0;
    }

    public static <T> ArrayList<T> getItems(ArrayList<T> list, int... indices) {
        ArrayList<T> items = new ArrayList<>();

        for (int index : indices)
            items.add(list.get(index));

        return items;
    }

    public static void removeItems(ArrayList list, int...indices) {
        for (int index : indices)
            list.remove(index);
    }
}
