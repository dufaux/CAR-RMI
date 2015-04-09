package rmi.test;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import org.junit.Test;

import rmi.graph.Graph;
import rmi.graph.SiteGraph;
import rmi.graph.SiteGraphImpl;

public class GraphTest {

	@Test
	public void testGraph() throws FileNotFoundException, UnsupportedEncodingException, RemoteException {
		PrintWriter writer = new PrintWriter("treetest.csv", "UTF-8");
		writer.println("0,1,2");
		writer.println("1,0");
		writer.println("2,0");
		writer.close();

		Graph biggraph = new Graph();
		SiteGraph s0 = new SiteGraphImpl("site0");
		SiteGraph s1 = new SiteGraphImpl("site1");
		SiteGraph s2 = new SiteGraphImpl("site2");
		SiteGraph s3 = new SiteGraphImpl("site3");
		
		biggraph.add(s0);
		biggraph.add(s1);
		biggraph.add(s2);
		biggraph.add(s3);
		biggraph.init("treetest.csv");
		
		assertArrayEquals(new SiteGraph[]{s0},s1.getNeighbor());
		assertArrayEquals(new SiteGraph[]{s1,s2},s0.getNeighbor());
		
		// we clean the file system
		new File("treetest.csv").delete();
	}

}
