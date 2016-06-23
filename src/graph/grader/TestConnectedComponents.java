package graph.grader;

import java.util.List;

import graph.CapGraph;
import graph.Graph;
import graph.UndirectedGraph;
import util.GraphLoader;

public class TestConnectedComponents {
	
	public static void main(String args[])
	{
		Graph graph = new CapGraph();
		//GraphLoader.loadGraph(graph, "data/directed_small_test.txt");
		GraphLoader.loadGraph(graph, "data/soc-Epinions1.txt");
		
		Graph undirected = UndirectedGraph.makeUndirected(graph);
		
		List<Graph> connectedComponents = undirected.getSCCs();
		
		for (Graph g : connectedComponents)
		{
			System.out.println(g.exportGraph().size());
		}
	}

}
