/**
 * File:        WaveModel.java
 * Created By:  Anirudh Sodhi
 * Date:        January 21, 2016
 * Description: The Model class Waves
 */

package application.models;

import application.components.JWaveView;
import application.structures.Wave;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


@SuppressWarnings("EmptyMethod")
public class WaveModel extends DataModel<Wave> {

    //region Instance Variables
    private final JList<String> list;
    private final JWaveView view;

    private JPanel dialog;
    private JPanel calculator;

    private JTree tree;

    private JFormattedTextField fldName;
    private JFormattedTextField fldWavelength;
    private JFormattedTextField fldFrequency;
    private JFormattedTextField fldAmplitude;
    private JFormattedTextField fldNumCycles;

    private JLabel lblName;
    private JLabel lblWavelength;
    private JLabel lblFrequency;
    private JLabel lblAmplitude;
    private JLabel lblNumCycles;

    private PrinterJob printerJob;
    //endregion

    //region Constructor
    /**
     * Creates a new WaveModel object based on the provided data for waves
     * Sets up the dialog box used to add and edit waves
     * @param data  ArrayList of Wave objects containing information about each wave
     * @param list  List of wave names
     * @param view  The view used to draw the waves
     */
    public WaveModel(ArrayList<Wave> data, JList<String> list, JWaveView view) {
        super(data);
        this.list = list;
        this.view = view;
        this.printerJob = PrinterJob.getPrinterJob();

        this.initDialog();
        this.layoutDialog();
        this.styleDialog();
    }
    //endregion

    //region List Methods
    /**
     * Adds a wave to the list of waves from the given user information
     */
    public void attach() {
        Wave wave = this.getWaveInfo();

        if (wave != null)
            super.attach(wave);
    }

    /**
     * Removes a wave from the selected index in the list
     */
    public void delete() {
        for (int index : list.getSelectedIndices())
        super.delete(index);
    }

    /**
     * Replaces the wave at a specific index with a new wave
     */
    public void edit() {
        Wave wave = this.getWaveInfo(data.get(list.getSelectedIndex()));

        if (wave != null)
            super.edit(wave, list.getSelectedIndex());
    }

    /**
     * Swaps the wave at the selected index with the one below the selected index
     */
    public void movedown() {
        super.movedown(list.getSelectedIndex());
    }

    /**
     * Swaps the wave at the selected index with the one above the selected index
     */
    public void moveup() {
        super.moveup(list.getSelectedIndex());
    }

    /**
     * Syncs the wave data with the View class
     * Updates the list of wave names
     */
    @Override
    protected void update() {
        String[] names = new String[data.size()];

        for (int index = 0; index < data.size(); index++)
            names[index] = data.get(index).getName();

        this.list.setListData(names);
        this.view.setWaves(data);
    }

    public void cut() {
        super.cut(list.getSelectedIndices());
    }

    public void copy() {
        super.copy(list.getSelectedIndices());
    }

    public void paste() {
        super.paste();
    }
    //endregion

    //region Dialog
    /**
     * Displays a input dialog for adding a new wave
     * @return  The Wave object containing information about the new Wave given by user
     */
    private Wave getWaveInfo() {
        return this.getWaveInfo(null);
    }

    /**
     * Displays a input dialog based on pre-existing wave data
     * @param info  The Wave data used to set up the dialog
     * @return  The Wave object containing information about the new Wave given by user
     */
    private Wave getWaveInfo(Wave info) {
        if (info != null)
            loadData(info);

        do {
            if (JOptionPane.showConfirmDialog(
                    null,
                    dialog,
                    "Wave Info",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE) != JOptionPane.OK_OPTION)
                return null;

            if (!isDataValid())
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid data was entered.",
                        "Message",
                        JOptionPane.ERROR_MESSAGE,
                        null);

        } while (!isDataValid());

        return new Wave(
                data.size() > 0 ? ((int) (data.get(data.size() - 1).getStartOffset() + 50 + getAmplitude() * 2)) : 0,
                getWaveName(),
                getWavelength(),
                getAmplitude(),
                getFrequency(),
                getNumCycles());
    }

    /**
     * Sets up the layout of the input dialog for waves
     */
    private void layoutDialog() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(1, 1, 1, 1);
        constraints.weightx = 1;
        constraints.weighty = 0;

        constraints.gridx = 1;
        constraints.gridy = 1;

        dialog.add(fldName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;

        dialog.add(fldAmplitude, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;

        dialog.add(fldNumCycles, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;

        dialog.add(fldFrequency, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;

        dialog.add(fldWavelength, constraints);

        constraints.gridwidth = 1;

        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.ipadx = 25;

        constraints.gridx = 0;
        constraints.gridy = 1;

        dialog.add(lblName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;

        dialog.add(lblAmplitude, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;

        dialog.add(lblNumCycles, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;

        dialog.add(lblFrequency, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        dialog.add(lblWavelength, constraints);
    }

    /**
     * Styles the components inside the wave input dialog
     */
    private void styleDialog() {

    }

    /**
     * Sets up the wave input dialog and its components
     */
    private void initDialog() {
        this.dialog = new JPanel(new GridBagLayout());

        this.lblName = new JLabel("Name:");
        this.lblWavelength = new JLabel("Wavelength:");
        this.lblFrequency = new JLabel("Frequency (Waves Per Cycle):");
        this.lblAmplitude = new JLabel("Amplitude:");
        this.lblNumCycles = new JLabel("Number of Cycles:");

        this.fldName = new JFormattedTextField("");
        this.fldAmplitude = new JFormattedTextField(0d);
        this.fldFrequency = new JFormattedTextField(0d);
        this.fldNumCycles = new JFormattedTextField(1);
        this.fldWavelength = new JFormattedTextField(0d);
    }

    /**
     * Checks for validity of data in the wave input dialog
     * @return  True or false based on whether the input was valid or not
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isDataValid() {
        return getWaveName().length() > 0
                && getAmplitude() > 0
                && getNumCycles() > 0
                && getFrequency() > 0
                && getWavelength() > 0;
    }

    /**
     * Loads suggestions for input data based on pre-existing wave data
     * @param info  The Wave object used to load suggestions for input data
     */
    private void loadData(Wave info) {
        setWaveName(info.getName());
        setFrequency(info.getFrequency());
        setWavelength(info.getWaveLength());
        setNumCycles(info.getNumCycles());
        setAmplitude(info.getAmplitude());
    }
    //endregion

    //region Calculator
    /**
     * Displays a dialog box showing the set of points for each added wave in the form of a tree structure
     * @param rootNode  The root tree node containing sub-nodes for each added wave
     */
    private void initCalculator(DefaultMutableTreeNode rootNode) {
        this.calculator = new JPanel();
        this.tree = new JTree(rootNode);
    }

    /**
     * Sets up the layout for the calculator dialog that displays set of points for each added wave
     */
    private void layoutCalculator() {
        JScrollPane scrollPane = new JScrollPane(this.tree);
        this.calculator.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(300, 300));
    }

    /**
     * Displays the calculator dialog showing list of all added waves
     * and their X,Y points in the form of a tree structure
     */
    public void showCalculator() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("[WAVES LIST]");
        for (Wave w : this.data) {
            DefaultMutableTreeNode waveNode = new DefaultMutableTreeNode(w.getName() +
                    " (Equation: " + w.getAmplitude() + " sin " + (360/w.getWaveLength()) + ")");
            for (Double x : w.getPoints().keySet() ) {
                Double y = w.getPoints().get(x);
                DefaultMutableTreeNode pointNode = new DefaultMutableTreeNode("X: " + x + "          Y: " + y);
                waveNode.add(pointNode);
            }
            rootNode.add(waveNode);
        }

        initCalculator(rootNode);
        layoutCalculator();

        JOptionPane.showMessageDialog(null, calculator, "Find Points", JOptionPane.PLAIN_MESSAGE);
    }
    //endregion

    //region Getters and Setters

    /**
     * Getter method for the inputted wave name
     * @return  The inputted wave name
     */
    private String getWaveName() {
        return this.fldName.getText();
    }

    /**
     * Getter method for the inputted amplitude of the wave
     * @return  The inputted wave amplitude
     */
    private double getAmplitude() {
        return ((Number) this.fldAmplitude.getValue()).doubleValue();
    }

    /**
     * Getter method for the inputted number of cycles to display for the wave
     * @return  The inputted value for number of cycles
     */
    private long getNumCycles() { return ((Number) this.fldNumCycles.getValue()).intValue();}

    /**
     * Getter method for the inputted wave frequency
     * @return  The inputted value for wave name
     */
    private double getFrequency () {return ((Number) this.fldFrequency.getValue()).doubleValue(); }

    /**
     * Getter method for the inputted wave length
     * @return  The inputted value for wave length
     */
    private double getWavelength() {return ((Number) this.fldWavelength.getValue()).doubleValue();}

    /**
     * Setter method for the input suggestion of wave name
     * @return  The wave name to be suggested for input
     */
    private void setWaveName(String name) {
        this.fldName.setValue(name);
    }

    /**
     * Setter method for the input suggestion of wave amplitude
     */
    private void setAmplitude(double angle) {
        this.fldAmplitude.setValue(angle);
    }

    /**
     * Setter method for the input suggestion for number of cycles of the wave
     */
    private void setNumCycles(long cycles) {
        this.fldNumCycles.setValue(cycles);
    }

    /**
     * Setter method for the input suggestion of wave frequency
     */
    private void setFrequency (double freq) {
        this.fldFrequency.setValue(freq);
    }

    /**
     * Setter method for the input suggestion of wave length
     */
    private void setWavelength(double wavelength) {
        this.fldWavelength.setValue(wavelength);
    }
    //endregion

    //region FileIO
    /**
     * Reads wave data from Scanner object and adds it to the current wave data
     * @param in    The Scanner object to read wave data from
     */
    @Override
    public void read(Scanner in) {
        while (in.hasNext()) {
            double amplitude = in.nextDouble();
            int numCycles = in.nextInt();
            double frequency = in.nextDouble();
            double wavelength = in.nextDouble();
            int startOffset = in.nextInt();
            String name = in.nextLine();

            data.add(new Wave(startOffset,
                    name,
                    wavelength,
                    amplitude,
                    frequency,
                    numCycles));
        }

        this.update();
    }

    /**
     * Writes data for each added wave to a PrintWriter object
     * @param out   The PrintWriter object to write to
     */
    @Override
    public void write(PrintWriter out) {
        for (Wave v : data) {
            out.print(v.getAmplitude() + " ");
            out.print(v.getNumCycles() + " ");
            out.print(v.getFrequency() + " ");
            out.print(v.getWaveLength() + " ");
            out.print(v.getStartOffset() + " ");
            out.print(v.getName());
            out.println();
        }

    }
    //endregion

    //region Data Type
    /**
     * Gets the data type of this class
     * @return  The Wave class as a data type
     */
    @Override
    protected Class getDataType() {
        return Wave.class;
    }
    //endregion

    //region Print Methods
    /**
     * Prints the waves drawn by JWaveView class
     * @param graphics  The Graphics object used for drawing the page
     * @param pageFormat    The format settings of the page to print
     * @param pageIndex The index of the page to print
     * @return  An integer value depending on whether the printing was successful
     * @throws PrinterException Error thrown when there is an error in printing
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        graphics.translate((int) pageFormat.getImageableX(), (int)pageFormat.getImageableY());

        Dimension old = this.view.getSize();

        this.view.setSize((int) pageFormat.getImageableWidth(), (int)pageFormat.getImageableHeight());

        this.view.zoom();
        this.view.printAll(graphics);

        this.view.setSize(old);
        this.view.zoom();

        return PAGE_EXISTS;
    }

    public void print() {
        Book book = new Book();
        book.append(this, printerJob.defaultPage());

        printerJob.setPageable(book);

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public void preview() {
        printerJob.pageDialog(printerJob.defaultPage());
    }
    //endregion

    //region View Methods
    /**
     * Toggles the locked state of the JWaveView class
     */
    public void toggleLock() { this.view.lockView(); }

    /**
     * Triggers the zoom method of the JWaveView class
     */
    public void zoomView() {
        this.view.zoom();
    }

    /**
     * Moves the position of the JWaveView back to original (0,0)
     */
    public void resetView() { this.view.resetView(); }
    //endregion
}
