package rmi.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import rmi.tree.SiteTree;
import rmi.tree.SiteTreeImpl;
import rmi.tree.Tree;

public class TreeTest {

	@Test
	public void testTree() throws FileNotFoundException, UnsupportedEncodingException, RemoteException {
		PrintWriter writer = new PrintWriter("treetest.csv", "UTF-8");
		writer.println("0,1,2");
		writer.println("1,3");
		writer.close();

		Tree greentree = new Tree();
		SiteTree s0 = new SiteTreeImpl("site0");
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		SiteTree s3 = new SiteTreeImpl("site3");
		
		greentree.add(s0);
		greentree.add(s1);
		greentree.add(s2);
		greentree.add(s3);
		greentree.init("treetest.csv");
		
		assertEquals(s0,s1.getFather());
		assertEquals(s1,s3.getFather());
		assertEquals(s1,s0.getSons()[0]);
		assertEquals(s3,s1.getSons()[0]);
		
		File file = new File("treetest.csv");
		file.delete();
	}

}
