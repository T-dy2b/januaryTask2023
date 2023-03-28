package january_assignment;

import java.util.Scanner;

/** 問題８ */
public class TriangleDirectionCreation {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能
			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス
			TriangleDirectionCreation td = new TriangleDirectionCreation();
			
			// 受け付ける段数
			int steps = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION9, Constant.MESSAGE9, Constant.QUESTION9,
					Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));
			
			// 三角形の向きを受け付ける
			int direction = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION10, Constant.MESSAGE10, Constant.QUESTION10,
					Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));
			
			System.out.printf("入力された値は%d段になります\n", steps);
			
			// 仕切り線生成
			td.getSelectivePrinting(steps, Constant.DIVIDING_LINE, direction);
			
			// 向きと段数を渡して三角形を表示する
			td.getTriangleDisplay(steps, direction);
			
			// 仕切り線生成
			td.getSelectivePrinting(steps, Constant.DIVIDING_LINE, direction);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/** スペースの生成取得
	 * @param	steps 段数
	 * @param position 行の位置
	 *  */
	public void getSpaceGeneration(int steps, int position) throws Exception{
		// スペースを生成 3個 最初はiが０だからステップ数の３回スペースを生成
		// ２回目iが１だからステップー１になり二回だけスペースが生成
		int currentPosition = steps - position; // 現在位置計算
		// 行引く段数分スペースを生成
		for (int i = 0; i < currentPosition; i++) {
			System.out.print(" ");
		}
	}
	
	/** 
	 * 選択印字取得
	 * @param numberOfPrints 印字回数
	 * @param printSelection 印字する文字
	 * @param direction 向きの選択
	 *  */
	public void getSelectivePrinting(int numberOfPrints, String printSelection, int direction) throws Exception{
		if(direction < 3) {
			numberOfPrints = numberOfPrints * 2;
		}
		// 行を二倍にした分生成
		for (int i = 0; i <= numberOfPrints; i++) {
			System.out.print(printSelection);
		}
		// 次のラインへ移動する。
		System.out.println("");
	}
	
	/**
	 *  三角形表示向き取得
	 * @param steps 三角形の段数
	 * @param direction 向きの選択
	 * */
	public void getTriangleDisplay(int steps, int direction) throws Exception{
		// 4はデフォルトになる設定
		switch(direction) {
		case Constant.UPPER -> getUpperTriangle(steps, direction);
		case Constant.LOWER -> getLowerTriangle(steps, direction);
		case Constant.LEFT -> getLeftOrRightTriangle(steps, direction);
		default -> getLeftOrRightTriangle(steps, direction);
		}
	}
	
	/** 
	 * 上方向の三角形取得 
	 * @param steps 三角形の段数
	 * @param direction 向きの選択
	 * */
	public void getUpperTriangle(int steps, int direction) throws Exception{
		// 上三角形
		for (int i = 0; i < steps; i++) {

			// 前半のスペースを生成
			getSpaceGeneration(steps, i);

			// アスタリスク生成
			getSelectivePrinting(i, Constant.ASTERISK_PRINT, direction);
			
		}
	}
	
	/** 
	 * 下方向の三角形取得
	 * @param steps 三角形の段数
	 * @param direction 向きの選択
	 *  */
	public void getLowerTriangle(int steps, int direction) throws Exception{
		int row = steps - 1;

		for (int i = row; 0 <= i; i--) {
			
			// 前半のスペースを生成
			getSpaceGeneration(steps, i);

			// アスタリスク生成
			getSelectivePrinting(i, Constant.ASTERISK_PRINT, direction);
		}
	}

	/**
	 *  左右方向の三角形取得
	 * @param steps 三角形の段数
	 * @param direction 向きの選択
	 *   */
	public void getLeftOrRightTriangle(int steps, int direction) throws Exception{
		for (int i = 0; i < steps; i++) {
			
			// 左方向の場合先にスペースを入れる処理
			if(direction == 3) {
				// 前半のスペースを生成
				getSpaceGeneration(steps, i);
			}
			
			// アスタリスク生成
			getSelectivePrinting(i, Constant.ASTERISK_PRINT, direction);
		}

		for (int i = steps - 2; 0 <= i; i--) {
			
			// 左方向の場合先にスペースを入れる処理
			if(direction == 3) {
				// 前半のスペースを生成
				getSpaceGeneration(steps, i);
			}
			
			// アスタリスク生成
			getSelectivePrinting(i, Constant.ASTERISK_PRINT, direction);
		}
		
	}
}
