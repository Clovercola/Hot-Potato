package me.CloverCola.HotPotato.DataClasses;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ArenaStatus {
	
	private ArrayList<Player> waitingPlayers;
	private boolean started;
	
	public ArenaStatus() {
		started = false;
	}
	
	public ArenaStatus(Player player) {
		waitingPlayers.add(player);
		started = false;
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
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}
	
}
