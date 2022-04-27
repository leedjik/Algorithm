import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;



public class Boj12018 {

	static ArrayList<Integer> inputArray = new ArrayList<>();
	static ArrayList<Integer> ascendingSortedList = new ArrayList<>(); 
	static int N, M, P, L, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// (1) �� ���񸶴� �������� ���� -> L��° ���� �ٸ� List�� �ֱ�
		//inputFunc();
		
			while(N-- > 0) {
			
			//System.out.println("N:"+N);
			st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			//System.out.println("P:"+P+", L:"+L);
			
			inputArray.clear();
			st = new StringTokenizer(br.readLine());
			
			// �� ���� �ٸ� �л� ���ϸ��� �Է�
			while(P-- > 0) {
				int tmp = Integer.parseInt(st.nextToken());
				inputArray.add(tmp);
				//inputArray.add(Integer.parseInt(st.nextToken()));
			}
			
			// inputArray �������� ����
			Collections.sort(inputArray, Collections.reverseOrder());
			
			// L��° ���� ascendingSortList�� �ֱ�
			if(inputArray.size() >= L) {
				ascendingSortedList.add(inputArray.get(L - 1));
			}
			else {
				// �ش� ���� ��û�ο� L���� �۾Ƽ�, ���ϸ��� 1���� �־ �ȴ�.
				ascendingSortedList.add(1);
			}
		}
		
		
		// (2) list�� �������� ����
		Collections.sort(ascendingSortedList);
		
		// (3) M��ŭ ��ư��鼭 ����� ++
		for(int num : ascendingSortedList) {
			M -= num;
			if(M < 0) break;
			
			ans++;
		}
		System.out.println(ans);
	}
	
	/*
	 * ascendingSortedList �ϼ���Ű�� �Լ�
	 */
	
	public static void inputFunc() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(N-- > 0) {
			
			System.out.println("N:"+N);
			st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			System.out.println("P:"+P+", L:"+L);
			
			inputArray.clear();
			st = new StringTokenizer(br.readLine());
			
			// �� ���� �ٸ� �л� ���ϸ��� �Է�
			while(P-- > 0) {
				int tmp = Integer.parseInt(st.nextToken());
				inputArray.add(tmp);
				//inputArray.add(Integer.parseInt(st.nextToken()));
			}
			
			// inputArray �������� ����
			Collections.sort(inputArray, Collections.reverseOrder());
			
			// L��° ���� ascendingSortList�� �ֱ�
			if(inputArray.size() >= L) {
				int canInput = inputArray.get(L - 1);
				if(canInput <= 36)
					ascendingSortedList.add(inputArray.get(L - 1));
			}
			else {
				// �ش� ���� ��û�ο� L���� �۾Ƽ�, ���ϸ��� 1���� �־ �ȴ�.
				ascendingSortedList.add(1);
			}
		}
	}
}
