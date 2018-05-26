package com.renault.guide.map;


import com.google.maps.model.DirectionsStep;
import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import org.joda.time.DateTime;

public class MyDirectionsLeg {
  private static final long serialVersionUID = 1L;
  public MyDirectionsStep[] steps;
  public Distance distance;
  public Duration duration;
  public Duration durationInTraffic;
  public DateTime arrivalTime;
  public DateTime departureTime;
  public LatLng startLocation;
  public LatLng endLocation;
  public String startAddress;
  public String endAddress;

  public MyDirectionsLeg() {
  }
}
