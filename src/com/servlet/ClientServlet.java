package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		Client client = Client.create();
		WebResource wr = 	client.resource("http://madflyersbusiness.cfapps.io/rest/fetchAD?pincode=600096");
		ClientResponse cr = wr.accept("text/html").get(ClientResponse.class);
		ArrayList<AdDetail> adList = new ArrayList<AdDetail>();
		if(cr.getStatus()==200){
			String output = cr.getEntity(String.class);
			System.out.println(output);
			JSONArray array = new JSONArray(output);
			for(int i=0;i<array.length();i++){
			JSONObject obj = 	array.getJSONObject(i);
			AdDetail ad = new AdDetail();
			ad.setBusinessName(obj.getString("business"));
			ad.setAddr1(obj.getString("addressL1"));
			ad.setAddr2(obj.getString("addressL2"));
			ad.setAddr3(obj.getString("addressL3"));
			ad.setCity(obj.getString("city"));
			ad.setState(obj.getString("state"));
			ad.setCountry(obj.getString("country"));
			ad.setPincode(obj.getString("pincode"));
			ad.setDesc(obj.getString("desc"));
			ad.setUsername(obj.getString("username"));
			ad.setPhone(obj.getString("phone"));
		adList.add(ad);
				}
		}
		}catch(Exception e){
			
		}
	}

}
