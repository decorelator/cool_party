package com.kmware.hrm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.kmware.hrm.actionbar.ActionBar;
import com.kmware.hrm.actionbar.ActionBar.IntentAction;
import com.kmware.hrm.preferences.PrefActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ZActivity extends Activity {
	private final String LOGTAG = ZActivity.class.getSimpleName();
	static class MessageToast {
		private final String LOGTAG = MessageToast.class.getSimpleName();

		private final int ID_ERROR = 1;
		private final int ID_WARNING = 2;
		private final int ID_INFO = 3;

		private void createMessage(Context context, int id, String msg) {

			try {

				Toast toast = new Toast(context);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);

				LayoutInflater inflater = LayoutInflater.from(context);
				View layout = inflater.inflate(R.layout.message_toast, null);

				TextView title = (TextView) layout.findViewById(R.id.te_title);
				ImageView iv = (ImageView) layout.findViewById(R.id.dlg_icon);
				TextView message = (TextView) layout.findViewById(R.id.te_message);

				switch (id) {
				case ID_ERROR:
					title.setText(context.getResources().getString(R.string.dlg_error));
					iv.setImageResource(R.drawable.dlg_error);
					break;

				case ID_WARNING:
					title.setText(context.getString(R.string.dlg_warning));
					iv.setImageResource(R.drawable.dlg_warning);
					break;

				case ID_INFO:
					title.setText(context.getString(R.string.dlg_info));
					iv.setImageResource(R.drawable.dlg_info);
					break;
				}

				message.setText(msg);
				toast.setView(layout);
				toast.show();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		public void showError(Context act, String msg) {
			createMessage(act, ID_ERROR, msg);
		}

		public void showWarning(Context act, String msg) {
			createMessage(act, ID_WARNING, msg);
		}

		public void showInfo(Context act, String msg) {
			createMessage(act, ID_INFO, msg);
		}

		public void showMessage(Context context, String title, String mes) {

			AlertDialog dialog = (new AlertDialog.Builder(context)).setTitle(title).setMessage(mes).setPositiveButton(R.string.ok, null).create();

			dialog.show();
		}

		public static void showDialog(Context context, String title, String mes, OnClickListener l) {

			AlertDialog dialog = (new AlertDialog.Builder(context)).setTitle(title).setMessage(mes).setPositiveButton(R.string.ok, l)
					.setNegativeButton(R.string.cancel, l).create();

			dialog.setCancelable(false);
			dialog.show();
		}

		public static void showEnterTextDialog(Context context, String title, OnClickListener l) {

			LayoutInflater factory = LayoutInflater.from(context);
            final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
           
            AlertDialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(textEntryView).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	// ((EditText) textEntryView.findViewById(R.id.edt_alertdialog)).getText().toString();
                	 
//                	 try{
//                		  // Create file 
//                		 / FileWriter fstream = new FileWriter("/res/raw/role.txt");
//                		  BufferedWriter out = new BufferedWriter(fstream);
//                		  out.write(((EditText) textEntryView.findViewById(R.id.edt_alertdialog)).getText().toString());
//                		  //Close the output stream
//                		  out.close();
//                		  }catch (Exception e){//Catch exception if any
//                		  System.err.println("Error: " + e.getMessage());
//                		  }
//                		  
                

                	
                	
                }
            })
            .setNegativeButton(R.string.cancel, l/*new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            }*/)
            .create();
			dialog.setCancelable(true);
			dialog.show();
		}
	
	}

	private MessageToast message;
	ActionBar bar;
	boolean showMenu = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		bar = new ActionBar(this, null);
		bar.clearHomeAction();
		LinearLayout l = (LinearLayout) findViewById(R.id.root);
		// addActionBarBtn();
		if (l != null)
			l.addView(bar, 0);
		super.onCreate(savedInstanceState);
	}

	public void addprefBarBtn(int icon, android.view.View.OnClickListener action) {
		// bar.addAction(new IntentAction(this, createIntent(this),
		// R.drawable.act_pref));
		bar.addAction(new IntentAction(this, action, icon));
	}

	public void backHomeBar(int icon, Intent action){
		bar.setHomeAction(new IntentAction(this, action, icon));
	}
	
	public void deleteBarBtn(int index){
		bar.removeActionAt(index);
	}
	
	public MessageToast getDialog() {
		if (message == null)
			message = new MessageToast();
		return message;
	}

	// private Intent createIntent(Context context) {
	// Intent i = new Intent(this, PrefActivity.class);
	// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// return i;
	// }

	// public boolean onCreateOptionsMenu(Menu menu) {
	// if (!showMenu) return super.onCreateOptionsMenu(menu);
	// MenuItem mi = menu.add(0, 1, 0, "Preferences");
	// mi.setIntent(new Intent(this, PrefActivity.class));
	// return super.onCreateOptionsMenu(menu);
	// }

	// Initiating Menu XML file (menu.xml)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;

		switch (item.getItemId()) {
		case R.id.menu_search:

			Toast.makeText(ZActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
			return true;

		case R.id.menu_preferences:
			intent = new Intent(this, PrefActivity.class);
			Toast.makeText(ZActivity.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void setTitle(String title, int drawable) {
		bar.setTitle(title);
		bar.setTitleIco(drawable);
	}

	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(LOGTAG, "onRestoreInstanceState");
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(LOGTAG, "onSaveInstanceState");
	}

}
