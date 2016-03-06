package instavans.sanchit.instavans.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by shashankvaibhav on 13/10/2015.
 */
public class FetchAddressIntentService extends IntentService {

    private static final String TAG = "FetchAddressIS";
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME = "in.justride";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

    protected ResultReceiver mReceiver;

    public FetchAddressIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String errorMessage = "";
        mReceiver = intent.getParcelableExtra(RECEIVER);
        Location location = intent.getParcelableExtra(LOCATION_DATA_EXTRA);

        if (location == null) {
            errorMessage = "NO location data provided";
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
        } catch (IOException ioException) {
            errorMessage = "Service not available";

        } catch (IllegalArgumentException illegalArgumentException) {
            errorMessage = "Invalid lat & long used";
        }
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "No address found";
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);

            deliverResultToReceiver(SUCCESS_RESULT,address);
        }
    }

    private void deliverResultToReceiver(int resultCode,Address address) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESULT_DATA_KEY, address);
        mReceiver.send(resultCode, bundle);
    }

    private void deliverResultToReceiver(int resultCode,String error) {
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY, error);
        mReceiver.send(resultCode, bundle);
    }


}
