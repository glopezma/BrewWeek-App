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
float wheelCount = 0;

JSONObject json;
ArrayList<Business> b;

//should have a giant for loop that sets up everything.
public void setup() {
  // fullScreen();
  img = loadImage("../image_assets/wood1.jpg");
  
  img.resize(width, height);

  b = new ArrayList<Business>();
  load_json();
  //for(int i=0; i<b.size(); i++){
  // print(b.get(i).name);
  //}
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
    testing();
    for(int i = 0; i<b.size(); i++){
      b.get(i).show();
      b.get(i).move(wheelCount);
    }
    wheelCount = 0;
  }
}

public void mouseClicked(){
  edit_display();
  save_json();
}

public void mouseWheel(MouseEvent event){
  wheelCount = event.getCount()*50;
}
class Business{
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
  Business(String coName, int prevH, int prevS){
    pos = new PVector(width/2, /*headerSize + */companySize*prevH + subSize*prevS + companySize/2);

    prevBusinesses = prevH; //Allows it to know where it should go
    prevBeers = prevS;

    name = coName;
    display = true;

    beers = new ArrayList<Beer>();
  }

  public void addBeer(String beerName, int prevH, int prevS, boolean t){
    beers.add(new Beer(beerName, prevH, prevS, t));
  }

  // This function should hide or display the beers listed underneath it
  // when the header of the beers (the company) is clicked.
  public boolean toggle(){
    if(display){
      //only need the y because there aren't any borders, so can't click display screen.
      if(pos.y - mouseY > 0 && pos.y - mouseY < companySize/2 || mouseY - pos.y > 0 && mouseY - pos.y < companySize/2){
        display=false; //everything vanishes
        return true;
      }
    }
    else{
      if(pos.y - mouseY > 0 && pos.y - mouseY < companySize/2 || mouseY - pos.y > 0 && mouseY - pos.y < companySize/2){
        display=true;  //everythign appears
        return true;
      }
    }
    return false;
  }

  public void show(){
    stroke(0);
    strokeWeight(1);
    fill(255, 255, 255);
    rectMode(CENTER);
    rect(pos.x, pos.y, width, companySize);
    fill(0);
    text(name, pos.x, pos.y+companySize/4);

    if(display){
      for(Beer beer : beers){
        beer.show();
      }
    }
  }

  public void repos(int y){
    pos.y += subSize*y;
  }

  public void move(float y){
    pos.y += y;
    for(int i = 0; i<beers.size(); i++){
      beers.get(i).move(y);
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
    rectMode(CENTER);

    fill(255, 255, 255, 90);
    rect(pos.x, pos.y, width, subSize);

    //Make text black
    fill(0);
    textAlign(CENTER);
    text(name, pos.x, pos.y+subSize/4);
  }

  public void repos(int y){
    pos.y += subSize*y;
  }

  public void move(float y){
    pos.y += y;
  }
}
String errorMessage = "";

public void load_json(){
  json = loadJSONObject("data.json");
  JSONArray values = json.getJSONArray("company");

  String currentBusiness = "";

  for(int i = 0; i<values.size(); i++){
    JSONObject newBeer = values.getJSONObject(i);
    String beerName = newBeer.getString("beer");
    String businessName = newBeer.getString("name");
    if(businessName.equals(currentBusiness)){
      b.get(numHeaders-1).addBeer(beerName, numHeaders, numSubs, false);
      numSubs++;
    }
    else{
      b.add(new Business(businessName, numHeaders, numSubs));
      numHeaders++;

      b.get(numHeaders-1).addBeer(beerName, numHeaders, numSubs, false);
      numSubs++;

      currentBusiness = businessName;
    }
  }
}

public void save_json(){
  //save everything to a json file
}

public void edit_display(){
  int indent;

  for(int i = 0; i<b.size(); i++){
    if(b.get(i).toggle()){    //if clicked correct spot
      indent = b.get(i).beers.size(); //grab how much space should be displaced


        for(int j = i+1; j<b.size(); j++){ //for all entities after the collapsed entity


          if(!b.get(i).display){ //if clicked to show, everythng should move down, else everythign should move up to tke it's place. 
            b.get(j).repos(-indent);
            for(int k = 0; k<b.get(j).beers.size(); k++){
              b.get(j).beers.get(k).repos(-indent);
            }
          }
          else{
            b.get(j).repos(indent);
            for(int k=0; k<b.get(j).beers.size(); k++){
              b.get(j).beers.get(k).repos(indent);
            }
          }


        }



      }
    }
  }


public void testing(){
  //testing
  // text(mouseY, width/2, height/2);
  // text(pos.y, width/2, height/2 +40);
}

public void loading(){
  //Load everthing in for the app from a Json fill
  load = false;
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
