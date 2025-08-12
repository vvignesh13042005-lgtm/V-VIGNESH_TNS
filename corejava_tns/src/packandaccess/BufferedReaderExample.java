package packandaccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderExample {

	public static void main(String[] args)throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.println("Enter your country: ");
		String country = br.readLine();
		
		System.out.println("Enter your pin code: ");
		int pin = Integer.parseInt(br.readLine());
		
		System.out.println("Country: "+ country + ", Pin Code: " + pin);
		
	}

}
