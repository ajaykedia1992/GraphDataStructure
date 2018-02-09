package BFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Queue {

	Integer[] queue;
	int top;

	public Queue() {
		queue = new Integer[100];
		top = -1;
	}

	public void enqueue(Integer num) {
		queue[++top] = num;
	}

	public Integer dequeue() {
		int index = top;
		--top;
		return queue[index];
	}

	public Integer peek() {
		return queue[top];
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}
}

class Neighbor {
	int neighbourNum;
	Neighbor next;

	public Neighbor(int neighbourNum, Neighbor next) {
		this.neighbourNum = neighbourNum;
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

	public Graph(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
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
		//graph.print();
		graph.bfs();
		sc.close();
	}

	private void bfs() {

		Queue queue = new Queue();
		boolean[] visited = new boolean[adjLists.length];
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				bfs(i, visited, queue);
			}
		}

	}

	private void bfs(int i, boolean[] visited, Queue queue) {
		visited[i] = true;
		System.out.println("Visited " + adjLists[i].name);
		queue.enqueue(i);
		while (!queue.isEmpty()) {
			int neighbor = queue.dequeue();
			for (Neighbor nbr = adjLists[neighbor].adjList; nbr != null; nbr = nbr.next) {
				System.out.println(adjLists[neighbor].name + "-------------" + adjLists[nbr.neighbourNum].name);
				if (!visited[nbr.neighbourNum]) {
					System.out.println("Visiting " + adjLists[nbr.neighbourNum].name);
					queue.enqueue(nbr.neighbourNum);
					visited[nbr.neighbourNum] = true;
				}
			}
		}

	}

	private void print() {
		for (Vertex v : adjLists) {
			System.out.print(v.name);
			for (Neighbor nbr = v.adjList; nbr != null; nbr = nbr.next) {
				System.out.print("->" + adjLists[nbr.neighbourNum].name);
			}
			System.out.println();
		}

	}
}
