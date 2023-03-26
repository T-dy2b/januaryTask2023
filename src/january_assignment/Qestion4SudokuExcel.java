package january_assignment;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/** 問題４ */
public class Qestion4SudokuExcel {

	// 問題４ [1,4,7][1,4,7]の位置がユニークな数値で尚且つ列行がユニークな値で埋まる
	public static void main(String[] args) {
		try {
			// インスタンス
			Qestion4SudokuExcel qs = new Qestion4SudokuExcel();

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

			// 数独をExcelにする
			qs.getExcel(randomNumberArray);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/** 数値毎背景色取得 ただ残念なことに色を1色づつ変えるには、セルを1個づつ分離させて1個づつ色を入れないといけない。
	 * @param number 番号
	 * 
	 * @return result 背景色
	 * */
	public void getBackgroundColor(int number, CellStyle cellStyle) {
		short result = 0;
		switch(number) {
		case 1 ->  result = IndexedColors.GREY_25_PERCENT.getIndex();
		case 2 ->  result = IndexedColors.AQUA.getIndex();
		case 3 ->  result = IndexedColors.BRIGHT_GREEN.getIndex();
		case 4 ->  result = IndexedColors.CORAL.getIndex();
		case 5 ->  result = IndexedColors.LEMON_CHIFFON.getIndex();
		case 6 ->  result = IndexedColors.LIGHT_ORANGE.getIndex();
		case 7 ->  result = IndexedColors.GOLD.getIndex();
		case 8 ->  result = IndexedColors.PINK.getIndex();
		case 9 ->  result = IndexedColors.INDIGO.getIndex();
		}
		
		cellStyle.setFillForegroundColor(result);
	}
	
	/**
	 *  Excelに数独を出力する 
	 * @param randomNumberArray 二次元配列
	 * */
	public void getExcel(int[][] randomNumberArray) throws Exception {
		Random rand = new Random();
		
		// Excelファイルを作成
		Workbook wb = new XSSFWorkbook();

		// シートを作成
		Sheet ws = wb.createSheet("Sheet1");

		// 中央揃え
		CellStyle cellStyle = wb.createCellStyle();
		
    	// 背景色
    	cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// 水平・垂直位置設定
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		// 先にシート自体に行を作成する
		for (int i = 0; i < 9; i++) {
			ws.createRow(i);
		}
		
		// 数独をExcelシートに出力する
		int sudoku = 1 ;
		for (int row = 0; row < 9; row += sudoku) {
			sudoku = rand.nextInt(2) + 1;
			for (int column = 0; column < 9; column += sudoku) {
				sudoku = rand.nextInt(2) + 1;
				
				getBackgroundColor(randomNumberArray[row][column], cellStyle);
				ws.getRow(row).createCell(column).setCellValue(randomNumberArray[row][column]);
				ws.getRow(row).getCell(column).setCellStyle(cellStyle);
			}
		}

		// 出力用のストリームを用意
		FileOutputStream out = new FileOutputStream("Sudoku.xlsx");

		// ファイルへ出力
		wb.write(out);
		wb.close();
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
		boolean isOnly = false; // ユニークな値が出てきたかどうか判定
		int createNumber = 0; // 乱数を入れる変数
		int refresh = 0; // 全体をリフレッシュするための変数
		int resetCount = 0; // 9カ所ユニークな値が入らなかった時の回数

		int rowAxis = 0; // 行軸管理変数
		int searchRowAxis = 0; // 検索行軸変数
		int backSearchRowAxis = 0; // 検索行軸変数戻り量蓄積用
		int uniqueAreaR = 1; // 行軸ユニーク位置追跡用

		int columnAxis = 0; // 列軸管理変数
		int searchColumnAxis = 0; // 検索列軸変数
		int backSearchColumnAxis = 0; // 検索列軸変数戻り量蓄積用
		int uniqueAreaC = 1; // 列軸ユニーク位置追跡用

		// ユニーク配列から1個づつ周りのユニーク数値を埋めていく処理
		for (int i = 0; i < uniqueArray.length; i++) {

			// uniqueArrayからi番目の値をperimeterArrayの[0]に入れる処理
			int[] perimeterArray = getUniqueCenterSet(uniqueArray, i);

			// 0に中心の値があるので1 perimeter_arrayに入れる値の位置を別で控える
			int remainingCount = 1;

			// 9列目まで来たら初期化して行を3足して次の3行のエリアへ移動
			if (columnAxis == 9) {
				// 行軸は3足す
				rowAxis += 3;
				searchRowAxis += 3;

				// 列軸は初期化
				columnAxis = 0;
				searchColumnAxis = 0;

				// 中央ユニーク値の位置行軸3足し列軸初期位置へ
				uniqueAreaC = 1; // 次のユニーク位置合わせ
				uniqueAreaR += 3; // 次のユニーク位置合わせ
			}

			// 中心の値以外残り8個数値が入るまでループする。
			while (uniqueCount != 8) {

				// 9個ユニークな値が取れなかった回数が5回を超えた場合中身を一旦リセットする
				if (5 < resetCount) {
					perimeterArray = getUniqueCenterSet(uniqueArray, i);
					remainingCount = 1; // 0に中心の値があるので1 perimeter_arrayに入れる値の位置を別で控える
					resetCount = 0; // カウントもリセット

					// ここで比較する列と行の値を進んだ量を一旦戻す
					searchRowAxis -= backSearchRowAxis;
					searchColumnAxis -= backSearchColumnAxis;

					// 進んだ量を管理していた変数を初期化
					backSearchRowAxis = 0;
					backSearchColumnAxis = 0;

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

					//uniqueArrayの中身を全て確認する。
					createNumber = randomArrays[j];
					// perimeterArrayの中身と比較する
					isOnly = getArraySearch(createNumber, remainingCount, perimeterArray);
					if (!isOnly) {
						continue;
					} else {
						isOnly = getWarpAndWeftSearch(createNumber, randomNumberArray, searchRowAxis,
								searchColumnAxis);
						if (!isOnly) {
							resetCount++;
							continue;
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
						searchRowAxis++;

						// 同時に比較戻り用変数を加算
						backSearchRowAxis++;

						// 3行目に達した時に次の列へ、行は3行戻る
						if (searchRowAxis % 3 == 0) {

							// 行戻りと同時に蓄積量側も戻す
							searchRowAxis -= 3;
							backSearchRowAxis -= 3;

							// 列移動と同時に蓄積量側も加算する
							searchColumnAxis++;
							backSearchColumnAxis++;

							// ユニーク値の個数を管理してる変数が5の時はユニークな[1,4,7]の位置なので行を加算すると同時に蓄積量も加算
						} else if (remainingCount == 5) {
							searchRowAxis++;
							backSearchRowAxis++;
						}
					}
				}

				// ユニーク値が9個埋まった場合に二次元配列へ登録処理
				if (remainingCount == 9) {
					for (int j = 1; j < perimeterArray.length; j++) {
						// 元の二次元配列に入れる
						randomNumberArray[columnAxis][rowAxis] = perimeterArray[j];

						// 次の行に移動
						rowAxis++;

						// ユニークの値が入ったカウント
						uniqueCount++;

						// 3行目まで到達した時に行数を3行戻して、1列ずらす
						if (rowAxis % 3 == 0) {
							rowAxis -= 3;
							columnAxis++;

							// ユニークな中心の値がある場所に来た際は1行ずらしてスキップする。
						} else if (rowAxis == uniqueAreaR && columnAxis == uniqueAreaC) {
							// 行数加算
							rowAxis++;
						}
					}

					// 全て登録し終わったら蓄積量変数を初期化
					backSearchRowAxis = 0;
					backSearchColumnAxis = 0;
					uniqueAreaC += 3;
				}
			}

			// 中心の値以外残り8個数値がユニークで入った時点で初期化
			uniqueCount = 0;
		}
		return true;
	}

	/**
	 *  行と列内一括検索結果取得
	 * @param createNumber 追加予定の数値
	 * @param randomNumberArray 二次元配列
	 * @param searchRowAxis 検索する行位置
	 * @param searchColumnAxis 検索する列位置
	 * 
	 * @return 重複無しならtrue
	 * @apiNote 検索行と検索列の位置を一括検索する
	 * */
	public boolean getWarpAndWeftSearch(int createNumber, int[][] randomNumberArray, int searchRowAxis,
			int searchColumnAxis) throws Exception {
		boolean isOnly = false;
		// 行と列のユニーク検索
		for (int i = 0; i < 9; i++) {

			// 比較して違えばTrueで同じ場合Falseになる
			isOnly = randomNumberArray[i][searchRowAxis] != createNumber;
			if (!isOnly) {
				return false;
			} else {
				isOnly = randomNumberArray[searchColumnAxis][i] != createNumber;
				if (!isOnly) {
					return false;
				}
			}
		}
		return true;
	}

	/** 
	 * ユニーク配列内一致検索
	 * @param createNumber 追加予定の数値
	 * @param remainingCount perimeterArray内のユニーク値数
	 * @param perimeterArray ユニーク配列
	 * 
	 * @return 重複無しならtrue
	 * @apiNote  perimeterArray内のユニーク値個数分For分を回して検索する
	 *  */
	public boolean getArraySearch(int createNumber, int remainingCount, int[] perimeterArray) throws Exception {
		for (int n = 0; n < remainingCount; n++) {
			// createNumberとperimeterArray内を比較して違えばTrueで同じ場合Falseになる
			boolean isOnly = perimeterArray[n] != createNumber;
			if (!isOnly) {
				return false;
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
