import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj2781_BottomUp {

	static long dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new long[N + 1];
		
		System.out.println(fibbo(N));
	}

	public static long fibbo(int n) {
		dp[0] = 0;
		dp[1] = 1;
		
		for(int i=2; i<=n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		return dp[n];
	}
}
