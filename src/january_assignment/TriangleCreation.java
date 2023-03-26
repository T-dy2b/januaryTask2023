package january_assignment;

import java.util.Scanner;

/** 問題７ */
public class TriangleCreation {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能
			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス
			TriangleCreation tc = new TriangleCreation(); // インスタンス
			
			// 段数を受け付ける
			int steps = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION9, Constant.MESSAGE9, Constant.QUESTION9,
					Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));
			
			System.out.printf("入力された値は%d段になります\n", steps);
			
			// 仕切り線生成処理
			tc.getAsteriskOrDividingLine(steps, Constant.DIVIDING_LINE);
			
			// 段数を渡して三角形を表示する
			tc.getUpperTriangle(steps);
			
			// 仕切り線生成処理
			tc.getAsteriskOrDividingLine(steps, Constant.DIVIDING_LINE);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/** 
	 * スペースの生成結果取得
	 * @param steps 段数
	 * @param position 行の位置
	 *  */
	public void getSpaceGeneration(int steps, int position) throws Exception{
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
	 *  */
	public void getAsteriskOrDividingLine(int numberOfPrints, String printSelection) throws Exception{
		int numberOfAsterisks = numberOfPrints * 2; // アスタリスクの数
		// 行を二倍にした分アスタリスク生成
		for (int i = 0; i <= numberOfAsterisks; i++) {
			System.out.print(printSelection);
		}
		// 次のラインへ移動する。
		System.out.println("");
	}
	
	/** 
	 * 三角形取得
	 * @param steps 段数
	 */
	public void getUpperTriangle(int steps) throws Exception{
		// 段数分生成
		for (int row = 0; row < steps; row++) {

			// 前半のスペースを生成
			getSpaceGeneration(steps, row);

			// アスタリスク生成
			getAsteriskOrDividingLine(row, Constant.ASTERISK_PRINT);
		}
	}
}
