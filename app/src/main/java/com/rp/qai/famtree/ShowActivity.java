package com.rp.qai.famtree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Member> member;
    MemberAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        member = new ArrayList<Member>();
        lv = findViewById(R.id.mainBranchList);
        aa = new MemberAdapter(this, R.layout.row, member);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(ShowActivity.this,
                        ModifyActivity.class);
                Member data = member.get(position);
                i.putExtra("data", data);
                startActivityForResult(i, 9);
            }
        });
        DBHelper dbh = new DBHelper(ShowActivity.this);
        member.clear();
        member.addAll(dbh.getAllMembers());
        dbh.close();
        aa.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(ShowActivity.this);
            member.clear();
            member.addAll(dbh.getAllMembers());
            dbh.close();
            aa.notifyDataSetChanged();

        }

    }
}
