String errorMessage = "";

void load_json() {
  json = loadJSONObject("data.json");
  JSONArray values = json.getJSONArray("company");

  String currentBusiness = "";

  for (int i = 0; i<values.size(); i++) {
    JSONObject newBeer = values.getJSONObject(i);
    String beerName = newBeer.getString("beer");
    String businessName = newBeer.getString("name");
    if (businessName.equals(currentBusiness)) {
      b.get(numHeaders-1).addBeer(beerName, numHeaders, numSubs, false);
      numSubs++;
    }
    else {
      b.add(new Business(businessName, numHeaders, numSubs));
      numHeaders++;

      b.get(numHeaders-1).addBeer(beerName, numHeaders, numSubs, false);
      numSubs++;

      currentBusiness = businessName;
    }
  }
}

void save_json() {
  //save everything to a json file
}

void edit_display() {
  int indent;

  for (int i = 0; i<b.size(); i++) {
    if (b.get(i).toggle()) {    //if clicked correct spot
      indent = b.get(i).beers.size(); //grab how much space should be displaced
      for (int j = i+1; j<b.size(); j++) { //for all entities after the collapsed entity
        if (!b.get(i).display) { //if clicked to show, everythng should move down, else everythign should move up to tke it's place.
          b.get(j).repos(-indent);
          for (int k = 0; k<b.get(j).beers.size(); k++) {
            b.get(j).beers.get(k).repos(-indent);
          }
        }
        else {
          b.get(j).repos(indent);
          for (int k=0; k<b.get(j).beers.size(); k++) {
            b.get(j).beers.get(k).repos(indent);
          }
        }
      }
    }
  }
}

void testing() {
  //testing
  // text(mouseY, width/2, height/2);
  // text(pos.y, width/2, height/2 +40);
}

void loading() {
  //Load everthing in for the app from a Json fill
  load = false;
}
