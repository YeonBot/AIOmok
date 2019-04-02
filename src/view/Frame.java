package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import controller.MouseEventHandler;
import model.Checkerboard;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea = new JTextArea("���� �Է��ϼ���.", 5, 10);// ��ũ�ѹ� ����
	// private JScrollPane jp = new JScrollPane(textArea);// ��Ŭ�ѹ� �����
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("FILE");
	private JMenu helpMenu = new JMenu("HELP");

	private JPanel JPanel = new JPanel();
	private JPanel ButtonPanel = new JPanel();

	private Container contentPane;
	private DrawBoard drawBoard;
	private Checkerboard checkerboard;

	public Frame(String title) {

		addMenu();

		this.setBounds(200, 20, 1480, 900); // ��ġ ũ�� ����
		this.setTitle(title);
		this.setJMenuBar(menuBar);

		checkerboard = new Checkerboard();
		drawBoard = new DrawBoard(checkerboard);
		contentPane = this.getContentPane();
		contentPane.setLayout(null);

		ButtonPanel.setPreferredSize(new Dimension(200, 900));
		textArea.setPreferredSize(new Dimension(450, 900));

		JPanel.setLayout(new BorderLayout());
		JPanel.add(drawBoard, BorderLayout.CENTER);
		JPanel.add(textArea, BorderLayout.EAST);
		JPanel.add(ButtonPanel, BorderLayout.WEST);

		setContentPane(JPanel);
		drawBoard.addMouseListener(new MouseEventHandler(checkerboard, drawBoard));

		setVisible(true); // ������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���� ��ư�� ó��
	}

	public void addMenu() {
		// ���� �޴� ����
		fileMenu.add(new JMenuItem("�� ����"));
		fileMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK ^ InputEvent.ALT_MASK)); // ����Ű����
		fileMenu.add(new JMenuItem("����"));
		fileMenu.add(new JMenuItem("����"));
		fileMenu.addSeparator(); // ���м� �߰�
		fileMenu.add(new JMenuItem("����"));

		// ���� �޴� ����
		helpMenu.add(new JMenuItem("����"));
		helpMenu.add(new JMenuItem("����"));

		// �޴��� �޴��ٿ� ���
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
	}

}
