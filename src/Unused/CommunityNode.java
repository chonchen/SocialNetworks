package Unused;

import java.util.HashMap;
import java.util.HashSet;

import graph.Graph;

public class CommunityNode {
	
	private final HashMap<Integer, HashSet<Integer>> exportedGraph;
	private final int nodeIndex;
	
	public CommunityNode(Graph graph, int nodeIndex)
	{
		exportedGraph = graph.exportGraph();
		this.nodeIndex = nodeIndex;
	}
	
	public boolean containsVertex(int vertex)
	{
		return exportedGraph.containsKey(vertex);
	}
	
	public boolean containsEdge(int from, int to)
	{
		HashSet<Integer> edgeTos = exportedGraph.get(from);
		if (edgeTos == null)
			return false;
		else
			return edgeTos.contains(to);
	}
	
	public int nodeIndex(){ return nodeIndex; }
}
