package january_assignment_2023_2_07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/** 問題４ */
public class Qestion4Sudoku {

	// 問題４ [1,4,7][1,4,7]の位置がユニークな数値で尚且つ縦横がユニークな値で埋まる
	public static void main(String[] args) {
		try {
			Qestion4Sudoku qs = new Qestion4Sudoku();

			int[][] randomNumberArray = new int[9][9]; //9*9の配列を作成
			int[] uniqueArray = new int[9]; // ユニークな9点の数値保管用

			// 数独が完成したかどうかを入れる変数
			boolean isCompleted = false;

			// 全エリアがユニーク尚且つ中央が全てユニークになるまで終了しない	
			while (!isCompleted) {

				// ユニークな中心の値9個を作成する処理
				qs.getUniqueCenterNine(randomNumberArray, uniqueArray);

				// ユニークな中心値の回りをユニークな値で埋める処理
				isCompleted = qs.getSequencePerimeter(randomNumberArray, uniqueArray);
			}

			// 数独を表示する
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					System.out.printf("%2d", randomNumberArray[row][column]);
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 先頭配列要素取得
	 * @param uniqueArray [1,4,7][1,4,7]の9箇所の中心ユニーク値の配列
	 * @param arrayPosition 中心のユニーク値配列内の位置
	 * 
	 * @return perimeterArray [0]に中心の値、残りユニークの値を入れる配列
	 * @apiNote uniqueArrayから i 番目の値をperimeterArrayの[0]に入れる処理
	 * ユニーク配列から引数値番号を別配列の先頭にセットした配列を取得
	 * */
	public int[] getUniqueCenterSet(int[] uniqueArray, int arrayPosition) throws Exception {
		int[] perimeterArray = new int[9]; // 中心のユニークな値を1つ入れて周りの数値もユニークになる配列
		perimeterArray[0] = uniqueArray[arrayPosition]; // ユニーク配列から先頭中心外周配列に入れる
		return perimeterArray;
	}

	/**
	 * ユニーク乱数順番外周取得
	 * @param randomNumberArray 乱数配列
	 * @param uniqueArray [1,4,7][1,4,7]の9箇所の中心ユニーク値の配列
	 * 
	 * @return true 全て処理が終わった場合trueで返す
	 * @apiNote ユニークな中心値の回り8箇所をユニークな値で埋め終わると次の8箇所へと順に埋めていき、
	 * 途中で埋めれなくなると一旦8箇所を5回までリセットしてそれ以上になると全体をリセットして再び埋めていく形になる。
	 * */
	public boolean getSequencePerimeter(int[][] randomNumberArray, int[] uniqueArray) throws Exception {
		Random rand = new Random();
		int uniqueCount = 0; // ユニーク値個数を格納
		int horizontalAxis = 0; // 横軸管理変数
		int verticalAxis = 0; // 縦軸管理変数
		int searchHorizontalAxis = 0; // 検索横軸変数
		int backSearchHorizontalAxis = 0; // 検索横軸変数戻り量蓄積用
		int searchVerticalAxis = 0; // 検索縦軸変数
		int backSearchVerticalAxis = 0; // 検索縦軸変数戻り量蓄積用
		boolean isOnly = false; // ユニークな値が出てきたかどうか判定
		int uniqueAreaV = 1; // 縦軸ユニーク位置追跡用
		int uniqueAreaH = 1; // 横軸ユニーク位置追跡用
		boolean isThrough = false; // 3ヵ所やり終えた時のフラグ
		int resetCount = 0; // 9カ所ユニークな値が入らなかった時の回数
		int createNumber = 0; // 乱数を入れる変数
		int refresh = 0; // 全体をリフレッシュするための変数

		// ユニーク配列から1個づつ周りのユニーク数値を埋めていく処理
		for (int i = 0; i < uniqueArray.length; i++) {

			// uniqueArrayからi番目の値をperimeterArrayの[0]に入れる処理
			int[] perimeterArray = getUniqueCenterSet(uniqueArray, i);

			// 0に中心の値があるので1 perimeter_arrayに入れる値の位置を別で控える
			int remainingCount = 1;

			// 中心の値以外残り8個数値が入るまでループする。
			while (uniqueCount != 8) {

				// 9個ユニークな値が取れなかった場合中身を一旦リセットする
				if (5 < resetCount) {
					perimeterArray = getUniqueCenterSet(uniqueArray, i);
					remainingCount = 1; // 0に中心の値があるので1 perimeter_arrayに入れる値の位置を別で控える
					resetCount = 0; // カウントもリセット

					// ここで比較する列と行の値を進んだ量を一旦戻す
					searchHorizontalAxis -= backSearchHorizontalAxis;
					searchVerticalAxis -= backSearchVerticalAxis;

					// 進んだ量を管理していた変数を初期化
					backSearchHorizontalAxis = 0;
					backSearchVerticalAxis = 0;

					// 全マスをリフレッシュする変数を加算
					refresh++;

					// 全マスをリフレッシュする変数が10以上になった場合全ての値を一旦リセットする
					if (10 < refresh) {
						refresh = 0;
						for (int j = 0; j < 9; j++) {
							uniqueArray[j] = 0;
							for (int k = 0; k < 9; k++) {
								randomNumberArray[j][k] = 0;
							}
						}
						// リセットしたので一旦question_4に戻る
						return false;
					}
				}

				// １から９までの数字が入った配列を用意
				int[] randomArrays = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
				int randomNumber = randomArrays.length;

				// 配列内数値入れ替え処理
				for (int j = 0; j < randomArrays.length; j++) {

					int createArrayNumber = rand.nextInt(8); // 配列内の数字の位置をrandomで生成
					int nextNumber = randomArrays[--randomNumber]; // 乱数によって上書きされる場所の配列を控えておく。

					// 1回目random_arrays[8]にrandom_arrays[乱数]を格納する。
					randomArrays[randomNumber] = randomArrays[createArrayNumber];
					// random_arrays[乱数]の中に控えておいたnext_numberのrandom_arrays[8]を格納する。
					randomArrays[createArrayNumber] = nextNumber;
				}

				// シャッフルした配列をperimeterArray内の数値と比較、randomNumberArrayの行と列を比較する
				for (int j = 0; j < randomArrays.length; j++) {

					// 検索横軸変数か検索縦軸変数が9なら一旦前の処理に戻る
					if (searchVerticalAxis == 9 || searchHorizontalAxis == 9) {
						break;
					}
					//uniqueArrayの中身を全て確認する。
					createNumber = randomArrays[j];
					reset: for (int n = 0; n < remainingCount; n++) {

						// createNumberとperimeterArray内を比較して違えばTrueで同じ場合Falseになる
						isOnly = perimeterArray[n] != createNumber;
						if (!isOnly) {
							break reset;
						} else {

							// 行と列のユニーク検索
							for (int k = 0; k < 9; k++) {

								// 比較して違えばTrueで同じ場合Falseになる
								isOnly = randomNumberArray[k][searchHorizontalAxis] != createNumber;
								if (!isOnly) {
									resetCount++;
									break reset;
								} else {
									isOnly = randomNumberArray[searchVerticalAxis][k] != createNumber;
									if (!isOnly) {
										resetCount++;
										break reset;
									}
								}
							}
						}
					}

					// 確認した結果被りなしなら登録する。
					if (isOnly) {

						// ユニークな値と確認が出来たら中心ユニーク配列の中に入れる
						perimeterArray[remainingCount] = createNumber;

						// 中心ユニーク配列の次に代入する位置をずらす
						remainingCount++;

						// 問題ない数字が入ったのでリセットカウントを初期化
						resetCount = 0;

						// 問題ない数値が入った場合次の比較行へ移動
						searchHorizontalAxis++;

						// 同時に比較戻り用変数を加算
						backSearchHorizontalAxis++;

						// 3行目に達した時に次の列へ、行は3行戻る
						if (searchHorizontalAxis % 3 == 0) {

							// 行戻りと同時に蓄積量側も戻す
							searchHorizontalAxis -= 3;
							backSearchHorizontalAxis -= 3;

							// 列移動と同時に蓄積量側も加算する
							searchVerticalAxis++;
							backSearchVerticalAxis++;

							// ユニーク値の個数を管理してる変数が5の時はユニークな[1,4,7]の位置なので行を加算すると同時に蓄積量も加算
						} else if (remainingCount == 5) {
							searchHorizontalAxis++;
							backSearchHorizontalAxis++;
						}
					}

				}

				// ユニーク値が9個埋まった場合に二次元配列へ登録処理
				if (remainingCount == 9) {
					for (int j = 1; j < perimeterArray.length; j++) {
						// 元の二次元配列に入れる
						randomNumberArray[verticalAxis][horizontalAxis] = perimeterArray[j];

						// 次の行に移動
						horizontalAxis++;

						// ユニークの値が入ったカウント
						uniqueCount++;

						// 3行目まで到達した時に行数を3行戻して、1列ずらす
						if (horizontalAxis % 3 == 0) {
							horizontalAxis -= 3;
							verticalAxis++;

							// ユニークな中心の値がある場所に来た際は1行ずらしてスキップする。
						} else if (horizontalAxis == uniqueAreaH && verticalAxis == uniqueAreaV) {

							// ユニークな中心の値が7列目に到達した時にスキップする次の予定位置へ変更する
							if (uniqueAreaV == 7) {
								uniqueAreaH += 3; // 次のユニーク位置合わせ
								uniqueAreaV = 1; // 次のユニーク位置合わせ
								isThrough = true; // ここに入った場合次回列の加算をスルーする。uniqueAreaVを-2にすることで必要は無くなる
							}

							// 行数加算
							horizontalAxis++;
						}
					}

					// 全て登録し終わったら蓄積量変数を初期化
					backSearchHorizontalAxis = 0;
					backSearchVerticalAxis = 0;

				}
			}

			// スルーする変数がfalseなら次の中心ユニークの位置へ加算
			if (!isThrough) {
				uniqueAreaV += 3;
			}

			isThrough = false;

			// 9列目まで来たら初期化して行を3足して次の3行のエリアへ移動
			if (verticalAxis == 9) {
				horizontalAxis += 3;
				verticalAxis = 0;
			}

			// 中心の値以外残り8個数値がユニークで入った時点で初期化
			uniqueCount = 0;

			// 9列目まで来たら初期化して行を3足して次の3行のエリアへ移動
			if (searchVerticalAxis == 9) {
				searchHorizontalAxis += 3;
				searchVerticalAxis = 0;
			}
		}
		return true;
	}

	/**
	 * ユニークな中心の値9個を取得
	 * @param randomNumberArray 乱数配列
	 * @param uniqueArray [1,4,7][1,4,7]の9箇所の中心ユニーク値の配列
	 * */
	public void getUniqueCenterNine(int[][] randomNumberArray, int[] uniqueArray) throws Exception {
		int uniqueCount = 0;
		while (uniqueCount != 9) {
			// １から９の数字を入れるリストを作成
			ArrayList<Integer> randomArrays = new ArrayList<Integer>();
			
			// １から９をリストに入れる
			for (int i = 1; i <= Constant.SUDOKU_MAX_NUMBER; i++) {
				randomArrays.add(i);
			}
			
			// シャッフルして、順番を変える
			Collections.shuffle(randomArrays);
			
			// シャッフルしたリストを順に配列に挿入
			for (int uniqueRow = 1; uniqueRow < 9; uniqueRow += 3) {
				for (int uniqueColumn = 1; uniqueColumn < 9; uniqueColumn += 3) {
					uniqueArray[uniqueCount] = randomArrays.get(uniqueCount);
					randomNumberArray[uniqueColumn][uniqueRow] = randomArrays.get(uniqueCount);
					uniqueCount++;
				}
			}
		}
	}
}
