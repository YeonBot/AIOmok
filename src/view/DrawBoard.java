package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Main;
import model.Checkerboard;

/*
 * Map�� �����ڷ� �޾� �迭���� Ȯ�� �� ��, �������� �׷��ش�. 
 */
public class DrawBoard extends JPanel {

	private static final long serialVersionUID = 1L;

	private Checkerboard checkerboard;

	public DrawBoard(Checkerboard checkerboard) {
		// TODO Auto-generated constructor stub
		this.checkerboard = checkerboard;
		setBackground(new Color(206, 167, 61)); // ���� ����
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.BLACK); // �׷��� ���� ������
		drawBoard(arg0); // ���带 �׸�
		drawStone(arg0); // �迭�� ������ ���� ���� �׸�
	}

	public void drawBoard(Graphics arg0) {
		// 1~19 ����
		for (int i = 1; i < Main.SIZE - 1; i++) {
			arg0.drawLine(2 * Main.CELL, (i + 1) * Main.CELL, Main.CELL * (Main.SIZE - 1), (i + 1) * Main.CELL); // ������
			arg0.drawLine((i + 1) * Main.CELL, 2 * Main.CELL, (i + 1) * Main.CELL, Main.CELL * (Main.SIZE - 1)); // ������
		}

	}

	public void drawStone(Graphics arg0) {
		for (int x = 1; x < Main.SIZE - 1; x++) {
			for (int y = 1; y < Main.SIZE - 1; y++) {
				if (checkerboard.getXY(x, y) == Main.BLACK) {
					drawBlackStone(arg0, x, y);
				} else if (checkerboard.getXY(x, y) == Main.WHITE) {
					drawWhiteStone(arg0, x, y);
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
