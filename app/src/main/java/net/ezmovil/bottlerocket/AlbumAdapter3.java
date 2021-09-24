package net.ezmovil.bottlerocket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.ezmovil.bottlerocket.info.infoWeatherImage;

import java.util.List;

public class AlbumAdapter3 extends RecyclerView.Adapter<AlbumAdapter3.MyViewHolder> {

    private Context mContext;
    private List<Album3> albumList;

    public String global;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView low, weatherType, dayOfTheWeek, high;
        public ImageView image, overflow;

        public MyViewHolder(View view) {
            super(view);

            low = (TextView) view.findViewById(R.id.low);
            weatherType = (TextView) view.findViewById(R.id.weatherType);
            dayOfTheWeek = (TextView) view.findViewById(R.id.dayOfTheWeek);
            high = (TextView) view.findViewById(R.id.high);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            image = (ImageView) view.findViewById(R.id.thumbnail_weather);

        }
    }


    public AlbumAdapter3(Context mContext, List<Album3> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card2_weather, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album3 album3 = albumList.get(position);
        holder.low.setText("low :" + album3.getLow());
        holder.high.setText("high : " + album3.getHigh());
        holder.dayOfTheWeek.setText("day :" + album3.getDayOfTheWeek());
        holder.weatherType.setText("weather Type :" +album3.getweatherType());

        if (album3.getweatherType().contains("snow_sleet")) {
            Glide.with(mContext)
                    .load(R.drawable.icon_weather_active_ic_partly_cloudy_active)
                    .into(holder.image);
        }
        if (album3.getweatherType().contains("light_rain")) {
            Glide.with(mContext)
                    .load(R.drawable.icon_weather_active_ic_light_rain_active)
                    .into(holder.image);
        }
        if (album3.getweatherType().contains("heavyRain")) {
                Glide.with(mContext)
                        .load(R.drawable.icon_weather_active_ic_heavy_rain_active)
                        .into(holder.image);
        }
        if (album3.getweatherType().contains("sunny")) {
            Glide.with(mContext)
                    .load(R.drawable.icon_weather_active_ic_sunny_active)
                    .into(holder.image);
        }
        if (album3.getweatherType().contains("snowSleet")) {
            Glide.with(mContext)
                    .load(R.drawable.icon_weather_active_ic_sunny_active)
                    .into(holder.image);
        }else{
            Glide.with(mContext)
                    .load(R.drawable.icon_weather_active_ic_cloudy_active)
                    .into(holder.image);
        }

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, String uri) {

        global = uri;

        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_share:
                    Toast.makeText(mContext, "Compartir imagen", Toast.LENGTH_SHORT).show();


                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, global);
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, "Share Image from BetterFly");
                    mContext.startActivity(shareIntent);


                    return true;
                case R.id.action_details:
                    /*Toast.makeText(mContext, "Detalles", Toast.LENGTH_SHORT).show();
                    Intent weatherIntent = new Intent(mContext, WeatherActivity.class);
                    //weatherIntent.putExtra(Intent.EXTRA_TEXT, global);
                    //weatherIntent.setType("text/plain");
                    mContext.startActivity(weatherIntent);
                    */

                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}