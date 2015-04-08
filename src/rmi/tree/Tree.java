package rmi.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * 
 *  Represents a tree network of site
 */
public class Tree {
	
	private List<SiteTree> liste;
	private String textGraph;
	
	/**
	 * initialize the site list with an empty liste.
	 */
	public Tree(){
		liste = new ArrayList<SiteTree>();
		this.textGraph = "";
	}
	
	/**
	 * add a new site to the tree
	 * @param site
	 */
	public void add(SiteTree site){
		this.liste.add(site);
	}
	
	/**
	 * set the list of sites from an array of params
	 * @param lst : list of site
	 */
	public void setListe(SiteTree... lst){
		this.liste = Arrays.asList(lst);
	}
	
	/**
	 * set list of sites from a list
	 * @param lst : list of site
	 */
	public void setListe(List<SiteTree> lst){
		this.liste = lst;
	}
	
	/**
	 * init the tree and set neighbors from the file in parameter
	 * @param nameFile : the name of tree file as example
	 * @throws FileNotFoundException
	 * @throws RemoteException
	 */
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
	        			liste.get(father).addSon(liste.get(son));
	        			liste.get(son).setFather(liste.get(father));
	        			
	        			System.out.println(liste.get(father).getId()+" add son"+liste.get(son).getId());
	        			this.textGraph+= liste.get(father).getId()+" -> "+liste.get(son).getId()+";\n";
	        		}
	        	}
        	}
        }
        scanner.close();
	}
	
	/**
	 * create a tree file from the current tree.
	 * @param namefile : the name of the text file
	 */
	public void createFile(String namefile){
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(namefile), "utf-8"));
		    
		    writer.write("digraph tree { \n");
		    writer.write(textGraph);
		    writer.write("}");
		} catch (IOException ex) {
		  // report
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
}
