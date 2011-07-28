package pl.froger.helloalertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	static final private int ALERT_DIALOG_PLAIN = 1;
	static final private int ALERT_DIALOG_BUTTONS = 2;
	static final private int ALERT_DIALOG_LIST = 3;
	static final private int CUSTOM_ALERT_DIALOG = 4;

	private Button btnNewAlertDialog;
	private Button btnNewAlertDialogButton;
	private Button btnNewAlertDialogList;
	private Button btnNewCustomAlertDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnNewAlertDialog = (Button) findViewById(R.id.btnNewAlertDialog);
		btnNewAlertDialogButton = (Button) findViewById(R.id.btnNewAlertDialogButton);
		btnNewAlertDialogList = (Button) findViewById(R.id.btnNewAlertDialogList);
		btnNewCustomAlertDialog = (Button) findViewById(R.id.btnNewCustomAlertDialog);
		initButtonsClick();
	}

	private void initButtonsClick() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btnNewAlertDialog:
					showDialog(ALERT_DIALOG_PLAIN);
					break;
				case R.id.btnNewAlertDialogButton:
					showDialog(ALERT_DIALOG_BUTTONS);
					break;
				case R.id.btnNewAlertDialogList:
					showDialog(ALERT_DIALOG_LIST);
					break;
				case R.id.btnNewCustomAlertDialog:
					showDialog(CUSTOM_ALERT_DIALOG);
					break;
	
				default:
					break;
				}
			}
		};
		btnNewAlertDialog.setOnClickListener(listener);
		btnNewAlertDialogButton.setOnClickListener(listener);
		btnNewAlertDialogList.setOnClickListener(listener);
		btnNewCustomAlertDialog.setOnClickListener(listener);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ALERT_DIALOG_PLAIN:
			return createPlainAlertDialog();
		case ALERT_DIALOG_BUTTONS:
			return createAlertDialogWithButtons();
		case ALERT_DIALOG_LIST:
			return createAlertDialogWithList();
		case CUSTOM_ALERT_DIALOG:
			return createCustomAlertDialog();
		default:
			return null;

		}
	}

	private Dialog createPlainAlertDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Dialog title");
		dialogBuilder.setMessage("Dialog content text...");
		return dialogBuilder.create();
	}

	private Dialog createAlertDialogWithButtons() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("Dialog title");
		dialogBuilder.setMessage("Are you sure?");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("Yes", new Dialog.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int whichButton) {
		    	showToast("You picked positive button");
		    }
		});
		dialogBuilder.setNegativeButton("No", new Dialog.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int whichButton) {
		    	showToast("You picked negative button");
		    }
		});
		return dialogBuilder.create();
	}
	
	private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
	}

	private Dialog createAlertDialogWithList() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		final String[] options = {"Option 1", "Option 2", "Another option"};
		dialogBuilder.setTitle("Pick any option");
		dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int position) {
		        showToast("Picked: " + options[position]);
		    }
		});
		return dialogBuilder.create();
	}
	
	private Dialog createCustomAlertDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		View layout = getCustomDialogLayout();
	    dialogBuilder.setView(layout);
	    dialogBuilder.setTitle("Custom AlertDialog");
		return dialogBuilder.create();
	}
	
	private View getCustomDialogLayout() {
	    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	    return inflater.inflate(R.layout.dialog, (ViewGroup)findViewById(R.id.layout_root));
	}
}