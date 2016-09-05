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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStopService = (Button) findViewById(R.id.btnStopService);

        btnStopService.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStopService:
                TextView status_message = (TextView) findViewById(R.id.status_message);
                EditText email_input = (EditText) findViewById(R.id.email_address_input);
                EditText tax_id_input = (EditText) findViewById(R.id.tax_id_input);
                EditText amount_input = (EditText) findViewById(R.id.deposit_amount_input);
                SeekBar term_in_days_input = (SeekBar) findViewById(R.id.term_in_days_input);
                Double amount;
                Integer term;

                status_message.setText("");

                // Validate email
                if(TextUtils.isEmpty(email_input.getText().toString()) ||
                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email_input.getText().toString()).matches()) {
                    email_input.setError(getText(R.string.failure_message_email));
                    break;
                }

                // Validate tax id
                if(TextUtils.isEmpty(tax_id_input.getText().toString())) {
                    tax_id_input.setError(getText(R.string.failure_message_tax_id));
                }

                // Validate amount
                if(TextUtils.isEmpty(amount_input.getText().toString())) {
                    amount_input.setError(getText(R.string.failure_message_deposit_amount));
                    break;
                }

                // Validate term
                if(term_in_days_input.getProgress() == 0) {
                    status_message.setTextColor(ContextCompat.getColor(this, R.color.colorRojo));
                    status_message.setText(getText(R.string.failure_message_term_in_days));
                }

                amount = Double.parseDouble(amount_input.getText().toString());
                term = term_in_days_input.getProgress();

                break;
        }
    }
}
