package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int COFFEE_PRICE = 5;
    final int PEPPERONI = 2;
    final int HAM = 2;
    final int BACON = 2;
    final int PINAPPLE = 1;
    final int SAUSAGE = 2;
    final int SPINICH = 1;
    int quantity = 1;
    String orderSummaryMessage;
    Button Sum_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sum_Btn = (Button) findViewById(R.id.btn_sum);

        Sum_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, SummaryActivity.class);

        startActivity(intent);
    }


    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
        EditText userInputEmailView = (EditText) findViewById(R.id.email_input);
        String userInputEmail = userInputEmailView.getText().toString();

        CheckBox ham = (CheckBox) findViewById(R.id.ham_checked);
        boolean hasHam = ham.isChecked();

        CheckBox bacon = (CheckBox) findViewById(R.id.bacon_checked);
        boolean hasBacon = bacon.isChecked();

        CheckBox pepperoni = (CheckBox) findViewById(R.id.pepperoni_checked);
        boolean hasPepperoni = pepperoni.isChecked();

        CheckBox sausage = (CheckBox) findViewById(R.id.sausage_checked);
        boolean hasSausage = sausage.isChecked();

        CheckBox pineapple = (CheckBox) findViewById(R.id.pineapple_checked);
        boolean hasPineapple = pineapple.isChecked();

        CheckBox spinach = (CheckBox) findViewById(R.id.spinach_checked);
        boolean hasSpinach = spinach.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasHam, hasPepperoni, hasBacon, hasSausage, hasPineapple, hasSpinach);

        // create and store the order summary
        orderSummaryMessage = createOrderSummary(userInputName, userInputEmail, hasHam, hasBacon, hasPepperoni, hasSausage, hasPineapple, hasSpinach, totalPrice);

        Toast.makeText(MainActivity.this, orderSummaryMessage, Toast.LENGTH_LONG).show();

        sendEmail(userInputEmail, orderSummaryMessage);
        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intent
    }

    public void sendEmail(String name, String output) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{name});
        i.putExtra(Intent.EXTRA_SUBJECT, "Pizza Receipt");
        i.putExtra(Intent.EXTRA_TEXT, output);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, String userInputEmail, boolean hasHam, boolean hasBacon, boolean hasPepperoni, boolean hasSausage, boolean hasPineapple, boolean hasSpinach, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_email, userInputEmail) + "\n" +
                getString(R.string.order_summary_ham, boolToString(hasHam)) + "\n" +
                getString(R.string.order_summary_bacon, boolToString(hasBacon)) + "\n" +
                getString(R.string.order_summary_pepperoni, boolToString(hasPepperoni)) + "\n" +
                getString(R.string.order_summary_sausage, boolToString(hasSausage)) + "\n" +
                getString(R.string.order_summary_pineapple, boolToString(hasPineapple)) + "\n" +
                getString(R.string.order_summary_spinach, boolToString(hasSpinach)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }
    /**
     * Method to calculate the total price
     *
     * @return total Price
     */

    private float calculatePrice(boolean hasHam, boolean hasBacon, boolean hasPepperoni, boolean hasSausage, boolean hasPineapple, boolean hasSpinach) {
        int basePrice = COFFEE_PRICE;
        if (hasHam) {
            basePrice += HAM;
        }
        if (hasPepperoni) {
            basePrice += PEPPERONI;
        }
        if (hasBacon) {
            basePrice += BACON;
        }
        if (hasSausage) {
            basePrice += SAUSAGE;
        }
        if (hasPineapple) {
            basePrice += PINAPPLE;
        }
        if (hasSpinach) {
            basePrice += SPINICH;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}
