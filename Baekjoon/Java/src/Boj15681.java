import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15681 {

	public static int N, R, Q;
	public static int[] size;
	public static ArrayList<Integer>[] arrayList;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		size = new int[N + 1];
		arrayList = new ArrayList[N + 1];
		for(int i=1; i<=N; i++) {
			arrayList[i] = new ArrayList<Integer>();
		}
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			arrayList[U].add(V);
			arrayList[V].add(U);
		}
		
		makeTree(R, -1);
		
		for(int i=0; i<Q; i++) {
			int inputNode = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			
			System.out.println(size[inputNode]);
		}
	}
	public static void makeTree(int currentNode, int parent) {
		size[currentNode] = 1;
		for(int i=0; i<arrayList[currentNode].size(); i++) {
			int nextNode = arrayList[currentNode].get(i);
			
			if(nextNode != parent) {
				makeTree(nextNode, currentNode);
			}
		}
		if(parent != -1) {
			size[parent] += size[currentNode];
		}
	}
}
