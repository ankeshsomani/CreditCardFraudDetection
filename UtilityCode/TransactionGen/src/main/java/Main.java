import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

	
	public static void main(String[] args) {
		static Long startTransId = 25155100l;
	static int countNFG = 0;
	static int posid = 1234;
	static int accountid = 100;
	static String description="Sai";
		
		Connection con=null;
		try {
			con=DBConnection.getConnection();
			createNonFraudTransactions(con);
			// createFraudTransactions(con); //comment ethier on the one function
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(con !=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//15
	public static int randomSpendAmountNonFraud[]={2200,3300,1800,2390,4000,800,6500,4800,6200,3400,5000,1100,9000,1700,2480};
	
	
	//15
	public static int randomSpendAmountFraud[]={50232,55323,67623,65675,87878,98232,111232,123122,123313,121222,94333,98000,87348,98231,72333};
	
	//15
	public static int randomDayTimimgs[]={600,725,750,700,825,850,935,800,900,925,950,940,880,730,1000};
	
	//4
	public static int randomDailyTransactions[]={1,2,3,4};
	public static int cardnumber=3456;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	
	private static void generateFile(Connection con){
		//List<Transaction>transactions=TransactionRepository.findAll(con);
		
	}
	private static void createFraudTransactions(Connection con) throws ParseException {
		int days=90;
		Long startTransId=221211l;
		Date startDate=sdf.parse("11/02/2017 00:00:00");
		System.out.println("createFraudTransactions");
		int countFt=0;
		do{
			int dailyTransactions= randomSpendAmountFraud[randomWithRange(0,3)];
			int temp=0;
			Calendar c = Calendar.getInstance(); 
			c.setTime(startDate); 
			c.add(Calendar.DATE, 2);	
			long t= c.getTimeInMillis();
			do{
				Transaction trans=new Transaction();
				
				int minutes=randomDayTimimgs[randomWithRange(0,14)];
				
				Date afterAddingMins=new Date(t + (minutes * ONE_MINUTE_IN_MILLIS));
				trans.setTransactionTime(afterAddingMins);
				trans.setAmount(Double.valueOf(randomSpendAmountNonFraud[randomWithRange(0,14)]));
				trans.setCardNumber(cardnumber);
				trans.setId(startTransId);
				trans.setTransactionId("T"+startTransId);
				trans.setTransactionscol("F");
				if (trans.getAccount() == null) {
					trans.setAccount(accountid);
				}
				if (trans.getPosid() == null) {
					trans.setPosid(posid);
				}
				if (trans.getDescription() == null) {
					trans.setDescription(description);
				}
				saveTransactionInDB(con,trans);
				saveTransactionInCSV(trans.toString());
				startTransId++;
				temp++;
			}
			while(dailyTransactions>temp);
			startDate=new Date(t);
			countFt++;
		}
		while(countFt<days);
		
	}

	private static void createNonFraudTransactions(Connection con) throws ParseException {
		int days=90;
		Long startTransId=221211l;
		Date startDate=sdf.parse("01/02/2017 00:00:00");
	
		int countNF=0;
		do{
			int dailyTransactions= randomDailyTransactions[randomWithRange(0,3)];
			int temp=0;
			Calendar c = Calendar.getInstance(); 
			c.setTime(startDate); 
			c.add(Calendar.DATE, 1);	
			long t= c.getTimeInMillis();
			do{
				Transaction trans=new Transaction();
				
				int minutes=randomDayTimimgs[randomWithRange(0,14)];
				
				Date afterAddingMins=new Date(t + (minutes * ONE_MINUTE_IN_MILLIS));
				trans.setTransactionTime(afterAddingMins);
				trans.setAmount(Double.valueOf(randomSpendAmountNonFraud[randomWithRange(0,14)]));
				trans.setCardNumber(cardnumber);
				trans.setId(startTransId);
				trans.setTransactionId("T"+startTransId);
				trans.setTransactionscol(null);
				if (trans.getAccount() == null) {
					trans.setAccount(accountid);
				}
				if (trans.getPosid() == null) {
					trans.setPosid(posid);
				}
				if (trans.getDescription() == null) {
					trans.setDescription(description);
				}
				saveTransactionInDB(con,trans);
				saveTransactionInCSV(trans.toString());
				startTransId++;
				temp++;
			}
			while(dailyTransactions>temp);
			startDate=new Date(t);
			countNF++;
		}
		while(countNF<days);
		
	}
	static int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

	private static void saveTransactionInCSV(String records) {
		// TODO Auto-generated method stub
		try {
			BufferedWriter bw= new BufferedWriter(new FileWriter("D:/30-05-17/transcationfile.csv",true));
					bw.write(records);
					bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("FILE WRITTEN" );
	}
	private static void saveTransactionInDB(Connection con,Transaction trans) {
		try {
			TransactionRepository.create(trans, con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
