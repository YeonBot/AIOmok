package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Checkerboard;
import view.DrawBoard;

public class MouseEventHandler extends MouseAdapter {

	private final static int CELL = 38;

	private Checkerboard map;
	private DrawBoard board;
	// private AI ai = new AI();

	public MouseEventHandler(Checkerboard map, DrawBoard board) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.board = board;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		int x = (int) Math.round(e.getX() / (double) CELL) - 1;
		int y = (int) Math.round(e.getY() / (double) CELL) - 1;

		if (x < 1 || x > 19 || y < 1 || y > 19) {
			return;
		}
		if (map.getXY(x, y) != 0) {
			return;
		}

		System.out.println("Me : X = " + x + " Y = " + y);
		map.setStoneAboveBoard(x, y);
		map.winnerCheck(x, y);
		board.repaint();

		// ai.setMap(map.getMap());
		// Node node = new Node(map.getMap());
		// ai.Minimax(node, 1, 2);
		// System.out.println("¤»AI : X = "+node.xmove+ " Y = "+node.ymove);
		// map.setMap(node.xmove,node.ymove);
		// board.repaint();
		//
		if (map.isGameRun() == false && map.isColorflag() == true) {
			System.out.println("¹é ½Â");
		} else if (map.isGameRun() == false && map.isColorflag() == false) {
			System.out.println("Èæ ½Â");
		}
	}
}