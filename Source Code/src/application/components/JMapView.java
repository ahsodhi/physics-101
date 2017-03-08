package application.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class JMapView extends JComponent implements MouseListener, MouseWheelListener, MouseMotionListener {
    private double translateX = 0;
    private double translateY = 0;

    private double zoom = 1;

    private int dragX = 0;
    private int dragY = 0;

    private boolean locked = false;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        if (!locked)
            this.zoom = zoom;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        if (!locked)
            this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        if (!locked)
            this.translateY = translateY;
    }

    private int getDragX() {
        return dragX;
    }

    private void setDragX(int dragX) {
        this.dragX = dragX;
    }

    private int getDragY() {
        return dragY;
    }

    private void setDragY(int dragY) {
        this.dragY = dragY;
    }

    private void translateX(double amount) {
        if (!locked)
            this.translateX += amount;
    }

    private void translateY(double amount) {
        if (!locked)
            this.translateY += amount;
    }

    private void zoom(double factor) {
        if (!locked)
            this.zoom *= factor;
    }

    //region Unimplemented

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //endregion

    JMapView() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }

    //endregion

    //region Paint

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.paintComponent(((Graphics2D) g));
    }

    void paintComponent(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.translate(translateX, translateY + this.getHeight());
        g.scale(zoom, -zoom);
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    }

    //endregion

    //region Implemented

    @Override
    public void mousePressed(MouseEvent e) {
        this.setDragX(e.getX());
        this.setDragY(this.getHeight() - e.getY());

        this.requestFocus();

        this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        this.transferFocus();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.hasFocus()) {
            int currentX = e.getX();
            int currentY = this.getHeight() - e.getY();

            int deltax = currentX - this.getDragX();
            int deltay = currentY - this.getDragY();

            this.translateX(deltax);
            this.translateY(-deltay);

            this.setDragX(currentX);
            this.setDragY(currentY);

            this.repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.zoom(e.getPreciseWheelRotation() != 1 ? 1.2 : 0.8);

        this.repaint();
    }

    public void zoom(double width, double height, double tx, double ty) {
        double propW = this.getWidth() / width;
        double propH = this.getHeight() / height;
        this.zoom = Math.min(propW, propH);

        this.translateX = -tx * zoom;
        this.translateY = ty * zoom;

        this.repaint();
    }

    public void resetView() {
        this.translateX = 0;
        this.translateY = 0;

        this.zoom = 1;

        this.repaint();
    }
}
