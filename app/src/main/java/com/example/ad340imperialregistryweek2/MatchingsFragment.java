package com.example.ad340imperialregistryweek2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchingsFragment extends Fragment {

    public MatchingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        private ImageButton toastButton;
        String myString = "You Liked this";

        public ViewHolder(LayoutInflater inflater, final ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            picture = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_text);

            toastButton = (ImageButton) itemView.findViewById(R.id.share_button);

            toastButton.setOnClickListener(v ->
                    Toast.makeText(name.getContext(),"you like", Toast.LENGTH_SHORT) .show());
            //todoModel.updateTodoItemById(item);


        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 3;
        private TodoItem[] mPlaces;
        private String[] mPlaceDesc;
        private String[] mPlacePictures;


        public ContentAdapter(Context context) {

            FirebaseTodoViewModel viewModel = new FirebaseTodoViewModel();

            viewModel.getTodoItems(
                    (ArrayList<TodoItem> todoItems) ->{

                        mPlaces = new TodoItem [todoItems.size()];
                        //mPlacePictures = new String [todoItems.size()];
                        for (int i = 0 ; i < todoItems.size(); i++){
                            mPlaces[i] = todoItems.get(i);
                        }
                        notifyDataSetChanged();
                        }
            );
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Picasso.get().load(mPlaces[position % mPlaces.length].getImageUrl()).into(holder.picture);
            holder.name.setText(mPlaces[position % mPlaces.length].getName());


            //holder.description.setText(mPlaceDesc[position % mPlaceDesc.length].getUid());
        }

        @Override
        public int getItemCount() {
            if (mPlaces == null){
                return 0;
            }
            return mPlaces.length;
        }
    }
}