package com.qa.gamestore.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	//@NonNull
	//private Long fkAccountId;
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
	
	//will need to assign account the value of the person currently logged in

	public void updateFields(Orders newAccount) {
		//this.fkAccountId = newAccount.getFkAccountId();
		this.orderDate = newAccount.getOrderDate();;
	}
}
