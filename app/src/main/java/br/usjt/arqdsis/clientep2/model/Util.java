package br.usjt.arqdsis.clientep2.model;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;

import java.lang.reflect.Field;

import br.usjt.arqdsis.clientep2.R;

/**
 * Created by arqdsis on 05/10/2016.
 */
public class Util {

    public static Drawable getDrawable(Activity context, String name){
        Class<?> c = R.drawable.class;
        try{
            Field idField = c.getDeclaredField(name);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        }catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }
}
