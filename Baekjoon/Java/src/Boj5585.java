import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj5585 {

	static int ans = 0;
	static int remain = 0;
	static int[] money = {500, 100, 50, 10, 5, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		remain = 1000 - N;
		
		for(int i=0; i<6; i++) {
			if(remain >= money[i]) {
				int num = remain / money[i];
				remain %= (money[i] * num);
				
				ans += num;
			}
		}
		
		System.out.println(ans);
	}
}
