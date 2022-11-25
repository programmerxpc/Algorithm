package zcy.recur;

/**
 * @ClassName Islands_220919
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-19 15:47
 * @Version 1.0
 */
public class Islands_220919 {

    public static void main(String[] args) {
        int[][] m = {
                {0, 0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        System.out.println(countIslands(m));
    }

    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length; // 行
        int M = m[0].length; // 列
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {
                    res++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    private static void infect(int[][] m, int i, int j, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }

}
