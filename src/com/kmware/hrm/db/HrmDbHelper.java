package com.kmware.hrm.db;

import com.kmware.hrm.model.Addresses;
import com.kmware.hrm.model.Roles;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

public class HrmDbHelper {

	private static final String LOGTAG = HrmDbHelper.class.getSimpleName();

	public static Addresses[] getAddresses(ContentResolver cr) {

		Cursor c = cr.query(HrmProvider.ADDRESSES_URI, null, null, null, null);
		Addresses[] addresses = new Addresses[c.getCount()];
		int i = 0;

		try {
			c.moveToFirst();
			while (!c.isAfterLast()) {
				addresses[i] = HrmHelper.cursorToAddresses(c);
				c.moveToNext();
				i++;
			}
		} finally {
			c.close();
		}

		// for (Addresses address : addresses) {
		// address.setOperations(getOperations(cr, address.getOrderId()));
		// }
		return addresses;
	}
	
	public static Roles[] getRoles(ContentResolver cr) {

		Cursor c = cr.query(HrmProvider.ROLES_URI, null, null, null, null);
		Roles[] roles = new Roles[c.getCount()];
		int i = 0;

		try {
			c.moveToFirst();
			while (!c.isAfterLast()) {
				roles[i] = HrmHelper.cursorToRoles(c);
				c.moveToNext();
				i++;
			}
		} finally {
			c.close();
		}

		// for (Roles role : roles) {
		// role.setOperations(getOperations(cr, role.getOrderId()));
		// }
		return roles;
	}

	public static Uri saveRoles(ContentResolver cr, Roles role) {
		return cr.insert(HrmProvider.ROLES_URI, HrmHelper.rolesToContent(role));	
	}
	
}
