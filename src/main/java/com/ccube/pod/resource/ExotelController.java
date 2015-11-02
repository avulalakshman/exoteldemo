package com.ccube.pod.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccube.pod.domain.CallDetails;
import com.ccube.pod.domain.Receiver;
import com.ccube.pod.domain.User;
import com.ccube.pod.service.ReceiverService;
import com.ccube.pod.service.UserService;

@Component
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Path("/")
public class ExotelController {
	@Autowired
	private ReceiverService receiverService;

	@Autowired
	private UserService userService;

	private Map<String, CallDetails> callDetailsMap = new HashMap<>();

	private Map<String, CallDetails> currentCalls = new HashMap<>();

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExotelController.class.getName());

	@Path("/addreceiver")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Receiver addReceiver(Receiver receiver) {
		receiver = receiverService.addReceiver(receiver);
		return receiver;
	}

	@Path("/adduser")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public User addUser(User user) {
		user = userService.addUser(user);
		return user;
	}

	@Path("/getuser")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public User getUser(@QueryParam("id") long uid) {
		User user = userService.getUser(uid);
		return user;
	}

	@Path("/getreceiver")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Receiver getReceiver(@QueryParam("rid") long rid) {
		Receiver receiver = receiverService.getReceiver(rid);
		return receiver;
	}
	

	@Path("/deletereceiver")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public void deleteReceiver(@QueryParam("rid") long rid) {
		 receiverService.deleteReceiver(rid);
	}
	
	@Path("/deleteuser")
	@POST
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public void deleteUser(@QueryParam("uid") long uid) {
		 userService.deleteReceiver(uid);
	}
	
	@Path("/getallreceivers")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Receiver> getAllReceivers() {
		List<Receiver> receiversList = receiverService.listAllReceivers();
		LOGGER.info("Receivers List :" + receiversList);
		return receiversList;
	}

	@Path("/getallusers")
	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<User> getAllUsers() {
		List<User> usersList = userService.listUser();
		LOGGER.info("Users List :" + usersList);
		return usersList;
	}

	@Path("/getuseridbyname")
	@GET
	public long getUserIdByName(@QueryParam("name") String name) {
		LOGGER.info("User name :" + name);
		long uid = userService.getUserIdByName(name);
		LOGGER.info("User id return by the name is  :" + uid);
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
			LOGGER.info(user.getFullName() + " is try to call :" + receiver.getFullName());
			callDetailsMap.put(user.getMobile(), obj);
			LOGGER.info("In queue  - >" + callDetailsMap);
			return Response.ok().build();
		}

		return Response.status(404).build();
	}

	@Path("/verifynumber")
	@GET
	public Response verifyNumber(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String from = queryParams.getFirst("From");
		LOGGER.info("Number to be verified..." + from);
		CallDetails callDetails = callDetailsMap.get(from);

		if (callDetails != null) {
			String callSid = queryParams.getFirst("CallSid");
			LOGGER.info("Call sid ..." + callSid);
			callDetails.setStatus("VERIFIED");
			callDetailsMap.remove(from);
			currentCalls.put(callSid, callDetails);
		} else {
			LOGGER.info("Call Details Not found, User not found");
			return Response.status(404).build();
		}
		LOGGER.info("Current calls :" + currentCalls);
		LOGGER.info("Query Params from Verify number API :{" + queryParams + "}");
		return Response.status(200).build();
	}

	@Path("/getdailtonumber")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getDailToNumber(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		LOGGER.info("Query params of getDialToNumber..." + queryParams);
		String callSid = queryParams.getFirst("CallSid");
		CallDetails callDetails = currentCalls.get(callSid);
		callDetails.setStatus("INPROGRESS");
		String passThru = queryParams.getFirst("passthru");
		if (passThru != null) {
			LOGGER.info("PassThru is part of params...");
		}
		LOGGER.info("Call Sid :" + callSid + " and Call Details :" + callDetails);
		// Appending mobile number with the +91
		String mobile = callDetails.getReceiver().getMobile();
		return mobile;
	}

	/*
	 * private String getMobileNumber(String mobile) {
	 * 
	 * if (mobile != null) { mobile = mobile.trim(); if (mobile.length() == 13)
	 * { return mobile; } else if (mobile.length() == 11) { mobile =
	 * mobile.substring(1, 10); return "+91" + mobile; } else if
	 * (mobile.length() == 10) { return "+91" + mobile; } } throw new
	 * IllegalArgumentException("Mobile number is not found"); }
	 */

	@Path("/oncallcompleted")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response onCallCompleted(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String callSid = queryParams.getFirst("CallSid");
		CallDetails callDetails = currentCalls.get(callSid);
		String timeSec = queryParams.getFirst("Duration");
		currentCalls.remove(callSid);

		LOGGER.info(callDetails.getUser().getMobile() + " made call : " + callDetails.getReceiver().getMobile()
				+ " Time in seconds " + timeSec);
		LOGGER.info("Current calls :" + currentCalls);
		return Response.status(200).build();
	}

	@Path("/onnoanswer")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response onNoAnswer(@Context UriInfo uriInfo) {
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String callSid = queryParams.getFirst("CallSid");
		CallDetails callDetails = currentCalls.get(callSid);
		String timeSec = queryParams.getFirst("Duration");
		currentCalls.remove(callSid);

		LOGGER.info(callDetails.getUser().getMobile() + " made call : " + callDetails.getReceiver().getMobile()
				+ " Time in seconds " + timeSec);
		LOGGER.info("Current calls :" + currentCalls);
		return Response.status(200).build();
	}

	@Path("/allqueuelist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, CallDetails> allQuequList() {
		return callDetailsMap;
	}

	@Path("/allcurrentcalls")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, CallDetails> allCurrentCalls() {
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
