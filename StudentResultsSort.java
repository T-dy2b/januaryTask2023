package january_assignment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/** 問題６ */
public class StudentResultsSort {

	public static void main(String[] args) {
		try {
			ColumnChartQuestion1 ccq1 = new ColumnChartQuestion1(); // 問題１のインスタンス
			Scanner sc = new Scanner(System.in); // スキャナー機能
			StudentResultsSort srs = new StudentResultsSort(); // インスタンス
			Comp comp = new Comp(); // コンパレーターインスタンス
			Object[][] testData = new Object[6][10]; // 生徒の名前と各種教科、平均と合計を入れる配列生成
			
			// randomで名前を生成して1列目に格納、各教科得点入力受付
			srs.getRandomNameGeneration(testData, ccq1, sc);			

			// 各教科毎の平均点算出表示
			srs.getEachAveragePointIndicates(testData);

			// 順位付けの基準を選択
			int averageSelect = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION7, Constant.MESSAGE7, Constant.QUESTION7,
							Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));

			// ソートをかけて昇順か降順かを選択受付
			int sortSelect =  Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION7, Constant.MESSAGE7, Constant.QUESTION8,
					Constant.NOT_DEFAULT, Constant.REGEX_MATCH, sc));

			// ソートをかける
			comp.setOrder(sortSelect, averageSelect);
			Arrays.sort(testData, comp);

			// ソート後の表示と順位を表示
			srs.getStudentResult(testData, sortSelect);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * 6人分結果取得
	 * @param testData 6人分生徒の名前と点数配列
	 * @param sortSelect ソート選択結果
	 * */
	public void getStudentResult(Object[][] testData, int sortSelect) throws Exception {
		System.out.println("順位 名前               国語    数学    英語    理科    社会  3教科合計 平均  5教科合計 平均");
		
		// studenの二次元配列の中から各データを取り出して表示する
		for (int i = 0; i < testData.length; i++) {
			
			// ソートが1で降順なので上から順に順位を出す
			if (sortSelect == 1) {
				System.out.printf("%d位 ", (i + 1));
			
			// ソートが2なら昇順となるので上から最下位を表示していく
			} else {
				System.out.printf("%d位 ", (testData.length - i));
			}
			
			// 名前を表示
			System.out.printf("%s", testData[i][0]);
			
			// 名前の後ろに入れるスペース判定用に変数に格納
			String name = (String) testData[i][0];

			// ここで名前の文字列量でスペースの量を変えて数字の位置を合わせてます。
			switch (name.length()) {
			case 3 -> System.out.printf("%11s", " ");
			case 4 -> System.out.printf("%9s", " ");
			case 5 -> System.out.printf("%7s", " ");
			case 6 -> System.out.printf("%5s", " ");
			case 7 -> System.out.printf("%3s", " ");
			}

			// 名前以降の点数を表示する処理
			for (int j = 1; j < 10; j++) {
				System.out.printf("%8d", testData[i][j]);
			}
			System.out.println("");
		}
	}


	/**
	 * 各教科平均点取得
	 * @param testData 6人分生徒の名前と点数配列
	 * */
	public void getEachAveragePointIndicates(Object[][] testData) throws Exception {
		int average = 0;

		for (int i = 0; i < 5; i++) {
			
			// 各生徒の点数を足していき平均点を割り出してます。
			for (int j = 0; j < 6; j++) {
				average += (int) testData[j][i + 1];
			}
			// 平均を出す
			average = average / 6;
			
			System.out.printf("%sの平均点は%d\n", Constant.ALL_SUBJECTS[i], average);
			average = 0; // 平均を一度出したら次に行くときにはリセットする
		}
	}

	/**
	 * 名前生成点数入力結果取得
	 * @param testData 6人分生徒の名前と点数配列
	 * @param ccq1 問題１のインスタンス
	 * @param sc スキャナー機能
	 * */
	public void getRandomNameGeneration(Object[][] testData, ColumnChartQuestion1 ccq1, Scanner sc) throws Exception {
		for (int studentPosition = 0; studentPosition < 6; studentPosition++) {
			
			// ランダムで名前を生成
			testData[studentPosition][0] = setCreateRandomName();
			
			System.out.printf("%sさんの各テスト点数を数値で入力してください\n", (String) testData[studentPosition][0]);

			// 各教科の点数の入力を受け付ける
			for (int j = 1; j < 6; j++) {
				// 教科毎の配列群
				String subject = Constant.ALL_SUBJECTS[j-1];
				
				// 要素数入力受付・規制処理
				testData[studentPosition][j] = Integer.parseInt(ccq1.getRequiredInformation(Constant.REGULATION6, Constant.MESSAGE6, Constant.QUESTION6,
						subject, Constant.REGEX_MATCH, sc));
			}

			// 3,5教科合計と平均算出
			getSumAndAverageCalculation(testData, studentPosition);
		}
	}

	/**
	 * 3・5教科合計平均算出結果取得
	 * @param	testData 6人分生徒の名前と点数配列
	 * @param   position 生徒位置
	 * @apiNote 3教科と5教科の合計と平均算出結果取得
	 * */
	public void getSumAndAverageCalculation(Object[][] testData, int position) throws Exception {
		
		// 3教科合計と平均算出
		int sum3Score = 0;
		int average3Score = 0;

		// 5教科合計と平均算出
		int sum5Score = 0;
		int average5Score = 0;

		// それぞれの点数を合計していく
		for (int j = 1; j <= 5; j++) {
			sum5Score += (int) testData[position][j];

			// 3教科の場合は3以上は足さない
			if (j <= 3) {
				sum3Score += (int) testData[position][j];
			}
		}

		// 3教科と5教科の合計と平均を変数に格納する
		average3Score = sum3Score / 3;
		average5Score = sum5Score / 5;
		testData[position][6] = sum3Score;
		testData[position][7] = average3Score;
		testData[position][8] = sum5Score;
		testData[position][9] = average5Score;
	}

	/**
	 * random数値取得
	 * @param	elementary 乱数の設定値
	 * @return ramdom 乱数生成結果
	 * */
	public int getRandom(int elementary) {
		int ramdom = (int) Math.floor(Math.random() * elementary);
		return ramdom;
	}

	/**
	 * ランダム名前取得 
	 * @return fullName 苗字と名前
	 */
	public String setCreateRandomName() throws Exception {

		String[] myoujiList = { "佐藤", "鈴木", "高橋", "田中", "渡辺", "伊藤", "山本", "中村", "小林", "加藤", "吉田", "山田", "佐々木", "山口",
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
		String[] namaeList = { "大輔", "誠", "直樹", "亮", "剛", "大介", "学", "健一", "健", "哲也", "聡", "健太郎", "洋平", "淳", "竜也",
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
		String myouji = myoujiList[getRandom(myoujiList.length)];
		String namae = namaeList[getRandom(namaeList.length)];
		String fullName = myouji + " " + namae;
		return fullName;
	}
}

/**
 * コンパレーター処理
 * */
class Comp implements Comparator<Object> {

	private int sortOrder = 0; // ソート選択
	private int comparatorPosition = 0; // 比較する位置
	private int result = 0; // 結果用

	void setOrder(int sort, int comparator) {
		sortOrder = sort;
		comparatorPosition = comparator;
	}

	@Override
	public int compare(Object row2, Object row1) {
		// ここのオブジェクトには二次元配列の１行づつ入ってくるのでその中のデータが、StringとInt混じっているためエラーが起きていた。
		// 一旦受け取ったものをオブジェクト配列としてからその中の比較対象部分のデータを指定することで比較が出来る。
		Object[] objA = (Object[]) row1;
		Object[] objB = (Object[]) row2;
		int compareA = 0;
		int compareB = 0;

		// ここでは比較結果のみを返す形。
		// コンペア側が並べ替えてくれるのでここでは１か０か－１かを渡すだけ。

		// ３教科の合計を比較する場合はTrue
		if (comparatorPosition == 1) {
			compareA = (int) objA[6];
			compareB = (int) objB[6];
		} else {
			compareA = (int) objA[8];
			compareB = (int) objB[8];
		}

		// 1なら降順
		if (sortOrder == 1) {
			result = Integer.compare(compareA, compareB);
			
		// 2なら昇順
		} else {
			result = Integer.compare(compareB, compareA);
		}
		return result;
	}
}