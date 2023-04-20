package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    public static int size = 20;

    private Universe universe;
    private final Map map;
    EffectiveFinal<Integer> genNo = new EffectiveFinal<>();
    private final JLabel genLabel;
    private final JLabel aliveLabel;
    private final Timer timer;
    private final JToggleButton toggle;
    private final JButton reset;

    public GameOfLife() {

        super("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel controlPanel = new JPanel();

        genLabel = new JLabel("Generation #");
        genLabel.setName("GenerationLabel");
        controlPanel.add(genLabel);

        aliveLabel = new JLabel("Alive: ");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 25, 0));
        controlPanel.add(aliveLabel);

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 20));

        add(controlPanel);

        map = new Map();
        add(map);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setVisible(true);

        timer = new Timer(500, (actionEvent) -> {

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
        pack();
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