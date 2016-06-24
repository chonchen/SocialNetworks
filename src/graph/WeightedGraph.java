package graph;

import java.util.HashMap;

public class WeightedGraph {
	
	private HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>> vertices = new HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>>();

	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (!vertices.containsKey(num))
		{
			vertices.put(num, new HashMap<Integer, WeightedDirectedEdge>());
		}
	}

	public void addEdge(int from, int to, Edge e) {
		// TODO Auto-generated method stub
		if (vertices.containsKey(from) && vertices.containsKey(to))
		{
			HashMap<Integer, WeightedDirectedEdge> edgeTos = vertices.get(from);
			if (edgeTos.containsKey(to))
			{
				WeightedDirectedEdge wDEdge = edgeTos.get(to);
				wDEdge.addEdge(e);
			}
			else
			{
				WeightedDirectedEdge wDEdge = new WeightedDirectedEdge(from, to);
				wDEdge.addEdge(e);
				edgeTos.put(to, wDEdge);
			}
		}
	}
	
	public HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>> exportGraph()
	{
		return vertices;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("From     To     Weight\n");
		for (Integer from: vertices.keySet())
		{
			HashMap<Integer, WeightedDirectedEdge> toVertices = vertices.get(from);
			for (Integer to: toVertices.keySet())
			{
				sb.append(from.toString() + "         " + to.toString() + "       " + toVertices.get(to).weight() +"\n");
			}
		}
		
		return sb.toString();
	}
	
	
	public static void main(String[] args)
	{
		WeightedGraph wGraph = new WeightedGraph();
		
		wGraph.addVertex(0);
		wGraph.addVertex(1);
		wGraph.addEdge(0, 1, new Edge(4, 5));
		wGraph.addEdge(0, 1, new Edge(4, 5));
		wGraph.addEdge(1, 0, new Edge(5, 4));
		System.out.println(wGraph.toString());
		
	}
}
