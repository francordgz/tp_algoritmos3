package src.main;

public class Constant {
    public static final int NULA = 0;
    public static final double MEDIA = 0.5;
    public static final int SIMPLE = 1;
    public static final int DOBLE = 2;
    public static final int TERCIO = 33;
    static final int MAX_NOMBRE = 50;
    static final int NOT_INT = -1;
    static final int SALIR = 0;

    static public double[][] crearEfectividades() {
        double[][] efectividades = new double[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                efectividades[i][j] = Constant.SIMPLE;
            }
        }

        //0s
        efectividades[3][12] = Constant.NULA;
        efectividades[4][8] = Constant.NULA;
        efectividades[4][10] = Constant.NULA;
        efectividades[7][4] = Constant.NULA;
        efectividades[8][4] = Constant.NULA;
        efectividades[12][14] = Constant.NULA;

        //1/2
        efectividades[0][0] = Constant.MEDIA;
        efectividades[0][2] = Constant.MEDIA;
        efectividades[0][9] = Constant.MEDIA;
        efectividades[1][4] = Constant.MEDIA;
        efectividades[1][5] = Constant.MEDIA;
        efectividades[1][7] = Constant.MEDIA;
        efectividades[1][14] = Constant.MEDIA;
        efectividades[3][2] = Constant.MEDIA;
        efectividades[3][3] = Constant.MEDIA;
        efectividades[3][9] = Constant.MEDIA;
        efectividades[5][0] = Constant.MEDIA;
        efectividades[5][2] = Constant.MEDIA;
        efectividades[5][5] = Constant.MEDIA;
        efectividades[5][11] = Constant.MEDIA;
        efectividades[6][0] = Constant.MEDIA;
        efectividades[6][6] = Constant.MEDIA;
        efectividades[7][1] = Constant.MEDIA;
        efectividades[7][10] = Constant.MEDIA;
        efectividades[7][13] = Constant.MEDIA;
        efectividades[7][14] = Constant.MEDIA;
        efectividades[8][11] = Constant.MEDIA;
        efectividades[9][1] = Constant.MEDIA;
        efectividades[9][2] = Constant.MEDIA;
        efectividades[9][5] = Constant.MEDIA;
        efectividades[9][9] = Constant.MEDIA;
        efectividades[9][13] = Constant.MEDIA;
        efectividades[9][14] = Constant.MEDIA;
        efectividades[10][10] = Constant.MEDIA;
        efectividades[11][7] = Constant.MEDIA;
        efectividades[11][12] = Constant.MEDIA;
        efectividades[12][1] = Constant.MEDIA;
        efectividades[12][9] = Constant.MEDIA;
        efectividades[13][3] = Constant.MEDIA;
        efectividades[13][11] = Constant.MEDIA;
        efectividades[13][12] = Constant.MEDIA;
        efectividades[13][13] = Constant.MEDIA;
        efectividades[14][3] = Constant.MEDIA;
        efectividades[14][11] = Constant.MEDIA;

        // x2
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (efectividades[i][j] != Constant.NULA && efectividades[i][j] != 1 && efectividades[i][j] != Constant.MEDIA) {
                    efectividades[i][j] = Constant.DOBLE;
                }
            }
        }

        return efectividades;
    }
}
