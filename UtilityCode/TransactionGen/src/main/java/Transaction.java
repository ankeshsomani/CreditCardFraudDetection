

import java.util.Date;

public class Transaction {
	
	private String transactionscol;
	private String transactionId;
	private Long id;
	private Double amount;
	private Integer cardNumber;
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


}
