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
    private final JToggleButton playBtn;
    private final JButton resetBtn;
    private int speed = 175;

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
        controlPanel.add(Box.createHorizontalStrut(32));
        add(controlPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        //mainPanel.setPreferredSize(new Dimension(500, 500));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#FF972F"));
        add(mainPanel, BorderLayout.CENTER);

        map = new Map();
        mainPanel.add(map);

        timer = new Timer(speed, (actionEvent) -> {

            universe = Generation.getNextGeneration(universe);
            genLabel.setText("Generation: " + genNo.value++);
            aliveLabel.setText("Alive: " + universe.getAlive());
            map.setUniverse(universe);
            map.repaint();

        });


        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonsPanel.setOpaque(false);

        // speed control
        JLabel speedIcon = new JLabel(new ImageIcon("./resources/speed-24.png"));
        JSlider speedSlider = new JSlider(0, 250, 125);
        speedSlider.addChangeListener(e -> {
            speed = 250 - speedSlider.getValue() + 50;
            timer.setDelay(speed);
        });
        speedSlider.setOpaque(false);
        speedSlider.setPreferredSize(new Dimension(150, 24));
        buttonsPanel.add(speedIcon);
        buttonsPanel.add(speedSlider);
        buttonsPanel.add(Box.createHorizontalStrut(24));


        playBtn = new JToggleButton();
        playBtn.setName("PlayToggleButton");
        // make it transparent
        playBtn.setOpaque(false);
        playBtn.setContentAreaFilled(false);
        playBtn.setBorderPainted(false);
        playBtn.setMargin(new Insets(0,0,0,0));
        // set icons
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
        buttonsPanel.add(playBtn);

        resetBtn = new JButton();
        resetBtn.setName("ResetButton");
        // make it transparent
        resetBtn.setOpaque(false);
        resetBtn.setContentAreaFilled(false);
        resetBtn.setBorderPainted(false);
        resetBtn.setMargin(new Insets(0,0,0,0));
        // set icon
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
        buttonsPanel.add(resetBtn);

        controlPanel.add(buttonsPanel);

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