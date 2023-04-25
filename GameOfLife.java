package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    public static int size = 50;

    private Universe universe;
    private final Map map;
    EffectiveFinal<Integer> genNo = new EffectiveFinal<>();
    private final JLabel genLabel;
    private final JLabel aliveLabel;
    private final Timer timer;
    private final JToggleButton toggle;
    private final JButton reset;
    private int speed = 100;

    public GameOfLife() {

        super("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        controlPanel.setBackground(Color.decode("#A35200"));

        genLabel = new JLabel("Generation #");
        genLabel.setName("GenerationLabel");
        genLabel.setForeground(Color.WHITE);
        ImageIcon genIcon = new ImageIcon("resources/gen-24.png");
        genLabel.setIcon(genIcon);
        controlPanel.add(genLabel);

        aliveLabel = new JLabel("Alive: ");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setForeground(Color.WHITE);
        ImageIcon aliveIcon = new ImageIcon("resources/alive-blue-24.png");
        aliveLabel.setIcon(aliveIcon);
        controlPanel.add(aliveLabel);

        add(controlPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#FF972F"));
        add(mainPanel, BorderLayout.CENTER);

        map = new Map();
        mainPanel.add(map);

        pack();
        setVisible(true);

        timer = new Timer(speed, (actionEvent) -> {

            universe = Generation.getNextGeneration(universe);
            genLabel.setText("Generation #" + genNo.value++);
            aliveLabel.setText("Alive: " + universe.getAlive());
            map.setUniverse(universe);
            map.repaint();

        });

        toggle = new JToggleButton(" Pause ");
        toggle.setName("PlayToggleButton");
        controlPanel.add(toggle);
        toggle.addActionListener(actionEvent -> {

            if (timer.isRunning()) {
                timer.stop();
                toggle.setText("Resume");
            } else {
                timer.restart();
                toggle.setText(" Pause ");
            }
        });

        controlPanel.add(new JLabel(" "));

        reset = new JButton("Reset");
        reset.setName("ResetButton");
        controlPanel.add(reset);
        reset.addActionListener(actionEvent -> {

            if (timer.isRunning()) {
                timer.stop();
                reset();
                timer.restart();
            } else {
                reset();
            }
        });

        reset();
        timer.start();

    }

    private void reset() {

        genNo.value = 0;

        universe = Generation.getInitialGeneration(size);

        genLabel.setText("Generation #" + genNo.value++);
        aliveLabel.setText("Alive: " + universe.getAlive());
        map.setUniverse(universe);
        map.repaint();
    }
}