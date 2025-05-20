package org.example;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalHotkeyListener implements NativeKeyListener {

    public GlobalHotkeyListener() {
        try {
            // Reduce logging spam
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            logger.setUseParentHandlers(false);

            // Register native hook
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (Exception e) {
            System.err.println("Failed to register global hotkey listener: " + e.getMessage());
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if ((e.getModifiers() & NativeKeyEvent.ALT_MASK) != 0) {
            if (e.getKeyCode() == NativeKeyEvent.VC_Y) {
                ResolutionChanger.changeResolution(
                        ResolutionChanger.defaultWidth,
                        ResolutionChanger.defaultHeight,
                        ResolutionChanger.defaultRefreshRate
                );
            } else if (e.getKeyCode() == NativeKeyEvent.VC_T) {
                ResolutionChanger.changeResolution(
                        ResolutionChanger.customWidth,
                        ResolutionChanger.customHeight,
                        ResolutionChanger.customRefresh
                );
            }
        }
    }

    @Override public void nativeKeyReleased(NativeKeyEvent e) {}
    @Override public void nativeKeyTyped(NativeKeyEvent e) {}
}