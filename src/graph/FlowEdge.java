package graph;

public class FlowEdge {
	
	private final int v;
	private final int w;
	private final int capacity;
	private int flow;
	
	public FlowEdge(int v, int w, int capacity)
	{
		this.v = v;
		this.w = w;
		this.capacity = capacity;
		this.flow = 0;
	}
	
	public int from() {return v;}
	public int to() {return w;}
	public int capacity() {return capacity;}
	public int flow() {return flow;}
	
	public int other(int vertex)
	{
		if (vertex == v) return w;
		else if (vertex == w)return v;
		else throw new RuntimeException("Inconsistent Edge");
	}
	
	public int residualCapacityTo(int vertex)
	{
		if (vertex == v) return flow;
		else if (vertex == w) return capacity - flow;
		else throw new RuntimeException("Inconsistent Edge");
	}
	
	public void addResidualFlowTo(int vertex, int delta)
	{
		if (vertex == v) flow -= delta;
		else if (vertex == w) flow += delta;
		else throw new RuntimeException("Inconsistent Edge");
	}

}
