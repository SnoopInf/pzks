package edu.kpi.pzks.server;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.kpi.pzks.core.Main;

@Path("/ajax")
public final class AjaxController {

  private static final Log LOGGER = LogFactory.getLog(AjaxController.class);

  @POST
  @Path("/greet")
  public void greet(String data) {
    LOGGER.info("Got " + data);
  }

  @GET
  @Path("/greeting")
  @Produces(MediaType.APPLICATION_JSON)
  public Object greeting() {
    return Main.GREETING;
  }
}
