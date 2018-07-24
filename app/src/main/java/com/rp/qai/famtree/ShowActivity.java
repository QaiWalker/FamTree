package com.rp.qai.famtree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.show) {
            Intent intent = new Intent(ShowActivity.this, ShowActivity.class);
            startActivity(intent);
        } else if (id == R.id.add){
            Intent intent = new Intent(ShowActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
