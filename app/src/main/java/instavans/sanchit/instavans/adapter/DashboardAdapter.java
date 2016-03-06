package instavans.sanchit.instavans.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import instavans.sanchit.instavans.DashboardActivity;
import instavans.sanchit.instavans.R;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {
    private String tabTitles[];
    private Context context;
    private DashboardActivity dashboardActivity;
    private LayoutInflater inflater;

    public DashboardAdapter( Context context,DashboardActivity dashboardActivity) {
        this.context = context;
        this.dashboardActivity = dashboardActivity;
        inflater =  LayoutInflater.from(context);
        this.tabTitles = new String[] { "NEW JOBS", "ACTIVE JOBS","COMPLETED JOBS"};

    }

    @Override
    public DashboardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.jobtitle, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DashboardAdapter.MyViewHolder holder, final int position) {
        holder.jobTitle.setText(tabTitles[position]);
        holder.jobItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobOptions(position);
            }
        });

    }

    @Override
    public int getItemCount() {
     return tabTitles.length;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.jobTitle)TextView jobTitle;
        @Bind(R.id.jobItemLayout)LinearLayout jobItemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public void jobOptions(int pos)
    {
        switch (pos) {
            case 0:
                dashboardActivity.callFragmentNewJobs();
                break;
            case 1:
                dashboardActivity.callFragmentActiveJobs();
                break;
            case 2:
                dashboardActivity.callFragmentCompleteJobs();
                break;
        }
    }
}
