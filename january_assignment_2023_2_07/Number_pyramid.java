package january_assignment_2023_2_07;

import java.util.Scanner;

// 問題９・１０・１０ＥＸ
public class Number_pyramid {

	private Scanner sc = new Scanner(System.in); // スキャナー機能

	public static void main(String[] args) {
		try {
			Number_pyramid np = new Number_pyramid(); // インスタンス

			// ピラミッドの段数入力受付
			int step_number = np.get_pyramid_paragraphs();

			// 表示する図の量を受け付ける
			int figure_quantity = np.get_figure_quantity_selection();

			// ピラミッドかダイヤか入力受付
			boolean diamond = np.get_diamond_ONorOff();

			System.out.printf("入力した段数は%d段になります。\n", step_number);

			// 仕切り線追加処理
			np.set_starting_line(step_number, figure_quantity, diamond);

			// ピラミッドかダイヤのどちらで表示するかを処理
			np.set_vertical_scale(step_number, figure_quantity, diamond);

			// 仕切り線追加処理
			np.set_starting_line(step_number, figure_quantity, diamond);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	/**
	 * 表示する縦横の拡張数値分縦に拡張する処理
	 * 引数		step_number：ピラミッドの段数
	 * 		figure_quantity：表示する図の量
	 * 				diamond：ピラミッドかダイヤの選択結果
	 * */
	public void set_vertical_scale(int step_number, int figure_quantity, boolean diamond) throws Exception {
		// 縦に拡張
		for (int i = 0; i < figure_quantity; i++) {
			
			// ピラミッドかダイヤを数字で表示する処理
			set_pyramid_or_diamond(step_number, figure_quantity, diamond);

			// 最後の部分にスペースがいらないため
			if (i < figure_quantity - 1) {
				System.out.println("");
			}
		}
	}

	/**
	 * 仕切り線生成処理
	 * 引数		step_number：ピラミッドの段数
	 * 		figure_quantity：表示する図の量
	 * 				diamond：ピラミッドかダイヤの選択結果
	 * */
	public void set_starting_line(int step_number, int figure_quantity, boolean diamond) throws Exception {
		for (int i = 0; i < figure_quantity; i++) {
			for (int j = 0; j < step_number; j++) {
				if(diamond) {
					System.out.print("---");
				}else {
					System.out.print("--");
				}
			}
		}
		System.out.println("");
	}

	/**
	 * 表示する図の量を受け付ける
	 * */
	public int get_figure_quantity_selection() throws Exception {
		
		int figure_quantity = 0; // 縦横複数表示する量を格納する変数
		
		// 入力規制 1から5で入力受付する設定
		String select = "[1-5 １-５]";

		System.out.println("縦横複数表示する量を1～5で入力してください");
		// 入力されるまでループ
		while (figure_quantity == 0) {

			// 入力を受け付けて文字列として変数に格納
			String user_input = sc.nextLine();

			// 入力規制処理
			if (user_input.matches(select)) {
				figure_quantity = Integer.parseInt(user_input);

			// 入力規制以外は再度入力を求める
			} else {
				System.out.println("1から5の数値を入力してください");
			}
		}
		return figure_quantity;
	}

	// ピラミッドの段数入力受付
	public int get_pyramid_paragraphs() throws Exception {
		// 入力規制 1から13で入力受付する設定
		String select = "[1-9 １-９]|[1 １][0-3 ０-３]";
		int paragraphs = 0; // 初期値

		System.out.println("ピラミッドの段数を入力してください");
		// 入力されるまでループ
		while (paragraphs == 0) {

			// 入力を受け付ける
			String user_input = sc.nextLine();
			// 入力規制処理
			if (user_input.matches(select)) {
				paragraphs = Integer.parseInt(user_input);

			// 入力規制以外は再度入力を求める
			} else {
				System.out.println("1から13の数値を入力してください");
			}
		}
		return paragraphs;
	}

	// ピラミッドかダイヤか入力受付
	public boolean get_diamond_ONorOff() throws Exception {
		// 入力規制 1か2で入力受付する設定
		String select = "[1-2 １-２]";
		boolean diamond_select = false; // 初期値
		int trigger = 0; 				// 分岐の引き金

		System.out.println("1個目に表示される図形は以下から選べます");
		System.out.println("1: ピラミッド  2: ダイヤ ");
		// 入力されるまでループ
		while (trigger == 0) {

			// 入力を受け付ける
			String user_input = sc.nextLine();

			// 入力規制処理
			if (user_input.matches(select)) {
				trigger = Integer.parseInt(user_input);

				// 入力値が2ならダイヤのスイッチをONにする
				if (trigger == 2) {
					diamond_select = true;
				}
			// 入力規制以外は再度入力を求める
			} else {
				System.out.println("1か2の数値を入力してください");
			}
		}
		return diamond_select;
	}

	/**
	 * ピラミッドかダイヤを表示する処理
	 * 引数		step_number：ピラミッドの段数
	 * 		figure_quantity：表示する図の量
	 * 				diamond：ピラミッドかダイヤの選択結果
	 * 
	 * diamondがtrueならダイヤ型に表示
	 * */
	public void set_pyramid_or_diamond(int step_number, int figure_quantity, boolean diamond) throws Exception {
		int i = 0; 			// 行の管理
		int area_size = 0;  // 横に拡張する回数

		// diamondならステップを先に1個加算する
		if (diamond) {
			step_number++;
		}

		// 行のループ
		for (i = 1; i <= step_number; i++) {
			area_size = 0; // 初期化
			
			// 行にスペースと数字その後またスペースを入れる処理
			set_line_space_numbers(step_number, figure_quantity, area_size, i);
			System.out.println("");
		}

		// diamondがtrueなら下側を生成
		if (diamond) {
			
			// ここから下側のピラミッド作成
			for (i = step_number - 1; i >= 1; i--) {
				area_size = 0; // 初期化
				
				// 行にスペースと数字その後またスペースを入れる処理
				set_line_space_numbers(step_number, figure_quantity, area_size, i);
				System.out.println("");
			}
		}
	}

	/**
	 * 行にスペースと数字その後またスペースを入れる処理
	 * 引数		step_number：ピラミッドの段数
	 * 		figure_quantity：表示する図の量
	 * 			  area_size：横に拡張する回数
	 * 					  i：ピラミッド側現在の状態
	 * */
	public void set_line_space_numbers(int step_number, int figure_quantity, int area_size, int i) throws Exception{
		
		// 横に拡張する回数が表示する図の量と同じになるまで繰り返す。
		while (area_size != figure_quantity) {

			// 行内スペース量を計算してスペースをセットする
			for (int j = 1; j <= step_number - i; j++) {
				System.out.print(" ");
			}

			 // 行内の処理用
			for (int j = 1; j < 2 * i; j++) {
				// 一桁だけ抽出するために一度文字列に変換してからint型に戻して表示
				String number = String.valueOf(i);
				int one_digit = Integer.parseInt(number.substring(number.length() - 1));
				System.out.printf("%d", one_digit);
			}
			
			// 行内スペース量を計算してスペースをセットする
			for (int j = 1; j <= step_number - i; j++) {
				System.out.print(" ");
			}
			area_size++;
			System.out.print(" ");
		}
	}

}
