package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSorting {

	int v;
	List<Integer> adj[];

	@SuppressWarnings("unchecked")
	public TopologicalSorting(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	private void addEdge(int i, int j) {
		adj[i].add(j);
	}

	private void topologicalSort(int i, Stack<Integer> stack, boolean[] visited) {
		visited[i] = true;
		Iterator<Integer> itr = adj[i].iterator();
		while (itr.hasNext()) {
			int v = itr.next();
			if (!visited[v]) {
				topologicalSort(v, stack, visited);
			}
		}
		
		stack.push(new Integer(i));

	}

	private void topologicalSort() {
		boolean visited[] = new boolean[v];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < v; i++) {
			if (visited[i] == false) {
				topologicalSort(i, stack, visited);
			}
		}

		while (!stack.isEmpty()) {
			int top = stack.pop();
			System.out.print(top + " ");
		}

	}

	public static void main(String args[]) {
		TopologicalSorting g = new TopologicalSorting(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.topologicalSort();
		System.out.println();
	}
}
