package com.vikramezhil.droidspeechexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;
import com.vikramezhil.droidspeech.OnDSPermissionsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Droid Speech Example Activity
 *
 * @author Vikram Ezhil
 */

public class Activity_DroidSpeech extends Activity implements OnClickListener, OnDSListener, OnDSPermissionsListener
{
    public final String TAG = "Activity_DroidSpeech";

    private DroidSpeech droidSpeech;
    private TextView finalSpeechResult;
    private ImageView start, stop;

    // MARK: Activity Methods

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Setting the layout;[.
        setContentView(R.layout.activity_droid_speech);

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
        if(text.get(0).equals("संकटकालीन मदत") ||text.get(0).equals("मदत")|| text.get(0).equals("अपघात कालीन मदत") || text.get(0).equals("अपघात कालीं मदत") || text.get(0).equals("अपघातकालीन मदत") || text.get(0).equals("इमरजन्सी") || text.get(0).equals("इमरजन्सी"))
        {
            Toast toast = Toast.makeText(getApplicationContext(), "OKOKO", Toast.LENGTH_SHORT); toast.show();
            startActivity(new Intent(Activity_DroidSpeech.this, Emergancy.class));

        }
        else if(text.get(0).equals("पोलिसांना कॉल करा") || text.get(0).equals("पोलिसांना कॉल करा") || text.get(0).equals("पोलिसांना कॉल लावा") || text.get(0).equals("पोलिसांना फोन करा"))
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
        else if(text.get(0).equals("अवयवदान")  || text.get(0).equals("अवयव दान"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, OrganDonation.class));

        }
        else if(text.get(0).equals("अवयवदान म्हणजे काय") || text.get(0).equals("अवयव दान म्हणजे काय"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, WhatisOrganDonation.class));

        }
        else if(text.get(0).equals("अवयव दान कोण करू शकेल") || text.get(0).equals("अवयवदान कोण करू शकेल"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, WhocanDo.class));

        }
        else if(text.get(0).equals("कोणते अवयव दान करू शकतो") || text.get(0).equals("कोणते अवयवदान करू शकतो"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, WhichOrgans.class));

        }
        else if(text.get(0).equals("अवयवदान कसे करावे") || text.get(0).equals("अवयव दान कसे करावे"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, HowtoDonate.class));

        }
        else if(text.get(0).equals("सरकारी योजना") || text.get(0).equals("सरकारी योजना दाखवा") || text.get(0).equals("सरकारी योजना शोधा") || text.get(0).equals("शासकीय योजना") || text.get(0).equals("शासकीय योजना शोधा") || text.get(0).equals("सरकारी योजनांची माहिती") || text.get(0).equals("सरकारी योजनांची माहिती दाखवा") || text.get(0).equals("सरकारी योजना कोणत्या आहेत"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this,SchemeSelection.class));

        }
        else if(text.get(0).equals("जन औषधालय") || text.get(0).equals("औषधालय") || text.get(0).equals("औषधालय शोधा") || text.get(0).equals("जन औषधालय शोधा") || text.get(0).equals("जवळचे मेडिकल दाखवा") || text.get(0).equals("जवळचे मेडीकल्स दाखवा") || text.get(0).equals("मेडीकल्स") || text.get(0).equals("मेडीकल्स दाखवा"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, MedicalStores.class));

        }
        else if(text.get(0).equals("अवयवदान अर्ज भरा") || text.get(0).equals("अवयव दान अर्ज भरा"))
        {
            startActivity(new Intent(Activity_DroidSpeech.this, Donation.class));

        }
        else if(text.get(0).equals("मित्राला कॉल करा") || text.get(0).equals("मित्राला फोन लावा") ||  text.get(0).equals("मित्राला फोन करा") || text.get(0).equals("मित्राला कॉल लावा")  )
        {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:7447209449"));
            startActivity(intent);
        }
        else  if (text.get(0).equals("नेमणूक बुक करा") || text.get(0).equals("नेमणूक करा")|| text.get(0).equals("अपॉईंटमेंट बुक करा") || text.get(0).equals("अपॉईंटमेंट बुक कर") || text.get(0).equals("बुक अपॉईंटमेंट") || text.get(0).equals("बुक अपॉईंटमेंट्स") || text.get(0).equals("मला नियुक्ती बुक करावी लागेल") || text.get(0).equals("माझी डॉक्टर सोबत अपॉइंटमेंट बुक करा") || text.get(0).equals("मला आज माझ्या डॉक्टरांबरोबर अपॉइंटमेंट बुक करायची आहे") || text.get(0).equals("मला आज माझ्या डॉक्टरांसोबत अपॉइंटमेंट बुक करायची आहे") || text.get(0).equals("डॉक्टरांसोबत अपॉइंटमेंट बुक करा") || text.get(0).equals("डॉक्टरांसोबत अपॉइंटमेंट बुक कर")) {
            startActivity(new Intent(Activity_DroidSpeech.this, BookApointment.class));

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

    public void helpp(View view)
    {
        startActivity(new Intent(Activity_DroidSpeech.this, Emergancy.class));

    }
    public void opendonation(View view)
    {
        startActivity(new Intent(Activity_DroidSpeech.this, OrganDonation.class));


    }

    public void openmedical(View view)
    {
        startActivity(new Intent(Activity_DroidSpeech.this, MedicalStores.class));
    }

    public void openschemes(View view)
    {
        startActivity(new Intent(Activity_DroidSpeech.this,SchemeSelection.class));
    }

    public void openinfo(View view)
    {
        startActivity(new Intent(Activity_DroidSpeech.this,BookApointment.class));

    }
   /* public void openuploaddata(View view)
    {
        startActivity(new Intent(SecondActivity.this,UploadData.class));
    }
    public void openseedata(View view)
    {
        startActivity(new Intent(SecondActivity.this,SeeData.class));
    }

    public void openseeoffers(View view)
    {
        startActivity(new Intent(SecondActivity.this,OffersFirst.class));

    }
    public void openinfo(View view)
    {
        startActivity(new Intent(SecondActivity.this,InformationActivity.class));

    }

    public void openremainder(View view)
    {
        startActivity(new Intent(SecondActivity.this,OpenRemainder.class));

    }
    */
}
