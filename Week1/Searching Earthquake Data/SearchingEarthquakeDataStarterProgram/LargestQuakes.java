import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    
    public void findLargestQuakes(){
         EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
       
        
        int counter=0;
        for(QuakeEntry q:list){
               
                     //System.out.println(q.toString());
                     counter++;
        }
        //System.out.println("Found "+counter+" quakes that match that criteria");
       // System.out.println("location " +indexOfLargest(list));
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       answer=getLargest(list, 5);
       for(QuakeEntry q:answer)
            System.out.println(q.toString());
    }

    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double max = 0;
        int index=0;
        for(QuakeEntry q:data){
                    if(q.getMagnitude()>max){
                        max=q.getMagnitude();
                        index=data.indexOf(q);
                    }
                    
        }
        
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy= new ArrayList<QuakeEntry>(quakeData);
        
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(int i=0;i<howMany;i++){
            answer.add(copy.get(indexOfLargest(copy)));
            copy.remove(indexOfLargest(copy));
            
        }
        return answer;
        
    }
}
