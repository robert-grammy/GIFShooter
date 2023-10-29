package ru.robert_grammy.gifshooter.ui.main_window;

import ru.robert_grammy.gifshooter.config.ProgramIcon;
import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.FrameMouseDragListener;
import ru.robert_grammy.gifshooter.ui.component.ColoredButton;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.ui.component.LineLabel;

import javax.swing.*;
import java.util.Arrays;

public class ProgramTitleBar implements ThemeComponent, LocaleComponent {
    private JPanel rootPane;
    private JButton closeButton;
    private JButton minimizeButton;
    private JLabel title;
    private JPanel titlePane;
    private JPanel buttonsPane;

    private void createUIComponents() {
        initializeMinimizeButton();
        initializeCloseButton();
        initializeTitle();
    }

    public void loadListeners() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPane);
        FrameMouseDragListener listener = new FrameMouseDragListener(frame);
        rootPane.addMouseMotionListener(listener);
    }

    private void initializeCloseButton() {
        closeButton = new ColoredButton();
        closeButton.addActionListener(event -> {
            System.exit(0);
        });
    }

    private void initializeMinimizeButton() {
        minimizeButton = new ColoredButton();
        minimizeButton.addActionListener(event -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPane);
            frame.setExtendedState(JFrame.ICONIFIED);
        });
    }

    private void initializeTitle() {
        title = new LineLabel(Strings.PROGRAM_NAME);
        title.setIcon(ProgramIcon.PROGRAM.get());
    }

    @Override
    public void updateTexts() {
        LocaleComponent.Companion.update(title);
    }

    @Override
    public void updateTheme() {
        rootPane.setBackground(Theme.PRIMARY_COLOR.get());
        titlePane.setBackground(Theme.PRIMARY_COLOR.get());
        buttonsPane.setBackground(Theme.PRIMARY_COLOR.get());

        Arrays.stream(buttonsPane.getComponents()).forEach(ThemeComponent.Companion::update);

        ((ColoredButton) closeButton).setHoverColor(Theme.HOVER_CLOSE_BUTTON.get());
        ((ColoredButton) closeButton).setActiveColor(Theme.ACTIVE_CLOSE_BUTTON.get());
    }

}
