package app.com.example.shalan.bakingudacity.Utils;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by noura on 10/07/2017.
 */

public class MainActivityIdlingResource implements IdlingResource {
    private AtomicBoolean mIsIdleNow = new AtomicBoolean() ;

    @Nullable
    private volatile ResourceCallback mCallback ;
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
            mCallback = callback ;
    }


}
