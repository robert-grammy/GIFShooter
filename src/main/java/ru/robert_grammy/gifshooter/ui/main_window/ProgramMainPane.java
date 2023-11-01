package ru.robert_grammy.gifshooter.ui.main_window;

import ru.robert_grammy.gifshooter.config.ProgramIcon;
import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.UIProperties;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ProgramController;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.AllocateButtonListener;
import ru.robert_grammy.gifshooter.control.listener.CaptureButtonListener;
import ru.robert_grammy.gifshooter.control.listener.SelectorCardChangeListener;
import ru.robert_grammy.gifshooter.record.area.CaptureArea;
import ru.robert_grammy.gifshooter.record.area.FreeArea;
import ru.robert_grammy.gifshooter.record.area.ScreenArea;
import ru.robert_grammy.gifshooter.ui.component.button.ColoredButton;
import ru.robert_grammy.gifshooter.ui.component.frame.ProgramFileChooser;
import ru.robert_grammy.gifshooter.ui.component.panel.ProgramScrollPane;
import ru.robert_grammy.gifshooter.ui.component.view.LineLabel;
import ru.robert_grammy.gifshooter.ui.component.input.OutputPathField;
import ru.robert_grammy.gifshooter.ui.component.selector.AreaTypeSelector;
import ru.robert_grammy.gifshooter.ui.component.selector.DelaySelector;
import ru.robert_grammy.gifshooter.ui.component.selector.FPSSelector;
import ru.robert_grammy.gifshooter.ui.component.selector.ScreenSelector;
import ru.robert_grammy.gifshooter.ui.language_dialog.LanguageSelectorDialog;
import ru.robert_grammy.gifshooter.ui.theme_dialog.ThemeSelectorDialog;

import javax.swing.*;
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
    private JPanel scrollContent;

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
        initializeResultInfoPane();
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

    private void initializeResultInfoPane() {
        resultsInfoScroll = new ProgramScrollPane();
    }

    public void loadListeners() {
        areaTypeSelector.addActionListener(new SelectorCardChangeListener((AreaTypeSelector) areaTypeSelector, areaTypeOptionPane));
        freeAreaAllocateButton.addActionListener(AllocateButtonListener.INSTANCE);
        pathChooseButton.addActionListener(event -> {
            int result = ProgramFileChooser.INSTANCE.open();
            if (result == JFileChooser.APPROVE_OPTION) {
                outputPathTextField.setText(ProgramFileChooser.INSTANCE.getSelectedFile().getPath());
            }
        });
        recordFPSSelector.addActionListener(event -> frameDelaySelector.setSelectedIndex(recordFPSSelector.getSelectedIndex()));
        resetFPSButton.addActionListener(event -> {
            recordFPSSelector.setSelectedIndex(1);
            frameDelaySelector.setSelectedIndex(1);
        });
        resetFrameDelayButton.addActionListener(event -> frameDelaySelector.setSelectedIndex(recordFPSSelector.getSelectedIndex()));
        captureButton.addActionListener(CaptureButtonListener.INSTANCE);
        localeSelectButton.addActionListener(event -> new LanguageSelectorDialog());
        themeSelectButton.addActionListener(event -> new ThemeSelectorDialog());
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

        updateTheme();
        resetSelectors();
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
        ThemeComponent.Companion.update(captureButton);
        ThemeComponent.Companion.update(themeSelectButton);
        ThemeComponent.Companion.update(localeSelectButton);

        ThemeComponent.Companion.update(resultsInfoScroll);

        ThemeComponent.Companion.update(areaTypeSelector);
        ThemeComponent.Companion.update(screenSelector);
        ThemeComponent.Companion.update(recordFPSSelector);
        ThemeComponent.Companion.update(frameDelaySelector);
    }

    public File getOutputFolder() {
        return ((OutputPathField) outputPathTextField).getFolder();
    }

    public double getFPS() {
        return FPSSelector.INSTANCE.getFRAME_LIST().get(recordFPSSelector.getSelectedIndex());
    }

    public Byte getDelay() {
        return DelaySelector.INSTANCE.getDELAY_LIST().get(frameDelaySelector.getSelectedIndex());
    }

    public CaptureArea getCaptureArea() {
        String selected = ((AreaTypeSelector) areaTypeSelector).getSelected();
        if (selected.equals(AreaTypeSelector.FREE_AREA_CARD)) {
            return FreeArea.INSTANCE;
        } else {
            return new ScreenArea(((ScreenSelector) screenSelector).getSelectedScreen());
        }
    }

    public void changeCaptureButton(final boolean isRecording) {
        if (isRecording) {
            captureButton.setIcon(ProgramIcon.RECORDING_STATUS_ON.get());
            captureButton.setText(Strings.STOP_CAPTURE_BUTTON.get());
        } else {
            captureButton.setIcon(ProgramIcon.RECORDING_STATUS_OFF.get());
            captureButton.setText(Strings.START_CAPTURE_BUTTON.get());
        }
    }

    private void resetSelectors() {
        recordFPSSelector.setSelectedIndex(1);
        frameDelaySelector.setSelectedIndex(1);
    }

}
