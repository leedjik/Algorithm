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
		
		// ������ �켱 ���� �ֱ�
		hashMap.put("+", 1);
		hashMap.put("-", 1);
		hashMap.put("*", 2);
		hashMap.put("/", 2);
		
		// �Է� �ޱ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		// ����, ���� ������
		makeDeque(input);

		// 
		while(dequeOp.size() > 1) {
						
			// �� ���� ����� ��������
			long frontFirstNum = dequeNum.pollFirst();
			long frontSecondNum = dequeNum.pollFirst();
			String frontOp = dequeOp.pollFirst();
			
			// �� ���� ����� ��������
			long lastSecondNum = dequeNum.pollLast();
			long lastFirstNum = 0;
			if(dequeNum.isEmpty()) flag = true; // 3 + 2 - 1 �� ��, 2�� �������� ���
			if(flag == true) lastFirstNum = frontSecondNum;	
			else lastFirstNum = dequeNum.pollLast();
			String lastOp = dequeOp.pollLast();
			
			// ������ �켱���� ������
			int priorityNumfrontOp = hashMap.get(frontOp);
			int priorityNumLastOp = hashMap.get(lastOp);
			
			if(priorityNumfrontOp > priorityNumLastOp) {
				// ���� �켱����
				long calValue = calculate(frontFirstNum, frontSecondNum, frontOp);
				dequeNum.addFirst(calValue);
				
				// �ڴ� �״�� �ٽ� �ֱ�
				dequeOp.addLast(lastOp);
				if(flag == false) dequeNum.addLast(lastFirstNum);
				dequeNum.addLast(lastSecondNum);
			}
			else if(priorityNumfrontOp < priorityNumLastOp) {
				// �ڰ� �켱����
				long calValue = calculate(lastFirstNum, lastSecondNum, lastOp);
				dequeNum.addLast(calValue);
				
				// ���� �״�� �ٽ� �ֱ�
				dequeOp.addFirst(frontOp);
				if(flag == false) dequeNum.addFirst(frontSecondNum);
				dequeNum.addFirst(frontFirstNum);
			}
			else {
				// �켱���� ����
				
				// �� ���� �غ���
				long frontRet = calculate(frontFirstNum, frontSecondNum, frontOp);
				// �� ���� �غ���
				long lastRet = calculate(lastFirstNum, lastSecondNum, lastOp);
				
				if(frontRet < lastRet) {
					// �ڰ� �� ŭ
					dequeNum.addLast(lastRet);
					
					// ���� �״�� �ٽ� �ֱ�
					dequeOp.addFirst(frontOp);
					if(flag == false) dequeNum.addFirst(frontSecondNum);
					dequeNum.addFirst(frontFirstNum);
				}
				else {
					// ���� �� ũ�ų�, �յ� ����
					dequeNum.addFirst(frontRet);
					
					// �ڴ� �״�� �ٽ� �ֱ�
					dequeOp.addLast(lastOp);
					if(flag == false) dequeNum.addLast(lastFirstNum);
					dequeNum.addLast(lastSecondNum);
				}
			}
		}
		// 1�� ������ �׳� �����ϱ�
		long firstNum = dequeNum.pollFirst();
		if(dequeNum.isEmpty()) {
			// �Է��� 1���� ���
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
				if(i == 0 && input.charAt(i) == '-') continue; // �� �� ������ ����

				// ���� �ֱ�
				dequeNum.addLast(Long.parseLong(input.substring(befIdx, i)));
				befIdx = i + 1;
				// ���� �ֱ�
				dequeOp.addLast(Character.toString(input.charAt(i)));
			}
		}
		// ������ ���� �ֱ�
		dequeNum.addLast(Long.parseLong(input.substring(befIdx, input.length())));
	}
}
