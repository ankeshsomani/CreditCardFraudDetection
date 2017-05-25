import java.io.Serializable;
import java.util.List;

public class Transactions implements Serializable {

	List<TestTransaction> transactions;

	public List<TestTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TestTransaction> transactions) {
		this.transactions = transactions;
	}
	
}
