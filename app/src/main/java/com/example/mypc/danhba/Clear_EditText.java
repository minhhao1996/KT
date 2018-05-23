package com.example.mypc.danhba;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

public class Clear_EditText extends android.support.v7.widget.AppCompatEditText {
    Drawable crowssx,nonecrowssx,drawable;
    boolean visibale = false;
    public Clear_EditText(Context context) {
        super(context);
        khoitao();
    }

    public Clear_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public Clear_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }
    private  void khoitao(){
        crowssx = ContextCompat.getDrawable(getContext(),R.drawable.ic_clear_black_24dp);
        nonecrowssx = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        cauhinh();
    }
    private void cauhinh(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable [] drawables= getCompoundDrawables();
         drawable = visibale ? crowssx:nonecrowssx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(MotionEvent.ACTION_DOWN == event.getAction()&& event.getX() >=(getRight() - drawable.getBounds().width())){
            setText(" ");

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start ==0){
            Log.d("kiemtra",lengthAfter + "-" + lengthBefore);
            visibale = false;
            cauhinh();
        }else {
            visibale =true;
            cauhinh();
        }
    }
}
