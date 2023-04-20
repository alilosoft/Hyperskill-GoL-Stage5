package life;

import java.util.Random;

public class Generation {

    public static final int TO_REBORN = 3;
    public static final int TO_LIVE_LEAST = 2;
    public static final int TO_LIVE_MOST = 3;

    public static Universe getNextGeneration(Universe currentGeneration) {

        Universe next = new Universe(currentGeneration.getSize());

        boolean[][] currentMap = currentGeneration.getMap();
        boolean[][] nextMap = next.getMap();
        int alive = 0;

        for (int i = 0; i < currentGeneration.getSize(); i++) {
            for (int j = 0; j < currentGeneration.getSize(); j++) {
                int count = currentGeneration.countNeighbours(i, j);
                if (currentMap[i][j]) {
                    if (count >= TO_LIVE_LEAST && count <= TO_LIVE_MOST) {
                        nextMap[i][j] = true;
                        alive++;
                    }
                } else if (count == TO_REBORN) {
                    nextMap[i][j] = true;
                    alive++;
                }
            }
        }

        next.setAlive(alive);
        return next;
    }

    public static Universe getInitialGeneration(int size) {

        Universe initial = new Universe(size);

        boolean[][] map = initial.getMap();

        Random random = new Random();

        int alive = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = random.nextBoolean();
                if (map[i][j]) {
                    alive++;
                }
            }
        }

        initial.setAlive(alive);

        return initial;
    }
}