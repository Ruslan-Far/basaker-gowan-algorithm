public class Dijkstra {

	public static int[][] shortestWay;

	public static void algorithm() {
		int d[] = new int[Main.INITIAL_MATRIX.length];
		int v[] = new int[d.length];
		int temp, minindex, min;

		for (int i = 0; i < d.length; i++)
		{
			d[i] = Main.INF_MAX;
			v[i] = 1;
		}
		d[Main.s] = 0;
		do {
			minindex = Main.INF_MAX;
			min = Main.INF_MAX;
			for (int i = 0; i < d.length; i++)
			{
				if ((v[i] == 1) && (d[i] < min))
				{
					min = d[i];
					minindex = i;
				}
			}
			if (minindex != Main.INF_MAX)
			{
				for (int i = 0; i < d.length; i++)
				{
					if (Main.INITIAL_MATRIX[minindex][i] != 0)
					{
						temp = min + Main.INITIAL_MATRIX[minindex][i];
						if (temp < d[i])
						{
							d[i] = temp;
						}
					}
				}
				v[minindex] = 0;
			}
		} while (minindex < Main.INF_MAX);
		System.out.println("Кратчайшие расстояния до вершин:");
		CommonFunctions.printArray(d);
		restorePath(d);
	}

	private static void restorePath(int[] d) {
		int v[] = new int[d.length];
		int begin = Main.s;
		int end = Main.t;
		v[0] = end;
		int k = 1;
		int weight = d[end];

		while (end != begin)
		{
			for (int i = 0; i < d.length; i++)
				if (Main.INITIAL_MATRIX[i][end] != 0)
				{
					int temp = weight - Main.INITIAL_MATRIX[i][end];
					if (temp == d[i])
					{
						weight = temp;
						end = i;
						v[k] = end;
						k++;
					}
				}
		}
		shortestWay = new int[k - 1][2];
		System.out.println("\nВывод кратчайшего пути");
		for (int i = k - 1; i >= 1; i--) {
			shortestWay[begin][0] = v[i];
			shortestWay[begin][1] = v[i - 1];
			begin++;
		}
		CommonFunctions.printMatrix(shortestWay);
	}
}