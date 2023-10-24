package ru.robert_grammy.gifshooter.ui.view;

import ru.robert_grammy.gifshooter.config.Strings;
import ru.robert_grammy.gifshooter.control.LocaleComponent;
import ru.robert_grammy.gifshooter.control.ThemeComponent;
import ru.robert_grammy.gifshooter.ui.component.ColoredBorder;
import ru.robert_grammy.gifshooter.ui.component.ColoredButton;
import ru.robert_grammy.gifshooter.ui.component.AreaTypeSelector;
import ru.robert_grammy.gifshooter.ui.component.ScreenSelector;
import ru.robert_grammy.gifshooter.ui.graphics.ComponentDimension;
import ru.robert_grammy.gifshooter.ui.graphics.Theme;
import ru.robert_grammy.gifshooter.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
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
        ((ColoredButton) areaAllocateButton).setBorderWeight(2F);

        chooseFolderButton.setIcon(ResourceLoader.INSTANCE.getIcon("select_folder_button_icon.png", ResourceLoader.ICONS_DIR));
        ((ColoredButton) chooseFolderButton).setBorderWeight(2F);
        chooseFolderButton.setPreferredSize(ComponentDimension.SQUARE_BUTTON.get());
        chooseFolderButton.setMinimumSize(ComponentDimension.SQUARE_BUTTON.get());

        //-----
    }

    private void setupFrame() {
        setUndecorated(true);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupListeners() {
        areaTypeSelector.addActionListener(event -> {
            if (areaTypeSelector.getSelectedIndex() == -1) return;
            String card = ((AreaTypeSelector) areaTypeSelector).getSelected();
            CardLayout layout = (CardLayout) areaOptionPane.getLayout();
            layout.show(areaOptionPane, card);
        });

        titleBar.setupListeners();
    }

    @Override
    public void updateTexts() {
        areaLineLabel.setText(Strings.AREA_LINE_LABEL.get());
        outputLineLabel.setText(Strings.OUTPUT_LINE_LABEL.get());

        LocaleComponent.Companion.update(areaTypeSelector);
        LocaleComponent.Companion.update(screenSelector);
        areaAllocateButton.setText(Strings.ALLOCATION_BUTTON.get());

        titleBar.updateTexts();
    }

    @Override
    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(rootPane);
        Arrays.stream(rootPane.getComponents()).forEach(SwingUtilities::updateComponentTreeUI);

        titleBar.updateTheme();
        ThemeComponent.Companion.update(areaAllocateButton);
        ThemeComponent.Companion.update(chooseFolderButton);

        contentPane.setBorder(new ColoredBorder(Theme.PRIMARY_COLOR.get(), new Insets(0, 5, 5, 5)));
        rootPane.setBorder(BorderFactory.createLineBorder(Theme.BORDER_COLOR.get(), 1));
    }

    private void createUIComponents() {
        areaTypeSelector = new AreaTypeSelector();
        screenSelector = new ScreenSelector();
        areaAllocateButton = new ColoredButton();
        chooseFolderButton = new ColoredButton();
    }

    public void setColorTheme(String themeName) {
        Theme.Companion.reload(themeName);
        updateTheme();
    }

    public void setStringsLocale(String locale) {
        Strings.Companion.reload(locale);
        updateTexts();
        pack();
    }

}
