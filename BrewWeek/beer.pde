class Beer{
  PVector pos;
  String name;
  boolean tasted;
  float len;
  float h;
  int prevBusinesses;
  int prevBeers;

  Beer(String beerName, int num, boolean t){
    //should eventually change this to pput in correct spot
    pos = new PVector(width/2, height/2);
    name = beerName;
    tasted = t;
    len = width;
    h = 40;
  }

  void crossOff(){

  }

  void show(){
    //Make an area to show the name of beer and to click
    //should be white
    rectMode(CENTER);
    noStroke();
    // fill(66, 229, 244);
    fill(255);
    rect(pos.x, pos.y, len, h);

    //Make text black
    fill(0);
    textAlign(CENTER);
    text(name, pos.x, pos.y+2);
  }
}
