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
// int headerSize = 150; //Space to make top piece fit
int companySize = 60; //size of company list entities
int subSize = 40;     //size of beers list entities
int beersTried = 0;
int totalBeers = 0;

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
  b = new Business("Jackie-O\'s Beer CO.", 0, 0);

  //load beers from list
  b.addBeer("Razz Wheat", 1, 0, false);

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

public void mouseClicked(){
  b.toggle();
}
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

  public void addBeer(String beerName, int prevH, int prevS, boolean t){
    beers.add(new Beer(beerName, prevH, prevS, t));
  }

  // This function should hide or display the beers listed underneath it
  // when the header of the beers (the company) is clicked.
  public void toggle(){
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

  public void show(){
    stroke(0);
    strokeWeight(1);
    fill(255, 255, 255, 90);
    rectMode(CENTER);
    rect(pos.x, pos.y, width, companySize);

    //add click to make this show
    fill(0);
    text(name, pos.x, pos.y+companySize/4);

    //testing
    // text(mouseY, width/2, height/2);
    // text(pos.y, width/2, height/2 +40);

    if(!off){
      for(Beer beer : beers){
        beer.show();
      }
    }
  }
}
class Beer{
  PVector pos;
  String name;
  boolean tasted;
  int prevBusinesses;
  int prevBeers;

  Beer(String beerName, int prevH, int prevS, boolean t){
    //should eventually change this to pput in correct spot
    pos = new PVector(width/2, /*headerSize + */companySize*prevH + subSize*prevS + subSize/2 + 1);
    name = beerName;
    tasted = t;
    prevBusinesses = prevH;
    prevBeers = prevS;
  }

  public void crossOff(){

  }

  public void show(){
    //Make an area to show the name of beer and to click
    //should be white
    rectMode(CENTER);
    // noStroke();
    // fill(66, 229, 244);
    fill(255, 255, 255, 90);
    rect(pos.x, pos.y, width, subSize);

    //Make text black
    fill(0);
    textAlign(CENTER);
    text(name, pos.x, pos.y+subSize/4);
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
