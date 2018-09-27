package thomasWilliams.ErgoCalculator;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TabPredictions extends Activity {
	private AdView adView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabpredictions);
		//Set up AdMob
		/*adView = new AdView(this, AdSize.BANNER, "a14f51446c622f1");
		LinearLayout layoutTop = (LinearLayout) findViewById(R.id.AdMobPredictions);
		layoutTop.addView(adView);
		adView.loadAd(new AdRequest());*/

		// If I want to open the Screen outside of the TabHoast use -->
		// startActivity(new Intent(this, SrnPredictions.class));
		TextView textDistance = (TextView) findViewById(R.id.txtDistancePredictions);
		TextView textTime = (TextView) findViewById(R.id.txtTimePredictions);
		TextView textAvgSplit = (TextView) findViewById(R.id.txtAvgSplitPredictions);

		Intent intentGetParentCalcs = getParent().getIntent();
		String strDistance = intentGetParentCalcs.getStringExtra("strDistance");
		String strTime = intentGetParentCalcs.getStringExtra("strTime");
		String strAvgSplit = intentGetParentCalcs.getStringExtra("strAvgSplit");
		boolean booCalculateAccurate = intentGetParentCalcs.getBooleanExtra("booCalculateAccurate", true);
		
		textDistance.setText(strDistance);
		textTime.setText(strTime);
		textAvgSplit.setText(strAvgSplit);

		int intPredictionsCount = 50;
		int intPredictionsDistance = 0;
		double mSplit = 500;
		double dblScrCalculateDistance = Double.parseDouble(strDistance);
		StringToMilliseconds CalculatedMillisecondsTime = new StringToMilliseconds(strAvgSplit);
		double dblScrCalculateAvgSplit = CalculatedMillisecondsTime.getMillisecs(strAvgSplit);
		double dblPredictionsAvgSplit;
		double dblPredictionsTime;
		String strPredictionsAvgSplit;
		String strPredictionsTime;
		// Check to see if Data is usable
		if (booCalculateAccurate == false) {
			return;
		}
		// Set up the List
		ListView list = (ListView) findViewById(R.id.PREDICTIONS);
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < intPredictionsCount; i++) {
			HashMap<String, String>
			map = new HashMap<String, String>();

			//Calculate Distance
			intPredictionsDistance = intPredictionsDistance + 500;

			//Calculate AvgSplit
			dblPredictionsAvgSplit = (logx(intPredictionsDistance/dblScrCalculateDistance,2)*5)/(60*60*24)+(dblScrCalculateAvgSplit);
			dblPredictionsAvgSplit = toRound(dblPredictionsAvgSplit);
			MillisecondsToString ConvertedAvgSplit = new MillisecondsToString(dblPredictionsAvgSplit);
			strPredictionsAvgSplit = ConvertedAvgSplit.getConvertedString();
			
			//Calculate Time
			dblPredictionsTime = (intPredictionsDistance/mSplit)*dblPredictionsAvgSplit;
			dblPredictionsTime = toRound(dblPredictionsTime);
			MillisecondsToString ConvertedTime = new MillisecondsToString(dblPredictionsTime);
			strPredictionsTime = ConvertedTime.getConvertedString();

			//Populate List
			map.put("Distance", Integer.toString(intPredictionsDistance) + " m");
			map.put("Time", strPredictionsTime);
			map.put("AvgSplit", strPredictionsAvgSplit);
			mylist.add(map);
		}

		SimpleAdapter loadPredictions = new SimpleAdapter(this, mylist,
				R.layout.mylistpredictions, new String[] { "Distance", "Time", "AvgSplit" }, new int[] {
						R.id.PredictionsDistance, R.id.PredictionsTime, R.id.PredictionsAvgSplit });
		list.setAdapter(loadPredictions);
	}

	private double toRound(double tempDouble) {
		tempDouble = tempDouble + (1.0/(10*10*10*60*60*24));
		String tempString = Double.toString(tempDouble * 10 * 60 * 60 * 24);

		int toRound = Integer.parseInt(tempString.substring(tempString.indexOf(".") + 1, tempString.indexOf(".") + 2));
		if (toRound >= 5) {
			tempDouble = tempDouble + (1.0 / (10 * 60 * 60 * 24));
		}
		return tempDouble;
	}

	private double logx(double dblNumber, int intBase) {
		return Math.log(dblNumber) / Math.log(intBase);
	}
}
