package graph.grader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import graph.CapGraph;
import graph.CommunityNetwork;
import graph.Graph;
import graph.MinDominatingSet;
import graph.WeightedGraph;
import util.GraphLoader;

public class TestDirected {
	
	public static void main(String[] args) throws IOException
	{
		
		PrintWriter out = new PrintWriter(new FileWriter("E:\\workspace\\SocialNetworks\\data\\Eopinionsgraphreverse.txt"));
		
		Graph graph = new CapGraph();
        //GraphLoader.loadGraph(graph, "data/directed_small_test.txt");
        //GraphLoader.loadGraph(graph, "data/directed_small_test_non.txt");
        //GraphLoader.loadGraph(graph, "data/small_test_graph.txt");
        //GraphLoader.loadGraph(graph, "data/twitter_combined.txt");
        //GraphLoader.loadGraph(graph, "data/twitter_higgs.txt");
        //GraphLoader.loadGraph(graph, "data/facebook_2000.txt");
        //GraphLoader.loadGraph(graph, "data/facebook_1000.txt");
		//GraphLoader.loadGraph(graph, "data/facebook_ucsd.txt");
		GraphLoader.loadGraph(graph, "data/soc-Epinions1.txt");
        
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
        
        CommunityNetwork cN = new CommunityNetwork(graph.reverse());
        
        WeightedGraph wG = cN.getCommunityGraph();
        
        //HashMap<Integer, HashMap<Integer, Integer>> vertices = wG.exportGraph();
        
        
        
        out.println(wG.toString());
        out.close();
        
        //for (Integer i: cN.communityToVertex(1))
        //{
        	//System.out.println(i);
        //}
        
	}

}
