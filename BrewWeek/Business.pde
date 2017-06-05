class Business{
  PVector pos;
  String name;
  ArrayList<Beer> beers;
  int h;
  int prevBusinesses;
  int prevBeers;
  boolean off = false;


  //add to list beers.add()
  //get from list beers.get(location);
  //remove from list beers.remove(location);
  //for(Beer beer : beers){beers.display();}
  Business(String coName){
    h = 60;
    pos = new PVector(width/2, h/2);
    name = coName;
    beers = new ArrayList<Beer>();
    //Temp here, but will become dynamic once I start puting things in
    prevBusinesses = 0;
    prevBeers = 0;
  }

  void addBeer(String beerName, int prevH, int prevS, boolean t){
    beers.add(new Beer(beerName, prevH, prevS, t));
  }

  // This function should hide or display the beers listed underneath it
  // when the header of the beers (the company) is clicked.
  void toggle(){
    if(!off){
      if(pos.x - mouseX < width && (pos.y - mouseY < h || mouseY - pos.y < 20) ){

        off=true;
      }
    }
    else{
      if(pos.x - mouseX < width && (pos.y - mouseY < h || mouseY - pos.y < 20) ){
        off=false;
      }
    }
  }

  void show(){
    noStroke();
    fill(255);
    rectMode(CENTER);
    rect(pos.x, pos.y, width, h);

    //add click to make this show
    fill(0);
    text(name, pos.x, pos.y);
    for(Beer beer : beers){
      beer.show();
    }
  }
}
