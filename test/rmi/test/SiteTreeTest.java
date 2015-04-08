package rmi.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import rmi.Message;
import rmi.tree.*;

public class SiteTreeTest {

	@Test
	public void testSiteTreeImpl() throws RemoteException {
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		assertNotNull(s1);
		assertNotNull(s2);
	}

	@Test
	public void testSendReceiveMessage() throws RemoteException {
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		SiteTree s3 = new SiteTreeImpl("site3");
		SiteTree s4 = new SiteTreeImpl("site4");
		s1.setSons(s2,s3);
		s2.setFather(s1);
		s3.setFather(s1);
		s3.setSons(s4);
		s4.setFather(s3);
		Message m1 = s1.createMessage("test message from site 1");
		s1.sendMessage(m1);
		assertEquals("test message from site 1",s4.getLastMessage().getContent());
		assertEquals("test message from site 1",s3.getLastMessage().getContent());
		assertEquals("test message from site 1",s2.getLastMessage().getContent());
		assertNull(s1.getLastMessage());
		assertEquals(s1,s4.getLastMessage().getInitiator());
		assertEquals(s3,s4.getLastMessage().getSender());
		assertEquals(s1,s2.getLastMessage().getSender());
		assertEquals(s1,s3.getLastMessage().getSender());
		
		//renvoi du même message à partir du noeud 4
		s4.sendMessage(m1);
		assertEquals(m1,s1.getLastMessage());
		
		Message m4 = s1.createMessage("test message from site 4");
		s4.sendMessage(m4);
		assertEquals("test message from site 4",s1.getLastMessage().getContent());
		assertEquals("test message from site 4",s2.getLastMessage().getContent());
		assertEquals("test message from site 4",s3.getLastMessage().getContent());
		
	}

	@Test
	public void testCreateMessage() throws RemoteException {
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		SiteTree s3 = new SiteTreeImpl("site3");
		Message m1 = s1.createMessage("test message from site 1");
		assertNotNull(m1);
		assertEquals("test message from site 1", m1.getContent());
		assertEquals(s1,m1.getInitiator());

		Message m2 = s2.createMessage("test message must not equals");
		Message m3 = s3.createMessage("test message must not equals");
		Message m3bis = s3.createMessage("test message");

		assertEquals(m2.getContent(), m3.getContent());
		assertEquals(m3.getInitiator(),m3bis.getInitiator());
		assertNotEquals(m2.getInitiator(),m3.getInitiator());
		assertNotEquals(m2, m3);
		assertNotEquals(m3.getContent(), m3bis.getContent());
	}

	@Test
	public void testSetFatherAndSons() throws RemoteException {
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		SiteTree s3 = new SiteTreeImpl("site3");
		s1.setSons(s2);
		s2.setFather(s1);
		fail();
	}

	@Test
	public void testEqualsObject() throws RemoteException {
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		SiteTree s3 = new SiteTreeImpl("site1");
		assertEquals(s1,s1);
		assertEquals(s1,s3);
		assertNotEquals(s1,s2);
	}

	@Test
	public void testGetId() throws RemoteException {
		SiteTree s1 = new SiteTreeImpl("site1");
		SiteTree s2 = new SiteTreeImpl("site2");
		assertEquals("site1",s1.getId());
		assertEquals("site2",s2.getId());
		assertNotEquals("site1",s2.getId());
	}

}
