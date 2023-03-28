package january_assignment_2023_2_07;

class Constant {
	// 問題１・２で使う定数
	/** 配列の要素数  デフォルト 10 */
	static final int DEFAULT_ELEMENTS = 10;
	/** グラフの文字列  デフォルト ＊ */
	static final String DEFAULT_STRING = "＊";
	/** 縦軸の目盛り単位  デフォルト 0.5 */
	static final double DEFAULT_VERTICAL_AXIS_SCALE_UNIT = 0.5;
	/** 横軸最大行数 */
	static final double DEFAULT_HORIZONTAL_AXIS = 10;

	// 問題３・４で使う定数
	static final int DEFAULT_ARRAY = 5; // 問題３のデフォルト配列
	static final int MAX_ARRAYS_NUMBER = 99; // 配列内最大数値
	static final int SUDOKU_MAX_NUMBER = 9; // 数独用最大数値

	// 問題７・８で使用する定数
	// 定数にするにはstaticが必要と知る
	static final int UPPER = 1; // 上方向
	static final int LOWER = 2; // 下方向
	static final int LEFT = 3; // 左方向

}
