public class PerritosFactory{
	
	public Perritos getPerritos(String type)
	{
		if(type==null){
			return null;
		}
		if(type.equalsIgnoreCase("golden")){
			return new Golden();
		}
		if(type.equalsIgnoreCase("pug")){
			return new Pug();
		}
		return null;
	}
}