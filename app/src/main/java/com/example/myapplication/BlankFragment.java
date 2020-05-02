package com.example.myapplication;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private String address;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        address = getArguments().getString("address");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapi);
        mapFragment.getMapAsync(this);

        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Geocoder coder = new Geocoder(getContext());
        List<Address> address1;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address1 = coder.getFromLocationName(address, 5);
            if (address == null) {
                Toast.makeText(getActivity(), "NOT POSSIBLE", Toast.LENGTH_SHORT).show();
            }

            map = googleMap;
            Address location = address1.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            map.addMarker(new MarkerOptions().position(p1).title("MY ADDRESS"));
            map.moveCamera(CameraUpdateFactory.newLatLng(p1));

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }
}
