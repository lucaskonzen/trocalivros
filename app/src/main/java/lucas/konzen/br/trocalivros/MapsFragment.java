package lucas.konzen.br.trocalivros;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,GoogleMap.OnMapClickListener {

    private static final String TAG = "MapsFragment";

    private GoogleMap mMap;

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            mMap = googleMap;

            mMap.getUiSettings().setZoomControlsEnabled(true); //abilita zoom

            mMap.setMyLocationEnabled(true);

          //  LatLng minhaLocalizacao = new LatLng(location.getLatitude(),location.getLongitude());

        }catch (SecurityException ex){
            Log.e(TAG,"Erro",ex);
        }







        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
    }

    @Override
    public void onMapClick(LatLng latLng) {
        //quando clicar no mapa
    }
}
