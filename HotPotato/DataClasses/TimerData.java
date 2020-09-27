package me.CloverCola.HotPotato.DataClasses;

import org.bukkit.boss.BossBar;

public class TimerData {

	private BossBar bar;
	private int time;

	public TimerData() {
		this.setBossBar(null);
		this.setTime(0);
	}

	public TimerData(BossBar bar, int time) {
		this.setBossBar(bar);
		this.setTime(time);
	}

	/**
	 * @return the bar
	 */
	public BossBar getBossBar() {
		return bar;
	}

	/**
	 * @param bar the bar to set
	 */
	public void setBossBar(BossBar bar) {
		this.bar = bar;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

}
