package MyException;

public class DatabaseConnectionErrorException extends RuntimeException {

	public DatabaseConnectionErrorException() {
		super("Failed database connection");
	}
}
