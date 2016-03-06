package instavans.sanchit.instavans.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParsePush;

import java.util.List;

import butterknife.Bind;

import butterknife.ButterKnife;
import instavans.sanchit.instavans.DashboardActivity;
import instavans.sanchit.instavans.Instavans;
import instavans.sanchit.instavans.R;
import instavans.sanchit.instavans.adapter.JobUpdater;
import instavans.sanchit.instavans.datamodel.FindJob;
import instavans.sanchit.instavans.restapi.models.ActionAPI;
import instavans.sanchit.instavans.restapi.models.FindJobsAPI;
import instavans.sanchit.instavans.utilties.InstavanPrefs;
import instavans.sanchit.instavans.utilties.VerticalSpaceItemDecoration;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class FragmentJobs extends Fragment {

    @Bind(R.id.joblist) RecyclerView jobOpt;
    JobUpdater jobUpdater;
    InstavanPrefs mPrefs;
    List<FindJob> jobsList;
    Activity activity;
    private AlertDialog alertD;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_jobs, null);
        ButterKnife.bind(this, rootView);
        mPrefs = InstavanPrefs.getInstance(getActivity());
        updateList();
        Log.e("Porter",mPrefs.getPORTERID());
        return rootView;

    }
    public void updateList()
    {
        Call<FindJobsAPI> findJobsAPICall = Instavans.getRestClient(getActivity()).getApiService().getJobs(mPrefs.getPORTERID());
        findJobsAPICall.enqueue(new Callback<FindJobsAPI>() {
            @Override
            public void onResponse(Response<FindJobsAPI> response, Retrofit retrofit) {
                if (response.body().getSuccess()) {
                    jobsList = response.body().getResponse();
                    jobUpdater = new JobUpdater(getActivity(), jobsList, 1, FragmentJobs.this);
                    jobOpt.setAdapter(jobUpdater);
                    jobOpt.setLayoutManager(new LinearLayoutManager(getActivity()));
                    jobOpt.addItemDecoration(new VerticalSpaceItemDecoration(5, 3));

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Something Went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Went wrong in Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void alertshow(final int Pos) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        View promptView = layoutInflater.inflate(R.layout.custom_alert, null);
        final FindJob fj = jobsList.get(Pos);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setView(promptView);
        TextView header = (TextView) promptView.findViewById(R.id.alert_header_text);
        final TextView body = (TextView) promptView.findViewById(R.id.alert_body_text);
        final TextView tvPostive = (TextView) promptView.findViewById(R.id.positiveText);
        final TextView tvNegative = (TextView) promptView.findViewById(R.id.negativeText);
        body.setText("Are you ready to do the Job?");
        header.setText("Job Acceptance");
        tvNegative.setText("CANCEL");
        tvPostive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.dismiss();
                Call<ActionAPI> actionAPICall = Instavans.getRestClient(activity).getApiService().setJobAction(mPrefs.getPORTERID(),String.valueOf(fj.getShipperId()),
                                                                                                                String.valueOf(fj.getJobNumber()),1);
                actionAPICall.enqueue(new Callback<ActionAPI>() {
                    @Override
                    public void onResponse(Response<ActionAPI> response, Retrofit retrofit) {
                        if (response.body().getSuccess()) {
                            updateList();
                            ParsePush.subscribeInBackground("Job_" + fj.getJobNumber());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });


            }
        });
        tvNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.dismiss();
                Call<ActionAPI> actionAPICall = Instavans.getRestClient(activity).getApiService().setJobAction(mPrefs.getPORTERID(),String.valueOf(fj.getShipperId()),
                        String.valueOf(fj.getJobNumber()),2);
                actionAPICall.enqueue(new Callback<ActionAPI>() {
                    @Override
                    public void onResponse(Response<ActionAPI> response, Retrofit retrofit) {
                        if (response.body().getSuccess()) {
                            updateList();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });

            }
        });
        // create an alert dialog
        alertD = alertDialogBuilder.create();

        alertD.show();

    }

}
