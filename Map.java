package life;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {


    private Universe universe;

    public Map() {
        super();
        setBackground(Color.decode("#FF972F"));//#CC6600
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createEmptyBorder(5, 7, 7, 7));
    }

    public Universe getUniverse() {
        return universe;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        if (universe == null) {
            return;
        }

        int h = getHeight() / GameOfLife.size;
        int w = getWidth() / GameOfLife.size;
        g.setColor(Color.black);
        boolean[][] map = universe.getMap();

        for (int i = 0; i < GameOfLife.size; i++) {
            for (int j = 0; j < GameOfLife.size; j++) {
                g.drawRect(j * w, i * h, w, h);
                if (map[i][j]) {
                    g.fillRect(j * w, i * h, w, h);
                }
            }
        }
    }
}
