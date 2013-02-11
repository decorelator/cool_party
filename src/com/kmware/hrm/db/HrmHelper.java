package com.kmware.hrm.db;

import com.kmware.hrm.db.HrmProvider.ADDRESS_COLUMNS;
import com.kmware.hrm.db.HrmProvider.ROLES_COLUMNS;
import com.kmware.hrm.model.Addresses;
import com.kmware.hrm.model.Roles;

import android.content.ContentValues;
import android.database.Cursor;

public class HrmHelper {

	public static String LOGTAG = HrmHelper.class.getSimpleName();

	public static ContentValues adressToContent(Addresses addr) {

		ContentValues cv = new ContentValues();
		cv.put(ADDRESS_COLUMNS.ID, addr.getId());
		cv.put(ADDRESS_COLUMNS.COUNTRY, addr.getCountry());
		cv.put(ADDRESS_COLUMNS.CITY, addr.getCity());
		cv.put(ADDRESS_COLUMNS.ADDRESS_LINE1, addr.getAddressLine1());
		cv.put(ADDRESS_COLUMNS.ADDRESS_LINE2, addr.getAddressLine2());
		cv.put(ADDRESS_COLUMNS.POSTAL_CODE, addr.getPostalCode());
		cv.put(ADDRESS_COLUMNS.CREATED_ID, addr.getCreatorId());
		cv.put(ADDRESS_COLUMNS.CREATED, addr.getCreated());
		cv.put(ADDRESS_COLUMNS.IS_DELETED, addr.getIsDeleted());
		return cv;
	}

	public static Addresses cursorToAddresses(Cursor c) {
		Addresses adr = new Addresses();

		adr.setCountry(c.getString(c.getColumnIndex(ADDRESS_COLUMNS.COUNTRY)));
		adr.setCity(c.getString(c.getColumnIndex(ADDRESS_COLUMNS.CITY)));
		adr.setAddressLine1(c.getString(c.getColumnIndex(ADDRESS_COLUMNS.ADDRESS_LINE1)));
		adr.setAddressLine2(c.getString(c.getColumnIndex(ADDRESS_COLUMNS.ADDRESS_LINE2)));
		adr.setPostalCode(c.getString(c.getColumnIndex(ADDRESS_COLUMNS.POSTAL_CODE)));
		adr.setCreatorId(c.getInt(c.getColumnIndex(ADDRESS_COLUMNS.CREATED_ID)));
		adr.setCreated(c.getString(c.getColumnIndex(ADDRESS_COLUMNS.CREATED)));
		adr.setIsDeleted(c.getInt(c.getColumnIndex(ADDRESS_COLUMNS.IS_DELETED)));

		return adr;
	}

	public static ContentValues rolesToContent(Roles roles) {

		ContentValues cv = new ContentValues();
		
		cv.put(ROLES_COLUMNS.APPLICATION_ID, roles.getAppId());
		//cv.put(ROLES_COLUMNS.ID, roles.getRoleId());
		cv.put(ROLES_COLUMNS.ROLE_NAME, roles.getRoleName());
		cv.put(ROLES_COLUMNS.LOWERED_ROLE_NAME, roles.getLoweredRoleName());
		cv.put(ROLES_COLUMNS.DESCRIPTION, roles.getDescription());
		
		return cv;
	}
	
	public static Roles cursorToRoles(Cursor c) {
		Roles roles = new Roles();

		roles.setAppId(c.getInt(c.getColumnIndex(ROLES_COLUMNS.APPLICATION_ID)));
		roles.setRoleId(c.getInt(c.getColumnIndex(ROLES_COLUMNS.ID)));
		roles.setRoleName(c.getString(c.getColumnIndex(ROLES_COLUMNS.ROLE_NAME)));
		roles.setLoweredRoleName(c.getString(c.getColumnIndex(ROLES_COLUMNS.LOWERED_ROLE_NAME)));
		roles.setDescription(c.getString(c.getColumnIndex(ROLES_COLUMNS.DESCRIPTION)));
		
		return roles;
	}
	
}
