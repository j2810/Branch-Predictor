public class Predictor2400 extends Predictor {
    public Predictor2400() {
    }

    Table ist = new Table(1024, 2);
    Table ghr = new Table(64, 4);
    {

        for (int i = 0; i < 1024; i++) {
            ist.setBit(i, 0, true);
            ist.setBit(i, 1, true);
        }

        for (int i = 0; i < 64; i++) {
            ghr.setBit(i, 0, true);
            ghr.setBit(i, 1, true);
            ghr.setBit(i, 2, true);
            ghr.setBit(i, 3, true);
        }
    }

    public void Train(long address, boolean outcome, boolean predict) {
        int c = (int) (address % 1024);
        int d = (int) (address % 64);
        int x_t = ghr.getInteger(d, 0, 3);
        int st = x_t ^ d;
        boolean value = ist.getBit(st, 0);
        if (outcome == true) {
            if (ist.getInteger(st, 0, 1) == 3) {
                return;
            } else {
                int p = ist.getInteger(st, 0, 1);
                p++;
                ist.setInteger(st, 0, 1, p);
            }
        } else {
            if (ist.getInteger(st, 0, 1) == 0) {
                return;
            } else {
                int p = ist.getInteger(st, 0, 1);
                p--;
                ist.setInteger(st, 0, 1, p);
            }
        }

    }

    public boolean predict(long address) {
        int c = (int) (address % 1024);
        int d = (int) (address % 64);
        int x_t = ghr.getInteger(d, 0, 3);
        int st = x_t ^ d;
        boolean value = ist.getBit(st, 0);

        return value;
    }
}