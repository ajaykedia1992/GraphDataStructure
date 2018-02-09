package Graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AlienLanguageDictionary {
	private int v;
	private List<Integer> adj[];

	@SuppressWarnings("unchecked")
	public AlienLanguageDictionary(int v) {
		this.v = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	private void createGraph(AlienLanguageDictionary g, String[] arr, int n, int count) {
		for (int i = 0; i < n - 1; i++) {
			String word1 = arr[i];
			String word2 = arr[i + 1];
			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				if (word1.charAt(j) != word2.charAt(j)) {
					g.addEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
					break;
				}
			}
		}
		
		g.topologicalSort();

	}

	private void topologicalSort() {
		boolean[] visited = new boolean[v];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0;i<v;i++) {
			if(visited[i]==false) {
				topologicalGraphSort(i,visited,stack);
			}
		}
		
		printStack(stack);
		
	}

	private void printStack(Stack<Integer> stack) {
		while(!stack.empty()) {
			System.out.print((char)('a' + stack.pop()) + " ");
		}
		
	}

	private void topologicalGraphSort(int i, boolean[] visited, Stack<Integer> stack) {
		visited[i] = true;
		Iterator<Integer> itr = adj[i].iterator();
		while(itr.hasNext()) {
			int v = itr.next();
			if(!visited[v]) {
				topologicalGraphSort(v, visited, stack);
			}
		}
		
		stack.push(new Integer(i));
		
	}

	private void addEdge(int i, int j) {
		adj[i].add(j);
	}

	public static void main(String args[]) {
		String[] arr = { "baa", "abcd", "abca", "cab", "cad" };
		int n = arr.length;
		Map<Character, Integer> map = new HashMap<>();
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < arr[i].length(); j++) {
				char key = arr[i].charAt(j);
				if (map.containsKey(key)) {
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
					count++;
				}
			}
		}
		AlienLanguageDictionary g = new AlienLanguageDictionary(count);
		g.createGraph(g, arr, n, count);
	}

}
