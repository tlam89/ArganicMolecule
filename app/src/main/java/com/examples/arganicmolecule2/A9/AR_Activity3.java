package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
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

public class AR_Activity3 extends AppCompatActivity {
    private ArFragment arFragment;
    StorageReference storageReference;
    String model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar3);

        storageReference= FirebaseStorage.getInstance().getReference("Amino Acids/"+"A.glb");
        model = storageReference.toString();
        Toast.makeText(AR_Activity3.this, model , Toast.LENGTH_LONG).show();

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        arFragment.onCreate(savedInstanceState);

        // adding listener for detecting plane.
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();

            // adding model to the scene
            ModelRenderable.builder()
                    .setSource(this, RenderableSource.builder().setSource(
                                    this, Uri.parse(model), RenderableSource.SourceType.GLB)
                            .setScale(0.1f)
                            .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                            .build())
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable));
        });
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode node = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(node); // need to attach to parent
        transformableNode.setRenderable(modelRenderable);
        transformableNode.getLight();

        arFragment.getArSceneView().getScene().addChild(node); // adddding only parent node, so the child nodes will be added automatically
        transformableNode.select();
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


