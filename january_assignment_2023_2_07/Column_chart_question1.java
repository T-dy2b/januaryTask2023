package january_assignment_2023_2_07;

import java.util.Scanner;

// 問題１・２
public class Column_chart_question1 extends Column_chart_question2{

	private Scanner sc = new Scanner(System.in); // スキャナー機能
	// 問題１・２で使う定数
	/**配列の要素数  デフォルト 10*/
	private static final int ARRAY_ELEMENTS = 10; 				 // 配列の要素数  デフォルト 10
	private static final String CHART_STRING = "＊"; 			 // グラフの文字列  デフォルト ＊
	private static final double VERTICAL_AXIS_SCALE_UNIT = 0.5; // 縦軸の目盛り単位  デフォルト 0.5

	public static void main(String[] args) {

		try {
			Column_chart_question1 cc = new Column_chart_question1(); // インスタンス	

			//要素数入力受付処理
			int array_elements = cc.get_input_acceptance_element();

			// グラフ文字列受付
			String chart_string = cc.get_graph_string_acceptance();

			// 縦軸目盛り単位受付
			double vertical_axis_scale_unit = cc.get_Scale_unit_designation();

			// 乱数用に二次元配列初期設定
			Double[][] random_number_array = new Double[array_elements][2];

			// 乱数を作成して二次元配列へ順に番号セットする処理
			random_number_array = cc.set_random_number_array(random_number_array, array_elements);

			// ソート種類選択受付
			cc.get_sort_selection(random_number_array);

			// グラフ作成処理
			cc.set_create_graphing(vertical_axis_scale_unit, array_elements, random_number_array, chart_string);

			// コンソール入力を終了する
			cc.sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/**
	 * グラフ作成処理
	 * 引数 vertical_axis_scale_unit：目盛りの単位
	 * 				  array_elements：要素数
	 * 			 random_number_array：乱数二次元配列
	 * 					chart_string：グラフ設定文字
	 * 
	 * */
	public void set_create_graphing(double vertical_axis_scale_unit, int array_elements, Double[][] random_number_array,
			String chart_string) throws Exception {
		
		// 限界値が10なのでその分から順に縦軸目盛り分for分を回していく
		for (double i = 10; 0 < i; i -= vertical_axis_scale_unit) {

			// ここで横軸移動時スペースか＊の分岐
			for (int j = 0; j < array_elements; j++) {

				// 二桁になると2文字なので余分にスペースを含めていく。
				if (random_number_array[j][0] > 9) {
					System.out.print("  ");
				}else {
					System.out.print(" ");
				}

				// 10引く縦軸目盛り+0.1(0と0.1差別化)が乱数の二次元配列の数値が大きい場合＊描画
				if ((i - vertical_axis_scale_unit + 0.1) <= random_number_array[j][1]) {
					System.out.print(chart_string);

				// 当てはまらない場合はスペースを描画していく。
				} else {
					System.out.print("  ");
				}

				// 要素移動時にもスペースを描画
				System.out.print("  ");
			}

			// 縦軸移動
			System.out.println();
		}

		// グラフ描画後の仕切り線
		for (int i = 0; i < array_elements; i++) {
			System.out.print("-----");
			if (i > 9) {
				System.out.print("-");
			}
		}
		
		System.out.println();

		//要素数分横軸にNo描画
		for (int i = 0; i < array_elements; i++) {
			System.out.print("  ");
			System.out.print(Math.round(random_number_array[i][0]));
			System.out.print("  ");
		}
		System.out.println("\n");

		//ソート後のデータを出力
		for (int i = 0; i < random_number_array.length; i++) {
			System.out.print(Math.round(random_number_array[i][0]));

			// 2桁以下Noの場合は乱数位置調整
			if (Math.round(random_number_array[i][0]) < 10) {
				System.out.print(" ");
			}
			System.out.println(" " + random_number_array[i][1]);
		}
	}

	/**
	 * 乱数二次元配列に番号、乱数セット処理
	 * 引数		random_number_array：乱数二次元配列
	 * 			     array_elements：要素数
	 * */
	public Double[][] set_random_number_array(Double[][] random_number_array, int array_elements) throws Exception {

		// 乱数を作成して二次元配列へ順に番号セットで格納していく。
		for (int i = 0; i < array_elements; i++) {

			// ここでNoを入れていく。
			random_number_array[i][0] = (double) (i + 1);

			// Math.randomで0.0から9.9が取れるので10倍して小数点以下切り捨てしてから割ることで小数点第一位までを変数に格納
			random_number_array[i][1] = Math.floor(Math.random() * 100) / 10;
		}
		return random_number_array;
	}

	/**
	 * 目盛り単位入力受付処理
	 * */
	public double get_Scale_unit_designation() throws Exception {
		double default_scale = VERTICAL_AXIS_SCALE_UNIT; // 定数クラスからデフォルト値を受け取る
		int judgment = 0; // 問題なく入力を得たか確認する変数
		
		// 入力規制 0.1~1までの範囲
		String input_restrictions = "^[0 ０][\\.][0-9 ０-９]{1}|^[1]";

		// 適正入力を得るまでループ
		while (judgment == 0) {
			
			//要素数入力受付
			System.out.printf("縦軸の目盛り単位(デフォルト%.1f)：", default_scale);
			String user_input = sc.nextLine();

			// 入力規制処理
			if (user_input.matches(input_restrictions)) {
				
				// 問題ない入力の場合String型からdouble型に変換しつつ返却用変数へ格納
				default_scale = Double.parseDouble(user_input);
				judgment++;
				
			// エンターだけ押した場合ループから抜けるのでデフォルト値が適用
			} else if ("".equals(user_input)) {
				
				// ここにデフォルト値を入れることでjudgmentの変数が不必要になる。
				// breakは基本無しにする。必要なところは統一をかける
				break;
				
			// 入力されたのが0.1~1以外の場合入力を再度求める
			} else {
				System.out.println("0.1～1の数字を入力してください");
			}
		}
		return default_scale;

	}

	/**
	 * グラフ描画文字列受付処理
	 * 
	 * */
	public String get_graph_string_acceptance() throws Exception {
		String default_string = CHART_STRING; // 定数クラスからデフォルト値を受け取る
		int judgment = 0; // 問題なく入力を得たか確認する変数

		while (judgment == 0) {
			System.out.printf("グラフ文字列(デフォルト%s)：", default_string);
			String user_input = sc.nextLine();
			
			// スペースを入れた場合はここで再入力を求める
			if (user_input.matches("(.*\s.*|.*\\h.*)")) {
				System.out.println("別の文字または記号に変えてください");
			
			// エンターだけ押した場合ループから抜けるのでデフォルト値が適用
			} else if ("".equals(user_input)) {
				break;
			
			// 入力規制外の処理
			} else {
				
				// 問題ない入力ならその値を返却用変数へ格納
				default_string = user_input;
				judgment++;
			}
		}
		return default_string;
	}

	/**
	 * 要素数入力受付処理
	 * 
	 * */
	public int get_input_acceptance_element() throws Exception {
		int default_element = ARRAY_ELEMENTS; // 定数クラスからデフォルト値を受け取る
		int judgment = 0; // 問題なく入力を得たか確認する変数

		// 入力規制 入力受付する設定
		String input_restrictions = "[1-9 １-９][0-9 ０-９]{0,1}";

		while (judgment == 0) {
			
			//要素数入力受付
			System.out.printf("配列の要素数(デフォルト%d)：", default_element);
			
			// 入力受付処理
			String user_input = sc.nextLine();

			// 入力規制処理
			if (user_input.matches(input_restrictions)) {
				
				// 問題ない入力ならString型からInt型へ変換しつつ返却用変数へ格納
				default_element = Integer.parseInt(user_input);
				judgment++;

			// 入力されたのエンターならデフォルト値で抜ける
			} else if ("".equals(user_input)) {
				break;
				
			// 入力規制外の処理
			} else {
				System.out.println("1～99の数字を入力してください");
			}
		}
		return default_element;

	}

}
