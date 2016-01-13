public class Main{
	public static void main(String[] args){
		PerritosFactory pF = new PerritosFactory();
		
		System.out.println();
		Perritos simba= pF.getPerritos("golden");
		simba.ladrar();

		System.out.println();
		Perritos lucifer= pF.getPerritos("pug");
		lucifer.ladrar();
	}
}