package apitest;

public class Pojofile2 {
	 String phone_number;
	 String pin;
	 String language;
	
	 public Pojofile2(String phone_number,String pin,String language)
	 {
		 
		 this.phone_number=phone_number;
		 this.pin=pin;
		 this.language=language;
		 
		 
	 }
	 public void setphone_number(String phone_number)
	 {
		 this.phone_number=phone_number;
	 }
	 public String getphone_number()
	 {
		 return phone_number;
	 }
	 public void setpin(String pin)
	 {
		 this.pin=pin;
	 }
	 public String getpin()
	 {
		 return pin;
	 }
	 public void setlanguage(String language)
	 {
		this.language=language;
	 }
	 public String getlanguage()
	 {
		 return language;
	 }

}
