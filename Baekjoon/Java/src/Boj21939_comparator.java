import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj21939_comparator {

	public static int N, M;
	public static TreeSet<Pair> asc = new TreeSet<Pair>(new AscendingComparator());
	public static Map<Integer,Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			asc.add(new Pair(P, L));
			map.put(P, L);
		}
		
		M = Integer.parseInt(br.readLine());
		
		for(int j=0; j<M; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
						
			if(cmd.equals("recommend")) {
				// recommend x
				int x = Integer.parseInt(st.nextToken());
				
				if(x == 1) {
					//가장 어려운 문제
					System.out.println(asc.last().P);
				}
				else {
					// 가장 쉬운 문제
					System.out.println(asc.first().P);
				}
			} 
			else if(cmd.equals("add")){
				// add P L
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				
				asc.add(new Pair(P, L));
				map.put(P, L);
			}
			else {
				//solved P
				int P = Integer.parseInt(st.nextToken());
				int L = map.get(P);
				
				asc.remove(new Pair(P, L));
				map.remove(P);
			}
		}
	}
}

//Comparator 비교
class AscendingComparator implements Comparator<Pair>{
	
	@Override
	public int compare(Pair a, Pair b) {
		if(a.L < b.L) return -1;
		else if(a.L == b.L) {
			//가장 쉬운 문제가 여러개라면
			//문제 번호 오름차순 정렬
			if(a.P < b.P) return -1;
			else if(a.P == b.P) return 0;
			else return 1;
		}
		else return 1;
	}
}
/*
class DescendingComparator implements Comparator<Pair>{
	
	@Override
	public int compare(Pair a, Pair b) {
		if(a.L < b.L) return 1;
		else if(a.L == b.L) {
			//가장 어려운 문제가 여러 개라면
			//문제 번호 내림차순
			if(a.P < b.P) return 1;
			else if(a.P == b.P) return 0;
			else return -1;
		}
		else return -1;
	}
}
*/
class Pair{
	int P, L;
	
	Pair(int p, int l){
		this.P = p;
		this.L = l;
	}
}