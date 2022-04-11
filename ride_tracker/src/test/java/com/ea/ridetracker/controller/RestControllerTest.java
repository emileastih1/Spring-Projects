package com.ea.ridetracker.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ea.ridetracker.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	
	@Test(timeout=3000)
	public void testGreateRides() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = new Ride();
		ride.setName("Yellow fork Ride");
		ride.setDuration(38);
		
		ride = restTemplate.postForObject("http://localhost:9999/ride_tracker/ride", ride, Ride.class);
	}
	
	
	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:9999/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
	
	@Test(timeout=3000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:9999/ride_tracker/ride/1", Ride.class );
		
		System.out.println("Ride name: "+ride.getName());
		
	}
	
	@Test(timeout=3000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:9999/ride_tracker/ride/1", Ride.class );
		
		ride.setDuration(ride.getDuration()+1);
		
		restTemplate.put("http://localhost:9999/ride_tracker/ride", ride);
		
		System.out.println("Ride name: "+ride.getName());
		
	}
	
	@Test(timeout=3000)
	public void testBatchUpdate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:9999/ride_tracker/batch", Object.class );
	}
	
	@Test(timeout=3000)
	public void testDelete() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:9999/ride_tracker/delete/5" );
	}
	
	@Test(timeout=3000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:9999/ride_tracker/test", Ride.class );
	}
}
