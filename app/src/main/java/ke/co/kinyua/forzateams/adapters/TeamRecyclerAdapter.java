package ke.co.kinyua.forzateams.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ke.co.kinyua.forzateams.R;
import ke.co.kinyua.forzateams.interfaces.IOnClickTeamListener;
import ke.co.kinyua.forzateams.adapters.models.TeamModel;
import ke.co.kinyua.forzateams.views.FZTextView;

/**
 * Created by Kinyua on 2/18/18.
 */
public abstract class TeamRecyclerAdapter extends RecyclerView.Adapter<TeamRecyclerAdapter.ViewHolder> implements IOnClickTeamListener {

    Context context;
    ArrayList<TeamModel> teamModels;
    private View itemview;

    public TeamRecyclerAdapter(Context context, ArrayList<TeamModel> teamModels) {

        this.context = context;
        this.teamModels=teamModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teams, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (teamModels.get(position).isIs_national()) {
            holder.mClubTextView.setTextColor(ContextCompat.getColor(context, R.color.national));
            holder.mIsNationalTextView.setTextColor(ContextCompat.getColor(context, R.color.national));
            holder.mCountryTextView.setTextColor(ContextCompat.getColor(context, R.color.national));
        } else {
            holder.mClubTextView.setTextColor(ContextCompat.getColor(context, R.color.not_national));
            holder.mIsNationalTextView.setTextColor(ContextCompat.getColor(context, R.color.not_national));
            holder.mCountryTextView.setTextColor(ContextCompat.getColor(context, R.color.not_national));
        }
      holder.mClubTextView.setText(teamModels.get(position).getClub_name());
      holder.mIsNationalTextView.setText((teamModels.get(position).isIs_national()) ? "True" :"False");
      holder.mCountryTextView.setText(teamModels.get(position).getCountry_name());
      
        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someAction(teamModels.get(position).getClub_name());
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FZTextView mClubTextView;
        FZTextView mIsNationalTextView;
        FZTextView mCountryTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mClubTextView=(FZTextView) itemView.findViewById(R.id.club_name);
            mIsNationalTextView=(FZTextView) itemView.findViewById(R.id.is_national);
            mCountryTextView=(FZTextView) itemView.findViewById(R.id.country);
        }
    }
}