import java.util.ArrayList;

public class CsvObject {
	private String[] headers;
	private ArrayList<String[]> rows;
	private ArrayList<CsvReaderError> errors;
	private long timeToRead;

	public CsvObject() {
		this.headers = null;
		this.rows = new ArrayList<String[]>();
		this.errors = new ArrayList<CsvReaderError>();
		this.timeToRead = 0;
	}

	public CsvObject(String[] headers, ArrayList<String[]> rows,
			ArrayList<CsvReaderError> errors, long timeToRead) {
		this.headers = headers;
		this.rows = rows;
		this.errors = errors;
		this.timeToRead = timeToRead;
	}

	private String formatRowToString(String[] row) {
		StringBuilder rowStringBuilder = new StringBuilder();
		for (int i = 0; i < row.length - 2; i++) {
			rowStringBuilder.append(row[i] + ", ");
		}
		rowStringBuilder.append(row[row.length - 1]);

		return rowStringBuilder.toString();
	}

	public String[] getHeaders() {
		return this.headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public ArrayList<String[]> getRows() {
		return this.rows;
	}

	public void setRows(ArrayList<String[]> rows) {
		this.rows = rows;
	}

	public void addRow(String[] row) {
		if (null != row) {
			this.rows.add(row);
		}
	}

	public String toString() {
		StringBuilder csvFormatted = new StringBuilder();
	
		csvFormatted.append("Time to Read: " + this.timeToRead + "ms");
		csvFormatted.append(System.getProperty("line.separator"));
		
		
		if (null != this.headers) {
			csvFormatted.append("Headers: " + this.headers);
			csvFormatted.append(System.getProperty("line.separator"));
		}
		csvFormatted.append("File Data: ");

		for (int i = 1; i < this.rows.size(); i++) {
			csvFormatted.append("Line " + i + ": "
					+ formatRowToString(this.rows.get(i)));
			csvFormatted.append(System.getProperty("line.separator"));
		}

		if (!this.errors.isEmpty()) {
			csvFormatted.append(System.getProperty("line.separator"));
			csvFormatted.append("Erros: ");
			for (CsvReaderError error : this.errors) {
				csvFormatted.append("Error at line " + error.getLineNumber()
						+ " : " + error.getErrorMessage());
			}
		}
		return csvFormatted.toString();
	}

	public ArrayList<CsvReaderError> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<CsvReaderError> errors) {
		this.errors = errors;
	}

	public void addError(CsvReaderError error) {
		this.errors.add(error);

	}

	public long getTimeToRead() {
		return timeToRead;
	}

	public void setTimeToRead(long timeToRead) {
		this.timeToRead = timeToRead;
	}

}
