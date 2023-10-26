package ru.robert_grammy.gifshooter.ui;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.control.listener.FrameMouseDragListener;
import ru.robert_grammy.gifshooter.ui.component.ColoredButton;
import ru.robert_grammy.gifshooter.ui.config.Theme;

import javax.swing.*;
import java.util.Arrays;

public class TitleBar implements ThemeComponent, LocaleComponent {
    private JPanel rootPane;
    private JButton closeButton;
    private JButton minimizeButton;
    private JLabel title;
    private JPanel titlePane;
    private JPanel buttonsPane;

    private void createUIComponents() {
        initializeMinimizeButton();
        initializeCloseButton();
    }

    public void setupListeners() {
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

    @Override
    public void updateTexts() {
        title.setText(Strings.PROGRAM_NAME.get());
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
