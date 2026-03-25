package baekjoon.problem9012;

/**
 * <pre>
 * Desc : 괄호
 * 괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다.
 * 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다.
 * 여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.
 * </pre>
 *
 * @author devhee
 * @packageName baekjoon.problem9012
 * @date 2026-03-25
 *
 * <pre>
 * << 개정이력 >>
 * 수정일        수정자     수정내용
 * ----------  ---------  -------------------------------
 * 2026-03-25  devhee     최초 생성
 * </pre>
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        List<String> lines = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            String line =  sc.next();
            lines.add(line);
        }

        // 결과 출력
        lines.forEach(line -> System.out.println(checkReturnYesOrNo(line)));
    }

    public static String checkReturnYesOrNo(String line) {
        List<String> split = Arrays.asList(line.split(""));
        // 짝수가 아니거나 괄호 개수가 다르면
        if((split.size() % 2 != 0) ||
                (getCharCount(line, "(") != getCharCount(line, ")"))){
            return "NO";
        }

        boolean isCheck = false;
        int openCount = 0;
        int closeCount = 0;
        for(String s : split){
            if(s.equals("(")) openCount++;
            if(s.equals(")")) closeCount++;
            // closeCount는 openCount보다 클 수 없음.
            if(closeCount > openCount) {
                break;
            }
        }
        if(openCount == closeCount) isCheck = true;

        return isCheck ? "YES" : "NO";
    }

    // 원하는 문자열의 개수를 return
    public static int getCharCount(String line, String c){
        int count = 0;
        for(String s : line.split("")){
            if(s.equals(c)) count++;
        }
        return count;
    }
}
