package com.vikramezhil.droidspeechexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;
import com.vikramezhil.droidspeech.OnDSPermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class OrganDonation extends Activity implements View.OnClickListener, OnDSListener, OnDSPermissionsListener {
    public final String TAG = "Activity_DroidSpeech";

    private DroidSpeech droidSpeech;
    private TextView finalSpeechResult;
    private ImageView start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_donation);

        // Initializing the droid speech and setting the listener
        droidSpeech = new DroidSpeech(this, getFragmentManager());
        droidSpeech.setOnDroidSpeechListener(this);
        droidSpeech
                .setShowRecognitionProgressView(true);
        droidSpeech.setOneStepResultVerify(true);
        droidSpeech.setRecognitionProgressMsgColor(Color.WHITE);
        droidSpeech.setOneStepVerifyConfirmTextColor(Color.WHITE);
        droidSpeech.setOneStepVerifyRetryTextColor(Color.WHITE);


        start = findViewById(R.id.start);
        start.setOnClickListener(this);

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(this);

        droidSpeech.startDroidSpeechRecognition();

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if(stop.getVisibility() == View.VISIBLE)
        {
            stop.performClick();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if(stop.getVisibility() == View.VISIBLE)
        {
            stop.performClick();
        }
    }

    // MARK: OnClickListener Method

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.start:

                // Starting droid speech
                droidSpeech.startDroidSpeechRecognition();

                // Setting the view visibilities when droid speech is running
                start.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);

                break;

            case R.id.stop:

                // Closing droid speech
                droidSpeech.closeDroidSpeechOperations();

                stop.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);

                break;
        }
    }

    // MARK: DroidSpeechListener Methods

    @Override
    public void onDroidSpeechSupportedLanguages(String currentSpeechLanguage, List<String> supportedSpeechLanguages)
    {
        Log.i(TAG, "Current speech language = " + currentSpeechLanguage);
        Log.i(TAG, "Supported speech languages = " + supportedSpeechLanguages.toString());

        if(supportedSpeechLanguages.contains("mr-IN"))
        {
            // Setting the droid speech preferred language as tamil if found
            droidSpeech.setPreferredLanguage("mr-IN");

            // Setting the confirm and retry text in tamil

        }
    }

    @Override
    public void onDroidSpeechRmsChanged(float rmsChangedValue)
    {
        // Log.i(TAG, "Rms change value = " + rmsChangedValue);
    }

    @Override
    public void onDroidSpeechLiveResult(String liveSpeechResult)
    {

        final ArrayList<String> text = new ArrayList<>(0);
        text.add(liveSpeechResult);

        Log.i(TAG, "Live speech result = " + liveSpeechResult);
        if(text.get(0).equals("पोलिसांना कॉल करा") || text.get(0).equals("पोलिसांना कॉल करा") || text.get(0).equals("पोलिसांना कॉल लावा") || text.get(0).equals("पोलिसांना फोन करा"))
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:100"));
            startActivity(intent);
        }
        else if(text.get(0).equals("रुग्णवाहिकेला कॉल करा") || text.get(0).equals("रुग्णवाहिके ला कॉल करा"))
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:108"));
            startActivity(intent);
        }
        else if(text.get(0).equals("मित्राला कॉल करा") || text.get(0).equals("मित्राला फोन लावा") ||  text.get(0).equals("मित्राला फोन करा") || text.get(0).equals("मित्राला कॉल लावा")  )
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:7447209449"));
            startActivity(intent);
        }
         else if(text.get(0).equals("अवयवदान")  || text.get(0).equals("अवयव दान"))
    {
        startActivity(new Intent(OrganDonation.this, OrganDonation.class));

    }
    else if(text.get(0).equals("अवयवदान म्हणजे काय") || text.get(0).equals("अवयव दान म्हणजे काय"))
    {
        startActivity(new Intent(OrganDonation.this, WhatisOrganDonation.class));

    }
    else if(text.get(0).equals("अवयव दान कोण करू शकेल") || text.get(0).equals("अवयवदान कोण करू शकेल"))
    {
        startActivity(new Intent(OrganDonation.this, WhocanDo.class));

    }
    else if(text.get(0).equals("कोणते अवयव दान करू शकतो") || text.get(0).equals("कोणते अवयवदान करू शकतो"))
    {
        startActivity(new Intent(OrganDonation.this, WhichOrgans.class));

    }
    else if(text.get(0).equals("अवयवदान कसे करावे") || text.get(0).equals("अवयव दान कसे करावे"))
    {
        startActivity(new Intent(OrganDonation.this, HowtoDonate.class));

    }

    }

    @Override
    public void onDroidSpeechFinalResult(String finalSpeechResult)
    {
        // Setting the final speech result
        /*this.finalSpeechResult.setText(finalSpeechResult);
        Log.i("Activity_DroidSpeech","wqeghwerej::"+finalSpeechResult);

        if(droidSpeech.getContinuousSpeechRecognition())
        {
            int[] colorPallets1 = new int[] {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
            int[] colorPallets2 = new int[] {Color.YELLOW, Color.RED, Color.CYAN, Color.BLUE, Color.GREEN};

            // Setting random color pallets to the recognition progress view
            droidSpeech.setRecognitionProgressViewColors(new Random().nextInt(2) == 0 ? colorPallets1 : colorPallets2);
        }
        else
        {
            stop.setVisibility(View.GONE);
            start.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void onDroidSpeechClosedByUser()
    {
        stop.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDroidSpeechError(String errorMsg)
    {
        // Speech error
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();

        stop.post(new Runnable()
        {
            @Override
            public void run()
            {
                // Stop listening
                stop.performClick();
            }
        });
    }

    // MARK: DroidSpeechPermissionsListener Method

    @Override
    public void onDroidSpeechAudioPermissionStatus(boolean audioPermissionGiven, String errorMsgIfAny)
    {
        if(audioPermissionGiven)
        {
            start.post(new Runnable()
            {
                @Override
                public void run()
                {
                    // Start listening
                    start.performClick();
                }
            });
        }
        else
        {
            if(errorMsgIfAny != null)
            {
                // Permissions error
                Toast.makeText(this, errorMsgIfAny, Toast.LENGTH_LONG).show();
            }

            stop.post(new Runnable()
            {
                @Override
                public void run()
                {
                    // Stop listening
                    stop.performClick();
                }
            });
        }
    }



    public void h1(View view)
    {
        startActivity(new Intent(OrganDonation.this, WhatisOrganDonation.class));

    }

    public void h2(View view)
    {
        startActivity(new Intent(OrganDonation.this, WhocanDo.class));

    }
    public void h3(View view)
    {
        startActivity(new Intent(OrganDonation.this, WhichOrgans.class));

    }
    public void h4(View view)
    {
        startActivity(new Intent(OrganDonation.this, HowtoDonate.class));

    }

    public void h5(View view)
    {
        startActivity(new Intent(OrganDonation.this, Donation.class));

    }
}
