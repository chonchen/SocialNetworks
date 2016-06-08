package graph;

import java.util.HashMap;

public class WeightedGraph {
	
	private HashMap<Integer, HashMap<Integer, Integer>> vertices = new HashMap<Integer, HashMap<Integer, Integer>>();

	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (!vertices.containsKey(num))
		{
			vertices.put(num, new HashMap<Integer, Integer>());
		}
	}

	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (vertices.containsKey(from) && vertices.containsKey(to))
		{
			HashMap<Integer, Integer> edges = vertices.get(from);
			if (edges.containsKey(to))
			{
				Integer i = edges.get(to);
				i++;
				edges.put(to, i);
			}
			else
			{
				edges.put(to, 1);
			}
		}
	}
	
	public HashMap<Integer, HashMap<Integer, Integer>> exportGraph()
	{
		return vertices;
	}
}
