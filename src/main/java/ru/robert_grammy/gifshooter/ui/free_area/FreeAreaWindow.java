package ru.robert_grammy.gifshooter.ui.free_area;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ProgramController;
import ru.robert_grammy.gifshooter.control.ThemeComponent;

import javax.swing.*;
import java.awt.*;

public class FreeAreaWindow extends JFrame implements ThemeComponent, LocaleComponent {
    private JPanel rootPane;
    private AreaTitleBar areaTitleBar;
    private AreaBottomBar areaBottomBar;
    private JPanel areaPane;

    public FreeAreaWindow() {
        getContentPane().add(rootPane);
        setUndecorated(true);
        initializeAreaPane();
        updateTexts();
        setupFrame();
        setBackground(Theme.Companion.getTRANSPARENT_COLOR());
        loadListeners();
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        reset();
        setAlwaysOnTop(true);
    }

    private void initializeAreaPane() {
        rootPane.setBackground(Theme.Companion.getTRANSPARENT_COLOR());
        areaPane.setBackground(Theme.Companion.getTRANSPARENT_COLOR());
    }

    private void loadListeners() {
        areaTitleBar.loadListeners();
        areaBottomBar.loadListeners();
    }

    @Override
    public void updateTexts() {
        areaTitleBar.updateTexts();
        areaBottomBar.updateTexts();
        setTitle(Strings.FREE_AREA_TITLE.get());
        pack();
        updateTheme();
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);

        areaTitleBar.updateTheme();
        areaBottomBar.updateTheme();

        areaPane.setBorder(BorderFactory.createLineBorder(Theme.HOVER_COLOR.get(), 5));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));

        setIconImage(ProgramController.INSTANCE.createFrameIcon());
    }

    public void setAreaLocation(Point point) {
        point.x -= 10;
        point.y -= 10;
        setLocation(point);
    }

    public void setAreaDimension(Dimension dimension) {
        updateDimensionInfo(dimension);
        dimension.width += 30;
        dimension.height += 30;
        areaPane.setPreferredSize(dimension);
        areaPane.setSize(dimension);
        pack();
    }

    public void reset() {
        setAreaDimension(new Dimension(640, 360));
        setLocationRelativeTo(null);
    }

    public Rectangle getArea() {
        return new Rectangle(getX() + 16, getY() + 46, areaPane.getWidth() - 30, areaPane.getHeight() - 30);
    }

    private void updateDimensionInfo(Dimension dimension) {
        areaBottomBar.updateDimensionInfo(dimension);
    }

    public  void changeCaptureButtons(boolean isRecording) {
        areaTitleBar.changeCaptureButton(isRecording);
        areaBottomBar.changeCaptureButton(isRecording);
        if (isRecording) {
            areaPane.setBorder(BorderFactory.createLineBorder(Theme.CAPTURE.get(), 5));
        } else {
            areaPane.setBorder(BorderFactory.createLineBorder(Theme.HOVER_COLOR.get(), 5));
        }
    }

}
