package graph;

/**
 * A Flow Edge is like a Weighted Directed Edge in that it goes in one direction,
 * and has a weight (capacity) to it, but it also has an additional piece of information
 * called a flow value. The flow value is less than or equal to the weight of the edge.
 * We can think of the Flow Edge like a pipe that carries water. The weight would be the
 * maximum amount of water that the pipe can handle, and the flow value is the current
 * water level moving through the pipe.
 *
 */
public class FlowEdge {
	
	//the from vertex
	private final int v;
	
	//the to vertex
	private final int w;
	
	//the capacity of the FlowEdge. It is equal to the weight attribute in WeightedDirectedEdge 
	private final double capacity;
	
	//the flow value
	private double flow;
	
	public FlowEdge(int v, int w, int capacity)
	{
		this.v = v;
		this.w = w;
		this.capacity = capacity;
		this.flow = 0;
	}
	
	public int from() {return v;}
	public int to() {return w;}
	public double capacity() {return capacity;}
	public double flow() {return flow;}
	
	/**
	 * This method returns the "sibling" vertex that makes up the FlowEdge when a vertex is given
	 * This is helpful for the FordFulkerson algorithm when we travel backwards across directed
	 * edges
	 */
	public int other(int vertex)
	{
		if (vertex == v) return w;
		else if (vertex == w)return v;
		else throw new RuntimeException("Inconsistent Edge");
	}
	
	/**
	 * The residual capacity to a From edge is equal to the flow.
	 * The residual capacity to a To edge is equal to the capacity minus the flow.
	 */
	public double residualCapacityTo(int vertex)
	{
		if (vertex == v) return flow;
		else if (vertex == w) return capacity - flow;
		else throw new RuntimeException("Inconsistent Edge");
	}
	
	/**
	 * Adding a residual flow to a from 
	 * 
	 * 
	 */
	public void addResidualFlowTo(int vertex, double delta)
	{
		if (vertex == v) flow -= delta;
		else if (vertex == w) flow += delta;
		else throw new RuntimeException("Inconsistent Edge");
	}

}
