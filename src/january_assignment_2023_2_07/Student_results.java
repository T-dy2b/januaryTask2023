package january_assignment_2023_2_07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

// 問題５・６
public class Student_results {

	private Scanner sc = new Scanner(System.in); // スキャナー機能

	public static void main(String[] args) {
		try {

			Student_results sr = new Student_results();
			Comp comp = new Comp(); // コンパレーターインスタンス
			Object[][] student = new Object[6][10]; // 生徒の名前と各種教科、平均と合計を入れる配列生成

			// randomで名前を生成して1列目に格納、各教科得点入力受付
			sr.set_random_name_generation(student);

			// 各教科毎の平均点算出表示
			sr.set_each_average_point_indicates(student);

			// 順位付けの基準を選択
			int average_select = sr.get_sum_comparator_selection();

			// ソートをかけて昇順か降順かを選択受付
			int sort_select = sr.get_sort_selection();

			// ソートをかける
			comp.set_order(sort_select, average_select);
			Arrays.sort(student, comp);

			// ソート後の表示と順位を表示
			sr.set_student_result(student, sort_select);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * ソート後の表示と順位を表示
	 * 引数		student：6人分生徒の名前と点数配列
	 * 		sort_select：ソート選択結果
	 * */
	public void set_student_result(Object[][] student, int sort_select) throws Exception {
		System.out.println("順位 名前          国語    数学    英語    理科    社会  3教科合計 平均  5教科合計 平均");
		
		// studenの二次元配列の中から各データを取り出して表示する
		for (int i = 0; i < student.length; i++) {
			
			// ソートが1で降順なので上から順に順位を出す
			if (sort_select == 1) {
				System.out.printf("%d位 ", (i + 1));
			
			// ソートが2なら昇順となるので上から最下位を表示していく
			} else {
				System.out.printf("%d位 ", (student.length - i));
			}
			
			// 名前を表示
			System.out.printf("%s", student[i][0]);
			
			// 名前の後ろに入れるスペース判定用に変数に格納
			String name = (String) student[i][0];

			// ここで名前の文字列量でスペースの量を変えて数字の位置を合わせてます。
			switch (name.length()) {
			case 3 -> System.out.print("      ");
			case 4 -> System.out.print("    ");
			case 5 -> System.out.print("  ");
			}

			// 名前以降の点数を表示する処理
			for (int j = 1; j < 10; j++) {
				System.out.printf("%8d", student[i][j]);
			}
			System.out.println("");
		}
	}


	/**
	 * 各平均点を表示する処理
	 * 引数		student：6人分生徒の名前と点数配列
	 * */
	public void set_each_average_point_indicates(Object[][] student) throws Exception {
		int average = 0;

		// 教科毎の配列群
		String[] all_subjects = { "国語", "数学", "英語", "理科", "社会" };

		for (int i = 0; i < 5; i++) {
			
			// 各生徒の点数を足していき平均点を割り出してます。
			for (int j = 0; j < 6; j++) {
				average += (int) student[j][i + 1];
			}
			// 平均を出す
			average = average / 6;
			
			System.out.printf("%sの平均点は%d\n", all_subjects[i], average);
			average = 0; // 平均を一度出したら次に行くときにはリセットする
		}
	}

	/**
	 * ランダム名前生成＋点数を受け付ける
	 * 引数		student：6人分生徒の名前と点数配列
	 * */
	public void set_random_name_generation(Object[][] student) throws Exception {
		for (int i = 0; i < 6; i++) {
			
			// ランダムで名前を生成
			student[i][0] = set_create_randomName();
			System.out.printf("%sさんの各テスト点数を数値で入力してください", (String) student[i][0]);
			System.out.println("");

			// 各教科の点数の入力を受け付ける
			for (int j = 1; j < 6; j++) {
				student[i][j] = set_student_score(j);
			}

			// 3,5教科合計と平均算出
			get_sum_and_average_calculation(student, i);
		}
	}

	/**
	 * 教科毎の平均点を出す
	 * */
	public int get_sum_comparator_selection() throws Exception {
		int judgment = 0; // 問題なく入力を得たか確認する変数
		int select_comparator = 0;

		// 入力規制 0~2までの範囲
		String input_restrictions = "[1-2 １-２]";

		// 直前の入力でエンターなどの部分が残っているため一旦ここで全部読み込む
		if (sc.hasNextLine()) {
			sc.nextLine();
		}

		// 適正入力を得るまでループ
		while (judgment == 0) {

			// ソート選択受付
			System.out.println("\n3教科または5教科どちらの合計で順位付けしますか？： 1.「 3教科 」  2.「 5教科 」");
			String comparator_order = sc.nextLine();

			// 入力規制処理
			if (comparator_order.matches(input_restrictions)) {

				// 入力規制で適正な数値の場合は文字列から数値へ変換して、コンパレーター側に渡す処理
				select_comparator = Integer.parseInt(comparator_order);
				judgment++;

			// エンターだけ押された場合にここでは、もう一度入力を求める処理
			} else {
				System.out.println("1か2の数字を入力してください");
			}
		}
		return select_comparator;
	}

	/**
	 * ソート選択受付処理
	 * */
	public int get_sort_selection() throws Exception {
		int judgment = 0; // 問題なく入力を得たか確認する変数
		int select_sort = 0; // ソート選択変数初期値

		// 入力規制 0~2までの範囲
		String input_restrictions = "[1-2 １-２]";

		// 適正入力を得るまでループ
		while (judgment == 0) {

			// ソート選択受付
			System.out.println("ソート： 1. 1位から表示  2. 6位から表示");
			String sort_order = sc.nextLine();

			// 入力規制処理
			if (sort_order.matches(input_restrictions)) {

				// 入力規制で適正な数値の場合は文字列から数値へ変換して、コンパレーター側に渡す処理
				select_sort = Integer.parseInt(sort_order);
				judgment++;

			// エンターだけ押された場合にここでは、もう一度入力を求める処理
			} else if ("".equals(sort_order)) {
				System.out.println("1か2の数字を入力してください");
			
			// 入力されたのが0～2以外の場合入力を再度求める
			} else {
				System.out.println("1か2の数字を入力してください");
			}
		}
		return select_sort;
	}

	/**
	 * 3,5教科合計と平均算出
	 * 引数		student：6人分生徒の名前と点数配列
	 * 		   position：生徒位置
	 * */
	public void get_sum_and_average_calculation(Object[][] student, int position) throws Exception {
		
		// 3教科合計と平均算出
		int sum_3score = 0;
		int average_3score = 0;

		// 5教科合計と平均算出
		int sum_5score = 0;
		int average_5score = 0;

		// それぞれの点数を合計していく
		for (int j = 1; j <= 5; j++) {
			sum_5score += (int) student[position][j];

			// 3教科の場合は3以上は足さない
			if (j <= 3) {
				sum_3score += (int) student[position][j];
			}
		}

		// 3教科と5教科の合計と平均を変数に格納する
		average_3score = sum_3score / 3;
		average_5score = sum_5score / 5;
		student[position][6] = sum_3score;
		student[position][7] = average_3score;
		student[position][8] = sum_5score;
		student[position][9] = average_5score;
	}

	/**
	 * テストの点数入力受付処理
	 * 引数		position：生徒位置
	 * */
	public int set_student_score(int position) throws Exception {

		// 教科毎の配列群
		String[] all_subjects = { "国語", "数学", "英語", "理科", "社会" };
		int score = 0;

		// 教科側には名前の位置が無いためここでポジションに対してマイナス１
		String subject = all_subjects[position - 1];

		// 入力規制 1から100のみ入力受付する設定
		String select = "[1-9 １-９]$|[1-9 １-９][0-9 ０-９]$|[1 １][0 ０][0 ０]$";
		Pattern pattern = Pattern.compile(select);

		// 入力されるまでループ
		while (score == 0) {

			// 各教科名が表示される
			System.out.print(String.format("%s : ", subject));

			// 入力規制処理
			if (sc.hasNext(pattern)) {

				// 入力受付処理
				score = sc.nextInt();

			// 入力されたのが1～100以外の場合入力を再度求める
			} else {
				System.out.println("1～100の数字を入力してください");
				sc.nextInt();
			}
		}
		return score;
	}

	/**
	 * randomで数値を作る
	 * 引数		elementary：乱数の設定値
	 * */
	public int get_random(int elementary) {
		int ramdom = (int) Math.floor(Math.random() * elementary);
		return ramdom;
	}

	/**
	 * ランダムで名前を取得
	 */
	public String set_create_randomName() throws Exception {

		String[] myoujilist = { "佐藤", "鈴木", "高橋", "田中", "渡辺", "伊藤", "山本", "中村", "小林", "加藤", "吉田", "山田", "佐々木", "山口",
				"斎藤", "松本", "井上", "木村", "林", "清水", "山崎", "森", "阿部", "池田", "橋本", "山下", "石川", "中島", "前田", "藤田",
				"小川", "後藤", "岡田", "長谷川", "村上", "近藤", "石井", "齊藤", "坂本", "遠藤", "青木", "藤井", "西村", "福田", "太田", "三浦",
				"岡本", "松田", "中川", "中野", "原田", "小野", "田村", "竹内", "金子", "和田", "中山", "藤原", "石田", "上田", "森田", "原",
				"柴田", "酒井", "工藤", "横山", "宮崎", "宮本", "内田", "高木", "安藤", "谷口", "大野", "丸山", "今井", "高田", "藤本", "武田",
				"村田", "上野", "杉山", "増田", "平野", "大塚", "千葉", "久保", "松井", "小島", "岩崎", "桜井", "野口", "松尾", "野村", "木下",
				"菊地", "佐野", "大西", "杉本", "新井", "浜田", "菅原", "市川", "水野", "小松", "島田", "古川", "小山", "高野", "西田", "菊池",
				"山内", "西川", "五十嵐", "北村", "安田", "中田", "川口", "平田", "川崎", "飯田", "吉川", "本田", "久保田", "沢田", "辻", "関",
				"吉村", "渡部", "岩田", "中西", "服部", "樋口", "福島", "川上", "永井", "松岡", "田口", "山中", "森本", "土屋", "矢野", "広瀬",
				"秋山", "石原", "松下", "大橋", "松浦", "吉岡", "小池", "馬場", "浅野", "荒木", "大久保", "野田", "小沢", "田辺", "川村", "星野",
				"黒田", "堀", "尾崎", "望月", "永田", "熊谷", "内藤", "松村", "西山", "大谷", "平井", "大島", "岩本", "片山", "本間", "早川",
				"横田", "岡崎", "荒井", "大石", "鎌田", "成田", "宮田", "小田", "石橋", "篠原", "須藤", "河野", "大沢", "小西", "南", "高山",
				"栗原", "伊東", "松原", "三宅", "福井", "大森", "奥村", "岡", "内山", "片岡"
		};
		String[] namaelist = { "大輔", "誠", "直樹", "亮", "剛", "大介", "学", "健一", "健", "哲也", "聡", "健太郎", "洋平", "淳", "竜也",
				"崇", "翔太", "拓也", "健太", "翔", "達也", "雄太", "翔平", "大樹", "大輔", "和也", "達也", "翔太", "徹", "哲也", "秀樹", "英樹",
				"浩二", "健一", "博", "博之", "修", "大輝", "拓海", "海斗", "大輔", "大樹", "翔太", "大輝", "翼", "拓海", "直人", "康平",
				"達也", "駿", "雄大", "亮太", "拓也", "大貴", "亮太", "拓哉", "雄大", "誠", "隆", "茂", "豊", "明", "浩", "進", "勝",
				"洋子", "恵子", "京子", "幸子", "和子", "久美子", "由美子", "裕子", "美智子", "悦子", "智子", "久美子", "陽子", "理恵", "真由美",
				"香織", "恵", "愛", "優子", "智子", "裕美", "真由美", "めぐみ", "美穂", "純子", "美紀", "彩", "美穂", "成美", "沙織", "麻衣",
				"舞", "愛美", "瞳", "彩香", "麻美", "沙織", "麻衣", "由佳", "あゆみ", "友美", "麻美", "裕子", "美香", "恵美", "直美", "由美",
				"陽子", "直子", "未来", "萌", "美咲", "亜美", "里奈", "菜々子", "彩花", "遥", "美咲", "明日香", "真由", "楓", "奈々", "彩花",
				"優花", "桃子", "美咲", "佳奈", "葵", "菜摘", "桃子", "茜", "明美", "京子", "恵子", "洋子", "順子", "典子"
		};

		// ランダム関数で数字を作り、その数字にリスト内数量を掛ける。小数点以下を切り捨てた数字=配列位置の漢字が選択される。
		String myouji = myoujilist[get_random(myoujilist.length)];
		String namae = namaelist[get_random(namaelist.length)];
		String full_name = myouji + " " + namae;
		return full_name;
	}
}

/**
 * コンパレーター処理
 * */
class Comp implements Comparator<Object> {

	private int order = 0; // ソート選択
	private int comparator_position = 0; // 比較する位置
	private int result = 0; // 結果用

	void set_order(int sort, int comparator) {
		order = sort;
		comparator_position = comparator;
	}

	@Override
	public int compare(Object a, Object b) {
		// ここのオブジェクトには二次元配列の１行づつ入ってくるのでその中のデータが、StringとInt混じっているためエラーが起きていた。
		// 一旦受け取ったものをオブジェクト配列としてからその中の比較対象部分のデータを指定することで比較が出来る。
		Object[] obj_a = (Object[]) a;
		Object[] obj_b = (Object[]) b;
		int compare_a = 0;
		int compare_b = 0;

		// ここでは比較結果のみを返す形。
		// コンペア側が並べ替えてくれるのでここでは１か０か－１かを渡すだけ。

		// ３教科の合計を比較する場合はTrue
		if (comparator_position == 1) {
			compare_a = (int) obj_a[6];
			compare_b = (int) obj_b[6];
		} else {
			compare_a = (int) obj_a[8];
			compare_b = (int) obj_b[8];
		}

		// 1なら降順
		if (order == 1) {
			result = Integer.compare(compare_b, compare_a);
		// 2なら昇順
		} else {
			result = Integer.compare(compare_a, compare_b);
		}
		return result;
	}
}