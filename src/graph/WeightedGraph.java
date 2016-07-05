package graph;

import java.util.HashMap;

//WeightedGraph is the community level graph where each vertex represents an SCC
//Each WeightedDirected Edge represents the summation of all the edges that lead from one community to another
public class WeightedGraph {
	
	/**
	* This HashMap uses FROM vertices as keys. Each key has another HashMap as the corresponding value
	* Each Sub-HashMap uses the TO vertices as keys. The corresponding value is the WeightedDirectedEdge
	* that goes from the FROM vertex to the TO vertex.
	 */
	private HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>> vertices = new HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>>();

	//Adding a "num" vertex adds the key "num" to the "vertices" HashMap with a corresponding value of a new empty HashMap 
	public void addVertex(int num) {
		
		if (!vertices.containsKey(num))
		{
			vertices.put(num, new HashMap<Integer, WeightedDirectedEdge>());
		}
	}

	//The addEdge method adds a WeightedDirectedEdge object to the graph
	public void addEdge(int from, int to, EdgeComponent e) {
		
		if (vertices.containsKey(from) && vertices.containsKey(to))
		{
			//get the sub-HashMap where the key is equal to "from"
			HashMap<Integer, WeightedDirectedEdge> edgeTos = vertices.get(from);
			
			//if the sub-HashMap has the "to" key, get the WeightedDirectedEdge and add the EdgeComponent to it
			if (edgeTos.containsKey(to))
			{
				WeightedDirectedEdge wDEdge = edgeTos.get(to);
				wDEdge.addEdge(e);
			}
			//otherwise create a new WeightedDirectedEdge, add the EdgeComponent to it, and put it in the sub-HashMap
			else
			{
				WeightedDirectedEdge wDEdge = new WeightedDirectedEdge(from, to);
				wDEdge.addEdge(e);
				edgeTos.put(to, wDEdge);
			}
		}
	}
	
	public HashMap<Integer, HashMap<Integer, WeightedDirectedEdge>> exportGraph()
	{
		return vertices;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("From     To     Weight\n");
		for (Integer from: vertices.keySet())
		{
			HashMap<Integer, WeightedDirectedEdge> toVertices = vertices.get(from);
			for (Integer to: toVertices.keySet())
			{
				sb.append(from.toString() + "         " + to.toString() + "       " + toVertices.get(to).weight() +"\n");
			}
		}
		
		return sb.toString();
	}
	
}
