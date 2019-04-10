package model;

import main.Main;

public class AI {

	private short[][] map;

	public AI() {
		map = new short[Main.SIZE][Main.SIZE];
	}

	public short[][] getMap() {
		return map;
	}

	public void setMap(short[][] map) {
		this.map = map;
	}

	public int Heuristic(short[][] board, int myStone, int yourStone) {

		int score = 0;
		short[][] boardT = new short[27][27];  
		short[][] boardM = new short[27][27];
		for (int i = 4; i < Main.SIZE-4; i++) {
			for (int j = 4; j < Main.SIZE-4; j++) {
				boardT[j][i] = board[i][j];
				boardM[Main.SIZE-4 - i][j] = board[i][j];				
			}
		}
		

		for (int i = 4; i < Main.SIZE-4; i++) {
			for (int j = 4; j < Main.SIZE-4; j++) {
				//���� ����.
				//5�� 5���� �����ε�
				if(board[i-3][j] == myStone && board[i-2][j] == myStone &&board[i-1][j] == myStone &&board[i][j] == myStone && board[i+1][j] == myStone){
					//�糡�� ������ ��� ,6���̶�� 
					if(board[i-4][j] == myStone || board[i+2][j] == myStone ){
						score -= 1000;
					}
					//6���� �ƴ϶�� �¸��� . 
					else {
					score += 1000000;
					}
				}
				//4�� 4���� ������ ���
				else if(board[i-3][j] != myStone && board[i-2][j] == myStone && board[i-1][j] == myStone &&board[i][j] == myStone &&board[i+1][j] == myStone &&board[i+2][j] != myStone){
					//�糡�� ��뵹 -> ������ 
					if(board[i-3][j] == yourStone && board[i+2][j] == yourStone){
						score -= 1000;
					}
					//�ϳ��� ��뵹 -> ���� ����.
					else if(board[i-3][j] == yourStone || board[i+2][j] == yourStone){
						score += 10000;
					}
					//�� �Ը� �� . ���� 5�� ���� ���� ���ٸ�  ���� 2����. 
					else{
						score += 500000;
					}
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(board[i-3][j] == myStone && board[i-2][j] == 0 &&board[i-1][j] == myStone &&board[i][j] == myStone && board[i+1][j] == myStone && board[i-4][j] != myStone && board[i+2][j] !=myStone){
					//�糡�� �� ���� �ƴ� . -> 6�� ����Ǽ� ����
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(board[i-3][j] == myStone && board[i-2][j] == myStone &&board[i-1][j] == 0 &&board[i][j] == myStone && board[i+1][j] == myStone&& board[i-4][j] != myStone && board[i+2][j] !=myStone){
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(board[i-3][j] == myStone && board[i-2][j] == myStone &&board[i-1][j] == myStone &&board[i][j] == 0 && board[i+1][j] == myStone&& board[i-4][j] != myStone && board[i+2][j] !=myStone){
					score += 10000;
				}
				//3�� 3���� ������ ���.
				else if(board[i-2][j] != myStone && board[i-1][j] == myStone && board[i][j] == myStone &&board[i+1][j] == myStone &&board[i+2][j] != myStone ){
					//��뵹�� ������ ���. ��� ������ �����ִ� ���
					if(board[i-2][j] == yourStone && board[i+2][j] == yourStone){
						score -= 1000;	
					}else if(board[i-3][j] == yourStone && board[i+3][j] == yourStone){
						score -= 1000;	
					}else if(board[i-2][j] == yourStone || board[i+2][j] == yourStone){
						score -= 1000;	
					}else {
						score += 5000;
					}
				}
				//3�� 3���� ������ ���.
				else if(board[i-3][j] != myStone && board[i-2][j] == myStone && board[i-1][j] == 0 &&board[i][j] == myStone &&board[i+1][j] == myStone && board[i+2][j] != myStone ){
					if(board[i-3][j] == yourStone && board[i+2][j] == yourStone){
						score -= 1000;	
					}else if (board[i-3][j] == yourStone || board[i+2][j] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				else if(board[i-3][j] != myStone && board[i-2][j] == myStone && board[i-1][j] == myStone &&board[i][j] == 0 &&board[i+1][j] == myStone && board[i+2][j] != myStone ){
					if(board[i-3][j] == yourStone && board[i+2][j] == yourStone){
						score -= 1000;	
					}else if (board[i-3][j] == yourStone || board[i+2][j] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				//2��
				else if(board[i-2][j] != myStone && board[i-1][j] == myStone &&board[i][j] == myStone &&board[i+1][j] != myStone){
					if(board[i-2][j] == yourStone && board[i+1][j] == yourStone){
						score -= 100000;
					}else if(board[i-2][j] == yourStone || board[i+1][j] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(board[i-2][j] != myStone && board[i-1][j] == 0 &&board[i][j] == myStone &&board[i+1][j] == myStone && board[i+2][j] != myStone){
					if(board[i-2][j] == yourStone && board[i+2][j] == yourStone){
						score -= 100000;
					}else if(board[i-2][j] == yourStone || board[i+2][j] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(board[i-2][j] != myStone && board[i-1][j] == myStone &&board[i][j] == 0 &&board[i+1][j] == 0 && board[i+2][j] == myStone&& board[i+3][j] != myStone){
					if(board[i-2][j] == yourStone && board[i+3][j] == yourStone){
						score -= 100000;
					}else if(board[i-2][j] == yourStone || board[i+3][j] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}
				//��� ����
				//5�� ��� ���� 
				if(board[i-2][j] == myStone && board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 70000;
				}else if(board[i-2][j] == yourStone && board[i-1][j] == myStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 70000;
				}else if(board[i-2][j] == yourStone && board[i-1][j] == yourStone &&board[i][j] == myStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 70000;
				}else if(board[i-2][j] == yourStone && board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == myStone &&board[i+2][j] == yourStone ){
					score += 70000;
				}else if(board[i-2][j] == yourStone && board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == myStone ){
					score += 70000;
				}
				//4�� ��� ����
				else if(board[i-1][j] == myStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 30000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == myStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 30000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == myStone &&board[i+2][j] == yourStone ){
					score += 30000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == myStone ){
					score += 30000;
				}
				//3�� ��� 
				else if(board[i-1][j] == myStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone){
					score += 15000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == myStone &&board[i+1][j] == yourStone){
					score += 15000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == myStone){
					score += 15000;
				}
				//2�� ���
				else if(board[i-1][j] == myStone &&board[i][j] == yourStone){
					score += 5000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == myStone){
					score += 5000;
				}
				//1�� ��
				else if(board[i][j] == myStone){
					if(board[i+1][j] == yourStone || board[i-1][j] == yourStone){
						score += 10;
					}
				}
				
				//�밢��////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//���� ����.
				//5�� 5���� �����ε�
				if(board[i-3][j-3] == myStone && board[i-2][j-2] == myStone &&board[i-1][j-1] == myStone &&board[i][j] == myStone && board[i+1][j+1] == myStone){
					//�糡�� ������ ��� ,6���̶�� 
					if(board[i-4][j-4] == myStone || board[i+2][j+2] == myStone ){
						score -= 1000;
					}
					//6���� �ƴ϶�� �¸��� . 
					else {
					score += 1000000;
					}
				}
				//4�� 4���� ������ ���
				else if(board[i-3][j-3] != myStone && board[i-2][j-2] == myStone && board[i-1][j-1] == myStone &&board[i][j] == myStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] != myStone){
					//�糡�� ��뵹 -> ������ 
					if(board[i-3][j-3] == yourStone && board[i+2][j+2] == yourStone){
						score -= 1000;
					}
					//�ϳ��� ��뵹 -> ���� ����.
					else if(board[i-3][j-3] == yourStone || board[i+2][j+2] == yourStone){
						score += 10000;
					}
					//�� �Ը� �� . ���� 5�� ���� ���� ���ٸ�  ���� 2����. 
					else{
						score += 500000;
					}
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(board[i-3][j-3] == myStone && board[i-2][j-2] == 0 &&board[i-1][j-1] == myStone &&board[i][j] == myStone && board[i+1][j+1] == myStone && board[i-4][j-4] != myStone && board[i+2][j+2] !=myStone){
					//�糡�� �� ���� �ƴ� . -> 6�� ����Ǽ� ����
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(board[i-3][j-3] == myStone && board[i-2][j-2] == myStone &&board[i-1][j-1] == 0 &&board[i][j] == myStone && board[i+1][j+1] == myStone&& board[i-4][j-4] != myStone && board[i+2][j+2] !=myStone){
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(board[i-3][j-3] == myStone && board[i-2][j-2] == myStone &&board[i-1][j-1] == myStone &&board[i][j] == 0 && board[i+1][j+1] == myStone&& board[i-4][j-4] != myStone && board[i+2][j+2] !=myStone){
					score += 10000;
				}
				//3�� 3���� ������ ���.
				else if(board[i-2][j-2] != myStone && board[i-1][j-1] == myStone && board[i][j] == myStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] != myStone ){
					//��뵹�� ������ ���. ��� ������ �����ִ� ���
					if(board[i-2][j-2] == yourStone && board[i+2][j+2] == yourStone){
						score -= 1000;	
					}else if(board[i-3][j-3] == yourStone && board[i+3][j+3] == yourStone){
						score -= 1000;	
					}else if(board[i-2][j-2] == yourStone || board[i+2][j+2] == yourStone){
						score -= 1000;	
					}else {
						score += 5000;
					}
				}
				//3�� 3���� ������ ���.
				else if(board[i-3][j-3] != myStone && board[i-2][j-2] == myStone && board[i-1][j-1] == 0 &&board[i][j] == myStone &&board[i+1][j+1] == myStone && board[i+2][j+2] != myStone ){
					if(board[i-3][j-3] == yourStone && board[i+2][j+2] == yourStone){
						score -= 1000;	
					}else if (board[i-3][j-3] == yourStone || board[i+2][j+2] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				else if(board[i-3][j-3] != myStone && board[i-2][j-2] == myStone && board[i-1][j-1] == myStone &&board[i][j] == 0 &&board[i+1][j+1] == myStone && board[i+2][j+2] != myStone ){
					if(board[i-3][j-3] == yourStone && board[i+2][j+2] == yourStone){
						score -= 1000;	
					}else if (board[i-3][j-3] == yourStone || board[i+2][j+2] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				//2��
				else if(board[i-2][j-2] != myStone && board[i-1][j-1] == myStone &&board[i][j] == myStone &&board[i+1][j+1] != myStone){
					if(board[i-2][j-2] == yourStone && board[i+1][j+1] == yourStone){
						score -= 100000;
					}else if(board[i-2][j-2] == yourStone || board[i+1][j+1] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(board[i-2][j-2] != myStone && board[i-1][j-1] == 0 &&board[i][j] == myStone &&board[i+1][j+1] == myStone && board[i+2][j+2] != myStone){
					if(board[i-2][j-2] == yourStone && board[i+2][j+2] == yourStone){
						score -= 100000;
					}else if(board[i-2][j-2] == yourStone || board[i+2][j+2] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(board[i-2][j-2] != myStone && board[i-1][j-1] == myStone &&board[i][j] == 0 &&board[i+1][j+1] == 0 && board[i+2][j+2] == myStone&& board[i+3][j+3] != myStone){
					if(board[i-2][j-2] == yourStone && board[i+3][j+3] == yourStone){
						score -= 100000;
					}else if(board[i-2][j-2] == yourStone || board[i+3][j+3] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}
				//��� ����
				//5�� ��� ���� 
				if(board[i-2][j-2] == myStone && board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(board[i-2][j-2] == yourStone && board[i-1][j-1] == myStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(board[i-2][j-2] == yourStone && board[i-1][j-1] == yourStone &&board[i][j] == myStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(board[i-2][j-2] == yourStone && board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(board[i-2][j-2] == yourStone && board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == myStone ){
					score += 70000;
				}
				//4�� ��� ����
				else if(board[i-1][j-1] == myStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == myStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == myStone ){
					score += 30000;
				}
				//3�� ��� 
				else if(board[i-1][j-1] == myStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone){
					score += 15000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == myStone &&board[i+1][j+1] == yourStone){
					score += 15000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == myStone){
					score += 15000;
				}
				//2�� ���
				else if(board[i-1][j-1] == myStone &&board[i][j] == yourStone){
					score += 5000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == myStone){
					score += 5000;
				}
				//1�� ��
				else if(board[i][j] == myStone){
					if(board[i+1][j+1] == yourStone || board[i-1][j-1] == yourStone){
						score += 10;
					}
				}
			}
		}
		
		for (int i = 4; i < Main.SIZE-4; i++) {
			for (int j = 4; j < Main.SIZE-4; j++) {
				//���� ����.
				//5�� 5���� �����ε�
				if(boardT[i-3][j] == myStone && boardT[i-2][j] == myStone &&boardT[i-1][j] == myStone &&boardT[i][j] == myStone && boardT[i+1][j] == myStone){
					//�糡�� ������ ��� ,6���̶�� 
					if(boardT[i-4][j] == myStone || boardT[i+2][j] == myStone ){
						score -= 1000;
					}
					//6���� �ƴ϶�� �¸��� . 
					else {
					score += 1000000;
					}
				}
				//4�� 4���� ������ ���
				else if(boardT[i-3][j] != myStone && boardT[i-2][j] == myStone && boardT[i-1][j] == myStone &&boardT[i][j] == myStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] != myStone){
					//�糡�� ��뵹 -> ������ 
					if(boardT[i-3][j] == yourStone && boardT[i+2][j] == yourStone){
						score -= 1000;
					}
					//�ϳ��� ��뵹 -> ���� ����.
					else if(boardT[i-3][j] == yourStone || boardT[i+2][j] == yourStone){
						score += 10000;
					}
					//�� �Ը� �� . ���� 5�� ���� ���� ���ٸ�  ���� 2����. 
					else{
						score += 500000;
					}
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(boardT[i-3][j] == myStone && boardT[i-2][j] == 0 &&boardT[i-1][j] == myStone &&boardT[i][j] == myStone && boardT[i+1][j] == myStone && boardT[i-4][j] != myStone && boardT[i+2][j] !=myStone){
					//�糡�� �� ���� �ƴ� . -> 6�� ����Ǽ� ����
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(boardT[i-3][j] == myStone && boardT[i-2][j] == myStone &&boardT[i-1][j] == 0 &&boardT[i][j] == myStone && boardT[i+1][j] == myStone&& boardT[i-4][j] != myStone && boardT[i+2][j] !=myStone){
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(boardT[i-3][j] == myStone && boardT[i-2][j] == myStone &&boardT[i-1][j] == myStone &&boardT[i][j] == 0 && boardT[i+1][j] == myStone&& boardT[i-4][j] != myStone && boardT[i+2][j] !=myStone){
					score += 10000;
				}
				//3�� 3���� ������ ���.
				else if(boardT[i-2][j] != myStone && boardT[i-1][j] == myStone && boardT[i][j] == myStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] != myStone ){
					//��뵹�� ������ ���. ��� ������ �����ִ� ���
					if(boardT[i-2][j] == yourStone && boardT[i+2][j] == yourStone){
						score -= 1000;	
					}else if(boardT[i-3][j] == yourStone && boardT[i+3][j] == yourStone){
						score -= 1000;	
					}else if(boardT[i-2][j] == yourStone || boardT[i+2][j] == yourStone){
						score -= 1000;	
					}else {
						score += 5000;
					}
				}
				//3�� 3���� ������ ���.
				else if(boardT[i-3][j] != myStone && boardT[i-2][j] == myStone && boardT[i-1][j] == 0 &&boardT[i][j] == myStone &&boardT[i+1][j] == myStone && boardT[i+2][j] != myStone ){
					if(boardT[i-3][j] == yourStone && boardT[i+2][j] == yourStone){
						score -= 1000;	
					}else if (boardT[i-3][j] == yourStone || boardT[i+2][j] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				else if(boardT[i-3][j] != myStone && boardT[i-2][j] == myStone && boardT[i-1][j] == myStone &&boardT[i][j] == 0 &&boardT[i+1][j] == myStone && boardT[i+2][j] != myStone ){
					if(boardT[i-3][j] == yourStone && boardT[i+2][j] == yourStone){
						score -= 1000;	
					}else if (boardT[i-3][j] == yourStone || boardT[i+2][j] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				//2��
				else if(boardT[i-2][j] != myStone && boardT[i-1][j] == myStone &&boardT[i][j] == myStone &&boardT[i+1][j] != myStone){
					if(boardT[i-2][j] == yourStone && boardT[i+1][j] == yourStone){
						score -= 100000;
					}else if(boardT[i-2][j] == yourStone || boardT[i+1][j] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(boardT[i-2][j] != myStone && boardT[i-1][j] == 0 &&boardT[i][j] == myStone &&boardT[i+1][j] == myStone && boardT[i+2][j] != myStone){
					if(boardT[i-2][j] == yourStone && boardT[i+2][j] == yourStone){
						score -= 100000;
					}else if(boardT[i-2][j] == yourStone || boardT[i+2][j] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(boardT[i-2][j] != myStone && boardT[i-1][j] == myStone &&boardT[i][j] == 0 &&boardT[i+1][j] == 0 && boardT[i+2][j] == myStone&& boardT[i+3][j] != myStone){
					if(boardT[i-2][j] == yourStone && boardT[i+3][j] == yourStone){
						score -= 100000;
					}else if(boardT[i-2][j] == yourStone || boardT[i+3][j] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}
				//��� ����
				//5�� ��� ���� 
				if(boardT[i-2][j] == myStone && boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 70000;
				}else if(boardT[i-2][j] == yourStone && boardT[i-1][j] == myStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 70000;
				}else if(boardT[i-2][j] == yourStone && boardT[i-1][j] == yourStone &&boardT[i][j] == myStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 70000;
				}else if(boardT[i-2][j] == yourStone && boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] == yourStone ){
					score += 70000;
				}else if(boardT[i-2][j] == yourStone && boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == myStone ){
					score += 70000;
				}
				//4�� ��� ����
				else if(boardT[i-1][j] == myStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 30000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == myStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 30000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] == yourStone ){
					score += 30000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == myStone ){
					score += 30000;
				}
				//3�� ��� 
				else if(boardT[i-1][j] == myStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone){
					score += 15000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == myStone &&boardT[i+1][j] == yourStone){
					score += 15000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == myStone){
					score += 15000;
				}
				//2�� ���
				else if(boardT[i-1][j] == myStone &&boardT[i][j] == yourStone){
					score += 5000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == myStone){
					score += 5000;
				}
				//1�� ��
				else if(boardT[i][j] == myStone){
					if(boardT[i+1][j] == yourStone || boardT[i-1][j] == yourStone){
						score += 10;
					}
				}
			}
		}
		for (int i = 4; i < Main.SIZE-4; i++) {
			for (int j = 4; j < Main.SIZE-4; j++) {
				if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == myStone &&boardM[i-1][j-1] == myStone &&boardM[i][j] == myStone && boardM[i+1][j+1] == myStone){
					//�糡�� ������ ��� ,6���̶�� 
					if(boardM[i-4][j-4] == myStone || boardM[i+2][j+2] == myStone ){
						score -= 1000;
					}
					//6���� �ƴ϶�� �¸��� . 
					else {
					score += 1000000;
					}
				}
				//4�� 4���� ������ ���
				else if(boardM[i-3][j-3] != myStone && boardM[i-2][j-2] == myStone && boardM[i-1][j-1] == myStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] != myStone){
					//�糡�� ��뵹 -> ������ 
					if(boardM[i-3][j-3] == yourStone && boardM[i+2][j+2] == yourStone){
						score -= 1000;
					}
					//�ϳ��� ��뵹 -> ���� ����.
					else if(boardM[i-3][j-3] == yourStone || boardM[i+2][j+2] == yourStone){
						score += 10000;
					}
					//�� �Ը� �� . ���� 5�� ���� ���� ���ٸ�  ���� 2����. 
					else{
						score += 500000;
					}
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == 0 &&boardM[i-1][j-1] == myStone &&boardM[i][j] == myStone && boardM[i+1][j+1] == myStone && boardM[i-4][j-4] != myStone && boardM[i+2][j+2] !=myStone){
					//�糡�� �� ���� �ƴ� . -> 6�� ����Ǽ� ����
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == myStone &&boardM[i-1][j-1] == 0 &&boardM[i][j] == myStone && boardM[i+1][j+1] == myStone&& boardM[i-4][j-4] != myStone && boardM[i+2][j+2] !=myStone){
					score += 10000;
				}
				//4�� 4���� ������ �ƴ� ��� -> ��ĭ �� 4�� ���� .  
				else if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == myStone &&boardM[i-1][j-1] == myStone &&boardM[i][j] == 0 && boardM[i+1][j+1] == myStone&& boardM[i-4][j-4] != myStone && boardM[i+2][j+2] !=myStone){
					score += 10000;
				}
				//3�� 3���� ������ ���.
				else if(boardM[i-2][j-2] != myStone && boardM[i-1][j-1] == myStone && boardM[i][j] == myStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] != myStone ){
					//��뵹�� ������ ���. ��� ������ �����ִ� ���
					if(boardM[i-2][j-2] == yourStone && boardM[i+2][j+2] == yourStone){
						score -= 1000;	
					}else if(boardM[i-3][j-3] == yourStone && boardM[i+3][j+3] == yourStone){
						score -= 1000;	
					}else if(boardM[i-2][j-2] == yourStone || boardM[i+2][j+2] == yourStone){
						score -= 1000;	
					}else {
						score += 5000;
					}
				}
				//3�� 3���� ������ ���.
				else if(boardM[i-3][j-3] != myStone && boardM[i-2][j-2] == myStone && boardM[i-1][j-1] == 0 &&boardM[i][j] == myStone &&boardM[i+1][j+1] == myStone && boardM[i+2][j+2] != myStone ){
					if(boardM[i-3][j-3] == yourStone && boardM[i+2][j+2] == yourStone){
						score -= 1000;	
					}else if (boardM[i-3][j-3] == yourStone || boardM[i+2][j+2] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				else if(boardM[i-3][j-3] != myStone && boardM[i-2][j-2] == myStone && boardM[i-1][j-1] == myStone &&boardM[i][j] == 0 &&boardM[i+1][j+1] == myStone && boardM[i+2][j+2] != myStone ){
					if(boardM[i-3][j-3] == yourStone && boardM[i+2][j+2] == yourStone){
						score -= 1000;	
					}else if (boardM[i-3][j-3] == yourStone || boardM[i+2][j+2] == yourStone){
						score -= 1000;
					}else {
						score += 1000;
					}
				}
				//2��
				else if(boardM[i-2][j-2] != myStone && boardM[i-1][j-1] == myStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] != myStone){
					if(boardM[i-2][j-2] == yourStone && boardM[i+1][j+1] == yourStone){
						score -= 100000;
					}else if(boardM[i-2][j-2] == yourStone || boardM[i+1][j+1] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(boardM[i-2][j-2] != myStone && boardM[i-1][j-1] == 0 &&boardM[i][j] == myStone &&boardM[i+1][j+1] == myStone && boardM[i+2][j+2] != myStone){
					if(boardM[i-2][j-2] == yourStone && boardM[i+2][j+2] == yourStone){
						score -= 100000;
					}else if(boardM[i-2][j-2] == yourStone || boardM[i+2][j+2] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}else if(boardM[i-2][j-2] != myStone && boardM[i-1][j-1] == myStone &&boardM[i][j] == 0 &&boardM[i+1][j+1] == 0 && boardM[i+2][j+2] == myStone&& boardM[i+3][j+3] != myStone){
					if(boardM[i-2][j-2] == yourStone && boardM[i+3][j+3] == yourStone){
						score -= 100000;
					}else if(boardM[i-2][j-2] == yourStone || boardM[i+3][j+3] == yourStone){
						score -= 10000;
					}else{
						score+=1000;
					}
				}
				//��� ����
				//5�� ��� ���� 
				if(boardM[i-2][j-2] == myStone && boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(boardM[i-2][j-2] == yourStone && boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(boardM[i-2][j-2] == yourStone && boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(boardM[i-2][j-2] == yourStone && boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] == yourStone ){
					score += 70000;
				}else if(boardM[i-2][j-2] == yourStone && boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == myStone ){
					score += 70000;
				}
				//4�� ��� ����
				else if(boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == myStone ){
					score += 30000;
				}
				//3�� ��� 
				else if(boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone){
					score += 15000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == yourStone){
					score += 15000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == myStone){
					score += 15000;
				}
				//2�� ���
				else if(boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone){
					score += 5000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone){
					score += 5000;
				}
				//1�� ��
				else if(boardM[i][j] == myStone){
					if(boardM[i+1][j+1] == yourStone || boardM[i-1][j-1] == yourStone){
						score += 10;
					}
				}
			}		
		}

		return score;

	}

	public int Minimax(Node node, int depth, int player) {
		if (depth == 0) {
			node.setBestValue(Heuristic(node.getCurrentBoard(), Main.WHITE, Main.BLACK));
			return node.getBestValue();
		}
		//��ǻ���� ���� . max ���� ���� �;� �Ѵ�. 
		if (player == 2) {
			for (int i = 4; i < Main.SIZE-4; i++) {
				for (int j = 4; j < Main.SIZE-4; j++) {
					if (node.getCurrentBoard()[i][j] == 0) {
						Node childnode = new Node(node.getCurrentBoard());
						childnode.getCurrentBoard()[i][j] = (short) Main.WHITE;
						childnode.setXmove(i);
						childnode.setYmove(j);
						node.getChild().add(childnode);
					} else {
						continue;
					}
				}
			}
			int bestValue = -999999;
			for (int i = 0; i < node.getChild().size(); i++) {
				int bv = Minimax(node.getChild().get(i), depth - 1, 1);

				if (bestValue < bv) {
					bestValue = Math.max(bestValue, bv);
					node.setXmove(node.getChild().get(i).getXmove());
					node.setYmove(node.getChild().get(i).getYmove());
				}
//				System.out.println(node.getChild().get(i).getBestValue() + " " + node.getChild().get(i).getXmove() + " "
//						+ node.getChild().get(i).getYmove());
			}
			return bestValue;
		} 
		else if (player == 1) {
			for (int i = 4; i < Main.SIZE-4; i++) {
				for (int j = 4; j < Main.SIZE-4; j++) {
					if (node.getCurrentBoard()[i][j] == 0) {
						Node childnode = new Node(node.getCurrentBoard());
						childnode.getCurrentBoard()[i][j] = (short) Main.BLACK;
						childnode.setXmove(i);
						childnode.setYmove(j);
						node.getChild().add(childnode);
					} else {
						continue;
					}

				}
			}
			int bestValue = 999999;
			for (int i = 0; i < node.getChild().size(); i++) {
				int bv = Minimax(node.getChild().get(i), depth - 1, 2);
				bestValue = Math.min(bestValue, bv);
			}
			return bestValue;
		}
		return 0;
	}

}