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
        //setPreferredSize(new Dimension(450, 450));
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
        int offsetY = (getHeight() % GameOfLife.size) / 2;
        int w = getWidth() / GameOfLife.size;
        int offsetX = (getWidth() % GameOfLife.size) / 2;

        boolean[][] map = universe.getMap();
        for (int i = 0; i < GameOfLife.size; i++) {
            for (int j = 0; j < GameOfLife.size; j++) {
                g.setColor(bgColor); // draw the grid
                int x = j * w + offsetX;
                int y = i * h + offsetY;
                //g.drawRect(x, y, w, h);
                if (map[i][j]) {
                    g.setColor(aliveCellColor);
                    //g.fillRect(x, y, w, h);
                    g.fillOval(x, y, w, h);
                }
            }
        }
    }
}
