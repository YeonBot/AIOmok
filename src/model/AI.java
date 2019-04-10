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
				//공격 점수.
				//5목 5개가 연속인데
				if(board[i-3][j] == myStone && board[i-2][j] == myStone &&board[i-1][j] == myStone &&board[i][j] == myStone && board[i+1][j] == myStone){
					//양끝이 내돌이 라면 ,6목이라면 
					if(board[i-4][j] == myStone || board[i+2][j] == myStone ){
						score -= 1000;
					}
					//6목이 아니라면 승리로 . 
					else {
					score += 1000000;
					}
				}
				//4목 4개가 연속인 경우
				else if(board[i-3][j] != myStone && board[i-2][j] == myStone && board[i-1][j] == myStone &&board[i][j] == myStone &&board[i+1][j] == myStone &&board[i+2][j] != myStone){
					//양끝이 상대돌 -> 막힌돌 
					if(board[i-3][j] == yourStone && board[i+2][j] == yourStone){
						score -= 1000;
					}
					//하나만 상대돌 -> 공격 가능.
					else if(board[i-3][j] == yourStone || board[i+2][j] == yourStone){
						score += 10000;
					}
					//뻥 뚤린 돌 . 상대방 5개 놔둘 곳이 없다면  공격 2순위. 
					else{
						score += 500000;
					}
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(board[i-3][j] == myStone && board[i-2][j] == 0 &&board[i-1][j] == myStone &&board[i][j] == myStone && board[i+1][j] == myStone && board[i-4][j] != myStone && board[i+2][j] !=myStone){
					//양끝이 내 돌이 아닌 . -> 6목 경우의수 제외
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(board[i-3][j] == myStone && board[i-2][j] == myStone &&board[i-1][j] == 0 &&board[i][j] == myStone && board[i+1][j] == myStone&& board[i-4][j] != myStone && board[i+2][j] !=myStone){
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(board[i-3][j] == myStone && board[i-2][j] == myStone &&board[i-1][j] == myStone &&board[i][j] == 0 && board[i+1][j] == myStone&& board[i-4][j] != myStone && board[i+2][j] !=myStone){
					score += 10000;
				}
				//3목 3개가 연속인 경우.
				else if(board[i-2][j] != myStone && board[i-1][j] == myStone && board[i][j] == myStone &&board[i+1][j] == myStone &&board[i+2][j] != myStone ){
					//상대돌로 감싸진 경우. 모두 공격이 막혀있는 경우
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
				//3목 3개가 떨어진 경우.
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
				//2목
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
				//방어 점수
				//5목 방어 성공 
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
				//4목 방어 성공
				else if(board[i-1][j] == myStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 30000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == myStone &&board[i+1][j] == yourStone &&board[i+2][j] == yourStone ){
					score += 30000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == myStone &&board[i+2][j] == yourStone ){
					score += 30000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone &&board[i+2][j] == myStone ){
					score += 30000;
				}
				//3목 방어 
				else if(board[i-1][j] == myStone &&board[i][j] == yourStone &&board[i+1][j] == yourStone){
					score += 15000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == myStone &&board[i+1][j] == yourStone){
					score += 15000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == yourStone &&board[i+1][j] == myStone){
					score += 15000;
				}
				//2목 방어
				else if(board[i-1][j] == myStone &&board[i][j] == yourStone){
					score += 5000;
				}else if(board[i-1][j] == yourStone &&board[i][j] == myStone){
					score += 5000;
				}
				//1목 옆
				else if(board[i][j] == myStone){
					if(board[i+1][j] == yourStone || board[i-1][j] == yourStone){
						score += 10;
					}
				}
				
				//대각선////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//공격 점수.
				//5목 5개가 연속인데
				if(board[i-3][j-3] == myStone && board[i-2][j-2] == myStone &&board[i-1][j-1] == myStone &&board[i][j] == myStone && board[i+1][j+1] == myStone){
					//양끝이 내돌이 라면 ,6목이라면 
					if(board[i-4][j-4] == myStone || board[i+2][j+2] == myStone ){
						score -= 1000;
					}
					//6목이 아니라면 승리로 . 
					else {
					score += 1000000;
					}
				}
				//4목 4개가 연속인 경우
				else if(board[i-3][j-3] != myStone && board[i-2][j-2] == myStone && board[i-1][j-1] == myStone &&board[i][j] == myStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] != myStone){
					//양끝이 상대돌 -> 막힌돌 
					if(board[i-3][j-3] == yourStone && board[i+2][j+2] == yourStone){
						score -= 1000;
					}
					//하나만 상대돌 -> 공격 가능.
					else if(board[i-3][j-3] == yourStone || board[i+2][j+2] == yourStone){
						score += 10000;
					}
					//뻥 뚤린 돌 . 상대방 5개 놔둘 곳이 없다면  공격 2순위. 
					else{
						score += 500000;
					}
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(board[i-3][j-3] == myStone && board[i-2][j-2] == 0 &&board[i-1][j-1] == myStone &&board[i][j] == myStone && board[i+1][j+1] == myStone && board[i-4][j-4] != myStone && board[i+2][j+2] !=myStone){
					//양끝이 내 돌이 아닌 . -> 6목 경우의수 제외
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(board[i-3][j-3] == myStone && board[i-2][j-2] == myStone &&board[i-1][j-1] == 0 &&board[i][j] == myStone && board[i+1][j+1] == myStone&& board[i-4][j-4] != myStone && board[i+2][j+2] !=myStone){
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(board[i-3][j-3] == myStone && board[i-2][j-2] == myStone &&board[i-1][j-1] == myStone &&board[i][j] == 0 && board[i+1][j+1] == myStone&& board[i-4][j-4] != myStone && board[i+2][j+2] !=myStone){
					score += 10000;
				}
				//3목 3개가 연속인 경우.
				else if(board[i-2][j-2] != myStone && board[i-1][j-1] == myStone && board[i][j] == myStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] != myStone ){
					//상대돌로 감싸진 경우. 모두 공격이 막혀있는 경우
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
				//3목 3개가 떨어진 경우.
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
				//2목
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
				//방어 점수
				//5목 방어 성공 
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
				//4목 방어 성공
				else if(board[i-1][j-1] == myStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == myStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == myStone &&board[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone &&board[i+2][j+2] == myStone ){
					score += 30000;
				}
				//3목 방어 
				else if(board[i-1][j-1] == myStone &&board[i][j] == yourStone &&board[i+1][j+1] == yourStone){
					score += 15000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == myStone &&board[i+1][j+1] == yourStone){
					score += 15000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == yourStone &&board[i+1][j+1] == myStone){
					score += 15000;
				}
				//2목 방어
				else if(board[i-1][j-1] == myStone &&board[i][j] == yourStone){
					score += 5000;
				}else if(board[i-1][j-1] == yourStone &&board[i][j] == myStone){
					score += 5000;
				}
				//1목 옆
				else if(board[i][j] == myStone){
					if(board[i+1][j+1] == yourStone || board[i-1][j-1] == yourStone){
						score += 10;
					}
				}
			}
		}
		
		for (int i = 4; i < Main.SIZE-4; i++) {
			for (int j = 4; j < Main.SIZE-4; j++) {
				//공격 점수.
				//5목 5개가 연속인데
				if(boardT[i-3][j] == myStone && boardT[i-2][j] == myStone &&boardT[i-1][j] == myStone &&boardT[i][j] == myStone && boardT[i+1][j] == myStone){
					//양끝이 내돌이 라면 ,6목이라면 
					if(boardT[i-4][j] == myStone || boardT[i+2][j] == myStone ){
						score -= 1000;
					}
					//6목이 아니라면 승리로 . 
					else {
					score += 1000000;
					}
				}
				//4목 4개가 연속인 경우
				else if(boardT[i-3][j] != myStone && boardT[i-2][j] == myStone && boardT[i-1][j] == myStone &&boardT[i][j] == myStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] != myStone){
					//양끝이 상대돌 -> 막힌돌 
					if(boardT[i-3][j] == yourStone && boardT[i+2][j] == yourStone){
						score -= 1000;
					}
					//하나만 상대돌 -> 공격 가능.
					else if(boardT[i-3][j] == yourStone || boardT[i+2][j] == yourStone){
						score += 10000;
					}
					//뻥 뚤린 돌 . 상대방 5개 놔둘 곳이 없다면  공격 2순위. 
					else{
						score += 500000;
					}
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(boardT[i-3][j] == myStone && boardT[i-2][j] == 0 &&boardT[i-1][j] == myStone &&boardT[i][j] == myStone && boardT[i+1][j] == myStone && boardT[i-4][j] != myStone && boardT[i+2][j] !=myStone){
					//양끝이 내 돌이 아닌 . -> 6목 경우의수 제외
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(boardT[i-3][j] == myStone && boardT[i-2][j] == myStone &&boardT[i-1][j] == 0 &&boardT[i][j] == myStone && boardT[i+1][j] == myStone&& boardT[i-4][j] != myStone && boardT[i+2][j] !=myStone){
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(boardT[i-3][j] == myStone && boardT[i-2][j] == myStone &&boardT[i-1][j] == myStone &&boardT[i][j] == 0 && boardT[i+1][j] == myStone&& boardT[i-4][j] != myStone && boardT[i+2][j] !=myStone){
					score += 10000;
				}
				//3목 3개가 연속인 경우.
				else if(boardT[i-2][j] != myStone && boardT[i-1][j] == myStone && boardT[i][j] == myStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] != myStone ){
					//상대돌로 감싸진 경우. 모두 공격이 막혀있는 경우
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
				//3목 3개가 떨어진 경우.
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
				//2목
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
				//방어 점수
				//5목 방어 성공 
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
				//4목 방어 성공
				else if(boardT[i-1][j] == myStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 30000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == myStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == yourStone ){
					score += 30000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == myStone &&boardT[i+2][j] == yourStone ){
					score += 30000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone &&boardT[i+2][j] == myStone ){
					score += 30000;
				}
				//3목 방어 
				else if(boardT[i-1][j] == myStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == yourStone){
					score += 15000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == myStone &&boardT[i+1][j] == yourStone){
					score += 15000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == yourStone &&boardT[i+1][j] == myStone){
					score += 15000;
				}
				//2목 방어
				else if(boardT[i-1][j] == myStone &&boardT[i][j] == yourStone){
					score += 5000;
				}else if(boardT[i-1][j] == yourStone &&boardT[i][j] == myStone){
					score += 5000;
				}
				//1목 옆
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
					//양끝이 내돌이 라면 ,6목이라면 
					if(boardM[i-4][j-4] == myStone || boardM[i+2][j+2] == myStone ){
						score -= 1000;
					}
					//6목이 아니라면 승리로 . 
					else {
					score += 1000000;
					}
				}
				//4목 4개가 연속인 경우
				else if(boardM[i-3][j-3] != myStone && boardM[i-2][j-2] == myStone && boardM[i-1][j-1] == myStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] != myStone){
					//양끝이 상대돌 -> 막힌돌 
					if(boardM[i-3][j-3] == yourStone && boardM[i+2][j+2] == yourStone){
						score -= 1000;
					}
					//하나만 상대돌 -> 공격 가능.
					else if(boardM[i-3][j-3] == yourStone || boardM[i+2][j+2] == yourStone){
						score += 10000;
					}
					//뻥 뚤린 돌 . 상대방 5개 놔둘 곳이 없다면  공격 2순위. 
					else{
						score += 500000;
					}
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == 0 &&boardM[i-1][j-1] == myStone &&boardM[i][j] == myStone && boardM[i+1][j+1] == myStone && boardM[i-4][j-4] != myStone && boardM[i+2][j+2] !=myStone){
					//양끝이 내 돌이 아닌 . -> 6목 경우의수 제외
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == myStone &&boardM[i-1][j-1] == 0 &&boardM[i][j] == myStone && boardM[i+1][j+1] == myStone&& boardM[i-4][j-4] != myStone && boardM[i+2][j+2] !=myStone){
					score += 10000;
				}
				//4목 4개가 연속이 아닌 경우 -> 한칸 뛴 4개 공격 .  
				else if(boardM[i-3][j-3] == myStone && boardM[i-2][j-2] == myStone &&boardM[i-1][j-1] == myStone &&boardM[i][j] == 0 && boardM[i+1][j+1] == myStone&& boardM[i-4][j-4] != myStone && boardM[i+2][j+2] !=myStone){
					score += 10000;
				}
				//3목 3개가 연속인 경우.
				else if(boardM[i-2][j-2] != myStone && boardM[i-1][j-1] == myStone && boardM[i][j] == myStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] != myStone ){
					//상대돌로 감싸진 경우. 모두 공격이 막혀있는 경우
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
				//3목 3개가 떨어진 경우.
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
				//2목
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
				//방어 점수
				//5목 방어 성공 
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
				//4목 방어 성공
				else if(boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == myStone &&boardM[i+2][j+2] == yourStone ){
					score += 30000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone &&boardM[i+2][j+2] == myStone ){
					score += 30000;
				}
				//3목 방어 
				else if(boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == yourStone){
					score += 15000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone &&boardM[i+1][j+1] == yourStone){
					score += 15000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == yourStone &&boardM[i+1][j+1] == myStone){
					score += 15000;
				}
				//2목 방어
				else if(boardM[i-1][j-1] == myStone &&boardM[i][j] == yourStone){
					score += 5000;
				}else if(boardM[i-1][j-1] == yourStone &&boardM[i][j] == myStone){
					score += 5000;
				}
				//1목 옆
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
		//컴퓨터의 차례 . max 값을 가져 와야 한다. 
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