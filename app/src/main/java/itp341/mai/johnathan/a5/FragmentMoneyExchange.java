package itp341.mai.johnathan.a5;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FragmentMoneyExchange extends Fragment {

    EditText mEditTextInput;
    Spinner mSpinnerFrom;
    Spinner mSpinnerTo;
    Button mButtonConvert;
    TextView mTextViewResult;

    final DecimalFormat df = new DecimalFormat("0.00");
    double exchangeAmount = 0.0;
    double resultAmount = 0.0;

    // Multiplier Arrays
    double[] USDMultipliers = { 1.0, 6.51, 0.90 };
    double[] yuanMultipliers = { 0.15, 1.0, 0.14 };
    double[] euroMultipliers = {1.12, 7.27, 1.0 };



    public FragmentMoneyExchange() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_money_exchange, container, false);

        mEditTextInput = (EditText) v.findViewById(R.id.editTextMoneyExchange);
        mSpinnerFrom = (Spinner) v.findViewById(R.id.spinnerMoneyFrom);
        mSpinnerTo = (Spinner) v.findViewById(R.id.spinnerMoneyTo);
        mButtonConvert = (Button) v.findViewById(R.id.buttonConvertMoney);
        mTextViewResult = (TextView) v.findViewById(R.id.textViewResultMoney);

        mButtonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditTextInput.getText().toString())) {
                    mEditTextInput.setError(getResources().getString(R.string.empty_amount_error));
                } else {
                    exchangeAmount = Double.parseDouble(mEditTextInput.getText().toString());
                    switch (mSpinnerFrom.getSelectedItemPosition()) {
                        case 0:
                            mTextViewResult.setVisibility(View.VISIBLE);
                            resultAmount = exchangeAmount * USDMultipliers[mSpinnerTo.getSelectedItemPosition()];
                            mTextViewResult.setText(String.valueOf(df.format(resultAmount)));
                            break;
                        case 1:
                            mTextViewResult.setVisibility(View.VISIBLE);
                            resultAmount = exchangeAmount * yuanMultipliers[mSpinnerTo.getSelectedItemPosition()];
                            mTextViewResult.setText(String.valueOf(df.format(resultAmount)));
                            break;
                        case 2:
                            mTextViewResult.setVisibility(View.VISIBLE);
                            resultAmount = exchangeAmount * euroMultipliers[mSpinnerTo.getSelectedItemPosition()];
                            mTextViewResult.setText(String.valueOf(df.format(resultAmount)));
                            break;
                        default:
                            break;
                    }

                }
            }
        });

        return v;
    }
}
