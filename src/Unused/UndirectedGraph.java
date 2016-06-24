package Unused;

import java.util.HashMap;
import java.util.HashSet;

import graph.CapGraph;
import graph.Graph;

public final class UndirectedGraph {
	
	public static Graph makeUndirected(Graph g)
	{	
		Graph undirected = new CapGraph();
		
		HashMap<Integer, HashSet<Integer>> vertices = g.exportGraph();
		
		for (Integer v : vertices.keySet())
			undirected.addVertex(v);
		
		for (Integer v : vertices.keySet())
		{
			HashSet<Integer> edgesTo = vertices.get(v);
			for (Integer e : edgesTo)
			{
				undirected.addEdge(v, e);
				undirected.addEdge(e, v);
			}
		}
		
		return undirected;
	}
	
}
