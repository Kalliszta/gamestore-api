package com.qa.gamestore.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrdersTest {
	private Orders existingOrder;
	
	@BeforeEach
	void setUpForEach() {
		existingOrder = new Orders(1L, 4L, Timestamp.valueOf("2022-03-12 13:12:18.000"));
	}
	
	@Test
	public void testGetId() {
		//given
		Long expected = 1L;
		//when
		Long actual = existingOrder.getId();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAccountId() {
		//given
		Long expected = 4L;
		//when
		Long actual = existingOrder.getAccountsId();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetAccountId() {
		//given
		Long expected = 5L;
		//when
		existingOrder.setAccountsId(5L);
		Long actual = existingOrder.getAccountsId();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAccountIdWithExistingAccount() {
		//given
		Long expected = 4L;
		Accounts account1 = new Accounts(4L, "Steph", "&7C,Mt67@)skZO3", "Steph", "Ann", 30, "stepha@email.com", "07853388831", false);
		existingOrder.setAccounts(account1);
		//when
		Long actual = existingOrder.getAccountsId();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetOrderDate() {
		//given
		Date expected = Timestamp.valueOf("2022-03-12 13:12:18.000");
		//when
		Date actual = existingOrder.getOrderDate();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetOrderDate() {
		//given
		Date expected = Timestamp.valueOf("2022-03-13 18:50:54.000");
		//when
		existingOrder.setOrderDate(Timestamp.valueOf("2022-03-13 18:50:54.000"));
		Date actual = existingOrder.getOrderDate();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetOrderDateWithNull() {
		//given
		Date expected = Timestamp.valueOf("2022-03-12 13:12:18.000");
		//when
		existingOrder.setOrderDate(null);
		Date actual = existingOrder.getOrderDate();
		//then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetSetAccounts() {
		//given
		Accounts expected = new Accounts(4L, "Steph", "&7C,Mt67@)skZO3", "Steph", "Ann", 30, "stepha@email.com", "07853388831", false);
		//when
		existingOrder.setAccounts(expected);
		Accounts actual = existingOrder.getAccounts();
		//then
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testToString() {
		//given
		String expected = "Orders [id=1, accountsId=4, orderDate=2022-03-12 13:12:18.0]";
		//when
		String actual = existingOrder.toString();
		//then
		assertEquals(expected, actual);
	}
}
