package january_assignment_2023_2_07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 問題２
public class Column_chart_question2 {
	private Scanner sc = new Scanner(System.in); // スキャナー機能
	/**
	 * ソート選択受付処理
	 * 引数	 random_number_array：乱数二次元配列
	 * */
	public void get_sort_selection(Double[][] random_number_array) throws Exception {
		Comp comp = new Comp(); // コンパレーターインスタンス
		int judgment = 0; 		// 問題なく入力を得たか確認する変数
		
		// 入力規制 0~2までの範囲
		String input_restrictions = "[0-2 ０-２]";
		
		// 適正入力を得るまでループ
		while (judgment == 0) {
			
			// ソート選択受付
			System.out.println("ソート： 0. No.順  1. 昇順  2. 降順");
			String sort_order = sc.nextLine();

			// 入力規制処理
			if (sort_order.matches(input_restrictions)) {
				
				// 入力規制で適正な数値の場合は文字列から数値へ変換して、コンパレーター側に渡す処理
				int select_sort = Integer.parseInt(sort_order);
				comp.set_order(select_sort);
				judgment++;
				
			// エンターだけ押された場合にここでは、もう一度入力を求める処理
			} else if ("".equals(sort_order)) {
				System.out.println("0～2の数字を入力してください");
			// 入力されたのが0～2以外の場合入力を再度求める
			} else {
				System.out.println("0～2の数字を入力してください");
			}
		}

		//Arraysクラスのsort()を実行する
		Arrays.sort(random_number_array, comp);
	}

	/**
	 * コンパレーター処理
	 * */
	public class Comp implements Comparator<Object> {

		private int order = 0;  // ソート選択
		private int result = 0; // 結果用

		// どのソートをするか受け取る
		void set_order(int i) {
			order = i;
		}

		@Override
		public int compare(Object a, Object b) {
			Double[] Double_a = (Double[]) a;
			Double[] Double_b = (Double[]) b;

			// ここでは比較結果のみを返す形で問題ない。
			// コンペア側が並べ替えてくれるのでここでは１か０か－１かを渡すだけ。
			// １ならAが上に０ならそのままー１ならAが下に行く形になる。
			double compare_a = Double_a[1];
			double compare_b = Double_b[1];
			// 1なら昇順
			if (order == 1) {
				result = Double.compare(compare_a, compare_b);
			// 2なら降順
			} else if (order == 2) {
				result = Double.compare(compare_b, compare_a);
			}
			// それ以外はそのままNo順になる
			return result;
		}
	}
}
