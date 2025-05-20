package org.example;

import org.jnativehook.GlobalScreen;
import javax.swing.SwingUtilities;  // <-- Add this import

public class ResolutionChangerApp {
    public static void main(String[] args) {
        // Initialize current resolution detection
        ResolutionChanger.initializeCurrentResolution();

        try {
            // Set up global hotkey listener
            GlobalScreen.registerNativeHook();
            new GlobalHotkeyListener();
        } catch (Exception e) {
            System.err.println("Failed to initialize hotkeys: " + e.getMessage());
        }

        // Create and show GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui();
            gui.createAndShowGUI();
        });
    }
}