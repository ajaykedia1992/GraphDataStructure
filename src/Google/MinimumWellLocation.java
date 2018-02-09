package Google;

public class MinimumWellLocation {

	public static void main(String[] args) {
		int[][] matrix = { { 0, 2, 1, 0 }, { 2, 2, 0, 2 }, { 2, 1, 0, 2 }, { 2, 0, 0, 2 }, { 2, 2, 1, 0 } };
		MinimumWellLocation m = new MinimumWellLocation();
		int[][] houseList = m.findHouse(matrix);
		m.calculateDistance(matrix, houseList);
	}

	private void calculateDistance(int[][] matrix, int[][] houseList) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (isSafe(matrix, i, j) || matrix[i][j] == 1) {
					continue;
				} else {
					calculateMinimumDistance(matrix, houseList, i, j);
				}
			}
		}

	}

	public boolean isSafe(int[][] matrix, int i, int j) {
		if (matrix[i][j] == 0) {
			return false;
		}
		return true;
	}

	private void calculateMinimumDistance(int[][] matrix, int[][] houseList, int i, int j) {
		if ((i >= 0 && j >= 0) || (i < matrix.length && j < matrix[0].length)) {
			if (isSafe(matrix, i, j)) {
				calculateMinimumDistance(matrix, houseList, i, j + 1);
				calculateMinimumDistance(matrix, houseList, i + 1, j);
				calculateMinimumDistance(matrix, houseList, i, j - 1);
				calculateMinimumDistance(matrix, houseList, i - 1, j);
			}
		}
	}

	private int[][] findHouse(int[][] matrix) {
		StringBuffer house = new StringBuffer();
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					count++;
					house.append("{" + i + "," + j + "}-");
				}
			}
		}
		house.deleteCharAt(house.length() - 1);
		System.out.println(house);
		String[] houseSplit = house.toString().split("-");
		int[][] houseList = new int[houseSplit.length][2];
		for (int i = 0; i < houseSplit.length; i++) {
			String[] split1 = houseSplit[i].split(",");
			StringBuffer part1 = new StringBuffer(split1[0]).deleteCharAt(0);
			StringBuffer part2 = new StringBuffer(split1[1]).deleteCharAt(split1[1].length() - 1);
			houseList[i][0] = Integer.parseInt(new String(part1));
			houseList[i][1] = Integer.parseInt(new String(part2));
		}
		/*
		 * for(int i = 0;i<houseList.length; i++) { System.out.println(houseList[i][0] +
		 * "-" + houseList[i][1]); }
		 */
		return houseList;
	}

}
