package me.CloverCola.HotPotato.DataClasses;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ArenaStatus {
	
	private ArrayList<Player> waitingPlayers = new ArrayList<Player>();
	private boolean started;
	private Player taggedPlayer;
	
	public ArenaStatus() {
		started = false;
	}
	
	public ArenaStatus(Player player) {
		waitingPlayers.add(player);
		setStarted(false);
		setTaggedPlayer(null);
	}

	/**
	 * @return the waitingPlayers
	 */
	public ArrayList<Player> getWaitingPlayers() {
		return waitingPlayers;
	}

	/**
	 * @param waitingPlayers the waitingPlayers to set
	 */
	public void setWaitingPlayers(ArrayList<Player> waitingPlayers) {
		this.waitingPlayers = waitingPlayers;
	}

	/**
	 * @return the started
	 */
	public boolean hasStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	/**
	 * @return the taggedPlayer
	 */
	public Player getTaggedPlayer() {
		return taggedPlayer;
	}

	/**
	 * @param taggedPlayer the taggedPlayer to set
	 */
	public void setTaggedPlayer(Player taggedPlayer) {
		this.taggedPlayer = taggedPlayer;
	}
	
}
