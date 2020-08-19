package online.genit.genitonline;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomeContentAdapter extends RecyclerView.Adapter<HomeContentAdapter.ViewHolder> {

    private List<HomeContent> homeContentsList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView userName, createDate, content;
        public View divider;

        public ViewHolder(View view) {
            super(view);
            icon = (ImageView) view.findViewById(R.id.icon);
            userName = (TextView) view.findViewById(R.id.userName);
            createDate = (TextView) view.findViewById(R.id.createDate);
            content = (TextView) view.findViewById(R.id.content);
            divider = (View) view.findViewById(R.id.divider);
        }
    }

    public HomeContentAdapter(List<HomeContent> homeContentsList){
        this.homeContentsList = homeContentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_list_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeContent homeContent = homeContentsList.get(i);
        viewHolder.icon.setImageResource(homeContent.getIcon());
        viewHolder.userName.setText(homeContent.getUserName());
        viewHolder.createDate.setText(homeContent.getCreateDate());
        viewHolder.content.setText(homeContent.getContent());
    }

    @Override
    public int getItemCount() {
        return homeContentsList.size();
    }


}
