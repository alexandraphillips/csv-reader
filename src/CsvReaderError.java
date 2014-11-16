public class CsvReaderError {
	public CsvReaderError(int lineNumber, String errorMessage) {
		this.lineNumber = lineNumber;
		this.errorMessage = errorMessage;
	}

	private int lineNumber;
	private String errorMessage;

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
