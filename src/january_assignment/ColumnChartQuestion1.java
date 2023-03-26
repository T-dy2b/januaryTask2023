package january_assignment;

import java.util.Scanner;

/** 問題１*/
public class ColumnChartQuestion1 {


	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in); // スキャナー機能
			ColumnChartQuestion1 cc = new ColumnChartQuestion1(); // インスタンス

			// 要素数入力受付・規制処理
			int arrayElements = Integer.parseInt(cc.getRequiredInformation(Constant.REGULATION1, Constant.MESSAGE1, Constant.QUESTION1,
					Constant.DEFAULT_ELEMENTS, Constant.REGEX_MATCH, sc));

			// 文字列入力受付・規制処理
			String chartString = cc.getRequiredInformation(Constant.REGULATION2, Constant.MESSAGE2, Constant.QUESTION2,
					Constant.DEFAULT_STRING, Constant.REGEX_MISMATCH, sc);

			// 縦軸目盛り単位受付・規制処理
			double verticalAxisScaleUnit = Double.parseDouble(cc.getRequiredInformation(Constant.REGULATION3, Constant.MESSAGE3,
					Constant.QUESTION3, Constant.DEFAULT_VERTICAL_AXIS_SCALE_UNIT, Constant.REGEX_MATCH, sc));
			
			// 乱数用に二次元配列初期設定
			Double[][] randomNumberArray = new Double[arrayElements][2];

			// 乱数を作成して二次元配列へ順に番号セットする処理
			randomNumberArray = cc.getRandomNumberArray(randomNumberArray, arrayElements);

			// グラフ作成処理
			cc.getCreateGraphing(verticalAxisScaleUnit, arrayElements, randomNumberArray,
					chartString);

			// コンソール入力を終了する
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/**
	 * 入力受付判定結果取得
	 * @param regulation 入力規制
	 * @param outOfRegulationMessage 入力規制外の時に表示するメッセージ
	 * @param question 入力規制外の場合表示するコメント
	 * @param defaultValue デフォルト値の有無
	 * @param isMatchRule 一致または不一致どちらでどう処理するかを選択
	 * @param sc スキャナー機能
	 * @return result 入力判定結果
	 * 
	 * */
	public String getRequiredInformation(String regulation, String outOfRegulationMessage, String question,
			Object defaultValue, boolean isMatchRule, Scanner sc) throws Exception {
		String userInput = "";

		System.out.printf(question, defaultValue);
		while ("".equals(userInput)) {
			// 入力処理
			userInput = getInputAcceptance(defaultValue, sc);
			
			// 判定処理
			boolean isInputResult = getInputJudgment(regulation, userInput, isMatchRule);
			
			// 規制外の時の処理
			if (!isInputResult) {
				System.out.println(outOfRegulationMessage);
				userInput = "";
			}
		}

		return userInput;
	}

	/** 入力情報取得
	 * @param defaultValue デフォルト値
	 * @param  sc スキャナー機能
	 * @return userInput ユーザーの入力
	 * */
	public String getInputAcceptance(Object defaultValue, Scanner sc) throws Exception {
		
		// 入力受付
		String userInput = sc.nextLine();
		if ("".equals(userInput)) {
			// デフォルト値がある場合はセットする
			if (!"".equals(defaultValue)) {
				// 定数クラスからデフォルト値を受け取る
				return defaultValue.toString();
			}
		}
		return userInput;
	}

	/** 入力情報判定結果取得
	 * @param	regulation 入力規制
	 * @param	userInput ユーザーの入力
	 * @param	isMatchRule 一致または不一致どちらでどう処理するかを選択
	 * @return checkResult 正規表現チェック結果
	 * */
	public boolean getInputJudgment(String regulation, String userInput, boolean isMatchRule) throws Exception {
		boolean checkResult = false;

		// 入力規制処理
		// 入力規制 trueなら正規表現一致でマッチ、falseなら正規表現不一致でマッチ
		if (!isMatchRule) {
			
			// 規制不一致の処理
			checkResult = !userInput.matches(regulation);
		} else {
			// 規制一致の処理
			checkResult = userInput.matches(regulation);
		}
		
		return checkResult;
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

				// 10引く縦軸目盛り+0.1(0と0.1差別化)が乱数の二次元配列の数値が大きい場合＊描画
				if (horizontalVertical <= randomNumberArray[intVerticalAxis][1]) {
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
	 * @param randomNumberArray	乱数二次元配列
	 * @param arrayElements 要素数
	 * @return randomNumberArray
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
	
}
