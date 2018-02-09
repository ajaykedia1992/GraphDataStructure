package DFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Neighbor {
	int neighborNum;
	Neighbor next;

	public Neighbor(int num, Neighbor next) {
		this.neighborNum = num;
		this.next = next;
	}
}

class Vertex {
	String name;
	Neighbor adjList;

	public Vertex(String name, Neighbor adjList) {
		this.name = name;
		this.adjList = adjList;
	}
}

public class Graph {
	Vertex[] adjLists;

	public Graph(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		boolean undirected = true;
		if (sc.next().equalsIgnoreCase("directed")) {
			undirected = false;
		}
		adjLists = new Vertex[sc.nextInt()];
		for (int i = 0; i < adjLists.length; i++) {
			adjLists[i] = new Vertex(sc.next(), null);
		}

		while (sc.hasNext()) {
			int v1 = findNearerNeighbor(sc.next());
			int v2 = findNearerNeighbor(sc.next());

			adjLists[v1].adjList = new Neighbor(v2, adjLists[v1].adjList);
			if (undirected) {
				adjLists[v2].adjList = new Neighbor(v1, adjLists[v2].adjList);
			}
		}

		sc.close();
	}

	private int findNearerNeighbor(String name) {
		for (int i = 0; i < adjLists.length; i++) {
			if (adjLists[i].name.equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter file name = ");
		String fileName = sc.nextLine();
		Graph graph = new Graph(fileName);
		graph.dfs();
		//graph.print();
		sc.close();
	}

	private void print() {
		for (Vertex v : adjLists) {
			System.out.print(v.name);
			for (Neighbor nbr = v.adjList; nbr != null; nbr = nbr.next) {
				System.out.print("->" + adjLists[nbr.neighborNum].name);
			}
			System.out.println();
		}

	}

	private void dfs() {
		boolean[] visited = new boolean[adjLists.length];
		for(int i = 0; i <visited.length; i++) {
			if(!visited[i]) {
				System.out.println("Starting at " + adjLists[i].name);
				dfs(i, visited);
			}
		}

	}

	private void dfs(int i, boolean[] visited) {
		visited[i] = true;
		System.out.println("Visiting -> " + adjLists[i].name);
		for(Neighbor nbr = adjLists[i].adjList; nbr != null; nbr = nbr.next) {
			if(!visited[nbr.neighborNum]) {
				System.out.println(adjLists[i].name + ".........." + adjLists[nbr.neighborNum].name);
				dfs(nbr.neighborNum,visited);
			}
		}
		
	}
}
