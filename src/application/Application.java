package application;

import application.content.ForceUI;
import application.content.VectorsUI;
import application.content.WaveUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by:  Anirudh Sodhi
 * Date:        2016-01-09
 * File:        ${FILE_NAME}
 * Description:
 */
class Application {
    public static void main(String[] args) {
        new Application();
    }

    private Application() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.getDefaults().put("OptionPane.buttonOrientation", SwingConstants.RIGHT);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());

        frame.add(new WaveUI(), BorderLayout.CENTER);

        frame.pack();
        frame.setTitle("Physics 101");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
