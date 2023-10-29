package ru.robert_grammy.gifshooter.ui.main_window;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.ui.graphics.ColoredBorder;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProgramWindow extends JFrame implements ThemeComponent, LocaleComponent {

    private JPanel rootPane;
    private ProgramTitleBar programTitleBar;
    private ProgramMainPane programMainPane;
    private JPanel contentPane;

    public ProgramWindow() {
        getContentPane().add(rootPane);
        updateTexts();
        updateTheme();
        setupFrame();
        loadListeners();
    }

    private void setupFrame() {
        setUndecorated(true);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void loadListeners() {
        programMainPane.loadListeners();
        programTitleBar.loadListeners();
    }

    @Override
    public void updateTexts() {
        programTitleBar.updateTexts();
        programMainPane.updateTexts();
    }

    @Override
    public void updateTheme() {
        programTitleBar.updateTheme();
        programMainPane.updateTheme();

        contentPane.setBorder(new ColoredBorder(Theme.PRIMARY_COLOR.get(), new Insets(0,5,5,5)));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));
    }

    public void setColorTheme(String themeName) {
        Theme.Companion.reload(themeName);
        updateTheme();
    }

    public void setStringsLocale(String locale) {
        Strings.Companion.reload(locale);
        updateTexts();
        pack();
    }

    public File getOutputFolder() {
        return programMainPane.getOutputFolder();
    }

    public int getFPS() {
        return programMainPane.getFPS();
    }

    public int getDelay() {
        return programMainPane.getDelay();
    }

}
