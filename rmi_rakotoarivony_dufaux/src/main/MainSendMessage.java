package main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.Message;
import rmi.Site;

/**
 * Send the message from the site on the computer as params.
 *
 */
public class MainSendMessage {

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		if (args.length<3){
			usage();
			return;
		}
		Registry reg = LocateRegistry.getRegistry(args[0]);
		String numero = args[1];
		Site site = (Site) reg.lookup("site"+numero);
		Message m = site.createMessage(args[2]);
		site.sendMessage(m);
	}
	
	private static void usage(){
		System.out.println("usage : sendMessage <pc_name> <machine_id> <message>");
		System.out.println("\t<pc_name> : name or adress of a pc");
		System.out.println("\t<machine_id> : number of the VM on the pc");
		System.out.println("\t<message> : message to send");
		
	}

}
