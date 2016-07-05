package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MinDominatingSet{
	
	//wrapper class for vertices so that they can be sorted by number of outgoing edges
	private class Node implements Comparable<Node>
	{
		private int outgoingvertices;
		private int vertex;
		
		public int compareTo(Node n)
		{
			if (this.outgoingvertices > n.outgoingvertices)
			{
				return -1;
			}
			else if (this.outgoingvertices < n.outgoingvertices)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	//stores exported graph
	private HashMap<Integer, HashSet<Integer>> vertices;
	//list of Node-wrapped vertices that will be sorted
	private List<Node> vertexList;
	//the dominating set that will be returned
	private List<Integer> dominatingSet;
	
	public MinDominatingSet(Graph graph)
	{	
		if (graph == null) throw new IllegalArgumentException("Null graph");
		
		vertices = graph.exportGraph();
		
		if (vertices == null) throw new IllegalArgumentException("Graph has no vertices");
		
		vertexList = new LinkedList<Node>();
		
		//wrap each vertex in a Node and add it to the vertexList
		for (Integer v: vertices.keySet())
		{
			HashSet<Integer> edges = vertices.get(v);
			Node node = new Node();
			node.vertex = v;
			node.outgoingvertices = edges.size();
			vertexList.add(node);
		}
		
		//sort the vertexList
		Collections.sort(vertexList);
		
		
		dominatingSet = new LinkedList<Integer>();
		HashSet<Integer> covered = new HashSet<Integer>();
		
		
		for (Node node: vertexList)
		{
			// If node has already been marked as visited, then go to the next node in the list.
			if (covered.contains(node.vertex)) continue;
			
			//Otherwise add node to the dominating set.
			covered.add(node.vertex);
			dominatingSet.add(node.vertex);
			
			HashSet<Integer> edges = vertices.get(node.vertex);
			
			//Mark each of node's neighbors as visited
			for (Integer e: edges)
			{
				covered.add(e);
			}
		}
	}
	
	public List<Integer> getDominatingSet()
	{
		return dominatingSet;
	}
}
