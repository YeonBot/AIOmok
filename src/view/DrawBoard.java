package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Main;
import model.Checkerboard;

/*
 * Map을 생성자로 받아 배열값을 확인 후 흰돌, 검정돌을 그려준다. 
 */
public class DrawBoard extends JPanel {

	private static final long serialVersionUID = 1L;

	private Checkerboard checkerboard;

	public DrawBoard(Checkerboard checkerboard) {
		// TODO Auto-generated constructor stub
		this.checkerboard = checkerboard;
		setBackground(new Color(206, 167, 61)); // 배경색 지정
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK); // 그려질 색을 블랙지정
		drawBoard(arg0); // 보드를 그림
		drawStone(arg0); // 배열에 정보에 따라 돌을 그림
	}

	public void drawBoard(Graphics arg0) {
		// 1~19 까지
		for (int i = 1; i < 20; i++) {
			arg0.drawLine(2 * Main.CELL, (i + 1) * Main.CELL, Main.CELL * (20), (i + 1) * Main.CELL); // 가로줄
			arg0.drawLine((i + 1) * Main.CELL, 2 * Main.CELL, (i + 1) * Main.CELL, Main.CELL * (20)); // 세로줄
		}

	}

	public void drawStone(Graphics arg0) {
		for (int x = 4; x < Main.SIZE-4; x++) {
			for (int y = 4; y < Main.SIZE-4; y++) {
				if (checkerboard.getXY(x, y) == Main.BLACK) {
					drawBlackStone(arg0, x-3, y-3);
				} else if (checkerboard.getXY(x, y) == Main.WHITE) {
					drawWhiteStone(arg0, x-3, y-3);
				}
			}
		}
	}

	public void drawBlackStone(Graphics arg0, int x, int y) {
		arg0.setColor(Color.BLACK);
		arg0.fillOval((x + 1) * Main.CELL - 19, (y) * Main.CELL + 19, Main.STONE_SIZE, Main.STONE_SIZE);
	}

	public void drawWhiteStone(Graphics arg0, int x, int y) {
		arg0.setColor(Color.WHITE);
		arg0.fillOval(x * Main.CELL + 19, y * Main.CELL + 19, Main.STONE_SIZE, Main.STONE_SIZE);
	}

}
