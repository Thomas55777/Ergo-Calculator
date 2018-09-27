package thomasWilliams.ErgoCalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class TabWeightAdjusted extends Activity {
	private Intent i;
	private LinearLayout MainLayout;
	private double dblWeightFactor;
	private String strWeightType;
	private String strWeight;
	private double dblWeight;
	private String strDistance;
	private double dblDistance;
	private String strTime;
	private String strAvgSplit;
	private double dblTime;
	private double dblAvgSplit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabweightadjusted);
		i = getParent().getIntent();

		MainLayout = (LinearLayout) findViewById(R.id.MainLinearLayout01);
		MainLayout.setOnTouchListener(new OnTouchListener() {
			// @Override
			public boolean onTouch(View v, MotionEvent event) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(MainLayout.getWindowToken(), 0);
				return false;
			}
		});

		// Set up and check for previous inputs
		Spinner spinWeight = (Spinner) findViewById(R.id.spinnerWeight);
		EditText ediTextWeight = (EditText) findViewById(R.id.editxtWeight);

		int intSpinnerPosition = i.getIntExtra("intSpinnerPosition", 0);
		strWeight = i.getStringExtra("strWeight");

		if (intSpinnerPosition != 0) {
			spinWeight.setSelection(intSpinnerPosition);
		}
		if (strWeight != null) {
			ediTextWeight.setText(strWeight);
		}
		strWeightType = String.valueOf(spinWeight.getSelectedItem());
		System.out.println("strWeightType:= " + strWeightType);

		// Set up Variables from TabCalculate
		TextView txtDistanceCalc = (TextView) findViewById(R.id.txtDistanceCalc);
		TextView txtTimeCalc = (TextView) findViewById(R.id.txtTimeCalc);
		TextView txtAvgSplitCalc = (TextView) findViewById(R.id.txtAvgSplitCalc);

		txtDistanceCalc.setText(i.getStringExtra("strDistance"));
		txtTimeCalc.setText(i.getStringExtra("strTime"));
		txtAvgSplitCalc.setText(i.getStringExtra("strAvgSplit"));

		// Check to see if it is all Calculated
		boolean booCalculateAccurate = i.getBooleanExtra("booCalculateAccurate", true);
		if (booCalculateAccurate == false) {
			return;
		}

		// Perform the initial calculations
		WeightAdjustedCalculations();

		// Set Up Text Change Listener
		ediTextWeight.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable arg0) {
				WeightAdjustedCalculations();
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});

		// Set Up Spinner Change Listener
		spinWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				WeightAdjustedCalculations();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	private void WeightAdjustedCalculations() {
		EditText ediTextWeight = (EditText) findViewById(R.id.editxtWeight);
		TextView txtDistanceCalc = (TextView) findViewById(R.id.txtDistanceCalc);
		TextView txtTimeCalc = (TextView) findViewById(R.id.txtTimeCalc);
		TextView txtAvgSplitCalc = (TextView) findViewById(R.id.txtAvgSplitCalc);
		TextView txtDistanceWeight = (TextView) findViewById(R.id.txtDistanceWeight);
		TextView txtTimeWeight = (TextView) findViewById(R.id.txtTimeWeight);
		TextView txtAvgSplitWeight = (TextView) findViewById(R.id.txtAvgSplitWeight);

		strWeight = ediTextWeight.getText().toString();
		strDistance = txtDistanceCalc.getText().toString();
		strTime = txtTimeCalc.getText().toString();
		strAvgSplit = txtAvgSplitCalc.getText().toString();
		if ("".equals(strWeight)) {
			strWeight = "0";
			// return;
		}
		dblWeight = Double.parseDouble(strWeight);
		dblDistance = Double.parseDouble(strDistance);
		StringToMilliseconds TimeCalc = new StringToMilliseconds(strTime);
		dblTime = TimeCalc.getMillisecs(strTime);
		StringToMilliseconds AvgSplitCalc = new StringToMilliseconds(strAvgSplit);
		dblAvgSplit = AvgSplitCalc.getMillisecs(strAvgSplit);
		Spinner spinWeight = (Spinner) findViewById(R.id.spinnerWeight);
		strWeightType = String.valueOf(spinWeight.getSelectedItem());

		if ("Lbs".equals(strWeightType)) {
			dblWeightFactor = Math.pow((dblWeight / 270), (2.0 / 9));
		} else if ("Kg".equals(strWeightType)) {
			dblWeightFactor = Math.pow(((dblWeight * 2.20462262) / 270), (2.0 / 9));
		}
		// Corrected distance = actual distance / Wf
		// Corrected time = Wf x actual time (seconds)

		// Calculate Weight Adjusted Distance
		long lngDistanceWeightAdjusted = Math.round(dblDistance / dblWeightFactor);
		if (lngDistanceWeightAdjusted > 999999)
		{
			txtDistanceWeight.setText("999999");
		} else {
			txtDistanceWeight.setText(Long.toString(lngDistanceWeightAdjusted));
		}

		// Calculate Weight Adjusted Time
		double dblTimeWeightAdjusted = dblWeightFactor * dblTime;
		dblTimeWeightAdjusted = toRound(dblTimeWeightAdjusted);
		MillisecondsToString TimeWeightAdjusted = new MillisecondsToString(dblTimeWeightAdjusted);
		String strTimeWeightAdjusted = TimeWeightAdjusted.getConvertedString();
		txtTimeWeight.setText(strTimeWeightAdjusted);

		// Calculate Weight Adjusted AvgSplit //Use TabCalculate Distance and
		// Weight AdjustedTime
		double dblAvgSplitWeightAdjusted = (dblTimeWeightAdjusted * 500) / dblDistance;
		dblAvgSplitWeightAdjusted = toRound(dblAvgSplitWeightAdjusted);
		MillisecondsToString AvgSplitWeightAdjusted = new MillisecondsToString(dblAvgSplitWeightAdjusted);
		String strAvgSplitWeightAdjusted = AvgSplitWeightAdjusted.getConvertedString();
		txtAvgSplitWeight.setText(strAvgSplitWeightAdjusted);
	}

	private double toRound(double tempDouble) {
		tempDouble = tempDouble + (1.0 / (10 * 10 * 10 * 60 * 60 * 24));
		String tempString = Double.toString(tempDouble * 10 * 60 * 60 * 24);

		int toRound = Integer.parseInt(tempString.substring(tempString.indexOf(".") + 1, tempString.indexOf(".") + 2));
		if (toRound >= 5) {
			tempDouble = tempDouble + (1.0 / (10 * 60 * 60 * 24));
		}
		return tempDouble;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		EditText ediTextWeight = (EditText) findViewById(R.id.editxtWeight);
		strWeight = ediTextWeight.getText().toString();

		Spinner spinWeight = (Spinner) findViewById(R.id.spinnerWeight);
		String strWeightType = String.valueOf(spinWeight.getSelectedItem());

		@SuppressWarnings("unchecked")
		ArrayAdapter<String> myAdap = (ArrayAdapter<String>) spinWeight.getAdapter();
		int intSpinnerPosition = myAdap.getPosition(strWeightType);

		i.putExtra("intSpinnerPosition", intSpinnerPosition);
		i.putExtra("strWeight", strWeight);

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(MainLayout.getWindowToken(), 0);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mnuInflater = getMenuInflater();
		mnuInflater.inflate(R.menu.weightadjusted_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// case R.id.menuMainHistory:startActivity(new Intent(this,
		// SrnHistory.class));
		case R.id.menuWeightAdjExplain:
			startActivity(new Intent(this, mnuWeightAdjMenu.class));
			return true;
		}
		return false;
	}
}