import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class JavaProject {
	
	public class GenelUye{ // Genel uye adında bir class olusturdum ve icerisinde istenilen degiskenleri tuttum.
	    String isim;
	    String soyisim;
	    String email;
	    String uyeTipi;
	        
	    public GenelUye(String isim, String soyisim, String email, String uyeTipi ) {
	        this.isim = isim;
	        this.soyisim = soyisim;
	        this.email = email;
	        this.uyeTipi = uyeTipi;
	        dosyayaYaz();
	    }
	    	    
	    public void dosyayaYaz() { // Dosya islemleri icin bir fonksiyon olusturdum.
	        try {
	        	String dosyaIsmi = " ";
	        	if (this.uyeTipi.equals("elit")) {
					dosyaIsmi = "elituyeler.txt";
				}
	        	else if (this.uyeTipi.equals("genel")) {
	        		dosyaIsmi = "geneluyeler.txt";					
				}
	            FileWriter writer = new FileWriter(dosyaIsmi, true);
	            writer.write(this.isim + "\t" + this.soyisim + "\t" + this.email + "\n");
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("Dosya yazma hatası: " + e.getMessage());
	        }
	    }
	}
	
	public class ElitUye extends GenelUye{ // Burada kalitim kullandim.
		
		public ElitUye(String isim, String soyisim, String email, String uyeTipi) {
			
			super(isim, soyisim, email, uyeTipi);		
		}
	}
	
	public class Menu{ // Projenin daha dinamik olmasi ve main kisminda uzun uzun kodlar olmamasi icin Menu adinda bir class olusturdum.
		
		public Menu() { 
			MenuyuCalistir(); // 97. kod blogunda olusturdugum fonksiyonu cagirdim.
		}
		
		public void ElitUyeOlustur(String isim, String soyisim, String email, String uyeTipi) {
			ElitUye elitUye = new ElitUye(isim, soyisim, email, uyeTipi); // Yeni bir ozellik eklenmesi durumunda kolaylıkla degisiklik yapabilmek icin
		}																  // elit uyeleri bu fonksiyonda tutmak istedim.
		
		public void GenelUyeOlustur(String isim, String soyisim, String email, String uyeTipi) {
			GenelUye genelUye = new GenelUye(isim, soyisim, email, uyeTipi); // Genel uyeleri tutmak icin de bir fonksiyon olusturdum.		
		}
		public void UyeOlustur(String uyeTipi) { // Uye olusturmak icin bir fonksiyon kullandim.
			Scanner scanner = new Scanner(System.in);
			String isim,soyisim,mail;
			System.out.println("Isim Giriniz");
			isim = scanner.nextLine();
			System.out.println("Soyisim Giriniz.");
			soyisim = scanner.nextLine();
			System.out.println("Mail Giriniz.");
			mail = scanner.nextLine();
			
			if (uyeTipi.equals("elit")) {			
				ElitUye elitUye = new ElitUye(isim, soyisim, mail, "elit");
			} else if(uyeTipi.equals("genel")) {
				GenelUye genelUye = new GenelUye(isim, soyisim, mail, "genel");
			}
		}
		public void MailGonder(String dosyaIsmi) { // Yapay bir mail gonderme fonksiyonu yazdim.  
				
		        BufferedReader reader;
		        try {
		            reader = new BufferedReader(new FileReader(dosyaIsmi));
		            String satir = reader.readLine();
		            while (satir != null) {
		                String[] satirDizi = satir.split("\t");
		                String email = satirDizi[2];
		                System.out.println(email + "'e mail gonderildi.");
		                satir = reader.readLine();
		            }
		            reader.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
		
		public void MenuyuCalistir() { // Bu fonksiyon ile istedigimiz menu calisiyor.
			
			while (true) { // Islem bittikten sonra menuyu ekranda tekrar bastirmak icin while dongusu kullandim.
		
			String[] secenekler = {"- Elit Uye Olustur","- Genel Uye Olustur","- Mail Gonder"};
			
			for(int index = 0 ; index<secenekler.length ; index++) {
				int sirasayisi = index + 1;
				System.out.println(sirasayisi + " " + secenekler[index] + "\n");		
			}
			
			Scanner scanner = new Scanner(System.in);
			int secim = scanner.nextInt();
			
			switch (secim) {
			case 1:
				System.out.println("Elit uye olusturmak icin bilgilerini giriniz.");	
				UyeOlustur("elit");
				break;
			case 2:
				System.out.println("Genel uye olusturmak icin bilgilerini giriniz.");	
				UyeOlustur("genel");
				break;
			case 3:
				System.out.println("Elit uyeler icin 1'i Genel uyeler icin 2'yi seciniz.");	
				int mailsecimi = scanner.nextInt();
				if (mailsecimi == 1) {
					MailGonder("elituyeler.txt");
				} else if (mailsecimi == 2) {
					MailGonder("geneluyeler.txt");
				}
				break;
			default:
				System.out.println("Yanlis bir secenek girdiniz.");	
				break;
		  }
		}
	  }
	}
	
	public static void main(String[] args) { // Kodlari dinamik bir sekilde yazmaya calistim bu yuzden Menu classının constructorini cagirmak yetti.
	    System.out.println("Hosgeldiniz...\nYapmak istediginiz islemi secin.\n");
	    
	    JavaProject proje = new JavaProject();
	       
	    Menu menu = proje.new Menu();
	}
}