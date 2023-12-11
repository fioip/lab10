package SwingElements;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SwingElements {
        private JFrame mainFrame;
        private JLabel headerLabel;
        private JLabel statusLabel;
        private JPanel controlPanel;
        private JPanel controlButtons;
        private JPanel progress;
        private JPanel radioButtons;

        public SwingElements(){
            prepareGUI();
        }
        public static void main(String[] args){
            SwingElements  swingControlDemo = new SwingElements();
            swingControlDemo.showLabelDemo();

            SwingElements  swingControlDemo1 = new SwingElements();
            swingControlDemo.showButtonDemo();

            SwingElements  swingControlDemo2 = new SwingElements();
            swingControlDemo.showProgressBarDemo();

            SwingElements swingControlDemo3 = new SwingElements();
            swingControlDemo.showRadioButtonDemo();
        }
        private void prepareGUI(){
            mainFrame = new JFrame("Java Swing Examples");
            mainFrame.setSize(1000,1000);
            mainFrame.setLayout(new GridLayout(3, 1));

            mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }
            });
            headerLabel = new JLabel("", JLabel.CENTER);
            statusLabel = new JLabel("",JLabel.CENTER);
            statusLabel.setSize(500,500);
            controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout());

            controlButtons = new JPanel();
            progress = new JPanel();
            radioButtons = new JPanel();

            mainFrame.add(headerLabel);
            mainFrame.add(controlPanel);
            mainFrame.add(controlButtons);
            mainFrame.add(progress);
            mainFrame.add(radioButtons);
            mainFrame.add(statusLabel);
            mainFrame.setVisible(true);
        }
        private void showLabelDemo(){
            headerLabel.setText("Laborator Java 2023");
            JLabel label  = new JLabel("", JLabel.CENTER);
            label.setText("Iuhasz P. Fiona");
            label.setBorder(new EmptyBorder(10,10,10,10));
            label.setOpaque(true);
            label.setBackground(Color.orange);
            label.setForeground(Color.BLACK);
            controlPanel.add(label);

            mainFrame.setVisible(true);
        }

    private static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = swingControlDemo1.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void showButtonDemo(){
        headerLabel.setText("Control in action: Button");

        //resources folder should be inside SWING folder.
        ImageIcon icon = createImageIcon("/resources/java_icon.png","Java");

        JButton okButton = new JButton("OK");
        JButton javaButton = new JButton("Submit", icon);
        JButton cancelButton = new JButton("Cancel", icon);
        cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Ok Button clicked.");
            }
        });
        javaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Submit Button clicked.");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Cancel Button clicked.");
            }
        });
        controlButtons.add(okButton);
        controlButtons.add(javaButton);
        controlButtons.add(cancelButton);
        controlButtons.setLayout(new GridLayout(3, 1));

        mainFrame.setVisible(true);
    }
    private JProgressBar progressBar;
    private Task task;
    private JButton startButton;
    private JTextArea outputTextArea;

    private void showProgressBarDemo(){
        headerLabel.setText("Control in action: JProgressBar");
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        startButton = new JButton("Start");
        outputTextArea = new JTextArea("",5,20);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                task = new Task();
                task.start();
            }
        });
        progress.add(startButton);
        progress.add(progressBar);
        progress.add(scrollPane);

        progress.setLayout(new GridLayout(1, 3));
        mainFrame.setVisible(true);
    }
    private class Task extends Thread {
        public Task(){
        }
        public void run(){
            for(int i =0; i<= 100; i+=10){
                final int progress = i;

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar.setValue(progress);
                        outputTextArea.setText(outputTextArea.getText()
                                + String.format("Completed %d%% of task.\n", progress));
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        }
    }
    private void showRadioButtonDemo(){
        headerLabel.setText("Control in action: RadioButton");

        final JRadioButton radApple = new JRadioButton("Apple", true);
        final JRadioButton radMango = new JRadioButton("Mango");
        final JRadioButton radPeer = new JRadioButton("Peer");

        radApple.setMnemonic(KeyEvent.VK_C);
        radMango.setMnemonic(KeyEvent.VK_M);
        radPeer.setMnemonic(KeyEvent.VK_P);

        radApple.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                statusLabel.setText("Apple RadioButton: "
                        + (e.getStateChange()==1?"checked":"unchecked"));
            }
        });
        radMango.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                statusLabel.setText("Mango RadioButton: "
                        + (e.getStateChange()==1?"checked":"unchecked"));
            }
        });
        radPeer.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                statusLabel.setText("Peer RadioButton: "
                        + (e.getStateChange()==1?"checked":"unchecked"));
            }
        });

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();

        group.add(radApple);
        group.add(radMango);
        group.add(radPeer);

        radioButtons.add(radApple);
        radioButtons.add(radMango);
        radioButtons.add(radPeer);
        radioButtons.setLayout(new GridLayout(3, 1));

        mainFrame.setVisible(true);
    }
}

