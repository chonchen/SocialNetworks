package graph.grader;

import java.util.HashMap;
import java.util.List;

import graph.CapGraph;
import graph.CommunityNetwork;
import graph.Graph;
import graph.MinDominatingSet;
import graph.WeightedGraph;
import util.GraphLoader;

public class TestDirected {
	
	public static void main(String[] args)
	{
		Graph graph = new CapGraph();
        GraphLoader.loadGraph(graph, "data/directed_small_test.txt");
        //GraphLoader.loadGraph(graph, "data/small_test_graph.txt");
        //GraphLoader.loadGraph(graph, "data/twitter_combined.txt");
        //GraphLoader.loadGraph(graph, "data/facebook_2000.txt");
        //GraphLoader.loadGraph(graph, "data/facebook_1000.txt");
        
        List<Graph> ll = graph.getSCCs();
        
        MinDominatingSet mDS = new MinDominatingSet(graph);
        
        System.out.println(ll.size());
        
        List<Integer> dom = mDS.getDominatingSet();
        /**
        for (Integer i: dom)
        {
        	System.out.println(i);
        }
        **/
        System.out.println(dom.size());
        
        CommunityNetwork cN = new CommunityNetwork(graph);
        
        WeightedGraph wG = cN.getCommunityGraph();
        
        HashMap<Integer, HashMap<Integer, Integer>> vertices = wG.exportGraph();
        
        for (Integer g: vertices.keySet())
        {
        	System.out.println(g);
        	HashMap<Integer, Integer> hM = vertices.get(g);
        	System.out.println("Size" + hM.size());
        	
        
        	
        }
        
        System.out.println(wG.toString());
	}

}
