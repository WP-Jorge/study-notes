package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterService {
	private String dataPath;
	
	public RegisterService(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public DVDItem getDVDItem(int year, String title)
			throws ObjectNotFoundException {
		// System.out.println("ReService" + dataPath);
		
		DVDItemService dvdItemService = new DVDItemService(dataPath);
		return dvdItemService.getDVD(year, title);
	}
	
	public Borrower getBorrower(String name) {
		BorrowerService borrowerService = new BorrowerService(dataPath);
		return borrowerService.getBorrower(name);
	}
	
	public void register(DVDItem dvdItem, Borrower borrower, int age) {
		BorrowerService borrowerService = new BorrowerService(dataPath);
		borrowerService.save(borrower);
		insertRegistration(dvdItem, borrower, age);
	}
	
	private void insertRegistration(DVDItem dvdItem, Borrower borrower, int age) {
		String regFile = dataPath + "registrations.txt";
		PrintWriter regWriter = null;
		try {
			regWriter = new PrintWriter(new FileWriter(regFile, true));
			regWriter.print(dvdItem.objectID);
			regWriter.print(",");
			regWriter.print(borrower.name);
			regWriter.print(",");
			regWriter.print(age);
			regWriter.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (regWriter != null) {
				regWriter.close();
			}
		}
	}
}
