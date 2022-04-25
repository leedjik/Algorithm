import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


public class Boj19591 {

	static Deque<String> dequeOp = new ArrayDeque<>();
	static Deque<Long> dequeNum = new ArrayDeque<>();
	static HashMap<String, Integer> hashMap = new HashMap<>();
	static boolean flag = false;

	public static void main(String[] args) throws IOException{
		
		// 연산자 우선 순위 넣기
		hashMap.put("+", 1);
		hashMap.put("-", 1);
		hashMap.put("*", 2);
		hashMap.put("/", 2);
		
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		// 연산, 숫자 나누기
		makeDeque(input);

		// 
		while(dequeOp.size() > 1) {
						
			// 앞 연산 대상자 꺼내오기
			long frontFirstNum = dequeNum.pollFirst();
			long frontSecondNum = dequeNum.pollFirst();
			String frontOp = dequeOp.pollFirst();
			
			// 뒤 연산 대상자 꺼내오기
			long lastSecondNum = dequeNum.pollLast();
			long lastFirstNum = 0;
			if(dequeNum.isEmpty()) flag = true; // 3 + 2 - 1 일 때, 2를 가져오는 경우
			if(flag == true) lastFirstNum = frontSecondNum;	
			else lastFirstNum = dequeNum.pollLast();
			String lastOp = dequeOp.pollLast();
			
			// 연산자 우선순위 따지기
			int priorityNumfrontOp = hashMap.get(frontOp);
			int priorityNumLastOp = hashMap.get(lastOp);
			
			if(priorityNumfrontOp > priorityNumLastOp) {
				// 앞이 우선순위
				long calValue = calculate(frontFirstNum, frontSecondNum, frontOp);
				dequeNum.addFirst(calValue);
				
				// 뒤는 그대로 다시 넣기
				dequeOp.addLast(lastOp);
				if(flag == false) dequeNum.addLast(lastFirstNum);
				dequeNum.addLast(lastSecondNum);
			}
			else if(priorityNumfrontOp < priorityNumLastOp) {
				// 뒤가 우선순위
				long calValue = calculate(lastFirstNum, lastSecondNum, lastOp);
				dequeNum.addLast(calValue);
				
				// 앞은 그대로 다시 넣기
				dequeOp.addFirst(frontOp);
				if(flag == false) dequeNum.addFirst(frontSecondNum);
				dequeNum.addFirst(frontFirstNum);
			}
			else {
				// 우선순위 같음
				
				// 앞 연산 해보기
				long frontRet = calculate(frontFirstNum, frontSecondNum, frontOp);
				// 뒤 연산 해보기
				long lastRet = calculate(lastFirstNum, lastSecondNum, lastOp);
				
				if(frontRet < lastRet) {
					// 뒤가 더 큼
					dequeNum.addLast(lastRet);
					
					// 앞은 그대로 다시 넣기
					dequeOp.addFirst(frontOp);
					if(flag == false) dequeNum.addFirst(frontSecondNum);
					dequeNum.addFirst(frontFirstNum);
				}
				else {
					// 앞이 더 크거나, 앞뒤 같음
					dequeNum.addFirst(frontRet);
					
					// 뒤는 그대로 다시 넣기
					dequeOp.addLast(lastOp);
					if(flag == false) dequeNum.addLast(lastFirstNum);
					dequeNum.addLast(lastSecondNum);
				}
			}
		}
		// 1개 남은건 그냥 연산하기
		long firstNum = dequeNum.pollFirst();
		if(dequeNum.isEmpty()) {
			// 입력이 1개인 경우
			System.out.println(firstNum);
			return;
		}
		long secondNum = dequeNum.pollFirst();
		String op = dequeOp.pollFirst();

		System.out.println(calculate(firstNum, secondNum, op));
	}
	public static long calculate(long num1, long num2, String op) {
		if(op.equals("+".toString())) return num1 + num2;
		else if(op.equals("-".toString())) return num1 - num2;
		else if(op.equals("/".toString())) return num1 / num2;
		else return num1 * num2;
	}
	public static void makeDeque(String input) {
		int befIdx = 0;

		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '/' || input.charAt(i) == '*') {
				if(i == 0 && input.charAt(i) == '-') continue; // 맨 앞 음수는 제외

				// 숫자 넣기
				dequeNum.addLast(Long.parseLong(input.substring(befIdx, i)));
				befIdx = i + 1;
				// 연산 넣기
				dequeOp.addLast(Character.toString(input.charAt(i)));
			}
		}
		// 마지막 숫자 넣기
		dequeNum.addLast(Long.parseLong(input.substring(befIdx, input.length())));
	}
}
