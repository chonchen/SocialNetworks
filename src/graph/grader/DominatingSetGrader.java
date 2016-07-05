package graph.grader;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.CapGraph;
import graph.Graph;
import graph.MinDominatingSet;
import util.GraphLoader;

public class DominatingSetGrader {
	
	private Graph singleVertexGraph;
	private Graph smallGraph;
	private Graph seperateComponents;
	private Graph nonOptimalSolution;
	
	private MinDominatingSet singleVertexSet;
	private MinDominatingSet smallSet;
	private MinDominatingSet seperateComponentsSet;
	private MinDominatingSet nonOptimalSet;
	
	@Before
	public void setUp() throws Exception {
		
		singleVertexGraph = new CapGraph();
		singleVertexGraph.addVertex(0);
		singleVertexSet = new MinDominatingSet(singleVertexGraph);
		
		smallGraph = new CapGraph();
		smallGraph.addVertex(0);
		smallGraph.addVertex(1);
		smallGraph.addVertex(2);
		smallGraph.addEdge(0, 2);
		smallGraph.addEdge(1, 0);
		smallGraph.addEdge(1, 2);
		smallSet = new MinDominatingSet(smallGraph);
		
		seperateComponents = new CapGraph();
		seperateComponents.addVertex(0);
		seperateComponents.addVertex(1);
		seperateComponents.addVertex(2);
		seperateComponents.addVertex(3);
		seperateComponents.addVertex(4);
		seperateComponents.addEdge(0, 1);
		seperateComponents.addEdge(0, 2);
		seperateComponents.addEdge(3, 4);
		seperateComponentsSet = new MinDominatingSet(seperateComponents);
		
		nonOptimalSolution = new CapGraph();
		GraphLoader.loadGraph(nonOptimalSolution, "data/nonoptimalsolution.txt");
		nonOptimalSet = new MinDominatingSet(nonOptimalSolution);
		
	}

	@Test
	public void testGetDominatingSet() {
		
		assertEquals("Check single vertex", Integer.valueOf(0), singleVertexSet.getDominatingSet().get(0));
		
		assertEquals("Check small graph", Integer.valueOf(1), smallSet.getDominatingSet().get(0));
		
		assertEquals("Check seperate components first", Integer.valueOf(0), seperateComponentsSet.getDominatingSet().get(0));
		assertEquals("Check seperate components second", Integer.valueOf(3), seperateComponentsSet.getDominatingSet().get(1));
		
		
		assertEquals("Check non-Optimal first", Integer.valueOf(4), nonOptimalSet.getDominatingSet().get(0));
		assertEquals("Check non-Optimal second", Integer.valueOf(0), nonOptimalSet.getDominatingSet().get(1));
		assertEquals("Check non-Optimal third", Integer.valueOf(7), nonOptimalSet.getDominatingSet().get(2));
		
		
	}

}
