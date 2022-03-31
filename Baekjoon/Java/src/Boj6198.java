import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Stack;

public class Boj6198 {

	public static Stack<Integer> stack = new Stack<>();
	public static long ans;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			
			int height = Integer.parseInt(br.readLine());
			
			while(!stack.isEmpty()) {
				
				if(stack.peek() <= height) {
					// i번째 빌딩보다 낮거나 같은 애들은 빼버린다.
					stack.pop();
				}
				else break;

			}
			ans += stack.size();
			stack.push(height);
		}
		
		bw.write(String.valueOf(ans));
		bw.close();
	}
}
