package graph;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
	
	private boolean[] marked;
	private FlowEdge[] edgeTo;
	private int value;
	
	public FordFulkerson(FlowNetwork G, int s, int t)
	{
		while (hasAugmentingPath(G, s, t))
		{
			int bottle = Integer.MAX_VALUE;
			for (int v = t; v != s; v = edgeTo[v].other(v))
				bottle = Math.min(bottle,edgeTo[v].residualCapacityTo(v));
			
			for (int v = t; v != s; v = edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottle);
			
			value += bottle;
		}
	}
	
	public int value(){return value;}
	
	public boolean inCut(int v) {return marked[v];}
	
	private boolean hasAugmentingPath(FlowNetwork G, int s, int t)
	{
		marked = new boolean[G.V()];
		edgeTo = new FlowEdge[G.V()];
		Queue<Integer> q = new LinkedList<Integer>();
		
		marked[s] = true;
		q.add(s);
		while (!q.isEmpty())
		{
			int v = q.remove();
			for (FlowEdge e : G.adj(v))
			{
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !marked[w])
				{
					edgeTo[w] = e;
					marked[w] = true;
					q.add(w);
				}
			}
		}
		return marked[t];
	}

}
