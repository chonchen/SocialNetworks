/**
 * 
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	
	//HashMap which stores FROM vertices as keys and a HashSet as values
	//Each HashSet contains the TO vertices that the FROM vertices have an edge to
	private HashMap<Integer, HashSet<Integer>> vertices = new HashMap<Integer, HashSet<Integer>>();


	@Override
	//adding a vertex "num" means adding a key "num" to the HashMap, with the value of an empty HashSet object
	public void addVertex(int num) {
		
		if (!vertices.containsKey(num))
		{
			vertices.put(num, new HashSet<Integer>());
		}
	}


	@Override
	//adding an Edge adds the TO vertex integer to the HashSet from the HashMap where the key is equal to FROM
	public void addEdge(int from, int to) {
		
		if (vertices.containsKey(from) && vertices.containsKey(to))
		{
			HashSet<Integer> edges = vertices.get(from);
			edges.add(to);
		}

	}


	@Override
	//returns the Egonet for a specific vertex
	public Graph getEgonet(int center) {
		
		if (!vertices.containsKey(center)) return null;
			
		//Keeps a list of all vertices that should be in the egonetgraph
		HashSet<Integer> egonetVertices = new HashSet<Integer>();
		
		//adds the center to the Set of vertices
		egonetVertices.add(center);
		
		//adds each of the center's neighboring vertices to the set of vertices
		for (Integer v: vertices.get(center))
		{
			egonetVertices.add(v);
		}
		
		Graph egonet = new CapGraph();
		
		//adds each of the vertices in the Set to the actual egonet graph
		for (Integer v: egonetVertices)
		{
			egonet.addVertex(v);
		}
		
		//adds all of the edges that go between all the vertices in the Set to the egonet graph
		for (Integer v: egonetVertices)
		{
			for (Integer e: vertices.get(v))
			{
				if (egonetVertices.contains(e))
				{
					egonet.addEdge(v, e);
				}
			}
		}
	
		return egonet;
	}


	@Override
	//get all SCCs for the graph by performing two passes of Depth first search
	public List<Graph> getSCCs() {
		
		List<Graph> sCCs = new LinkedList<Graph>();
		
		List<Integer> reversePostOrder = reversePostOrder(this.reverse());
		
		HashSet<Integer> visited = new HashSet<Integer>();
		
		Graph graph = new CapGraph();
		
		for (Integer i: reversePostOrder)
		{
			if (dfsSecondPass(i, graph, visited))
			{
				sCCs.add(graph);
				graph = new CapGraph();
			}
		}
		
		return sCCs;
	}


	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		
		return vertices;
	}
	
	//Creates a reversed version of the graph.
	public Graph reverse()
	{
		Graph reverse = new CapGraph();
		
		for (Integer v: vertices.keySet())
		{
			reverse.addVertex(v);
		}
		
		for (Integer v: vertices.keySet())
		{
			for (Integer e: vertices.get(v))
			{
				reverse.addEdge(e, v);
			}
		}
		
		return reverse;
	}
	
	//returns the reverse post order
	private List<Integer> reversePostOrder(Graph g)
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
	
	//the first pass of Depth First Search that creates the reverse post order through recursion
	private void dfsFirstPass(Integer i, HashMap<Integer, HashSet<Integer>> graphData, HashSet<Integer> visited, List<Integer> finished)
	{
		if (visited.contains(i)) return;
		
		visited.add(i);
		
		for (Integer v: graphData.get(i))
		{
			dfsFirstPass(v, graphData, visited, finished);
		}
		
		finished.add(0, i);
	}
	
	//the second pass of dfs which uses the reverse post order created from the first pass
	private boolean dfsSecondPass(Integer i, Graph graph, HashSet<Integer> visited)
	{
		if (visited.contains(i)) return false;
		
		visited.add(i);
		graph.addVertex(i);
		
		for (Integer v: vertices.get(i))
		{
			if (dfsSecondPass(v, graph, visited))
			{
				graph.addEdge(i, v);
			}
		}
		return true;
	}
}
