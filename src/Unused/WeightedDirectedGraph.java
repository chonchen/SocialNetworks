package Unused;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import graph.WeightedDirectedEdge;

public class WeightedDirectedGraph {

	private HashMap<Integer, List<WeightedDirectedEdge>> vertices = new HashMap<Integer, List<WeightedDirectedEdge>>();

	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (!vertices.containsKey(num))
		{
			vertices.put(num, new LinkedList<WeightedDirectedEdge>());
		}
	}

	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (vertices.containsKey(from) && vertices.containsKey(to))
		{
			WeightedDirectedEdge edges = vertices.get(from);
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
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("From     To     Weight\n");
		for (Integer from: vertices.keySet())
		{
			HashMap<Integer, Integer> toVertices = vertices.get(from);
			for (Integer to: toVertices.keySet())
			{
				sb.append(from.toString() + "         " + to.toString() + "       " + toVertices.get(to).toString() +"\n");
			}
		}
		
		return sb.toString();
	}
}
