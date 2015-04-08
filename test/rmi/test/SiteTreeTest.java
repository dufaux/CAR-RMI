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
		fail();
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
		assertNotEquals(m2.getInitiator(),m3.getInitiator());
		assertNotEquals(m2, m3);
		assertNotEquals(m3.getContent(), m3bis.getContent());
	}

	@Test
	public void testSetFather() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSons() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLastMessage() {
		fail("Not yet implemented");
	}

}
