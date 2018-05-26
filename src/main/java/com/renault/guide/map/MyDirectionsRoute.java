package com.renault.guide.map;

import com.google.maps.model.Bounds;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.Fare;
import java.io.Serializable;

public class MyDirectionsRoute implements Serializable {
  private static final long serialVersionUID = 1L;
  public String summary;
  public DirectionsLeg[] legs;
  public int[] waypointOrder;
  public Bounds bounds;
  public String copyrights;
  public Fare fare;
  public String[] warnings;

  public MyDirectionsRoute() {
  }
}