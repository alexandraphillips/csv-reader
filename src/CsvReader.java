import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CsvReader {

	public static void main(String[] args) {
		BufferedReader consoleInput = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Enter the location of your CSV on the file system");
		String csvLocation, fileDelimiter, existsHeaders = null;
		try {
			csvLocation = consoleInput.readLine();

			System.out.println("Enter the delimiter");
			fileDelimiter = consoleInput.readLine();

			System.out.println("Is the first line headers? (Y/N)");
			existsHeaders = consoleInput.readLine();

			long startTime = System.currentTimeMillis();
			CsvObject csvObject = new CsvObject();

			BufferedReader csvFileInput = new BufferedReader(new FileReader(
					csvLocation));

			// Assign the headers
			if ("Y" == existsHeaders) {
				String headerLine = csvFileInput.readLine();
				String[] headers = headerLine.split(fileDelimiter);
				csvObject.setHeaders(headers);
			}

			String csvLine = "";
			int lineNumber = 1;
			while (null != (csvLine = csvFileInput.readLine())) {
				String[] rowData = csvLine.split(fileDelimiter);
				if (("Y" == existsHeaders)
						&& (rowData.length != csvObject.getHeaders().length)) {
					csvObject.addError(new CsvReaderError(lineNumber,
							"Row does match the headers"));
				}

				csvObject.addRow(rowData);
				lineNumber++;
			}

			long endTime = System.currentTimeMillis();
			csvObject.setTimeToRead(endTime - startTime);

			System.out.println("READING COMPLETE");
			System.out.println("-----------------------------------------");
			System.out.println(csvObject.toString());

		} catch (IOException e) {
			System.err.println("ERROR: Could no read line from console");
			e.printStackTrace();
		}

	}
}
