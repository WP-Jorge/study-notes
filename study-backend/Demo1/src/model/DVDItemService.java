package model;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class DVDItemService {
	private List DVDItems_CACHE = new LinkedList();
	private String dataPath;
	public DVDItemService(String dataPath) {
		this.dataPath = dataPath;
		synchronized (DVDItems_CACHE) {
			if (DVDItems_CACHE.isEmpty()) {
				cacheDVDs();
			}
		}
	}
	
	private void cacheDVDs() {
		BufferedReader DVDReader = null;
		
		try {
			DVDReader = new BufferedReader(new FileReader(dataPath));
			String record;
			
			while ((record = DVDReader.readLine()) != null) {
				String[] fileds = record.split(",");
				int objectID = Integer.parseInt(fileds[0]);
				int year = Integer.parseInt(fileds[1]);
				String title = fileds[2];
				String genre = fileds[3];
				DVDItem item = new DVDItem(objectID, year, title, genre);
				DVDItems_CACHE.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (DVDReader != null) {
				try {
					DVDReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List getAllDVDItems() {
		return Collections.unmodifiableList(DVDItems_CACHE);
	}
	
	public DVDItem getDVD(int year, String title) throws ObjectNotFoundException {
		Iterator set = DVDItems_CACHE.iterator();
		while (set.hasNext()) {
			DVDItem d = (DVDItem) set.next();
			if ((d.year == year) && (d.title.equals(title))) {
				return d;
			}
		}
		throw new ObjectNotFoundException();
	}
	
	public DVDItem createDVD(int year, String genre, String title) {
		// Determine the next league objectID
		int nextID = DVDItems_CACHE.size() + 1;
		
		DVDItem DVDItem = new DVDItem(nextID, year, title, genre);
		storeDVD(DVDItem);
		DVDItems_CACHE.add(DVDItem);
		return DVDItem;
	}
	
	public void storeDVD(DVDItem dvdItem) {
		String DVDFile = dataPath + "my-library.txt";
		PrintWriter DVDWriter = null;
		try {
			DVDWriter = new PrintWriter(new FileWriter(DVDFile, true));
			DVDWriter.write(dvdItem.objectID + "");
			DVDWriter.write(',');
			DVDWriter.write(dvdItem.year + "");
			DVDWriter.write(',');
			DVDWriter.write(dvdItem.title);
			DVDWriter.write(',');
			DVDWriter.write(dvdItem.genre);
			DVDWriter.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (DVDWriter != null) {
				DVDWriter.close();
			}
		}
	}
}
