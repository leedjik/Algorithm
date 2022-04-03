import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj9081 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String input = br.readLine();
			char[] arr = input.toCharArray();
			
			//(1) ���������� i ã��
			int i = arr.length - 1;
			while(i > 0 && (arr[i - 1] >= arr[i])) i--;
			
			//(2) i <= 0 �̸� ���������� ����.
			if(i <= 0) {
				System.out.println(arr);
				continue;
			}
			
			//(3) arr[i-1]�� �¹ٲ� arr[i-1]���� ū arr[j] ã��
			int j = arr.length - 1;
			while(arr[j] <= arr[i-1]) j--;
			
			swap(arr, i-1, j);
			
			//(4)i���� ������������ �����ϱ�	
			Arrays.sort(arr, i, arr.length);
			
			System.out.println(arr);
		}
	}
	public static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
