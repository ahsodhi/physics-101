package application.content;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("EmptyMethod")
abstract class MainFrameUI extends JPanel {
    JToolBar toolBar;
    JMenuBar menuBar;
    JPanel content;

    MainFrameUI() {
        super(new GridBagLayout());

        this.toolBar = new JToolBar();
        this.menuBar = new JMenuBar();
        this.content = new JPanel(new BorderLayout());

        this.preSetup();

        this.setupMenu();
        this.setupToolbar();
        this.setupContent();

        this.postSetup();

        this.attachComponents();
    }

    private void attachComponents () {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 1;
        constraints.weighty = 0;

        constraints.gridx = 0;
        constraints.gridy = 0;

        this.add(menuBar, constraints);

        this.content.add(toolBar, BorderLayout.NORTH);

        constraints.weightx = 1;
        constraints.weighty = 1;

        constraints.gridx = 0;
        constraints.gridy = 1;

        this.add(content, constraints);
    }

    protected abstract void setupMenu();
    protected abstract void setupToolbar();
    protected abstract void setupContent();
    protected abstract void preSetup();
    protected abstract void postSetup();

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        if (toolBar != null)
            this.toolBar = toolBar;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        if (menuBar != null)
            this.menuBar = menuBar;
    }

    public JPanel getContent() {
        return content;
    }

    public void setContent(JPanel content) {
        if (content != null)
            this.content = content;
    }

    JMenuItem createJMenuItem(String text,
                              char mnemonic,
                              Icon icon,
                              KeyStroke accelerator) {
        JMenuItem jmi = new JMenuItem();

        jmi.setAccelerator(accelerator);
        jmi.setText(text);
        jmi.setMnemonic(mnemonic);
        jmi.setIcon(icon);

        return jmi;
    }

    JMenu createJMenu(String text, char mnemonic) {
        JMenu jm = new JMenu();

        jm.setText(text);
        jm.setMnemonic(mnemonic);

        return jm;
    }
}
