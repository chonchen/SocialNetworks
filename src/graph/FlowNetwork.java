package graph;

import java.util.LinkedList;
import java.util.List;

public class FlowNetwork {
	
	private final int V;
	private int E;
	private List<FlowEdge>[] adj;
	
	public FlowNetwork(int V)
	{
		this.V = V;
		adj = (List<FlowEdge>[]) new List[V];
		for (int i = 0; i < this.V; i++)
			adj[i] = new LinkedList<FlowEdge>();
	}
	
	public int V() {return V;}
	
	public int E() {return E;}
	
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
	
	public Iterable<FlowEdge> adj(int v)
	{
		return adj[v];
	}
	
	public Iterable<FlowEdge> edges()
	{
		List<FlowEdge> list = new LinkedList<FlowEdge>();
		for (int i = 0; i < V; i++)
			for (FlowEdge e: adj[i])
				if (e.to() != i)
					list.add(e);
		
		return list;
	}
	
	private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }

}
