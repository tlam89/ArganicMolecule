package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.examples.arganicmolecule2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public class AR_Activity3 extends AppCompatActivity {
    private ArFragment arFragment;
    FirebaseStorage storage;
    StorageReference storageRef;
    String model_path;
    String fb_path;
    Uri glbUri;
    String name = "C";
    String glb_path;
    private Renderable renderable;
    ModelRenderable modelRenderable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar3);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        arFragment.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference aminoRef = storageRef.child("Amino Acids/Y.glb");


        verifyStoragePermissions(this);




//        File download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        File file1 = new File(download.getAbsolutePath(), "1DAA.glb");
//        Log.i("file1", file1.toString());
//        Log.i("file1_can_read", String.valueOf(file1.isFile()));
//
//
//        Callable callable = new Callable() {
//            @Override
//            public InputStream call() throws Exception {
//                InputStream inputStream = new FileInputStream(file1);
//                return inputStream;
//            }
//        };

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();

            try {
                File tempfile = File.createTempFile("temp","glb");
                aminoRef.getFile(tempfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        buildModel(tempfile,anchor);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    private void buildModel(File file, Anchor anchor) {
        ModelRenderable.builder()
                .setSource(this, RenderableSource.builder().setSource(this,Uri.parse(file.getPath()), RenderableSource.SourceType.GLB)
                        .setScale(0.1f)
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
//                    .setSource(this, callable)
                .build()
                .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable));

    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode node = new AnchorNode(anchor);
        arFragment.getArSceneView().getScene().addChild(node);

        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(node);
        transformableNode.setRenderable(modelRenderable);
        transformableNode.getLight();
        transformableNode.select();
    }

//    private Long doInBackground(String path) {
//        DownloadManager downloadManager = (DownloadManager) AR_Activity3.this.getSystemService(Context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(path);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setTitle(name + ".glb");
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  name + ".glb");
//        return downloadManager.enqueue(request);
//    }
//
//    private void onPostExecute(Long result) {
//        AR_Activity3.this.receiveDownloadId(result);
//    }
//
//
//
//    private void receiveDownloadId(Long downloadId) {
////        DownloadManager downloadManager = (DownloadManager) AR_Activity3.this.getSystemService(Context.DOWNLOAD_SERVICE);
////        glb_path = downloadManager.getUriForDownloadedFile(downloadId);
////
//////        glb_path = new File(glbUri.getPath());
//////        return glb_path;
//
//    }
//
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}



//        Method 1: failed
//        String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//        Toast.makeText(AR_Activity3.this, filepath, Toast.LENGTH_LONG).show();

//        File directory = new File(filepath.toString());
//        File[] files = directory.listFiles();
//        for (File f: files) {
//            if (f.toString().equals(filepath +"/100D.glb")) {
//                Toast.makeText(AR_Activity3.this, f.toString(), Toast.LENGTH_LONG).show();
//                model_path = f.toString();
//            }
//        }


