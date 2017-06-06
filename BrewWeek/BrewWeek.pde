// Name:  Gabriel Lopez
// App:   Brew Week Android App!
// Email: galopezmatthews@gmail.com
// Date:  June 4, 2017

// int beersTried = 0;
// int totalBusinesses = 1;
// int totalBeers = 5;
PImage img;
boolean load = true;
// int headerSize = 150; //Space to make top piece fit
int companySize = 60; //size of company list entities
int subSize = 40;     //size of beers list entities

int numHeaders = 0;   //number of headers in program (aka companies)
int numSubs = 0;      //number of sublist items in program (aka beers)

JSONObject json;


ArrayList<Business> b;


void testing(){
  //testing
  // text(mouseY, width/2, height/2);
  // text(pos.y, width/2, height/2 +40);
}

void loading(){
  //Load everthing in for the app from a Json fill
  load = false;
}

//should have a giant for loop that sets up everything.
void setup() {
  // fullScreen();
  img = loadImage("../image_assets/wood1.jpg");
  size(600,600);
  img.resize(width, height);

  b = new ArrayList<Business>();

  json = loadJSONObject("data.json");
  JSONArray values = json.getJSONArray("company");
  String current = "";
  for(int i = 0; i<values.size(); i++){
    JSONObject newBeer = values.getJSONObject(i);
    if(newBeer.getString("name") == current){
      b.get(numHeaders).addBeer(newBeer.getString("beer"), numHeaders, numSubs, false);
      numSubs++;
    }
    else{
      b.add(new Business(newBeer.getString("name"), numHeaders, numSubs));
      numHeaders++;

      b.get(numHeaders-1).addBeer(newBeer.getString("beer"), numHeaders, numSubs, false);
      numSubs++;

      current = newBeer.getString("name");
    }
  }
}

void draw() {
  if(load){
    fill(255);
    background(51);
    textAlign(CENTER);
    textSize(24);
    text("Loading", width/2, height/2);
    loading(); // move to top if want to be faster
  }
  else{
    background(img);
    testing();
    for(int i = 0; i<b.size(); i++){
      b.get(i).show();
    }
  }
}

void mouseClicked(){
  for(int i = 0; i<b.size(); i++){
    b.get(i).toggle();
  }
}