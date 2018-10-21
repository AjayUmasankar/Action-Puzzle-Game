package ui;

import java.util.ArrayList;

import entities.Arrow;
import entities.Bomb;
import entities.Bone;
import entities.Coordinate;
import entities.Entity;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TestController extends Controller {
	
	@FXML
	private AnchorPane screen;
	@FXML 
	private Button upBtn;
	@FXML 
	private Button rightBtn;
	@FXML 
	private Button downBtn;
	@FXML 
	private Button leftBtn;
	@FXML
	private Button escapeBtn;
	@FXML
	protected Label instructions;
	@FXML
	protected GridPane imageMap;
	
	
	protected Game game;
	protected Player player;
	protected Scene scene;
	protected Game initialGame;
	
	public TestController(Stage s, Game game) {
		super(s);
		this.game = game;
		this.player = game.getPlayer();
		this.scene = s.getScene();
		this.initialGame = new Game(game);
	}
	
	/**
	 * Set up Map
	 */
	public void initialize() {
		makeGridPane(game.getHeight(), game.getWidth()); // makes it more readable even though possible code smell?
		printGame();
		instructions.setText("Arrow Keys to move, 1 for Inventory, 2 to shoot arrow, Escape to exit");
	}
	
	/**
	 * 
	 * @param height of the GridPane
	 * @param width of the GridPane
	 */
	private void makeGridPane(int height, int width) {
		imageMap.setStyle("-fx-background-image: url('https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQxsasGQIwQNwjek3F1nSwlfx60g6XpOggnxw5dyQrtCL_0x8IW')");

		for( int i = 0; i <= width; i++ ) {
	        ColumnConstraints cc = new ColumnConstraints();
	        cc.setPercentWidth(100.0 / width);
	        cc.setMaxWidth(32);
	        cc.setPrefWidth(32);
	        cc.setFillWidth(true);
	        imageMap.getColumnConstraints().add(cc);
		}
		
	   for (int i = 0; i <= height; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / height);
	        rc.setMaxHeight(32);
	        rc.setPrefHeight(32);
	        rc.setFillHeight(true);
            imageMap.getRowConstraints().add(rc);         
       }
	}

	/*
	 * Print current game
	 */
	public void printGame() {
		imageMap.getChildren().clear();
		String path;
		for( int i = 0; i <= game.getWidth(); i++ ) {
			for ( int j = 0; j <= game.getHeight(); j++ ) {
				Coordinate newPos = new Coordinate(i,j);
				Entity entity = game.getFirstEntity(newPos);
				if(game.getTheme().equals("theme1")) {
					path = "resources/theme1/";
				}else {
					path = "resources/theme2/";
				}
				//Image image = new Image(path + "white.png");
				if (entity != null) {
					Image image = new Image(path + entity.getName()
												+ ".png");
					ImageView iv = new ImageView(image);
					iv.setFitHeight(32);
					iv.setFitWidth(32);
					imageMap.add(iv, i, j);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param ke the KeyEvent that triggered by User
	 */
	public void keyPressed(KeyEvent ke) {
		KeyCode key = ke.getCode();
    	Player player = game.getPlayer();
    	System.out.println(player == initialGame.getPlayer());
    	player.setDx(0);
    	player.setDy(0);
		if (key.equals(KeyCode.W)) {
			moveUp();
		} else if (key.equals(KeyCode.S)) {
			moveDown();
		} else if (key.equals(KeyCode.A)) {
			moveLeft();
		} else if (key.equals(KeyCode.D)) {
			moveRight();
		} else if (key.equals(KeyCode.ESCAPE)) {
			previousMenu();
		} else if (key.equals(KeyCode.DIGIT1)) {
	    	System.out.println("Inventory contents: ");
	    	for (Entity curItem : game.getPlayerInventory()) {
	    		System.out.print(curItem.getName() + " ");
	    	}
	    	System.out.println("\n");
		} else if (Arrow.checkKeyCode(key)) {
	    	Arrow arrow = game.getPlayerArrow();
	    	if(arrow!=null) {
	    		arrow.setDirection(key);
	    		arrow.setPosition(player.getPosition());
	    		player.removeItem(arrow);
	    		game.addEntity(arrow);
	    		update();
	    	} else {
	    		System.out.println("No arrows :(");
	    	}

		} else if (key.equals(KeyCode.V)) {
	    	System.out.println("Checking for bomb");
	    	if(player.hasItem("Bomb")) {
	    		System.out.println("Light and drop the bomb");
	    		Bomb placedBomb = player.setBomb();
	    		placedBomb.setPosition(player.getPosition());
	    		game.addEntity(placedBomb);
	    	}
		} else if (key.equals(KeyCode.DIGIT2)) {
	    	Bone bone = null;
	    	for (Entity curItem : game.getPlayerInventory()) {
	    		if (curItem instanceof Bone) {
	    			bone = (Bone)curItem;
	    			bone.setPosition(game.getPlayer().getPosition());
	    			bone.setDx(player.getDx()); 
	    			bone.setDy(player.getDy());
	    			game.addEntity(bone);
	    			break;
	    		}
	    	}
	    	if (bone!=null) {
	    		player.removeItem(bone);
	    		update();
	    	}
		}
		printGame();
	}
		
	public void moveUp () {
		play_move();
		player.setDx(0);
		player.setDy(-1);
		update();
		printGame();
	}
	public void moveRight () {
		play_move();
		player.setDx(1);
		player.setDy(0);
		update();
		printGame();
	}
	
	public void moveLeft () {
		play_move();
		player.setDx(-1);
		player.setDy(0);
		update();
		printGame();
	}
	public void moveDown () {
		play_move();
		player.setDx(0);
		player.setDy(1);
		update();
		printGame();
	}

/**
 * Update state of the game
 */
	private void update() {
		game.update();
		if (game.isPlayerAlive() && game.hasPlayerWon()) {
			previousMenu();
		} else if (!game.isPlayerAlive()) {
			previousMenu();
		}
	}
	
	/**
	 * Sound clip plays when player moves
	 */
	private void play_move() {
		AudioClip note = new AudioClip(this.getClass().getResource("move.wav").toString());
		//note.setCycleCount(3);
		note.play();
	}
	
	/**
	 * Return to editor
	 */
	public void previousMenu() {
        Screen editor = new Screen(super.getS(), "Editor", "view/editorScreen.fxml");
        EditorController ec = new EditorController(super.getS(), initialGame);
        editor.start(ec);
	}

}
