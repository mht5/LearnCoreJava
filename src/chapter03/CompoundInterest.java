package chapter03;

/**
 * 二维数组
 * @author mhts
 * @date 2018年6月15日
 */
public class CompoundInterest {
	public static void main(String[] args) {
		final double START_RATE = 10;
		final int NRATES = 6;
		final int NYEARS = 10;
		
		double[] interestRate = new double[NRATES];
		for (int i = 0; i < interestRate.length; i++) {
			interestRate[i] = (START_RATE + i) / 100.0;
		}
		double[][] balances = new double[NYEARS][NRATES];
		for (int i = 0; i < balances[0].length; i++) {
			balances[0][i] = 10000;
		}
		for (int i = 1; i < balances.length; i++) {
			for (int j = 0; j < balances[i].length; j++) {
				double oldBalance = balances[i - 1][j];
				double interest = oldBalance * interestRate[j];
				balances[i][j] = oldBalance + interest;
			}
		}
		for (int j = 0; j < interestRate.length; j++) {
			System.out.printf("%11.0f%%", 100 * interestRate[j]);
		}
		System.out.println();
		for (double[] row : balances) {
			for (double b : row) {
				System.out.printf("%10.2f", b);
			}
			System.out.println();
		}
	}
}
