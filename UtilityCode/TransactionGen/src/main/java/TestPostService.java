import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

public class TestPostService {
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String FILE_LOCATION = "D://Workspace_Dec2016//FraudDetection//transactionsfraud_test.csv";
	
	public static void main(String[] args) {

		try {
			readCSVFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void readCSVFile() {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			ObjectMapper mapper = new ObjectMapper();
	
			br = new BufferedReader(new FileReader(FILE_LOCATION));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] lineArray = line.split(cvsSplitBy);
				List<TestTransaction> transactions = new ArrayList<TestTransaction>();
				TestTransaction trans = new TestTransaction();
				trans.setAmount(new Double(lineArray[5]));
				trans.setTransactionId(lineArray[1]);
				trans.setAccountId(new Integer(lineArray[0]));
				trans.setDescription(lineArray[4]);
				trans.setPosid(new Integer(lineArray[2]));
				trans.setTransactionDate(lineArray[3]);
				transactions.add(trans);
				Transactions transactionsObj = new Transactions();
				transactionsObj.setTransactions(transactions);
				String jsonInString = mapper.writeValueAsString(transactionsObj);
				System.out.println(jsonInString);
				sendPost(jsonInString);
				
				Thread.sleep(1000);
				//break; //for singlee transcations only
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// HTTP POST request
	private static void sendPost(String jsonInString) throws Exception {

		String url = "http://34.192.115.151:8081/addTransactions";

		HttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url);

		// add header
		postRequest.setHeader("User-Agent", USER_AGENT);

		StringEntity input = new StringEntity(jsonInString);
		System.out.println(jsonInString);
		input.setContentType("application/json");
		postRequest.setEntity(input);

		HttpResponse response = client.execute(postRequest);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postRequest.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

	}

}
