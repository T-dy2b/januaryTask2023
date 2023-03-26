package january_assignment;

import java.util.Scanner;

/** 問題１０ */
public class NumberPyramidDuplicate {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能
			NumberPyramidDuplicate npd = new NumberPyramidDuplicate(); // インスタンス
			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス

			// ピラミッドの段数入力受付
			int stepNumber = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION11, Constant.MESSAGE11, Constant.QUESTION11,
					Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));

			// 表示する図の量を受け付ける
			int figureQuantity = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION12, Constant.MESSAGE12, Constant.QUESTION12,
					Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));

			System.out.printf("入力した段数は%d段になります。\n", stepNumber);

			// 仕切り線追加処理
			npd.setStartingLine(stepNumber, figureQuantity);

			// ピラミッド表示処理
			npd.setVerticalScale(stepNumber, figureQuantity);

			// 仕切り線追加処理
			npd.setStartingLine(stepNumber, figureQuantity);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/** スペース生成取得
	 * @param  stepNumber 段数
	 * @param  position 行の位置
	 *  */
	public void getSpaceGeneration(int stepNumber, int position) throws Exception{
		// スペースを生成 3個 最初はiが０だからステップ数の３回スペースを生成
		// ２回目iが１だからステップー１になり二回だけスペースが生成
		int currentPosition = stepNumber - position; // 現在位置計算
		// 行引く段数分スペースを生成
		for (int i = 0; i < currentPosition; i++) {
			System.out.print(" ");
		}
	}
	
	/**
	 * ピラミッド生成結果取得
	 * @param	stepNumber ピラミッドの段数
	 * @param	figureQuantity 表示する図の量
	 * @apiNote 表示する縦横の拡張数値分縦に拡張する処理
	 * */
	public void setVerticalScale(int stepNumber, int figureQuantity) throws Exception {
		// 縦に拡張
		for (int i = 0; i < figureQuantity; i++) {
			
			// ピラミッドを数字で表示する処理
			setPyramid(stepNumber, figureQuantity);

			int lastRow = figureQuantity - 1;
			// 最後の行にスペースがいらないため
			if (i < lastRow) {
				System.out.println("");
			}
		}
	}

	/**
	 * 仕切り線取得
	 * @param stepNumber ピラミッドの段数
	 * @param figureQuantity 表示する図の量
	 * */
	public void setStartingLine(int stepNumber, int figureQuantity) throws Exception {
		for (int i = 0; i < figureQuantity; i++) {
			for (int j = 0; j < stepNumber; j++) {
					System.out.print("--");
			}
		}
		System.out.println("");
	}

	/**
	 * 行毎の結果取得
	 * @param stepNumber ピラミッドの段数
	 * @param figureQuantity 表示する図の量
	 * */
	public void setPyramid(int stepNumber, int figureQuantity) throws Exception {
		// 行のループ
		for (int row = 1; row <= stepNumber; row++) {
			
			// 行にスペースと数字その後またスペースを入れる処理
			setLineSpaceNumbers(stepNumber, figureQuantity, row);
			System.out.println("");
		}
	}

	/**
	 * 行文字列生成処理取得
	 * @param stepNumber ピラミッドの段数
	 * @param figureQuantity 表示する図の量
	 * @param areaSize 横に拡張する回数
	 * @param row 処理中の行
	 * */
	public void setLineSpaceNumbers(int stepNumber, int figureQuantity, int row) throws Exception{
		int areaSize = 0;  // 横に拡張する回数
		
		// 横に拡張する回数が表示する図の量と同じになるまで繰り返す。
		while (areaSize != figureQuantity) {

			// 行内スペース量を計算してスペースをセットする
			getSpaceGeneration(stepNumber, row);
			
			int oneDigit = row % 10; // 成果報告後に修正
			int writeVolume = 2 * row; // 書く量
			
			// 行内の処理用
			for (int writeNumber = 1; writeNumber < writeVolume; writeNumber++) {
				System.out.printf("%d", oneDigit);
			}
			
			// 行内スペース量を計算してスペースをセットする
			getSpaceGeneration(stepNumber, row);
			
			areaSize++;
			System.out.print(" ");
		}
	}

}
