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
import android.widget.Toast;

import com.example.android_simple_ordering_system_uvp.events.OnMessageListener;
import com.example.android_simple_ordering_system_uvp.model.Confirmation;
import com.example.android_simple_ordering_system_uvp.model.Generic;
import com.example.android_simple_ordering_system_uvp.model.Order;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMessageListener{

    // -------------------------------------
    // XML references
    // -------------------------------------
    private ImageView product1ImageView;
    private ImageView product2ImageView;
    private ImageView product3ImageView;
    private ImageView product4ImageView;

    // -------------------------------------
    // Global assets
    // -------------------------------------
    private UDPConnection udp;

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

        udp = new UDPConnection();
        udp.setObserver(this);
        udp.start();


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

        Gson gson = new Gson();

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setDate(new GregorianCalendar());
        order.setDescription("It represents an order that has been placed");

        String json;

        switch (v.getId()){

            case R.id.product1ImageView:

                order.setProduct("BURGER");
                json = gson.toJson(order);
                udp.sendMessage(json);

                break;

            case R.id.product2ImageView:

                order.setProduct("COCA_COLA");
                json = gson.toJson(order);
                udp.sendMessage(json);

                break;

            case R.id.product3ImageView:

                order.setProduct("ICE_CREAM");
                json = gson.toJson(order);
                udp.sendMessage(json);

                break;

            case R.id.product4ImageView:

                order.setProduct("SANDWICH");
                json = gson.toJson(order);
                udp.sendMessage(json);

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

                if(confirmation.getConfirmationMessage().equals("DONE")){

                    runOnUiThread(

                            ()->{

                                Intent i = new Intent(this, ConfirmationActivity.class);
                                startActivity(i);


                            }

                    );

                }else if(confirmation.getConfirmationMessage().equals("FULL_STACK")){
                    runOnUiThread(()-> Toast.makeText(this, "The oders stack is full, please wait.", Toast.LENGTH_SHORT).show());
                }



                break;

            case "Order":

                Order order = gson.fromJson(json,  Order.class);
                break;

        }
    }

}