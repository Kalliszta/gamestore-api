package com.qa.gamestore.domain;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
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
		this.orderDate = new Timestamp(d.getTime());
	}
	
	public Orders(Long accountsId, Date d) {
		this.accountsId = accountsId;
		this.orderDate = new Timestamp(d.getTime());
	}

	public Orders(Long id, Long accountsId, Date d) {
		this.id = id;
		this.accountsId = accountsId;
		this.orderDate = new Timestamp(d.getTime());
	}

	public void updateFields(Orders newOrder) {
		this.accountsId = newOrder.getAccountsId();
		this.orderDate = newOrder.getOrderDate();
	}
	
	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
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

	public Accounts getAccounts() {
		return accounts;
	}
	
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
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

	@Override
	public String toString() {
		return "Orders [id=" + id + ", accountsId=" + accountsId + ", orderDate=" + orderDate + ", accounts=" + accounts
				+ "]";
	}	
	
}
