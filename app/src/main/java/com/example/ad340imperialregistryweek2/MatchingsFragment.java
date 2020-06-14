package com.example.ad340imperialregistryweek2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
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
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
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

        public ViewHolder(LayoutInflater inflater, final ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            picture = itemView.findViewById(R.id.card_image);
            name = itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_text);
            toastButton = (ImageButton) itemView.findViewById(R.id.share_button);

            toastButton.setOnClickListener(v ->
                    Toast.makeText(name.getContext(),"you liked " + name.getText(), Toast.LENGTH_SHORT) .show());
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 3;
        private TodoItem[] mPlaces;
        private String[] mPlaceDesc;
        private String[] mPlacePictures;

        LocationManager locationManager;
        double longitudeNetwork, latitudeNetwork;
        FirebaseTodoViewModel viewModel = new FirebaseTodoViewModel();

        public ContentAdapter(Context context) {

            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

            toggleNetworkUpdates();
        }

        private boolean checkLocation() {
            if(!isLocationEnabled()) {
                showAlert();
            }
            return isLocationEnabled();
        }

        private boolean isLocationEnabled() {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        private void showAlert() {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle(R.string.enable_location)
                    .setMessage(getString(R.string.location_message))
                    .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    })
                    .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
            dialog.show();
        }

        public void toggleNetworkUpdates() {
            if(!checkLocation()) {
                return;
            }
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2* 60 * 1000, 10, locationListenerNetwork);
                }
            }

        private final LocationListener locationListenerNetwork = new LocationListener() {
            public void onLocationChanged(Location location) {

                 longitudeNetwork = location.getLongitude();
                 latitudeNetwork = location.getLatitude();

                viewModel.getTodoItems(
                        (ArrayList<TodoItem> todoItems) ->{
                            ArrayList<TodoItem> filteredList = new ArrayList<>();

                            for (int i = 0; i < todoItems.size(); i++) {
                                double xSquare = Math.pow(Double.parseDouble(todoItems.get(i).getLongitude()) - longitudeNetwork, 2);
                                double ySquare = Math.pow(Double.parseDouble(todoItems.get(i).getLat()) - latitudeNetwork, 2);
                                double distance = Math.sqrt(xSquare + ySquare);
                                    if(distance <= 10){
                                        filteredList.add(todoItems.get(i));
                                }
                            }

                            mPlaces = new TodoItem [filteredList.size()];
                            for (int i = 0 ; i < filteredList.size(); i++){
                                mPlaces[i] = filteredList.get(i);
                            }
                            notifyDataSetChanged();
                        }
                );
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}

            @Override
            public void onProviderEnabled(String s) {}

            @Override
            public void onProviderDisabled(String s) {}
        };

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