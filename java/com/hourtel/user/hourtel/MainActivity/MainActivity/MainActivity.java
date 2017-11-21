package com.hourtel.user.hourtel.MainActivity.MainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Favorites.FavoritesHotelsList;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.ArrayAdapter_LeftMenu;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Login_pkg.Login;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Messages.EmailHistory;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg.Registration;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Settings_pkg.Settings;
import com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.UserProfile.UserProfileSettings;
import com.hourtel.user.hourtel.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    ListView menuListView;
    private ConstraintLayout leftMenu;
//    _SwipeListenerLeftMenu swipeListenerLeftMenu;
//    private GestureDetectorCompat mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String languageToLoad  = "it"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);
        mainLayout = (ConstraintLayout) findViewById(R.id.main_layout);


        leftMenu = (ConstraintLayout) findViewById(R.id.left_menu_layout);
        mainLayout = (ConstraintLayout) findViewById(R.id.main_layout);
        menuListView = (ListView) findViewById(R.id.menu_listview);


        //  ICON MENU

        ImageView iconMenu = (ImageView) findViewById(R.id.icon_menu);
        iconMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {

                manageLeftMenu();

            }
        });
        ImageView iconCloseMenu = (ImageView) findViewById(R.id.menu_close);
        iconCloseMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {

                manageLeftMenu();

            }
        });


        // LOGIN

        isLogged();



        // LEFT MENU

//        mDetector = new GestureDetectorCompat(this, new _SwipeListenerLeftMenu(MainActivity.this));
//        swipeListenerLeftMenu = new _SwipeListenerLeftMenu(MainActivity.this);

    }


    /*
    *
    *
    *
    *           FUNCTIONS
    *
    *
    *
    * */


    public void manageLeftMenu() {

        int i=0, n=mainLayout.getWidth()-200;

        if (mainLayout.getX() > 0) {

            for (; i<=n; i++) {

                mainLayout.animate().x(n-i).setDuration(250).start();
            }
        } else {

            for (; i<=n; i++) {

                mainLayout.animate().x(i).setDuration(250).start();
            }
        }
    }

    public void isLogged() {

//        SHARED PREFERENCES
        SharedPreferences prefs = getSharedPreferences("login", MainActivity.this.MODE_PRIVATE);
        String textData = prefs.getString("logged", "");

        System.out.println(textData);

        if (textData.trim().equals("") == false) {

            String[] list = new String[]{"HOME", "PROFILO", "MESSAGGI", "PREFERITI", "IMPOSTAZIONI", "LOGOUT"};

            ArrayAdapter_LeftMenu menuListAdapter = new ArrayAdapter_LeftMenu(this, list);
            menuListView.setAdapter(menuListAdapter);

            menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction frTransaction = fragmentManager.beginTransaction();

                    switch (position) {

                        case 0: {

//                        Fragment_Home fragmentHome = new Fragment_Home();
//                        frTransaction.replace(R.id.main_layout, fragmentHome);
//                        frTransaction.addToBackStack(null);
//                        frTransaction.commit();
                            break;
                        }
                        case 1: {

                            Intent intent = new Intent(MainActivity.this, UserProfileSettings.class);
                            MainActivity.this.startActivity(intent);

                            break;
                        }
                        case 2: {

                            Intent intent = new Intent(MainActivity.this, EmailHistory.class);
                            MainActivity.this.startActivity(intent);

                            break;
                        }
                        case 3: {

                            Intent intent = new Intent(MainActivity.this, FavoritesHotelsList.class);
                            MainActivity.this.startActivity(intent);

                            break;
                        }
                        case 4: {

                            Intent intent = new Intent(MainActivity.this, Settings.class);
                            MainActivity.this.startActivity(intent);

                            break;
                        }
                        case 5: {

//                            SHARED PREFERENCES
                            SharedPreferences prefs = getSharedPreferences("login", MainActivity.this.MODE_PRIVATE);

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.clear();
                            editor.commit();

                            isLogged();

//                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                            MainActivity.this.startActivity(intent);

                            break;
                        }

                    }

                    manageLeftMenu();
                    menuListView.setItemChecked(position, true);

                }
            });

        } else {

            String[] list = new String[]{"HOME", "LOGIN", "REGISTRATI"};

            ArrayAdapter_LeftMenu menuListAdapter = new ArrayAdapter_LeftMenu(this, list);
            menuListView.setAdapter(menuListAdapter);

            menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction frTransaction = fragmentManager.beginTransaction();

                    switch (position) {

                        case 0: {

//                        Fragment_Home fragmentHome = new Fragment_Home();
//                        frTransaction.replace(R.id.main_layout, fragmentHome);
//                        frTransaction.addToBackStack(null);
//                        frTransaction.commit();
                            break;
                        }
                        case 1: {

                            Intent intent = new Intent(MainActivity.this, Login.class);
                            intent.putExtra("from", "MainActivity");
                            MainActivity.this.startActivity(intent);

                            break;
                        }
                        case 2: {

                            Intent intent = new Intent(MainActivity.this, Registration.class);
                            MainActivity.this.startActivity(intent);

                            break;
                        }

                    }

                    manageLeftMenu();
                    menuListView.setItemChecked(position, true);

                }
            });
        }

    }

}


