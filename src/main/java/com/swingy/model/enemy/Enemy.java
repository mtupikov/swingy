package com.swingy.model.enemy;

import com.swingy.model.Creature;
import com.swingy.model.races.MonsterRace;

import java.util.Random;

public class Enemy extends Creature {
	public Enemy(Creature player) {
		this.setName("Monster");
		this.setPlayerRace(new MonsterRace());
		this.setLevel(player.getLevel() + (new Random().nextBoolean() ? 1 : 0));
		this.setHealthPoints(this.getLevel() * 50 + 100);
		this.setAttack(2 * this.getLevel() + new Random().nextInt(3));
	}
}
