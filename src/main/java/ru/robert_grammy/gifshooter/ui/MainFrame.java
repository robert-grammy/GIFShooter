package ru.robert_grammy.gifshooter.ui;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.SelectorCardChangeListener;
import ru.robert_grammy.gifshooter.ui.component.*;
import ru.robert_grammy.gifshooter.ui.component.selector.AreaTypeSelector;
import ru.robert_grammy.gifshooter.ui.component.selector.DelaySelector;
import ru.robert_grammy.gifshooter.ui.component.selector.FPSSelector;
import ru.robert_grammy.gifshooter.ui.component.selector.ScreenSelector;
import ru.robert_grammy.gifshooter.ui.config.Theme;
import ru.robert_grammy.gifshooter.ui.graphics.ColoredBorder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class MainFrame extends JFrame implements ThemeComponent, LocaleComponent {
    private JPanel rootPane;
    private TitleBar titleBar;
    private JPanel contentPane;
    private JLabel areaLineLabel;
    private JComboBox<String> areaTypeSelector;
    private JPanel areaOptionPane;
    private JComboBox<String> screenSelector;
    private JButton areaAllocateButton;
    private JLabel outputLineLabel;
    private JTextField outputTextField;
    private JButton chooseFolderButton;
    private JButton resetFpsButton;
    private JComboBox<String> fpsSelector;
    private JLabel fpsLineLabel;
    private JComboBox<String> delaySelector;
    private JLabel delayLineLabel;
    private JButton resetDelayButton;

    public MainFrame() {
        initializeRootPane();
        setupComponents();
        updateTheme();
        updateTexts();
        setupFrame();
        setupListeners();
    }

    private void initializeRootPane() {
        getContentPane().add(rootPane);
    }

    private void setupComponents() {
        ColoredButton.Companion.setDefaultStyle(areaAllocateButton);
        ColoredButton.Companion.setSquareStyle(chooseFolderButton);
        ColoredButton.Companion.setSquareStyle(resetFpsButton);
        ColoredButton.Companion.setSquareStyle(resetDelayButton);
    }

    private void setupFrame() {
        setUndecorated(true);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupListeners() {
        areaTypeSelector.addActionListener(new SelectorCardChangeListener((AreaTypeSelector) areaTypeSelector, areaOptionPane));
        titleBar.setupListeners();
    }

    @Override
    public void updateTexts() {
        areaLineLabel.setText(Strings.AREA_LINE_LABEL.get());
        outputLineLabel.setText(Strings.OUTPUT_LINE_LABEL.get());
        fpsLineLabel.setText(Strings.FPS_LINE_LABEL.get());
        delayLineLabel.setText(Strings.DELAY_LINE_LABEL.get());

        areaAllocateButton.setText(Strings.ALLOCATION_BUTTON.get());

        LocaleComponent.Companion.update(areaTypeSelector);
        LocaleComponent.Companion.update(screenSelector);
        LocaleComponent.Companion.update(fpsSelector);
        LocaleComponent.Companion.update(delaySelector);

        titleBar.updateTexts();
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);
        Arrays.stream(rootPane.getComponents()).forEach(SwingUtilities::updateComponentTreeUI);

        ThemeComponent.Companion.update(areaTypeSelector);
        ThemeComponent.Companion.update(screenSelector);
        ThemeComponent.Companion.update(fpsSelector);
        ThemeComponent.Companion.update(delaySelector);

        ThemeComponent.Companion.update(areaAllocateButton);
        ThemeComponent.Companion.update(chooseFolderButton);
        ThemeComponent.Companion.update(resetFpsButton);
        ThemeComponent.Companion.update(resetDelayButton);

        contentPane.setBorder(new ColoredBorder(Theme.PRIMARY_COLOR.get(), new Insets(0, 5, 5, 5)));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));

        titleBar.updateTheme();
    }

    private void createUIComponents() {
        areaTypeSelector = new AreaTypeSelector();
        screenSelector = new ScreenSelector();
        areaAllocateButton = new ColoredButton();
        outputTextField = new OutputPathField();
        chooseFolderButton = new ColoredButton();
        fpsSelector = new FPSSelector();
        resetFpsButton = new ColoredButton();
        delaySelector = new DelaySelector();
        resetDelayButton = new ColoredButton();
    }

    public void setColorTheme(String themeName) {
        Theme.Companion.reload(themeName);
        updateTheme();
    }

    public void setStringsLocale(String locale) {
        Strings.Companion.reload(locale);
        updateTexts();
        pack();
        rootPane.requestFocus();
    }

    public File getOutputFolder() {
        return ((OutputPathField) outputTextField).getFolder();
    }

    public int getFPS() {
        return FPSSelector.Companion.getFRAME_LIST().get(fpsSelector.getSelectedIndex());
    }

    public int getDelay() {
        return DelaySelector.Companion.getDELAY_LIST().get(delaySelector.getSelectedIndex());
    }

}
