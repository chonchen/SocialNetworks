package graph;

import java.util.LinkedList;
import java.util.Queue;

//This class is used to determine the minimum cut for a given FlowNetwork. The algorithm is fairly complex.
public class FordFulkerson {
	
	//keeps track of vertices already visited. When the algorithm finishes, all the vertices
	//in the mincut will return true
	private boolean[] marked;
	
	//an array of FlowEdge objects used to show the path found from start vertex to end vertex
	private FlowEdge[] edgeTo;
	
	//keeps track of the value of the total flow from starting vertex to ending vertex
	private double value;
	
	public FordFulkerson(FlowNetwork G, int s, int t)
	{
		while (hasAugmentingPath(G, s, t))
		{
			//the bottleneckFlow is equal to the largest amount of additional flow that can be added
			//to a path without "bursting any pipes"
			//It's set to infinity initially, but the next step will narrow it down to its
			//maximum value
			double bottleneckFlow = Double.POSITIVE_INFINITY;
			
			//this step looks at each FlowEdge of the augmenting path and calculates the maximum
			//amount of flow that can be added to that path
			for (int v = t; v != s; v = edgeTo[v].other(v))
				bottleneckFlow = Math.min(bottleneckFlow,edgeTo[v].residualCapacityTo(v));
			
			//this step adds the bottleneckFlow value to each edge in the augmenting path
			for (int v = t; v != s; v = edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottleneckFlow);
			
			//update the total flow value by adding the add the bottleneckFlow value 
			value += bottleneckFlow;
		}
	}
	
	public double value(){return value;}
	
	//return all the vertices that are in the minimum cut
	public boolean inCut(int v) {return marked[v];}
	
	
	private boolean hasAugmentingPath(FlowNetwork G, int s, int t)
	{
		//clear the visited vertices
		marked = new boolean[G.V()];
		
		//clear the flow augmenting path found
		edgeTo = new FlowEdge[G.V()];
		
		//Queue data structure used to determin which vertex to travel to next
		Queue<Integer> q = new LinkedList<Integer>();
		
		marked[s] = true;
		q.add(s);
		while (!q.isEmpty())
		{
			//set v equal to the vertex we remove from the queue
			int v = q.remove();
			
			//for each of v's FlowEdges determine if additional flow can be added
			for (FlowEdge e : G.adj(v))
			{
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !marked[w])
				{
					//if additional flow can be added, add the edge to the path
					edgeTo[w] = e;
					
					//mark the vertex as true. This is used so that at the end of this private method,
					//if the end vertex has been marked as true, then we know there is a path that can
					//add additional flow
					marked[w] = true;
					
					//add the vertex w to the queue so that it can be visited next
					q.add(w);
				}
			}
		}
		//if the end vertex t returns as true, we know that a path that can add additional flow has been found
		return marked[t];
	}

}
