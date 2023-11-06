![Logo](https://i.imgur.com/UWrZRzx.png)
# GIFShooter - screen capture app
### A very simple application to capture your screen to GIF file.
![Downloads](https://img.shields.io/github/downloads/robert-grammy/GIFShooter/total) ![Contributors](https://img.shields.io/github/contributors/robert-grammy/GIFShooter?color=dark-green) ![Issues](https://img.shields.io/github/issues/robert-grammy/GIFShooter) ![License](https://img.shields.io/github/license/robert-grammy/GIFShooter)

[Bug or feature?](https://github.com/robert-grammy/GIFShooter/issues)

# About The Project
![Main frame screenshot](https://i.imgur.com/fBmnpek.png)

The need to record a screen capture and get an animated GIF file has arisen. The option with video recording and manual formatting was immediately rejected, as it is time-consuming and inconvenient. Analogs on the Internet also seemed inconvenient to me and it was decided to implement its own application. At the moment it is an extremely simple application that already allows you to record the screen or its area in animation format, but its development does not stop. I plan to add new functionality, optimize the final files and refine the current components.

# Usage
1. Select mode: fullscreen (you can select screen) or free area.
2. Select output folder. You can choose folder on button click or type in field. While typing you can use some pat keys:
    - %DOCUMENTS% - the documents folder on you PC.
    - %PROGRAM_DIR% - the folder where you have been saved the application.
    - %DESKTOP% - the desktop folder.
3. Select FPS and frame delay.
   Recomended parameters - 20/50 or 25/40. These parameters provide normal animation speed.
   For each FPS, the optimal latency is specified. If it is lower, the animation speed will be faster, and vice versa. When recording large areas, there may be a loss of frames, so the final FPS will be lower and animation speed will be higher, so set the value     above 20 FPS should only when recording small areas, otherwise it is recommended to play with the settings. I have two FullHD monitors on my PC, when trying to record all screens with 50/20 settings my final FPS is about 20, so the animation speed is about       2.5 times faster than normal.
4. Start capturing and enjoy the result.

![GIF demo](https://i.imgur.com/ufbcAUd.gif)

### In future
- Taking screenshots
- Hotkeys

### Built With
Java 21, Kotlin 1.9.0.