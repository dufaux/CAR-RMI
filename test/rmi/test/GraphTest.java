package rmi.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import org.junit.Test;

import rmi.graph.Graph;
import rmi.graph.SiteGraph;
import rmi.graph.SiteGraphImpl;
import rmi.tree.SiteTree;
import rmi.tree.SiteTreeImpl;
import rmi.tree.Tree;

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
		
		assertEquals(s0,s1.getNeighbor()[0]);
		assertEquals(s1,s0.getNeighbor()[0]);
		assertEquals(s1,s0.getNeighbor()[1]);
		
		File file = new File("treetest.csv");
		file.delete();
	}

}
