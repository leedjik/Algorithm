import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2302 {

	public static int N, M;
	public static int[] fibo;
	public static int befIdx, idx, num;
	public static int ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		fibo = new int[N + 1];
		befIdx = 1;
		ans = 1;
		
		
		for(int i=0; i<M; i++) {
			idx = Integer.parseInt(br.readLine());
			
			// [befIdx, idx) 범위의 방법 수 계산
			num = idx - befIdx;
			ans *= FiboDp(num);
			befIdx = idx + 1;
		}

		// [befIdx, N+1] 범위의 방법 수 계산
		num = N - befIdx + 1;
		ans *= FiboDp(num);
				
		System.out.println(ans);
	}
	
	public static int FiboDp(int num) {
		
		if(num <= 1) return 1;
		if(fibo[num] != 0) return fibo[num];
		
		return fibo[num] = FiboDp(num - 1) + FiboDp(num - 2);
	}
}
