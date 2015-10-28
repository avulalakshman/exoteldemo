package com.ccube.pod.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component
@Path("/exotel")
@Consumes(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class ExotelController {
	
	
	private static int callCount=0;
	
	@Path("/initiatecall")
	@POST
	public Response initiateCall(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams=uriInfo.getQueryParameters();
		String sid=queryParams.getFirst("Sid");
		System.out.println("SID "+sid);
		return Response.status(200).build();
	}
	
	@Path("/verifynumber")
	@GET
	public Response verifyNumber(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams=uriInfo.getQueryParameters();
		System.out.println("Query Params from Verify number API :{"+queryParams+"}");
		return Response.status(200).build();
	}
	
	@Path("/getdailtonumber")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDailToNumber(@Context UriInfo uriInfo) {
		String mobileNumber="09945529337";
		MultivaluedMap<String, String> queryParams=uriInfo.getQueryParameters();
		System.out.println("Query Params from Get Dail to Number API:{"+queryParams+"}");
		return mobileNumber;
	}
	
	@Path("/oncallcompleted")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response onCallCompleted(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams=uriInfo.getQueryParameters();
		System.out.println("Query Params from onCallCompleted API:{"+queryParams+"}");
		return Response.status(200).build();
	}
	
	
	@Path("/onnoanswer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response onNoAnswer(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams=uriInfo.getQueryParameters();
		System.out.println("Query Params from onnoanswer:{"+queryParams+"}");
		return Response.status(200).build();
	}
	
		
}
