class Beer {
  PVector pos;
  String name;
  boolean tasted;
  int prevBusinesses;
  int prevBeers;

  Beer(String beerName, int prevH, int prevS, boolean t) {
    //should eventually change this to pput in correct spot
    pos = new PVector(width/2, /*headerSize + */companySize*prevH + subSize*prevS + subSize/2 + 1);
    name = beerName;
    tasted = t;
    prevBusinesses = prevH;
    prevBeers = prevS;
  }

  void crossOff() {
  }

  void show() {
    rectMode(CENTER);

    fill(255, 255, 255, 90);
    rect(pos.x, pos.y, width, subSize);

    //Make text black
    fill(0);
    textAlign(CENTER);
    text(name, pos.x, pos.y+subSize/4);
  }

  void repos(int y) {
    pos.y += subSize*y;
  }

  void move(float y) {
    pos.y += y;
  }
}