package ru.robert_grammy.gifshooter.ui.theme_dialog;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.config.Theme;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ProgramController;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.ui.component.button.ColoredButton;
import ru.robert_grammy.gifshooter.ui.component.panel.ProgramScrollPane;
import ru.robert_grammy.gifshooter.ui.component.selector.ProgramList;
import ru.robert_grammy.gifshooter.ui.component.view.LineLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class ThemeSelectorDialog extends JDialog implements ThemeComponent, LocaleComponent {
    private JPanel contentPane;
    private JList<String> themes;
    private JScrollPane scrollPane;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel dialogTitle;

    public ThemeSelectorDialog() {
        setUndecorated(true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        scrollPane.setMinimumSize(new Dimension(300, 90));

        okButton.addActionListener(e -> onOK());
        cancelButton.addActionListener(e -> onCancel());
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        updateTexts();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOK() {
        if (themes.getSelectedIndex() == -1) return;
        ProgramController.INSTANCE.setTheme(themes.getSelectedValue());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        initializeTitle();
        initializeLanguagesList();
        initializeButtons();
        initializeScrollBar();
    }

    private void initializeTitle() {
        dialogTitle = new LineLabel(Strings.DIALOG_SELECT_THEME_TITLE);
    }

    private void initializeLanguagesList() {
        themes = new ProgramList();
        String[] list = Theme.Companion.getThemeNames().toArray(String[]::new);
        themes.setListData(list);
    }

    private void initializeButtons() {
        okButton = new ColoredButton(Strings.DIALOG_SELECT_THEME_BUTTON_OK);
        ColoredButton.Companion.setDefaultStyle(okButton);
        cancelButton = new ColoredButton(Strings.DIALOG_SELECT_THEME_BUTTON_CANCEL);
        ColoredButton.Companion.setDefaultStyle(cancelButton);
    }

    private void initializeScrollBar() {
        scrollPane = new ProgramScrollPane();
    }

    @Override
    public void updateTexts() {
        LocaleComponent.Companion.update(dialogTitle);
        LocaleComponent.Companion.update(okButton);
        LocaleComponent.Companion.update(cancelButton);

        updateTheme();
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);
        Arrays.stream(rootPane.getComponents()).forEach(SwingUtilities::updateComponentTreeUI);

        ThemeComponent.Companion.update(scrollPane);
        ThemeComponent.Companion.update(okButton);
        ThemeComponent.Companion.update(cancelButton);

        rootPane.setBorder(BorderFactory.createLineBorder(Theme.DIALOG_BORDER.get(), 5));
    }

}
