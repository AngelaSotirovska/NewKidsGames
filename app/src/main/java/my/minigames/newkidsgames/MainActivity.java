package my.minigames.newkidsgames;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Toast;

import my.minigames.newkidsgames.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private InterstitialAd mInterstitialAd;

    Intent intentgame;

    private AppBarConfiguration mAppBarConfiguration;
    ExpandableHeightGridView simpleGridKids;
    int logosKids[] = {R.drawable.crazyrunner, R.drawable.fruitslasher, R.drawable.plumber, R.drawable.brickout, R.drawable.trafficcommand, R.drawable.traffic,R.drawable.balloonscreator, R.drawable.boxsize,R.drawable.bridgedown,R.drawable.coloredwaterpin,
            R.drawable.dotshot,R.drawable.greenprickle,R.drawable.instrumentsforkids,R.drawable.lineroad,R.drawable.poisonattack,R.drawable.waydawn,R.drawable.yellowdot};
    String namesKids[] = {"Crazy Runner", "Friut Slasher", "Plumber", "Brick Out", "Traffic Command", "Traffic","Balloons Creator","Box Size","Bridge Down","Colored Water Pin","Dot Shot",
            "Green Pickle","Instruments For Kids","Line Road","Poison Attack","Way Down","Yellow Dot"};
    String linksKids[] = {"https://www.onlinefreekidsgames.com/app/Crazy-Runner/", "https://www.onlinefreekidsgames.com/app/Fruit-Slasher/", "https://www.onlinefreekidsgames.com/app/Plumber/", "https://www.onlinefreekidsgames.com/app/Brick-Out/", "https://www.onlinefreekidsgames.com/app/Traffic-Command/", "https://www.onlinefreekidsgames.com/app/Traffic/",
            "https://www.onlinefreekidsgames.com/app/Balloons-Creator/","https://www.onlinefreekidsgames.com/app/Box-Size/","https://www.onlinefreekidsgames.com/app/Bridge-Dawn/","https://www.onlinefreekidsgames.com/app/Colored-Water-Pin/","https://www.onlinefreekidsgames.com/app/Dot-Shot/",
            "https://www.onlinefreekidsgames.com/app/Green-Prickle/","https://www.onlinefreekidsgames.com/app/Instruments-For-Kids/","https://www.onlinefreekidsgames.com/app/Line-Road/","https://www.onlinefreekidsgames.com/app/Poison-Attack/","https://www.onlinefreekidsgames.com/app/Way-Down/","https://www.onlinefreekidsgames.com/app/Yellow-Dot/"};

    ExpandableHeightGridView simpleGridPuzzle;
    int logosPuzzle[] = {R.drawable.jellymatch3,R.drawable.candysuperlines,R.drawable.candymatch3,R.drawable.fruitsnake,R.drawable.halloweenbubbleshooter,R.drawable.goldminer,R.drawable.goldminerjack,
            R.drawable.profesorbubble,R.drawable.supercolorlines,R.drawable.hotjewels,R.drawable.filledglass,R.drawable.filledglass2nogravity,R.drawable.flatjumper2,R.drawable.gemsshot,R.drawable.golfpin,
            R.drawable.happyfilledglass,R.drawable.jellyboom,R.drawable.jumpingball,R.drawable.jumpingbox,R.drawable.mazecontrol,R.drawable.monsterdestroyer,
            R.drawable.movethepin,R.drawable.redrope,R.drawable.routedigger2,R.drawable.routedigger,R.drawable.slipblock,R.drawable.threearcade};
    String namesPuzzle[] = {"Jelly Match 3","Candy Super-Lines","Candy Match 3","Fruit Snake","Halloween Bubble","Gold Miner","Gold Miner Jack","Professor Bubble","Super Color Lines","Hot Jewels",
            "Filled Glass","Filled Glass 2 No gravity","Flat Jumper 2","Gems Shot","Golf Pin","Happy Filled Glass","Jelly Boom","Jumping Ball","Jumping Box","Monster Destroyer",
            "Maze Control","Move The Pin","Red Rope","Route Digger 2","Route Digger","Slip Block","Three Arcade"};
    String linksPuzzle[] = {"https://www.onlinefreekidsgames.com/app/Jelly-Match-3/","https://www.onlinefreekidsgames.com/app/Candy-Super-Lines/","https://www.onlinefreekidsgames.com/app/Candy-Match-3/","https://www.onlinefreekidsgames.com/app/Fruit-Snake/","https://www.onlinefreekidsgames.com/app/Halloween-Bubble-Shooter/","https://www.onlinefreekidsgames.com/app/Gold-Miner/",
            "https://www.onlinefreekidsgames.com/app/Gold-Miner-Jack/","https://www.onlinefreekidsgames.com/app/Professor-Bubble/","https://www.onlinefreekidsgames.com/app/Super-Color-Lines/","https://www.onlinefreekidsgames.com/app/Hot-Jewels/","https://www.onlinefreekidsgames.com/app/Filled-Glass/",
            "https://www.onlinefreekidsgames.com/app/Filled-Glass-2-No-Gravity/","https://www.onlinefreekidsgames.com/app/Flat-Jumper-2/","https://www.onlinefreekidsgames.com/app/Gems-Shot/","https://www.onlinefreekidsgames.com/app/Golf-Pin/",
            "https://www.onlinefreekidsgames.com/app/Happy-Filled-Glass/","https://www.onlinefreekidsgames.com/app/Jelly-Boom/","https://www.onlinefreekidsgames.com/app/Jumping-Ball/",
            "https://www.onlinefreekidsgames.com/app/Jumping-Box/","https://www.onlinefreekidsgames.com/app/Maze-Control/","https://www.onlinefreekidsgames.com/app/Monster-Destroyer/","https://www.onlinefreekidsgames.com/app/Move-The-Pin/",
            "https://www.onlinefreekidsgames.com/app/Red-Rope/","https://www.onlinefreekidsgames.com/app/Route-Digger-2/","https://www.onlinefreekidsgames.com/app/Route-Digger/","https://www.onlinefreekidsgames.com/app/Slip-Blocks/",
            "https://www.onlinefreekidsgames.com/app/Three-Arcade/"};

    ExpandableHeightGridView simpleGridAnimal;
    int logosAnimal[] = {R.drawable.fishingfrenzy,R.drawable.christmaspandarun,R.drawable.fishrescuepullthepin};
    String namesAnimal[] = {"Fishing Frenzy","Christmas Panda Run","Fish Rescue Pull The Pin"};
    String linksAnimal[] = {"https://www.onlinefreekidsgames.com/app/Fishing-Frenzy/","https://www.onlinefreekidsgames.com/app/Christmas-Panda-Run/","https://www.onlinefreekidsgames.com/app/Fish-Rescue-Pull-The-Pin/"};

    ExpandableHeightGridView simpleGridRacing;
    int logosRacing[] = {R.drawable.speedracer,R.drawable.trafficracer};
    String namesRacing[] = {"Speed Racer","Traffic Racer"};
    String linksRacing[] = {"https://www.onlinefreekidsgames.com/app/Speed-Racer/","https://www.onlinefreekidsgames.com/app/Traffic-Racer/"};

    ExpandableHeightGridView simpleGridShooting;
    int logosShooting[] = {R.drawable.kingdomdefender,R.drawable.rangervszombies,R.drawable.rightshot,R.drawable.shootrobbers,R.drawable.spacepurge,R.drawable.tankdefender,R.drawable.zombieshooter,
            R.drawable.duckshooter,R.drawable.tankwars,R.drawable.shoottheballoon};
    String namesShooting[] = {"Kingdom Defense","Ranger vs Zombies","Right Shot","Shoot Robbers","Space Purge","Tank Defender","Zombie Shooter","Duck Shooter","Tank Wars",
            "Shoot the balloon"};
    String linksShooting[] = {"https://www.onlinefreekidsgames.com/app/Kingdom-Defense/","https://www.onlinefreekidsgames.com/app/Ranger-vs-Zombies/","https://www.onlinefreekidsgames.com/app/Right-Shot/","https://www.onlinefreekidsgames.com/app/Shoot-Robbers/",
            "https://www.onlinefreekidsgames.com/app/Space-Purge/","https://www.onlinefreekidsgames.com/app/Tank-Defender/","https://www.onlinefreekidsgames.com/app/Zombie-Shooter/",
            "https://www.onlinefreekidsgames.com/app/Duck-Shooter/","https://www.onlinefreekidsgames.com/app/Tank-Wars/","https://www.onlinefreekidsgames.com/app/Shoot-The-Balloon/"};

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createAd();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_kids, R.id.nav_puzzle, R.id.nav_animal, R.id.nav_racing, R.id.nav_shooting)
                .setDrawerLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_kids:
                        Intent intentkids = new Intent(MainActivity.this,KidsGames.class);
                        startActivity(intentkids);
                        return true;
                    case R.id.nav_puzzle:
                        Intent interpuzzle = new Intent(MainActivity.this,PuzzleGames.class);
                        startActivity(interpuzzle);
                        return true;
                    case R.id.nav_animal:
                        Intent intentanimal = new Intent(MainActivity.this,AnimalGames.class);
                        startActivity(intentanimal);
                        return true;
                    case R.id.nav_racing:
                        Intent intentracing = new Intent(MainActivity.this,RacingGames.class);
                        startActivity(intentracing);
                        return true;
                    case R.id.nav_shooting:
                        Intent intentshooting = new Intent(MainActivity.this,ShootingGames.class);
                        startActivity(intentshooting);
                        return true;
                    case R.id.nav_share:
                        Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/NewKidsGames"));
                        startActivity(fb);
                        return true;
                }
                return true;
            }
        });

        simpleGridKids = (ExpandableHeightGridView) findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logosKids, namesKids, linksKids);
        simpleGridKids.setAdapter(customAdapter);
        simpleGridKids.setExpanded(true);
        // implement setOnItemClickListener event on GridView
        simpleGridKids.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isNetworkAvailable()){
                    Toast.makeText(MainActivity.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                }else {
                    intentgame = new Intent(MainActivity.this, SecondActivity.class);
                    intentgame.putExtra("link", linksKids[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
        simpleGridPuzzle =(ExpandableHeightGridView) findViewById(R.id.simpleGridView2);
        CustomAdapter customAdapter2 = new CustomAdapter(getApplicationContext(), logosPuzzle, namesPuzzle, linksPuzzle);
        simpleGridPuzzle.setAdapter(customAdapter2);
        simpleGridPuzzle.setExpanded(true);
        simpleGridPuzzle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isNetworkAvailable()){
                    Toast.makeText(MainActivity.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                }else {
                    intentgame = new Intent(MainActivity.this, SecondActivity.class);
                    intentgame.putExtra("link", linksPuzzle[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
        simpleGridAnimal =(ExpandableHeightGridView) findViewById(R.id.simpleGridView3);
        CustomAdapter customAdapter3 = new CustomAdapter(getApplicationContext(), logosAnimal, namesAnimal, linksAnimal);
        simpleGridAnimal.setAdapter(customAdapter3);
        simpleGridAnimal.setExpanded(true);
        simpleGridAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isNetworkAvailable()){
                    Toast.makeText(MainActivity.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                }else {
                    // set an Intent to Another Activity
                    intentgame = new Intent(MainActivity.this, SecondActivity.class);
                    intentgame.putExtra("link", linksAnimal[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
        simpleGridRacing =(ExpandableHeightGridView) findViewById(R.id.simpleGridView4);
        CustomAdapter customAdapter4 = new CustomAdapter(getApplicationContext(), logosRacing, namesRacing, linksRacing);
        simpleGridRacing.setAdapter(customAdapter4);
        simpleGridRacing.setExpanded(true);
        simpleGridRacing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!isNetworkAvailable()){
                    Toast.makeText(MainActivity.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                }else {
                    intentgame = new Intent(MainActivity.this, SecondActivity.class);
                    intentgame.putExtra("link", linksRacing[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
        simpleGridShooting =(ExpandableHeightGridView) findViewById(R.id.simpleGridView5);
        CustomAdapter customAdapter5 = new CustomAdapter(getApplicationContext(), logosShooting, namesShooting, linksShooting);
        simpleGridShooting.setAdapter(customAdapter5);
        simpleGridShooting.setExpanded(true);
        simpleGridShooting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isNetworkAvailable()) {
                    Toast.makeText(MainActivity.this, "Please connect to the internet", Toast.LENGTH_LONG).show();
                } else {
                    intentgame = new Intent(MainActivity.this, SecondActivity.class);
                    intentgame.putExtra("link", linksShooting[position]); // put image data in Intent
                    if (mInterstitialAd != null) {
                        mInterstitialAd.show(MainActivity.this);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        startActivity(intentgame); // start Intent
                    }
                    // set an Intent to Another Activity
                }
            }
        });
        this.setTitle("");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.policy){
            Intent intent = new Intent(MainActivity.this,PolicyActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.terms){
            Intent intent = new Intent(MainActivity.this,TermsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    protected void onResume() {
        createAd();
        super.onResume();
    }
}