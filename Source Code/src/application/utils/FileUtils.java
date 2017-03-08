package application.utils;

import application.models.DataModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by:  Anirudh Sodhi
 * Date:        2016-01-18
 * File:        ${FILE_NAME}
 * Description:
 */
public final class FileUtils {
    private static File current;

    public static void openFile(DataModel model, String description, String extension) {
        promptSave(model, description, extension);

        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter(description, extension));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setDialogTitle("Open File.");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            current = chooser.getSelectedFile();

            try {
                Scanner scanner = new Scanner(current);

                model.clear();
                model.read(scanner);

                scanner.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "File was not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void newFile(DataModel model, String description, String extension) {
        promptSave(model, description, extension);
        current = null;
        model.clear();
    }

    private static void promptSave(DataModel model, String description, String extension) {
        if (JOptionPane.showConfirmDialog(
                null,
                "Would you like to save the current document?",
                "Message",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            saveFile(model, description, extension);
    }

    public static void saveFile(DataModel model, String description, String extension) {
        if (current == null)
            saveAsFile(model, description, extension);
        else {
            try {
                PrintWriter writer = new PrintWriter(current);

                model.write(writer);

                writer.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "File was not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void saveAsFile(DataModel model, String description, String extension) {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter(description, extension));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("Save File.");

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            current = new File(chooser.getSelectedFile().getAbsolutePath() + "."  + extension);

            saveFile(model, description, extension);
        }
    }
}
