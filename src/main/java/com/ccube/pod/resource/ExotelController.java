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
	private static List<String> numbers=null;
	static{
		numbers=new ArrayList<>();
		numbers.add("08951586661");
		numbers.add("09945529337");
		numbers.add("09880366899");
	}
	
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
		String mobileNumber=getMobileNumber();
		MultivaluedMap<String, String> queryParams=uriInfo.getQueryParameters();
		System.out.println("Query Params from Get Dail to Number API:{"+queryParams+"}");
		return mobileNumber;
	}
	
	
	
	private String getMobileNumber(){
			if(callCount<numbers.size()){
				return numbers.get(callCount++);
			}
			throw new IllegalArgumentException("Max number of connections reached");
	}
		
}
