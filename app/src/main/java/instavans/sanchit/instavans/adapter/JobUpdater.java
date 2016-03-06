package instavans.sanchit.instavans.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import instavans.sanchit.instavans.DashboardActivity;
import instavans.sanchit.instavans.R;
import instavans.sanchit.instavans.datamodel.FindJob;
import instavans.sanchit.instavans.fragment.FragmentActiveJobs;
import instavans.sanchit.instavans.fragment.FragmentCompleteJobs;
import instavans.sanchit.instavans.fragment.FragmentJobs;
import instavans.sanchit.instavans.utilties.Universal;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class JobUpdater extends RecyclerView.Adapter<JobUpdater.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<FindJob> jobs = Collections.emptyList();
    int jobtype;
    FragmentJobs fragmentJobs;
    FragmentActiveJobs fragmentActiveJobs;
    FragmentCompleteJobs fragmentCompleteJobs;
    LatLng currentp,shipper;

    public JobUpdater( Context context,List<FindJob> jobs,int jobtype,FragmentJobs fragmentJobs) {
        this.context = context;
        inflater =  LayoutInflater.from(context);
        this.jobs = jobs;
        this.jobtype = jobtype;
        this.fragmentJobs = fragmentJobs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_job_description, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FindJob current = jobs.get(position);
        holder.jobId.setText(""+current.getJobNumber());
        if(current.getAddress()!=null) {
            holder.jobdirections.setText(""+current.getAddress());
        }else
        {
            holder.jobdirections.setText("getDirections");
        }
        holder.jobPrice.setText("₹ "+current.getPrice());
        holder.jobTime.setText(Universal.convertTime(current.getStartTime()));
        Log.e("Hello", "Hello");
        holder.jobdirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callMapActivity(current.getAddress(),new LatLng(current.getLatitude(),current.getLongitude()));
            }
        });
        holder.jobItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jobtype==1)
                {
                    fragmentJobs.alertshow(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return jobs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.jobid)TextView jobId;
        @Bind(R.id.time)TextView jobTime;
        @Bind(R.id.jodPrice)TextView jobPrice;
        @Bind(R.id.jodDirection)TextView jobdirections;
        @Bind(R.id.jobLayout)LinearLayout jobItemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void callMapActivity(String Address,LatLng shipper) {
        Uri gmmIntentUri = null;
        gmmIntentUri = Uri.parse("geo:" + shipper.latitude + "," + shipper.longitude + "?q=" + shipper.latitude + "," + shipper.longitude + "?q=" + Address);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }

}
