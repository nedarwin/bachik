package com.example.tamagtom;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class sleep extends Fragment implements View.OnLongClickListener, View.OnDragListener {
    private ImageView banana, tree,sun,moon,bk;
    private static final String IMAGEVIEW_TAG = "icon bitmap";
    private final Tamag tom;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sleep, container, false);
        banana = v.findViewById(R.id.imageView4);
        tree = v.findViewById(R.id.imageView9);
        sun = v.findViewById(R.id.imageView15);
        moon = v.findViewById(R.id.imageView19);
        bk=v.findViewById(R.id.imageView20);
        moon.setOnClickListener(gm);
        banana.setTag(IMAGEVIEW_TAG);
        banana.setOnLongClickListener(this);
        tree.setOnDragListener(this);
        return v;
    }
    public sleep(Tamag tom) {
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
                tom.doSleep();
                night();
                v.invalidate();
                return true;
            default:
                break;
        }
        return false;
    }
    View.OnClickListener gm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            moon.setVisibility(View.INVISIBLE);
            sun.setVisibility(View.VISIBLE);
            bk.setAlpha(0.0f);
        }
    };

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

   public void night(){
        moon.setVisibility(View.VISIBLE);
        sun.setVisibility(View.INVISIBLE);
        bk.setAlpha(0.5f);


   }
}