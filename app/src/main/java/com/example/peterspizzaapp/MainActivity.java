package com.example.peterspizzaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


/**
 * This app displays an order form to order pizza.
 */

public class MainActivity extends AppCompatActivity {
    RadioGroup crustType, toppingType;
    boolean validationBoolean = false;

    //setting the default quantity to 0 in the global scope so it can be called anywhere.
    int quantitypicker = 0;


    String crustValue = "";
    String ToppingValue = "";


    //global variable declaration for true or false
    boolean hasBeef;
    boolean hasChicken;

    //initialization for the EditTexts
    EditText cName, cAddress, cPhone,
            cState, cLga, cEmail;

    TextView informationView,quantityView, crustTypeView, toppingTypeView;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginButton = (Button) findViewById(R.id.button3);
        informationView = (TextView)findViewById(R.id.informationText);
        quantityView = (TextView) findViewById(R.id.quantityTextTop);
        crustTypeView = (TextView) findViewById(R.id.crustTypeText);
        toppingTypeView = (TextView) findViewById(R.id.toppingTypeText);

        //create a listener for the submit order Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod();
            }
        });
        //create an textview object called animateCustomername
        cName = (EditText) findViewById(R.id.customer_name);
        //create an textview object called animateCustomeraddress
        cAddress = (EditText) findViewById(R.id.customer_address);
        //create an textview object called animateCustomernumber
        cPhone = (EditText) findViewById(R.id.customer_number);
        //create an textview object called animateCustomerstate
        cState = (EditText) findViewById(R.id.customer_state);
        //create an textview object called animateCustomerLga
        cLga = (EditText) findViewById(R.id.customer_lga);
        //create an textview object called animateCustomerEmail
        cEmail = (EditText) findViewById(R.id.customer_email_address);
        ViewGroup viewGroup = (ViewGroup)findViewById(R.id.viewContainer);




      YoYo.with(Techniques.RotateInDownRight)
                .duration(1400)
                .repeat(0)
                .playOn(findViewById(R.id.informationText));
       YoYo.with(Techniques.FlipInY)
                .duration(1400)
               .repeat(0)
                .playOn(findViewById(R.id.viewContainer));



        //creates and Animation object called animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        cName.startAnimation(animation);
        //creates and Animation object called animation1
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        cAddress.startAnimation(animation1);
        //creates and Animation object called animation2
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        cPhone.startAnimation(animation2);
        //creates an Animation object called animation3
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        cState.startAnimation(animation3);
        //creates an Animation object called animation4
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        cLga.startAnimation(animation4);
        //creates an Animation object called animation5
        Animation animation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadetext);
        cEmail.startAnimation(animation5);

        //non focus of EditText views
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        crustType = findViewById(R.id.crustgroup);
        toppingType = findViewById(R.id.toppingGroup);


        Typeface addressText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        cAddress.setTypeface(addressText);
        Typeface stateText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        cState.setTypeface(stateText);
        Typeface lgaText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        cLga.setTypeface(lgaText);
        Typeface emailText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        cEmail.setTypeface(emailText);
        Typeface phoneText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        cPhone.setTypeface(phoneText);
        Typeface orderButtonText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        loginButton.setTypeface(orderButtonText);
        Typeface quantityText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        quantityView.setTypeface(quantityText);
        Typeface crustText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        crustTypeView.setTypeface(crustText);
        Typeface toppingText = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        toppingTypeView.setTypeface(toppingText);
        Typeface informationText = Typeface.createFromAsset(getAssets(), "Lato_Light.ttf");
        informationView.setTypeface(informationText);

    }

            // this method calls the decrement button


    public void decrement(View view) {
        if (quantitypicker == 0) {

            Toast.makeText(this, "You cannot order less than 1 pizza", Toast.LENGTH_SHORT).show();
            return;
        }
        quantitypicker = quantitypicker - 1;
        displayNumberOfPizzas(quantitypicker);
}
/**
 * this method calls the increment button
 */
public void increment(View view) {
    if (quantitypicker == 10) {

        Toast.makeText(this, "You cannot order more than 10 pizzas", Toast.LENGTH_SHORT).show();

        return;
    }
    quantitypicker = quantitypicker + 1;
    displayNumberOfPizzas(quantitypicker);
}

    //calculates total price of the order and returns total price
    public int calculatePrice() {
        int count = quantitypicker * 3200;
        int additionalTopping = 0;
        if (hasBeef) {
            additionalTopping += 500;
        }
        if (hasChicken) {
            additionalTopping += 1000;
        }
        int getTotalAmount = count + additionalTopping;

        return getTotalAmount;


    }

    //Login Validation
    private boolean loginMethod() {

        fieldsValidation();
        crustValidation();
        toppingValidation();

//        Toast.makeText(this, "checking 1st validation", Toast.LENGTH_SHO RT).show();
        if (fieldsValidation() == true || crustValidation() == true || toppingValidation() == true) {

            EditText nameDetails = (EditText) findViewById(R.id.customer_name);
            String name = nameDetails.getText().toString();

            //gets address text from input on the EditText Field
            EditText addressDetails = (EditText) findViewById(R.id.customer_address);
            String address = addressDetails.getText().toString();

            //gets phone number text from input on the EditText Field
            EditText numberDetails = (EditText) findViewById(R.id.customer_number);
            String number = numberDetails.getText().toString();

            //gets State text from input on the EditText Field
            EditText stateDetails = (EditText) findViewById(R.id.customer_state);
            String state = stateDetails.getText().toString();

            //gets Lga text from input on the EditText Field
            EditText lgaDetails = (EditText) findViewById(R.id.customer_lga);
            String lga = lgaDetails.getText().toString();

            //gets email address text from input on the EditText Field
            EditText emailDetails = (EditText) findViewById(R.id.customer_email_address);
            String email = emailDetails.getText().toString();

            //displays a thank you message on order click
            Toast.makeText(this, "Thank You for your Order!", Toast.LENGTH_SHORT).show();

            //finds out if the thick crust is clicked
            RadioButton thickCrustBox = (RadioButton) findViewById(R.id.thick_crust);
            RadioButton thinCrustBox = (RadioButton) findViewById(R.id.thin_crust);
            if (thickCrustBox.isChecked()) {
                crustValue = "Thick Crust";
            } else if (thinCrustBox.isChecked()) {
                crustValue = "Thin Crust";
            }

            //condition if selected
            //finds out if the beef topping is selected
            RadioButton Topping1 = (RadioButton) findViewById(R.id.beef);
            //finds out if the chicken topping is selected
            RadioButton Topping2 = (RadioButton) findViewById(R.id.chicken);

            if (Topping1.isChecked()) {
                ToppingValue = "Beef Topping";
            } else if (Topping2.isChecked()) {
                ToppingValue = "Chicken Topping";
            }

            //displayMessage(priceMessage);
            // creates a new activity and passes the priceMessage summary and displays it.

            Intent nextActivityIntent = new Intent(MainActivity.this, MainActivity2.class);
            nextActivityIntent.putExtra("name", name);
            nextActivityIntent.putExtra("address", address);
            nextActivityIntent.putExtra("state", state);
            nextActivityIntent.putExtra("lga", lga);
            nextActivityIntent.putExtra("number", number);
            nextActivityIntent.putExtra("email", email);
            nextActivityIntent.putExtra("quantity", String.valueOf(quantitypicker));
            nextActivityIntent.putExtra("crust", crustValue);
            nextActivityIntent.putExtra("topping", ToppingValue);
            nextActivityIntent.putExtra("amount", calculatePrice());
            startActivity(nextActivityIntent);

        } else {
            Toast.makeText(this, "Please fill and select all fields", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean fieldsValidation() {
        if (TextUtils.isEmpty(cName.getText().toString().trim())
                || TextUtils.isEmpty(cAddress.getText().toString().trim())
                || TextUtils.isEmpty(cState.getText().toString().trim())
                || TextUtils.isEmpty(cLga.getText().toString().trim())
                || TextUtils.isEmpty(cPhone.getText().toString().trim())
                || TextUtils.isEmpty(cEmail.getText().toString().trim()))
//            Toast.makeText(this, "Checking EditText Validation", Toast.LENGTH_SHORT).show();


        {
            cName.setError("Please fill in your name");
            cAddress.setError("Please fill in your address");
            cState.setError("Please fill in your state");
            cLga.setError("Please fill in your lga");
            cPhone.setError("Please fill in your phone number");
            cEmail.setError("Please fill in your email address");

        }
        return false;
    }


    public boolean crustValidation() {

        int crust = crustType.getCheckedRadioButtonId();

        if (crust != -1) {
            switch (crust) {
                case R.id.thick_crust:
                    return true;
                case R.id.thin_crust:
                    return true;
                default:
                    return false;
            }
        } else {
//            Toast.makeText(this, "Crust Type was not checked", Toast.LENGTH_SHORT).show();
            validationBoolean = false;
            return false;
        }
    }

    public boolean toppingValidation() {

        int crust = crustType.getCheckedRadioButtonId();

        if (crust != -1) {
            switch (crust) {

                case R.id.beef:
//                    Toast.makeText(this, "beef Type was checked", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.chicken:
//                    Toast.makeText(this, "chicken Type was checked", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        } else {
//            Toast.makeText(this, "topping Type was not checked", Toast.LENGTH_SHORT).show();
            validationBoolean = false;
            return false;
        }
    }

//    /**
//     * @param price             of the order
//     * @param crustType1;       if user wants thick or thin crust
//     * @param beef              if the user selected beef topping
//     * @param chicken           if the user selected chicken topping
//     * @param nameOfCustomer    ; retrieves the name of the customer from getText
//     * @param addressOfCustomer retrieves the address of the customer
//     * @param phoneNumber       retrieves the phonenumber
//     * @param crustType2        if the user wants thick crust
//     * @return
//     */
//    public String createOrderSummary(String nameOfCustomer, String addressOfCustomer, String phoneNumber, String state, String lga, String email, int price, String crustType1, String crustType2, String beef, String chicken) {
//        //declare a variable priceMessage to store the retrieved name of customer
//        priceMessage = "Name: " + nameOfCustomer;
//        priceMessage += "\n" + "Address: " + addressOfCustomer;
//        priceMessage += "\n" + "State: " + state;
//        priceMessage += "\n" + "LGA: " + lga;
//        priceMessage += "\n" + "Email Address: " + email;
//        priceMessage += "\n" + "Phone Number: " + phoneNumber;
//        priceMessage += "\n" + "No.of Pizza's: " + quantitypicker;
//
//        //if crust type is equal to nothing, then display the method crustType1 or 2
//        //if the checkbox is clicked it should display only the selected box and nothing else
//        priceMessage += (crustType1 == null) ? "" : "\n" + "Crust Type:" + crustType1;
//        priceMessage += (crustType2 == null) ? "" : "\n" + "Crust Type:" + crustType2;
//
//        priceMessage += (beef == null) ? "" : "\n" + "Topping Type: " + beef;
//        priceMessage += (chicken == null) ? "" : "\n" + "Topping Type: " + chicken;
//
//        //priceMessage += "\n Thin Crust? " + crustType2;
//        priceMessage += "\n" + "Total: " + price + " Naira";
//        priceMessage += "\n" + "Thank you for your patronage!";
//        return priceMessage;
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayNumberOfPizzas(int numberOfPizzas) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfPizzas);
    }



    public class EditTextViews{



    }




    //dialog for close of application
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Peter's Pizza App");
        builder.setIcon(R.drawable.pizza);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

/**
 * Pizza order form Main Activity @PeterOluwadayo, October, 2022.
 */
}