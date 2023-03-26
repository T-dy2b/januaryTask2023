package january_assignment_2023_2_07;

import java.util.Random;
import java.util.Scanner;

// 問題３・４
public class Two_dimensions_array_random_number {

	private Scanner sc = new Scanner(System.in); // スキャナー機能
	// 問題３・４で使う定数
	private static final int DEFAULT_ARRAY = 5; // 問題３のデフォルト配列
	private static final int SUDOKU_MAX_NUMBER = 9; // 数独用最大数値

	public static void main(String[] args) {
		try {
			Two_dimensions_array_random_number ta = new Two_dimensions_array_random_number();

			// 問題３か４どちらを始めるかを選択求める処理
			int question_select = ta.get_question_selection();

			// 選択結果からメソッド分岐
			switch (question_select) {
			case 3 -> ta.set_question_3();
			case 4 -> ta.set_question_4();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	// 問題３
	public void set_question_3() throws Exception {
		
		// 配列サイズを受け付ける処理
		int default_array_size = set_array_value(); 

		// 乱数用に二次元配列初期設定		
		int[][] random_number_array = new int[default_array_size][default_array_size];

		// 乱数の生成、すべてユニークにする
		set_random_number_comparison(random_number_array);

		// 乱数の配列を表示する処理
		for (int i = 0; i < default_array_size; i++) {
			for (int j = 0; j < default_array_size; j++) {
				// Stringformatで3桁分の表示にすることでスペースなどを入れなくても整列に表示ができる。
				System.out.printf("%5d", random_number_array[i][j]);
			}
			System.out.println();
		}
	}

	// 問題４ [1,4,7][1,4,7]の位置がユニークな数値で尚且つ縦横がユニークな値で埋まる
	public void set_question_4() throws Exception {
		int[][] random_number_array = new int[9][9]; //9*9の配列を作成
		int[] unique_array = new int[9]; // ユニークな9点の数値保管用
		
		// 数独が完成したかどうかを入れる変数
		boolean sudoku_completed = false;

		// 全エリアがユニーク尚且つ中央が全てユニークになるまで終了しない	
		while (!sudoku_completed) {
			
			// ユニークな中心の値9個を作成する処理
			set_unique_center_nine(random_number_array, unique_array);
			
			// ユニークな中心値の回りをユニークな値で埋める処理
			sudoku_completed = set_perimeter(random_number_array, unique_array);
		}
		
		// 数独を表示する
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.printf("%2d", random_number_array[i][j]);
			}
			System.out.println();
		}

	}

	// 配列のサイズを受け付ける
	public int set_array_value() throws Exception {
		// 入力規制 1から99で入力受付する設定
		String select = "[1-9 １-９][0-9 ０-９]{0,1}";
		int participant = DEFAULT_ARRAY;

		int judgment = 0;

		// 入力されるまでループ
		while (judgment == 0) {
			System.out.println("配列の大きさを入力してください");
			System.out.println("例 5   5×5 になります : デフォルト5");

			String user_input = sc.nextLine();
			// 入力規制処理
			if (user_input.matches(select)) {
				participant = Integer.parseInt(user_input);
				judgment++;

			// エンターのみならデフォルト値
			} else if ("".equals(user_input)) {
				break;
			}
			// 入力規制以外なら再度入力を求める
			else {
				System.out.println("1から99の数値を入力してください");
			}
		}
		return participant;
	}

	/**
	 * 9マスの中央ユニーク値を別のユニーク配列先頭にセットする処理
	 * 引数 unique_array：中心のユニーク値の配列
	 * 			position：中心のユニーク値配列内の位置
	 * */
	public int[] set_perimeter_array(int[] unique_array, int position) throws Exception {
		int[] perimeter_array = new int[9]; // 中心のユニークな値を1つ入れて周りの数値もユニークになるよう比較配列
		perimeter_array[0] = unique_array[position]; // 中心のユニークな値
		return perimeter_array;
	}

	/**
	 * ユニークな中心値の回りをユニークな値で埋める処理
	 * 引数		random_number_array：乱数配列
	 * 				   unique_array：中心のユニーク値の配列
	 * */
	public boolean set_perimeter(int[][] random_number_array, int[] unique_array) throws Exception {
		Random rand = new Random();
		int unique_count = 0; 					// ユニーク値個数を格納
		int horizontal_axis = 0; 				// 横軸管理変数
		int vertical_axis = 0;					// 縦軸管理変数
		int search_horizontal_axis = 0; 		// 検索横軸変数
		int back_search_horizontal_axis = 0; 	// 検索横軸変数戻り量蓄積用
		int search_vertical_axis = 0; 			// 検索縦軸変数
		int back_search_vertical_axis = 0; 		// 検索縦軸変数戻り量蓄積用
		boolean judgment = false; 				// ユニークな値が出てきたかどうか判定
		int unique_area_v = 1;					// 縦軸ユニーク位置追跡用
		int unique_area_h = 1; 					// 横軸ユニーク位置追跡用
		boolean pass_through = false; 			// 3ヵ所やり終えた時のフラグ
		int reset_count = 0;					// 9カ所ユニークな値が入らなかった時の回数
		int create_number = 0; 					// 乱数を入れる変数
		int refresh = 0; 						// 全体をリフレッシュするための変数

		// ユニーク配列から1個づつ周りのユニーク数値を埋めていく処理
		for (int i = 0; i < unique_array.length; i++) {

			// 中央のユニークな値を取得する処理
			int[] perimeter_array = set_perimeter_array(unique_array, i);
			
			// 0に中心の値があるので1 perimeter_arrayに入れる値の位置を別で控える
			int remaining_count = 1; 

			// 中心の値以外残り8個数値が入るまでループする。
			while (unique_count != 8) {

				// 9個ユニークな値が取れなかった場合中身を一旦リセットする
				if (5 < reset_count) {
					perimeter_array = set_perimeter_array(unique_array, i);
					remaining_count = 1; // 0に中心の値があるので1 perimeter_arrayに入れる値の位置を別で控える
					reset_count = 0; // カウントもリセット

					// ここで比較する列と行の値を進んだ量を一旦戻す
					search_horizontal_axis -= back_search_horizontal_axis;
					search_vertical_axis -= back_search_vertical_axis;

					// 進んだ量を管理していた変数を初期化
					back_search_horizontal_axis = 0;
					back_search_vertical_axis = 0;

					// 全マスをリフレッシュする変数を加算
					refresh++;

					// 全マスをリフレッシュする変数が10以上になった場合全ての値を一旦リセットする
					if (10 < refresh) {
						refresh = 0;
						for (int j = 0; j < 9; j++) {
							unique_array[j] = 0;
							for (int k = 0; k < 9; k++) {
								random_number_array[j][k] = 0;
							}
						}
						// リセットしたので一旦question_4に戻る
						return false;
					}
				}
				

				// １から９までの数字が入った配列を用意
				int[] random_arrays = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
				int random_number = random_arrays.length;

				// 配列内数値入れ替え処理
				for (int j = 0; j < random_arrays.length; j++) {
					
					int create_array_number = rand.nextInt(8); 			// 配列内の数字の位置をrandomで生成
					int next_number = random_arrays[--random_number]; 	// 乱数によって上書きされる場所の配列を控えておく。
					
					// 1回目random_arrays[8]にrandom_arrays[乱数]を格納する。
					random_arrays[random_number] = random_arrays[create_array_number];
					// random_arrays[乱数]の中に控えておいたnext_numberのrandom_arrays[8]を格納する。
					random_arrays[create_array_number] = next_number;
				}

				// シャッフルした配列をunique_array内の数値と比較、random_number_arrayの行と列を比較する
				for (int m = 0; m < random_arrays.length; m++) {
					
					// 検索横軸変数か検索縦軸変数が9なら一旦前の処理に戻る
					if (search_vertical_axis == 9 || search_horizontal_axis == 9) {
						break;
					}
					//unique_arrayの中身を全て確認する。
					create_number = random_arrays[m];
					reset: for (int n = 0; n < remaining_count; n++) {
						
						// create_numberとperimeter_array内を比較して違えばTrueで同じ場合Falseになる
						judgment = perimeter_array[n] != create_number;
						if (!judgment) {
							break reset;
						} else {
							
							// 行と列のユニーク検索
							for (int k = 0; k < 9; k++) {
								
								// 比較して違えばTrueで同じ場合Falseになる
								judgment = random_number_array[k][search_horizontal_axis] != create_number;
								if (!judgment) {
									reset_count++;
									break reset;
								} else {
									judgment = random_number_array[search_vertical_axis][k] != create_number;
									if (!judgment) {
										reset_count++;
										break reset;
									}
								}
							}
						}
					}

					// 確認した結果被りなしなら登録する。
					if (judgment) {

						// ユニークな値と確認が出来たら中心ユニーク配列の中に入れる
						perimeter_array[remaining_count] = create_number;
						
						// 中心ユニーク配列の次に代入する位置をずらす
						remaining_count++;

						// 問題ない数字が入ったのでリセットカウントを初期化
						reset_count = 0;

						// 問題ない数値が入った場合次の比較行へ移動
						search_horizontal_axis++;
						
						// 同時に比較戻り用変数を加算
						back_search_horizontal_axis++;

						// 3行目に達した時に次の列へ、行は3行戻る
						if (search_horizontal_axis % 3 == 0) {
							
							// 行戻りと同時に蓄積量側も戻す
							search_horizontal_axis -= 3;
							back_search_horizontal_axis -= 3;

							// 列移動と同時に蓄積量側も加算する
							search_vertical_axis++;
							back_search_vertical_axis++;

						// ユニーク値の個数を管理してる変数が5の時はユニークな[1,4,7]の位置なので行を加算すると同時に蓄積量も加算
						} else if (remaining_count == 5) {
							search_horizontal_axis++;
							back_search_horizontal_axis++;
						}
					}

				}

				// ユニーク値が9個埋まった場合に二次元配列へ登録処理
				if (remaining_count == 9) {
					for (int j = 1; j < perimeter_array.length; j++) {
						// 元の二次元配列に入れる
						random_number_array[vertical_axis][horizontal_axis] = perimeter_array[j];

						// 次の行に移動
						horizontal_axis++;

						// ユニークの値が入ったカウント
						unique_count++;

						// 3行目まで到達した時に行数を3行戻して、1列ずらす
						if (horizontal_axis % 3 == 0) {
							horizontal_axis -= 3;
							vertical_axis++;

						// ユニークな中心の値がある場所に来た際は1行ずらしてスキップする。
						} else if (horizontal_axis == unique_area_h && vertical_axis == unique_area_v) {

							// ユニークな中心の値が7列目に到達した時にスキップする次の予定位置へ変更する
							if (unique_area_v == 7) {
								unique_area_h += 3; 	// 次のユニーク位置合わせ
								unique_area_v = 1; 		// 次のユニーク位置合わせ
								pass_through = true; 	// ここに入った場合次回列の加算をスルーする。unique_area_vを-2にすることで必要は無くなる
							}
							
							// 行数加算
							horizontal_axis++; 
						}
					}

					// 全て登録し終わったら蓄積量変数を初期化
					back_search_horizontal_axis = 0;
					back_search_vertical_axis = 0;

				}
			}

			// スルーする変数がfalseなら次の中心ユニークの位置へ加算
			if (!pass_through) {
				unique_area_v += 3;
			}
			pass_through = false;

			// 9列目まで来たら初期化して行を3足して次の3行のエリアへ移動
			if (vertical_axis == 9) {
				horizontal_axis += 3;
				vertical_axis = 0;
			}

			// 中心の値以外残り8個数値がユニークで入った時点で初期化
			unique_count = 0;

			// 9列目まで来たら初期化して行を3足して次の3行のエリアへ移動
			if (search_vertical_axis == 9) {
				search_horizontal_axis += 3;
				search_vertical_axis = 0;
			}
		}
		return true;
	}

	/**
	 * 乱数生成
	 * 引数	max_random：乱数
	 * */
	public int get_random(int max_random) throws Exception{
		Random rnd = new Random();
		int result = 0;
		
		// 引数に＋１して乱数を作成することで受け取った値が最大値になり、０が１になる。
		result = rnd.nextInt(max_random) + 1;

		return result;
	}

	/**
	 * ユニークな中心の値9個を作成する処理
	 * 引数		random_number_array：乱数配列
	 * 				   unique_array：中心のユニーク値の配列
	 * */
	public void set_unique_center_nine(int[][] random_number_array, int[] unique_array) throws Exception {
		int count = 0;
		boolean judgment = false;
		while (count != 9) {
			for (int k = 1; k < 9; k += 3) {
				for (int l = 1; l < 9;) {
					int create_number = get_random(SUDOKU_MAX_NUMBER); //乱数;

					// unique_arrayの中身を全て確認する。
					for (int n = 0; n < unique_array.length; n++) {
						judgment = unique_array[n] != create_number;
						if (!judgment) {
							break;
						}
					}

					// 確認した結果被りなしなら登録する。
					if (judgment) {
						unique_array[count] = create_number;
						random_number_array[l][k] = create_number;
						count++;
						l += 3;
					}
				}
			}
		}
	}

	/**
	 * 乱数生成比較用メソッド
	 * 引数 random_number_array：乱数配列
	 * */
	public int[][] set_random_number_comparison(int[][] random_number_array) throws Exception {
		int random_number = 0; 		// 乱数を入れる変数
		boolean judgment = false; 	// 被り有りかどうかを判定する
		int vertical_axis = 0;		// 縦軸用変数
		int horizontal_axis = 0;	// 横軸用変数
		int array_size = random_number_array.length * random_number_array[0].length; // 全体の箇所数
		int array_count = array_size; // 箇所数計算用

		while (array_count != 0) {
			// 乱数用に二次元配列初期設定		
			random_number = get_random(array_size); // 箇所数を最大値として乱数を作成

			// ユニーク判定
			loop: for (int i = 0; i < random_number_array.length; i++) {
				for (int j = 0; j < random_number_array[i].length; j++) {

					if (random_number_array[i][j] == random_number) {
						judgment = false;
						break loop;
					} else {
						judgment = true;
					}
				}
			}

			// ユニークであれば追加していく。
			if (judgment == true) {
				random_number_array[vertical_axis][horizontal_axis] = random_number;
				horizontal_axis++;
				array_count--;
				if (random_number_array.length == horizontal_axis) {
					vertical_axis++;
					horizontal_axis = 0;
				}
				judgment = false;
			}
		}
		return random_number_array;

	}

	/**
	 * 問題３か４どちらをやるのか選択する処理
	 * */
	public int get_question_selection() throws Exception {
		int judgment = 0; // 問題なく入力を得たか確認する変数
		int result = 0;

		// 入力規制 ３か４のみ許可
		String input_restrictions = "[3-4 ３-４]";

		// 適正入力を得るまでループ
		while (judgment == 0) {

			// 問題選択受付
			System.out.println("開始するのは問題 3 or 4 を入力してください");
			String question_order = sc.nextLine();

			// 入力規制処理
			if (question_order.matches(input_restrictions)) {
				result = Integer.valueOf(question_order);
				judgment++;

			} else {
				System.out.println("3か4の数字を入力してください");
			}
		}
		return result;
	}
}
