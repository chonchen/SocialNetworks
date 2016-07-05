package graph;

//EdgeComponent represents an edge going from an individual to another individual outside of its community
//EdgeComponents sum together to make up a WeightedDirectedEdge
public class EdgeComponent {
	
	private final int from;
	private final int to;
	public EdgeComponent(int from, int to)
	{
		this.from = from;
		this.to = to;
	}
	public String toString()
	{
		return "Edge From Vertex: " + from + " To Vertex: " + to;
	}
}
