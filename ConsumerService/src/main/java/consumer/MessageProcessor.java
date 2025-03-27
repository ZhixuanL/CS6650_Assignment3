package consumer;

import org.json.JSONObject;
import redis.clients.jedis.Jedis;

public class MessageProcessor {
  public static void processMessage(String message) {
    try {
      JSONObject json = new JSONObject(message);
      int skierID = json.getInt("skierID");
      int liftID = json.getInt("liftID");
      int resortID = json.getInt("resortID");
      int dayID = json.getInt("dayID");
      int seasonID = json.getInt("seasonID");
      int time = json.getInt("time");

      JSONObject rideJson = new JSONObject();
      rideJson.put("resortID", resortID);
      rideJson.put("seasonID", seasonID);
      rideJson.put("dayID", dayID);
      rideJson.put("liftID", liftID);
      rideJson.put("time", time);

      String redisKey = "skier:" + skierID + ":day:" + dayID + ":time:" + time;

      try (Jedis jedis = new Jedis("localhost", 6379)) {
        jedis.set(redisKey, rideJson.toString());

        jedis.sadd("skierDays:" + skierID, String.valueOf(dayID));

        jedis.hincrBy("skierVertical:" + skierID, String.valueOf(dayID), liftID * 10);

        jedis.sadd("skierLifts:" + skierID + ":day:" + dayID, String.valueOf(liftID));

        jedis.sadd("resortVisitors:" + resortID + ":day:" + dayID, String.valueOf(skierID));

        System.out.println("✔ Stored in Redis → " + redisKey);
      }

    } catch (Exception e) {
      System.err.println("Error processing message: " + e.getMessage());
    }
  }
}