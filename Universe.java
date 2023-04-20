package life;

import java.util.function.Consumer;

public class Universe {

    private final boolean[][] map;
    private final int size;

    private int alive = 0;

    public Universe(int size) {
        this.map = new boolean[size][size];
        this.size = size;
    }

    public boolean[][] getMap() {
        return map;
    }

    public int getAlive() {
        return alive;
    }

    public void setAlive(int alive) {
        this.alive = alive;
    }

    public int getSize() {
        return size;
    }

    public int countNeighbours(int row, int col) {

        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == j && i == 0) {
                    continue;
                }

                int r = row + i;
                if (r < 0) {
                    r = size - 1;
                } else if (r == size) {
                    r = 0;
                }

                int c = col + j;
                if (c < 0) {
                    c = size - 1;
                } else if (c == size) {
                    c = 0;
                }

                if (map[r][c]) {
                    count++;
                }
            }
        }
        return count;
    }
}