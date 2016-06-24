package graph;

import java.util.LinkedList;
import java.util.List;

public class WeightedDirectedEdge {

	private final int fromVertex;
	
	private final int toVertex;
	
	private int weight = 0;
	
	private List<Edge> edges = new LinkedList<Edge>();
	
	
	public WeightedDirectedEdge(int fromVertex, int toVertex)
	{
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	public void addEdge(Edge e)
	{
		edges.add(e);
		weight++;
	}
	
	public List<Edge> composedOfEdges()
	{
		return edges;
	}
	
	public int fromVertex() { return fromVertex; }
	public int toVertex() { return toVertex; }
	public int weight() { return weight; }
}
