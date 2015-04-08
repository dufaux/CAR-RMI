package rmi.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tree {
	
	private List<SiteTree> liste;
	
	public Tree(){
		liste = new ArrayList<SiteTree>();
	}
	
	public void add(SiteTree site){
		this.liste.add(site);
	}
	
	public void setListe(SiteTree... lst){
		this.liste = Arrays.asList(lst);
	}
	
	public void setListe(List<SiteTree> lst){
		this.liste = lst;
	}
	
	public void init(String nameFile) throws FileNotFoundException, RemoteException{
        int nbSite = this.liste.size();
        
		Scanner scanner = new Scanner(new File(nameFile));
        while(scanner.hasNext()){
        	String[] ligne = scanner.next().split(",");
        	int father = Integer.parseInt(ligne[0]);
        	if(father < nbSite){
	        	for(int i=1;i<ligne.length;i++){
	        		int son = Integer.parseInt(ligne[i]);
	        		if(son < nbSite){
	        			System.out.println(liste.get(father).getId()+" add son"+liste.get(son).getId());
	        			liste.get(father).addSon(liste.get(son));
	        			liste.get(son).setFather(liste.get(father));
	        		}
	        	}
        	}
        }
        scanner.close();
	}
}
