package january_assignment;

import java.util.Scanner;

/** 問題９ */
public class NumberPyramid {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能
			NumberPyramid np = new NumberPyramid(); // インスタンス
			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス

			// ピラミッドの段数入力受付
			int stepNumber = Integer.parseInt(
					ccq1.getRequiredInformation(Constant.REGULATION11, Constant.MESSAGE11, Constant.QUESTION11,
							Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));

			System.out.printf("入力した段数は%d段になります。\n", stepNumber);

			// 仕切り線追加処理
			np.setStartingLine(stepNumber);

			// ピラミッド表示処理
			np.setLineSpaceNumbers(stepNumber);

			// 仕切り線追加処理
			np.setStartingLine(stepNumber);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 仕切り線取得
	 * @param	stepNumber ピラミッドの段数
	 * */
	public void setStartingLine(int stepNumber) throws Exception {
		for (int j = 0; j < stepNumber; j++) {
			System.out.print("--");
		}
		System.out.print("-");
		System.out.println("");
	}

	/**
	 * 行毎の結果取得
	 * @param	stepNumber ピラミッドの段数
	 * */
	public void setLineSpaceNumbers(int stepNumber) throws Exception {
		// 行の処理
		for (int row = 1; row <= stepNumber; row++) {
			
			int numberOfSpaces = stepNumber - row;
			// 行内スペース量を計算してスペースをセットする
			for (int j = 0; j <= numberOfSpaces; j++) {
				System.out.print(" ");
			}

			int oneDigit = row % 10; // 成果報告後に修正
			int writeVolume = 2 * row; // 数字を書く量
			
			// 行内の処理用
			for (int writeNumber = 1; writeNumber < writeVolume; writeNumber++) {
				System.out.printf("%d", oneDigit);
			}
			System.out.println("");
		}
	}

}
