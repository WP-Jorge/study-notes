package model;

import java.io.*;

public class BorrowerService {
	private String dataPath;
	
	public BorrowerService(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public Borrower getBorrower(String name) {
		Borrower borrower = readBorrower(name);
		if (borrower == null) {
			borrower = new Borrower(name);
		}
		return borrower;
	}
	
	public void save(Borrower borrower) {
		storeBorrower(borrower);
	}
	
	private Borrower readBorrower(String search_name) {
		File borrowerFile = new File(dataPath, "borrower.txt");
		// System.out.println(borrowerFile);
		BufferedReader borrowerReader = null;
		Borrower borrower = null;
		
		if (!borrowerFile.exists()) {
			return null;
		}
		try {
			borrowerReader = new BufferedReader(new FileReader(borrowerFile));
			String record;
			
			while ((record = borrowerReader.readLine()) != null) {
				String[] fields = record.split(",");
				String name = fields[0];
				if (!search_name.equals(name)) {
					continue;
				}
				int age = Integer.parseInt(fields[1]);
				String sex = fields[2];
				String title = fields[3];
				
				borrower = new Borrower(name,age,sex,title);
				break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (borrowerReader != null) {
				try {
					borrowerReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return borrower;
	}
	private void storeBorrower(Borrower borrower) {
		String borrowerFile = dataPath + "borrower.txt";
		PrintWriter borrowerWriter = null;
		
		try {
			borrowerWriter = new PrintWriter(new FileWriter(borrowerFile));
			borrowerWriter.print(borrower.name);
			borrowerWriter.print(",");
			borrowerWriter.print(borrower.age);
			borrowerWriter.print(",");
			borrowerWriter.print(borrower.sex);
			borrowerWriter.print(",");
			borrowerWriter.print(borrower.title);
			borrowerWriter.println();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (borrowerWriter != null) {
				borrowerWriter.close();
			}
		}
	}
}
