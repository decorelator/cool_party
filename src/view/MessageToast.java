package view;


import com.kmware.hrm.R;
import com.kmware.hrm.R.drawable;
import com.kmware.hrm.R.id;
import com.kmware.hrm.R.layout;
import com.kmware.hrm.R.string;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MessageToast {
	private static final String LOGTAG = MessageToast.class.getSimpleName();	
	
	private static final int ID_ERROR	= 1;
	private static final int ID_WARNING = 2;
	private static final int ID_INFO 	= 3;
	
	private static void createMessage(Context context, int id, String msg){
		
		try {
			
			Toast toast = new Toast(context);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.setDuration(Toast.LENGTH_SHORT);
			
			LayoutInflater inflater =  LayoutInflater.from(context);
			View layout = inflater.inflate(R.layout.message_toast, null);
			
			TextView title   = (TextView)layout.findViewById(R.id.te_title);
			ImageView iv     = (ImageView)layout.findViewById(R.id.dlg_icon);
			TextView message = (TextView)layout.findViewById(R.id.te_message);		
			
			switch(id) {
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

		
		} catch(Exception ex) {
		}

	}	
	
	public static void showError(Context act, String msg){
		createMessage(act,ID_ERROR, msg);
	}

	public static void showWarning(Context act, String msg){
		createMessage(act,ID_WARNING, msg);		
	}

	public static void showInfo(Context act, String msg){
		createMessage(act,ID_INFO, msg);
	}
	
	public static void showMessage(Context context, String title, String mes){
    	
		AlertDialog dialog = (new AlertDialog.Builder(context))
    	.setTitle(title)
    	.setMessage(mes)
    	.setPositiveButton(R.string.ok, null).create();
    	
    	dialog.show();		
	}
	
	
	public static void showDialog(Context context, String title, String mes, OnClickListener l){
    	
		AlertDialog dialog = (new AlertDialog.Builder(context))
    	.setTitle(title)
    	.setMessage(mes)
    	.setPositiveButton(R.string.ok, l)
    	.setNegativeButton(R.string.cancel, l)
    	.create();
    	
		dialog.setCancelable(false);
    	dialog.show();		
	}
	
}
