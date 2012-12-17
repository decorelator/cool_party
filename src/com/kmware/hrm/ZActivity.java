package com.kmware.hrm;

import com.kmware.hrm.actionbar.ActionBar;
import com.kmware.hrm.actionbar.ActionBar.IntentAction;
import com.kmware.hrm.preferences.PrefActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ZActivity extends Activity {
	class MessageToast {
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

			AlertDialog dialog = (new AlertDialog.Builder(context)).setTitle(title).setMessage(mes).setPositiveButton(R.string.ok, null)
					.create();

			dialog.show();
		}

		public void showDialog(Context context, String title, String mes, OnClickListener l) {

			AlertDialog dialog = (new AlertDialog.Builder(context)).setTitle(title).setMessage(mes).setPositiveButton(R.string.ok, l)
					.setNegativeButton(R.string.cancel, l).create();

			dialog.setCancelable(false);
			dialog.show();
		}

	}

	private MessageToast message;
	ActionBar bar;
	boolean showMenu = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		bar = new ActionBar(this, null);
		LinearLayout l = (LinearLayout) findViewById(R.id.root);
		//addActionBarBtn();
		if (l != null)
			l.addView(bar, 0);
		super.onCreate(savedInstanceState);
	}

	public void addprefBarBtn(int icon,android.view.View.OnClickListener action) {
//		bar.addAction(new IntentAction(this, createIntent(this), R.drawable.act_pref));
		bar.addAction(new IntentAction(this, action, icon));
	}

	public MessageToast getDialog() {
		if (message == null)
			message = new MessageToast();
		return message;
	}
	
//	private Intent createIntent(Context context) {
//		Intent i = new Intent(this, PrefActivity.class);
//		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		return i;
//	}

	public boolean onCreateOptionsMenu(Menu menu) {
		if (!showMenu) return super.onCreateOptionsMenu(menu);
		MenuItem mi = menu.add(0, 1, 0, "Preferences");
		mi.setIntent(new Intent(this, PrefActivity.class));
		return super.onCreateOptionsMenu(menu);
	}
	
	public void setTitle(String title, int drawable)
	{
		bar.setTitle(title);
		bar.setTitleIco(drawable);
	}

}
