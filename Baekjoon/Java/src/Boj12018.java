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
		
		// (1) 매 과목마다 내림차순 정렬 -> L번째 점수 다른 List에 넣기
		//inputFunc();
		
			while(N-- > 0) {
			
			//System.out.println("N:"+N);
			st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			//System.out.println("P:"+P+", L:"+L);
			
			inputArray.clear();
			st = new StringTokenizer(br.readLine());
			
			// 각 과목에 다른 학생 마일리지 입력
			while(P-- > 0) {
				int tmp = Integer.parseInt(st.nextToken());
				inputArray.add(tmp);
				//inputArray.add(Integer.parseInt(st.nextToken()));
			}
			
			// inputArray 내림차순 정렬
			Collections.sort(inputArray, Collections.reverseOrder());
			
			// L번째 점수 ascendingSortList에 넣기
			if(inputArray.size() >= L) {
				ascendingSortedList.add(inputArray.get(L - 1));
			}
			else {
				// 해당 과목 신청인원 L보다 작아서, 마일리지 1점만 넣어도 된다.
				ascendingSortedList.add(1);
			}
		}
		
		
		// (2) list를 오름차순 정렬
		Collections.sort(ascendingSortedList);
		
		// (3) M만큼 깍아가면서 과목수 ++
		for(int num : ascendingSortedList) {
			M -= num;
			if(M < 0) break;
			
			ans++;
		}
		System.out.println(ans);
	}
	
	/*
	 * ascendingSortedList 완성시키는 함수
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
			
			// 각 과목에 다른 학생 마일리지 입력
			while(P-- > 0) {
				int tmp = Integer.parseInt(st.nextToken());
				inputArray.add(tmp);
				//inputArray.add(Integer.parseInt(st.nextToken()));
			}
			
			// inputArray 내림차순 정렬
			Collections.sort(inputArray, Collections.reverseOrder());
			
			// L번째 점수 ascendingSortList에 넣기
			if(inputArray.size() >= L) {
				int canInput = inputArray.get(L - 1);
				if(canInput <= 36)
					ascendingSortedList.add(inputArray.get(L - 1));
			}
			else {
				// 해당 과목 신청인원 L보다 작아서, 마일리지 1점만 넣어도 된다.
				ascendingSortedList.add(1);
			}
		}
	}
}
