import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    
    public ArrayList<QuakeEntry>  filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where,
    String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        if(where.equals("start")){
            for (QuakeEntry qe : quakeData) {
               
                if (  qe.getInfo().startsWith(phrase))
                    answer.add(qe);
            }
        }
        else if(where.equals("end"))
            for (QuakeEntry qe : quakeData) {
                   
                    if (  qe.getInfo().endsWith(phrase))
                        answer.add(qe);
                }
        else{
             for (QuakeEntry qe : quakeData) {
                   
                    if (  qe.getInfo().contains(phrase))
                        answer.add(qe);
                }}
        
        return answer;
    }
    
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
       
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
         answer = filterByPhrase(list,"start", "Explosion");
        int counter=0;
        for(QuakeEntry q:answer){
               
                     System.out.println(q.toString());
                     counter++;
        }
        System.out.println("Found "+counter+" quakes that match that criteria");
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Find quakes with depth between -8000.0 and -5000.0");
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        answer = filterByDepth(list, -8000.0 , -5000.0);
        int counter=0;
        for(QuakeEntry q:answer){
               
                     System.out.println(q.toString());
                     counter++;
        }
        System.out.println("Found "+counter+" quakes that match that criteria");
        

    }
    
    
     public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth()>minDepth && qe.getDepth()<maxDepth)
                answer.add(qe);
            }
        
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes(double magMin) {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        int counter=0;
        for(QuakeEntry q:list){
                if(q.getMagnitude() >= magMin){
                     System.out.println(q.toString());
                     counter++;}
        }
        System.out.println("Found "+counter+" quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODOArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        int counter=0;
        for (int k=0; k< list.size(); k++) {
            QuakeEntry entry = list.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            if(distanceInMeters/1000<=1000){
                System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
                counter++;}
         
        }
        System.out.println("Found "+counter+" quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
