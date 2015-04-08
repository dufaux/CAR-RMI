package rmi.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Graph {
	private List<SiteGraph> liste;
	
	public Graph(){
		liste = new ArrayList<SiteGraph>();
	}
	
	public void add(SiteGraph site){
		this.liste.add(site);
	}
	
	public void setListe(SiteGraph... lst){
		this.liste = Arrays.asList(lst);
	}
	
	public void setListe(List<SiteGraph> lst){
		this.liste = lst;
	}
	
	public void init(String nameFile) throws FileNotFoundException, RemoteException{
        int nbSite = this.liste.size();
        
		Scanner scanner = new Scanner(new File(nameFile));
        while(scanner.hasNext()){
        	String[] ligne = scanner.next().split(",");
        	int pere = Integer.parseInt(ligne[0]);
        	if(pere < nbSite){
	        	for(int i=1;i<ligne.length;i++){
	        		int voisin = Integer.parseInt(ligne[i]);
	        		if(voisin < nbSite){
	        			liste.get(pere).setNeighbor(liste.get(voisin));
	        		}
	        	}
        	}
        }
        scanner.close();
	}
}
