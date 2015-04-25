package gko.app.gexam.student;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import gko.app.gexam.R;

/**
 * Created by MR.T on 4/25/2015.
 */
public class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.RuleViewHolder> {

    private LayoutInflater inflater;
    List<Rule> data = Collections.emptyList();

    public RuleAdapter(Context context, List<Rule> data) {
         inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RuleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.recylcerview_rule, viewGroup, false);
        RuleViewHolder ruleViewHolder = new RuleViewHolder(view);
        return ruleViewHolder;
    }

    @Override
    public void onBindViewHolder(RuleViewHolder viewHolder, int i) {

        Rule currentRule = data.get(i);
        viewHolder.title.setText(currentRule.title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RuleViewHolder extends RecyclerView.ViewHolder {

        TextView title;


        public RuleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
        }
    }
}
