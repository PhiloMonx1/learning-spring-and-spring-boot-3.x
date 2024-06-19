package com.in28minutes.learn_spring_framework.game;

public class SuperContraGame implements GamingConsole {

	@Override
	public void up() {
		System.out.println("위로 이동");
	}

	@Override
	public void down() {
		System.out.println("앉기");
	}

	@Override
	public void left() {
		System.out.println("뒤로 이동");
	}

	@Override
	public void right() {
		System.out.println("총알 발사");
	}
}
