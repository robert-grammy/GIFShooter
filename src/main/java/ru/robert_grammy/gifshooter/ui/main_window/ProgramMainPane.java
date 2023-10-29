package ru.robert_grammy.gifshooter.ui.main_window;

import ru.robert_grammy.gifshooter.config.ProgramIcon;
import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.SelectorCardChangeListener;
import ru.robert_grammy.gifshooter.ui.component.ColoredButton;
import ru.robert_grammy.gifshooter.ui.component.LineLabel;
import ru.robert_grammy.gifshooter.ui.component.OutputPathField;
import ru.robert_grammy.gifshooter.ui.component.selector.AreaTypeSelector;
import ru.robert_grammy.gifshooter.ui.component.selector.DelaySelector;
import ru.robert_grammy.gifshooter.ui.component.selector.FPSSelector;
import ru.robert_grammy.gifshooter.ui.component.selector.ScreenSelector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.util.Arrays;

public class ProgramMainPane implements ThemeComponent, LocaleComponent {

    private JPanel rootPane;
    private JPanel lineLabelsPane;
    private JLabel captureAreaLineLabel;
    private JLabel outputFolderLineLabel;
    private JLabel recordFPSLineLabel;
    private JLabel frameDelayLineLabel;
    private JPanel captureAreaLinePane;
    private JPanel outputFolderLinePane;
    private JPanel recordFPSLinePane;
    private JPanel frameDelayLinePane;
    private JPanel captureAndSettingsButtonsLinePane;
    private JScrollPane resultsInfoScroll;
    private JComboBox<String> areaTypeSelector;
    private JPanel areaTypeOptionPane;
    private JComboBox<String> screenSelector;
    private JButton freeAreaAllocateButton;
    private JTextField outputPathTextField;
    private JButton pathChooseButton;
    private JComboBox<String> recordFPSSelector;
    private JComboBox<String> frameDelaySelector;
    private JButton resetFPSButton;
    private JButton resetFrameDelayButton;
    private JButton captureButton;
    private JButton themeSelectButton;
    private JButton localeSelectButton;
    private JList<Object> resultsInfoList;

    public ProgramMainPane() {
        rootPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    private void createUIComponents() {
        initializeLabels();
        initializeAreaTypeLine();
        initializeOutputFolderLine();
        initializeRecordFPSLine();
        initializeFrameDelayLine();
        initializeCaptureAndSettingsButtonsLine();
    }

    private void initializeLabels() {
        captureAreaLineLabel = new LineLabel(Strings.AREA_LINE_LABEL);
        outputFolderLineLabel = new LineLabel(Strings.OUTPUT_LINE_LABEL);
        recordFPSLineLabel = new LineLabel(Strings.FPS_LINE_LABEL);
        frameDelayLineLabel = new LineLabel(Strings.DELAY_LINE_LABEL);
    }

    private void initializeAreaTypeLine() {
        areaTypeSelector = AreaTypeSelector.INSTANCE;
        screenSelector = ScreenSelector.INSTANCE;
        freeAreaAllocateButton = new ColoredButton(Strings.ALLOCATION_BUTTON);
        ColoredButton.Companion.setDefaultStyle(freeAreaAllocateButton);
    }

    private void initializeOutputFolderLine() {
        outputPathTextField = new OutputPathField();
        pathChooseButton = new ColoredButton(ProgramIcon.FOLDER.get());
        ColoredButton.Companion.setDefaultStyle(pathChooseButton);
    }

    private void initializeRecordFPSLine() {
        recordFPSSelector = FPSSelector.INSTANCE;
        resetFPSButton = new ColoredButton(ProgramIcon.RESET.get());
        ColoredButton.Companion.setSquareStyle(resetFPSButton);
    }

    private void initializeFrameDelayLine() {
        frameDelaySelector = DelaySelector.INSTANCE;
        resetFrameDelayButton = new ColoredButton(ProgramIcon.RESET.get());
        ColoredButton.Companion.setSquareStyle(resetFrameDelayButton);
    }

    private void initializeCaptureAndSettingsButtonsLine() {
        captureButton = new ColoredButton(Strings.START_CAPTURE_BUTTON, ProgramIcon.RECORDING_STATUS_OFF.get());
        ColoredButton.Companion.setDefaultStyle(captureButton);
        themeSelectButton = new ColoredButton(ProgramIcon.THEME.get());
        ColoredButton.Companion.setSquareStyle(themeSelectButton);
        localeSelectButton = new ColoredButton(ProgramIcon.PLANET.get());
        ColoredButton.Companion.setSquareStyle(localeSelectButton);
    }

    public void loadListeners() {
        areaTypeSelector.addActionListener(new SelectorCardChangeListener((AreaTypeSelector) areaTypeSelector, areaTypeOptionPane));
    }

    @Override
    public void updateTexts() {
        Arrays.stream(lineLabelsPane.getComponents()).forEach(LocaleComponent.Companion::update);

        LocaleComponent.Companion.update(freeAreaAllocateButton);
        LocaleComponent.Companion.update(captureButton);

        LocaleComponent.Companion.update(areaTypeSelector);
        LocaleComponent.Companion.update(screenSelector);
        LocaleComponent.Companion.update(recordFPSSelector);
        LocaleComponent.Companion.update(frameDelaySelector);
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);
        Arrays.stream(rootPane.getComponents()).forEach(SwingUtilities::updateComponentTreeUI);

        Arrays.stream(lineLabelsPane.getComponents()).forEach(ThemeComponent.Companion::update);

        ThemeComponent.Companion.update(freeAreaAllocateButton);
        ThemeComponent.Companion.update(pathChooseButton);
        ThemeComponent.Companion.update(resetFPSButton);
        ThemeComponent.Companion.update(resetFrameDelayButton);

        ThemeComponent.Companion.update(areaTypeSelector);
        ThemeComponent.Companion.update(screenSelector);
        ThemeComponent.Companion.update(recordFPSSelector);
        ThemeComponent.Companion.update(frameDelaySelector);
    }

    public File getOutputFolder() {
        return ((OutputPathField) outputPathTextField).getFolder();
    }

    public int getFPS() {
        return FPSSelector.INSTANCE.getFRAME_LIST().get(recordFPSSelector.getSelectedIndex());
    }

    public int getDelay() {
        return DelaySelector.INSTANCE.getDELAY_LIST().get(frameDelaySelector.getSelectedIndex());
    }

}
