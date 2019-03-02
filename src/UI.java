package src;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI{

    private Board board;
    private GraphicsContext gc;
    private Canvas canvas;

    private CommandPanel commandPanel = new CommandPanel();
    private InformationPanel infoPanel = new InformationPanel();
    private StartMenu startMenu = new StartMenu();

    private int whosGo;
    private int dice1, dice2;
    private Dice d1 = new Dice(1);
    private Dice d2 = new Dice(2);

    public UI(Stage stage){
        start(stage);
    }

    public void start(Stage stage){

        stage.setTitle("Backgammon - DeepBlue2");
        BorderPane border = new BorderPane();
        Scene scene = new Scene(border, 700, 400);
        stage.setScene(scene);

        border.setBottom(getCommandPanel().addHBox());
        border.setRight(getInfoPanel().addVBox());

        Pane wrapperPane = new Pane();

        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        board = new Board(gc, canvas.getWidth(), canvas.getHeight());           //  Initialises Board and Counters

        border.setCenter(wrapperPane); //Border at center of screen
        wrapperPane.getChildren().add(canvas); //Adds canvas to wrapper pane

        //Bind width and height property to wrapper pane
        canvas.widthProperty().bind(wrapperPane.widthProperty());
        canvas.heightProperty().bind(wrapperPane.heightProperty());

        stage.show();

        drawStart();
            getStartMenu().enterUserNames(stage);

        //Uses lambda expressions to call draw() every time the window is resized
            canvas.widthProperty().addListener(event -> draw());
            canvas.heightProperty().addListener(event -> draw());


}
    //Redraws the updated game board
    public void draw() {
        board.drawBoard(gc, canvas.getWidth(), canvas.getHeight(), (byte) (getWhosGo()%2));  //  Draws the board
        board.drawPlayerCounters(gc, canvas.getWidth(), canvas.getHeight());            //  Draws the counters

        getD1().drawDice(gc, canvas.getWidth(), canvas.getHeight(), getDice1());                  //  Draws Die 1
        getD2().drawDice(gc, canvas.getWidth(), canvas.getHeight(), getDice2());                  //  Draws Die 2
    }

    private void drawStart() {
        gc.drawImage(new Image("src/Resources/Logo.png"), 0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public Dice getD1() { return d1; }
    public Dice getD2() { return d2; }
    public int getWhosGo() { return whosGo; }
    public int getDice1() { return dice1; }
    public int getDice2() { return dice2; }
    public CommandPanel getCommandPanel() { return commandPanel; }
    public InformationPanel getInfoPanel() { return infoPanel; }
    public StartMenu getStartMenu() { return startMenu; }
    public Board getBoard() { return board; }
    public GraphicsContext getGc() { return gc; }
    public Canvas getCanvas() { return canvas; }
    public void setWhosGo(int whosGo) { this.whosGo = whosGo; }
    public void setDice1(int dice1) { this.dice1 = dice1; }
    public void setDice2(int dice2) { this.dice2 = dice2; }
}