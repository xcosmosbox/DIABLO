package com.codequest23;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameUtils {
  public static Map<String, JsonObject>  preMsgObj = null;
  private static double maxBulletVelocity = 450.0;

  // Parse the position data from a JsonArray and convert it to a 2D array of doubles
  public static double[][] parsePosition(JsonArray positionArray) {
    double[][] position = new double[positionArray.size()][2];
    for (int i = 0; i < positionArray.size(); i++) {
      JsonArray singlePosition = positionArray.get(i).getAsJsonArray();
      position[i][0] = singlePosition.get(0).getAsDouble(); // Extract the X-coordinate
      position[i][1] = singlePosition.get(1).getAsDouble(); // Extract the Y-coordinate
    }
    return position;
  }

  public static List<JsonObject> getAreaAllBullet(String tankId, Map<String, JsonObject> mapState){
    JsonObject tank = mapState.get(tankId);
    JsonArray tankPosition = tank.get("position").getAsJsonArray();
    double xPosition = tankPosition.get(0).getAsDouble();
    double yPosition = tankPosition.get(1).getAsDouble();
    double leftSide = xPosition - 65;
    double rightSide = xPosition + 65;
    double top = yPosition + 65;
    double bottom = yPosition - 65;

    List<JsonObject> result = new ArrayList<>();
    for (JsonObject jsonObject : mapState.values()) {
      int type = jsonObject.get("type").getAsInt();
      if (type  == 2){
        double bulletXPostion = jsonObject.get("position").getAsJsonArray().get(0).getAsDouble();
        double bulletYPostion = jsonObject.get("position").getAsJsonArray().get(1).getAsDouble();
        if (bulletXPostion <= rightSide && bulletXPostion >= leftSide && bulletYPostion <= top && bulletYPostion >= bottom){
          result.add(jsonObject);
        }
      }
    }

    return result;
  }

  private static double getDistance(JsonObject vector1, JsonObject vector2){
    JsonArray position1 = vector1.get("position").getAsJsonArray();
    JsonArray position2 = vector2.get("position").getAsJsonArray();
    return Math.sqrt( ( Math.pow(Math.abs(position1.get(0).getAsDouble()-position2.get(0).getAsDouble()),2) ) +
            ( Math.pow(Math.abs(position1.get(1).getAsDouble()-position2.get(1).getAsDouble()),2) ) );
  }


//  public static boolean willCollisionWithBullet(JsonObject tank, JsonObject bullet){
//
//  }
}
