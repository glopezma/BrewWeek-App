// Name:  Gabriel Lopez
// App:   Brew Week Android App!
// Email: galopezmatthews@gmail.com
// Date:  June 4, 2017

PImage img;
boolean load = true;
// int headerSize = 150; //Space to make top piece fit
int companySize = 60; //size of company list entities
int subSize = 40;     //size of beers list entities
int beersTried = 0;
int totalBeers = 0;

Business b;


void testing(){
  //testing
  // text(mouseY, width/2, height/2);
  // text(pos.y, width/2, height/2 +40);
}

void loading(){
  //Load everthing in for the app from a Json fill
  //https://processing.org/reference/loadJSONArray_.html

  load = false;
}

//should have a giant for loop that sets up everything.
void setup() {
  // fullScreen();
  img = loadImage("../image_assets/wood1.jpg");
  size(600,600);
  img.resize(width, height);

  //initialize everything
  b = new Business("Jackie-O\'s Beer CO.", 0, 0);

  //load beers from list
  b.addBeer("Razz Wheat", 1, 0, false);

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
    b.show();

    testing();
  }
}

void mouseClicked(){
  b.toggle();
}
