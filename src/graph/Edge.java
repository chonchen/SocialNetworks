package graph;

public class Edge {
	
	private final int from;
	private final int to;
	public Edge(int from, int to)
	{
		this.from = from;
		this.to = to;
	}
	public String toString()
	{
		return "Edge From Vertex: " + from + " To Vertex: " + to;
	}
}
