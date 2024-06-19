package com.in28minutes.learn_spring_framework.game;

import org.springframework.stereotype.Component;

@Component
public class PacmanGame implements GamingConsole {

	@Override
	public void up() {
		System.out.println("위로 이동");
	}

	@Override
	public void down() {
		System.out.println("아래로 이동");
	}

	@Override
	public void left() {
		System.out.println("왼쪽으로 이동");
	}

	@Override
	public void right() {
		System.out.println("오른쪽으로 이동");
	}
}
