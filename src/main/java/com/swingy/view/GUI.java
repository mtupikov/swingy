package com.swingy.view;

import com.swingy.model.Creature;

import java.util.ArrayList;

public interface GUI {
	void	drawMenu();
	void	drawChoosePlayer(ArrayList<Creature> players);
	void	drawChooseRace();
	void	drawChooseClass();
	void	drawChooseName();
	void	drawMap();
	void	drawPlayer();
	void	drawCheckPlayerWantsToBattle();
	void	drawDie();
	void	drawWin();
	void	drawGetNothing();
	int		drawGetPrize(String type);
}
