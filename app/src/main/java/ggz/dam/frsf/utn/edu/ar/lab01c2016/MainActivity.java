package ggz.dam.frsf.utn.edu.ar.lab01c2016;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStopService = (Button) findViewById(R.id.btnStopService);
        SeekBar skbTermInDays = (SeekBar) findViewById(R.id.term_in_days_input);

        btnStopService.setOnClickListener(this);
        skbTermInDays.setOnSeekBarChangeListener(this);

        TextView term_in_days_value = (TextView) findViewById(R.id.term_in_days_value);
        term_in_days_value.setText(String.valueOf(skbTermInDays.getProgress()));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStopService:
                TextView interest_amount = (TextView) findViewById(R.id.interest_amount);
                TextView status_message = (TextView) findViewById(R.id.status_message);
                EditText email_input = (EditText) findViewById(R.id.email_address_input);
                EditText tax_id_input = (EditText) findViewById(R.id.tax_id_input);
                EditText amount_input = (EditText) findViewById(R.id.deposit_amount_input);
                SeekBar term_in_days_input = (SeekBar) findViewById(R.id.term_in_days_input);
                Double amount;
                Integer term;
                Double interest;
                boolean hasErrors = false;

                status_message.setText("");
                interest_amount.setText("");

                // Validate email
                if(TextUtils.isEmpty(email_input.getText().toString()) ||
                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email_input.getText().toString()).matches()) {
                    email_input.setError(getText(R.string.failure_message_email));
                    hasErrors = true;
                }

                // Validate tax id
                if(TextUtils.isEmpty(tax_id_input.getText().toString())) {
                    tax_id_input.setError(getText(R.string.failure_message_tax_id));
                    hasErrors = true;
                }

                // Validate amount
                if(TextUtils.isEmpty(amount_input.getText().toString())) {
                    amount_input.setError(getText(R.string.failure_message_deposit_amount));
                    hasErrors = true;
                }

                // Validate term
                if(term_in_days_input.getProgress() == 0) {
                    status_message.setTextColor(ContextCompat.getColor(this, R.color.colorRojo));
                    status_message.setText(getText(R.string.failure_message_term_in_days));
                    hasErrors = true;
                }

                if (!hasErrors) {
                    amount = Double.parseDouble(amount_input.getText().toString());
                    term = term_in_days_input.getProgress();
                    interest = Calculator.calculate_interest(this, amount, term);
                    status_message.setTextColor(ContextCompat.getColor(this, R.color.colorVerde));
                    status_message.setText(String.format(getResources().getString(R.string.success_message), interest));
                    interest_amount.setText(String.format(getResources().getString(R.string.small_text_money), interest));
                }

                break;
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.term_in_days_input:
                TextView term_in_days_value = (TextView) findViewById(R.id.term_in_days_value);
                term_in_days_value.setText(String.valueOf(progress));
                break;
        }

    }
}
