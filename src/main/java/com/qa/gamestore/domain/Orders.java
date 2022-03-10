package com.qa.gamestore.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private Date orderDate;
	
	//relationship with accounts
	@JsonBackReference(value = "accountToOrders")
	@ManyToOne(targetEntity = Accounts.class, fetch = FetchType.LAZY)
	@JoinColumn(name="fk_accounts_id")
	private Accounts accounts;
	
	//relationship with OrderGames
	@JsonManagedReference(value = "ordersToOrderGenres")
	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) //if deleted so are its children
	private List<OrderGames> orderGames;
	
	//TO-DO will need to assign account the value of the person currently logged in

	
	
	public void updateFields(Orders newAccount) {
		//this.fkAccountId = newAccount.getFkAccountId();
		this.orderDate = newAccount.getOrderDate();
	}
	
	public Orders(Date orderDate, Accounts accounts) {
		this.orderDate = orderDate;
		this.accounts = accounts;
	}

	public Orders(Long id, Date orderDate, Accounts accounts) {
		this.id = id;
		this.orderDate = orderDate;
		this.accounts = accounts;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderDate=" + orderDate + ", accounts=" + accounts + "]";
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
