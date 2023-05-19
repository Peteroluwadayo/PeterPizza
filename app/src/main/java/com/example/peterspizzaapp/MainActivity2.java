package com.example.peterspizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 123;
    Uri imageLocation;

    Button btn;
    ImageView imageView;

    TextView nameTextView, addressTextView, stateTextView, lgaTextView, numberTextView,
            emailTextView, quantityTextView, crustTextView, toppingTextView, amountTextView;

    String nameDisplay, addressDisplay, stateDisplay, lgaDisplay, numberDisplay,
            emailDisplay, quantityDisplay, crustDisplay, toppingDisplay, amountDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //to display the title bar arrow button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Order Summary");

        nameTextView = findViewById(R.id.name);
        addressTextView = findViewById(R.id.address);
        stateTextView = findViewById(R.id.state);
        lgaTextView = findViewById(R.id.lga);
        numberTextView = findViewById(R.id.number);
        emailTextView = findViewById(R.id.email);
        quantityTextView = findViewById(R.id.quantity);
        crustTextView = findViewById(R.id.crust);
        toppingTextView = findViewById(R.id.topping);
        amountTextView = findViewById(R.id.amount);

        //get intent
        Intent nextActivityIntent = getIntent();
        nameDisplay = nextActivityIntent.getExtras().getString("name");
        addressDisplay = nextActivityIntent.getExtras().getString("address");
        stateDisplay = nextActivityIntent.getExtras().getString("state");
        lgaDisplay = nextActivityIntent.getExtras().getString("lga");
        numberDisplay = nextActivityIntent.getExtras().getString("number");
        emailDisplay = nextActivityIntent.getExtras().getString("email");
        quantityDisplay = nextActivityIntent.getExtras().getString("quantity");
        crustDisplay = nextActivityIntent.getExtras().getString("crust");
        toppingDisplay = nextActivityIntent.getExtras().getString("topping");
        amountDisplay = String.valueOf(nextActivityIntent.getExtras().getInt("amount"));

        //remove focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        //camera object
        btn = (Button) findViewById(R.id.cameraButton);
        imageView = (ImageView) findViewById(R.id.capturedImage);

        initializeActivity(nameDisplay, addressDisplay, stateDisplay, lgaDisplay, numberDisplay,
                emailDisplay, quantityDisplay, crustDisplay, toppingDisplay, amountDisplay);

        Button imageButton = (Button)findViewById(R.id.shareAllapps);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlToShare = "https://www.arkandarcs.com";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                // intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                startActivity(intent);
            }
        });
    }

    public void initializeActivity(String name, String address, String state, String lga,
                                   String number, String email, String quantity, String crust,
                                   String topping, String amount) {
        nameTextView.setText(name);
        addressTextView.setText(address);
        stateTextView.setText(state);
        lgaTextView.setText(lga);
        numberTextView.setText(number);
        emailTextView.setText(email);
        quantityTextView.setText(quantity);
        crustTextView.setText(crust);
        toppingTextView.setText(topping);
        amountTextView.setText(amount);

    }



    public void mapLocation(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3?z=11"));

        //check if there is an implicit application to handle the request
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void visitWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.arkandarcs.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    public void capturePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    public void  onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            File path = saveImage(photo);
            File nameIsPic = new File(Environment.getExternalStorageDirectory() + "/" + path);
            imageLocation = Uri.fromFile(nameIsPic);
        }
    }

    public File saveImage(Bitmap bitmap) {
//        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
//        Toast.makeText(this, root, Toast.LENGTH_SHORT).show();
//        File myImages = new File(root + "/NerosImage");
//        myImages.mkdir();


// Create the file directory
        File myDir = new File(Environment.getExternalStorageDirectory() + "/Peters Pizza");

        myDir.mkdirs();
        Toast.makeText(this, myDir.getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
// For multiple files you want to create a timestamp so
// the name is unique...using d time d pic was taken

        DateFormat format = new SimpleDateFormat("yyyy_MM_dd_H_mm_ss", Locale.getDefault());

        Date curDate = new Date();

        String displayDate = format.format(curDate);

        String fname = displayDate + "_Pizza_image.jpg";

        // now this is dynamic // create the file in the directory
        File file = new File(myDir, fname);

        try {
            boolean fileCreated = file.createNewFile();

            if (fileCreated) {
                // write the bitmap to that file
                FileOutputStream outputStream = new FileOutputStream(file);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                outputStream.close();

                return file;
            }
            return null;
        } catch (Exception ex) {
            return null;
        }


    }


    public void dialPhoneNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: 08069809921"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void sendEmail(View view) {

        String passAllDetails = "Name: " + nameDisplay + "\n" + "Address: " + addressDisplay +
                "\n" + "State: " + stateDisplay + "\n" + "LGA: " + lgaDisplay +
                "\n" + "Phone Number: " + numberDisplay + "\n" + "Email Address: " + emailDisplay +
                "\n" + "Quantity: " + quantityDisplay + "\n" + "Amount: " + amountDisplay +
                "\n" + "\n" + "Thank you for your patronage!";
        // TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        // ImageView capturedPix = (ImageView) findViewById(R.id.capturedImage);
        // priceTextView.setText(newPageDisplay);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:peteroluwadayo2017@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Customer Pizza Order Request for " + nameDisplay);
        intent.putExtra(Intent.EXTRA_TEXT, passAllDetails);
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_STREAM, imageLocation);
//        intent.setType("image/jpg");

//check for an application to receive the intent else, dont bother
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //method for going back to the main activity pressing the back arrow
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return true;

    }
}