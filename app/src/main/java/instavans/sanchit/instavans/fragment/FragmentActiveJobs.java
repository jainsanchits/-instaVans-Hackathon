package instavans.sanchit.instavans.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import instavans.sanchit.instavans.Instavans;
import instavans.sanchit.instavans.R;
import instavans.sanchit.instavans.adapter.JobUpdater;
import instavans.sanchit.instavans.adapter.JobsAdapterActive;
import instavans.sanchit.instavans.chatActivity;
import instavans.sanchit.instavans.datamodel.Action;
import instavans.sanchit.instavans.restapi.models.ActionAPI;
import instavans.sanchit.instavans.restapi.models.ActionListAPI;
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
public class FragmentActiveJobs extends Fragment {

    @Bind(R.id.joblist)
    RecyclerView jobOpt;
    JobsAdapterActive jobUpdaterActive;
    InstavanPrefs mPrefs;
    Activity activity;
    private AlertDialog alertD;
    List<Action> actionList;


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
        activity = getActivity();
        mPrefs = InstavanPrefs.getInstance(getActivity());
        updateActiveList();
        return rootView;

    }

    public void updateActiveList()
    {
        Call<ActionListAPI> actionListAPICall =  Instavans.getRestClient(activity).getApiService().getActiveJobs(mPrefs.getPORTERID(),"1");
        actionListAPICall.enqueue(new Callback<ActionListAPI>() {
            @Override
            public void onResponse(Response<ActionListAPI> response, Retrofit retrofit) {
                if (response.body().getSuccess()) {
                    actionList = response.body().getResponse();
                    jobUpdaterActive = new JobsAdapterActive(getActivity(), actionList, 2, FragmentActiveJobs.this);
                    jobOpt.setAdapter(jobUpdaterActive);
                    jobOpt.setLayoutManager(new LinearLayoutManager(getActivity()));
                    jobOpt.addItemDecoration(new VerticalSpaceItemDecoration(5, 3));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                try {
                    Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
        });
    }

    public void alertshow(int Pos) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        View promptView = layoutInflater.inflate(R.layout.custom_alert, null);

        final Action action =  actionList.get(Pos);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setView(promptView);
        TextView header = (TextView) promptView.findViewById(R.id.alert_header_text);
        final TextView body = (TextView) promptView.findViewById(R.id.alert_body_text);
        final TextView tvPostive = (TextView) promptView.findViewById(R.id.positiveText);
        final TextView tvNegative = (TextView) promptView.findViewById(R.id.negativeText);

        tvNegative.setVisibility(View.GONE);
        header.setText("JOB");
        if(action.getAction()==1) {
            body.setText("Start your first step towards Earning Money");
            tvPostive.setText("Start");
        }else if (action.getAction()==3)
        {
            body.setText("Almost there. Get the job done and you'll get your money.");

            tvPostive.setText("Completed");
        }
        tvPostive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.dismiss();
                if (action.getAction() == 1) {
                    Call<ActionAPI> actionAPICall = Instavans.getRestClient(activity).getApiService().setJobActivity(mPrefs.getPORTERID(),
                            String.valueOf(action.getShipperId()), String.valueOf(action.getJobNumber()), 3);
                    actionAPICall.enqueue(new Callback<ActionAPI>() {
                        @Override
                        public void onResponse(Response<ActionAPI> response, Retrofit retrofit) {
                            updateActiveList();
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });

                } else if (action.getAction() == 3) {
                    Call<ActionAPI> actionAPICall = Instavans.getRestClient(activity).getApiService().setJobActivity(mPrefs.getPORTERID(),
                            String.valueOf(action.getShipperId()), String.valueOf(action.getJobNumber()), 4);
                    actionAPICall.enqueue(new Callback<ActionAPI>() {
                        @Override
                        public void onResponse(Response<ActionAPI> response, Retrofit retrofit) {
                            updateActiveList();
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }
            }
        });
        // create an alert dialog
        alertD = alertDialogBuilder.create();

        alertD.show();

    }
    public void callChat(int pos)
    {
        Intent i = new Intent();
        i.setClass(getActivity(),chatActivity.class);
        i.putExtra("JOBNUMBER", String.valueOf(actionList.get(pos).getJobNumber()));
        startActivity(i);
    }

}
