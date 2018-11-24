package com.app.britech.riung.Utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.app.britech.riung.R;

import java.util.HashMap;
import java.util.Map;
import com.app.britech.riung.R.styleable;

/**
 * Created by mrx on 7/12/2017.
 */

@SuppressLint("AppCompatCustomView")
public class TypeFaceTextView extends TextView {
    private static Map<String, Typeface> mTypefaces;

    public TypeFaceTextView(final Context context) {
        this(context, null);
    }

    public TypeFaceTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TypeFaceTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        if (mTypefaces == null) {
            mTypefaces = new HashMap<String, Typeface>();
        }

        // prevent exception in Android Studio / ADT interface builder
        if (this.isInEditMode()) {
            return;
        }

        final TypedArray array = context.obtainStyledAttributes(attrs, styleable.TypefaceTextView);
        if (array != null) {
            final String typefaceAssetPath = array.getString(
                    R.styleable.TypefaceTextView_customTypeface);

            if (typefaceAssetPath != null) {
                Typeface typeface;

                if (mTypefaces.containsKey(typefaceAssetPath)) {
                    typeface = mTypefaces.get(typefaceAssetPath);
                } else {
                    AssetManager assets = context.getAssets();
                    typeface = Typeface.createFromAsset(assets, typefaceAssetPath);
                    mTypefaces.put(typefaceAssetPath, typeface);
                }

                setTypeface(typeface);
            }
            array.recycle();
        }
    }
}
