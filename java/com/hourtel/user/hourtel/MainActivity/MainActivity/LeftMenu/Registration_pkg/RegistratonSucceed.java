package com.hourtel.user.hourtel.MainActivity.MainActivity.LeftMenu.Registration_pkg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hourtel.user.hourtel.MainActivity.MainActivity.MainActivity;
import com.hourtel.user.hourtel.R;

public class RegistratonSucceed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraton_succeed);

        TextView iconButton = (TextView) findViewById(R.id.go_to_home);
        iconButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(RegistratonSucceed.this, MainActivity.class);
                RegistratonSucceed.this.startActivity(intent);
            }
        });

    }
}
