package model;

import java.util.ArrayList;

import main.Main;

public class Node{
	
	private int bestValue = 0;
	private short[][] currentBoard;	
	private ArrayList<Node> child = new ArrayList<Node>();
	private int xmove,ymove;
	
	public Node(short[][] board){
		//주소 복사가 아닌 값 복사를 하기위해서 for문을 이용한다.
		currentBoard = new short[Main.SIZE][];
		for (int i = 0; i < Main.SIZE; i++)
			currentBoard[i] = board[i].clone();
	}

	public int getBestValue() {
		return bestValue;
	}

	public void setBestValue(int bestValue) {
		this.bestValue = bestValue;
	}

	public short[][] getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(short[][] currentBoard) {
		this.currentBoard = currentBoard;
	}

	public ArrayList<Node> getChild() {
		return child;
	}

	public void setChild(ArrayList<Node> child) {
		this.child = child;
	}

	public int getXmove() {
		return xmove;
	}

	public void setXmove(int xmove) {
		this.xmove = xmove;
	}

	public int getYmove() {
		return ymove;
	}

	public void setYmove(int ymove) {
		this.ymove = ymove;
	}
	
	
}