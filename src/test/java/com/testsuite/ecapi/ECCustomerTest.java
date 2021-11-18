package com.testsuite.ecapi;

import org.apache.logging.log4j.LogManager;

import com.base.ApiBaseSetup;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ECCustomerTest extends ApiBaseSetup{

	Logger log = LogManager.getLogger("ApiBaseSetup");
	
	//private static final String BASE_URL = apiBaseUrl + "/ec-customer/";
	
	@Test
	public void login()
	{
		String url= apiBaseUrl + "/ec-customer/users/login";
//		log.info("url:" + apiBaseUrl + "//ec-customer//" + "users//login");
//		//RestAssured.baseURI =  apiBaseUrl + "//ec-customer//" + "users//login";
//		
//		RestAssured.baseURI =  "https://uat-ecapi.yamibuy.tech/ec-customer/users/login";
//		RequestSpecification request = RestAssured.given();
//
//		JSONObject requestParams = new JSONObject();
//		requestParams.put("email", "markin.li@yamibuy.com"); // Cast
//		requestParams.put("pwd", "123456789");
//		
//		request.body(requestParams.toString());
//		request.header("Content-Type","application/json").header("token", "eyJhdXRoIjoiYmMxNTliNzg1OTMyMTdmZWQ5MmIxM2U1YTY2ZGY5OWMiLCJkYXRhIjoiMTA2MDUzYmEtMzYyMC00MmRhLWI5ZTAtYjBiODBmNDIzYjE3IiwiZXhwIjoxNjY3Njk5MDg1LCJpc0xvZ2luIjowfQ==");
//		
//		Response response = request.post();
//
//		log.info("response:" + response.getStatusCode() + "---"+ response.getBody().asPrettyString());
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, "200");
//		String successCode = response.jsonPath().get("SuccessCode");
//		Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("email", "markin.li@yamibuy.com");
		requestBody.put("pwd", "123456789");
		
		given().
			header("Content-Type","application/json").
			header("token", "eyJhdXRoIjoiYmMxNTliNzg1OTMyMTdmZWQ5MmIxM2U1YTY2ZGY5OWMiLCJkYXRhIjoiMTA2MDUzYmEtMzYyMC00MmRhLWI5ZTAtYjBiODBmNDIzYjE3IiwiZXhwIjoxNjY3Njk5MDg1LCJpc0xvZ2luIjowfQ==").
			body(requestBody.toString()).
		when().log().all().
			post(url).
		then().
			statusCode(200).log().all();

	}
	
}
