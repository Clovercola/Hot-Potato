package me.CloverCola.HotPotato.DataClasses;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ArenaStatus {
	
	private ArrayList<Player> arenaPlayers = new ArrayList<Player>();
	private boolean started;
	private Player taggedPlayer;
	
	public ArenaStatus() {
		started = false;
	}
	
	public ArenaStatus(Player player) {
		arenaPlayers.add(player);
		setStarted(false);
		setTaggedPlayer(null);
	}

	/**
	 * @return the waitingPlayers
	 */
	public ArrayList<Player> getPlayers() {
		return arenaPlayers;
	}

	/**
	 * @param waitingPlayers the waitingPlayers to set
	 */
	public void setPlayers(ArrayList<Player> arenaPlayers) {
		this.arenaPlayers = arenaPlayers;
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
