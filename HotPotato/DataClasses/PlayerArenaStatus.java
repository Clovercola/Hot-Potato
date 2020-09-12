package me.CloverCola.HotPotato.DataClasses;

import org.bukkit.entity.Player;

/**
 * Description: A data class for creating PlayerArenaStatus objects that hold
 * the status of a player in a HotPotato arena. Held data includes the Player
 * object, the ArenaName, whether they have the hot potato, and whether they are
 * alive (or eliminated). Player objects are used and converted to UUIDs so that
 * they can also be used for getting display names.
 * 
 * @author CloverCola
 */
public class PlayerArenaStatus {

	private String arenaName;
	private boolean isTagged;
	private boolean isAlive;

	public PlayerArenaStatus() {
		// Constructor for accessing class
	}

	public PlayerArenaStatus(Player player, String arenaName) {
		this.setArenaName(arenaName);
		this.setTagged(false);
		this.setAliveStatus(true);
	}

	/**
	 * @return the arena
	 */
	public String getArenaName() {
		return arenaName;
	}

	/**
	 * @param arena the arena to set
	 */
	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}

	/**
	 * @return whether the player currently has the Hot Potato.
	 */
	public boolean isTagged() {
		return isTagged;
	}

	/**
	 * @param tagged whether the player currently has the Hot Potato.
	 */
	public void setTagged(boolean tagged) {
		this.isTagged = tagged;
	}

	/**
	 * @return the whether the player is alive in any arena.
	 */
	public boolean isPlayerAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive whether the player is alive in an arena or not.
	 */
	public void setAliveStatus(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
