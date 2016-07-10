package graph;

import java.util.LinkedList;
import java.util.List;

//A WeightedDirectedEdge represents an edge leading from one SCC community to another.
public class WeightedEdge {

	//fromVertex is an integer representation of a community the edge is coming from
	private final int fromVertex;
	
	//toVertex is an integer representation of a community the edge is going to
	private final int toVertex;
	
	//the initial weight of the WeightedDirectedEdge is 0
	private int weight = 0;
	
	//List to keep track of what individual-level edges make up the community-level WeightedDirectedEdge
	private List<EdgeComponent> edges = new LinkedList<EdgeComponent>();
	
	
	public WeightedEdge(int fromVertex, int toVertex)
	{
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	//Adding an EdgeComponent increases the weight of the WeightedDirectedEdge by 1
	public void addEdge(EdgeComponent e)
	{
		edges.add(e);
		weight++;
	}
	
	//Returns the list of all the individual-level edgecomponents that make up this WeightedDirectedEdge
	public List<EdgeComponent> composedOfEdges()
	{
		return edges;
	}
	
	public int fromVertex() { return fromVertex; }
	public int toVertex() { return toVertex; }
	public int weight() { return weight; }
}
