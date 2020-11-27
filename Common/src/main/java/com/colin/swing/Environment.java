package com.colin.swing;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.nio.file.Path;

/**
 * A utility class for learning about the running environment.
 */
public class Environment {
    private Environment(){
        throw new AssertionError();
    }

    /**
     * Gets the working directory of this application.
     * @return The path of the directory to put configs in
     */
    public static Path workingDir(){
        return Path.of(System.getProperty("user.home"));
    }

    /**
     * Sets the current look and feel.
     * @param feel The string describing the look and feel, as shown above
     */
    public static void setLookAndFeel(String feel){
        LookAndFeel toSet;
        switch (feel) {
            case "light":
                toSet = new FlatLightLaf();
                break;
            case "dark":
                toSet = new FlatDarkLaf();
                break;
            case "intellij":
                toSet = new FlatIntelliJLaf();
                break;
            case "darcula":
                toSet = new FlatDarculaLaf();
                break;
            default:
                toSet = new NimbusLookAndFeel();
                break;
        }
        try{
            UIManager.setLookAndFeel(toSet);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        for (Window w : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(w);
        }
    }
    public static String getMessageSeparator(){
        return "://:";
    }
}
