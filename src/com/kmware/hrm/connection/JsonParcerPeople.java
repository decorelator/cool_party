package com.kmware.hrm.connection;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.model.People;

public class JsonParcerPeople {

	// url to make request
	private static String url = "http://api.androidhive.info/people/";

	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";
	private static final String TAG_PHONE_OFFICE = "office";

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	// Creating JSON Parser instance
	JSONParser jParser = new JSONParser();

	// getting JSON string from URL
	JSONObject json;

	public JsonParcerPeople() {
		try {
			// Getting Array of Contacts
			json = jParser.getJSONFromUrl(url);
			contacts = json.getJSONArray(TAG_CONTACTS);
			// looping through All Contacts
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);

				// Storing each json item in variable
				String id = c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String email = c.getString(TAG_EMAIL);

				// Phone number is agin JSON Object
				JSONObject phone = c.getJSONObject(TAG_PHONE);
				String mobile = phone.getString(TAG_PHONE_MOBILE);

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();

				// adding each child node to HashMap key => value
				map.put(TAG_ID, id);
				map.put(TAG_NAME, name);
				map.put(TAG_EMAIL, email);
				map.put(TAG_PHONE_MOBILE, mobile);

				// adding HashList to ArrayList
				contactList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BaseModel> getPeople() {
		try {
			contacts = json.getJSONArray(TAG_CONTACTS);
			// looping through All Contacts
			ArrayList<BaseModel> js_people = null;
			for (int i = 0; i < contacts.length(); i++) {
				JSONObject c = contacts.getJSONObject(i);
				People p = new People(c.getInt(TAG_ID), c.getString(TAG_NAME), c.getString(TAG_NAME));
				js_people.add(p);
			}
			return js_people;
		} catch (JSONException e) {
			e.printStackTrace();
			return new ArrayList<BaseModel>();
		}
		
	}
}