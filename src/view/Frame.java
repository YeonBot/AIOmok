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

	private JTextArea textArea = new JTextArea("내용 입력하세요.", 5, 10);// 스크롤바 없음
	// private JScrollPane jp = new JScrollPane(textArea);// 스클롤바 만들기
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

		this.setBounds(200, 20, 1480, 900); // 위치 크기 선정
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

		setVisible(true); // 보일지 말지
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 종료 버튼시 처리
	}

	public void addMenu() {
		// 파일 메뉴 생성
		fileMenu.add(new JMenuItem("새 파일"));
		fileMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK ^ InputEvent.ALT_MASK)); // 단축키설정
		fileMenu.add(new JMenuItem("열기"));
		fileMenu.add(new JMenuItem("저장"));
		fileMenu.addSeparator(); // 구분선 추가
		fileMenu.add(new JMenuItem("종료"));

		// 도움 메뉴 생성
		helpMenu.add(new JMenuItem("버전"));
		helpMenu.add(new JMenuItem("정보"));

		// 메뉴를 메뉴바에 등록
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
	}

}
