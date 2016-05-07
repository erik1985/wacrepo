package worldmap.services;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import worldmap.model.Country;
import worldmap.model.ServiceProvider;
import worldmap.model.CountryService;

@Path("/countries")
public class CountryResource {
	
	@GET
	@Produces("application/json")
	public String getCountries() {
		CountryService service = ServiceProvider.getWorldService();
		JsonArray countryArray = buildJsonCountryArray(service.getAllCountries());
		
		return countryArray.toString();	
	}

	@GET
	@Path("/{code}")
	@Produces("application/json")
	public String getCountry(@PathParam("code") String countryCode) {
		CountryService service = ServiceProvider.getWorldService();
		
		List<Country> countries = new ArrayList<Country>();
		countries.add(service.getCountryByCode(countryCode));
		JsonArray countryArray = buildJsonCountryArray(countries);
		
		return countryArray.toString();	
	}
	
	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String get10LargestPopulations() {
		CountryService service = ServiceProvider.getWorldService();
		
		List<Country> countries = service.get10LargestPopulations();
		JsonArray countryArray = buildJsonCountryArray(countries);
		
		return countryArray.toString();						
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getLargestSurfaces(@PathParam("id") int param1) {
		CountryService service = ServiceProvider.getWorldService();
		
		List<Country> countries = service.get10LargestSurfaces();
		JsonArray countryArray = buildJsonCountryArray(countries);
		
		return countryArray.toString();	
	}
	

	private JsonArray buildJsonCountryArray(List<Country> countries) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for (Country c : countries) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("government", c.getGovernment());
			
			jsonArrayBuilder.add(job);
		}
		
		return jsonArrayBuilder.build();
	}
}
