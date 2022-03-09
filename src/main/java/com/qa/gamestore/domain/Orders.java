package com.qa.gamestore.domain;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_account_id", referencedColumnName = "id")
	private Accounts accounts;
	//will need to assign account the value of the person currently logged in

	public void updateFields(Orders newAccount) {
		//this.fkAccountId = newAccount.getFkAccountId();
		this.orderDate = newAccount.getOrderDate();;
	}
}
