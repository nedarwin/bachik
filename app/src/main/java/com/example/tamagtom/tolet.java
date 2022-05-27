package com.example.tamagtom;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.material.progressindicator.BaseProgressIndicator;

public class tolet extends Fragment implements View.OnDragListener, View.OnLongClickListener {
    private ImageView banana, toil,bath;
    private static final String IMAGEVIEW_TAG = "icon bitmap";


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tolet, container, false);
        banana = v.findViewById(R.id.imageView2);
        toil = v.findViewById(R.id.imageView8);
        bath=v.findViewById(R.id.imageView18);
        banana.setTag(IMAGEVIEW_TAG);
        banana.setOnLongClickListener(this);
        toil.setOnDragListener(this);
        bath.setOnDragListener(this);
        return v;
    }
    private Tamag tom;
    public tolet(Tamag tom) {
        this.tom = tom;
    }
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    v.invalidate();
                    return true;
                }
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

            case DragEvent.ACTION_DRAG_EXITED:

            case DragEvent.ACTION_DRAG_ENDED:

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DROP:
                tom.doToil();

                Animation animation = AnimationUtils.loadAnimation(this.getContext(),
                        R.anim.trans);
                banana.startAnimation(animation);
                v.invalidate();
                return true;
            default:
                break;
        }
        return false;
    }

    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
        v.startDrag(data
                , dragshadow
                , v
                , 0
        );
        return true;
    }
}