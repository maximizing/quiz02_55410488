package app.buusk15.quiz02_55410488;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Play<getUserid> extends Activity implements OnClickListener {
	private Button btn_hi, btn_low, btn_reset;
	private TextView txtshow1, txtshow2, txtshow3;
	private int rn, result;
	private String getUserid, put;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		btn_hi = (Button) findViewById(R.id.btn_hi);
		btn_low = (Button) findViewById(R.id.btn_low);
		btn_reset = (Button) findViewById(R.id.btn_reset);
		txtshow1 = (TextView) findViewById(R.id.txtshow1);
		txtshow2 = (TextView) findViewById(R.id.txtshow2);
		txtshow3 = (TextView) findViewById(R.id.txtshow3);
		btn_hi.setOnClickListener(this);
		btn_low.setOnClickListener(this);
		btn_reset.setOnClickListener(this);
		getUserid = getIntent().getStringExtra("Userid");
		getUserid.toString();

		rn = Firstrandom();

		txtshow1.setText("Number " + rn);
		txtshow2.setText(" ");
		txtshow3.setText(" ");
	}

	private int random(int in1, int in2) {
		int rnds = 0;
		Random r = new Random();
		if (in2 > 0) {
			rnds = r.nextInt(in1 - in2 + 1) + in2;
		}
		return rnds;
	}

	private int Firstrandom() {
		Random r = new Random();
		int rnd = r.nextInt(9 - 1 + 1) + 1;
		return rnd;
	}

	@Override
	public void onClick(View v) {
		control488DB db = new control488DB(this);
		switch (v.getId()) {
		case R.id.btn_hi:
			result = random(9, 5);
			txtshow1.setText("Number " + rn);
			txtshow3.setText("Your score " + result);
			if (rn == result) {
				txtshow2.setText("You're Win");
			} else if (rn != result) {
				txtshow2.setText("You're Loose");
			}
			put = String.valueOf(result);
			db.InsertScore(getUserid, put); // Insert Score

			break;
		case R.id.btn_low:
			result = random(5, 1);
			txtshow1.setText("Number " + rn);
			txtshow3.setText("Your score " + result);
			if (rn == result) {
				txtshow2.setText("You're Win");
			} else if (rn != result) {
				txtshow2.setText("You're Loose");
			}
			put = String.valueOf(result);
			db.InsertScore(getUserid, put); // Insert Score

			break;
		case R.id.btn_reset:
			rn = Firstrandom();
			txtshow1.setText("Number " + rn);
			txtshow2.setText(" ");
			txtshow3.setText(" ");
		default:
			break;
		}

	}

}
