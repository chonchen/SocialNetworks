package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommunityNetwork {
	
	private HashMap<Integer, Integer> vertexToCommunity;
	private ArrayList<Set<Integer>> communityToVertex;
	private WeightedGraph wGraph;
	
	public CommunityNetwork(Graph graph)
	{		
		List<Graph> sCCs = graph.getSCCs();
		int communityNumber = 0;
		
		vertexToCommunity = new HashMap<Integer, Integer>();
		communityToVertex = new ArrayList<Set<Integer>>();
		
		//I define strongly connected components as communities. Community numbers are just integers that start at 0 and go up
		//This loop populates a hashmap so-as to quickly know which community a vertex belongs to. It also has an Array Indexed by community to give the set of vertices
		for (Graph component: sCCs)
		{
			HashMap<Integer, HashSet<Integer>> componentVertices = component.exportGraph();
			communityToVertex.add(communityNumber, componentVertices.keySet());
			
			for (Integer vertex: componentVertices.keySet())
			{
				vertexToCommunity.put(vertex, communityNumber);
			}
			
			communityNumber++;
		}
		
		//add communities as vertices to an edge-weighted graph
		wGraph = new WeightedGraph();
		for (int i = 0; i <communityNumber; i++)
		{
			wGraph.addVertex(i);
		}
		
		//add edge to the weighted graph only if it leads from one community to another. Disregard intracommunity edges.
		HashMap<Integer, HashSet<Integer>> allVertices = graph.exportGraph();	
		for (Integer v: allVertices.keySet())
		{
			Integer vCommunity = vertexToCommunity.get(v);
			
			HashSet<Integer> edgesTo = allVertices.get(v);
			for (Integer eT: edgesTo)
			{
				Integer eTCommunity = vertexToCommunity.get(eT);
				if (vCommunity != eTCommunity)
				{	
					wGraph.addEdge(vCommunity, eTCommunity, new Edge(v, eT));	
				}
			}
		}
		
	}
	
	public WeightedGraph getCommunityGraph()
	{
		return wGraph;
	}
	
	public Integer vertexToCommunity(Integer vertex)
	{
		return vertexToCommunity.get(vertex);
	}
	
	public Set<Integer> communityToVertex(Integer community)
	{
		return communityToVertex.get(community);
	}

}
