package january_assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** 問題３ Map生成でリストに順番を入れて呼び出す */
public class TwoDimensionsArrayRandomNumberMap {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能

			TwoDimensionsArrayRandomNumberMap ta = new TwoDimensionsArrayRandomNumberMap(); // インスタンス

			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス

			// 配列サイズを受け付ける処理
			int arraySize = Integer
					.parseInt(ccq1.getRequiredInformation(Constant.REGULATION1, Constant.MESSAGE1, Constant.QUESTION5,
							Constant.DEFAULT_ARRAY, Constant.REGEX_MATCH, sc));

			// 乱数用に二次元配列初期設定		
			int[][] randomNumberArray = new int[arraySize][arraySize];

			// 乱数の生成、すべてユニークにする
			ta.getRandomNumberComparison(randomNumberArray);

			// 乱数の配列を表示する処理
			for (int row = 0; row < arraySize; row++) {
				for (int column = 0; column < arraySize; column++) {

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
	 * 乱数生成後配列順番入替結果取得
	 * @param randomNumberArray 二次元乱数配列
	 * @return randomNumberArray 二次元乱数配列
	 * */
	public int[][] getRandomNumberComparison(int[][] randomNumberArray) throws Exception {
		int arraySize = randomNumberArray.length * randomNumberArray[0].length; // 全体の箇所数
		int arrayCount = 0; // リストから抽出する番号

		// Map内キー0から値は１から全体の箇所数
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arraySize; i++) {
			map.put(i, i + 1);
		}
		
		// Mapのキーで呼ぶための値リスト生成
		ArrayList<Integer> randomArrays = new ArrayList<Integer>();
		
		// １以上最大量までの数値
		for (int i = 0; i < arraySize; i++) {
			randomArrays.add(i);
		}

		// シャッフルして、順番を変える
		Collections.shuffle(randomArrays);

		// シャッフルしてあるキーのリストから１個づつ取得していく。
		for (int i = 0; i < randomNumberArray.length; i++) {
			for (int j = 0; j < randomNumberArray[i].length; j++) {
				randomNumberArray[i][j] = map.get(randomArrays.get(arrayCount));
				arrayCount++;
			}
		}
		return randomNumberArray;
	}
}
