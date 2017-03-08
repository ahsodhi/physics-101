/**
 * File:        Wave.java
 * Created By:  Anirudh Sodhi
 * Date:        January 21, 2016
 * Description: Used to store and access data for a Wave object
 */

package application.structures;

import java.util.TreeMap;

public class Wave implements Cloneable
{
    //region Instance Variables
    private int startOffset;
    private String name;
    private double waveLength;
    private double amplitude;
    private double frequency;
    private long numCycles;
    private TreeMap<Double, Double> points;
    //endregion

    //region Constructor
    /**
     * Constructor creates a new Wave and initializes its instance variables with the given parameter values
     * @param startOffset   The vertical starting offset from which the wave should be drawn on the JWaveView
     * @param name          The name of the Wave object
     * @param waveLength    The wavelength of the Wave
     * @param amplitude     The amplitude of the Wave
     * @param frequency     The frequency of the Wave
     * @param numCycles     The number of cycles to display for the Wave
     */
    public Wave(int startOffset, String name, double waveLength, double amplitude, double frequency, long numCycles) {
        this.startOffset = startOffset;
        this.name = name;
        this.waveLength = waveLength;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.numCycles = numCycles;
        points = new TreeMap<>();
    }
    //endregion

    //region Getters and Setters

    /**
     * Getter method for the vertical starting from which the wave should be drawn on the JWaveView
     * @return  The start offset of this wave
     */
    public int getStartOffset() {
        return startOffset;
    }

    /**
     * Setter method for the vertical starting from which the wave should be drawn on the JWaveView
     * @param startOffset   The start offset to assign to this wave
     */
    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    /**
     * Getter method for the name of this wave
     * @return  The name of this wave
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for the name of this wave
     * @param name  The name to be assigned to this wave
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the wavelength of this wave
     * @return  The wavelength of this wave
     */
    public double getWaveLength() {
        return waveLength;
    }

    /**
     * Setter method for the wavelength of this wave
     * @param waveLength    The wavelength to be assigned to this wave
     */
    public void setWaveLength(double waveLength) {
        this.waveLength = waveLength;
    }

    /**
     * Getter method for the amplitude of this wave
     * @return  The amplitude of this wave
     */
    public double getAmplitude() {
        return amplitude;
    }

    /**
     * Setter method for the amplitude of this wave
     * @param amplitude The amplitude to be assigned to this wave
     */
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    /**
     * Getter method for the frequency of this wave
     * @return  The frequenct of this wave
     */
    public double getFrequency() {
        return frequency;
    }

    /**
     * Setter method for the frequency of this wave
     * @param frequency The frequency to be assigned to this wave
     */
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    /**
     * Getter method for the frequency of this wave
     * @return  The number of cycles to display for this wave
     */
    public long getNumCycles() {
        return numCycles;
    }

    /**
     * Setter method for the frequency of this wave
     * @param numCycles The number of displayed cycles to be assigned to this wave
     */
    public void setNumCycles(long numCycles) {
        this.numCycles = numCycles;
    }

    /**
     * Getter method for the list of points in the graph of this wave
     * @return  List of points in this wave's graph
     */
    public TreeMap<Double, Double> getPoints() { return this.points; }
    //endregion

    /**
     * Adds a point to the instance variable containing points for graph of the wave
     * @param x The x value of the point to be added
     * @param y The y value of the point to be added
     */
    public void addPoint(double x, double y) {
        this.points.put(x, y);
    }

    @Override
    public Wave clone() throws CloneNotSupportedException {
        try {
            return ((Wave) super.clone());
        } catch (CloneNotSupportedException e) {
            return new Wave(this.getStartOffset(),
                    this.getName(),
                    this.getWaveLength(),
                    this.getAmplitude(),
                    this.getFrequency(),
                    this.getNumCycles());
        }
    }
}