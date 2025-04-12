# BE4TS Resolution Controller (Beta)

![Java](https://img.shields.io/badge/Java-JDK%2021-blue) 
![Windows](https://img.shields.io/badge/Platform-Windows%20Only-important)
![AC-Safe](https://img.shields.io/badge/Anticheat-Safe-success)

> ⚠️ **Beta Notice**: This app requires manual resolution setup in your GPU control panel before use

## 🚨 Critical Setup
1. **Create resolutions FIRST in your GPU software**:
   - **NVIDIA**: NVIDIA Control Panel → Display → Change Resolution → Customize...
   - **AMD**: AMD Software → Display → Custom Resolutions  
   - **Intel**: Intel Graphics Command Center → Display → Custom Resolutions
2. *Then* select them in this app

## ✨ Features
- Switch between presets with `Alt+Y` (Default) / `Alt+T` (Custom)
- Anti-cheat safe (no memory injection/drivers used)
- Auto-detects existing resolutions

## ⚙️ Requirements
- **JDK 21** ([Adoptium](https://adoptium.net))
- **GPU Drivers**: Latest NVIDIA/AMD/Intel
- **Recommended**: Admin rights (for reliable hotkeys)

## 📝 Beta Notes
- Resolutions won't appear in-app until created in GPU software
- Hotkeys work without admin rights but may be less reliable
- 100% anti-cheat safe (uses Windows-native APIs)

> Example: To use 1728x1080, create it in NVIDIA Control Panel first, then it will appear here.
