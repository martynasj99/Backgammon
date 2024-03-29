/*
    17379526    Conor Dunne
    17424866    Martynas Jagutis
    17379773    Ronan Mascarenhas
*/

package Sprint1To4.Objects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

import static java.lang.Math.abs;

public class Dice {
    Random rand = new Random();
    private int numberOfSides;
    private int diceNumber;

    private Image image1 = new Image("Sprint1To4/Resources/Dice/1.png");
    private Image image2 = new Image("Sprint1To4/Resources/Dice/2.png");
    private Image image3 = new Image("Sprint1To4/Resources/Dice/3.png");
    private Image image4 = new Image("Sprint1To4/Resources/Dice/4.png");
    private Image image5 = new Image("Sprint1To4/Resources/Dice/5.png");
    private Image image6 = new Image("Sprint1To4/Resources/Dice/6.png");

    public Dice(int diceNumber) {
        this.numberOfSides = 6;
        this.diceNumber = diceNumber;
    }

    public int rollDice(GraphicsContext gc, double width, double height) {
        int number = rand.nextInt(numberOfSides) + 1;
        drawDice(gc, width, height, number);

        return number;
    }

    public void drawDice(GraphicsContext gc, double width, double height, int number) {
        if(diceNumber == 1)
            gc.drawImage(getImage(number), width*0.6, height*0.4, width*0.05, width*0.05);
        else
            gc.drawImage(getImage(number), width*0.7, height*0.5, width*0.05, width*0.05);
    }

    private Image getImage(int i) {
        Image img = image1;
        switch (abs(i)) {
            case 1:
                img = image1;
                break;
            case 2:
                img = image2;
                break;
            case 3:
                img = image3;
                break;
            case 4:
                img = image4;
                break;
            case 5:
                img = image5;
                break;
            case 6:
                img = image6;
        }

        return img;
    }
}
