package january_assignment_2023_2_07;

import java.util.Random;
import java.util.Scanner;

import january_assignment.ColumnChartQuestion1;
import january_assignment.TwoDimensionsArrayRandomNumberMap;

/** 問題３ 全件毎度検索型 */
public class TwoDimensionsArrayRandomNumber {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能

			TwoDimensionsArrayRandomNumberMap ta = new TwoDimensionsArrayRandomNumberMap(); // インスタンス

			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス

			// 配列サイズを受け付ける処理
			int defaultArraySize = Integer
					.parseInt(ccq1.getRequiredInformation("[1-9][0-9]{0,1}", "1～99の数字を入力してください", "配列の大きさを入力してください \n例 5   5×5 になります : デフォルト5\n",
							Constant.DEFAULT_ARRAY, true, sc));

			// 乱数用に二次元配列初期設定		
			int[][] randomNumberArray = new int[defaultArraySize][defaultArraySize];

			// 乱数の生成、すべてユニークにする
			ta.getRandomNumberComparison(randomNumberArray);

			// 乱数の配列を表示する処理
			for (int row = 0; row < defaultArraySize; row++) {
				for (int column = 0; column < defaultArraySize; column++) {
					
					// Stringformatで3桁分の表示にすることでスペースなどを入れなくても整列に表示ができる。
					System.out.printf("%5d", randomNumberArray[row][column]);
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 乱数取得
	 * @param	maxRandom 乱数
	 * @return result 乱数生成結果
	 * */
	public int getRandom(int maxRandom) throws Exception {
		Random rnd = new Random();
		int result = 0;

		// 引数に＋１して乱数を作成することで受け取った値が最大値になり、０が１になる。
		result = rnd.nextInt(maxRandom) + 1;

		return result;
	}

	/**
	 * 乱数生成比較後被り無し配列取得
	 * @param randomNumberArray 二次元乱数配列
	 * @return randomNumberArray 二次元乱数配列
	 * */
	public int[][] getRandomNumberComparison(int[][] randomNumberArray) throws Exception {
		int randomNumber = 0; // 乱数を入れる変数
		boolean isOnly = false; // 被り有りかどうかを判定する
		int verticalAxis = 0; // 縦軸用変数
		int horizontalAxis = 0; // 横軸用変数
		int arraySize = randomNumberArray.length * randomNumberArray[0].length; // 全体の箇所数
		int arrayCount = arraySize; // 箇所数計算用

		while (arrayCount != 0) {
			// 乱数用に二次元配列初期設定		
			randomNumber = getRandom(arraySize); // 箇所数を最大値として乱数を作成

			// ユニーク判定
			loop: for (int intRow = 0; intRow < randomNumberArray.length; intRow++) {
				for (int intColumn = 0; intColumn < randomNumberArray[intRow].length; intColumn++) {

					if (randomNumberArray[intRow][intColumn] == randomNumber) {
						isOnly = false;
						break loop;
					} else {
						isOnly = true;
					}
				}
			}

			// ユニークであれば追加していく。
			if (isOnly) {
				randomNumberArray[verticalAxis][horizontalAxis] = randomNumber;
				horizontalAxis++;
				arrayCount--;
				if (randomNumberArray.length == horizontalAxis) {
					verticalAxis++;
					horizontalAxis = 0;
				}
				isOnly = false;
			}
		}
		return randomNumberArray;
	}
}
