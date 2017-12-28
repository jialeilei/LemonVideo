package com.lei.lemonvideo.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lei.lemonvideo.R;

/**
 * Created by lei on 2017/12/14.
 */
public class ImageUtils {

    private static final float VER_POSTER_RATIO = 0.73f;//0.73
    private static final float HOR_POSTER_RATIO = 1.5f;

    public static void displayImage(ImageView img,String url){
        if (img != null && url != null){
            Glide.with(img.getContext()).load(url).into(img);
        }
    }

    public static void displayImage(ImageView img,String url,int width,int height){
        if (img != null && url != null && width > 0 && height > 0){
            if (width > height){
                Glide.with(img.getContext())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(height, width)
                        .fitCenter()//自适应
                        .error(R.mipmap.ic_launcher)
                        .into(img);
            }else {
                Glide.with(img.getContext())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(width, height)
                        .centerCrop()//对图片进行了剪裁
                        .error(R.mipmap.ic_launcher)
                        .into(img);
            }
        }
    }



    private static int getScreenWidthPixel(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }

    public static Point getHorPostSize(Context context, int columns) {
        return getPoint(context,columns,HOR_POSTER_RATIO);
    }

    public static Point getVerPostSize(Context context,int columns){
        return getPoint(context,columns,VER_POSTER_RATIO);
    }

    private static Point getPoint(Context context,int columns,float ratio){
        int width = getScreenWidthPixel(context) / columns;
        width = width - (int)context.getResources().getDimension(R.dimen.dimen_8dp);
        int height = Math.round( (float)width / ratio );
        Point point = new Point(width,height);
        return point;
    }

}
