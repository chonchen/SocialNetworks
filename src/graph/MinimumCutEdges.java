package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * This class has inputs of Graph and start and end vertices. It returns a list of edges that are the bottleneck
 * for the flow between the community containing the start vertex and the community containing the end vertex.
 * @author Tony
 *
 */
public final class MinimumCutEdges {
	
	public static List<EdgeComponent> getEdges(Graph graph, int startVertex, int endVertex)
	{
		if (!graph.containsVertex(startVertex) || !graph.containsVertex(endVertex))
		{
			throw new IllegalArgumentException("Must be valid vertices");
		}
		
		//Transform CapGraph to a WeightedGraph
		CommunityNetwork communityNetwork = new CommunityNetwork(graph);
		WeightedGraph weightedGraph = communityNetwork.getCommunityGraph();
		
		//Export the weighted graph so we can convert it to a FlowNetwork
		HashMap<Integer, HashMap<Integer, WeightedEdge>> wgExport = weightedGraph.exportGraph();
		
		//FlowNetwork conversion
		FlowNetwork flowNetwork = new FlowNetwork(wgExport.size());
		
		for (Integer from : wgExport.keySet())
		{
			HashMap<Integer, WeightedEdge> edges = wgExport.get(from);
			for(Integer to : edges.keySet())
			{
				int weight = edges.get(to).weight();
				flowNetwork.addEdge(new FlowEdge(from, to, weight));

			}
		}
		
		int startCommunity = communityNetwork.vertexToCommunity(startVertex);
		int endCommunity = communityNetwork.vertexToCommunity(endVertex);
		
		//return empty list if the start community equals end community
		if (startCommunity == endCommunity)
		{
			return new LinkedList<EdgeComponent>();
		}
		
		//perform the FordFulkerson algorithm
		FordFulkerson fordfulkerson = new FordFulkerson(flowNetwork, startCommunity, endCommunity);
		
		//Set to contain the list of vertices that are in the mincut group
		HashSet<Integer> minCut = new HashSet<Integer>();
		
        for (int v = 0; v < wgExport.size(); v++) {
            if (fordfulkerson.inCut(v))
            {
            	minCut.add(v);
            }
        }
        
        //List to contain all of the bottleneck WeightedEdges
        List<WeightedEdge> communityEdges = new LinkedList<WeightedEdge>();
        
        //get all of the WeightedEdges leading out of the mincut group and add them to the above list
        for (Integer v : minCut)
        {
        	HashMap<Integer, WeightedEdge> edge = wgExport.get(v);
        	for (Integer e : edge.keySet())
        	{
        		if (!minCut.contains(e))
        		{
        			WeightedEdge wDEdge = edge.get(e);
        			communityEdges.add(wDEdge);
        		}
        	}
        }
        
        //final list to contain the bottleneck normal edges
        List<EdgeComponent> edgeComponents = new LinkedList<EdgeComponent>();
        
        //Get the components that make up each bottleneck WeightedEdge
        for (WeightedEdge edge : communityEdges)
        {
        	List<EdgeComponent> edgeList = edge.composedOfEdges();
        	for (EdgeComponent e : edgeList)
        	{
        		edgeComponents.add(e);
        	}
        }
        
        //return final list of bottleneck edges
        return edgeComponents;
	}
}
