package com.qa.gamestore.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@NoArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Transient
	private Long accountsId;
	
	private Timestamp orderDate; //TO-DO get to work
	
	//relationship with accounts
	@JsonBackReference
	@ManyToOne(targetEntity = Accounts.class, fetch = FetchType.LAZY)
	@JoinColumn(name="fk_accounts_id")
	private Accounts accounts;
	
	//relationship with OrderGames
	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<OrderGames> orderGames;
	
	//TO-DO will need to assign account the value of the person currently logged in
	
	public Orders(Long accountsId) {
		this.accountsId = accountsId;
		Date d = new Date();
		this.setOrderDate(d);
	}
	
	public Orders(Long accountsId, Date d) {
		this.accountsId = accountsId;
		this.setOrderDate(d);
	}

	public Orders(Long id, Long accountsId, Date d) {
		this.id = id;
		this.accountsId = accountsId;
		this.setOrderDate(d);
	}

	public void updateFields(Orders newOrder) {
		this.accountsId = newOrder.getAccountsId();
		this.orderDate = newOrder.getOrderDate();
	}

	public Long getId() {
		return id;
	}
	
	public Long getAccountsId() {
		if (accounts != null) {
			return this.accounts.getId();
		} else {
		return accountsId;
		}
	}

	public void setAccountsId(Long accountsId) {
		this.accountsId = accountsId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date d) {
		if (d != null) {
			this.orderDate = new Timestamp(d.getTime());
		} else if (this.orderDate == null){
			this.orderDate = null;
		}
	}
	
	public Accounts getAccounts() {
		return accounts;
	}
	
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return "Orders [id=" + id + ", accountsId=" + accountsId + ", orderDate=" + orderDate + "]";
	}	

	@Override
	public int hashCode() {
		return Objects.hash(accounts, orderDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(accounts, other.accounts) && Objects.equals(orderDate, other.orderDate);
	}
	
}