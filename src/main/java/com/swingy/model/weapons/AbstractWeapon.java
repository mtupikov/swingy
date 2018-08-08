package com.swingy.model.weapons;

import com.swingy.model.InventoryObjectInterface;
import com.swingy.model.InventoryObjects;

public abstract class AbstractWeapon implements InventoryObjectInterface {
	private int					attack;
	private String				name;
	private InventoryObjects	type;

	public AbstractWeapon() {
		type = InventoryObjects.WEAPON;
	}

	public int getAttack() {
		return attack;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getName() {
		return name;
	}

	public InventoryObjects returnThis() {
		return type;
	}
}
