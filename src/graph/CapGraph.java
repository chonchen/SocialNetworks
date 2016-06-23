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
	
	private HashMap<Integer, HashSet<Integer>> vertices = new HashMap<Integer, HashSet<Integer>>();

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (!vertices.containsKey(num))
		{
			vertices.put(num, new HashSet<Integer>());
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (vertices.containsKey(from) && vertices.containsKey(to))
		{
			HashSet<Integer> edges = vertices.get(from);
			edges.add(to);
		}

	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		if (!vertices.containsKey(center)) return null;
			
		HashSet<Integer> egonetVertices = new HashSet<Integer>();
		egonetVertices.add(center);
		
		for (Integer v: vertices.get(center))
		{
			egonetVertices.add(v);
		}
		
		Graph egonet = new CapGraph();
		
		for (Integer v: egonetVertices)
		{
			egonet.addVertex(v);
		}
		
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

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
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

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return vertices;
	}
	
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
