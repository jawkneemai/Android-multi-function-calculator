package itp341.mai.johnathan.a5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FragmentTipCalc extends Fragment {

    // Widget Variables
    EditText mEditTextBillAmount;
    TextView mTextViewTipPercent;
    SeekBar mSeekBarTipPercent;
    TextView mTextViewTipTotal;
    TextView mTextViewBillTotal;
    Spinner mSpinnerSplitAmount;
    TextView mTextViewPersonAmount;
    TextView mTextViewPersonAmountDescriptor;

    // Instance Variables
    double currentBillAmount = 0.0;
    double currentTipAmount = 15.0;
    double currentPersonAmount = 0.0;
    double currentBillIncludeTip = 0.0;
    int splitBy = 1;
    final DecimalFormat df = new DecimalFormat("0.00");

    public FragmentTipCalc() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tip_calculator, container, false);

            mEditTextBillAmount = (EditText) v.findViewById(R.id.editTextBillAmount);
            mTextViewTipPercent = (TextView) v.findViewById(R.id.textViewTipPercent);
            mSeekBarTipPercent = (SeekBar) v.findViewById(R.id.seekBarTipPercent);
            mTextViewTipTotal = (TextView) v.findViewById(R.id.textViewTipTotal);
            mTextViewBillTotal = (TextView) v.findViewById(R.id.textViewBillTotal);
            mSpinnerSplitAmount = (Spinner) v.findViewById(R.id.spinnerSplitAmount);
            mTextViewPersonAmountDescriptor = (TextView) v.findViewById(R.id.textviewPersonAmountDescriptor);
            mTextViewPersonAmount = (TextView) v.findViewById(R.id.textViewPersonAmount);

            String spinnerChoices[] = getResources().getStringArray(R.array.per_person_options);
            mTextViewPersonAmount.setVisibility(View.GONE);
            mTextViewPersonAmountDescriptor.setVisibility(View.GONE);

            // Update bill amount after every time the EditText is touched
            mEditTextBillAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if ((mEditTextBillAmount.getText()) == null || mEditTextBillAmount.getText().toString().isEmpty()) {
                        currentBillAmount = 0.0;
                        updateInfo();
                    } else {
                        currentBillAmount = Double.parseDouble(mEditTextBillAmount.getText().toString());
                        updateInfo();
                    }
                    return false;
                }
            });

            // Seekbar Listener
            mSeekBarTipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentTipAmount = (double)progress;
                    mTextViewTipPercent.setText(String.valueOf(progress));
                    updateInfo();
                }

                @Override public void onStartTrackingTouch(SeekBar seekBar) {}
                @Override public void onStopTrackingTouch(SeekBar seekBar) {}
            });

            mSpinnerSplitAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            mTextViewPersonAmount.setVisibility(View.GONE);
                            mTextViewPersonAmountDescriptor.setVisibility(View.GONE);
                            break;
                        case 1:
                            splitBy = 2;
                            updateInfo();
                            mTextViewPersonAmount.setVisibility(View.VISIBLE);
                            mTextViewPersonAmountDescriptor.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            splitBy = 3;
                            updateInfo();
                            mTextViewPersonAmount.setVisibility(View.VISIBLE);
                            mTextViewPersonAmountDescriptor.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            splitBy = 4;
                            updateInfo();
                            mTextViewPersonAmount.setVisibility(View.VISIBLE);
                            mTextViewPersonAmountDescriptor.setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        return v;
    }

    // Function to update all areas that are update-able
    private void updateInfo() {
        currentBillIncludeTip = currentBillAmount + ( currentTipAmount / 100 ) * currentBillAmount;
        mTextViewTipTotal.setText(String.valueOf(df.format(( currentTipAmount / 100 ) * currentBillAmount )));
        mTextViewBillTotal.setText(String.valueOf(df.format(currentBillIncludeTip)));
        mTextViewPersonAmount.setText(String.valueOf(df.format( currentBillIncludeTip / splitBy )));
    }
}
