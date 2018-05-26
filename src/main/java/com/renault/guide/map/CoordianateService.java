package com.renault.guide.map;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.renault.guide.knowledge.domain.WikiExtract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoordianateService
{
  RestTemplate restTemplate = new RestTemplate();

  List<String> locations = new ArrayList<>();

  Iterator<String> locationsIterator = locations.iterator();


  public String setPath(String query) throws Exception {

    String[] queryStr  = query.split(",");
    String startLat = queryStr[0];
    String startLng = queryStr[1];

    String endLat = queryStr[2];
    String endLng = queryStr[3];

    locations.add(startLat + "," + startLng);

    final URI uri = new URIBuilder()
        .setScheme("https")
        .setHost("maps.googleapis.com/maps/api/directions/json?units=imperial&origin="  + startLat + "," +  startLng + "&destination=" + endLat + "%2C" + endLng + "&key=AIzaSyDWzNcCbYSly5TgeszyEpRN04InluDau5Q")
        .build()
        .toURL()
        .toURI();

    HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
    ResponseEntity<MyDirectionsResult> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, MyDirectionsResult.class);
    System.out.println(exchange.getBody());
    MyDirectionsResult derectionResult = exchange.getBody();
//    DirectionsLeg directionsLeg = derectionResult.routes[0].legs[0];
//
//    for (DirectionsStep directionsStep : directionsLeg.steps) {
//
//      locations.add(String.valueOf(directionsStep.endLocation.lat) + "," + String
//          .valueOf(directionsStep.endLocation.lng));
//
//      locations.add(String.valueOf(directionsStep.startLocation.lat) + "," + String
//          .valueOf(directionsStep.startLocation.lng));
//    }
//
//    locations.add(endLat + "," + endLng);
//
//    locationsIterator = locations.iterator();

    return locations.get(0);
  }

  public String getNextCoordinates()
  {
    while (locationsIterator.hasNext())
    {
      return locationsIterator.next();
    }
    return "No data";
  }

  private static HttpComponentsClientHttpRequestFactory useApacheHttpClientWithSelfSignedSupport() {
    CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
    HttpComponentsClientHttpRequestFactory useApacheHttpClient = new HttpComponentsClientHttpRequestFactory();
    useApacheHttpClient.setHttpClient(httpClient);
    return useApacheHttpClient;
  }

  public static RestTemplate initRestTemplateForPdfAsByteArrayAndSelfSignedHttps() {
    RestTemplate restTemplate = new RestTemplate(useApacheHttpClientWithSelfSignedSupport());
    restTemplate.getMessageConverters().add(generateByteArrayHttpMessageConverter());
    return restTemplate;
  }

  private static ByteArrayHttpMessageConverter generateByteArrayHttpMessageConverter() {
    ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

    List<MediaType> supportedApplicationTypes = new ArrayList<MediaType>();
    supportedApplicationTypes.add(new MediaType("application","json"));
    byteArrayHttpMessageConverter.setSupportedMediaTypes(supportedApplicationTypes);
    return byteArrayHttpMessageConverter;
  }

}
