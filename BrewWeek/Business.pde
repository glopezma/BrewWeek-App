class Business{
  PVector pos;
  int prevBusinesses;
  int prevBeers;
  String name;
  boolean off;
  ArrayList<Beer> beers;


  //add to list beers.add()
  //get from list beers.get(location);
  //remove from list beers.remove(location);
  //for(Beer beer : beers){beers.display();}
  Business(String coName, int prevH, int prevS){
    pos = new PVector(width/2, /*headerSize + */companySize*prevH + subSize*prevS + companySize/2);

    prevBusinesses = prevH; //Allows it to know where it should go
    prevBeers = prevS;

    name = coName;
    off = true;

    beers = new ArrayList<Beer>();
  }

  void addBeer(String beerName, int prevH, int prevS, boolean t){
    beers.add(new Beer(beerName, prevH, prevS, t));
  }

  // This function should hide or display the beers listed underneath it
  // when the header of the beers (the company) is clicked.
  void toggle(){
    if(!off){
      //only need the y because there aren't any borders, so can't click off screen.
      if(pos.y - mouseY > 0 && pos.y - mouseY < companySize/2 || mouseY - pos.y > 0 && mouseY - pos.y < companySize/2){
        off=true;
      }
    }
    else{
      if(pos.y - mouseY > 0 && pos.y - mouseY < companySize/2 || mouseY - pos.y > 0 && mouseY - pos.y < companySize/2){
        off=false;
      }
    }
  }

  void show(){
    stroke(0);
    strokeWeight(1);
    fill(255, 255, 255, 90);
    rectMode(CENTER);
    rect(pos.x, pos.y, width, companySize);

    //add click to make this show
    fill(0);
    text(name, pos.x, pos.y+companySize/4);
    
    if(!off){
      for(Beer beer : beers){
        beer.show();
      }
    }
  }
}
