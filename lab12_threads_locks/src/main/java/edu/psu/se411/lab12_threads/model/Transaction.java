package edu.psu.se411.lab12_threads.model;

import edu.psu.se411.lab12_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable {
	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	
	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super();
		if(amount < 0) throw new IllegalArgumentException("Amount used in transaction cannot be negative");
		
		if(id == null || sender == null || receiver == null || amount < 0) throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
	
	@Override
	public void run() {
		try {
			execute();
		} catch (InsufficientBalanceException e) {
			//System.out.println(e.getMessage());
		}
	}
	

	public void execute() throws InsufficientBalanceException {

		if (sender == receiver)
			throw new IllegalArgumentException("Same account");

		CryptoAccount first = sender;
		CryptoAccount second = receiver;

		// Lock accounts in ascending ID order
		if (first.getId().compareTo(second.getId()) > 0) {
			first = receiver;
			second = sender;
		}

		first.getLock().lock();
		try {
			second.getLock().lock();
			try {
				if (sender.getBalance() < amount)
					throw new InsufficientBalanceException(sender.getId());

				sender.retrieveFromBalance(amount);
				receiver.addToBalance(amount);

			} finally {
				second.getLock().unlock();
			}
		} finally {
			first.getLock().unlock();
		}
	}
	
	public CryptoAccount getSender() {
		return sender;
	}
	public void setSender(CryptoAccount sender) {
		this.sender = sender;
	}
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public void setReceiver(CryptoAccount receiver) {
		this.receiver = receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}


}
