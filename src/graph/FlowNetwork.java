package graph;

import java.util.LinkedList;
import java.util.List;

//A FlowNetwork is the same thing as a Graph, but it has FlowEdges instead of just Edges
public class FlowNetwork {
	
	private final int V;
	private int E;
	private List<FlowEdge>[] adj;
	
	//For the FlowNetwork, the constructor accepts an integer value for the number of vertices
	//we want in this graph. The vertices will be from 0 to V - 1. This just makes the construction
	//simpler than specifying an integer number for each vertex--as the community vertices are all
	//subsequent integers starting at 0 anyway 
	public FlowNetwork(int V)
	{
		this.V = V;
		adj = (List<FlowEdge>[]) new List[V];
		for (int i = 0; i < this.V; i++)
			adj[i] = new LinkedList<FlowEdge>();
	}
	
	public int V() {return V;}
	
	public int E() {return E;}
	
	//The addEdge adds the FlowEdge object to both the from vertex and the to vertex.
	public void addEdge(FlowEdge e)
	{
		int v = e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	//return the list of FlowEdges that include vertex v
	public Iterable<FlowEdge> adj(int v)
	{
		return adj[v];
	}
	
	//return the list of all FlowEdges in the FlowNetwork. Disregard edges where the FROM vertex is equal to the TO vertex
	public Iterable<FlowEdge> edges()
	{
		List<FlowEdge> list = new LinkedList<FlowEdge>();
		for (int i = 0; i < V; i++)
			for (FlowEdge e: adj[i])
				if (e.to() != i)
					list.add(e);
		
		return list;
	}
	
	//private method used to make sure that a vertex input is valid and not out of bounds
	private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

}
