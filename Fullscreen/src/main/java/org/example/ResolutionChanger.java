package org.example;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import java.util.Arrays;
import java.util.List;

public class ResolutionChanger {

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);
        int CDS_UPDATEREGISTRY = 0x00000001;
        int CDS_RESET = 0x40000000;
        int DISP_CHANGE_SUCCESSFUL = 0;

        int ChangeDisplaySettingsEx(String lpszDeviceName, DEVMODE lpDevMode, Pointer hwnd, int dwflags, Pointer lParam);
    }

    public interface User32Extended extends StdCallLibrary {
        User32Extended INSTANCE = Native.load("user32", User32Extended.class, W32APIOptions.DEFAULT_OPTIONS);
        boolean EnumDisplaySettings(String deviceName, int modeNum, DEVMODE devMode);
    }

    public static class DEVMODE extends Structure {
        public static final int CCHDEVICENAME = 32;
        public static final int CCHFORMNAME = 32;

        public char[] dmDeviceName = new char[CCHDEVICENAME];
        public short dmSpecVersion;
        public short dmDriverVersion;
        public short dmSize;
        public short dmDriverExtra;
        public int dmFields;
        public int dmPositionX;
        public int dmPositionY;
        public int dmDisplayOrientation;
        public int dmDisplayFixedOutput;
        public short dmColor;
        public short dmDuplex;
        public short dmYResolution;
        public short dmTTOption;
        public short dmCollate;
        public char[] dmFormName = new char[CCHFORMNAME];
        public short dmLogPixels;
        public int dmBitsPerPel;
        public int dmPelsWidth;
        public int dmPelsHeight;
        public int dmDisplayFlags;
        public int dmDisplayFrequency;
        public int dmICMMethod;
        public int dmICMIntent;
        public int dmMediaType;
        public int dmDitherType;
        public int dmReserved1;
        public int dmReserved2;
        public int dmPanningWidth;
        public int dmPanningHeight;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(
                    "dmDeviceName", "dmSpecVersion", "dmDriverVersion", "dmSize", "dmDriverExtra",
                    "dmFields", "dmPositionX", "dmPositionY", "dmDisplayOrientation", "dmDisplayFixedOutput",
                    "dmColor", "dmDuplex", "dmYResolution", "dmTTOption", "dmCollate", "dmFormName",
                    "dmLogPixels", "dmBitsPerPel", "dmPelsWidth", "dmPelsHeight", "dmDisplayFlags",
                    "dmDisplayFrequency", "dmICMMethod", "dmICMIntent", "dmMediaType", "dmDitherType",
                    "dmReserved1", "dmReserved2", "dmPanningWidth", "dmPanningHeight"
            );
        }
    }

    public static int customWidth = 1568;
    public static int customHeight = 1080;
    public static int customRefresh = 60;

    public static int defaultWidth = 1920;
    public static int defaultHeight = 1080;
    public static int defaultRefreshRate = 60;

    public static void initializeCurrentResolution() {
        DEVMODE dm = new DEVMODE();
        dm.dmSize = (short) dm.size();

        if (User32Extended.INSTANCE.EnumDisplaySettings(null, -1, dm)) {
            defaultWidth = dm.dmPelsWidth;
            defaultHeight = dm.dmPelsHeight;
            defaultRefreshRate = dm.dmDisplayFrequency;
        }
    }

    public static void changeResolution(int width, int height, int refreshRate) {
        if (!isResolutionSupported(width, height, refreshRate)) {
            System.out.println("Resolution not supported: " + width + "x" + height + " @" + refreshRate + "Hz");
            return;
        }

        DEVMODE dm = new DEVMODE();
        dm.dmSize = (short) dm.size();
        dm.dmPelsWidth = width;
        dm.dmPelsHeight = height;
        dm.dmBitsPerPel = 32;
        dm.dmDisplayFrequency = refreshRate;
        dm.dmFields = 0x00080000 | 0x00100000 | 0x00400000 | 0x00040000;

        int result = User32.INSTANCE.ChangeDisplaySettingsEx(null, dm, null,
                User32.CDS_UPDATEREGISTRY | User32.CDS_RESET, null);

        if (result == User32.DISP_CHANGE_SUCCESSFUL) {
            System.out.println("Changed resolution to: " + width + "x" + height + " @" + refreshRate + "Hz");
        } else {
            System.out.println("Failed to change resolution. Code: " + result);
        }
    }

    public static boolean isResolutionSupported(int width, int height, int refreshRate) {
        DEVMODE dm = new DEVMODE();
        dm.dmSize = (short) dm.size();

        int modeNum = 0;
        while (User32Extended.INSTANCE.EnumDisplaySettings(null, modeNum, dm)) {
            if (dm.dmPelsWidth == width &&
                    dm.dmPelsHeight == height &&
                    dm.dmDisplayFrequency == refreshRate) {
                return true;
            }
            modeNum++;
        }
        return false;
    }
}