package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    public static int size = 30;

    private Universe universe;
    private final Map map;
    EffectiveFinal<Integer> genNo = new EffectiveFinal<>();
    private final JLabel genLabel;
    private final JLabel aliveLabel;
    private final Timer timer;
    private final JToggleButton playBtn;
    private final JButton resetBtn;
    private int speed = 175;

    public GameOfLife() {

        super("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // cells grid
        map = new Map();
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#FF972F"));
        mainPanel.add(map);

        // controls
        Box controlPanel = new Box(BoxLayout.X_AXIS);
        add(controlPanel, BorderLayout.NORTH);
        controlPanel.setBackground(Color.decode("#A35200"));
        controlPanel.setOpaque(true);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        genLabel = new JLabel("Generation #");
        genLabel.setName("GenerationLabel");
        genLabel.setForeground(Color.WHITE);
        ImageIcon genIcon = new ImageIcon("resources/gen-24.png");
        genLabel.setIcon(genIcon);
        controlPanel.add(genLabel);
        controlPanel.add(Box.createHorizontalStrut(8));

        aliveLabel = new JLabel("Alive: ");
        aliveLabel.setName("AliveLabel");
        aliveLabel.setForeground(Color.WHITE);
        ImageIcon aliveIcon = new ImageIcon("resources/alive-blue-24.png");
        aliveLabel.setIcon(aliveIcon);
        controlPanel.add(aliveLabel);
        controlPanel.add(Box.createHorizontalGlue());

        timer = new Timer(speed, (actionEvent) -> {

            universe = Generation.getNextGeneration(universe);
            genLabel.setText("Generation: " + genNo.value++);
            aliveLabel.setText("Alive: " + universe.getAlive());
            map.setUniverse(universe);
            map.repaint();

        });

        // speed control
        JLabel speedIcon = new JLabel(new ImageIcon("./resources/speed-24.png"));
        JSlider speedSlider = new JSlider(0, 250, 125);
        speedSlider.addChangeListener(e -> {
            speed = 250 - speedSlider.getValue() + 50;
            timer.setDelay(speed);
        });
        speedSlider.setOpaque(false);
        speedSlider.setMaximumSize(new Dimension(100, 24));
        controlPanel.add(speedIcon);
        controlPanel.add(speedSlider);

        playBtn = new JToggleButton();
        playBtn.setName("PlayToggleButton");
        playBtn.setOpaque(false);
        playBtn.setContentAreaFilled(false);
        playBtn.setBorderPainted(false);
        playBtn.setMargin(new Insets(0, 0, 0, 0));
        ImageIcon playIcon = new ImageIcon("./resources/play-24.png");
        ImageIcon pauseIcon = new ImageIcon("./resources/pause-24.png");
        playBtn.setIcon(pauseIcon);
        playBtn.addActionListener(actionEvent -> {
            if (timer.isRunning()) {
                timer.stop();
                playBtn.setIcon(playIcon);
            } else {
                timer.restart();
                playBtn.setIcon(pauseIcon);
            }
        });
        controlPanel.add(playBtn);

        resetBtn = new JButton();
        resetBtn.setName("ResetButton");
        resetBtn.setOpaque(false);
        resetBtn.setContentAreaFilled(false);
        resetBtn.setBorderPainted(false);
        resetBtn.setMargin(new Insets(0, 0, 0, 0));
        ImageIcon resetIcon = new ImageIcon("./resources/repeat-24.png");
        resetBtn.setIcon(resetIcon);
        resetBtn.addActionListener(actionEvent -> {
            if (timer.isRunning()) {
                timer.stop();
                reset();
                timer.restart();
            } else {
                reset();
            }
        });
        controlPanel.add(resetBtn);

        reset();
        timer.start();
        pack();
        setVisible(true);
    }

    private void reset() {

        genNo.value = 0;

        universe = Generation.getInitialGeneration(size);

        genLabel.setText("Generation: " + genNo.value++);
        aliveLabel.setText("Alive: " + universe.getAlive());
        map.setUniverse(universe);
        map.repaint();
    }
}