package ke.co.kinyua.forzateams.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import ke.co.kinyua.forzateams.R;
import ke.co.kinyua.forzateams.adapters.TeamRecyclerAdapter;
import ke.co.kinyua.forzateams.adapters.models.TeamModel;
import ke.co.kinyua.forzateams.utils.AppConstants;
import ke.co.kinyua.forzateams.web.ForzaBase;
import ke.co.kinyua.forzateams.web.result_models.Team;
import ke.co.kinyua.forzateams.web.request.FetchTeamsRequest;

/**
 * Created by Kinyua on 2/18/18.
 */

public class MainActivity extends ForzaBase implements View.OnClickListener{



    RecyclerView mRecyclerView;
    ArrayList<TeamModel> mTeamList = new ArrayList<>();


    public static MainActivity newInstance() {
        return new MainActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
        getTeams();
    }

    private void initVars() {
        mRecyclerView = (RecyclerView)findViewById(R.id.expense_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getTeams(){
        FetchTeamsRequest request = new FetchTeamsRequest();
        postJob(AppConstants.FETCH_TEAMS_SERVICE, request);
    }

    @Override
    public void handleResult(String s, Object o) {
        
        if (s.equals(AppConstants.FETCH_TEAMS_SERVICE)) {
            mTeamList = new ArrayList<>();

            for (Team team : (Team[]) o) {
                
                mTeamList.add(new TeamModel(team.getName(),
                            team.isNational(),
                            team.getCountry_name()));
            }

            mRecyclerView.setAdapter(new TeamRecyclerAdapter(this, mTeamList) {
                @Override
                public void someAction(String name) {
                    Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void handleFault(String s, Object o) {
        if (s.equals(AppConstants.FETCH_TEAMS_SERVICE)) {
            Toast.makeText(MainActivity.this,o.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
        }
    }

}
