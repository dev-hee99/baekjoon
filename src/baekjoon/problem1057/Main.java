package baekjoon.problem1057;

/**
 * <pre>
 * Desc : 토너먼트
 * </pre>
 * 김지민은 N명이 참가하는 스타 토너먼트에 진출했다.
 * 토너먼트는 다음과 같이 진행된다. 일단 N명의 참가자는 번호가 1번부터 N번까지 배정받는다.
 * 그러고 난 후에 서로 인접한 번호끼리 스타를 한다.
 * 이긴 사람은 다음 라운드에 진출하고, 진 사람은 그 라운드에서 떨어진다. 만약 그 라운드의 참가자가 홀수명이라면, 마지막 번호를 가진 참가자는 다음 라운드로 자동 진출한다.
 * 다음 라운드에선 다시 참가자의 번호를 1번부터 매긴다. 이때, 번호를 매기는 순서는 처음 번호의 순서를 유지하면서 1번부터 매긴다.
 * 이 말은 1번과 2번이 스타를 해서 1번이 진출하고, 3번과 4번이 스타를 해서 4번이 진출했다면, 4번은 다음 라운드에서 번호 2번을 배정받는다.
 * 번호를 다시 배정받은 후에 한 명만 남을 때까지 라운드를 계속 한다.
 *
 * 마침 이 스타 대회에 임한수도 참가했다.
 * 김지민은 갑자기 스타 대회에서 우승하는 욕심은 없어지고, 몇 라운드에서 임한수와 대결하는지 궁금해졌다.
 * 일단 김지민과 임한수는 서로 대결하기 전까지 항상 이긴다고 가정한다.
 * 1 라운드에서 김지민의 번호와 임한수의 번호가 주어질 때, 과연 김지민과 임한수가 몇 라운드에서 대결하는지 출력하는 프로그램을 작성하시오.
 * @author devhee
 * @packageName baekjoon.problem1057
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
        int participantCount = sc.nextInt();    // 전체 참가자 수
        int kimjiminNo = sc.nextInt();          // 김지민 번호
        int imhansooNo = sc.nextInt();          // 임한수 번호

        // 토너먼트 참가자 초기화
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 1; i <= participantCount; i++){
            deque.addLast(i);
        }

        // 전체 참가자 대전 loop
        int round = 0;
        boolean isFight = false;
        Deque<Integer> tempDeque = new LinkedList<>();
        while(!isFight){ // 가능한 모든 라운드가 종료되면 exit
            round++;
            int fightCount = (int) Math.floor(deque.size() / 2.0); // 가능한 fight 횟수
            for(int i = 0; i < fightCount; i++){
                Integer p1 = deque.poll(); // player1
                Integer p2 = deque.poll(); // player2

                // 김지민 임한수 만났을 경우 라운드 종료
                if((p1 == kimjiminNo && p2 == imhansooNo)
                        || (p1 == imhansooNo && p2 == kimjiminNo)){
                    isFight = true;
                    break;
                }

                // p1, p2가 김지민이거나 임한수 일 경우 다음 라운드로 올림(조건. 김지민과 임한수는 대결하기 전까지 항상 이김)
                if(p1 == kimjiminNo ||  p1 == imhansooNo) tempDeque.addLast(p1);
                if(p2 == kimjiminNo ||  p2 == imhansooNo) tempDeque.addLast(p2);

                // p1도 p2도 김지민과 임한수가 아닐 경우 가장 앞에 있는 선수를 승리 처리
                if((p1 != kimjiminNo && p2 != kimjiminNo) && (p1 != imhansooNo && p2 != imhansooNo)) tempDeque.addLast(p1);

                // 부전승
                if(deque.size() == 1) {
                    tempDeque.addLast(deque.poll());
                }
            }

            // deque에 다음 라운드 올리고 tempDeque는 비워준다.
            deque.clear();
            deque.addAll(tempDeque);
            tempDeque.clear();
        }

        // 첫째 줄에 김지민과 임한수가 대결하는 라운드 번호를 출력한다.
        // 만약 서로 대결하지 않을 때는 -1을 출력한다.
        System.out.println(round);
    }
}
