package instavans.sanchit.instavans;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

import butterknife.Bind;
import butterknife.ButterKnife;
import instavans.sanchit.instavans.adapter.DashboardAdapter;
import instavans.sanchit.instavans.fragment.FragmentActiveJobs;
import instavans.sanchit.instavans.fragment.FragmentCompleteJobs;
import instavans.sanchit.instavans.fragment.FragmentJobs;
import instavans.sanchit.instavans.restapi.models.SignupAPI;
import instavans.sanchit.instavans.services.FetchAddressIntentService;
import instavans.sanchit.instavans.utilties.InstavanPrefs;
import instavans.sanchit.instavans.utilties.VerticalSpaceItemDecoration;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class DashboardActivity extends AppCompatActivity implements ResultCallback<LocationSettingsResult>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{

    @Bind(R.id.tokenTextView)TextView tokenTextView;
    @Bind(R.id.amountTextView)TextView amountTextView;
    @Bind(R.id.jobOptions)
    RecyclerView jobOptions;
    @Bind(R.id.container_body)
    FrameLayout containerBody;
    Fragment currentFragment;

    private GoogleApiClient mGoogleApiClient;
    private InstavanPrefs mPrefs;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        mPrefs = InstavanPrefs.getInstance(this);
        tokenTextView.setText(mPrefs.getPORTERID());
        startIntentService(new LatLng(12.33, 63.72));

        buildGoogleApiClient();
        createLocationRequest();
        buildLocationSettingsRequest();
        checkLocationSettings();
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(DashboardActivity.this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    protected void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                .checkLocationSettings(mGoogleApiClient, mLocationSettingsRequest);
        result.setResultCallback(this);
    }

    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                if (mLastLocation == null) {
                    startLocationUpdates();
                    break;
                }
                startIntentService(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                    try {
                        // Show the dialog by calling startResolutionForResult(), and
                        // check the result
                        // in onActivityResult().
                        status.startResolutionForResult(DashboardActivity.this, 200);

                    } catch (IntentSender.SendIntentException e) {
                        //Logger.printLog(TAG, "PendingIntent unable to execute request.");
                        e.printStackTrace();

                    }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


                    switch (resultCode) {
                        case Activity.RESULT_OK:
                            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                            if (mLastLocation == null) {
                                startLocationUpdates();
                                break;
                            }else
                            {
                                startIntentService(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
                            }
                            break;
                        case Activity.RESULT_CANCELED:
                            checkLocationSettings();
                            break;

                    }
            }
    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient == null) return;
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void startIntentService(LatLng latLng) {

        Call<SignupAPI> signupAPICall =  Instavans.getRestClient(DashboardActivity.this).getApiService().useLatLng(mPrefs.getPORTERID(),12.93,77.63);
        signupAPICall.enqueue(new Callback<SignupAPI>() {
            @Override
            public void onResponse(Response<SignupAPI> response, Retrofit retrofit) {
                if(response.body().getSuccess())
                {
                    amountTextView.setText(response.body().getResponse().getTotal_earning());
                    dashboardAdapter = new DashboardAdapter(DashboardActivity.this,DashboardActivity.this);
                    jobOptions.setAdapter(dashboardAdapter);
                    jobOptions.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                    jobOptions.addItemDecoration(new VerticalSpaceItemDecoration(5,5));

                }else
                {
                    Toast.makeText(getApplicationContext(),response.body().getErrors(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            Address address;
            if (resultCode == FetchAddressIntentService.SUCCESS_RESULT) {
                address = resultData.getParcelable(FetchAddressIntentService.RESULT_DATA_KEY);
            }
            else
                return;

            String userCity = address.getLocality();
            LatLng currentLatlng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            startIntentService(currentLatlng);

            }
        }


    protected void startLocationUpdates() {

        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        // Stop location updates to save battery, but don't disconnect the
        // GoogleApiClient object.

        if (mGoogleApiClient!=null && mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    public void callFragmentNewJobs()
    {
        currentFragment = new FragmentJobs();
        callFragment(currentFragment);
    }
    public void callFragmentActiveJobs()
    {
        currentFragment = new FragmentActiveJobs();
        callFragment(currentFragment);

    }
    public void callFragmentCompleteJobs()
    {
        currentFragment = new FragmentCompleteJobs();
        callFragment(currentFragment);
    }

    public void callFragment(Fragment currentFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, currentFragment, currentFragment.getClass().getName()).commit();
        jobOptions.setVisibility(View.GONE);
        containerBody.setVisibility(View.VISIBLE);
    }

    public void onBackPressed() {
        if (currentFragment!=null) {
            Fragment currFrag = getSupportFragmentManager().findFragmentByTag(currentFragment.getClass().getName());
            if (containerBody.getVisibility() == View.VISIBLE) {
                getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().remove(currFrag);
                        containerBody.setVisibility(View.GONE);
                        containerBody.removeAllViews();
                        jobOptions.setVisibility(View.VISIBLE);

                } else if(jobOptions.getVisibility()==View.VISIBLE)  {
                    super.onBackPressed();
                    mPrefs.setIS_LOGGED_IN(false);
                }

            } else {
                super.onBackPressed();
            }


        }
}
