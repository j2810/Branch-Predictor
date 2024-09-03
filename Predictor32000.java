


public class Predictor32000 extends Predictor {

	public Predictor32000() {

	}

	Table ist = new Table(4096, 2);
	Table ghr = new Table(256, 4);

	public void Train(long address, boolean outcome, boolean predict) {

		int c = (int) (address % 4096);

		int v = (int)address;
		int s = 2, t = 4;
		int d = (((1 << s) - 1) & (v >> (t - 1)));
		
		int g = ghr.getInteger(d, 0, 1);
		int st = g^c;
		
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
		int c = (int) (address % 4096);

		int v = (int)address;
		int s = 2, t = 4;
		int d = (((1 << s) - 1) & (v >> (t - 1)));
		
		int g = ghr.getInteger(d, 0, 1);
		int st = g^c;
		boolean value = ist.getBit(st, 0);

		return value;
	}

}
