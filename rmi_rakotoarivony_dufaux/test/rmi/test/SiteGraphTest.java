package rmi.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.rmi.RemoteException;

import org.junit.Test;

import rmi.Message;
import rmi.graph.SiteGraph;
import rmi.graph.SiteGraphImpl;

public class SiteGraphTest {

	@Test
	public void testSiteGraphImpl() throws RemoteException {
		SiteGraph s1 = new SiteGraphImpl("site1");
		SiteGraph s2 = new SiteGraphImpl("site2");
		assertNotNull(s1);
		assertNotNull(s2);
	}

	@Test
	public void testSendAndReceiveMessage() throws RemoteException {
		SiteGraph s1 = new SiteGraphImpl("site1");
		SiteGraph s2 = new SiteGraphImpl("site2");
		SiteGraph s3 = new SiteGraphImpl("site3");
		SiteGraph s4 = new SiteGraphImpl("site4");
		s1.setNeighbor(s2,s3);
		s2.setNeighbor(s2,s1);
		s3.setNeighbor(s1,s2,s4);
		s4.setNeighbor(s3);
		Message m1 = s1.createMessage("test message from site 1");
		s1.sendMessage(m1);
		assertEquals("test message from site 1",s4.getLastMessage().getContent());
		assertEquals("test message from site 1",s3.getLastMessage().getContent());
		assertEquals("test message from site 1",s2.getLastMessage().getContent());
		assertNull(s1.getLastMessage());
		
		Message m4 = s4.createMessage("test message from site 4");
		s4.sendMessage(m4);
		assertEquals("test message from site 4",s2.getLastMessage().getContent());
		assertEquals("test message from site 4",s3.getLastMessage().getContent());
		assertEquals("test message from site 4",s1.getLastMessage().getContent());
		assertEquals("test message from site 1",s4.getLastMessage().getContent());
	}


	
	@Test
	public void testEqualsObject() throws RemoteException {
		SiteGraph s1 = new SiteGraphImpl("site1");
		SiteGraph s2 = new SiteGraphImpl("site2");
		SiteGraph s3 = new SiteGraphImpl("site1");
		assertEquals(s1,s1);
		assertEquals(s1,s3);
		assertNotEquals(s1,s2);
	}
    
}
