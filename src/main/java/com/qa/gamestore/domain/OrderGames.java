package com.qa.gamestore.domain;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
public class OrderGames {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@JsonBackReference
	@ManyToOne(targetEntity = Orders.class, fetch = FetchType.LAZY)
	@JoinColumn(name="fk_orders_id")
	private Orders orders;
	
	//@JsonBackReference
	@ManyToOne(targetEntity = Games.class, fetch = FetchType.LAZY)
	@JoinColumn(name="fk_games_id")
	private Games games;

	@NonNull
	@Transient
	private Long ordersId;
	
	@NonNull
	@Transient
	private Long gamesId;
	
	public OrderGames(Long ordersId, Long gamesId) {
		this.ordersId = ordersId;
		this.gamesId = gamesId;
	}
	
	public OrderGames(Long id, Long ordersId, Long gamesId) {
		this.id = id;
		this.ordersId = ordersId;
		this.gamesId = gamesId;
	}
	
	public void updateFields(OrderGames newOrderGame) {
		this.ordersId = newOrderGame.getOrdersId();
		this.gamesId = newOrderGame.getGamesId();
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getOrdersId() {
		if (orders != null) {
			return this.orders.getId();
		} else {
			return ordersId;
		}
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getGamesId() {
		if (games != null) {
			return this.games.getId();
		} else {
			return gamesId;
		}
	}

	public void setGamesId(Long gamesId) {
		this.gamesId = gamesId;
	}
	
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Games getGames() {
		return games;
	}

	public void setGames(Games games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "OrderGames [id=" + id + ", ordersId=" + ordersId + ", gamesId=" + gamesId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(gamesId, ordersId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderGames other = (OrderGames) obj;
		return Objects.equals(gamesId, other.gamesId) && Objects.equals(ordersId, other.ordersId);
	}

}
