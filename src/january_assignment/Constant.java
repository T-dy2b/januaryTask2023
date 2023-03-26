package january_assignment;

class Constant {
	// 共通使用
	/** デフォルト無し */
	static final String NOT_DEFAULT = "";
	/** 正規表現一致のみ受け付ける */
	static final boolean REGEX_MATCH = true;
	/** 正規表現不一致のみ受け付ける */
	static final boolean REGEX_MISMATCH = false;
	
	// 問題１・２で使う定数
	/** 配列の要素数  デフォルト 10 */
	static final int DEFAULT_ELEMENTS = 10;
	/** グラフの文字列  デフォルト ＊ */
	static final String DEFAULT_STRING = "＊";
	/** 縦軸の目盛り単位  デフォルト 0.5 */
	static final double DEFAULT_VERTICAL_AXIS_SCALE_UNIT = 0.5;
	/** 横軸最大行数 */
	static final double DEFAULT_HORIZONTAL_AXIS = 10;


	// 問題１・２で使用する定数
	/** 入力規制外の場合に出すメッセージ 「1～99の数字を入力してください」 */
	static final String MESSAGE1 = "1～99の数字を入力してください";
	/** 入力規制 「1～99」*/
	static final String REGULATION1 = "[1-9][0-9]{0,1}";
	/** 入力受付内容 「配列の要素数(デフォルト%s)：」*/
	static final String QUESTION1 = "配列の要素数(デフォルト%s)：";
	/** 入力規制外の場合に出すメッセージ 「1文字で別の文字または記号に変えてください」*/
	static final String MESSAGE2 = "1文字で別の文字または記号に変えてください";
	/** 入力規制 「1文字で別の文字または記号」*/
	static final String REGULATION2 = "(.*\s.*|.*\\h.*)|^[ -~｡-ﾟ]+$|.{2,}";
	/** 入力受付内容 「グラフ文字列(デフォルト%s)：」*/
	static final String QUESTION2 = "グラフ文字列(デフォルト%s)：";
	/** 入力規制外の場合に出すメッセージ 「0.1～1の数字を入力してください」*/
	static final String MESSAGE3 = "0.1～1の数字を入力してください";
	/** 入力規制 「0.1～1の数字を入力」*/
	static final String REGULATION3 = "^[0][\\.][1-9]{1}|^[1]";
	/** 入力受付内容 「縦軸の目盛り単位(デフォルト%.1f)：」*/
	static final String QUESTION3 = "縦軸の目盛り単位(デフォルト%.1f)：";

	// 問題２で使用する定数
	/** 入力規制外の場合に出すメッセージ 「1～3の数字を入力してください」*/
	static final String MESSAGE4 = "1～3の数字を入力してください";
	/** 入力規制 「1～3の数字を入力」*/
	static final String REGULATION4 = "[1-3]";
	/** 入力受付内容 「ソート： 1. No.順  2. 昇順  3. 降順」*/
	static final String QUESTION4 = "ソート： 1. No.順  2. 昇順  3. 降順\n";

	// 問題３・４で使う定数
	/** 問題３のデフォルト配列 5 */
	static final int DEFAULT_ARRAY = 5;
	/** 配列内最大数値 99 */
	static final int MAX_ARRAYS_NUMBER = 99;
	/** 数独用最大数値 9 */
	static final int SUDOKU_MAX_NUMBER = 9;
	
	// 問題３で使用する定数
	/** 入力受付内容 「配列の大きさを入力してください \n例 5   5×5 になります : デフォルト5」*/
	static final String QUESTION5 = "配列の大きさを入力してください \n例 5   5×5 になります : デフォルト5\n";
	
	//問題５・６で使用する定数
	/** 教科毎の配列群 「"国語", "数学", "英語", "理科", "社会" 」*/
	static final String[] ALL_SUBJECTS = { "国語", "数学", "英語", "理科", "社会" };
	/** 入力規制外の場合に出すメッセージ 「0～100の数字を入力してください」*/
	static final String MESSAGE6 = "0～100の数字を入力してください";
	/** 入力規制 「0～100の数字を入力」*/
	static final String REGULATION6 ="[0-9]$|[1-9][0-9]$|[1][0][0]$";
	/** 入力受付内容 「%s : 」教科配列群から1個づつ表示用*/
	static final String QUESTION6 = "%s : ";
	/** 入力規制外の場合に出すメッセージ 「1か2の数字を入力してください」 */
	static final String MESSAGE7 = "1か2の数字を入力してください";
	/** 入力規制 「１か２」*/
	static final String REGULATION7 ="[1-2]";
	/** 入力受付内容 「3教科または5教科どちらの合計で順位付けしますか？： 1.「 3教科 」  2.「 5教科 」」 */
	static final String QUESTION7 = "\n3教科または5教科どちらの合計で順位付けしますか？： 1.「 3教科 」  2.「 5教科 」\n";
	/** 入力受付内容 「ソート： 1. 1位から表示  2. 6位から表示」 */
	static final String QUESTION8 = "ソート： 1. 1位から表示  2. 6位から表示\n";
	
	
	// 問題７・８で使用する定数
	/** 入力規制外の場合に出すメッセージ 「2から99の数値を入力してください」*/
	static final String MESSAGE9 = "2から99の数値を入力してください";
	/** 入力規制 「2～99の数字を入力」*/
	static final String REGULATION9 = "[2-9]|[1-9][0-9]";
	/** 入力受付内容 「三角形の段数を入力してください：」*/
	static final String QUESTION9 = "三角形の段数を入力してください：";
	/** 入力規制外の場合に出すメッセージ 「1から4の数値を入力してください」*/
	static final String MESSAGE10 = "1から4の数値を入力してください";
	/** 入力規制 「1～4の数字を入力」*/
	static final String REGULATION10 = "[1-4]";
	/** 入力受付内容 「三角形の向きを選択してください 1: 上  2: 下  3: 左  4: 右」*/
	static final String QUESTION10 = "三角形の向きを選択してください \n1: 上  2: 下  3: 左  4: 右 \n";
	/** 印字文字 「*」*/
	static final String ASTERISK_PRINT = "*";
	/** 印字文字 「-」*/
	static final String DIVIDING_LINE = "-";
	
	
	// 問題７・８で使用する定数
	/** 三角形上方向 */
	static final int UPPER = 1; 
	/** 三角形下方向 */
	static final int LOWER = 2; 
	/** 三角形左方向 */
	static final int LEFT = 3; 
	
	
	// 問題９・１０・１０EXで使用する定数
	// 問題９
	/** 入力規制外の場合に出すメッセージ 「1から13の数値を入力してください」*/
	static final String MESSAGE11 = "1から13の数値を入力してください";
	/** 入力規制 「1～13の数字を入力」*/
	static final String REGULATION11 = "[1-9]|[1][0-3]";
	/** 入力受付内容 「ピラミッドの段数を入力してください」*/
	static final String QUESTION11 = "ピラミッドの段数を入力してください\n";

	// 問題１０
	/** 入力規制外の場合に出すメッセージ 「1から5の数値を入力してください」*/
	static final String MESSAGE12 = "1から5の数値を入力してください";
	/** 入力規制 「1～5の数字を入力」*/
	static final String REGULATION12 = "[1-5]";
	/** 入力受付内容 「縦横複数表示する量を1～5で入力してください」*/
	static final String QUESTION12 = "縦横複数表示する量を1～5で入力してください\n";
	
	// 問題１０EX
	/** 入力受付内容 「ダイヤの段数を入力してください」*/
	static final String QUESTION13 = "ダイヤの段数を入力してください\n";

	
}
