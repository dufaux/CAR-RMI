package rmi.graph;

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
 * Represents a graph network of site
 * 
 */
public class Graph {
	private List<SiteGraph> liste;
	private String textGraph;

	/**
	 * initialize the site list with an empty liste.
	 */
	public Graph() {
		liste = new ArrayList<SiteGraph>();
		this.textGraph = "";
	}

	/**
	 * add a new site to the graph
	 * 
	 * @param site
	 */
	public void add(SiteGraph site) {
		this.liste.add(site);
	}

	/**
	 * set the list of sites from an array of params
	 * 
	 * @param lst
	 *            : list of site
	 */
	public void setListe(SiteGraph... lst) {
		this.liste = new ArrayList<SiteGraph>(Arrays.asList(lst));
	}

	/**
	 * set list of sites from a list
	 * 
	 * @param lst
	 *            : list of site
	 */
	public void setListe(List<SiteGraph> lst) {
		this.liste = lst;
	}

	/**
	 * init the graph and set neighbors from the file in parameter
	 * 
	 * @param nameFile
	 *            : the name of graph file as example
	 * @throws FileNotFoundException
	 * @throws RemoteException
	 */
	public void init(String nameFile) throws FileNotFoundException,
			RemoteException {
		int nbSite = this.liste.size();

		Scanner scanner = new Scanner(new File(nameFile));
		while (scanner.hasNext()) {
			String[] ligne = scanner.next().split(",");
			int father = Integer.parseInt(ligne[0]);
			if (father < nbSite) {
				for (int i = 1; i < ligne.length; i++) {
					int neighbor = Integer.parseInt(ligne[i]);
					if (neighbor < nbSite) {
						liste.get(father).setNeighbor(liste.get(neighbor));

						this.textGraph += liste.get(father).getId() + " -- "
								+ liste.get(neighbor).getId() + ";\n";
					}
				}
			}
		}
		scanner.close();
	}

	/**
	 * create a graph file from the current graph.
	 * 
	 * @param namefile
	 *            : the name of the file
	 */
	public void createFile(String namefile) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(namefile), "utf-8"));

			writer.write("graph mygraph { \n");
			writer.write(textGraph);
			writer.write("}");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
