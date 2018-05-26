package com.renault.guide.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

  @Autowired
  CoordianateService coordianateService;

  @GetMapping("/setPath")
  public String setPath(String query) throws Exception {
    return coordianateService.setPath(query);
  }

  @GetMapping("/getNextCoordinates")
  public String getNextCoordinateInPath()
  {
    return coordianateService.getNextCoordinates();
  }

}
