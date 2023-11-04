package ru.robert_grammy.gifshooter.ui.language_dialog;

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

public class LanguageSelectorDialog extends JDialog implements ThemeComponent, LocaleComponent {

    private final static Dimension MIN_SIZE = new Dimension(300, 90);

    private JPanel contentPane;
    private JList<String> languages;
    private JScrollPane scrollPane;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel dialogTitle;

    public LanguageSelectorDialog() {
        setUndecorated(true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(okButton);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        scrollPane.setMinimumSize(MIN_SIZE);

        loadListeners();

        updateTexts();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadListeners() {
        okButton.addActionListener(e -> {
            if (languages.getSelectedIndex() == -1) return;
            ProgramController.INSTANCE.setLocale((String) Strings.Companion.getLocales().keySet().toArray()[languages.getSelectedIndex()]);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });

        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createUIComponents() {
        initializeTitle();
        initializeLanguagesList();
        initializeButtons();
        initializeScrollBar();
    }

    private void initializeTitle() {
        dialogTitle = new LineLabel(Strings.DIALOG_SELECT_LOCALE_TITLE);
    }

    private void initializeLanguagesList() {
        languages = new ProgramList();
        String[] list = Strings.Companion.getLocales().values().stream().map(locale -> {
            String language = locale.getDisplayLanguage(locale);
            String country = locale.getDisplayCountry(locale);
            String display = String.format(Strings.LOCALE_DISPLAY_NAME_FORMAT, language, country);
            display = display.substring(0, 1).toUpperCase() + display.substring(1);
            return display;
        }).toArray(String[]::new);
        languages.setListData(list);
    }

    private void initializeButtons() {
        okButton = new ColoredButton(Strings.DIALOG_SELECT_LOCALE_BUTTON_OK);
        ColoredButton.Companion.setDefaultStyle(okButton);
        cancelButton = new ColoredButton(Strings.DIALOG_SELECT_LOCALE_BUTTON_CANCEL);
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
        SwingUtilities.updateComponentTreeUI(contentPane);

        ThemeComponent.Companion.update(scrollPane);
        ThemeComponent.Companion.update(okButton);
        ThemeComponent.Companion.update(cancelButton);

        rootPane.setBorder(BorderFactory.createLineBorder(Theme.DIALOG_BORDER.get(), 5));
    }

}
