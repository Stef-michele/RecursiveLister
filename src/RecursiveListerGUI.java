import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveListerGUI extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JButton quitButton;

    public RecursiveListerGUI() {
        setTitle("Recursive File Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Recursive File Lister", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Text area inside a scroll pane
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseDirectory();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = chooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDir = chooser.getSelectedFile();
            textArea.setText("");
            listFiles(selectedDir);
        }
    }

    private void listFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file); // Recursive call
                } else {
                    textArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }
}
