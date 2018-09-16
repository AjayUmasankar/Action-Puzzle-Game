package UnitTests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.junit.Before;

import ui.Application;
import ui.InputManagerMenu;
public abstract class testSetup {
	protected static final int DOWN = KeyEvent.VK_DOWN;
	protected static final int UP = KeyEvent.VK_UP;
	protected static final int LEFT = KeyEvent.VK_LEFT;
	protected static final int RIGHT = KeyEvent.VK_RIGHT;
	protected Robot robot;
	
	@Before
	public void setUp() throws Exception {
		Application app = new Application("Game Menu", 32, 32); //Create window application
		KeyListener playerInput = new InputManagerMenu(app);
		app.getFrame().addKeyListener(playerInput);  //refactor this
		this.robot = new Robot();
		robot.waitForIdle();
		robot.setAutoDelay(50);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
		robot.delay(100);
		//System.out.println(app.getFrame().getKeyListeners()[0]);
//
//		KeyListener playerInput = new InputManagerPlayer(game, app);
//		app.getFrame().addKeyListener(playerInput);
//		game.init();
//		game.setPlayerInput((InputManagerPlayer)playerInput);
//		game.setPlayerOne(player);
	}
	

	public void move (int keyCode, int count) {
		int i = 0;
		while (i < count) {
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
			robot.delay(100);
			i++;
		}
	}

}