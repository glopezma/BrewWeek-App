import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class BrewWeek extends PApplet {

// Name:  Gabriel Lopez
// App:   Brew Week Android App!
// Email: galopezmatthews@gmail.com
// Date:  June 4, 2017

PImage img;
boolean load = true;
int beersTried = 0;
int totalBeers = 0;
int companyTotal;
int beersTotal;

Business b;


public void loading(){
  //Load everthing in for the app from a Json fill
  //https://processing.org/reference/loadJSONArray_.html

  load = false;
}

//should have a giant for loop that sets up everything.
public void setup() {
  // fullScreen();
  img = loadImage("../image_assets/wood1.jpg");
  
  img.resize(width, height);

  //initialize everything
  b = new Business("Jackie-O\'s Beer CO.");

  //load beers from list
  b.addBeer("Razz Wheat", 0, false);

}

public void draw() {
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
  }

}
class Business{
  PVector pos;
  String name;
  ArrayList<Beer> beers;
  int h;
  int prevBusinesses;
  int prevBeers;

  
  //add to list beers.add()
  //get from list beers.get(location);
  //remove from list beers.remove(location);
  //for(Beer beer : beers){beers.display();}
  Business(String coName){
    h = 60;
    pos = new PVector(width/2, h/2);
    name = coName;
    beers = new ArrayList<Beer>();
  }

  public void addBeer(String beerName, int num, boolean t){
    beers.add(new Beer(beerName, num, t));
  }

  public void show(){
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

  public void crossOff(){

  }

  public void show(){
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
  public void settings() {  size(600,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "BrewWeek" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
