package ru.robert_grammy.gifshooter.ui.free_area;

import ru.robert_grammy.gifshooter.config.ProgramIcon;
import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.FrameMouseDragListener;
import ru.robert_grammy.gifshooter.ui.component.ColoredButton;
import ru.robert_grammy.gifshooter.ui.component.LineLabel;

import javax.swing.*;

public class AreaTitleBar implements ThemeComponent, LocaleComponent {
    private JPanel rootPane;
    private JPanel titlePane;
    private JLabel title;
    private JButton captureButton;

    private void createUIComponents() {
        initializeTitle();
        initializeButton();
    }

    private void initializeTitle() {
        title = new LineLabel(Strings.FREE_AREA_TITLE);
    }

    private void initializeButton() {
        captureButton = new ColoredButton(ProgramIcon.RECORDING_STATUS_OFF.get());
    }

    public void loadListeners() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPane);
        FrameMouseDragListener listener = new FrameMouseDragListener(frame);
        rootPane.addMouseMotionListener(listener);
    }

    @Override
    public void updateTexts() {
        LocaleComponent.Companion.update(title);
    }

    @Override
    public void updateTheme() {
        rootPane.setBackground(Theme.PRIMARY_COLOR.get());
        titlePane.setBackground(Theme.PRIMARY_COLOR.get());

        ThemeComponent.Companion.update(captureButton);
    }

}