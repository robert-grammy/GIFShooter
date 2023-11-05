package ru.robert_grammy.gifshooter.ui.main_window;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ProgramController;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.record.area.CaptureArea;
import ru.robert_grammy.gifshooter.ui.component.view.CreateGifProgressBar;
import ru.robert_grammy.gifshooter.ui.graphics.ColoredBorder;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProgramWindow extends JFrame implements ThemeComponent, LocaleComponent {

    private static final int MARGIN_VALUE = 5;

    private JPanel rootPane;
    private ProgramTitleBar programTitleBar;
    private ProgramMainPane programMainPane;
    private JPanel contentPane;

    public ProgramWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(rootPane);
        setUndecorated(true);
        updateTexts();
        updateTheme();
        setupFrame();
        loadListeners();
    }

    private void setupFrame() {
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
        setTitle(Strings.PROGRAM_NAME.get());
        pack();
    }

    @Override
    public void updateTheme() {
        programTitleBar.updateTheme();
        programMainPane.updateTheme();

        contentPane.setBorder(new ColoredBorder(Theme.PRIMARY_COLOR.get(), new Insets(0,MARGIN_VALUE, MARGIN_VALUE, MARGIN_VALUE)));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));

        setIconImage(ProgramController.INSTANCE.createFrameIcon());
    }

    public File getOutputFolder() {
        return programMainPane.getOutputFolder();
    }

    public double getFPS() {
        return programMainPane.getFPS();
    }

    public Byte getDelay() {
        return programMainPane.getDelay();
    }

    public CaptureArea getCaptureArea() {
       return programMainPane.getCaptureArea();
    }

    public void changeCaptureButtons(final boolean isRecording) {
        programMainPane.changeCaptureButton(isRecording);
    }

    public void addProgressBar(CreateGifProgressBar progressBar) {
        programMainPane.addProgressBar(progressBar);
    }

    public void removeProgressBar(JProgressBar progressBar) {
        programMainPane.removeProgressBar(progressBar);
    }

    public void freeze(boolean isFreezing) {
        programMainPane.freeze(isFreezing);
    }

}
