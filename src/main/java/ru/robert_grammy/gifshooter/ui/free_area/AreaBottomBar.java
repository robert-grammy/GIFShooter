package ru.robert_grammy.gifshooter.ui.free_area;

import ru.robert_grammy.gifshooter.config.ProgramIcon;
import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.CaptureButtonListener;
import ru.robert_grammy.gifshooter.control.listener.FrameMouseDragListener;
import ru.robert_grammy.gifshooter.ui.component.button.ColoredButton;
import ru.robert_grammy.gifshooter.ui.component.view.LineLabel;

import javax.swing.*;
import java.awt.*;

public class AreaBottomBar implements ThemeComponent, LocaleComponent {

    private static final String DIMENSION_FORMAT = "[%d : %d]";

    private JPanel titlePane;
    private JLabel dimensionInfoLabel;
    private JButton captureButton;
    private JPanel rootPane;
    private JLabel dimensionLabel;

    private void createUIComponents() {
        initializeTitle();
        initializeButton();
    }

    private void initializeTitle() {
        dimensionInfoLabel = new LineLabel(Strings.FREE_AREA_SIZE_INFO_FORMAT);
    }

    private void initializeButton() {
        captureButton = new ColoredButton(ProgramIcon.RECORDING_STATUS.getColored(Theme.TEXT_COLOR.hex()));
    }

    public void loadListeners() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPane);
        FrameMouseDragListener listener = new FrameMouseDragListener(frame);
        rootPane.addMouseMotionListener(listener);
        captureButton.addActionListener(CaptureButtonListener.INSTANCE);
    }

    @Override
    public void updateTexts() {
        LocaleComponent.Companion.update(dimensionInfoLabel);
        updateTheme();
    }

    @Override
    public void updateTheme() {
        rootPane.setBackground(Theme.PRIMARY_COLOR.get());
        titlePane.setBackground(Theme.PRIMARY_COLOR.get());

        ThemeComponent.Companion.update(captureButton);
        captureButton.setIcon(ProgramIcon.RECORDING_STATUS.getColored(Theme.TEXT_COLOR.hex()));
    }

    public void updateDimensionInfo(Dimension dimension) {
        dimensionLabel.setText(String.format(DIMENSION_FORMAT, dimension.width, dimension.height));
    }

    public void changeCaptureButton(boolean isRecording) {
        if (isRecording) {
            captureButton.setIcon(ProgramIcon.RECORDING_STATUS.getColored(Theme.CAPTURE.hex()));
        } else {
            captureButton.setIcon(ProgramIcon.RECORDING_STATUS.getColored(Theme.TEXT_COLOR.hex()));
        }
    }

}
