package graph.grader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import graph.CapGraph;
import graph.Graph;
import graph.MinDominatingSet;
import util.GraphLoader;

public class TestDirected {
	
	public static void main(String[] args) throws IOException
	{
		
		PrintWriter out = new PrintWriter(new FileWriter("E:\\workspace\\SocialNetworks\\data\\EpinionsDominatingSet.txt"));
		
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
		
		Graph reverse = graph.reverse();
                
        MinDominatingSet mDS = new MinDominatingSet(reverse);
        
        
        List<Integer> dom = mDS.getDominatingSet();
        
        out.println("Number of vertices in dominating set: " + dom.size());
        
        out.println("");
        out.println("Dominating Set");
        for (Integer i: dom)
        {
        	out.println(i);
        }

        out.close();
        

        
	}

}
