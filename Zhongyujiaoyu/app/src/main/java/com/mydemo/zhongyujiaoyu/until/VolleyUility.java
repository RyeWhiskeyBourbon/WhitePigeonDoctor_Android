package com.mydemo.zhongyujiaoyu.until;

import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Chunlei.Li on 16/2/13.
 */
public class VolleyUility {

    public static ImageLoader.ImageListener getSimpleImageListener(final ImageView view) {
        return new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                }
            }
        };
    }

    /**
     * 用于Adapter，防止图片显示错位
     *
     * @param view
     * @return
     */
    public static ImageLoader.ImageListener getTagImageListener(final ImageView view) {
        return new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null && ObjectUtils.isEquals(view.getTag(), response.getRequestUrl())) {
                    view.setImageBitmap(response.getBitmap());
                }
            }
        };
    }

    public static ImageLoader.ImageListener getImageListener(final ImageView view,
                                                             final int defaultImageResId, final int errorImageResId) {
        return new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (errorImageResId != 0) {
                    view.setImageResource(errorImageResId);
                }
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                } else if (defaultImageResId != 0) {
                    view.setImageResource(defaultImageResId);
                }
            }
        };
    }
}
