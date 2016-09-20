package com.bc.bao.myswiperefreshlayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2016/9/12.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<String> datas;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public MyAdapter(List<String> list){
        datas = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int position, String str){
        datas.add(0,str);
        notifyItemInserted(position);
    }
    public void removeData(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public  ViewHolder(View itemView){
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_rv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onClick(v, getLayoutPosition(),
                                datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
    interface OnItemClickListener{
        void  onClick(View v, int position, String str);

    }
}
