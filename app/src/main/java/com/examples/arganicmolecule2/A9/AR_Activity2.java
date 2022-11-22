package com.examples.arganicmolecule2.A9;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.R;

public class AR_Activity2 extends AppCompatActivity implements SensorEventListener {
    ImageView amino_acid_molecule_image;
    String model;

    SensorManager sm;
    Sensor shakeDevice;
    SensorEventListener sel;

    float xAccl, yAccl, zAccl, xOldAccl, yOldAccl, zOldAccl, changeInX, changeInY, changeInZ;

    boolean initialActivity = false, shakeInitialized = true, isSensorAvailable;
    float shakeThreshold = 5f;

    Vibrator vibrateDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar2);
        amino_acid_molecule_image = (ImageView) findViewById(R.id.amino_acid_image);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            shakeDevice = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensorAvailable = true;
        } else {
            Toast.makeText(this, "Accelerometer sensor not available on this device.",
                    Toast.LENGTH_LONG).show();
        }
        vibrateDevice = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent imageIntent = getIntent();
        if (imageIntent.hasExtra("image")) {
            String moleculeImage = imageIntent.getExtras().getString("image");
            Glide.with(AR_Activity2.this).load(moleculeImage)
                    .into(amino_acid_molecule_image);
        }
        if (imageIntent.hasExtra("aminoName")) {
            model = imageIntent.getExtras().getString("aminoName");
        } else {
            Toast.makeText(AR_Activity2.this,
                    "Image retrieval failed in Activity2", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xAccl = sensorEvent.values[0];
        yAccl = sensorEvent.values[1];
        zAccl = sensorEvent.values[2];

        if (initialActivity) {
            changeInX = Math.abs(xOldAccl - xAccl);
            changeInY = Math.abs(yOldAccl - yAccl);
            changeInZ = Math.abs(zOldAccl - zAccl);

            if ((changeInX > shakeThreshold && changeInY > shakeThreshold)
                    || (changeInY > shakeThreshold && changeInZ > shakeThreshold)
                    || (changeInZ > shakeThreshold && changeInX > shakeThreshold)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrateDevice.vibrate(VibrationEffect.createOneShot(500,
                            VibrationEffect.DEFAULT_AMPLITUDE));

                    amino_acid_molecule_image.setImageAlpha(1000);
                    amino_acid_molecule_image.animate().scaleYBy(0.3F).scaleXBy(0.3F);
                    amino_acid_molecule_image.animate().alpha(0.1f).setStartDelay(500)
                            .setDuration(2900);

                    new Handler().postDelayed(() -> {
                        Intent AR3 = new Intent(AR_Activity2.this,
                                AR_Activity3.class);
                        AR3.putExtra("aminoName", model);
                        startActivity(AR3);
                        finish();
                    }, 2900);
                } else {
                    vibrateDevice.vibrate(500);

                    amino_acid_molecule_image.setImageAlpha(1000);
                    amino_acid_molecule_image.animate().scaleYBy(0.3F).scaleXBy(0.3F);
                    amino_acid_molecule_image.animate().alpha(0.1f).setStartDelay(500)
                            .setDuration(3000);

                    new Handler().postDelayed(() -> {
                        Intent AR3 = new Intent(AR_Activity2.this,
                                AR_Activity3.class);
                        AR3.putExtra("aminoName", model);
                        startActivity(AR3);
                        finish();
                    }, 3500);
                }
            }
        }

        xOldAccl = xAccl;
        yOldAccl = yAccl;
        zOldAccl = zAccl;
        initialActivity = true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSensorAvailable) {
            sm.registerListener(this, shakeDevice, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
}
