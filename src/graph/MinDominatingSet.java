package graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MinDominatingSet{
		
	private class Node implements Comparable<Node>
	{
		private int outgoingvertices;
		private int vertex;
		
		public int compareTo(Node n)
		{
			if (this.outgoingvertices > n.outgoingvertices)
			{
				return 1;
			}
			else if (this.outgoingvertices < n.outgoingvertices)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
	
	private HashMap<Integer, HashSet<Integer>> vertices;
	private List<Node> vertexList;
	
	public MinDominatingSet(Graph graph)
	{	
		vertexList = new LinkedList<Node>();
		vertices = graph.exportGraph();
		
		for (Integer v: vertices.keySet())
		{
			HashSet<Integer> edges = vertices.get(v);
			Node node = new Node();
			node.vertex = v;
			node.outgoingvertices = edges.size();
			vertexList.add(node);
		}
		
		Collections.sort(vertexList);
	}
	
	public List<Integer> getDominatingSet()
	{
		List<Integer> dominatingSet = new LinkedList<Integer>();
		HashSet<Integer> covered = new HashSet<Integer>();
		
		for (Node node: vertexList)
		{
			if (covered.contains(node.vertex)) continue;
			
			covered.add(node.vertex);
			dominatingSet.add(node.vertex);
			
			HashSet<Integer> edges = vertices.get(node.vertex);
			for (Integer e: edges)
			{
				covered.add(e);
			}
		}
		
		return dominatingSet;
	}
}
