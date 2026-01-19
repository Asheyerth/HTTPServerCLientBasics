//public class Main {  //public static void main(String[] args) {    //System.out.printf("asd5757575757"); 
  //}//}
  public class Main {  public static void main(String[] args) {    String myStr = "Hello"; 
    byte[] result = myStr.getBytes(); 
    System.out.println(result[0]); 
    System.out.println(result[1]); 
    System.out.println(result[2]); 
    System.out.println(result[3]); 
    System.out.println(result[4]); 
        String respon = "Hola, célula"; 
    System.out.println(respon.length()); //12        
    String respond = "Hola, celula"; 
    System.out.println(respond.length()); //12    
    byte[] responB = respon.getBytes(); 
    byte[] respondB = respond.getBytes(); 
    System.out.println(result.length); //5 Hello    
    System.out.println(responB.length); //13 "Hola, célula"    
    System.out.println(respondB.length); //12 "Hola, celula"
      }}