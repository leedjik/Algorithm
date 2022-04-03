import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3079 {

	public static int N, M;
	public static int[] arr;
	public static long ans, maxTime = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		ans = Long.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			if(maxTime < arr[i]) maxTime = arr[i];
		}
		binarySearch();

		System.out.println(ans);
		
	}
	public static void binarySearch() {
		long left = 0;
		long right = maxTime * M;
		
		
		while(left <= right) {
			long mid = (left + right) / 2;
			long cnt = M;
			
			for(int i=0; i<N; i++) {
				cnt -= (mid / arr[i]);
			}
			
			if(cnt <= 0) {
				// M명 이상 심사 가능.. 시간을 더 줄여보자
				ans = mid;
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
	}
}
