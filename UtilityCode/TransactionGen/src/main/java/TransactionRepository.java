import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
	public static List<Transaction> findAll(Connection con)throws SQLException, ClassNotFoundException {
		List<Transaction> transactions=new ArrayList<Transaction>();
		
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from transactions");  
		while(rs.next()){
			Transaction trans=new Transaction();
			trans.setAmount(Double.valueOf(rs.getInt("amount")));
			trans.setCardNumber(rs.getInt("cardnumber"));
			trans.setTransactionId(rs.getString("transactionid"));
			trans.setTransactionTime(rs.getDate("transtime"));
			transactions.add(trans);
			
		}
		return transactions;
	}

	public static Transaction create(final Transaction transaction, final Connection con)
			throws SQLException, ClassNotFoundException

	{

		final String sql = "insert into transactions(id,transactionid,cardnumber,amount,transtime,transactionscol)"
				+ " values(?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, transaction.getId());
		ps.setString(2, transaction.getTransactionId());
		ps.setInt(3, transaction.getCardNumber());
		ps.setInt(4, transaction.getAmount().intValue());
		ps.setDate(5, new java.sql.Date(transaction.getTransactionTime().getTime()));
		ps.setString(6, transaction.getTransactionscol());
		ps.execute();
		return transaction;
	}
}
