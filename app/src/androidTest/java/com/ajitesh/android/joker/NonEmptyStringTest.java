package com.ajitesh.android.joker;

/**
 * Created by ajitesh on 10/6/16.
 */
import android.test.AndroidTestCase;
import android.util.Log;

public class NonEmptyStringTest extends AndroidTestCase {

    @Override
    protected void runTest() throws Throwable {
        super.runTest();

        try {
            String string = new EndpointsAsyncTask(getContext()).execute().get();
            assertNotNull(string);
            assertTrue(string.length() > 0);
            Log.v("NonEmpty:: ",string);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
