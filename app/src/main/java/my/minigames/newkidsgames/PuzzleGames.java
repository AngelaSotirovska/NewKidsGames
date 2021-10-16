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

public class PuzzleGames extends AppCompatActivity {

    Intent intentgame;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_games);
        createAd();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle("Puzzle games");

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#01579b"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        ExpandableHeightGridView simpleGrid;
        int logosPuzzle[] = {R.drawable.jellymatch3, R.drawable.candysuperlines, R.drawable.candymatch3, R.drawable.fruitsnake, R.drawable.halloweenbubbleshooter, R.drawable.goldminer, R.drawable.goldminerjack,
                R.drawable.profesorbubble, R.drawable.supercolorlines, R.drawable.hotjewels, R.drawable.filledglass, R.drawable.filledglass2nogravity, R.drawable.flatjumper2, R.drawable.gemsshot, R.drawable.golfpin,
                R.drawable.happyfilledglass, R.drawable.jellyboom, R.drawable.jumpingball, R.drawable.jumpingbox, R.drawable.mazecontrol, R.drawable.monsterdestroyer,
                R.drawable.movethepin, R.drawable.redrope, R.drawable.routedigger2, R.drawable.routedigger, R.drawable.slipblock, R.drawable.threearcade};
        String namesPuzzle[] = {"Jelly Match 3", "Candy Super-Lines", "Candy Match 3", "Fruit Snake", "Halloween Bubble", "Gold Miner", "Gold Miner Jack", "Professor Bubble", "Super Color Lines", "Hot Jewels",
                "Filled Glass", "Filled Glass 2 No gravity", "Flat Jumper 2", "Gems Shot", "Golf Pin", "Happy Filled Glass", "Jelly Boom", "Jumping Ball", "Jumping Box", "Monster Destroyer",
                "Maze Control", "Move The Pin", "Red Rope", "Route Digger 2", "Route Digger", "Slip Block", "Three Arcade"};
        String linksPuzzle[] = {"https://www.onlinefreekidsgames.com/app/Jelly-Match-3/", "https://www.onlinefreekidsgames.com/app/Candy-Super-Lines/", "https://www.onlinefreekidsgames.com/app/Candy-Match-3/", "https://www.onlinefreekidsgames.com/app/Fruit-Snake/", "https://www.onlinefreekidsgames.com/app/Halloween-Bubble-Shooter/", "https://www.onlinefreekidsgames.com/app/Gold-Miner/",
                "https://www.onlinefreekidsgames.com/app/Gold-Miner-Jack/", "https://www.onlinefreekidsgames.com/app/Professor-Bubble/", "https://www.onlinefreekidsgames.com/app/Super-Color-Lines/", "https://www.onlinefreekidsgames.com/app/Hot-Jewels/", "https://www.onlinefreekidsgames.com/app/Filled-Glass/",
                "https://www.onlinefreekidsgames.com/app/Filled-Glass-2-No-Gravity/", "https://www.onlinefreekidsgames.com/app/Flat-Jumper-2/", "https://www.onlinefreekidsgames.com/app/Gems-Shot/", "https://www.onlinefreekidsgames.com/app/Golf-Pin/",
                "https://www.onlinefreekidsgames.com/app/Happy-Filled-Glass/", "https://www.onlinefreekidsgames.com/app/Jelly-Boom/", "https://www.onlinefreekidsgames.com/app/Jumping-Ball/",
                "https://www.onlinefreekidsgames.com/app/Jumping-Box/", "https://www.onlinefreekidsgames.com/app/Maze-Control/", "https://www.onlinefreekidsgames.com/app/Monster-Destroyer/", "https://www.onlinefreekidsgames.com/app/Move-The-Pin/",
                "https://www.onlinefreekidsgames.com/app/Red-Rope/", "https://www.onlinefreekidsgames.com/app/Route-Digger-2/", "https://www.onlinefreekidsgames.com/app/Route-Digger/", "https://www.onlinefreekidsgames.com/app/Slip-Blocks/",
                "https://www.onlinefreekidsgames.com/app/Three-Arcade/"};

        simpleGrid = (ExpandableHeightGridView) findViewById(R.id.simpleGridViewPuzzle); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logosPuzzle, namesPuzzle, linksPuzzle);
        simpleGrid.setAdapter(customAdapter);
        simpleGrid.setExpanded(true);
        // implement setOnItemClickListener event on GridView
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isNetworkAvailable()){
                    Toast.makeText(PuzzleGames.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                }else {
                    intentgame = new Intent(PuzzleGames.this, SecondActivity.class);
                    intentgame.putExtra("link", linksPuzzle[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(PuzzleGames.this);
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