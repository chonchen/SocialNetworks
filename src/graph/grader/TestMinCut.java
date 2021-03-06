package graph.grader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import graph.CapGraph;
import graph.CommunityNetwork;
import graph.EdgeComponent;
import graph.FlowEdge;
import graph.FlowNetwork;
import graph.FordFulkerson;
import graph.Graph;
import graph.WeightedEdge;
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
		
		
		HashMap<Integer, HashMap<Integer, WeightedEdge>> wgExport = weightedGraph.exportGraph();
		
		System.out.println("number of vertices " + wgExport.size());
		
		
		FlowNetwork flowNetwork = new FlowNetwork(wgExport.size());
		
		int count = 0;
		
		for (Integer from : wgExport.keySet())
		{
			HashMap<Integer, WeightedEdge> edges = wgExport.get(from);
			for(Integer to : edges.keySet())
			{
				int weight = edges.get(to).weight();
				flowNetwork.addEdge(new FlowEdge(from, to, weight));
				
				count++;
			}
		}
		
		System.out.println("number of edges " + count);
		
		Set<Integer> startVertices = communityNetwork.communityToVertex(27255);
		Set<Integer> endVertices = communityNetwork.communityToVertex(17552);
		
		System.out.println("vertices in start:");

		System.out.println(startVertices.toArray()[0]);
			
		System.out.println("vertices in end:");
		for (Integer i: endVertices)
		{
			System.out.println(i);
		}
		
		FordFulkerson fordfulkerson = new FordFulkerson(flowNetwork, 27255,17552);
		
		
		HashSet<Integer> minCut = new HashSet<Integer>();
		
		
        for (int v = 0; v < wgExport.size(); v++) {
            if (fordfulkerson.inCut(v))
            {
            	minCut.add(v);
            	//System.out.println(v);
            }
        }
        
        List<WeightedEdge> communityEdges = new LinkedList<WeightedEdge>();
        
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
        
        for (WeightedEdge edge : communityEdges)
        {
        	System.out.println("From: " + edge.fromVertex() + " To: " + edge.toVertex() + " weight: " + edge.weight());
        	List<EdgeComponent> edgeList = edge.composedOfEdges();
        	for (EdgeComponent e : edgeList)
        	{
        		System.out.println(e.toString());
        	}
        }
        
        
        
	}
	

}
