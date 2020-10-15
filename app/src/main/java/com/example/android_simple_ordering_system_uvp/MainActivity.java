/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.android_simple_ordering_system_uvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.android_simple_ordering_system_uvp.events.OnMessageListener;
import com.example.android_simple_ordering_system_uvp.model.Confirmation;
import com.example.android_simple_ordering_system_uvp.model.Generic;
import com.example.android_simple_ordering_system_uvp.model.Order;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener{

    // -------------------------------------
    // XML references
    // -------------------------------------
    private ImageView product1ImageView;
    private ImageView product2ImageView;
    private ImageView product3ImageView;
    private ImageView product4ImageView;

    // -------------------------------------
    // Android methods
    // -------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background animation
        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        product1ImageView = findViewById(R.id.product1ImageView);
        product2ImageView = findViewById(R.id.product2ImageView);
        product3ImageView = findViewById(R.id.product3ImageView);
        product4ImageView = findViewById(R.id.product4ImageView);

        product1ImageView.setOnClickListener(this);
        product2ImageView.setOnClickListener(this);
        product3ImageView.setOnClickListener(this);
        product4ImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.product1ImageView:
                break;

            case R.id.product2ImageView:
                break;

            case R.id.product3ImageView:
                break;

            case R.id.product4ImageView:
                break;

        }

    }

    @Override
    public void onMessage(String json) {

        Gson gson = new Gson();
        Generic generic = gson.fromJson(json, Generic.class);

        switch (generic.type){

            case "Confirmation":

                Confirmation confirmation = gson.fromJson(json, Confirmation.class);

                runOnUiThread(

                        ()->{

                            Intent i = new Intent(this, ConfirmationActivity.class);
                            startActivity(i);
                            finish();

                        }

                );

                break;

            case "Order":

                Order order = gson.fromJson(json,  Order.class);
                break;

        }
    }

}