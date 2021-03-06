package nl.iprofs.geert.cv;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yammer.dropwizard.auth.Auth;
import com.yammer.metrics.annotation.Timed;

import nl.iprofs.geert.cv.model.CV;
import nl.iprofs.geert.cv.service.CVBackend;
import nl.iprofs.geert.cv.service.CVPrincipal;

@Path("/cvs")
@Produces(MediaType.APPLICATION_JSON)
public class CVResource {

  private CVBackend backend;

  @GET
  @Timed
  public List<CV> getAllCVs() {
    return backend.getCVs();
  }

  @GET
  @Path("/{name}.json")
  @Timed
  public CV getCV(@Auth(required = false) CVPrincipal user, @PathParam("name") String name) {

    if (name != null) {
      return backend.getCV(name);
    } else {

      return null;
    }
  }

  public void setBackend(CVBackend backend) {
    this.backend = backend;
  }
}
