package graph.grader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import graph.CapGraph;
import graph.CommunityNetwork;
import graph.Graph;
import graph.WeightedGraph;
import util.GraphLoader;

public class TestTopologicalSort {
	
	public static void main(String args[]) throws IOException
	{	
		PrintWriter out = new PrintWriter(new FileWriter("E:\\workspace\\SocialNetworks\\data\\topologicalorder.txt"));
		
		Graph graph = new CapGraph();
		//GraphLoader.loadGraph(graph, "data/directed_small_test.txt");
		GraphLoader.loadGraph(graph, "data/soc-Epinions1.txt");
		
		CommunityNetwork communityNetwork = new CommunityNetwork(graph);
		WeightedGraph weightedGraph = communityNetwork.getCommunityGraph();
		
		Graph reverse = new CapGraph();
		Graph reversed = reverse(weightedGraph, reverse);
		
		List<Integer> topologicalSort = reversePostOrder(reversed);
		
		for (Integer i : topologicalSort)
		{
			out.println(i);
		}
		out.close();
	}
	
	
	private static Graph reverse(WeightedGraph g, Graph reverse)
	{	
		HashMap<Integer, HashMap<Integer, Integer>> weightedGraphExport = g.exportGraph();
		
		for (Integer v: weightedGraphExport.keySet())
		{
			reverse.addVertex(v);
		}
		
		for (Integer v: weightedGraphExport.keySet())
		{
			for (Integer e: weightedGraphExport.get(v).keySet())
			{
				reverse.addEdge(e, v);
			}
		}
		
		return reverse;
	}
	
	
	private static List<Integer> reversePostOrder(Graph g)
	{
		HashMap<Integer, HashSet<Integer>> graphData = g.exportGraph();
		
		HashSet<Integer> visited = new HashSet<Integer>();
		
		List<Integer> finished = new LinkedList<Integer>();
		
		for (Integer v: graphData.keySet())
		{
			dfsFirstPass(v, graphData, visited, finished);
		}
		
		return finished;
	}
	
	private static void dfsFirstPass(Integer i, HashMap<Integer, HashSet<Integer>> graphData, HashSet<Integer> visited, List<Integer> finished)
	{
		if (visited.contains(i)) return;
		
		visited.add(i);
		
		for (Integer v: graphData.get(i))
		{
			dfsFirstPass(v, graphData, visited, finished);
		}
		
		finished.add(0, i);
	}
}
