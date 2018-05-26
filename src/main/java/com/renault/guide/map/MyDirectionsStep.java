package com.renault.guide.map;

import com.google.maps.model.Distance;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import com.google.maps.model.TransitDetails;
import com.google.maps.model.TravelMode;
import java.io.Serializable;

public class MyDirectionsStep implements Serializable {
  private static final long serialVersionUID = 1L;
  public String htmlInstructions;
  public Distance distance;
  /** @deprecated */
  @Deprecated
  public String maneuver;
  public Duration duration;
  public LatLng start_location;
  public LatLng end_location;
  public MyDirectionsStep[] steps;
  public TravelMode travelMode;
  public TransitDetails transitDetails;

  public MyDirectionsStep() {
  }
}
