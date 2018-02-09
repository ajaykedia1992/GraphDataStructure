package AdjacencyList;
import java.util.*;

public class AdjacencyList {
	class Edge{
		public int v,w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "(" + v + "," + w + ")";
		}
		
	}
	List<Edge>G[];
	public AdjacencyList(int n) {
		G = new LinkedList[n];
		for(int i = 0 ; i<G.length; i++) {
			G[i] = new LinkedList<Edge>();
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i<G.length; i++) {
			result += i + "=>" + G[i] + "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		AdjacencyList graph = new AdjacencyList(10);
		graph.addEdge(0,2,3);
		graph.addEdge(0, 4, 10);
		graph.addEdge(3, 4, 6);
		graph.addEdge(9, 0, 2);
		System.out.println(graph);
		System.out.println(graph.isConnected(0, 9));
	}
	private void addEdge(int u, int v, int w) {
		G[u].add(0, new Edge(v, w));
	}
	
	public boolean isConnected(int u, int v) {
		List<Edge> l = G[u];
		for(int i = 0;i<l.size();i++) {
			if(l.get(i).v == v){
				return true;
			}
		}
		return false;
	}
}
