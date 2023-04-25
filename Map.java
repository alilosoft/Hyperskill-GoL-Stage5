package life;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {


    private Universe universe;
    private final Color bgColor = Color.decode("#FF972F");

    private final Color aliveCellColor = Color.decode("#4CBB17");
    private final Color deadCellColor = Color.decode("#D6F8C6");

    public Map() {
        super();
        setBackground(deadCellColor);//#CC6600
        setPreferredSize(new Dimension(500, 500));
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
        boolean[][] map = universe.getMap();


        for (int i = 0; i < GameOfLife.size; i++) {
            for (int j = 0; j < GameOfLife.size; j++) {
                g.setColor(bgColor); // draw the grid
                g.drawRect(j * w, i * h, w, h);
                if (map[i][j]) {
                    g.setColor(aliveCellColor);
                    g.fillRect(j * w, i * h, w, h);
                    //g.fillOval(j * w, i * h, w, h);
                }
            }
        }
    }
}
