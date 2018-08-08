package com.swingy.model.map;

import com.swingy.model.Creature;

import java.util.Random;

public class Map {
	private int		size;
	private char	map[][];

	private void generateMap() {
		Random random = new Random();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (random.nextInt(size * 5) == 0 && i != size / 2 && j != size / 2)
					map[i][j] = 'E';
				else
					map[i][j] = '.';
		checkEmptyMap();
	}

	public void checkEmptyMap() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (map[i][j] != '.')
					return;
		generateMap();
	}

	private void createMap(Creature creature) {
		size = (creature.getLevel() - 1) * 5 + 10 - (creature.getLevel() % 2);
		map = new char[size][size];
		generateMap();
		creature.setPosX(size / 2);
		creature.setPosY(size / 2);
	}

	public Map(Creature creature) {
		createMap(creature);
	}

	public void expandMap(Creature creature) {
		createMap(creature);
	}

	public int getSize() {
		return size;
	}

	public char getMapCell(int i, int j) {
		return map[i][j];
	}

	public void setMapCell(int i, int j, char c) {
		map[i][j] = c;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public char[][] getMap() {
		return map;
	}
}
