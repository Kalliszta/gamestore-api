package com.qa.gamestore.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderGames {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Orders.class, fetch = FetchType.LAZY)
	@JoinColumn(name="fk_orders_id")
	private Orders orders;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Games.class, fetch = FetchType.LAZY)
	@JoinColumn(name="fk_games_id")
	private Games games;

	@Override
	public String toString() {
		return "OrderGames [id=" + id + ", orders=" + orders + ", games=" + games + "]";
	}
}
