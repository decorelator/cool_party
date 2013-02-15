package com.kmware.hrm.db;

import java.util.ArrayList;
import java.util.List;

import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.People;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;
import com.kmware.hrm.model.Roles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final String LOGTAG = DatabaseHandler.class.getSimpleName();
	private Context context;
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "HRM.db";

	// Tables name
	private static final String TABLE_ROLES = "roles";
	private static final String TABLE_PEOPLES = "peoples";
	private static final String TABLE_PROJECTS = "projects";
	private static final String TABLE_POSITIONS = "positions";
	private static final String TABLE_INTERVIEWS = "interviews";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			// Creating Table Peoples
			db.execSQL("CREATE TABLE " + TABLE_PEOPLES + " ( " + PEOPLES_COLUMNS.ID + " INTEGER PRIMARY KEY, " + PEOPLES_COLUMNS.NAME + " TEXT, "
					+ PEOPLES_COLUMNS.LAST_NAME + " TEXT," + PEOPLES_COLUMNS.EMAIL + " TEXT," + PEOPLES_COLUMNS.PHONE + " LONG," + PEOPLES_COLUMNS.SKYPE
					+ " TEXT," + PEOPLES_COLUMNS.EMPLOYMENT_DATE + " DATE," + PEOPLES_COLUMNS.ROLES + " TEXT," + PEOPLES_COLUMNS.POSITION + " INTEGER,"
					+ PEOPLES_COLUMNS.STATUS_ID + " INTEGER" + ")");
			// Creating Table Projects
			db.execSQL("CREATE TABLE " + TABLE_PROJECTS + " ( " + PROJECT_COLUMNS.ID + " INTEGER PRIMARY KEY, " + PROJECT_COLUMNS.NAME + " TEXT,"
					+ PROJECT_COLUMNS.EMAIL + " TEXT," + PROJECT_COLUMNS.PHONE + " LONG," + PROJECT_COLUMNS.SKYPE + " TEXT," + PROJECT_COLUMNS.START_DATE
					+ " DATE," + PROJECT_COLUMNS.END_DATE + " DATE," + PROJECT_COLUMNS.STATUS_ID + " INTEGER" + ")");
			// Creating Table Positions
			db.execSQL("CREATE TABLE " + TABLE_POSITIONS + " ( " + POSITION_COLUMNS.ID + " INTEGER PRIMARY KEY, " + POSITION_COLUMNS.NAME + " TEXT,"
					+ POSITION_COLUMNS.DESCRIPTION + " TEXT" + ")");
			// Creating Table Interview
			db.execSQL("CREATE TABLE " + TABLE_INTERVIEWS + " ( " + INTERVIEW_COLUMNS.ID + " INTEGER PRIMARY KEY, " + INTERVIEW_COLUMNS.NAME + " TEXT,"
					+ INTERVIEW_COLUMNS.DATE + " DATE," + INTERVIEW_COLUMNS.TIME + " TIME," + INTERVIEW_COLUMNS.DESCRIPTION + " TEXT" + ")");
			// Creating Table Roles
			db.execSQL("CREATE TABLE " + TABLE_ROLES + " ( " + ROLES_COLUMNS.ID + " INTEGER PRIMARY KEY, " + ROLES_COLUMNS.NAME + " TEXT,"
					+ ROLES_COLUMNS.LOWER_NAME + " TEXT," + ROLES_COLUMNS.DESCRIPTION + " TEXT," + " UNIQUE( " + ROLES_COLUMNS.NAME + " ) ON CONFLICT REPLACE "
					+ ")");

			String[] roles = context.getResources().getStringArray(com.kmware.hrm.R.array.people_roles);

			for (int i = 0; i < roles.length; i++) {
				db.execSQL(String.format("INSERT INTO roles (name) " + "VALUES ('%s')", roles[i]));
			}
			Log.i(LOGTAG, "DataBase was Create");
		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "DataBase was not created");
		}
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSITIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERVIEWS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLES);

		// Create tables again
		onCreate(db);
	}

	public static class PEOPLES_COLUMNS {
		public static String ID = "_id";
		public static String NAME = "name";
		public static String LAST_NAME = "last_name";
		public static String EMAIL = "email";
		public static String PHONE = "phone";
		public static String SKYPE = "skype";
		public static String EMPLOYMENT_DATE = "empl_date";
		public static String ROLES = "roles";
		public static String POSITION = "position";
		public static String STATUS_ID = "statusID";
	}

	public static class PROJECT_COLUMNS {
		public static String ID = "_id";
		public static String NAME = "name";
		public static String EMAIL = "email";
		public static String PHONE = "phone";
		public static String SKYPE = "skype";
		public static String START_DATE = "start_date";
		public static String END_DATE = "end_date";
		public static String STATUS_ID = "statusID";
	}

	public static class POSITION_COLUMNS {
		public static String ID = "_id";
		public static String NAME = "name";
		public static String DESCRIPTION = "description";
	}

	public static class INTERVIEW_COLUMNS {
		public static String ID = "_id";
		public static String NAME = "name";
		public static String DATE = "date";
		public static String TIME = "time";
		public static String DESCRIPTION = "description";
	}

	public static class ROLES_COLUMNS {
		public static String ID = "_id";
		public static String NAME = "name";
		public static String LOWER_NAME = "lowerName";
		public static String DESCRIPTION = "description";
	}

	// ====PEOPLE===================================================================================================

	// Adding new person
	public void addPerson(People person) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PEOPLES_COLUMNS.NAME, person.getName()); // Person Name
		values.put(PEOPLES_COLUMNS.LAST_NAME, person.getLastname());
		values.put(PEOPLES_COLUMNS.EMAIL, person.getEmail());
		values.put(PEOPLES_COLUMNS.PHONE, person.getPhone());
		values.put(PEOPLES_COLUMNS.SKYPE, person.getSkype());
		values.put(PEOPLES_COLUMNS.EMPLOYMENT_DATE, person.getEmployment_date());
		values.put(PEOPLES_COLUMNS.ROLES, person.getRole());
		values.put(PEOPLES_COLUMNS.POSITION, person.getPosition());
		values.put(PEOPLES_COLUMNS.STATUS_ID, person.getStatus_id());

		// Inserting Row
		db.insert(TABLE_PEOPLES, null, values);
		db.close(); // Closing database connection
	}

	// Getting one Person
	public People getPerson(int id) {
		if (id != 0) {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_PEOPLES, new String[] { PEOPLES_COLUMNS.ID, PEOPLES_COLUMNS.NAME, PEOPLES_COLUMNS.LAST_NAME, PEOPLES_COLUMNS.EMAIL,
					PEOPLES_COLUMNS.PHONE, PEOPLES_COLUMNS.SKYPE, PEOPLES_COLUMNS.EMPLOYMENT_DATE, PEOPLES_COLUMNS.ROLES, PEOPLES_COLUMNS.POSITION,
					PEOPLES_COLUMNS.STATUS_ID }, PEOPLES_COLUMNS.ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			People person = new People(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
			person.setLastname(cursor.getString(2));
			person.setEmail(cursor.getString(3));
			person.setPhone(Integer.parseInt(cursor.getString(4)));
			person.setSkype(cursor.getString(5));
			person.setEmployment_date(cursor.getString(6));
			person.setRole(cursor.getString(7));
			person.setPosition(Integer.parseInt(cursor.getString(8)));
			person.setStatus_id(Integer.parseInt(cursor.getString(9)));
			cursor.close();
			db.close();
			return person;
		}
		// return person
		return null;
	}

	// Getting All People
	public List<People> getAllPeople() {
		List<People> list = new ArrayList<People>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PEOPLES + " ORDER BY " + PEOPLES_COLUMNS.LAST_NAME + " ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				People person = new People();
				person.setId(Integer.parseInt(cursor.getString(0)));
				person.setName(cursor.getString(1));
				person.setLastname(cursor.getString(2));
				person.setEmail(cursor.getString(3));
				person.setPhone(Integer.parseInt(cursor.getString(4)));
				person.setSkype(cursor.getString(5));
				person.setEmployment_date(cursor.getString(6));
				person.setRole(cursor.getString(7));
				person.setPosition(Integer.parseInt(cursor.getString(8)));
				person.setStatus_id(Integer.parseInt(cursor.getString(9)));
				// Adding to list
				list.add(person);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// return list
		return list;
	}

	// Updating single person
	public int updatePerson(People person) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PEOPLES_COLUMNS.NAME, person.getName());
		values.put(PEOPLES_COLUMNS.LAST_NAME, person.getLastname());
		values.put(PEOPLES_COLUMNS.EMAIL, person.getEmail());
		values.put(PEOPLES_COLUMNS.PHONE, person.getPhone());
		values.put(PEOPLES_COLUMNS.SKYPE, person.getSkype());
		values.put(PEOPLES_COLUMNS.EMPLOYMENT_DATE, person.getEmployment_date());
		values.put(PEOPLES_COLUMNS.ROLES, person.getRole());
		values.put(PEOPLES_COLUMNS.POSITION, person.getPosition());
		values.put(PEOPLES_COLUMNS.STATUS_ID, person.getStatus_id());

		// updating row
		int result = db.update(TABLE_PEOPLES, values, PEOPLES_COLUMNS.ID + " = ?", new String[] { String.valueOf(person.getId()) });
		db.close();
		return result;
	}

	// Deleting single person
	public void deletePerson(People person) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PEOPLES, PEOPLES_COLUMNS.ID + " = ?", new String[] { String.valueOf(person.getId()) });
		db.close();
	}

	// Getting people Count
	public int getPeopleCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PEOPLES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}

	// ====PROJECT==================================================================================================

	// Adding new project
	public void addProject(Project project) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PROJECT_COLUMNS.NAME, project.getName());
		values.put(PROJECT_COLUMNS.EMAIL, project.getEmail());
		values.put(PROJECT_COLUMNS.PHONE, project.getPhone());
		values.put(PROJECT_COLUMNS.SKYPE, project.getSkype());
		values.put(PROJECT_COLUMNS.START_DATE, project.getsData());
		values.put(PROJECT_COLUMNS.END_DATE, project.geteData());
		values.put(PROJECT_COLUMNS.STATUS_ID, project.getStatus_id());
		// Inserting Row
		db.insert(TABLE_PROJECTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting one Project
	public Project getProject(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PROJECTS, new String[] { PROJECT_COLUMNS.ID, PROJECT_COLUMNS.NAME, PROJECT_COLUMNS.EMAIL, PROJECT_COLUMNS.PHONE,
				PROJECT_COLUMNS.SKYPE, PROJECT_COLUMNS.START_DATE, PROJECT_COLUMNS.END_DATE, PROJECT_COLUMNS.STATUS_ID }, PROJECT_COLUMNS.ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Project project = new Project(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		project.setEmail(cursor.getString(2));
		project.setPhone(Integer.parseInt(cursor.getString(3)));
		project.setSkype(cursor.getString(4));
		project.setsData(cursor.getString(5));
		project.seteData(cursor.getString(6));
		project.setStatus_id(Integer.parseInt(cursor.getString(7)));
		cursor.close();
		db.close();
		// return project
		return project;
	}

	// Getting All Projects
	public List<Project> getAllProject() {
		List<Project> list = new ArrayList<Project>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PROJECTS + " ORDER BY " + PROJECT_COLUMNS.NAME + " ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Project project = new Project();
				project.setId(Integer.parseInt(cursor.getString(0)));
				project.setName(cursor.getString(1));
				project.setEmail(cursor.getString(2));
				project.setPhone(Integer.parseInt(cursor.getString(3)));
				project.setSkype(cursor.getString(4));
				project.setsData(cursor.getString(5));
				project.seteData(cursor.getString(6));
				project.setStatus_id(Integer.parseInt(cursor.getString(7)));
				// Adding to list
				list.add(project);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// return list
		return list;
	}

	// Updating single project
	public int updateProject(Project project) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PROJECT_COLUMNS.NAME, project.getName());
		values.put(PROJECT_COLUMNS.EMAIL, project.getEmail());
		values.put(PROJECT_COLUMNS.PHONE, project.getPhone());
		values.put(PROJECT_COLUMNS.SKYPE, project.getSkype());
		values.put(PROJECT_COLUMNS.START_DATE, project.getsData());
		values.put(PROJECT_COLUMNS.END_DATE, project.geteData());
		values.put(PROJECT_COLUMNS.STATUS_ID, project.getStatus_id());

		// updating row
		int result = db.update(TABLE_PROJECTS, values, PROJECT_COLUMNS.ID + " = ?", new String[] { String.valueOf(project.getId()) });
		db.close();
		return result;
	}

	// Deleting single project
	public void deleteProject(Project project) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROJECTS, PROJECT_COLUMNS.ID + " = ?", new String[] { String.valueOf(project.getId()) });
		db.close();
	}

	// Getting project Count
	public int getProjectCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PROJECTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}

	// ====POSITION=================================================================================================

	// Adding new position
	public void addPosition(Position position) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(POSITION_COLUMNS.NAME, position.getName());
		values.put(POSITION_COLUMNS.DESCRIPTION, position.getDescription());
		// Inserting Row
		db.insert(TABLE_POSITIONS, null, values);
		db.close(); // Closing database connection
	}

	// Getting one Position
	public Position getPosition(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_POSITIONS, new String[] { POSITION_COLUMNS.ID, POSITION_COLUMNS.NAME, POSITION_COLUMNS.DESCRIPTION },
				POSITION_COLUMNS.ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Position position = new Position(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		position.setDescription(cursor.getString(2));
		cursor.close();
		db.close();
		// return position
		return position;
	}

	// Getting one Position by Name
	public Position getPositionByName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_POSITIONS, new String[] { POSITION_COLUMNS.ID, POSITION_COLUMNS.NAME, POSITION_COLUMNS.DESCRIPTION },
				POSITION_COLUMNS.NAME + "=?", new String[] { name }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Position position = new Position(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		position.setDescription(cursor.getString(2));
		cursor.close();
		db.close();
		// return position
		return position;
	}

	// Getting All Positions
	public List<Position> getAllPositions() {
		List<Position> list = new ArrayList<Position>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_POSITIONS + " ORDER BY " + POSITION_COLUMNS.NAME + " ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Position position = new Position();
				position.setId(Integer.parseInt(cursor.getString(0)));
				position.setName(cursor.getString(1));
				position.setDescription(cursor.getString(2));

				// Adding to list
				list.add(position);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// return list
		return list;
	}

	// Updating single position
	public int updatePosition(Position position) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(POSITION_COLUMNS.NAME, position.getName());
		values.put(POSITION_COLUMNS.DESCRIPTION, position.getDescription());

		// updating row
		int result = db.update(TABLE_POSITIONS, values, POSITION_COLUMNS.ID + " = ?", new String[] { String.valueOf(position.getId()) });
		db.close();
		return result;
	}

	// Deleting single position
	public void deletePosition(Position position) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_POSITIONS, POSITION_COLUMNS.ID + " = ?", new String[] { String.valueOf(position.getId()) });
		db.close();
	}

	// Getting position Count
	public int getPositionCount() {
		String countQuery = "SELECT  * FROM " + TABLE_POSITIONS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}

	// ====INTERVIEW================================================================================================

	// Adding new Interview
	public void addInterview(Interviewer interview) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(INTERVIEW_COLUMNS.NAME, interview.getName());
		values.put(INTERVIEW_COLUMNS.DATE, interview.getDate());
		values.put(INTERVIEW_COLUMNS.TIME, interview.getTime());
		values.put(INTERVIEW_COLUMNS.DESCRIPTION, interview.getDescription());

		// Inserting Row
		db.insert(TABLE_INTERVIEWS, null, values);
		db.close(); // Closing database connection
	}

	// Getting one Interview
	public Interviewer getInterview(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_INTERVIEWS, new String[] { INTERVIEW_COLUMNS.ID, INTERVIEW_COLUMNS.NAME, INTERVIEW_COLUMNS.DATE, INTERVIEW_COLUMNS.TIME,
				INTERVIEW_COLUMNS.DESCRIPTION }, INTERVIEW_COLUMNS.ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Interviewer interview = new Interviewer(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		interview.setDate(cursor.getString(2));
		interview.setTime(cursor.getString(3));
		interview.setDescription(cursor.getString(4));
		cursor.close();
		db.close();
		// return project
		return interview;
	}

	// Getting All Interview
	public List<Interviewer> getAllInterviewer() {
		List<Interviewer> list = new ArrayList<Interviewer>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_INTERVIEWS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Interviewer interview = new Interviewer();
				interview.setId(Integer.parseInt(cursor.getString(0)));
				interview.setName(cursor.getString(1));
				interview.setDate(cursor.getString(2));
				interview.setTime(cursor.getString(3));
				interview.setDescription(cursor.getString(4));
				// Adding to list
				list.add(interview);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// return list
		return list;
	}

	// Updating single interview
	public int updateInterview(Interviewer interview) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(INTERVIEW_COLUMNS.NAME, interview.getName());
		values.put(INTERVIEW_COLUMNS.DATE, interview.getDate());
		values.put(INTERVIEW_COLUMNS.TIME, interview.getTime());
		values.put(INTERVIEW_COLUMNS.DESCRIPTION, interview.getDescription());

		// updating row
		int result = db.update(TABLE_INTERVIEWS, values, INTERVIEW_COLUMNS.ID + " = ?", new String[] { String.valueOf(interview.getId()) });
		db.close();
		return result;
	}

	// Deleting single interview
	public void deleteInterview(Interviewer interview) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_INTERVIEWS, INTERVIEW_COLUMNS.ID + " = ?", new String[] { String.valueOf(interview.getId()) });
		db.close();
	}

	// Getting interview Count
	public int getInterviewCount() {
		String countQuery = "SELECT  * FROM " + TABLE_INTERVIEWS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}

	// ====ROLES====================================================================================================

	// Adding new Role
	public void addRole(Roles role) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ROLES_COLUMNS.NAME, role.getRoleName());
		values.put(ROLES_COLUMNS.LOWER_NAME, role.getLoweredRoleName());
		values.put(ROLES_COLUMNS.DESCRIPTION, role.getDescription());
		// Inserting Row
		db.insert(TABLE_ROLES, null, values);
		db.close(); // Closing database connection
	}

	// Getting one Role
	public Roles getRole(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ROLES, new String[] { ROLES_COLUMNS.ID, ROLES_COLUMNS.NAME, ROLES_COLUMNS.LOWER_NAME, ROLES_COLUMNS.DESCRIPTION },
				ROLES_COLUMNS.ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Roles role = new Roles(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		role.setLoweredRoleName(cursor.getString(2));
		role.setDescription(cursor.getString(3));
		cursor.close();
		db.close();
		// return role
		return role;
	}

	// Getting one Role by Name
	public Roles getRoleByName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ROLES, new String[] { ROLES_COLUMNS.ID, ROLES_COLUMNS.NAME, ROLES_COLUMNS.LOWER_NAME, ROLES_COLUMNS.DESCRIPTION },
				ROLES_COLUMNS.NAME + "=?", new String[] { name }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Roles role = new Roles(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
		role.setLoweredRoleName(cursor.getString(2));
		role.setDescription(cursor.getString(3));
		cursor.close();
		db.close();
		// return role
		return role;
	}

	// Getting All Roles
	public List<Roles> getAllRoles() {
		List<Roles> list = new ArrayList<Roles>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ROLES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Roles role = new Roles();
				role.setRoleId(Integer.parseInt(cursor.getString(0)));
				role.setRoleName(cursor.getString(1));
				role.setLoweredRoleName(cursor.getString(2));
				role.setDescription(cursor.getString(3));
				// Adding to list
				list.add(role);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// return list
		return list;
	}

	// Updating single role
	public int updateRole(Roles role) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ROLES_COLUMNS.NAME, role.getRoleName());
		values.put(ROLES_COLUMNS.LOWER_NAME, role.getLoweredRoleName());
		values.put(ROLES_COLUMNS.DESCRIPTION, role.getDescription());

		// updating row
		int result = db.update(TABLE_ROLES, values, ROLES_COLUMNS.ID + " = ?", new String[] { String.valueOf(role.getRoleId()) });
		db.close();
		return result;
	}

	// Deleting single Role
	public void deleteRole(Roles role) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ROLES, ROLES_COLUMNS.ID + " = ?", new String[] { String.valueOf(role.getRoleId()) });
		db.close();
	}

	// Getting Roles Count
	public int getRolesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ROLES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}
}
