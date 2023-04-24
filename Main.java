package life;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        // Use a custom font for labels
        File fontFile = new File("./resources/Nunito-SemiBold.ttf");
        try {
            Font nunitoFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            UIManager.put("Label.font", nunitoFont.deriveFont(16f));
        } catch (FontFormatException | IOException e) {
            System.err.println("Can't create font: Nunito");
            e.printStackTrace();
        }
        SwingUtilities.invokeAndWait(GameOfLife::new);
    }
}