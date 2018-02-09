package DirectedAndUndirectGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Neighbor {
	int number;
	Neighbor next;

	public Neighbor(int number, Neighbor next) {
		this.number = number;
		this.next = next;
	}
}

class Vertex {
	String name;
	Neighbor neighbor;

	public Vertex(String name, Neighbor neighbor) {
		this.name = name;
		this.neighbor = neighbor;
	}
}

public class Graph {

	Vertex[] adjLists;

	public Graph(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		boolean undirected;
		if (sc.next().equalsIgnoreCase("directed")) {
			undirected = false;
		} else {
			undirected = true;
		}

		adjLists = new Vertex[sc.nextInt()];
		for (int i = 0; i < adjLists.length; i++) {
			adjLists[i] = new Vertex(sc.next(), null);
		}

		while (sc.hasNext()) {
			int v1 = findNeighborhoodVertex(sc.next());
			int v2 = findNeighborhoodVertex(sc.next());

			adjLists[v1].neighbor = new Neighbor(v2, adjLists[v1].neighbor);
			if (undirected) {
				adjLists[v2].neighbor = new Neighbor(v1, adjLists[v2].neighbor);
			}
		}
		sc.close();
	}

	private int findNeighborhoodVertex(String name) {
		for (int i = 0; i < adjLists.length; i++) {
			if (adjLists[i].name.equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter file name = ");
		String fileName = sc.nextLine();
		try {
			Graph g = new Graph(fileName);
			g.print();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

	}

	private void print() {
		for (int v=0; v < adjLists.length; v++) {
            System.out.print(adjLists[v].name);
			for(Neighbor n = adjLists[v].neighbor; n != null; n=n.next) {
				System.out.print(" -> " + adjLists[n.number].name );
			}
			System.out.println("\n");
		}
		
	}
}
