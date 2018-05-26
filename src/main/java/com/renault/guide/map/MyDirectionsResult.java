package com.renault.guide.map;

import com.google.maps.model.GeocodedWaypoint;
import java.io.Serializable;

public class MyDirectionsResult implements Serializable {

  private static final long serialVersionUID = 1L;
  public GeocodedWaypoint[] geocodedWaypoints;
  public MyDirectionsRoute[] routes;
  public String status;


  public MyDirectionsResult() {
  }
}