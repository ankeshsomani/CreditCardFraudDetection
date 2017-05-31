

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	private String transactionscol;
	private String transactionId;
	private String description;
	private Long id;
	private Double amount;
	private Integer cardNumber;
	private Integer posid;
	private Integer accountId;
	//DD/M
	public Integer getAccount() {
		return accountId;
	}
	public void setAccount(Integer account) {
		accountId = account;
	}
	public Integer getPosid() {
		return posid;
	}
	public void setPosid(Integer posid) {
		this.posid = posid;
	}
	private Date transactionTime;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public String getFormattedTransactionTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY HH:MM:SS");
		return formatter.format(getTransactionTime());
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransactionscol() {
		return transactionscol;
	}
	public void setTransactionscol(String transactionscol) {
		this.transactionscol = transactionscol;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	 
	@Override
	public String toString() {
		return accountId + "," + transactionId + "," + posid
				+ "," + getFormattedTransactionTime() + "," + description + "," + amount+"\n";
	}


}
