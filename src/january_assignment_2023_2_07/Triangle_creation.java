package january_assignment_2023_2_07;

import java.util.Scanner;

// 問題７・８
public class Triangle_creation {

	private Scanner sc = new Scanner(System.in); // スキャナー機能
	// 問題７・８で使用する定数
	// 定数にするにはstaticが必要と知る
	private static final int UPPER = 1; // 上方向
	private static final int LOWER = 2; // 下方向
	private static final int LEFT = 3; // 左方向

	public static void main(String[] args) {
		try {
			Triangle_creation tc = new Triangle_creation();
			
			// 受け付ける段数
			int steps = tc.set_triangle_step_number();
			
			// 三角形の向きを受け付ける
			int direction = tc.set_triangle_direction_receive();
			
			System.out.printf("入力された値は%d段になります\n", steps);
			System.out.println("---------------------------");
			
			// 向きと段数を渡して三角形を表示する
			tc.set_triangle_display(steps, direction);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	// 向きと段数を渡して三角形を表示する処理
	public void set_triangle_display(int steps, int direction) throws Exception{
		// 4はデフォルトになる設定
		switch(direction) {
		case UPPER -> set_upper_triangle(steps);
		case LOWER -> set_lower_triangle(steps);
		case LEFT -> set_left_triangle(steps);
		default -> set_right_triangle(steps);
		}
	}
	
	// 三角形の向きを受け付ける処理
	public int set_triangle_direction_receive() throws Exception {
		// 入力規制 1から4で入力受付する設定
		String select = "[1-4 １-４]";
		int direction = 0; // 初期値

		System.out.println("三角形の向きを選択してください");
		System.out.println("1: 上  2: 下  3: 左  4: 右");
		// 入力されるまでループ
		while (direction == 0) {

			String user_input = sc.nextLine();
			// 入力規制処理
			if (user_input.matches(select)) {
				direction = Integer.parseInt(user_input);

			// 入力規制以外は再度入力を求める
			}else {
				System.out.println("1から4の数値を入力してください");
			}
		}
		return direction;
	}

	// 三角形の段数を受け付ける処理
	public int set_triangle_step_number() throws Exception {
		// 入力規制 2から99で入力受付する設定
		String select = "[2-9 ２-９]|[1-9 １-９][0-9 ０-９]";
		int steps = 0; 

		System.out.print("三角形の段数を入力してください：");
		// 入力されるまでループ
		while (steps == 0) {

			String user_input = sc.nextLine();
			// 入力規制処理
			if (user_input.matches(select)) {
				steps = Integer.parseInt(user_input);

			// 入力規制以外は再度入力を求める
			}else {
				System.out.println("2から99の数値を入力してください");
			}
		}

		return steps;
	}

	// 下三角形
	public void set_lower_triangle(int steps) throws Exception{
		for (int i = steps; i > 0; i--) {
			for (int j = 0; j <= steps - i; j++) {
				System.out.print(" ");
			}

			for (int j = 0; j < 2 * i - 1; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		for (int i = 0; i < steps * 2; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	// 左三角形
	public void set_left_triangle(int steps) throws Exception{
		for (int i = 0; i < steps; i++) {

			for (int j = 0; j < steps - i; j++) {
				System.out.print(" ");
			}
			// 三角形の上半分を生成
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			// 次のラインへ移動する。
			System.out.println("");
		}

		for (int i = steps - 2; i >= 0; i--) {

			for (int j = 0; j < steps - i; j++) {
				System.out.print(" ");
			}

			// 三角形の下半分を生成
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			// 次のラインへ移動する。
			System.out.println("");
		}
		for (int i = 0; i < steps * 2; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	// 右方向の三角形
	public void set_right_triangle(int steps) throws Exception{
		for (int i = 0; i < steps; i++) {

			// 三角形の上半分を生成
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			// 次のラインへ移動する。
			System.out.println("");
		}

		for (int i = steps - 2; i >= 0; i--) {
			// 三角形の下半分を生成
			for (int j = 0; j <= i; j++) {
				System.out.print("*");
			}
			// 次のラインへ移動する。
			System.out.println("");
		}
		for (int i = 0; i < steps * 2; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}

	// 上方向の三角形
	public void set_upper_triangle(int steps) throws Exception{
		// 上三角形
		for (int i = 0; i < steps; i++) {

			// スペースを生成 3個 最初はiが０だからステップ数の３回スペースを生成
			// ２回目iが１だからステップー１になり二回だけスペースが生成
			for (int j = 0; j < steps - i; j++) {
				System.out.print(" ");
			}

			// アスタリスク生成	 １個 最初はiが０だから１回だけ回る
			// ２回目iが１だから３回アスタリスクが生成される
			for (int j = 0; j <= (i * 2); j++) {
				System.out.print("*");
			}
			// 次のラインへ移動する。
			System.out.println("");
		}
		for (int i = 0; i < steps * 2; i++) {
			System.out.print("-");
		}
		System.out.println("");
	}
}
