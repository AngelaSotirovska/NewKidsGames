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

public class ShootingGames extends AppCompatActivity {

    Intent intentgame;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooting_games);
        createAd();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle("Shooting games");

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#01579b"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        ExpandableHeightGridView simpleGridShooting;
        int logosShooting[] = {R.drawable.kingdomdefender,R.drawable.rangervszombies,R.drawable.rightshot,R.drawable.shootrobbers,R.drawable.spacepurge,R.drawable.tankdefender,R.drawable.zombieshooter,
                R.drawable.duckshooter,R.drawable.tankwars,R.drawable.shoottheballoon};
        String namesShooting[] = {"Kingdom Defense","Ranger vs Zombies","Right Shot","Shoot Robbers","Space Purge","Tank Defender","Zombie Shooter","Duck Shooter","Tank Wars",
                "Shoot the balloon"};
        String linksShooting[] = {"https://www.onlinefreekidsgames.com/app/Kingdom-Defense/","https://www.onlinefreekidsgames.com/app/Ranger-vs-Zombies/","https://www.onlinefreekidsgames.com/app/Right-Shot/","https://www.onlinefreekidsgames.com/app/Shoot-Robbers/",
                "https://www.onlinefreekidsgames.com/app/Space-Purge/","https://www.onlinefreekidsgames.com/app/Tank-Defender/","https://www.onlinefreekidsgames.com/app/Zombie-Shooter/",
                "https://www.onlinefreekidsgames.com/app/Duck-Shooter/","https://www.onlinefreekidsgames.com/app/Tank-Wars/","https://www.onlinefreekidsgames.com/app/Shoot-The-Balloon/"};
        simpleGridShooting =(ExpandableHeightGridView) findViewById(R.id.simpleGridViewShooting);
        CustomAdapter customAdapter5 = new CustomAdapter(getApplicationContext(), logosShooting, namesShooting, linksShooting);
        simpleGridShooting.setAdapter(customAdapter5);
        simpleGridShooting.setExpanded(true);
        simpleGridShooting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isNetworkAvailable()) {
                    Toast.makeText(ShootingGames.this, "Please connect to the internet", Toast.LENGTH_LONG).show();
                } else {
                    intentgame = new Intent(ShootingGames.this, SecondActivity.class);
                    intentgame.putExtra("link", linksShooting[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(ShootingGames.this);
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

    private void createAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        createInterstitialAd(adRequest);
    }

    private void createInterstitialAd(AdRequest adRequest) {
        InterstitialAd.load(this, "ca-app-pub-5036969984637277/2776030245", adRequest, new InterstitialAdLoadCallback() {
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