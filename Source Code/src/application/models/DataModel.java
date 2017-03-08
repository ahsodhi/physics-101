package application.models;

import application.shared.Clipboard;
import application.utils.ListUtils;

import java.awt.print.Printable;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;


public abstract class DataModel<D extends Cloneable> implements Printable {
    final ArrayList<D> data;
    private final ArrayList<D[]> history;

    private int current;

    public abstract void read(Scanner in);
    public abstract void write(PrintWriter out);
    protected abstract void update();

    DataModel(ArrayList<D> data) {
        this.data = data;

        this.history = new ArrayList<>();
        this.current = 0;

        setHistory();
    }

    void cut(int... indices) {
        copy(indices);

        ListUtils.removeItems(data, indices);

        setHistory();

        update();
    }

    void copy(int... indices) {
        Clipboard.setData(ListUtils.getItems(data, indices).toArray());
    }

    @SuppressWarnings("unchecked")
    void paste() {
        for (Object item : Clipboard.getData())
            if (item.getClass() == getDataType())
                data.add((D) Clipboard.clone(item));

        setHistory();

        update();
    }

    public void clear() {
        data.clear();
        this.update();
    }

    public void undo() {
        if (canUndo()) {
            this.data.clear();

            Collections.addAll(this.data, history.get(++current));

            this.update();
        }
    }

    public void redo() {
        if (canRedo()) {
            this.data.clear();

            Collections.addAll(this.data, history.get(--current));

            update();
        }
    }

    protected abstract Class getDataType();

    private boolean canRedo() {
        return current > 0;
    }

    private boolean canUndo() {
        return current < this.history.size() - 1;
    }

    @SuppressWarnings("unchecked")
    private void setHistory() {
        this.history.add(0, data.toArray(((D[]) Array.newInstance(getDataType(), 0))).clone());

        if (this.history.size() > 10)
            this.history.remove(10);
    }

    //region List Methods

    void attach(D item) {
        this.data.add(item);

        setHistory();

        update();
    }

    void delete(int index) {
        data.remove(index);

        setHistory();

        update();
    }

    void edit(D item, int index) {
        data.set(index, item);

        setHistory();

        update();
    }

    void movedown(int index) {
        if (!ListUtils.canMoveDown(data, index))
            return;

        ListUtils.moveDown(data, index);

        setHistory();

        update();
    }

    void moveup(int index) {
        if (!ListUtils.canMoveUp(data, index))
            return;

        ListUtils.moveUp(data, index);

        setHistory();

        update();
    }

    //endregion
}
