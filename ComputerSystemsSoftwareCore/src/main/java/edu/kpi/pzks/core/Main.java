package edu.kpi.pzks.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

  public static final String GREETING = "Hello";
  private static Log LOGGER = LogFactory.getLog(Main.class);

  public static void main(String[] args) {
    System.out.println(GREETING);
    LOGGER.info("Main finished");
  }
}
