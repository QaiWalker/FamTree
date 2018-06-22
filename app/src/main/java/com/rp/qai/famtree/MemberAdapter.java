package com.rp.qai.famtree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberAdapter extends ArrayAdapter<Member> {
    Context context;
    ArrayList<Member> members;
    int resource;
    public MemberAdapter(Context context, int resource, ArrayList<Member> members) {
        super(context, resource, members);
        this.context = context;
        this.members = members;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvRelation = rowView.findViewById(R.id.tvRelation);
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvAddress = rowView.findViewById(R.id.tvAddress);
        TextView tvNumber = rowView.findViewById(R.id.tvNumber);
        ImageView displayPic = rowView.findViewById(R.id.displayPic);

        Member member = members.get(position);
        tvName.setText(member.getName());
        tvRelation.setText(member.getRelation());
        tvTitle.setText(member.getTitle());
        tvAddress.setText(member.getAddress());
        tvNumber.setText(""+member.getNumber());
        return rowView;
    }
}
