package application.shared;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.security.SecureClassLoader;


public final class Assets {
    public final static String cmdNew = "NEW";
    public final static String cmdOpen = "OPEN";
    public final static String cmdSave = "SAVE";
    public final static String cmdSaveAs = "SAVEAS";
    public final static String cmdPrint = "PRINT";
    public final static String cmdPrintPreview = "PRINTPREVIEW";
    public final static String cmdExit = "EXIT";

    public final static String cmdAttach = "ATTACH";
    public final static String cmdEdit = "EDIT";
    public final static String cmdDelete = "DELETE";
    public final static String cmdUp = "UP";
    public final static String cmdDown = "DOWN";

    public final static String cmdCut = "CUT";
    public final static String cmdCopy = "COPY";
    public final static String cmdPaste = "PASTE";

    public final static String cmdUndo = "UNDO";
    public final static String cmdRedo = "REDO";

    public final static String cmdZoom = "ZOOM";
    public final static String cmdLock = "LOCK";

    public final static String cmdResultant = "RESULTANT";
    public final static String cmdRun = "RUN";

    public final static String cmdEnergy = "ENERGY";

    public final static ImageIcon icoNew = new ImageIcon(SecureClassLoader.getSystemResource("New document.png"));
    public final static ImageIcon icoOpen = new ImageIcon(SecureClassLoader.getSystemResource("Folder.png"));
    public final static ImageIcon icoSave = new ImageIcon(SecureClassLoader.getSystemResource("Save.png"));
    public final static ImageIcon icoPrint = new ImageIcon(SecureClassLoader.getSystemResource("Print.png"));
    public final static ImageIcon icoPrintPreview = new ImageIcon(SecureClassLoader.getSystemResource("Text preview.png"));

    public final static ImageIcon icoLock = new ImageIcon(SecureClassLoader.getSystemResource("Lock.png"));

    public final static ImageIcon icoUndo = new ImageIcon(SecureClassLoader.getSystemResource("Undo.png"));
    public final static ImageIcon icoRedo = new ImageIcon(SecureClassLoader.getSystemResource("Redo.png"));
    public final static ImageIcon icoCut = new ImageIcon(SecureClassLoader.getSystemResource("Cut.png"));
    public final static ImageIcon icoCopy = new ImageIcon(SecureClassLoader.getSystemResource("Copy.png"));
    public final static ImageIcon icoPaste = new ImageIcon(SecureClassLoader.getSystemResource("Paste.png"));

    public final static ImageIcon icoAttach = new ImageIcon(SecureClassLoader.getSystemResource("Create.png"));
    public final static ImageIcon icoDelete = new ImageIcon(SecureClassLoader.getSystemResource("Delete.png"));
    public final static ImageIcon icoEdit = new ImageIcon(SecureClassLoader.getSystemResource("Modify.png"));
    public final static ImageIcon icoUp = new ImageIcon(SecureClassLoader.getSystemResource("Up.png"));
    public final static ImageIcon icoDown = new ImageIcon(SecureClassLoader.getSystemResource("Down.png"));

    public final static ImageIcon icoRun = new ImageIcon(SecureClassLoader.getSystemResource("Go.png"));
    public final static ImageIcon icoResultant = new ImageIcon(SecureClassLoader.getSystemResource("Sum.png"));

    public final static ImageIcon icoZoom = new ImageIcon(SecureClassLoader.getSystemResource("Zoom.png"));

    public final static ImageIcon icoEnergy =  new ImageIcon(SecureClassLoader.getSystemResource("Calculator.png"));

    public final static KeyStroke ksNew = KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK);
    public final static KeyStroke ksOpen = KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK);
    public final static KeyStroke ksSave = KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK);
    public final static KeyStroke ksSaveAs = KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
    public final static KeyStroke ksPrint = KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK);
    public final static KeyStroke ksPrintPreview = KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);

    public final static KeyStroke ksUndo = KeyStroke.getKeyStroke('Z', InputEvent.CTRL_MASK);
    public final static KeyStroke ksRedo = KeyStroke.getKeyStroke('Z', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
    public final static KeyStroke ksCut = KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK);
    public final static KeyStroke ksCopy = KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK);
    public final static KeyStroke ksPaste = KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK);

    public final static KeyStroke ksAttach = KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0);
    public final static KeyStroke ksDelete = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);
    public final static KeyStroke ksEdit = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK);
    public final static KeyStroke ksUp = KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK);
    public final static KeyStroke ksDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK);

    public final static KeyStroke ksRun = KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK);
    public final static KeyStroke ksResultant = KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);

    public final static KeyStroke ksEnergy = KeyStroke.getKeyStroke('E', InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);
}
