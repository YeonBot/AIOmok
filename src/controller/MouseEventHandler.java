package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Main;
import model.AI;
import model.Checkerboard;
import model.Node;
import view.DrawBoard;

public class MouseEventHandler extends MouseAdapter {

	private Checkerboard checkerboard;
	private DrawBoard drawBoard;
	private AI ai = new AI();

	private int x;
	private int y;

	public MouseEventHandler(Checkerboard checkerboard, DrawBoard drawBoard) {
		// TODO Auto-generated constructor stub
		this.checkerboard = checkerboard;
		this.drawBoard = drawBoard;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		// x,y �� ����
		setPositionXY(e);

		// x,y �� ���� Ȯ��, ����ִ��� Ȯ��.
		if (checkPositionXY()) {
			return;
		}

		if (checkerboard.isGameRun()) {
			UsersetStone();
		}
		if (checkerboard.isGameRun()) {
			AISetStone();
		}

		// ���� ���� Ȯ�� --- ���� �Ǿ��ٸ� ?
		GameRunCheck();
	}

	public void setPositionXY(MouseEvent e) {
		x = (int) Math.round(e.getX() / (double) Main.CELL) + 2;
		y = (int) Math.round(e.getY() / (double) Main.CELL) + 2;

//		System.out.println("����� : X = " + x + " Y = " + y);
	}

	public boolean checkPositionXY() {
		if (x < 4 || x > 22 || y < 4 || y > 22) {
			return true;
		}
		if (checkerboard.getXY(x, y) != 0) {
			return true;
		}
		if (checkerboard.isGameRun() == false) {
			return true;
		}
		return false;
	}

	public void UsersetStone() {
		checkerboard.setStoneAboveBoard(x, y);
		checkerboard.blackStoneCheck(x, y);
		drawBoard.repaint();
	}

	public void AISetStone() {

		Node node = new Node(checkerboard.getMap());
		ai.setMap(checkerboard.getMap());
		ai.Minimax(node, 1, 2);
//		System.out.println(node.getXmove());
//		System.out.println(node.getYmove());

		checkerboard.setStoneAboveBoard(node.getXmove(), node.getYmove());
		checkerboard.whiteStoneCheck(node.getXmove(), node.getYmove());
		drawBoard.repaint();
	}

	public void GameRunCheck() {
		if (checkerboard.isGameRun() == false && checkerboard.isColorflag() == true) {
			System.out.println("�� ��");
		} else if (checkerboard.isGameRun() == false && checkerboard.isColorflag() == false) {
			System.out.println("�� ��");
		}
	}

}