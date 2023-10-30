package ru.robert_grammy.gifshooter.ui.free_area;

import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
        updateTheme();
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
        pack();
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);
        Arrays.stream(rootPane.getComponents()).forEach(SwingUtilities::updateComponentTreeUI);

        areaTitleBar.updateTheme();
        areaBottomBar.updateTheme();

        areaPane.setBorder(BorderFactory.createLineBorder(Theme.HOVER_COLOR.get(), 5));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));
    }

    public void setAreaLocation(Point point) {
        setLocation(point);
    }

    public void setAreaDimension(Dimension dimension) {
        areaPane.setPreferredSize(dimension);
        areaPane.setSize(dimension);
        pack();
        updateDimensionInfo(dimension);
    }

    public void reset() {
        setAreaDimension(new Dimension(640, 360));
        setLocationRelativeTo(null);
    }

    public Rectangle getArea() {
        return areaPane.getBounds();
    }

    private void updateDimensionInfo(Dimension dimension) {
        areaBottomBar.updateDimensionInfo(dimension);
    }

}
