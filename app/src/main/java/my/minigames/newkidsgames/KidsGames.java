package my.minigames.newkidsgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import my.minigames.newkidsgames.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class KidsGames extends AppCompatActivity {

    Intent intentgame;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAd();
        setContentView(R.layout.activity_kids_games);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle("Kids games");

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#01579b"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        ExpandableHeightGridView simpleGrid;
        int logosKids[] = {R.drawable.crazyrunner, R.drawable.fruitslasher, R.drawable.plumber, R.drawable.brickout, R.drawable.trafficcommand, R.drawable.traffic,R.drawable.balloonscreator, R.drawable.boxsize,R.drawable.bridgedown,R.drawable.coloredwaterpin,
                R.drawable.dotshot,R.drawable.greenprickle,R.drawable.instrumentsforkids,R.drawable.lineroad,R.drawable.poisonattack,R.drawable.waydawn,R.drawable.yellowdot};
        String namesKids[] = {"Crazy Runner", "Friut Slasher", "Plumber", "Brick Out", "Traffic Command", "Traffic","Balloons Creator","Box Size","Bridge Down","Colored Water Pin","Dot Shot",
                "Green Pickle","Instruments For Kids","Line Road","Poison Attack","Way Down","Yellow Dot"};
        String linksKids[] = {"https://www.onlinefreekidsgames.com/app/Crazy-Runner/", "https://www.onlinefreekidsgames.com/app/Fruit-Slasher/", "https://www.onlinefreekidsgames.com/app/Plumber/", "https://www.onlinefreekidsgames.com/app/Brick-Out/", "https://www.onlinefreekidsgames.com/app/Traffic-Command/", "https://www.onlinefreekidsgames.com/app/Traffic/",
                "https://www.onlinefreekidsgames.com/app/Balloons-Creator/","https://www.onlinefreekidsgames.com/app/Box-Size/","https://www.onlinefreekidsgames.com/app/Bridge-Dawn/","https://www.onlinefreekidsgames.com/app/Colored-Water-Pin/","https://www.onlinefreekidsgames.com/app/Dot-Shot/",
                "https://www.onlinefreekidsgames.com/app/Green-Prickle/","https://www.onlinefreekidsgames.com/app/Instruments-For-Kids/","https://www.onlinefreekidsgames.com/app/Line-Road/","https://www.onlinefreekidsgames.com/app/Poison-Attack/","https://www.onlinefreekidsgames.com/app/Way-Down/","https://www.onlinefreekidsgames.com/app/Yellow-Dot/"};

        simpleGrid = (ExpandableHeightGridView) findViewById(R.id.simpleGridViewKids); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logosKids, namesKids, linksKids);
        simpleGrid.setAdapter(customAdapter);
        simpleGrid.setExpanded(true);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isNetworkAvailable()) {
                    Toast.makeText(KidsGames.this, "Please connect to the internet", Toast.LENGTH_LONG).show();
                } else {
                    intentgame = new Intent(KidsGames.this, SecondActivity.class);
                    intentgame.putExtra("link", linksKids[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(KidsGames.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
    }

    private void createAd(){
        AdRequest adRequest = new AdRequest.Builder().build();
        createInterstitialAd(adRequest);
    }

    private void createInterstitialAd(AdRequest adRequest){
        InterstitialAd.load(this,"ca-app-pub-5036969984637277/2776030245", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.d("admob", "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                        startActivity(intentgame); // start Intent
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("admob", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        createAd();
        super.onResume();
    }
}
