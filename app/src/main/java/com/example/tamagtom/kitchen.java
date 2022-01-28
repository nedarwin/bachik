package com.example.tamagtom;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class kitchen extends Fragment implements View.OnDragListener, View.OnLongClickListener {
    private Tamag tom;
    private ImageView banana, food;
    private static final String IMAGEVIEW_TAG = "icon bitmap";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kitchen, container, false);
        food = v.findViewById(R.id.imageView10);
        banana = v.findViewById(R.id.imageView);
        food.setTag(IMAGEVIEW_TAG);
        food.setOnLongClickListener(this);
        banana.setOnDragListener(this);

        return v;

    }
    public kitchen(Tamag tom) {
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
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                ClipData.Item item = event.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                if(tom.hunger<=tom.HUNGER_CAN) {
                    tom.doEat();
                }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                v.invalidate();
                return true;
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
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