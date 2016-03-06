package instavans.sanchit.instavans.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class FragmentCompleteJobs extends Fragment {

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
                    jobUpdaterActive = new JobsAdapterActive(getActivity(), actionList,3);
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

}
