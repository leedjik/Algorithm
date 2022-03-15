import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj2781_TopDown {

	static long dp[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dp = new long[N + 1];
		
		System.out.println(fibbo(N));
	}
	
	public static long fibbo(int n) {
		if(n <= 1) return n;
		else if(dp[n] != 0) return dp[n];
		
		dp[n] = fibbo(n - 1) + fibbo(n - 2);
		
		return dp[n];
	}
}
