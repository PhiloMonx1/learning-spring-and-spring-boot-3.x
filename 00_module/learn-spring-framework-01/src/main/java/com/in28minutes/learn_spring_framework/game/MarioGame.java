package com.in28minutes.learn_spring_framework.game;

public class MarioGame implements GamingConsole {

	@Override
	public void up() {
		System.out.println("점프");
	}

	@Override
	public void down() {
		System.out.println("파이프로 이동");
	}

	@Override
	public void left() {
		System.out.println("뒤로 이동");
	}

	@Override
	public void right() {
		System.out.println("가속");
	}
}
