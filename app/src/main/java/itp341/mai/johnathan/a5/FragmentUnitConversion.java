package itp341.mai.johnathan.a5;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class FragmentUnitConversion extends Fragment {

    EditText mEditTextInput;
    RadioGroup mRadioGroupFrom;
    RadioGroup mRadioGroupTo;
    Button mButtonConvert;
    TextView mTextViewResult;

    // Instance Variables
    double amountConvert = 0.0;
    double resultConvert = 0.0;

    // Arrays for multipliers

    double[] centimetersConvert = { 1.0, 0.01, 0.0328, 0.00000621, 0.00001 };
    double[] metersConvert = { 100.0, 1.0, 3.2808, 0.000621, 0.01 };
    double[] feetConvert = { 30.48, 0.3048, 1.0, 0.000189, 0.000304 };
    double[] milesConvert = { 160934.0, 1609.34, 5280.0, 1.0, 1.60934 };
    double[] kilometersConvert = { 10000.0, 1000.0, 3280.84, 0.62137, 1.0 };

    private static final String TAG = FragmentUnitConversion.class.getSimpleName();


    public FragmentUnitConversion() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_unit_conversion, container, false);

        mEditTextInput = (EditText) v.findViewById(R.id.editTextUnitConv);
        mRadioGroupFrom = (RadioGroup) v.findViewById(R.id.radioGroupUnitConvFrom);
        mRadioGroupTo = (RadioGroup) v.findViewById(R.id.radioGroupUnitConvTo);
        mButtonConvert = (Button) v.findViewById(R.id.buttonConvertUnit);
        mTextViewResult = (TextView) v.findViewById(R.id.textViewResultUnit);

        mButtonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(mEditTextInput.getText().toString())) {
                    mEditTextInput.setError(getResources().getString(R.string.empty_amount_error));
                } else {
                    amountConvert = Double.parseDouble(mEditTextInput.getText().toString());
                    switch (mRadioGroupFrom.getCheckedRadioButtonId()) {
                        case -1:
                            mTextViewResult.setText(getResources().getString(R.string.error_radio_button));
                            break;
                        case 1:
                            if (mRadioGroupTo.getCheckedRadioButtonId() == -1) {
                                mTextViewResult.setText(getResources().getString(R.string.error_radio_button));
                            } else {
                                resultConvert = amountConvert * centimetersConvert[mRadioGroupTo.getCheckedRadioButtonId() - 6];
                                mTextViewResult.setText(String.valueOf(resultConvert));
                            }
                            break;
                        case 2:
                            if (mRadioGroupTo.getCheckedRadioButtonId() == -1) {
                                mTextViewResult.setText(getResources().getString(R.string.error_radio_button));
                            } else {
                                resultConvert = amountConvert * metersConvert[mRadioGroupTo.getCheckedRadioButtonId() - 6];
                                mTextViewResult.setText(String.valueOf(resultConvert));
                            }
                            break;
                        case 3:
                            if (mRadioGroupTo.getCheckedRadioButtonId() == -1) {
                                mTextViewResult.setText(getResources().getString(R.string.error_radio_button));
                            } else {
                                resultConvert = amountConvert * feetConvert[mRadioGroupTo.getCheckedRadioButtonId() - 6];
                                mTextViewResult.setText(String.valueOf(resultConvert));
                            }
                            break;
                        case 4:
                            if (mRadioGroupTo.getCheckedRadioButtonId() == -1) {
                                mTextViewResult.setText(getResources().getString(R.string.error_radio_button));
                            } else {
                                resultConvert = amountConvert * milesConvert[mRadioGroupTo.getCheckedRadioButtonId() - 6];
                                mTextViewResult.setText(String.valueOf(resultConvert));
                            }
                            break;
                        case 5:
                            if (mRadioGroupTo.getCheckedRadioButtonId() == -1) {
                                mTextViewResult.setText(getResources().getString(R.string.error_radio_button));
                            } else {
                                resultConvert = amountConvert * kilometersConvert[mRadioGroupTo.getCheckedRadioButtonId() - 6];
                                mTextViewResult.setText(String.valueOf(resultConvert));
                            }
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
