package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class helps convert a graph where each vertex represents an individual to a WeightedGraph where each
 * vertex represents an SCC, and where each WeightedDirectedEdge is an Edge from one community to another.
 *
 */
public class CommunityNetwork {
	
	//data structure for keeping track of which vertices belong to which communities
	private HashMap<Integer, Integer> vertexToCommunity;
	
	//data structure for keeping track of which communities contain which vertices
	private ArrayList<Set<Integer>> communityToVertex;
	
	//the weighted graph that will be created given a Graph input
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
					wGraph.addEdge(vCommunity, eTCommunity, new EdgeComponent(v, eT));	
				}
			}
		}
		
	}
	
	//returns the WeightedGraph created
	public WeightedGraph getCommunityGraph()
	{
		return wGraph;
	}
	
	//given an individual vertex, give the community number it belongs to
	public Integer vertexToCommunity(Integer vertex)
	{
		return vertexToCommunity.get(vertex);
	}
	
	//given a community number, return the list of all individual vertices that belong to that community
	public Set<Integer> communityToVertex(Integer community)
	{
		return communityToVertex.get(community);
	}

}
