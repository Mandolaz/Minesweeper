/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template reader, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeper.storage;
//Libraries
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//Class
public class Highscore {
    //Variables
    static JSONParser  PARSER;
    static JSONArray   HIGHSCORE_JSONARRAY;
    static JSONObject  highscore_JSONObject_Beginner, highscore_JSONObject_Intermediate, highscore_JSONObject_Expert;
    //Constructor
    public Highscore(){
        create_JSONArray();
        create_saveFile();
    }
    
    //Methods
    private void create_JSONArray(){
        
        //Create JSONObject and fields
        highscore_JSONObject_Beginner = new JSONObject();
        highscore_JSONObject_Beginner.put("Difficulty", "Beginner");
        highscore_JSONObject_Beginner.put("Time", null);
        highscore_JSONObject_Beginner.put("Name", null);
        
        highscore_JSONObject_Intermediate = new JSONObject();
        highscore_JSONObject_Intermediate.put("Difficulty", "Intermidiate");
        highscore_JSONObject_Intermediate.put("Time", null);
        highscore_JSONObject_Intermediate.put("Name", null);
        
        highscore_JSONObject_Expert = new JSONObject();
        highscore_JSONObject_Expert.put("Difficulty", "Expert");
        highscore_JSONObject_Expert.put("Time", null);
        highscore_JSONObject_Expert.put("Name", null);
        
        //Create JSONArray and add object in it
        HIGHSCORE_JSONARRAY = new JSONArray();
        HIGHSCORE_JSONARRAY.add(highscore_JSONObject_Beginner);
        HIGHSCORE_JSONARRAY.add(highscore_JSONObject_Intermediate);
        HIGHSCORE_JSONARRAY.add(highscore_JSONObject_Expert);
    }
    
    private void create_saveFile(){
        try (FileWriter file = new FileWriter("highscore.json")) {
            file.write(HIGHSCORE_JSONARRAY.toJSONString()); 
            file.flush();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
    public static void saveFile(String difficulty, int time, String name){
        PARSER = new JSONParser();
        try (FileReader reader = new FileReader("highscore.json")){
            //Get raw JSONArray...
            Object object = PARSER.parse(reader);
            //...and cast it at HIGHSCORE_JSONARRAY...
            
            HIGHSCORE_JSONARRAY = (JSONArray) object;
            switch(difficulty){
                case "Beginner":
                    highscore_JSONObject_Beginner = (JSONObject) HIGHSCORE_JSONARRAY.get(0);
                    highscore_JSONObject_Beginner.put("Time", time);
                    highscore_JSONObject_Beginner.put("Name", name);
                    break;
                    
                case "Intermediate":
                    highscore_JSONObject_Intermediate = (JSONObject) HIGHSCORE_JSONARRAY.get(1);
                    highscore_JSONObject_Intermediate.put("Time", time);
                    highscore_JSONObject_Intermediate.put("Name", name);
                    break;
                    
                case "Expert":
                    highscore_JSONObject_Expert = (JSONObject) HIGHSCORE_JSONARRAY.get(2);
                    highscore_JSONObject_Expert.put("Time", time);
                    highscore_JSONObject_Expert.put("Name", name);
                    break;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try (FileWriter file = new FileWriter("highscore.json")) {
            file.write(HIGHSCORE_JSONARRAY.toJSONString()); 
            file.flush();
            
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean isHighscore(String difficulty, int time){
        PARSER = new JSONParser();
        try (FileReader reader = new FileReader("highscore.json")){
            //Get raw JSONArray...
            Object object = PARSER.parse(reader);
            //...and cast it at HIGHSCORE_JSONARRAY...
            HIGHSCORE_JSONARRAY = (JSONArray) object;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        switch(difficulty){ //Look at specific object based on difficulty
                case "Beginner":
                    //Get Beginner Highscore
                    JSONObject beginner = (JSONObject) HIGHSCORE_JSONARRAY.get(0);
                    if(beginner.get("Time") == null)
                        return true;
                    else
                        if(time < ((Long)beginner.get("Time")).intValue())
                            return true;
                    break;
                case "Intermediate":
                    //Get Intermediate Highscore
                    JSONObject intermediate = (JSONObject) HIGHSCORE_JSONARRAY.get(1);
                    if(intermediate.get("Time") == null)
                        return true;
                    else
                        if(time < ((Long)intermediate.get("Time")).intValue())
                            return true;
                    break;
                    
                case "Expert":
                    //Get Expert Highscore
                    JSONObject expert = (JSONObject) HIGHSCORE_JSONARRAY.get(2);
                    if(expert.get("Time") == null)
                        return true;
                    else
                        if(time < ((Long)expert.get("Time")).intValue())
                            return true;
                    break;
        }
        
        //If all statements are false...
        return false;
    }
    
    public static List<JSONObject> readHighscoreJSON(){
        PARSER = new JSONParser();
        try (FileReader reader = new FileReader("highscore.json")){
            //Get raw JSONArray...
            Object object = PARSER.parse(reader);
            //...and cast it at HIGHSCORE_JSONARRAY...
            HIGHSCORE_JSONARRAY = (JSONArray) object;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JSONObject beginner =       (JSONObject) HIGHSCORE_JSONARRAY.get(0);
        JSONObject intermediate =   (JSONObject) HIGHSCORE_JSONARRAY.get(1);
        JSONObject expert =         (JSONObject) HIGHSCORE_JSONARRAY.get(2);
        
        List<JSONObject> list = new ArrayList<>();
        list.add(beginner);
        list.add(intermediate);
        list.add(expert);
        
        return list;
    }
    
}
