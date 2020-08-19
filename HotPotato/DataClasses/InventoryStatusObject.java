package me.CloverCola.HotPotato.DataClasses;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * Data class for getting every part of the Player before storing it.
 * 
 * @author CloverCola
 *
 */
public class InventoryStatusObject {

	private ItemStack[] items;
	private int playerLevel;
	private float expPoints;
	private int playerFoodLevel;
	private float saturationLevel;
	private double healthLevel;
	private boolean isPlayerHealthScaled;
	private Collection<PotionEffect> playerEffects;

	public InventoryStatusObject() {

	}

	public InventoryStatusObject(Player player) {
		this.setItems(player.getInventory().getContents());
		this.setPlayerLevel(player.getLevel());
		this.setExpPoints(player.getExp());
		this.setPlayerFoodLevel(player.getFoodLevel());
		this.setSaturationLevel(player.getSaturation());
		this.setHealthLevel(player.getHealth());
		this.setPlayerHealthScaled(player.isHealthScaled());
		this.setPlayerEffects(player.getActivePotionEffects());
	}

	/**
	 * @return the items
	 */
	public ItemStack[] getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ItemStack[] items) {
		this.items = items;
	}

	/**
	 * @return the level
	 */
	public int getPlayerLevel() {
		return playerLevel;
	}

	/**
	 * @param level the level to set
	 */
	public void setPlayerLevel(int level) {
		this.playerLevel = level;
	}

	/**
	 * @return the exp
	 */
	public float getExpPoints() {
		return expPoints;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExpPoints(float exp) {
		this.expPoints = exp;
	}

	/**
	 * @return the playerFoodLevel
	 */
	public int getPlayerFoodLevel() {
		return playerFoodLevel;
	}

	/**
	 * @param playerFoodLevel the playerFoodLevel to set
	 */
	public void setPlayerFoodLevel(int playerFoodLevel) {
		this.playerFoodLevel = playerFoodLevel;
	}

	/**
	 * @return the saturationLevel
	 */
	public float getSaturationLevel() {
		return saturationLevel;
	}

	/**
	 * @param saturationLevel the saturationLevel to set
	 */
	public void setSaturationLevel(float saturationLevel) {
		this.saturationLevel = saturationLevel;
	}

	/**
	 * @return the healthLevel
	 */
	public double getHealthLevel() {
		return healthLevel;
	}

	/**
	 * @param healthLevel the healthLevel to set
	 */
	public void setHealthLevel(double healthLevel) {
		this.healthLevel = healthLevel;
	}

	/**
	 * @return the isPlayerHealthScaled
	 */
	public boolean isPlayerHealthScaled() {
		return isPlayerHealthScaled;
	}

	/**
	 * @param isPlayerHealthScaled the isPlayerHealthScaled to set
	 */
	public void setPlayerHealthScaled(boolean isPlayerHealthScaled) {
		this.isPlayerHealthScaled = isPlayerHealthScaled;
	}

	/**
	 * @return the playerEffects
	 */
	public Collection<PotionEffect> getPlayerEffects() {
		return playerEffects;
	}

	/**
	 * @param playerEffects the playerEffects to set
	 */
	public void setPlayerEffects(Collection<PotionEffect> playerEffects) {
		this.playerEffects = playerEffects;
	}

}
