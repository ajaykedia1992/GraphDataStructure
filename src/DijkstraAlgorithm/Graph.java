package DijkstraAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Neighbor{
	int node;
	int weight;
	Neighbor next;
	public Neighbor(int node, int weight, Neighbor next) {
		this.node = node;
		this.weight = weight;
		this.next = next;
	}
}

class Vertex{
	String name;
	Neighbor adjList;
	public Vertex(String name, Neighbor adjList) {
		this.name = name;
		this.adjList = adjList;
	}
}


public class Graph {
	Vertex[] adjLists;
	public Graph(String fileName) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(fileName));
		boolean undirected = true;
		if(sc.next().equalsIgnoreCase("directed")) {
			undirected = false;
		}
		
		adjLists = new Vertex[sc.nextInt()];
		for(int i = 0 ;i<adjLists.length; i++) {
			adjLists[i] = new Vertex(sc.next(), null);
		}
		
		while(sc.hasNext()) {
			String origin = sc.next();
			int weight = sc.nextInt();
			String destination = sc.next();
			int v1 = findIndex(origin);
			int v2 = findIndex(destination);
			adjLists[v1].adjList = new Neighbor(v2, weight , adjLists[v1].adjList);
			if(undirected) {
				adjLists[v2].adjList = new Neighbor(v1, weight, adjLists[v2].adjList);
			}
		}
		sc.close();
	}
	private int findIndex(String name) {
		for(int i = 0; i<adjLists.length; i++) {
			if(adjLists[i].name.equalsIgnoreCase(name)) {
				return i;
			}
			
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter file name = ");
		String fileName = sc.nextLine();
		Graph g = new Graph(fileName);
		g.print();
		g.dijkstraAlgorithm();
		sc.close();
	}
	private void dijkstraAlgorithm() {
		int[] d = new int[adjLists.length];
		boolean[] visited = new boolean[adjLists.length];
		int[] pattern = new int[adjLists.length];
		Map<Integer, String> patternMap = new HashMap<>();
		for(int i = 0; i<adjLists.length; i++) {
			d[i] = Integer.MAX_VALUE;
			visited[i] = false;
			pattern[i] = Integer.MIN_VALUE;
			patternMap.put(i, "$$$$");
		}
		
		System.out.println("Considering starting vertex = " + adjLists[0].name);
		Vertex vertex = adjLists[0];
		visited[0] = true;
		int index = 0;
		pattern[index] = 0;
		patternMap.put(index, "$");
		d[0] = 0;
		for(Neighbor nbr = vertex.adjList; nbr != null; nbr = nbr.next) {
			d[nbr.node] = d[0] + nbr.weight;
			patternMap.put(nbr.node, vertex.name);
		}
		
		for(int i = 1; i<adjLists.length; i++) {
			int minimumIndex = findMinimum(d, pattern);
			pattern[++index] = minimumIndex;
			visited[minimumIndex] = true;
			vertex = adjLists[minimumIndex];
			for(Neighbor nbr = vertex.adjList; nbr != null; nbr = nbr.next) {
				if(visited[nbr.node]) {
					continue;
				}
				d[nbr.node] = d[minimumIndex] + nbr.weight;
				patternMap.put(nbr.node, vertex.name);
			}
		}
		for(int i = 0; i< pattern.length; i++) {
			System.out.println(adjLists[pattern[i]].name + " -> " + d[pattern[i]]);
		}
		
		System.out.println("Printing all pattern from Origin to destination ");
		for (Map.Entry<Integer, String> entry : patternMap.entrySet()) {
			int key = entry.getKey();
			String value = entry.getValue();
			System.out.print(adjLists[key].name);
			do {
				System.out.print(" -> " + value);
				key = findIndex(value);
				if(key == -1)
					continue;
				value = patternMap.get(key);
			}while(!value.equals("$"));
			System.out.print(" minimum weight = " + d[entry.getKey()]);
			System.out.println();
		}
	}
	private int findMinimum(int[] d, int[] pattern) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		int count = 0;
		for(int i = 0; i<d.length; i++) {
			for(int j = 0; j<pattern.length ; j++) {
				if(i == pattern[j]) {
					count = 1;
					break;
				}
			}
			if(count == 1) {
				count = 0;
				continue;
			}
			if(min>d[i]) {
				min = d[i];
				index = i;
			}
		}
		
		return index;
	}
	private void print() {
		for(Vertex v : adjLists) {
			System.out.print(v.name);
			for(Neighbor nbr = v.adjList; nbr != null; nbr = nbr.next) {
				System.out.print("->" + nbr.weight + "->" + adjLists[nbr.node].name);
			}
			System.out.println();
		}
		
	}
}
