/*
    17379526    Conor Dunne
    17424866    Martynas Jagutis
    17379773    Ronan Mascarenhas
*/
package src;

//import various requirements for creating stage, color for objects and text properties
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

//board object class
public class Board {
	
	//private color objects store the colors of various objects
    private Color background;
    private Color gamingSquares;
    private Color bearOffArea;
    private Color trianglesPlayerOne;
    private Color trianglesPlayerTwo;
    private Color counterPlayerOne;
    private Color counterPlayerTwo;

    private Color logoDiamond = Color.DEEPSKYBLUE;
    private Color logoText = Color.DARKBLUE;

  //array of spike objects to manage spikes
    private Spike[] spike;
  //arrays for storing counters of each player
    private Counter[] playerOne;
    private Counter[] playerTwo;

  //board constructor
    public Board(GraphicsContext gc, double width, double height) {
    	//set colors for board/objects
        setColors();

      //create spike array and initialise the spikes on the board
        spike = new Spike[28];
        Spike.initSpike(spike);

      //counter arrays for each players counters
        playerOne = new Counter[15];
        playerTwo = new Counter[15];

      //returns the x co ordinate value of certain spikes for positioning
        double[] loc = {spike[0].getxCenter(), spike[5].getxCenter(), spike[7].getxCenter(), spike[11].getxCenter()};
      //places the counters for each player onto their board positions
        Counter.initCounter(playerOne, loc, getCounterPlayerOne(), true);
        Counter.initCounter(playerTwo, loc, getCounterPlayerTwo(), false);

      //places player counters onto spikes
        initSpikeCounters();
    }

//  Methods for retrieving colours
    private Color getBackground() {return background;}
    private Color getGamingSquares() {return gamingSquares;}
    private Color getBearOffArea() {return bearOffArea;}
    private Color getTrianglesPlayerOne() {return trianglesPlayerOne;}
    private Color getTrianglesPlayerTwo() {return trianglesPlayerTwo;}
    private Color getCounterPlayerOne() {return counterPlayerOne;}
    private Color getCounterPlayerTwo() {return counterPlayerTwo;}
    private Color getLogoDiamond() {return logoDiamond;}
    private Color getLogoText() {return logoText;}

  //method for drawing the board and its objects using width/height values
    public void drawBoard(GraphicsContext gc, double width, double height, byte rev) {
        background(gc, width, height);
        squares(gc, width, height);
        bearOff(gc, width, height);
        spikes(gc, width, height, rev);
        logo(gc, width, height);
    }
    
  //sets the colors for the background/objects
    private void setColors() {
        background = Color.rgb(208,157,93);
        gamingSquares = Color.DARKOLIVEGREEN;
        bearOffArea = Color.DARKRED;

        trianglesPlayerOne = Color.BLACK;
        trianglesPlayerTwo = Color.WHITE;

        counterPlayerOne = Color.DARKSLATEGRAY;
        counterPlayerTwo = Color.INDIANRED;
    }

//  Method for drawing background (Boarder)
    public void background(GraphicsContext gc, double width, double height) {
        gc.setFill(getBackground());
        gc.fillRect(0, 0, width, height);
    }

//  Method for drawing gaming square areas
    public void squares(GraphicsContext gc, double width, double height) {
        gc.setFill(getGamingSquares());
      //squares rescale every time the window resizes
            gc.fillRect(width*0.05, height*0.05, width*0.3645, height*0.9);
            gc.fillRect(width*0.4895, height*0.05, width*0.3645, height*0.9);
    }

//  Method for drawing bear off areas
    public void bearOff(GraphicsContext gc, double width, double height) {
        gc.setFill(getBearOffArea());
      //squares rescale every time the window resizes
            gc.fillRect(width*0.905, height*0.05, width*0.045, height*0.35);
            gc.fillRect(width*0.905, height*0.6, width*0.045, height*0.35);
    }

//  Method for drawing the spikes (Triangles)
    public void spikes(GraphicsContext gc, double width, double height, byte rev) {
        for(int i=1; i<25; i++){
            if(i%2 == 0)
                gc.setFill(getTrianglesPlayerTwo());
            else
                gc.setFill(getTrianglesPlayerOne());

            spike[i].drawSpike(gc, width, height, rev);
        }
    }

//  Method for drawing the counters
    public void drawPlayerCounters(GraphicsContext gc, double width, double height) {
        for (int i = 0; i < 15; i++) {
            playerOne[i].drawChecker(gc, width, height);
            playerTwo[i].drawChecker(gc, width, height);
        }
    }

//  Method for drawing the board logo
    public void logo(GraphicsContext gc, double width, double height) {
        gc.setFill(getLogoDiamond());
      //logo drawn depends on window width/height
        gc.fillPolygon( new double[]{width*((6*0.3645/12)+0.05), width*((0.3645/12)+0.05), width*((6*0.3645/12)+0.05), width*((11*0.3645/12)+0.05)},
                new double[]{height*0.4, height*0.5, height*0.6, height*0.5},
                4);
        gc.fillPolygon( new double[]{width*((6*0.3645/12)+0.4895), width*((0.3645/12)+0.4895), width*((6*0.3645/12)+0.4895), width*((11*0.3645/12)+0.4895)},
                new double[]{height*0.4, height*0.5, height*0.6, height*0.5},
                4);

        gc.setFill(getGamingSquares());
        gc.fillPolygon( new double[]{width*((6*0.3645/12)+0.05), width*((3*0.3645/12)+0.05), width*((6*0.3645/12)+0.05), width*((9*0.3645/12)+0.05)},
                new double[]{height*0.4, height*0.5, height*0.6, height*0.5},
                4);
        gc.fillPolygon(new double[]{width*((6*0.3645/12)+0.4895), width*((3*0.3645/12)+0.4895), width*((6*0.3645/12)+0.4895), width*((9*0.3645/12)+0.4895)},
                new double[]{height*0.4, height*0.5, height*0.6, height*0.5},
                4);

      //adjust logo text/color
        gc.setFill(getLogoText());
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(Font.font (60));
        gc.fillText("DB2", width*((6*0.3645/12)+0.05), height*0.5, width*0.325);
        gc.fillText("DB2", width*((6*0.3645/12)+0.4895), height*0.5, width*0.325);
    }

  //adds both player counters to their starting positions
    public void initSpikeCounters() {
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                spike[1].addToSpike(playerOne[i]);
                spike[24].addToSpike(playerTwo[i]);
            }
            else if (i < 7) {
                spike[12].addToSpike(playerOne[i]);
                spike[13].addToSpike(playerTwo[i]);
            }
            else if (i < 10) {
                spike[17].addToSpike(playerOne[i]);
                spike[8].addToSpike(playerTwo[i]);
            }
            else {
                spike[19].addToSpike(playerOne[i]);
                spike[6].addToSpike(playerTwo[i]);
            }
        }
    }

  //accessor methods for spike,counters of players one and two
    public Spike[] getSpike() { return spike; }
    public Counter[] getPlayerOne() { return playerOne; }
    public Counter[] getPlayerTwo() { return playerTwo; }
}
