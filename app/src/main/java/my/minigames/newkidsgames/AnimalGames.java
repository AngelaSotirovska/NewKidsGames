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

public class AnimalGames extends AppCompatActivity {

    Intent intentgame;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_games);
        createAd();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

        getSupportActionBar().setTitle("Animal games");

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#01579b"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        ExpandableHeightGridView simpleGridAnimal;
        int logosAnimal[] = {R.drawable.fishingfrenzy,R.drawable.christmaspandarun,R.drawable.fishrescuepullthepin};
        String namesAnimal[] = {"Fishing Frenzy","Christmas Panda Run","Fish Rescue Pull The Pin"};
        String linksAnimal[] = {"https://www.onlinefreekidsgames.com/app/Fishing-Frenzy/","https://www.onlinefreekidsgames.com/app/Christmas-Panda-Run/","https://www.onlinefreekidsgames.com/app/Fish-Rescue-Pull-The-Pin/"};


        simpleGridAnimal =(ExpandableHeightGridView) findViewById(R.id.simpleGridViewAnimal);
        CustomAdapter customAdapter3 = new CustomAdapter(getApplicationContext(), logosAnimal, namesAnimal, linksAnimal);
        simpleGridAnimal.setAdapter(customAdapter3);
        simpleGridAnimal.setExpanded(true);
        simpleGridAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isNetworkAvailable()){
                    Toast.makeText(AnimalGames.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                }else {
                    // set an Intent to Another Activity
                    intentgame = new Intent(AnimalGames.this, SecondActivity.class);
                    intentgame.putExtra("link", linksAnimal[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(AnimalGames.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
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