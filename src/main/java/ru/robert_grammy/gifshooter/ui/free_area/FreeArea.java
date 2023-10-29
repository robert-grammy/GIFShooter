package ru.robert_grammy.gifshooter.ui.free_area;

import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.ui.component.ColoredButton;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FreeArea extends JFrame implements ThemeComponent, LocaleComponent {
    private JPanel rootPane;
    private AreaTitleBar areaTitleBar;
    private AreaBottomBar areaBottomBar;
    private JPanel areaPane;

    public FreeArea() {
        getContentPane().add(rootPane);
        initializeAreaPane();
        updateTexts();
        updateTheme();
        setupFrame();
        setBackground(Theme.Companion.getTRANSPARENT_COLOR());
        loadListeners();
    }

    private void setupFrame() {
        setUndecorated(true);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
    }

    private void initializeAreaPane() {
        rootPane.setBackground(Theme.Companion.getTRANSPARENT_COLOR());
        areaPane.setBackground(Theme.Companion.getTRANSPARENT_COLOR());
        setAreaDimension(new Dimension(640, 360));
    }

    private void loadListeners() {
        areaTitleBar.loadListeners();
        areaBottomBar.loadListeners();
    }

    @Override
    public void updateTexts() {
        areaTitleBar.updateTexts();
        areaBottomBar.updateTexts();
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);
        Arrays.stream(rootPane.getComponents()).forEach(SwingUtilities::updateComponentTreeUI);

        areaTitleBar.updateTheme();
        areaBottomBar.updateTheme();

        areaPane.setBorder(BorderFactory.createLineBorder(Theme.SECONDARY_COLOR.get(), 5));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));
    }

    public void setAreaLocation(Point point) {
        setLocation(point);
    }

    public void setAreaDimension(Dimension dimension) {
        areaPane.setPreferredSize(dimension);
        areaPane.setSize(dimension);
    }

    public void updateDimensionInfo(Dimension dimension) {
        areaBottomBar.updateDimensionInfo(dimension);
    }

}
