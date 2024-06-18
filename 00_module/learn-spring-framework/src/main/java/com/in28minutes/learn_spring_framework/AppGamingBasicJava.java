package com.in28minutes.learn_spring_framework;

public class AppGamingBasicJava {

	public static void main(String[] args) {

		var marioGame = new MarioGame();
		var gameRunner = new GameRunner(marioGame);
		gameRunner.run();
	}
}
