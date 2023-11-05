package ru.robert_grammy.gifshooter.ui.error_dialog;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorDialog extends JDialog {

    private static final String MSG_DESCRIPTION_FORMAT = "Error message (exit code %d):";

    private JPanel contentPane;
    private JButton okButton;
    private JTextArea errorStackTrace;
    private JLabel dialogTitle;
    private JLabel errorDescriptionLabel;
    private JPanel buttonPanel;

    private final int exitCode;

    public ErrorDialog(int exitCode) {
        setUndecorated(true);
        setContentPane(contentPane);
        this.exitCode = exitCode;
        errorDescriptionLabel.setText(String.format(MSG_DESCRIPTION_FORMAT, exitCode));
        initialize();
        reloadUI();
    }

    private void initialize() {
        errorStackTrace.setEditable(false);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        okButton.addActionListener(e -> {
            dispose();
            System.exit(exitCode);
        });
    }

    private void showDialog(String stackTrace) {
        errorStackTrace.setText(stackTrace);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void reloadUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(contentPane);

            contentPane.setBackground(Color.LIGHT_GRAY);
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            okButton.setBackground(Color.LIGHT_GRAY);

            dialogTitle.setForeground(Color.RED);
            errorDescriptionLabel.setForeground(Color.BLACK);

            Font font = new Font("Dialog", Font.BOLD, 14);

            dialogTitle.setFont(font);
            errorDescriptionLabel.setFont(font);
            errorStackTrace.setFont(font);

        } catch (Exception exception) {
            ErrorHandler.showErrorDialog(exception, ErrorHandler.UI_UPDATE_ERROR);
        }
    }

    public static class ErrorHandler implements Thread.UncaughtExceptionHandler {

        public static int UNKNOWN_ERROR = 100;
        public static int UI_UPDATE_ERROR = 201;

        @Override
        public void uncaughtException(Thread thread, Throwable exception) {
            showErrorDialog(exception, UNKNOWN_ERROR);
        }

        private static void showErrorDialog(Throwable exception, int exitCode) {
            EventQueue.invokeLater(() -> {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                exception.printStackTrace(printWriter);
                ErrorDialog dialog = new ErrorDialog(exitCode);
                dialog.showDialog(stringWriter.toString());
            });
        }

        private static void showErrorDialog(String message, int exitCode) {
            ErrorDialog dialog = new ErrorDialog(exitCode);
            dialog.showDialog(message);
        }

    }

}
