package model;

import main.Main;

public class Checkerboard {

	private short[][] board;

	// true = �� false = ��
	private boolean colorFlag = true;
	// true = ������ false = ���� ����
	private boolean isGameRun = true;

	// ������.
	// ���� : 2���� �迭 ����
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

	//colorFlag�� ���� board�� stone�� �ø���. 
	public void setStoneAboveBoard(int x, int y) {
		if (colorFlag) {
			board[x][y] = Main.BLACK;
			colorFlag = false;
		} else {
			board[x][y] = Main.WHITE;
			colorFlag = true;
		}
	}

	public void blackStoneCheck(int x, int y) {

		int horizontal = horizontalCheck(x, y, Main.BLACK);
		int vertical = verticalCheck(x, y, Main.BLACK);
		int slash = slashCheck(x, y, Main.BLACK);
		int backslash = backSlashCheck(x, y, Main.BLACK);
		if (horizontal == 5 || vertical == 5 || slash == 5 || backslash == 5) {
			this.isGameRun = false;
		}
	}

	public void whiteStoneCheck(int x, int y) {
		int horizontal = horizontalCheck(x, y, Main.WHITE);
		int vertical = verticalCheck(x, y, Main.WHITE);
		int slash = slashCheck(x, y, Main.WHITE);
		int backslash = backSlashCheck(x, y, Main.WHITE);
		if (horizontal == 5 || vertical == 5 || slash == 5 || backslash == 5) {
			this.isGameRun = false;
		}
	}

	//���� ���η�, ���η�, �밢������, ���밢������ ���� 5�� ���� Ȯ�� �ϴ� �޼ҵ�
	/*
	 * �Է¹��� x ,y �� ���������� ���� �� ��ġ 
	 * �Է¹��� stoneColor�� ���ӵ� 5���� �ִ��� Ȯ�� �Ѵ�. 
	 */
	public int horizontalCheck(int x, int y, short stoneColor) {
		
		int horizontal = 0;

		for (int i = 0; i < Main.SIZE; i++) {//���η� ���ٸ� Ȯ�� .
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
		while (slashX != 4 && slashY != 22) {
			slashX--;
			slashY++;
		}
		while (slashX != 22 && slashY != 4) {
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
		while (slashX != 4 && slashY != 4) {
			slashX--;
			slashY--;
		}
		while (slashX != 22 && slashY != 22) {
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