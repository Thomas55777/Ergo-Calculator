package thomasWilliams.ErgoCalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TabWorldRecords extends Activity {
	private Intent i;
	private int intSpinnerPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabworldrecords);

		Spinner spnWorldRecords = (Spinner) findViewById(R.id.spinnerWorldRecords);
		i = getParent().getIntent();

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.worldrecords_arrays,
				// android.R.layout.simple_spinner_item);
				R.layout.myspinnertextview);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(R.layout.myspinnertextview);

		spnWorldRecords.setAdapter(adapter);
		intSpinnerPosition = i.getIntExtra("intSpinnerPositionWorldRecords", 2);
		spnWorldRecords.setSelection(intSpinnerPosition);
		// strWorldRecordMeasure =
		// String.valueOf(spnWorldRecords.getSelectedItem());

		spnWorldRecords.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Spinner spnWorldRecords = (Spinner) findViewById(R.id.spinnerWorldRecords);
				String strWorldRecordMeasure = String.valueOf(spnWorldRecords.getSelectedItem());
				@SuppressWarnings("unchecked")
				ArrayAdapter<String> myAdap = (ArrayAdapter<String>) spnWorldRecords.getAdapter();
				intSpinnerPosition = myAdap.getPosition(strWorldRecordMeasure);

				WorldRecordsLoad(intSpinnerPosition);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Spinner spnWorldRecords = (Spinner) findViewById(R.id.spinnerWorldRecords);
		String strWorldRecordMeasure = String.valueOf(spnWorldRecords.getSelectedItem());

		@SuppressWarnings("unchecked")
		ArrayAdapter<String> myAdap = (ArrayAdapter<String>) spnWorldRecords.getAdapter();
		int intSpinnerPositionWorldRecords = myAdap.getPosition(strWorldRecordMeasure);

		i.putExtra("intSpinnerPositionWorldRecords", intSpinnerPositionWorldRecords);
	}

	private void WorldRecordsLoad(int intSpinnerPosition) {
		TextView txtMenHvyWeightTime = (TextView) findViewById(R.id.txtMenHvyWeightTime);
		TextView txtMenLgtWeightTime = (TextView) findViewById(R.id.txtMenLgtWeightTime);
		TextView txtMenHvyWeightAvgSplit = (TextView) findViewById(R.id.txtMenHvyWeightAvgSplit);
		TextView txtMenLgtWeightAvgSplit = (TextView) findViewById(R.id.txtMenLgtWeightAvgSplit);
		TextView txtMenHvyWeightName = (TextView) findViewById(R.id.txtMenHvyWeightName);
		TextView txtMenLgtWeightName = (TextView) findViewById(R.id.txtMenLgtWeightName);
		TextView txtMenHvyWeightCountry = (TextView) findViewById(R.id.txtMenHvyWeightCountry);
		TextView txtMenLgtWeightCountry = (TextView) findViewById(R.id.txtMenLgtWeightCountry);
		TextView txtMenHvyWeightYear = (TextView) findViewById(R.id.txtMenHvyWeightYear);
		TextView txtMenLgtWeightYear = (TextView) findViewById(R.id.txtMenLgtWeightYear);
		TextView txtWomenHvyWeightTime = (TextView) findViewById(R.id.txtWomenHvyWeightTime);
		TextView txtWomenLgtWeightTime = (TextView) findViewById(R.id.txtWomenLgtWeightTime);
		TextView txtWomenHvyWeightAvgSplit = (TextView) findViewById(R.id.txtWomenHvyWeightAvgSplit);
		TextView txtWomenLgtWeightAvgSplit = (TextView) findViewById(R.id.txtWomenLgtWeightAvgSplit);
		TextView txtWomenHvyWeightName = (TextView) findViewById(R.id.txtWomenHvyWeightName);
		TextView txtWomenLgtWeightName = (TextView) findViewById(R.id.txtWomenLgtWeightName);
		TextView txtWomenHvyWeightCountry = (TextView) findViewById(R.id.txtWomenHvyWeightCountry);
		TextView txtWomenLgtWeightCountry = (TextView) findViewById(R.id.txtWomenLgtWeightCountry);
		TextView txtWomenHvyWeightYear = (TextView) findViewById(R.id.txtWomenHvyWeightYear);
		TextView txtWomenLgtWeightYear = (TextView) findViewById(R.id.txtWomenLgtWeightYear);

		txtMenHvyWeightTime.setText(aryMHTimeText[intSpinnerPosition]);
		txtMenLgtWeightTime.setText(aryMLTimeText[intSpinnerPosition]);
		txtMenHvyWeightAvgSplit.setText(aryMHAvgSplitText[intSpinnerPosition]);
		txtMenLgtWeightAvgSplit.setText(aryMLAvgSplitText[intSpinnerPosition]);
		txtMenHvyWeightName.setText(aryMHNameText[intSpinnerPosition]);
		txtMenLgtWeightName.setText(aryMLNameText[intSpinnerPosition]);
		txtMenHvyWeightCountry.setText(aryMHCtyText[intSpinnerPosition]);
		txtMenLgtWeightCountry.setText(aryMLCtyText[intSpinnerPosition]);
		txtMenHvyWeightYear.setText(aryMHYearText[intSpinnerPosition]);
		txtMenLgtWeightYear.setText(aryMLYearText[intSpinnerPosition]);
		txtWomenHvyWeightTime.setText(aryWHTimeText[intSpinnerPosition]);
		txtWomenLgtWeightTime.setText(aryWLTimeText[intSpinnerPosition]);
		txtWomenHvyWeightAvgSplit.setText(aryWHAvgSplitText[intSpinnerPosition]);
		txtWomenLgtWeightAvgSplit.setText(aryWLAvgSplitText[intSpinnerPosition]);
		txtWomenHvyWeightName.setText(aryWHNameText[intSpinnerPosition]);
		txtWomenLgtWeightName.setText(aryWLNameText[intSpinnerPosition]);
		txtWomenHvyWeightCountry.setText(aryWHCtyText[intSpinnerPosition]);
		txtWomenLgtWeightCountry.setText(aryWLCtyText[intSpinnerPosition]);
		txtWomenHvyWeightYear.setText(aryWHYearText[intSpinnerPosition]);
		txtWomenLgtWeightYear.setText(aryWLYearText[intSpinnerPosition]);

	}

	static final String[] aryMHTimeText = new String[] {
			"01:10.5", "02:39.6", "05:36.6", "14:58.3", "18:54.1", "31:36.5", "9,203m", "18,221m", "128:38:19.0" };
	static final String[] aryMHAvgSplitText = new String[] {
			"01:10.5", "01:19.8", "01:24.1", "01:29.8", "01:34.5", "01:34.8", "01:37.8", "01:38.8", "03:51.5"
	};
	static final String[] aryMHNameText = new String[] {
			"Leo Young", "Jose Luis Sanz Ortega", "Rob Waddell", "Rob Waddell", "Nikola Stojic", "Jean-Christophe Rolland", "Philip Turnham", "Graham Benton", "Nigel Gower"
	};
	static final String[] aryMHCtyText = new String[] {
			"AUS ", "ESP ", "NZL ", "NZL ", "YUG ", "FRA ", "GBR ", "GBR ", ""
	};
	static final String[] aryMHYearText = new String[] {
			"1991", "2005", "2008", "2008", "2000", "2000", "2011", "2008", "2003"
	};
	static final String[] aryMLTimeText = new String[] {
			"01:20.1", "02:56.7", "05:58.5", "16:18.4", "19:40.4", "32:15.7", "8,922m", "17,319m", "144:17:55.0"
	};
	static final String[] aryMLAvgSplitText = new String[] {
			"01:20.1", "01:28.4", "01:29.6", "01:37.8", "01:38.4", "01:36.8", "01:40.9", "01:43.9", "04:19.7"
	};
	static final String[] aryMLNameText = new String[] {
			"Gregg Stephens", "Daniel Teoli", "Henrik Stephansen", "Dan Staite", "Mads Rasmussen", "Augusto Farfan", "Dan Staite", "Thomas Ebert", "Neil Rhodes"
	};
	static final String[] aryMLCtyText = new String[] {
			"USA ", "USA ", "DEN ", "GBR ", "DEN ", "PER ", "GBR ", "DEN ", ""
	};
	static final String[] aryMLYearText = new String[] {
			"2005", "2011", "2009", "2006", "2006", "2005", "2006", "2003", "2001"
	};
	static final String[] aryWHTimeText = new String[] {
			"01:26.5", "03:13.0", "06:28.4", "17:58.0", "21:08.9", "35:55.0", "8,412m", "15,613m", ""
	};
	static final String[] aryWHAvgSplitText = new String[] {
			"01:26.5", "01:36.5", "01:37.1", "01:47.8", "01:45.7", "01:47.8", "01:47.0", "01:55.3", ""
	};
	static final String[] aryWHNameText = new String[] {
			"Lexie McAndrew", "Carolyn Ganes", "Sophie Balmary", "Magda Visser", "Sarah Hubbard", "Line Espedal", "Jess Ziebell", "Hilary Gehman", ""
	};
	static final String[] aryWHCtyText = new String[] {
			"GBR ", "CAN ", "FRA ", "AUS ", "AUS ", "USA ", "USA ", "USA ", ""
	};
	static final String[] aryWHYearText = new String[] {
			"2004", "2010", "2006", "2004", "2006", "2003", "2004", "2003", ""
	};
	static final String[] aryWLTimeText = new String[] {
			"01:34.0", "03:26.5", "06:54.7", "18:02.9", "22:15.0", "37:36.7", "8,275m", "16,193m", ""
	};
	static final String[] aryWLAvgSplitText = new String[] {
			"01:34.0", "01:43.2", "01:43.7", "01:48.3", "01:51.3", "01:52.8", "01:48.8", "01:51.2", ""
	};
	static final String[] aryWLNameText = new String[] {
			"Mary Stevenson", "Minna Nieminen", "Ursula Grobler", "Ardith Jordan", "Ank Hobbes", "Jenny Crownell", "Ank Hobbes", "Luanne Suplick", ""
	};
	static final String[] aryWLCtyText = new String[] {
			"USA ", "FIN ", "USA ", "USA ", "NLD ", "USA ", "NLD ", "USA ", ""
	};
	static final String[] aryWLYearText = new String[] {
			"2005", "2001", "2010", "2002", "2004", "2005", "2004", "2005", ""
	};

}