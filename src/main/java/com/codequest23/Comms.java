package com.codequest23;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class Comms {
  public static final String END_SIGNAL = "END";
  public static final String END_INIT_SIGNAL = "END_INIT";
  private static final Scanner scanner = new Scanner(System.in);

  private static final Gson gson = new Gson();

  // Send a message by printing the JSON representation of the message to the console
  public static void postMessage(JsonObject message) {
    String jsonMessage = gson.toJson(message);
    System.out.println(jsonMessage);
  }

  // Read a message from the console input and parse it as a JsonElement
  public static JsonElement readMessage() {
    String input = scanner.nextLine();
    return JsonParser.parseString(input);
  }
}
