package model;

import main.Main;

public class Checkerboard {

	private short[][] board;

	// true = 흑 false = 백
	private boolean colorFlag = true;
	// true = 게임중 false = 게임 종료
	private boolean isGameRun = true;

	// 생성자.
	// 역할 : 2차원 배열 생성
	public Checkerboard() {
		board = new short[Main.SIZE][Main.SIZE];
	}

	public boolean isColorflag() {
		return colorFlag;
	}

	public boolean isGameRun() {
		return isGameRun;
	}
	
	public short getXY(int x, int y) {
		return board[x][y];
	}

	public short[][] getMap() {
		return board;
	}

	//colorFlag를 보고 board에 stone을 올린다. 
	public void setStoneAboveBoard(int x, int y) {
		if (colorFlag) {
			board[x][y] = Main.BLACK;
			colorFlag = false;
		} else {
			board[x][y] = Main.WHITE;
			colorFlag = true;
		}
	}
	
	// 승리자를 채크 method 매번 흑백을 채크 할 필요 없이, 마지막 돌의 색상만 채크 한다.
	public void winnerCheck(int x, int y) {
		if (colorFlag) { // 마지막 돌이 하양인 경우 하양만 확인
			whiteStoneCheck(x, y);
		} else { // 마지막 돌이 검정인 경우 검정만 확인
			blackStoneCheck(x, y);
		}
	}

	public void blackStoneCheck(int x, int y) {

		int horizontal = horizontalCheck(x, y, Main.BLACK);
		int vertical = verticalCheck(x, y, Main.BLACK);
		int slash = slashCheck(x, y, Main.BLACK);
		int backslash = backSlashCheck(x, y, Main.BLACK);
		if (horizontal == 5 || vertical == 5 || slash == 5 || backslash == 5) {
			isGameRun = false;
		}
	}

	public void whiteStoneCheck(int x, int y) {
		int horizontal = horizontalCheck(x, y, Main.WHITE);
		int vertical = verticalCheck(x, y, Main.WHITE);
		int slash = slashCheck(x, y, Main.WHITE);
		int backslash = backSlashCheck(x, y, Main.WHITE);
		if (horizontal == 5 || vertical == 5 || slash == 5 || backslash == 5) {
			isGameRun = false;
		}
	}

	//각각 가로로, 세로로, 대각선으로, 역대각선으로 돌이 5개 인지 확인 하는 메소드
	/*
	 * 입력받은 x ,y 는 마지막으로 돌을 둔 위치 
	 * 입력받은 stoneColor로 연속된 5개가 있는지 확인 한다. 
	 */
	public int horizontalCheck(int x, int y, short stoneColor) {
		
		int horizontal = 0;

		for (int i = 0; i < Main.SIZE; i++) {//가로로 한줄만 확인 .
			if (board[i][y] == stoneColor) {
				horizontal++;
			} else {
				if (horizontal == 5)
					return 5;
				horizontal = 0;
			}
		}
		return 0;
	}

	public int verticalCheck(int x, int y, short stoneColor) {
		int vertical = 0;

		for (int i = 0; i < Main.SIZE; i++) {
			if (board[x][i] == stoneColor) {
				vertical++;
			} else {
				if (vertical == 5)
					return 5;
				vertical = 0;
			}
		}
		return 0;
	}

	public int slashCheck(int x, int y, short stoneColor) {
		int slash = 0;
		int slashX = x, slashY = y;
		while (slashX != 0 && slashY != 19) {
			slashX--;
			slashY++;
		}
		while (slashX != 19 && slashY != 0) {
			if (board[slashX][slashY] == stoneColor) {
				slash++;
			} else {
				if (slash == 5)
					return 5;
				slash = 0;
			}
			slashX++;
			slashY--;
		}
		return 0;
	}

	public int backSlashCheck(int x, int y, short stoneColor) {

		int slash = 0;
		int slashX = x, slashY = y;
		while (slashX != 0 && slashY != 0) {
			slashX--;
			slashY--;
		}
		while (slashX != 19 && slashY != 19) {
			if (board[slashX][slashY] == stoneColor) {
				slash++;
			} else {
				if (slash == 5)
					return 5;
				slash = 0;
			}
			slashX++;
			slashY++;
		}
		return 0;
	}

}