
package entities;

import java.awt.event.KeyEvent;

public class HoverPotion extends Entity {
	public HoverPotion(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_P;
	}
	
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		return true;
	}
	
	public String getName() {
		return "HoverPotion";
	}
}
