package app.com.example.shalan.bakingudacity.Utils;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

/**
 * Created by noura on 10/07/2017.
 */

public class MainActivityIdlingResource implements IdlingResource {

    private boolean mIsIdleNow = false  ;

    @Nullable
    private volatile ResourceCallback mCallback ;
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
            mCallback = callback ;
    }

    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow =isIdleNow ;
         if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();

        }
    }

}
