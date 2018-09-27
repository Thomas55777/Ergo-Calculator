package thomasWilliams.ErgoCalculator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class TabCalculate extends Activity implements OnClickListener {
	Button btnDistance;
	Button btnTime;
	Button btnAvgSplit;
	EditText editxtDistance;
	EditText editxtTime;
	EditText editxtAvgSplit;

	private LinearLayout MainLayout;
	private String strDistance;
	private String strTime;
	private String strAvgSplit;
	double mSplit = 500;
	private double dblDistance;
	private double dblTime;
	private double dblAvgSplit;

	TabHost tabHost;
	String tempString;
	double tempDouble = 0;
	double toRound = 0;
	private AdView adViewTop;
	private AdView adViewBottom;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabcalculate);

		// Create the adView
		// Lookup your LinearLayout
		// Add the adView to it
		// Initiate a generic request to load it with an ad
		/*
		 * adViewTop = new AdView(this, AdSize.BANNER, "a14f51446c622f1");
		 * LinearLayout layoutTop = (LinearLayout)
		 * findViewById(R.id.AdMobCalculateTop); layoutTop.addView(adViewTop);
		 * adViewTop.loadAd(new AdRequest());
		 */

		// adViewBottom = new AdView(this, AdSize.BANNER, "a14f51446c622f1");
		// LinearLayout layoutBottom = (LinearLayout)
		// findViewById(R.id.AdMobCalculateBottom);
		// layoutBottom.addView(adViewBottom);
		// adViewBottom.loadAd(new AdRequest());
		// ///////////
		// AdAobEnd///
		// ///////////

		MainLayout = (LinearLayout) findViewById(R.id.MainLinearLayout01);
		MainLayout.setOnTouchListener(new OnTouchListener() {
			// @Override
			public boolean onTouch(View v, MotionEvent event) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(MainLayout.getWindowToken(), 0);
				return false;
			}
		});

		// Add My Code Below
		btnDistance = (Button) findViewById(R.id.btnDistance);
		btnTime = (Button) findViewById(R.id.btnTime);
		btnAvgSplit = (Button) findViewById(R.id.btnAvgSplit);
		editxtDistance = (EditText) findViewById(R.id.editxtDistance);
		editxtTime = (EditText) findViewById(R.id.editxtTime);
		editxtAvgSplit = (EditText) findViewById(R.id.editxtAvgSplit);

		btnDistance.setOnClickListener((OnClickListener) this);
		btnTime.setOnClickListener((OnClickListener) this);
		btnAvgSplit.setOnClickListener((OnClickListener) this);

		// Set Cursor to always goto the end when being focused
		EditText ediDistance = (EditText) findViewById(R.id.editxtDistance);
		int posediDistance = ediDistance.getText().length();
		ediDistance.setSelection(posediDistance);

		EditText ediTime = (EditText) findViewById(R.id.editxtTime);
		int posediTime = ediTime.getText().length();
		ediTime.setSelection(posediTime);

		EditText ediAvgSplit = (EditText) findViewById(R.id.editxtAvgSplit);
		int posediAvgSplit = ediAvgSplit.getText().length();
		ediAvgSplit.setSelection(posediAvgSplit);
		// Set Cursor to always goto the end when being focused

		// Use this to Switch Screens--> startActivity(new Intent(this,
		// SrnPredictions.class));
		Intent i = getParent().getIntent();
		String strDistance = i.getStringExtra("strDistance");
		String strTime = i.getStringExtra("strTime");
		String strAvgSplit = i.getStringExtra("strAvgSplit");
		if (strDistance != null && strTime != null && strAvgSplit != null) {
			editxtDistance.setText(strDistance);
			editxtTime.setText(strTime);
			editxtAvgSplit.setText(strAvgSplit);
		}

		StoreIntent(editxtDistance.getText().toString(), editxtTime.getText().toString(), editxtAvgSplit.getText().toString());
		editxtDistance.addTextChangedListener(new TextWatcher() {
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			public void afterTextChanged(Editable s) {
				SetTextViewFormatting(0);
			}
		});
		editxtTime.addTextChangedListener(new TextWatcher() {
			private String editxtTimeTextChanged;
			public String editxtTimeTextChangedCharacter;

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				editxtTimeTextChanged = s.toString();
				if (editxtTimeTextChanged.length() >= 1) {
					editxtTimeTextChangedCharacter = editxtTimeTextChanged.substring(editxtTimeTextChanged.length() - 1, editxtTimeTextChanged.length());
					System.out.println("editxtTimeTextChanged:= " + editxtTimeTextChangedCharacter);
					System.out.println(editxtTime.getText().toString().charAt(editxtTime.getText().toString().length() - 1));
				}
				if (editxtTime.getText().toString().length() == 2 && before == 0) {
					if (isInteger(editxtTime.getText().toString())) {
						editxtTime.getText().insert(editxtTime.getSelectionStart(), ":");
					}
				}
				if (editxtTime.getText().toString().indexOf(":") > 0 && before == 0) {
					if ((editxtTime.getText().toString().length() - editxtTime.getText().toString().indexOf(":")) == 4) {
						if (isNumeric(String.valueOf(editxtTime.getText().toString().charAt(editxtTime.getText().toString().length() - 1)))) {
							String editxtInString = editxtTime.getText().toString();
							String LastKeyPressed = String.valueOf(editxtTime.getText().toString().charAt(editxtTime.getText().toString().length() - 1));
							String editxtNewString = editxtInString.substring(0, editxtInString.length() - 1) + "." + LastKeyPressed;
							editxtTime.setTextKeepState(editxtNewString);
							editxtTime.setSelection(editxtTime.getText().length());
						}
					}
				}
			}

			public void afterTextChanged(Editable s) {
				SetTextViewFormatting(0);
			}
		});
		editxtAvgSplit.addTextChangedListener(new TextWatcher() {
			private String editxtAvgSplitTextChanged;
			public String editxtAvgSplitTextChangedCharacter;

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				editxtAvgSplitTextChanged = s.toString();
				if (editxtAvgSplitTextChanged.length() >= 1) {
					editxtAvgSplitTextChangedCharacter = editxtAvgSplitTextChanged.substring(editxtAvgSplitTextChanged.length() - 1, editxtAvgSplitTextChanged.length());
					System.out.println("editxtAvgSplitTextChanged:= " + editxtAvgSplitTextChangedCharacter);
					System.out.println(editxtAvgSplit.getText().toString().charAt(editxtAvgSplit.getText().toString().length() - 1));
				}
				if (editxtAvgSplit.getText().toString().length() == 2 && before == 0) {
					if (isInteger(editxtAvgSplit.getText().toString())) {
						editxtAvgSplit.getText().insert(editxtAvgSplit.getSelectionStart(), ":");
					}
				}
				if (editxtAvgSplit.getText().toString().indexOf(":") > 0 && before == 0) {
					if ((editxtAvgSplit.getText().toString().length() - editxtAvgSplit.getText().toString().indexOf(":")) == 4) {
						if (isNumeric(String.valueOf(editxtAvgSplit.getText().toString().charAt(editxtAvgSplit.getText().toString().length() - 1)))) {
							String editxtInString = editxtAvgSplit.getText().toString();
							String LastKeyPressed = String.valueOf(editxtAvgSplit.getText().toString().charAt(editxtAvgSplit.getText().toString().length() - 1));
							String editxtNewString = editxtInString.substring(0, editxtInString.length() - 1) + "." + LastKeyPressed;
							editxtAvgSplit.setTextKeepState(editxtNewString);
							editxtAvgSplit.setSelection(editxtAvgSplit.getText().length());
						}
					}
				}
			}

			public void afterTextChanged(Editable s) {
				SetTextViewFormatting(0);
			}
		});

	}

	public static boolean isInteger(String str) {
		try {
			int i = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public void onDestroy() {
		// adViewTop.destroy();
		super.onDestroy();
		// StoreIntent(strDistance, strTime, strAvgSplit);
		StoreIntent(editxtDistance.getText().toString(), editxtTime.getText().toString(), editxtAvgSplit.getText().toString());
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(MainLayout.getWindowToken(), 0);
	}

	public void onClick(View v) {
		strDistance = editxtDistance.getText().toString();
		strTime = editxtTime.getText().toString();
		strAvgSplit = editxtAvgSplit.getText().toString();
		try {
			if (v.getId() == R.id.btnDistance) {
				StringToMilliseconds CalculatedMillisecondsTime = new StringToMilliseconds(strTime);
				double dblTime = CalculatedMillisecondsTime.getMillisecs(strTime);
				StringToMilliseconds CalculatedMillisecondsAvgSplit = new StringToMilliseconds(strAvgSplit);
				double dblAvgSplit = CalculatedMillisecondsAvgSplit.getMillisecs(strAvgSplit);
				double dblDistance = CalculateDistance(dblTime, dblAvgSplit);

				strDistance = Integer.toString((int) dblDistance);
				editxtDistance.setText(strDistance);
				System.out.println(strDistance);
				StoreData(strDistance, strTime, strAvgSplit);
				SetTextViewFormatting(1);
			} else if (v.getId() == R.id.btnTime) {
				StringToMilliseconds CalculatedMillisecondsAvgSplit = new StringToMilliseconds(strAvgSplit);
				double dblAvgSplit = CalculatedMillisecondsAvgSplit.getMillisecs(strAvgSplit);
				double dblDistance = Double.parseDouble(strDistance);
				double dblTime = CalculateTime(dblDistance, dblAvgSplit);

				MillisecondsToString ConvertedString = new MillisecondsToString(dblTime);
				strTime = ConvertedString.getConvertedString();

				editxtTime.setText(strTime);
				System.out.println(strTime);
				StoreData(strDistance, strTime, strAvgSplit);
				SetTextViewFormatting(2);
			} else if (v.getId() == R.id.btnAvgSplit) {
				StringToMilliseconds CalculatedMillisecondsTime = new StringToMilliseconds(strTime);
				double dblTime = CalculatedMillisecondsTime.getMillisecs(strTime);
				// double dblTime = CalculatedMilliseconds.millisecs; //this
				// could be used if millisecs was a public variable
				double dblDistance = Double.parseDouble(strDistance);
				double dblAvgSplit = CalculateAvgSplit(dblTime, dblDistance);

				MillisecondsToString ConvertedString = new MillisecondsToString(dblAvgSplit);
				strAvgSplit = ConvertedString.getConvertedString();

				editxtAvgSplit.setText(strAvgSplit);
				System.out.println(strAvgSplit);
				StoreData(strDistance, strTime, strAvgSplit);
				SetTextViewFormatting(3);
			}
		} catch (Exception e) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("There has been an Error in the Calculation");
			builder.setMessage("Please Enter 'Time' and 'AvgSplit' in mm:ss.0 Format");
			builder.setPositiveButton("OK", null);
			AlertDialog alert = builder.create();
			alert.show();
			// I could also use the below if I wanted more customization in my
			// dialog box
			// AlertDialog dialog = builder.show();
			// Must call show() prior to fetching text view
			// TextView messageView =
			// (TextView)dialog.findViewById(android.R.id.message);
			// messageView.setGravity(Gravity.CENTER);

		}
	}

	public void SetTextViewFormatting(int txtButtonPressed) {
		TextView txtViewDistance = (TextView) findViewById(R.id.txtDistanceCalc);
		TextView txtViewTime = (TextView) findViewById(R.id.txtTimeCalc);
		TextView txtViewAvgSplit = (TextView) findViewById(R.id.txtAvgSplitCalc);

		Button btnViewDistance = (Button) findViewById(R.id.btnDistance);
		Button btnViewTime = (Button) findViewById(R.id.btnTime);
		Button btnViewAvgsplit = (Button) findViewById(R.id.btnAvgSplit);

		String txtDistanceCalc = txtViewDistance.getText().toString();
		String txtTimeCalc = txtViewTime.getText().toString();
		String txtAvgSplitCalc = txtViewAvgSplit.getText().toString();

		String btnDistanceCalc = btnViewDistance.getText().toString();
		String btnTimeCalc = btnViewTime.getText().toString();
		String btnAvgSplitCalc = btnViewAvgsplit.getText().toString();

		txtViewDistance.setText(Html.fromHtml(txtDistanceCalc));
		txtViewTime.setText(Html.fromHtml(txtTimeCalc));
		txtViewAvgSplit.setText(Html.fromHtml(txtAvgSplitCalc));

		btnViewDistance.setText(Html.fromHtml(btnDistanceCalc));
		btnViewTime.setText(Html.fromHtml(btnTimeCalc));
		btnViewAvgsplit.setText(Html.fromHtml(btnAvgSplitCalc));

		boolean booCalculateAccurate = false;
		Intent i = getParent().getIntent();
		i.putExtra("booCalculateAccurate", booCalculateAccurate);

		if (txtButtonPressed == 1) {
			booCalculateAccurate = true;
			i.putExtra("booCalculateAccurate", booCalculateAccurate);

			txtViewDistance.setText(Html.fromHtml("<font color=blue>" + "<b>" + txtDistanceCalc + "</b>" + "</font>"));
			btnViewDistance.setText(Html.fromHtml("<font color=blue>" + "<b>" + btnDistanceCalc + "</b>" + "</font>"));
			// ((TextView)
			// this.findViewById(R.id.txtDistanceCalc)).setText(Html.fromHtml("<b>"
			// + txtDistanceCalc + "</b>"));
		} else if (txtButtonPressed == 2) {
			booCalculateAccurate = true;
			i.putExtra("booCalculateAccurate", booCalculateAccurate);

			txtViewTime.setText(Html.fromHtml("<font color=blue>" + "<b>" + txtTimeCalc + "</b>" + "</font>"));
			btnViewTime.setText(Html.fromHtml("<font color=blue>" + "<b>" + btnTimeCalc + "</b>" + "</font>"));
			// ((TextView)
			// this.findViewById(R.id.txtTimeCalc)).setText(Html.fromHtml("<b>"
			// + txtTimeCalc + "</b>"));
		} else if (txtButtonPressed == 3) {
			booCalculateAccurate = true;
			i.putExtra("booCalculateAccurate", booCalculateAccurate);

			txtViewAvgSplit.setText(Html.fromHtml("<font color=blue>" + "<b>" + txtAvgSplitCalc + "</b>" + "</font>"));
			btnViewAvgsplit.setText(Html.fromHtml("<font color=blue>" + "<b>" + btnAvgSplitCalc + "</b>" + "</font>"));
			// ((TextView)
			// this.findViewById(R.id.txtAvgSplitCalc)).setText(Html.fromHtml("<b>"
			// + txtAvgSplitCalc + "</b>"));
		}

	}

	// ///////////////////////////////////////////////////////
	// Methods to Calculate the Distance, Time, and AvgSplit//
	// ///////////////////////////////////////////////////////
	private double CalculateDistance(double dblTime, double dblAvgSplit) {
		dblDistance = (mSplit / dblAvgSplit) * dblTime;
		System.out.println(dblDistance);
		dblDistance = Math.round(dblDistance);
		if (dblDistance > 999999) {
			dblDistance = 999999;
		}
		return dblDistance;
	}

	private double CalculateTime(double dblDistance, double dblAvgSplit) {
		tempDouble = (dblDistance / mSplit) * dblAvgSplit;
		tempDouble = tempDouble + (1.0 / (10 * 10 * 10 * 60 * 60 * 24)); // add
																			// 00:00.001
																			// seconds
																			// due
																			// to
																			// truncated
																			// value
																			// in
																			// calculating

		tempString = Double.toString(tempDouble * 10 * 60 * 60 * 24);

		toRound = Integer.parseInt(tempString.substring(tempString.indexOf(".") + 1, tempString.indexOf(".") + 2));
		if (toRound >= 5) {
			tempDouble = tempDouble + (1.0 / (10 * 60 * 60 * 24));
		}
		dblTime = tempDouble;

		return dblTime;
	}

	private double CalculateAvgSplit(double dblTime, double dblDistance) {
		tempDouble = (mSplit / dblDistance) * dblTime;
		tempDouble = tempDouble + (1.0 / (10 * 10 * 10 * 60 * 60 * 24));
		System.out.println("CalculateAvgSplit:= " + tempDouble);
		tempString = Double.toString(tempDouble * 10 * 60 * 60 * 24);

		toRound = Integer.parseInt(tempString.substring(tempString.indexOf(".") + 1, tempString.indexOf(".") + 2));
		if (toRound >= 5) {
			tempDouble = tempDouble + (1.0 / (10 * 60 * 60 * 24));
		}
		dblAvgSplit = tempDouble;

		return dblAvgSplit;
	}

	private void StoreData(String strDistance, String strTime, String strAvgSplit) {

		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
		String strDate = df.format(c.getTime());

		StoreDataInSQLite DataStore = new StoreDataInSQLite(this);
		DataStore.open();
		DataStore.createRow(strDistance, strTime, strAvgSplit, strDate);
		DataStore.close();
	}

	private void StoreIntent(String strDistance, String strTime, String strAvgSplit) {
		Intent i = getParent().getIntent();
		i.putExtra("strDistance", strDistance);
		i.putExtra("strTime", strTime);
		i.putExtra("strAvgSplit", strAvgSplit);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mnuInflater = getMenuInflater();
		mnuInflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// case R.id.menuMainHistory:startActivity(new Intent(this,
		// SrnHistory.class));
		case R.id.menuMainHistory:
			startActivity(new Intent(this, SrnHistory.class));
			return true;
		case R.id.menuMainHelp:
			startActivity(new Intent(this, SrnHelp.class));
			// Set Up Later
			return true;
		}
		return false;
	}

	public String getEditTextDistance() {
		EditText ediText = (EditText) findViewById(R.id.editxtDistance);
		return ediText.getText().toString();
	}
}
/*
 * StoreDataInSQLite RetrieveData = new StoreDataInSQLite(this);
 * RetrieveData.open(); long lngCount = RetrieveData.TotalCount();
 * System.out.println("lngCount:= "+lngCount); Cursor c =
 * RetrieveData.fetchData(lngCount); Toast.makeText(this, "ID: " +
 * c.getString(0) + " Distance: " + c.getString(1) + " Time: "+ c.getString(2) +
 * " AvgSplit: " + c.getString(3) + " Date: " + c.getString(4),
 * Toast.LENGTH_SHORT).show(); RetrieveData.close();
 */
