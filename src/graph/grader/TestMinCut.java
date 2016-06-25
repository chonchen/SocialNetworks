package graph.grader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import graph.CapGraph;
import graph.CommunityNetwork;
import graph.Edge;
import graph.FlowEdge;
import graph.FlowNetwork;
import graph.FordFulkerson;
import graph.Graph;
import graph.WeightedDirectedEdge;
import graph.WeightedGraph;
import util.GraphLoader;

public class TestMinCut {
	
	public static void main(String[] args)
	{
		Graph graph = new CapGraph();
		//GraphLoader.loadGraph(graph, "data/directed_small_test.txt");
		GraphLoader.loadGraph(graph, "data/soc-Epinions1.txt");
		
		CommunityNetwork communityNetwork = new CommunityNetwork(graph.reverse());
		WeightedGraph weightedGraph = communityNetwork.getCommunityGraph();
		
		HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>> wgExport = weightedGraph.exportGraph();
		
		FlowNetwork flowNetwork = new FlowNetwork(wgExport.size());
		
		for (Integer from : wgExport.keySet())
		{
			HashMap<Integer, WeightedDirectedEdge> edges = wgExport.get(from);
			for(Integer to : edges.keySet())
			{
				int weight = edges.get(to).weight();
				flowNetwork.addEdge(new FlowEdge(from, to, weight));
			}
		}
		
		FordFulkerson fordfulkerson = new FordFulkerson(flowNetwork, 27255,17552);
		
		
		HashSet<Integer> minCut = new HashSet<Integer>();
		
		
        for (int v = 0; v < wgExport.size(); v++) {
            if (fordfulkerson.inCut(v))
            {
            	minCut.add(v);
            	System.out.println(v);
            }
        }
        
        List<WeightedDirectedEdge> communityEdges = new LinkedList<WeightedDirectedEdge>();
        
        for (Integer v : minCut)
        {
        	HashMap<Integer, WeightedDirectedEdge> edge = wgExport.get(v);
        	for (Integer e : edge.keySet())
        	{
        		if (!minCut.contains(e))
        		{
        			WeightedDirectedEdge wDEdge = edge.get(e);
        			communityEdges.add(wDEdge);
        		}
        	}
        }
        
        for (WeightedDirectedEdge edge : communityEdges)
        {
        	System.out.println("From: " + edge.fromVertex() + " To: " + edge.toVertex() + " weight: " + edge.weight());
        	List<Edge> edgeList = edge.composedOfEdges();
        	for (Edge e : edgeList)
        	{
        		System.out.println(e.toString());
        	}
        }
        
        
        
	}
	

}
