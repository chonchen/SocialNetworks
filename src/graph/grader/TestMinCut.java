package graph.grader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import graph.CapGraph;
import graph.CommunityNetwork;
import graph.FlowEdge;
import graph.FlowNetwork;
import graph.FordFulkerson;
import graph.Graph;
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
		
		HashMap<Integer, HashMap<Integer, Integer>> wgExport = weightedGraph.exportGraph();
		
		FlowNetwork flowNetwork = new FlowNetwork(wgExport.size());
		
		for (Integer from : wgExport.keySet())
		{
			HashMap<Integer, Integer> edges = wgExport.get(from);
			for(Integer to : edges.keySet())
			{
				int weight = edges.get(to);
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
        
        List<FlowEdge> communityEdges = new LinkedList<FlowEdge>();
        
        for (Integer v : minCut)
        {
        	HashMap<Integer, Integer> edge = wgExport.get(v);
        	for (Integer e : edge.keySet())
        	{
        		if (!minCut.contains(e))
        		{
        			Integer weight = edge.get(e);
        			communityEdges.add(new FlowEdge(v, e, weight));
        		}
        	}
        }
        
        for (FlowEdge edge : communityEdges)
        {
        	System.out.println("From: " + edge.from() + " To: " + edge.to() + " weight: " + edge.capacity());
        }
        
        
        
	}
	

}
