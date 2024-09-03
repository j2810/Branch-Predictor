

public class Predictor6400 extends Predictor {

	/*
	 * public Predictor6400() {
	 * 
	 * }
	 */

	Table ist = new Table(2024, 2);
	Table ghr = new Table(128, 4);

	public void Train(long address, boolean outcome, boolean predict) {

		int c = (int) (address % 2024);

		int v = (int)address;
		int k = 2, f = 4;
		int d = (((1 << k) - 1) & (v >> (f - 1))); 
		int q = ghr.getInteger(d, 0, 1);
		int st = q^c;
		if (outcome == true) {
			if (ist.getInteger(st, 0, 1) == 3) {
				  return;
			}

			else {
				int p = ist.getInteger(st, 0, 1);
				p++;
				ist.setInteger(st, 0, 1, p);
			}
		} else {
			if (ist.getInteger(st, 0, 1) == 0) {
				return;
			}

			else {
				int p = ist.getInteger(st, 0, 1);
				p--;
				ist.setInteger(st, 0, 1, p);
			}
		}

	}

	public boolean predict(long address) {
		int c = (int) (address % 2024);
		int v = (int)address;
		int k = 2, f = 4;
		int d = (((1 << k) - 1) & (v >> (f - 1))); 
		int q = ghr.getInteger(d, 0, 1);
		int st = q^c;
		
		
		boolean value = ist.getBit(st, 0);

		return value;

	}

}



