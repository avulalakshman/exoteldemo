package com.ccube.pod.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccube.pod.domain.CallDetails;
import com.ccube.pod.domain.Receiver;
import com.ccube.pod.domain.User;
import com.ccube.pod.service.ReceiverService;
import com.ccube.pod.service.UserService;

@Component
@Path("/exotel")
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ExotelController {
	@Autowired
	private ReceiverService receiverService;

	@Autowired
	private UserService userService;

	private Map<String,CallDetails> callDetailsMap = new HashMap<>();

	private Map<String, CallDetails> currentCalls = new HashMap<>();

	@Path("/getallreceivers")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Receiver> getAllReceivers() {
		List<Receiver> receiversList = receiverService.listAllReceivers();
		System.out.println("Receivers List :"+receiversList);
		return receiversList;
	}

	@Path("/getuseridbyname")
	@GET
	public long getUserIdByName(@QueryParam("name") String name) {
		System.out.println("User name :"+name);
		long uid = userService.getUserIdByName(name);
		System.out.println("User id return by the name is  :"+uid);
		return uid;
	}

	@Path("/callnow")
	@GET
	public Response callNow(@QueryParam("rid") long rid, @QueryParam("uid") long uid) {
		Receiver receiver = receiverService.getReceiver(rid);
		User user = userService.getUser(uid);

		if (receiver != null && user != null) {
			CallDetails obj = new CallDetails();
			obj.setReceiver(receiver);
			obj.setUser(user);
			obj.setStatus("CALLING");
			System.out.println(user.getFullName()+" is try to call :"+receiver.getFullName());
			callDetailsMap.put(user.getMobile(),obj);
			System.out.println("In queue  - >"+callDetailsMap);
			return Response.ok().build();
		}

		return Response.status(Status.NO_CONTENT).build();
	}

	@Path("/verifynumber")
	@GET
	public Response verifyNumber(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String from = queryParams.getFirst("From");

		CallDetails callDetails = callDetailsMap.get(from);
		if (callDetails != null) {
			String callSid = queryParams.getFirst("CallSid");
			callDetails.setStatus("VERIFIED");
			callDetailsMap.remove(from);
			currentCalls.put(callSid, callDetails);
		}
		System.out.println("Current calls :"+currentCalls);
		System.out.println("Query Params from Verify number API :{" + queryParams + "}");
		return Response.status(200).build();
	}

	@Path("/getdailtonumber")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDailToNumber(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String callSid = queryParams.getFirst("CallSid");
		CallDetails callDetails = currentCalls.get(callSid);
		callDetails.setStatus("INPROGRESS");
		String passThru = queryParams.getFirst("passThru");
		if(passThru!=null){
			System.out.println("PassThru is called");
		}
		System.out.println("Call Sid :"+callSid+" and Call Details :"+callDetails);
		return callDetails.getReceiver().getMobile();
	}

	@Path("/oncallcompleted")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response onCallCompleted(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String callSid = queryParams.getFirst("CallSid");
		CallDetails callDetails = currentCalls.get(callSid);
		String timeSec = queryParams.getFirst("Duration");
		currentCalls.remove(callSid);
		
		System.out.println(callDetails.getUser().getMobile() + " made call : " + callDetails.getReceiver().getMobile()
				+ " Time in seconds " + timeSec);
		System.out.println("Current calls :"+currentCalls);
		return Response.status(200).build();
	}

	@Path("/onnoanswer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response onNoAnswer(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		System.out.println("Query Params from onCallCompleted API:{" + queryParams + "}");
		return Response.status(200).build();
	}

	@Path("/allqueuelist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,CallDetails> allQuequList() {
			return callDetailsMap;
	}

	@Path("/allcurrentcalls")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,CallDetails> allCurrentCalls() {
			return currentCalls;
	}

	public ReceiverService getReceiverService() {
		return receiverService;
	}

	public void setReceiverService(ReceiverService receiverService) {
		this.receiverService = receiverService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
