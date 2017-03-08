

package application.content;

import static application.shared.Assets.*;

import application.components.JWaveView;
import application.controllers.WavesListController;
import application.models.WaveModel;
import application.structures.Wave;

import javax.swing.*;
import java.util.ArrayList;

public class WaveUI extends MainFrameUI {

    //region Instance Variables
    private JList<String> wavesList;
    private JWaveView waveView;

    private WavesListController ctrlList;
    //endregion

    //region UI Setup
    /**
     * Creates the menu bar for this View
     */
    protected void setupMenu() {
        JMenu jmFile = createJMenu("File", 'F');
        JMenu jmEdit = createJMenu("Edit", 'E');
        JMenu jmSimulate = createJMenu("Simulate", 'S');
        JMenu jmHelp = createJMenu("Help", 'H');

        JMenuItem jmiNew = createJMenuItem("New", 'N', icoNew, ksNew);
        JMenuItem jmiOpen = createJMenuItem("Open", 'O', icoOpen, ksOpen);
        JMenuItem jmiSave = createJMenuItem("Save", 'S', icoSave, ksSave);
        JMenuItem jmiSaveAs = createJMenuItem("Save As", 'v', null, ksSaveAs);
        JMenuItem jmiPrint = createJMenuItem("Print", 'P', icoPrint, ksPrint);
        JMenuItem jmiPrintPreview = createJMenuItem("Print Preview", 'r', icoPrintPreview, ksPrintPreview);
        JMenuItem jmiExit = createJMenuItem("Exit", 'x', null, null);

        jmFile.add(jmiNew);
        jmFile.addSeparator();
        jmFile.add(jmiOpen);
        jmFile.addSeparator();
        jmFile.add(jmiSave);
        jmFile.add(jmiSaveAs);
        jmFile.addSeparator();
        jmFile.add(jmiPrint);
        jmFile.add(jmiPrintPreview);
        jmFile.addSeparator();
        jmFile.add(jmiExit);

        JMenuItem jmiUndo = createJMenuItem("Undo", 'U', icoUndo, ksUndo);
        JMenuItem jmiRedo = createJMenuItem("Redo", 'R', icoRedo, ksRedo);
        JMenuItem jmiCut = createJMenuItem("Cut", 'u', icoCut, ksCut);
        JMenuItem jmiCopy = createJMenuItem("Copy", 'C', icoCopy, ksCopy);
        JMenuItem jmiPaste = createJMenuItem("Paste", 'P', icoPaste, ksPaste);

        jmEdit.add(jmiUndo);
        jmEdit.add(jmiRedo);
        jmEdit.addSeparator();
        jmEdit.add(jmiCut);
        jmEdit.add(jmiCopy);
        jmEdit.add(jmiPaste);

        JMenuItem jmiAttach = createJMenuItem("Attach", 'A', icoAttach, ksAttach);
        JMenuItem jmiDelete = createJMenuItem("Delete", 'D', icoDelete, ksDelete);
        JMenuItem jmiEdit = createJMenuItem("Edit", 'E', icoEdit, ksEdit);
        JMenuItem jmiMoveUp = createJMenuItem("Move Up", 'U', icoUp, ksUp);
        JMenuItem jmiMoveDown = createJMenuItem("Move Down", 'D', icoDown, ksDown);
        JMenuItem jmiResultant = createJMenuItem("Find Points", 'e', icoResultant, ksResultant);

        jmiResultant.addActionListener(ctrlList);
        jmiExit.addActionListener(ctrlList);
        jmiAttach.addActionListener(ctrlList);
        jmiEdit.addActionListener(ctrlList);
        jmiDelete.addActionListener(ctrlList);
        jmiCopy.addActionListener(ctrlList);
        jmiCut.addActionListener(ctrlList);
        jmiPaste.addActionListener(ctrlList);
        jmiNew.addActionListener(ctrlList);
        jmiOpen.addActionListener(ctrlList);
        jmiSave.addActionListener(ctrlList);
        jmiSaveAs.addActionListener(ctrlList);
        jmiMoveDown.addActionListener(ctrlList);
        jmiMoveUp.addActionListener(ctrlList);
        jmiUndo.addActionListener(ctrlList);
        jmiRedo.addActionListener(ctrlList);
        jmiPaste.addActionListener(ctrlList);
        jmiPrintPreview.addActionListener(ctrlList);

        jmiResultant.setActionCommand(cmdResultant);
        jmiExit.setActionCommand(cmdExit);
        jmiAttach.setActionCommand(cmdAttach);
        jmiEdit.setActionCommand(cmdEdit);
        jmiDelete.setActionCommand(cmdDelete);
        jmiCopy.setActionCommand(cmdCopy);
        jmiCut.setActionCommand(cmdCut);
        jmiPaste.setActionCommand(cmdPaste);
        jmiNew.setActionCommand(cmdNew);
        jmiOpen.setActionCommand(cmdOpen);
        jmiSave.setActionCommand(cmdSave);
        jmiSaveAs.setActionCommand(cmdSaveAs);
        jmiMoveDown.setActionCommand(cmdDown);
        jmiMoveUp.setActionCommand(cmdUp);
        jmiUndo.setActionCommand(cmdUndo);
        jmiPaste.setActionCommand(cmdPaste);
        jmiPrintPreview.setActionCommand(cmdPrintPreview);

        jmSimulate.add(jmiAttach);
        jmSimulate.add(jmiDelete);
        jmSimulate.add(jmiEdit);
        jmSimulate.add(jmiMoveUp);
        jmSimulate.add(jmiMoveDown);
        jmSimulate.addSeparator();
        jmSimulate.add(jmiResultant);
        jmSimulate.addSeparator();

        this.menuBar.add(jmFile);
        this.menuBar.add(jmEdit);
        this.menuBar.add(jmSimulate);
        this.menuBar.add(jmHelp);
    }

    /**
     * Creates the tool bar for this view
     */
    protected void setupToolbar() {
        JButton btnNew = new JButton(icoNew);
        JButton btnOpen = new JButton(icoOpen);
        JButton btnSave = new JButton(icoSave);
        JButton btnPrint = new JButton(icoPrint);
        JButton btnPrintPreview = new JButton(icoPrintPreview);
        JButton btnUndo = new JButton(icoUndo);
        JButton btnRedo = new JButton(icoRedo);
        JButton btnCut = new JButton(icoCut);
        JButton btnCopy = new JButton(icoCopy);
        JButton btnPaste = new JButton(icoPaste);
        JButton btnAttach = new JButton(icoAttach);
        JButton btnDelete = new JButton(icoDelete);
        JButton btnEdit = new JButton(icoEdit);
        JButton btnUp = new JButton(icoUp);
        JButton btnDown = new JButton(icoDown);
        JToggleButton btnLock = new JToggleButton(icoLock);
        JButton btnZoom = new JButton(icoZoom);
        JButton btnResultant = new JButton("Find Points");

        btnNew.setFocusable(false);
        btnOpen.setFocusable(false);
        btnSave.setFocusable(false);
        btnPrint.setFocusable(false);
        btnPrintPreview.setFocusable(false);
        btnUndo.setFocusable(false);
        btnRedo.setFocusable(false);
        btnCut.setFocusable(false);
        btnCopy.setFocusable(false);
        btnPaste.setFocusable(false);
        btnAttach.setFocusable(false);
        btnDelete.setFocusable(false);
        btnEdit.setFocusable(false);
        btnUp.setFocusable(false);
        btnDown.setFocusable(false);
        btnLock.setFocusable(false);
        btnZoom.setFocusable(false);
        btnResultant.setFocusable(false);

        btnAttach.addActionListener(ctrlList);
        btnEdit.addActionListener(ctrlList);
        btnDelete.addActionListener(ctrlList);
        btnUp.addActionListener(ctrlList);
        btnDown.addActionListener(ctrlList);

        btnSave.addActionListener(ctrlList);
        btnOpen.addActionListener(ctrlList);
        btnNew.addActionListener(ctrlList);

        btnLock.addActionListener(ctrlList);
        btnZoom.addActionListener(ctrlList);

        btnPrint.addActionListener(ctrlList);
        btnPrintPreview.addActionListener(ctrlList);

        btnResultant.addActionListener(ctrlList);

        btnCut.addActionListener(ctrlList);
        btnCopy.addActionListener(ctrlList);
        btnPaste.addActionListener(ctrlList);

        btnUndo.addActionListener(ctrlList);
        btnRedo.addActionListener(ctrlList);

        btnAttach.setActionCommand(cmdAttach);
        btnDelete.setActionCommand(cmdDelete);
        btnUp.setActionCommand(cmdUp);
        btnDown.setActionCommand(cmdDown);
        btnEdit.setActionCommand(cmdEdit);

        btnSave.setActionCommand(cmdSave);
        btnOpen.setActionCommand(cmdOpen);
        btnNew.setActionCommand(cmdNew);

        btnZoom.setActionCommand(cmdZoom);
        btnLock.setActionCommand(cmdLock);

        btnResultant.setActionCommand(cmdResultant);

        btnCut.setActionCommand(cmdCut);
        btnCopy.setActionCommand(cmdCopy);
        btnPaste.setActionCommand(cmdPaste);

        btnUndo.setActionCommand(cmdUndo);
        btnRedo.setActionCommand(cmdRedo);

        btnPrint.setActionCommand(cmdPrint);
        btnPrintPreview.setActionCommand(cmdPrintPreview);

        this.toolBar.add(btnNew);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnOpen);
        this.toolBar.addSeparator();
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.addSeparator();
        this.toolBar.add(btnUndo);
        this.toolBar.add(btnRedo);
        this.toolBar.addSeparator();
        this.toolBar.add(btnCut);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnPaste);
        this.toolBar.addSeparator();
        this.toolBar.add(btnAttach);
        this.toolBar.add(btnDelete);
        this.toolBar.add(btnEdit);
        this.toolBar.addSeparator();
        this.toolBar.add(btnUp);
        this.toolBar.add(btnDown);
        this.toolBar.addSeparator();
        this.toolBar.add(btnLock);
        this.toolBar.add(btnZoom);
        this.toolBar.addSeparator();
        this.toolBar.add(btnResultant);

    }

    /**
     * Sets up the main content pane for this view
     */
    protected void setupContent() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportView(wavesList);

        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(waveView);

        this.content.add(splitPane);
    }
    //endregion

    //region Pre and Post Setup
    /**
     * Initializes the instance variables of the class
     */
    @Override
    protected void preSetup() {
        ArrayList<Wave> wavesData = new ArrayList<>();
        this.wavesList = new JList<>();
        this.waveView = new JWaveView();

        WaveModel model = new WaveModel(wavesData, wavesList, waveView);

        this.ctrlList = new WavesListController(model);
    }

    @Override
    protected void postSetup() {
        this.wavesList.getInputMap().clear();
    }
    //endregion
}
