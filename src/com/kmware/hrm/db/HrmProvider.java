package com.kmware.hrm.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class HrmProvider extends ContentProvider {

	private static final String LOGTAG = HrmProvider.class.getSimpleName();

	private static final String databaseName = "hrm.db";
	private static final int DATABASE_VERSION = 1;

	private SQLiteOpenHelper openHelper;

	public static final Uri ADDRESSES_URI = Uri.parse("content://hrm/addresses");

	public static final Uri APPLICATIONS_URI = Uri.parse("content://hrm/applications");

	public static final Uri PATHS_URI = Uri.parse("content://hrm/paths");

	public static final Uri PERSONALIZATION_ALL_USERS_URI = Uri.parse("content://hrm/persAllUsers");

	public static final Uri PERSONALIZATION_PER_USER_URI = Uri.parse("content://hrm/persPerUser");

	public static final Uri PROFILE_URI = Uri.parse("content://hrm/profile");

	public static final Uri ROLES_URI = Uri.parse("content://hrm/roles");

	public static final Uri SCHEMA_VERSIONS_URI = Uri.parse("content://hrm/schemaVersions");

	public static final Uri USERS_URI = Uri.parse("content://hrm/users");

	public static final Uri USERS_IN_ROLES_URI = Uri.parse("content://hrm/usersInRoles");

	public static final Uri WEB_EVENT_EVENTS_URI = Uri.parse("content://hrm/wevEventEvents");

	public static final Uri AUDIT_LOGS_URI = Uri.parse("content://hrm/auditLogs");

	public static final Uri CONTACTS_URI = Uri.parse("content://hrm/contacts");

	public static final Uri CONTACT_TYPES_URI = Uri.parse("content://hrm/contactTypes");

	public static final Uri CUSTOMERS_URI = Uri.parse("content://hrm/customers");

	public static final Uri DEPARTMENTS_URI = Uri.parse("content://hrm/departments");

	public static final Uri EDM_METADATA_URI = Uri.parse("content://hrm/edmMetadata");

	public static final Uri EVALUATIONS_URI = Uri.parse("content://hrm/evaluations");

	public static final Uri EXPERIENCE_COMPANIES_URI = Uri.parse("content://hrm/expCompanies");

	public static final Uri EXPERIENCE_COMPANY_PROJECTS_URI = Uri.parse("content://hrm/expCompProjects");

	public static final Uri FILE_ATTACHMENTS_URI = Uri.parse("content://hrm/fileAttachments");

	public static final Uri INTERVIEW_ALERTS_URI = Uri.parse("content://hrm/intAlerts");

	public static final Uri INTERVIEW_PERSONS_URI = Uri.parse("content://hrm/intPersons");

	public static final Uri INTERVIEWS_URI = Uri.parse("content://hrm/interviews");

	public static final Uri INTERVIEW_TEMPLATE_PERSONS_URI = Uri.parse("content://hrm/intTemplatePersons");

	public static final Uri INTERVIEW_TEMPLATES_URI = Uri.parse("content://hrm/intTemplates");

	public static final Uri INTERVIEW_WORKFLOWS_URI = Uri.parse("content://hrm/intTemplateWork");

	public static final Uri LANGUAGES_URI = Uri.parse("content://hrm/languages");

	public static final Uri LEVELS_URI = Uri.parse("content://hrm/levels");

	public static final Uri PEOPLE_URI = Uri.parse("content://hrm/people");

	public static final Uri PERSON_EXPERIENCES_URI = Uri.parse("content://hrm/persExp");

	public static final Uri PERSON_FILE_ATTACHMENTS_URI = Uri.parse("content://hrm/persFileAtt");

	public static final Uri PERSON_LANGUAGES_URI = Uri.parse("content://hrm/persLanguage");

	public static final Uri PERSON_POSSIBLE_POSITIONS_URI = Uri.parse("content://hrm/persPossPositions");

	public static final Uri PERSON_REGISTRATIONS_URI = Uri.parse("content://hrm/persRegistration");

	public static final Uri PERSON_SKILL_LEVELS_URI = Uri.parse("content://hrm/persSkillLvls");

	public static final Uri PERSON_SKILLS_URI = Uri.parse("content://hrm/persSkills");

	public static final Uri PERSON_SUPERVISORS_URI = Uri.parse("content://hrm/persSupervisors");

	public static final Uri POSITION_DEPARTMENTS_URI = Uri.parse("content://hrm/posDepartments");

	public static final Uri POSITION_PERSONS_URI = Uri.parse("content://hrm/posPersons");

	public static final Uri POSITIONS_URI = Uri.parse("content://hrm/positions");

	public static final Uri POSITION_SKILL_LEVELS_URI = Uri.parse("content://hrm/posSkillLvls");

	public static final Uri POSITION_SKILLS_URI = Uri.parse("content://hrm/posSkills");

	public static final Uri POSITION_TEMPLATE_DEPARTMENTS_URI = Uri.parse("content://hrm/posTemplateDepartment");

	public static final Uri POSITION_TEMPLATES_URI = Uri.parse("content://hrm/posTemplates");

	public static final Uri POSITION_TEMPLATE_SKILL_LEVELS_URI = Uri.parse("content://hrm/posTemplateSkillLvls");

	public static final Uri PROJECTS_DEPARTMENTS_URI = Uri.parse("content://hrm/projDepartments");

	public static final Uri PROJECTS_ENDED_ALERTS_URI = Uri.parse("content://hrm/projEndedAlerts");

	public static final Uri PROJECTS_URI = Uri.parse("content://hrm/projects");

	public static final Uri PROJECTS_SKILLS_URI = Uri.parse("content://hrm/projSkills");

	public static final Uri PROJECTS_STAKE_HOLDERS_URI = Uri.parse("content://hrm/projStakeHolders");

	public static final Uri PROJECTS_PASSWORD_MARKERS_URI = Uri.parse("content://hrm/projPassMarkers");

	public static final Uri SEARCH_CRITERIAS_URI = Uri.parse("content://hrm/serachCriterias");

	public static final Uri SEARCH_SELECTIONS_URI = Uri.parse("content://hrm/serachSelections");

	public static final Uri SETTINGS_URI = Uri.parse("content://hrm/settings");

	public static final Uri SKILLS_URI = Uri.parse("content://hrm/skills");

	public static final Uri TEMPLATES_URI = Uri.parse("content://hrm/templates");

	private static final String[] TABLE_NAMES = { "addresses", "applications", "paths", "persAllUsers", "persPerUser", "profile", "roles", "schemaVersions",
			"users", "usersInRoles", "wevEventEvents", "auditLogs", "contacts", "contactTypes", "customers", "departments", "edmMetadata", "evaluations",
			"expCompanies", "expCompProjects", "fileAttachments", "intAlerts", "intPersons", "interviews", "intTemplatePersons", "intTemplates",
			"intTemplateWork", "languages", "levels", "people", "persExp", "persFileAtt", "persLanguage", "persPossPositions", "persRegistration",
			"persSkillLvls", "persSkills", "persSupervisors", "posDepartments", "posPersons", "positions", "posSkillLvls", "posSkills",
			"posTemplateDepartment", "posTemplates", "posTemplateSkillLvls", "projDepartments", "projEndedAlerts", "projects", "projSkills",
			"projStakeHolders", "projPassMarkers", "serachCriterias", "serachSelections", "settings", "skills", "templates" };

	static final int URI_ADDRESSES = 0;

	static final int URI_APPLICATIONS = 1;

	static final int URI_PATHS = 2;

	static final int URI_PERSONALIZATION_ALL_USERS = 3;

	static final int URI_PERSONALIZATION_PER_USER = 4;

	static final int URI_PROFILE = 5;

	static final int URI_ROLES = 6;

	static final int URI_SCHEMA_VERSIONS = 7;

	static final int URI_USERS = 8;

	static final int URI_USERS_IN_ROLES = 9;

	static final int URI_WEB_EVENT_EVENTS = 10;

	static final int URI_AUDIT_LOGS = 11;

	static final int URI_CONTACTS = 12;

	static final int URI_CONTACT_TYPES = 13;

	static final int URI_CUSTOMERS = 14;

	static final int URI_DEPARTMENTS = 15;

	static final int URI_EDM_METADATA = 16;

	static final int URI_EVALUATIONS = 17;

	static final int URI_EXPERIENCE_COMPANIES = 18;

	static final int URI_EXPERIENCE_COMPANY_PROJECTS = 19;

	static final int URI_FILE_ATTACHMENTS = 20;

	static final int URI_INTERVIEW_ALERTS = 21;

	static final int URI_INTERVIEW_PERSONS = 22;

	static final int URI_INTERVIEWS = 23;

	static final int URI_INTERVIEW_TEMPLATE_PERSONS = 24;

	static final int URI_INTERVIEW_TEMPLATES = 25;

	static final int URI_INTERVIEW_WORKFLOWS = 26;

	static final int URI_LANGUAGES = 27;

	static final int URI_LEVELS = 28;

	static final int URI_PEOPLE = 29;

	static final int URI_PERSON_EXPERIENCES = 30;

	static final int URI_PERSON_FILE_ATTACHMENTS = 31;

	static final int URI_PERSON_LANGUAGES = 32;

	static final int URI_PERSON_POSSIBLE_POSITIONS = 33;

	static final int URI_PERSON_REGISTRATIONS = 34;

	static final int URI_PERSON_SKILL_LEVELS = 35;

	static final int URI_PERSON_SKILLS = 36;

	static final int URI_PERSON_SUPERVISORS = 37;

	static final int URI_POSITION_DEPARTMENTS = 38;

	static final int URI_POSITION_PERSONS = 39;

	static final int URI_POSITIONS = 40;

	static final int URI_POSITION_SKILL_LEVELS = 41;

	static final int URI_POSITION_SKILLS = 42;

	static final int URI_POSITION_TEMPLATE_DEPARTMENTS = 43;

	static final int URI_POSITION_TEMPLATES = 44;

	static final int URI_POSITION_TEMPLATE_SKILL_LEVELS = 45;

	static final int URI_PROJECTS_DEPARTMENTS = 46;

	static final int URI_PROJECTS_ENDED_ALERTS = 47;

	static final int URI_PROJECTS = 48;

	static final int URI_PROJECTS_SKILLS = 49;

	static final int URI_PROJECTS_STAKE_HOLDERS = 50;

	static final int URI_PROJECTS_PASSWORD_MARKERS = 51;

	static final int URI_SEARCH_CRITERIAS = 52;

	static final int URI_SEARCH_SELECTIONS = 53;

	static final int URI_SETTINGS = 54;

	static final int URI_SKILLS = 55;

	static final int URI_TEMPLATES = 56;

	public static final UriMatcher URI_MATCHER;

	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_ADDRESSES], URI_ADDRESSES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_APPLICATIONS], URI_APPLICATIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PATHS], URI_PATHS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSONALIZATION_ALL_USERS], URI_PERSONALIZATION_ALL_USERS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSONALIZATION_PER_USER], URI_PERSONALIZATION_PER_USER);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROFILE], URI_PROFILE);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_ROLES], URI_ROLES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_SCHEMA_VERSIONS], URI_SCHEMA_VERSIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_USERS], URI_USERS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_USERS_IN_ROLES], URI_USERS_IN_ROLES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_WEB_EVENT_EVENTS], URI_WEB_EVENT_EVENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_AUDIT_LOGS], URI_AUDIT_LOGS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_CONTACTS], URI_CONTACTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_CONTACT_TYPES], URI_CONTACT_TYPES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_CUSTOMERS], URI_CUSTOMERS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_DEPARTMENTS], URI_DEPARTMENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_EDM_METADATA], URI_EDM_METADATA);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_EVALUATIONS], URI_EVALUATIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_EXPERIENCE_COMPANIES], URI_EXPERIENCE_COMPANIES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_EXPERIENCE_COMPANY_PROJECTS], URI_EXPERIENCE_COMPANY_PROJECTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_FILE_ATTACHMENTS], URI_FILE_ATTACHMENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_INTERVIEW_ALERTS], URI_INTERVIEW_ALERTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_INTERVIEW_PERSONS], URI_INTERVIEW_PERSONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_INTERVIEWS], URI_INTERVIEWS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_INTERVIEW_TEMPLATE_PERSONS], URI_INTERVIEW_TEMPLATE_PERSONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_INTERVIEW_TEMPLATES], URI_INTERVIEW_TEMPLATES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_INTERVIEW_WORKFLOWS], URI_INTERVIEW_WORKFLOWS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_LANGUAGES], URI_LANGUAGES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_LEVELS], URI_LEVELS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PEOPLE], URI_PEOPLE);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_EXPERIENCES], URI_PERSON_EXPERIENCES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_FILE_ATTACHMENTS], URI_PERSON_FILE_ATTACHMENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_LANGUAGES], URI_PERSON_LANGUAGES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_POSSIBLE_POSITIONS], URI_PERSON_POSSIBLE_POSITIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_REGISTRATIONS], URI_PERSON_REGISTRATIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_SKILL_LEVELS], URI_PERSON_SKILL_LEVELS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_SKILLS], URI_PERSON_SKILLS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PERSON_SUPERVISORS], URI_PERSON_SUPERVISORS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_DEPARTMENTS], URI_POSITION_DEPARTMENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_PERSONS], URI_POSITION_PERSONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITIONS], URI_POSITIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_SKILL_LEVELS], URI_POSITION_SKILL_LEVELS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_SKILLS], URI_POSITION_SKILLS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_TEMPLATE_DEPARTMENTS], URI_POSITION_TEMPLATE_DEPARTMENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_TEMPLATES], URI_POSITION_TEMPLATES);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_POSITION_TEMPLATE_SKILL_LEVELS], URI_POSITION_TEMPLATE_SKILL_LEVELS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROJECTS_DEPARTMENTS], URI_PROJECTS_DEPARTMENTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROJECTS_ENDED_ALERTS], URI_PROJECTS_ENDED_ALERTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROJECTS], URI_PROJECTS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROJECTS_SKILLS], URI_PROJECTS_SKILLS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROJECTS_STAKE_HOLDERS], URI_PROJECTS_STAKE_HOLDERS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_PROJECTS_PASSWORD_MARKERS], URI_PROJECTS_PASSWORD_MARKERS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_SEARCH_CRITERIAS], URI_SEARCH_CRITERIAS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_SEARCH_SELECTIONS], URI_SEARCH_SELECTIONS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_SETTINGS], URI_SETTINGS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_SKILLS], URI_SKILLS);

		URI_MATCHER.addURI("hrm", TABLE_NAMES[URI_TEMPLATES], URI_TEMPLATES);
	}

	public HrmProvider() {

	}

	@Override
	public boolean onCreate() {
		final Context context = getContext();
		openHelper = new DatabaseHelper(context);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws IllegalStateException {
		SQLiteDatabase db = openHelper.getReadableDatabase();

		int match = URI_MATCHER.match(uri);
		if (match == -1) {
			throw new IllegalArgumentException("Unknown URL");
		}

		Cursor c = null;
		StringBuffer sql;

		try {
			switch (match) {

			case 0:
				if (selectionArgs != null && selectionArgs.length > 1) {

					sql = new StringBuffer();

					sql.append("");
					sql.append("");

					c = db.rawQuery(sql.toString(), null);
				}
				break;
			}

		} catch (Exception e) {
			Log.e(LOGTAG, "Error getting data from DB: table " + TABLE_NAMES[match], e);

			if (!c.isClosed()) {

				c.close();

			}

		}
		return c;
	}

	@Override
	public Uri insert(Uri url, ContentValues initialValues) {

		SQLiteDatabase db = openHelper.getWritableDatabase();
		long id = -1;
		Uri uri = null;
		Cursor c = null;

		int match = URI_MATCHER.match(url);

		switch (match) {
		case URI_ADDRESSES:
			try {

				c = db.rawQuery("SELECT COUNT(*) FROM address WHERE ID = ?", new String[] { initialValues.getAsString(ADDRESS_COLUMNS.ID) });
				if (c.moveToFirst() != false && c.getInt(0) == 0) {
					id = db.insert(TABLE_NAMES[0], ADDRESS_COLUMNS.COUNTRY, initialValues);
				} else {
					String.format("The address with id = {0} is already in DB", initialValues.getAsString(ADDRESS_COLUMNS.IS_DELETED));
				}

			} finally {
				c.close();
			}
			break;

		case URI_ROLES:
			try {

				c = db.rawQuery("SELECT COUNT(*) FROM roles WHERE ID = ?", new String[] { initialValues.getAsString(ROLES_COLUMNS.ID) });
				if (c.moveToFirst() != false && c.getInt(0) == 0) {
					id = db.insert(TABLE_NAMES[6], ROLES_COLUMNS.ROLE_NAME, initialValues);
				} else {
					String.format("The roles with id = {0} is already in DB", initialValues.getAsString(ADDRESS_COLUMNS.IS_DELETED));
				}

			} finally {
				c.close();
			}
			break;

		}
		if (id > 0) {
			uri = ContentUris.withAppendedId(url, id);
		}
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int count = 0;

		int match = URI_MATCHER.match(uri);

		if (match == -1) {
			throw new IllegalArgumentException("Unknown URL");
		}

		try {
			count = db.delete(TABLE_NAMES[match], selection, selectionArgs);
		} catch (Exception e) {
			Log.e(LOGTAG, "Error deleting data from " + TABLE_NAMES[match], e);
		}
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = openHelper.getWritableDatabase();

		int count = 0;

		int match = URI_MATCHER.match(uri);
		if (match == -1) {
			throw new IllegalArgumentException("Unknown URL");
		}

		try {
			count = db.update(TABLE_NAMES[match], values, selection, selectionArgs);
		} catch (Exception e) {
			Log.e(LOGTAG, "Error updating table " + TABLE_NAMES[match], e);
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		return "";
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private Context context;

		public DatabaseHelper(Context context) {
			super(context, databaseName, null, DATABASE_VERSION);
			this.context = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE addresses (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "Country TEXT, " + "City TEXT, " + "AddressLine1 TEXT, "
					+ "AddressLine2 TEXT, " + "PostalCode TEXT, " + "CreatorID INTEGER, " + "Created DATETIME, " + "IsDeleted INTEGER " + ");");

			db.execSQL("CREATE TABLE roles (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "RoleName TEXT, " + "LoweredRoleName TEXT, " + "Description TEXT "
					+ ");");
			String[] roles = context.getResources().getStringArray(com.kmware.hrm.R.array.people_roles);
			Log.e(LOGTAG, roles.toString());
			for (int i = 0; i < roles.length; i++) {
				db.execSQL(String.format("INSERT INTO roles (RoleName, LoweredRoleName, Description) " + "VALUES ('%s', 0, 0)", roles[i].replace("'", "''")));
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(LOGTAG, "Upgrading database from version " + oldVersion + " to " + newVersion);

			switch (newVersion) {

			case 2:

				break;

			}

			// db.execSQL("DELETE FROM news");
			//
			// eFarmerSettings settings = eFarmerSettings.getInstance();
			// settings.loadFromDb(context);
			// settings.setHomePage(context,
			// context.getResources().getString(R.string.homepage_base));
			// settings.setShowWhatsNew(context, true);

		}

	}

	public static class ADDRESS_COLUMNS {
		public static String ID = "_id";
		public static String COUNTRY = "Country";
		public static String CITY = "City";
		public static String ADDRESS_LINE1 = "AddresLine1";
		public static String ADDRESS_LINE2 = "AddresLine2";
		public static String POSTAL_CODE = "PostalCode";
		public static String CREATED_ID = "CreatedId";
		public static String CREATED = "Created";
		public static String IS_DELETED = "IsDeleted";
	}

	public static class ROLES_COLUMNS {
		public static String APPLICATION_ID = "app_id";
		public static String ID = "_id";
		public static String ROLE_NAME = "RoleName";
		public static String LOWERED_ROLE_NAME = "LoweredRoleName";
		public static String DESCRIPTION = "RoleName";
	}

}
