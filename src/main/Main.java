package main;

import view.Frame;

public class Main {

	public final static int CELL = 38; //
	public final static int SIZE = 21; // �ٵ��� ũ�� 21 x 21
	public final static int STONE_SIZE = 38; // �� ������

	public final static short SPACE = 0;
	public final static short BLACK = 1;
	public final static short BLOCK = 3;
	public final static short WHITE = 5;

	public static void main(String[] args) {

		// (Swing GUI) Main Frame ����
		new Frame("AI ���� ����");

	}

}
