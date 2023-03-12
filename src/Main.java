public class Main {

	public static final int INF_MAX = 999999999;
	public static final int INF_MIN = -999999999;

//	public static final int[][] INITIAL_MATRIX = {
//			{0, 7, 9, 0, 0, 14},
//			{7, 0, 10, 15, 0, 0},
//			{9, 10, 0, 11, 0, 2},
//			{0, 15, 11, 0, 6, 0},
//			{0, 0, 0, 6, 0, 9},
//			{14, 0, 2, 0, 9, 0},
//	};

//	public static final int[][] INITIAL_MATRIX = {
//			{0, 7, 9, 0, 0, 14},
//			{7, 0, 10, 15, 0, 0},
//			{9, 10, 0, 11, 0, -2},
//			{0, 15, 11, 0, 6, 0},
//			{0, 0, 0, 6, 0, 9},
//			{14, 0, 0, 0, 9, 0},
//	};

	public static final int[][] INITIAL_MATRIX = {
			{0, 5, 1, 0},
			{0, 0, 0, 1},
			{0, 1, 0, 6},
			{0, 0, 0, 0}
	};

	public static int s = 0;
	public static int t = 3;

	public static void main(String[] args) {
		Dijkstra.algorithm();
	}
}
