package com.guptaji.resource;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.guptaji.entity.BankEntity;
import com.guptaji.service.BankService;

@Path("jwt-demo")
public class JwtConsumerResource {

  public static final Logger LOG = Logger.getLogger(JwtConsumerResource.class);

  @Inject BankService bankService;

  // this API will be available to everyone
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @PermitAll
  public Response getBankDetails() {

    LOG.info("API Resource::getBankDetails() Hit");
    List<BankEntity> bankEntities = bankService.sendAllBankDetails();
    LOG.info("returning from API Resource::getBankDetails() with some response");
    LOG.debug(
        "returning from API Resource::getBankDetails() with some response :: " + bankEntities);
    return Response.ok(bankEntities).build();
  }

  // now let's create one API which only admin can access delete API
  // this syntax is for multiple roles we can also write @RolesAllowed("Admin")
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @RolesAllowed({"Admin", "User"})
  public Response deleteBankDetails(BankEntity bankEntity) {
    List<BankEntity> bankEntities = bankService.deleteBankDetailsFromServer(bankEntity);
    return Response.ok(bankEntities).build();
  }
}
