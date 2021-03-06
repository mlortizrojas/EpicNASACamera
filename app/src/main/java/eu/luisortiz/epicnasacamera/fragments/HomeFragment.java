package eu.luisortiz.epicnasacamera.fragments;

import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.Text;

import java.io.IOException;
import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.List;

import eu.luisortiz.epicnasacamera.R;
import eu.luisortiz.epicnasacamera.downloader.AsyncResponse;
import eu.luisortiz.epicnasacamera.downloader.EpicImageDownloader;
import eu.luisortiz.epicnasacamera.entity.EpicImage;

/**
 * Created by mlortiz on 20/10/2015.
 */
public class HomeFragment extends MyFragment implements AsyncResponse{

    TextView tvHome;
    @Override
    protected void initialize() {

        //TODO prueba
        String url = "http://epic.gsfc.nasa.gov/api/images.php?date=2015-10-18";


        tvHome = (TextView) getView().findViewById(R.id.tvHome);

        EpicImageDownloader epicImageDownloader = new EpicImageDownloader();
        epicImageDownloader.delegate = this;
        epicImageDownloader.execute(url);

    }


    @Override
    public void processDownloadEpicImage(Object output) {
        Gson gson = new Gson();
        EpicImage[] epicImages = gson.fromJson((String) output, EpicImage[].class);

        //TODO El campo coords de EpicImage viene como tipo STRING y no OBJECT en el JSON, hay que extraer los valores y transformarlo en objeto

        tvHome.setText((String) output);

    }
}
