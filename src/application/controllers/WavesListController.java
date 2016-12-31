/**
 * File:        WavesListController.java
 * Created By:  Anirudh Sodhi
 * Date:        January 21, 2016
 * Description: Provides functionality for the all controller objects related to Waves
 */

package application.controllers;

import application.models.WaveModel;
import application.utils.FileUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static application.shared.Assets.*;


public class WavesListController implements ActionListener {

    //region Instance variables
    private final WaveModel model;
    //endregion

    //region Constructor
    /**
     * Constructor connects this controller class to the Model class
     * @param model The Model class to be connected with this controller class
     */
    public WavesListController(WaveModel model) {
        this.model = model;
    }
    //endregion

    //region Action Listener
    /**
     * Specifies the action-response in the Model class according to the action performed by the user
     * @param e The type of action performed by the user
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case cmdAttach:
                this.model.attach();
                break;
            case cmdDelete:
                this.model.delete();
                break;
            case cmdDown:
                this.model.movedown();
                break;
            case cmdUp:
                this.model.moveup();
                break;
            case cmdEdit:
                this.model.edit();
                break;
            case cmdSave:
                FileUtils.saveFile(model, "Wave File", "wave");
                break;
            case cmdSaveAs:
                FileUtils.saveFile(model, "Wave File", "wave");
                break;
            case cmdOpen:
                FileUtils.openFile(model, "Wave File", "wave");
                this.model.resetView();
                break;
            case cmdNew:
                FileUtils.newFile(model, "Wave File", "wave");
                this.model.resetView();
                break;
            case cmdLock:
                this.model.toggleLock();
                break;
            case cmdZoom:
                this.model.zoomView();
                break;
            case cmdResultant:
                this.model.showCalculator();
                break;
            case cmdPrint:
                this.model.print();
                break;
            case cmdPrintPreview:
                this.model.preview();
                break;
            case cmdCut:
                this.model.cut();
                break;
            case cmdCopy:
                this.model.copy();
                break;
            case cmdPaste:
                this.model.paste();
                break;
            case cmdUndo:
                this.model.undo();
                break;
            case cmdRedo:
                this.model.redo();
                break;
            case cmdExit:
                System.exit(0);
                break;
        }
    }
    //endregion
}
