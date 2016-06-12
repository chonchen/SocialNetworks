package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommunityNetwork {
	
	private HashMap<Integer, Integer> vertexToCommunity;
	private WeightedGraph wGraph;
	
	public CommunityNetwork(Graph graph)
	{		
		vertexToCommunity = new HashMap<Integer, Integer>();
		
		List<Graph> sCCs = graph.getSCCs();
		int communityNumber = 0;
		
		//I define strongly connected components as communities. Community numbers are just integers that start at 0 and go up
		//This loop populates a hashmap so-as to quickly know which community a vertex belongs to.
		for (Graph component: sCCs)
		{
			HashMap<Integer, HashSet<Integer>> componentVertices = component.exportGraph();
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
		HashMap<Integer, HashSet<Integer>> allVertices = new HashMap<Integer, HashSet<Integer>>();	
		for (Integer v: allVertices.keySet())
		{
			Integer vCommunity = vertexToCommunity.get(v);
			
			HashSet<Integer> edgesTo = allVertices.get(v);
			for (Integer eT: edgesTo)
			{
				Integer eTCommunity = vertexToCommunity.get(eT);
				if (vCommunity != eTCommunity)
				{
					wGraph.addEdge(vCommunity, eTCommunity);
				}
			}
		}
		
	}
	
	public WeightedGraph getCommunityGraph()
	{
		
		return wGraph;
	}

}
