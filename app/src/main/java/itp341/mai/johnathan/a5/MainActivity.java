package itp341.mai.johnathan.a5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonTip;
    Button buttonMoney;
    Button buttonUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting fragment manager
        FragmentManager fm = getSupportFragmentManager();
        // get the ID/location of where we want to load fragment
        Fragment f = fm.findFragmentById(R.id.fragment_container);
        if (f == null) {
            f = new FragmentTipCalc();
        }

        //if (f == null) { // means activity, and thus fragment, are created for 1st time
            // create transaction
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, f);
            ft.commit();
        //}

        buttonTip = (Button) findViewById(R.id.buttonTip);
        buttonUnit = (Button) findViewById(R.id.buttonUnit);
        buttonMoney = (Button) findViewById(R.id.buttonMoney);

        buttonTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment f = new FragmentTipCalc(); // f is made parent class Fragment so we can instantiate either child! :OOO

                if (f != null) {
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, f);
                    ft.commit();
                }
            }
        });

        buttonUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment f = fm.findFragmentById(R.id.fragment_container); // f is made parent class Fragment so we can instantiate either child! :OOO

                if (f != null) {
                    f = new FragmentUnitConversion();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, f);
                    ft.commit();
                }
            }
        });

        buttonMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment f = fm.findFragmentById(R.id.fragment_container); // f is made parent class Fragment so we can instantiate either child! :OOO

                if (f != null) {
                    f = new FragmentMoneyExchange();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, f);
                    ft.commit();
                }
            }
        });
    }
}
