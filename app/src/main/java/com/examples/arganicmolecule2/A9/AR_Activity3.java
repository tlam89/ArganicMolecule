package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.examples.arganicmolecule2.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class AR_Activity3 extends AppCompatActivity {
    private ArFragment arFragment;
    StorageReference storageReference;
    String model_path;

    Uri glbUri;
    File glb_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar3);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        storageReference= FirebaseStorage.getInstance().getReference("Amino Acids/A.glb");
        model_path = "https://firebasestorage.googleapis.com/v0/b/arganicmolecule2-66023.appspot.com/o/Amino%20Acids%2FA.glb?alt=media&token=7f57e996-74b1-4521-a90a-f4a0b09f4b4f";
        Log.i("model_path", model_path);
        //Create Uri for ArFragment
        Long glbID = doInBackground();
        Log.i("GLB_ID", glbID.toString());
        onPostExecute(glbID);
        Log.i("GLB_ID2", glbID.toString());
        receiveDownloadId(glbID);
        Log.i("GLB_ID3", glb_path.toString());



        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this, RenderableSource.builder().setSource(this, Uri.parse("G.glb"), RenderableSource.SourceType.GLB)
                            .setScale(0.1f)
                            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                            .build())
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load GLB renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });
        });
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

    private Long doInBackground() {
        DownloadManager downloadManager = (DownloadManager) AR_Activity3.this.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(model_path);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("A.glb");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "A.glb");
        return downloadManager.enqueue(request);
    }

    private void onPostExecute(Long result) {
        AR_Activity3.this.receiveDownloadId(result);
    }

    private void receiveDownloadId(Long downloadId) {
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//        glbUri = downloadManager.getUriForDownloadedFile(downloadId);
//        glb_path = new File(glbUri.getPath());
//        return glb_path;
        String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        Toast.makeText(AR_Activity3.this, filepath, Toast.LENGTH_LONG).show();

        File directory = new File(filepath.toString());
        File[] files = directory.listFiles();
        for (File f: files) {
            if (f.toString().equals(filepath +"/A.glb")) {
                Toast.makeText(AR_Activity3.this, f.toString(), Toast.LENGTH_LONG).show();
                glb_path = f;
                break;
            }
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


