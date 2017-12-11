package com.zixing.lbsdemo;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
//        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
//
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
//        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        addMarkers();
    }

    private void addMarkers() {

        aMap.setOnMarkerClickListener(markerClickListener);
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                WaterStation station = (WaterStation) marker.getObject();
                View view = inflater.inflate(R.layout.info_window,null);
                TextView nameTV = (TextView) view.findViewById(R.id.name);
                TextView allowanceTV = (TextView) view.findViewById(R.id.allowance);
                TextView emptyTV = (TextView) view.findViewById(R.id.empty);
                nameTV.setText(station.getStationName());
                allowanceTV.setText("商品余量 "+station.getAllowance());
                emptyTV.setText("空桶余量 "+station.getEmpty());

                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                WaterStation station = (WaterStation) marker.getObject();
                Toast.makeText(MainActivity.this,station.getStationName(),Toast.LENGTH_SHORT).show();
                marker.hideInfoWindow();
            }
        });

        List<WaterStation> waterStations = getPointList();
        int len = waterStations.size();
        ArrayList<MarkerOptions> markerOptionses = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            WaterStation station = waterStations.get(i);
            MarkerOptions options = new MarkerOptions();
            options.title(station.getStationName());
            options.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.location)));
            options.position(new LatLng(station.getLat(),station.getLng()));
            options.setFlat(false);
            markerOptionses.add(options);
//            Marker marker =  aMap.addMarker(new MarkerOptions().title(station.getStationName())
//                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.location)))
//                    .position(new LatLng(station.getLat(),station.getLng()))
//                    .setFlat(true));
//            marker.setObject(station);

        }
        ArrayList<Marker> markers = aMap.addMarkers(markerOptionses,true);
        for (int i = 0; i < len; i++) {
            markers.get(i).setObject( waterStations.get(i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {

            return false;
        }
    };

    private List<WaterStation> getPointList() {
        List<WaterStation> waterStations = new ArrayList<>();
        waterStations.add(new WaterStation(39.993755, 116.467987,"望京第一水站",230,198));
        waterStations.add(new WaterStation(39.985589, 116.469306,"鸟巢第5水站",23,1980));
        waterStations.add(new WaterStation(39.990946, 116.48439,"SOHO第一水站",20,98));
        waterStations.add(new WaterStation(40.000466, 116.463384,"天南门第3水站",30,18));
        waterStations.add(new WaterStation(39.975426, 116.490079,"故宫第一水站",2310,1098));
        waterStations.add(new WaterStation(40.016392, 116.464343,"颐和园第7水站",320,198));
        waterStations.add(new WaterStation(39.959215, 116.464882,"圆明园第1水站",340,98));
        waterStations.add(new WaterStation(39.962136, 116.495418,"潘家园第3水站",20,18));
        waterStations.add(new WaterStation(39.994012, 116.426363,"王府井第2水站",254,123));
        waterStations.add(new WaterStation(39.960666, 116.444798,"长城第一水站",170,352));
        waterStations.add(new WaterStation(39.972976, 116.424517,"香山第4水站",0,150));
        waterStations.add(new WaterStation(39.951329, 116.455913,"北京市第13水站",2930,9198));
        return waterStations;

    }


}

