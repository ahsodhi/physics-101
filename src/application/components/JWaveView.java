

package application.components;

import application.structures.Wave;
import application.utils.ListUtils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class JWaveView extends JMapView
{
    //region Instance Variables

    /** ArrayList used to store data for each wave */
    private ArrayList<Wave> waves;

    //endregion

    //region Static Constants
    public static final int TOP_MARGIN = 40;
    public static final int LEFT_MARGIN = 100;
    public static final int RIGHT_MARGIN = 100;

    public static final int WAVE_VSPACING = 50;
    public static final int WAVE_VSCALE = 20;
    public static final int WAVE_HSCALE = 360;

    public static final int LETTER_SPACING = 15;
    public static final int ARROW_SPACING = 10;
    public static final int ARROW_HEAD_SIZE = 10;

    public static final int POINT_INCREMENT = 45;

    public static final int VERTICAL_ARROW = 1;
    public static final int HORIZONTAL_ARROW = 0;
    //endregion

    //region Constructor
    /**
     * Constructor method
     * Initializes the component size and creates a new ArrayList to store wave data
     */
    public JWaveView() {
        this.waves = new ArrayList<>();
        this.setPreferredSize(new Dimension(500, 500));
    }
    //endregion

    //region Paint Methods
    /**
     * Sets up the default look of the JWaveView class
     * @param g The graphics object used to paint
     */
    @Override
    protected void paintComponent(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.translate(getTranslateX(), getTranslateY());
        g.scale(getZoom(), getZoom());
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        for (Wave wave : this.waves)
            this.drawWave(g, wave);
    }

    /**
     * Validates the spacing between each drawn wave
     * @param index The index of the wave to begin the validation from
     */
    @SuppressWarnings("SameParameterValue")
    private void reconnectWaves(int index) {
        if (waves.size() >= 1)
            waves.get(0).setStartOffset(0);

        for (int i = index; i < waves.size(); i++)
            waves.get(i).setStartOffset(((int) (waves.get(i - 1).getStartOffset() + waves.get(i - 1).getAmplitude()*2*WAVE_VSCALE + WAVE_VSPACING)));
    }

    /**
     * Draws a wave on the Component
     * @param g The graphics object used to draw the wave
     * @param wave Wave object containing information about the wave to be drawn
     */
    private void drawWave(Graphics2D g, Wave wave)
    {
        g.setColor(Color.BLACK);

        int xBase   = LEFT_MARGIN;
        int top     = TOP_MARGIN;
        double yScale  = wave.getAmplitude() * WAVE_VSCALE;
        double xAxis   = WAVE_HSCALE * wave.getWaveLength() * wave.getNumCycles() * wave.getFrequency();
        int oneCycle = (int)(xAxis / wave.getNumCycles());
        int oneWavelength = (int)(oneCycle / wave.getFrequency());

        double yBase = top + wave.getStartOffset() + yScale;
        double x, y, xOld, yOld;
        xOld = xBase; yOld = yBase;

        // Draw the axis for the wave
        g.draw( new Line2D.Double(xBase, top + wave.getStartOffset(), xBase, top + wave.getStartOffset() + 2 * yScale) );
        g.draw( new Line2D.Double(xBase, yBase, xBase + xAxis, yBase) );

        // Label the name of the wave
        g.setFont(new Font("Arial", Font.BOLD, 20));
        int nameLetterCount = wave.getName().length();
        g.drawString(wave.getName(), xBase - LETTER_SPACING * nameLetterCount, (int) yBase);

        // Plot the graph of the wave
        g.setColor( Color.RED );

        int currentCycle = 1;
        for( int i=0; i <= xAxis; i++)
        {
            x = xBase + i;
            y = yBase - Math.sin( Math.toRadians(i / wave.getWaveLength()) ) * yScale;

            if (i % POINT_INCREMENT == 0)
                wave.addPoint(i, (double)Math.round(y*10)/10);


            Line2D point = new Line2D.Double(x,y,x,y);
            g.draw(point);

            Line2D line = new Line2D.Double(xOld, yOld, x, y);
            g.draw(line);

            Color darkGreen = new Color(31, 95, 0);

            // Label the Cycles
            if (i == oneCycle * currentCycle) {
                g.setColor(darkGreen);
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Cycle " + (i/oneCycle), (int) x, (int) y + LETTER_SPACING * 2);
                g.setColor(Color.red);
                currentCycle++;
            }

            // Label the wavelength
            if (i == oneWavelength) {
                g.setColor(darkGreen);
                g.setFont(new Font("Arial", Font.BOLD, 15));
                this.drawDoublePointArrow(g, xBase, yBase - yScale - ARROW_SPACING,
                        xBase + oneWavelength, yBase - yScale - ARROW_SPACING, HORIZONTAL_ARROW);
                String waveLength = "Wavelength: ";
                g.drawString(waveLength + wave.getWaveLength(), xBase + (oneWavelength / 2) - LETTER_SPACING * waveLength.length() / 2,
                        (int) (yBase - yScale) - LETTER_SPACING);
                g.setColor(Color.red);
            }

            // Label the amplitude
            if (i == oneWavelength / 4) {
                g.setColor(darkGreen);
                g.setFont(new Font("Arial", Font.BOLD, 15));
                this.drawDoublePointArrow(g, xBase + (oneWavelength / 4), yBase - yScale + ARROW_SPACING,
                        xBase + (oneWavelength / 4), yBase - ARROW_SPACING, VERTICAL_ARROW);
                String amplitude = "Amplitude: ";
                g.drawString(amplitude + wave.getAmplitude(), xBase + (oneWavelength / 4) - LETTER_SPACING  * amplitude.length() / 2,
                        (int) yBase + LETTER_SPACING);
                g.setColor(Color.red);
            }

            xOld = x;
            yOld = y;
        }
    }

    /**
     * Draws double sided arrow on the component with
     * @param g The Graphics object used to draw the arrow
     * @param x1 The x value for the starting point of the arrow
     * @param y1 The y value for the starting point of the arrow
     * @param x2 The x value for the ending point of the arrow
     * @param y2 The y value for the ending point of the arrow
     * @param orientation Specifies whether to draw the arrow horizontally or vertically
     */
    private void drawDoublePointArrow(Graphics2D g, double x1, double y1, double x2, double y2, int orientation)
    {
        g.draw(new Line2D.Double(x1, y1, x2, y2));

        if (orientation == 1) {
            g.draw(new Line2D.Double(x1, y1, x1 - ARROW_HEAD_SIZE, y1 + ARROW_HEAD_SIZE));
            g.draw(new Line2D.Double(x1, y1, x1 + ARROW_HEAD_SIZE, y1 + ARROW_HEAD_SIZE));

            g.draw(new Line2D.Double(x2, y2, x2 - ARROW_HEAD_SIZE, y2 - ARROW_HEAD_SIZE));
            g.draw(new Line2D.Double(x2, y2, x2 + ARROW_HEAD_SIZE, y2 - ARROW_HEAD_SIZE));
        }
        else {
            g.draw(new Line2D.Double(x1, y1, x1 + ARROW_HEAD_SIZE, y1 + ARROW_HEAD_SIZE));
            g.draw(new Line2D.Double(x1, y1, x1 + ARROW_HEAD_SIZE, y1 - ARROW_HEAD_SIZE));

            g.draw(new Line2D.Double(x2, y2, x2 - ARROW_HEAD_SIZE, y2 + ARROW_HEAD_SIZE));
            g.draw(new Line2D.Double(x2, y2, x2 - ARROW_HEAD_SIZE, y2 - ARROW_HEAD_SIZE));
        }
    }
    //endregion

    //region Getters and Setters
    /**
     * Setter method for the instance variables that stores the list of waves
     * @param waves ArrayList of Wave objects to be assigned to the instance variable
     */
    public void setWaves(ArrayList<Wave> waves) {
        this.waves = waves;

        this.reconnectWaves(1);
        this.repaint();
    }

    /**
     * Getter method for the instance variable that stores the list of waves
     * @return ArrayList of Wave objects stored in the instance variable
     */
    public ArrayList<Wave> getWaves()
    {
        return waves;
    }
    //endregion

    //region Zoom and Lock
    /**
     * Enables or disables the ability to move the view by using mouse drag
     */
    public void lockView() { super.setLocked(!super.isLocked()); }

    /**
     * Finds the length of the longest wave horizontally
     * @return The maximum horizontal length found
     */
    public int getMaxX() {
        int max = (int) (waves.get(0).getWaveLength() * waves.get(0).getFrequency() * waves.get(0).getNumCycles() * WAVE_HSCALE)
                + LEFT_MARGIN + RIGHT_MARGIN;

        for (Wave w : waves)
            if (w.getWaveLength() * w.getFrequency() * w.getNumCycles() * WAVE_HSCALE + LEFT_MARGIN + RIGHT_MARGIN > max)
                max = (int) (w.getWaveLength() * w.getFrequency() * w.getNumCycles() * WAVE_HSCALE) + LEFT_MARGIN + RIGHT_MARGIN;

        return max;
    }

    /**
     * Zooms the view to fit everything within the display region
     */
    public void zoom() {
        int height = (int) (ListUtils.getLast(waves).getStartOffset() + ListUtils.getLast(waves).getAmplitude()*2*WAVE_VSCALE + WAVE_VSPACING);
        int width = getMaxX();

        super.zoom(width, height, 0, 0);
    }
    //endregion
}