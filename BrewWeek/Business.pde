class Business {
  PVector pos;
  int prevBusinesses;
  int prevBeers;
  String name;
  boolean display;
  ArrayList<Beer> beers;


  //add to list beers.add()
  //get from list beers.get(location);
  //remove from list beers.remove(location);
  //for(Beer beer : beers){beers.display();}
  Business(String coName, int prevH, int prevS) {
    pos = new PVector(width/2, /*headerSize + */companySize*prevH + subSize*prevS + companySize/2);

    prevBusinesses = prevH; //Allows it to know where it should go
    prevBeers = prevS;

    name = coName;
    display = true;

    beers = new ArrayList<Beer>();
  }

  void addBeer(String beerName, int prevH, int prevS, boolean t) {
    beers.add(new Beer(beerName, prevH, prevS, t));
  }

  // This function should hide or display the beers listed underneath it
  // when the header of the beers (the company) is clicked.
  boolean toggle() {
    if (display) {
      //only need the y because there aren't any borders, so can't click display screen.
      if (pos.y - mouseY > 0 && pos.y - mouseY < companySize/2 || mouseY - pos.y > 0 && mouseY - pos.y < companySize/2) {
        display=false; //everything vanishes
        return true;
      }
    } else {
      if (pos.y - mouseY > 0 && pos.y - mouseY < companySize/2 || mouseY - pos.y > 0 && mouseY - pos.y < companySize/2) {
        display=true;  //everythign appears
        return true;
      }
    }
    return false;
  }

  void show() {
    stroke(0);
    strokeWeight(1);
    fill(255, 255, 255);
    rectMode(CENTER);
    rect(pos.x, pos.y, width, companySize);
    fill(0);
    text(name, pos.x, pos.y+companySize/4);

    if (display) {
      for (Beer beer : beers) {
        beer.show();
      }
    }
  }

  void repos(int y) {
    pos.y += subSize*y;
  }

  void move(float y) {
    pos.y += y;
    for (int i = 0; i<beers.size(); i++) {
      beers.get(i).move(y);
    }
  }
}