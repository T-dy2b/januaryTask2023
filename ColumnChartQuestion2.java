package january_assignment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/** 問題２ */
public class ColumnChartQuestion2 {


	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能
			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス
			ColumnChartQuestion2 cc = new ColumnChartQuestion2(); // インスタンス	
			Comp comp = cc.new Comp(); // コンパレーターインスタンス

			// 要素数入力受付・規制処理
			int arrayElements = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION1, Constant.MESSAGE1, Constant.QUESTION1,
					Constant.DEFAULT_ELEMENTS, Constant.REGEX_MATCH, sc));

			// 文字列入力受付・規制処理
			String chartString = ccq1.getRequiredInformation(Constant.REGULATION2, Constant.MESSAGE2, Constant.QUESTION2,
					Constant.DEFAULT_STRING, Constant.REGEX_MISMATCH, sc);

			// 縦軸目盛り単位受付・規制処理
			double verticalAxisScaleUnit = Double.parseDouble(ccq1.getRequiredInformation(Constant.REGULATION3, Constant.MESSAGE3,
					Constant.QUESTION3, Constant.DEFAULT_VERTICAL_AXIS_SCALE_UNIT, Constant.REGEX_MATCH, sc));

			// 乱数用に二次元配列初期設定
			Double[][] randomNumberArray = new Double[arrayElements][2];

			// 乱数を作成して二次元配列へ順に番号セットする処理
			randomNumberArray = cc.getRandomNumberArray(randomNumberArray, arrayElements);

			// ソート種類選択受付
			int selectSort = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION4, Constant.MESSAGE4, Constant.QUESTION4, Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));
			
			// ソート設定
			comp.setOrder(selectSort);

			//Arraysクラスのsort()を実行する
			Arrays.sort(randomNumberArray, comp);

			// グラフ作成処理
			cc.getCreateGraphing(verticalAxisScaleUnit, arrayElements,
					randomNumberArray, chartString);

			// コンソール入力を終了する
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * グラフ描画表示取得
	 * @param verticalAxisScaleUnit  目盛りの単位
	 * @param arrayElements  要素数
	 * @param randomNumberArray  乱数二次元配列
	 * @param chartString  グラフ設定文字
	 * */
	public void getCreateGraphing(double verticalAxisScaleUnit, int arrayElements, Double[][] randomNumberArray,
			String chartString) throws Exception {

		// 限界値が10なのでその分から順に縦軸目盛り分for分を回していく
		for (double doubleHorizontalAxis = Constant.DEFAULT_HORIZONTAL_AXIS; 0 < doubleHorizontalAxis; doubleHorizontalAxis -= verticalAxisScaleUnit) {

			// 先に計算
			double horizontalVertical = (doubleHorizontalAxis - verticalAxisScaleUnit + 0.1);

			// ここで横軸移動時スペースか＊の分岐
			for (int intVerticalAxis = 0; intVerticalAxis < arrayElements; intVerticalAxis++) {

				// 10引く縦軸目盛り+0.1(0と0.1差別化)が乱数の二次元配列の数値より大きい場合＊描画
			if(horizontalVertical <= randomNumberArray[intVerticalAxis][1]) {
					System.out.printf("%2s", chartString);

				// 当てはまらない場合はスペースを描画していく。
				} else {
					System.out.printf("%3s", " ");
				}

				// 要素移動時にもスペースを描画
				System.out.printf("%2s", " ");
			}

			// 縦軸移動
			System.out.println();
		}

		// グラフ描画後の仕切り線
		for (int i = 0; i < arrayElements; i++) {
			System.out.print("-----");
		}

		System.out.println();

		//要素数分横軸にNo描画
		for (int i = 0; i < arrayElements; i++) {
			System.out.printf("%3d  ", Math.round(randomNumberArray[i][0]));
		}
		System.out.println("\n");
	}

	/**
	 * 乱数配列取得
	 * @param randomNumberArray：乱数二次元配列
	 * @param arrayElements：要素数
	 * @return randomNumberArray 乱数二次元配列
	 * @apiNote 乱数二次元配列に番号、乱数セット処理
	 * */
	public Double[][] getRandomNumberArray(Double[][] randomNumberArray, int arrayElements) throws Exception {

		// 乱数を作成して二次元配列へ順に番号セットで格納していく。
		for (int i = 0; i < arrayElements; i++) {

			// ここでNoを入れていく。
			randomNumberArray[i][0] = (double) (i + 1);

			// Math.randomで0.0から9.9が取れるので10倍して小数点以下切り捨てしてから割ることで小数点第一位までを変数に格納
			randomNumberArray[i][1] = Math.floor(Math.random() * 100) / 10;
		}
		return randomNumberArray;
	}

	/**
	 * コンパレーター処理
	 * */
	public class Comp implements Comparator<Object> {

		private int sortOrder = 0; // ソート選択
		private int result = 0; // 結果用

		// どのソートをするか受け取る
		/** ソートのオーダーを格納
		 * @param sortNumber ソートオーダー番号
		 * */
		void setOrder(int sortNumber) {
			sortOrder = sortNumber;
		}

		@Override
		public int compare(Object row2, Object row1) {
			Double[] doubleA = (Double[]) row1;
			Double[] doubleB = (Double[]) row2;

			// ここでは比較結果のみを返す形で問題ない。
			// コンペア側が並べ替えてくれるのでここでは１か０か－１かを渡すだけ。
			// １ならAが上に０ならそのままー１ならAが下に行く形になる。
			double compareA = doubleA[1];
			double compareB = doubleB[1];
			// 1なら昇順
			if (sortOrder == 2) {
				result = Double.compare(compareA, compareB);
				// 2なら降順
			} else if (sortOrder == 3) {
				result = Double.compare(compareB, compareA);
			}
			// それ以外はそのままNo順になる
			return result;
		}
	}
}
