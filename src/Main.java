import java.util.Arrays;

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

//	public static int[][] initialMatrix = {
//			{0, 5, 1, 0},
//			{0, 0, 0, 0},
//			{0, 1, 0, 0},
//			{0, 0, 0, 0}
//	};

	public static int[][] initialMatrix = {
			{0, 5, 1, 0},
			{0, 0, 0, 1},
			{0, 1, 0, 6},
			{0, 0, 0, 0}
	};

	private static int[][] c = { // пропускная способность
			{0, 4, 2, 0},
			{0, 0, 0, 2},
			{0, 3, 0, 3},
			{0, 0, 0, 0}
	};

	private static int[][] f = new int[c.length][c[0].length]; // запущенные потоки

	public static int s = 0; // начало движения
	public static int t = 3; // конец движения

	private static int v = 3; // мощность

	public static void main(String[] args) {
		int[][] copyInitialMatrix;
		int foundV;
		int e;
		int e1;
		int e2;
		int count;

		copyInitialMatrix = getCopyInitialMatrix();
		foundV = 0;
		count = 0;
		while (foundV < v) {
			System.out.println(++count + " итерация");
			Dijkstra.algorithm();
			if (Dijkstra.shortestWay == null)
				break;
			e1 = findE1();
			System.out.println("e1 = " + e1);
			e2 = findE2();
			System.out.println("e2 = " + e2);
			e = findE(e1, e2, foundV);
			System.out.println("e = " + e);
			foundV += e;
			System.out.println("foundV = " + foundV);
			redraw(e1, e);
			System.out.println("Матрица стоимости");
			CommonFunctions.printMatrix(initialMatrix);
			System.out.println("Матрица запущенных потоков");
			CommonFunctions.printMatrix(f);
			System.out.println("\n\n\n");
		}
		getAnswer(copyInitialMatrix, foundV);
	}

	private static int[][] getCopyInitialMatrix() {
		int[][] copyInitialMatrix;

		copyInitialMatrix = new int[initialMatrix.length][initialMatrix[0].length];
		for (int i = 0; i < copyInitialMatrix.length; i++) {
			for (int j = 0; j < copyInitialMatrix[i].length; j++) {
				copyInitialMatrix[i][j] = initialMatrix[i][j];
			}
		}
		return copyInitialMatrix;
	}

	private static int findE1() {
		int min;
		int[] coords;

		min = INF_MAX;
		for (int i = 0; i < Dijkstra.shortestWay.length; i++) {
			coords = Dijkstra.shortestWay[i];
			if (initialMatrix[coords[0]][coords[1]] > 0) {
				if (c[coords[0]][coords[1]] - f[coords[0]][coords[1]] < min) {
					min = c[coords[0]][coords[1]] - f[coords[0]][coords[1]];
				}
			}
		}
		return min;
	}

	private static int findE2() {
		int min;
		int[] coords;

		min = INF_MAX;
		for (int i = 0; i < Dijkstra.shortestWay.length; i++) {
			coords = Dijkstra.shortestWay[i];
			if (initialMatrix[coords[0]][coords[1]] < 0) {
				if (f[coords[0]][coords[1]] < min) {
					if (f[coords[0]][coords[1]] != 0)
						min = f[coords[0]][coords[1]];
					else
						min = f[coords[1]][coords[0]];
//					System.out.println("min=" + min);
//					System.out.println("coords[0]=" + coords[0]);
//					System.out.println("coords[1]=" + coords[1]);
				}
			}
		}
		return min;
	}

	private static int findE(int e1, int e2, int foundV) {
		int e;

		e = Math.min(e1, e2);
		e = Math.min(e, v - foundV);
		return e;
	}

	private static void redraw(int e1, int e) {
		int[] coords;

		for (int i = 0; i < Dijkstra.shortestWay.length; i++) {
			coords = Dijkstra.shortestWay[i];
			if (initialMatrix[coords[0]][coords[1]] > 0)
				f[coords[0]][coords[1]] += e;
			else {
				if (f[coords[0]][coords[1]] != 0)
					f[coords[0]][coords[1]] -= e;
				else
					f[coords[1]][coords[0]] -= e;
			}
			initialMatrix[coords[1]][coords[0]] = -initialMatrix[coords[0]][coords[1]];
			if (e1 == c[coords[0]][coords[1]]) {
				initialMatrix[coords[0]][coords[1]] = 0;
			}
		}
	}

	private static void getAnswer(int[][] copyInitialMatrix, int foundV) {
		if (foundV == v) {
			System.out.println("Получилось добиться указанной мощности = " + v);
		}
		else {
			System.out.println("Не получилось добиться указанной мощности = " + v);
			System.out.println("Максимальная полученная мощность = " + foundV);
		}
		System.out.println("Матрица запущенных потоков");
		CommonFunctions.printMatrix(f);
		System.out.println("\nМинимальная стоимость = " + getMinCost(copyInitialMatrix));
	}

	private static int getMinCost(int[][] copyInitialMatrix) {
		int minCost;

		minCost = 0;
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[i].length; j++) {
				if (f[i][j] > 0) {
					minCost += f[i][j] * copyInitialMatrix[i][j];
				}
			}
		}
		return minCost;
	}
}
