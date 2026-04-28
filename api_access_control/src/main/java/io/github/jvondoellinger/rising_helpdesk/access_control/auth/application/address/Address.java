package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.address;

public class Address {
	public final String city;
	public final String country;
	public final String isp;

	public Address(String city, String country, String isp) {
		this.city = city;
		this.country = country;
		this.isp = isp;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getIsp() {
		return isp;
	}
}
