package Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Neighbor{
	int neighborNum;
	Neighbor next;
	public Neighbor(int num, Neighbor next) {
		this.neighborNum = num;
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
		if(sc.next().equalsIgnoreCase(fileName)) {
			
		}
	}
}
