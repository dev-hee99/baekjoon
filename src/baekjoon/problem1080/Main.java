package baekjoon.problem1080;

/**
 * <pre>
 * Desc : 행렬
 * 0과 1로만 이루어진 행렬 A와 행렬 B가 있다. 이때, 행렬 A를 행렬 B로 바꾸는데 필요한 연산의 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 * 행렬을 변환하는 연산은 어떤 3×3크기의 부분 행렬에 있는 모든 원소를 뒤집는 것이다. (0 → 1, 1 → 0)
 * </pre>
 *
 * @author devhee
 * @packageName baekjoon.problem1080
 * @date 2026-03-26
 *
 * <pre>
 * << 개정이력 >>
 * 수정일        수정자     수정내용
 * ----------  ---------  -------------------------------
 * 2026-03-26  devhee     최초 생성
 * </pre>
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 입력 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 세로 길이
        int m = sc.nextInt(); // 가로 길이
        
        int[][] array1 = new int[n][m];
        int[][] array2 = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) array1[i][j] = line.charAt(j) - '0';
        }
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) array2[i][j] = line.charAt(j) - '0';
        }

        int count = 0;
        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                // 왼쪽 상단 값이 다를 때만 뒤집는다.
                if (array1[i][j] != array2[i][j]) {
                    flip(array1, i, j);
                    count++;
                }
            }
        }

        if (isSameMatrix(array1, array2)) {
            System.out.println(count);
        } else{
            System.out.println(-1);
        }
    }

    // 범위에 해당하는 3x3 모든 원소 뒤집기
    public static void flip(int[][] matrix, int r, int c) {
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                matrix[i][j] = 1 - matrix[i][j]; // 0->1, 1->0 변환
            }
        }
    }

    // 결과 행렬과 같은지 여부
    public static boolean isSameMatrix(int[][] a, int[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
}
