package com.qa.gamestore.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum UserRoles {
	CUSTOMER, //(Sets.newHashSet()),
	ADMIN; //(Sets.newHashSet(UserPermissions.ACCOUNTS_CREATE, UserPermissions.ACCOUNTS_READ, UserPermissions.ACCOUNTS_UPDATE, UserPermissions.ACCOUNTS_DELETE));
	
//	private final Set<UserPermissions> permissions;
//	
//	UserRoles(Set<UserPermissions> permissions) {
//		this.permissions = permissions;
//	}
	
}
