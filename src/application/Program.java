package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			System.out.print("Check-in date(dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date(dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());
			
			
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			
			System.out.println("Reservation: "+reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date(dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date(dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
	
			reservation.updateDates(checkIn, checkOut); //N�o vai me retornar uma string com uma msg de
			//erro, vai lan�ar uma exce��o caso ela ocorra.
			System.out.println("Reservation: "+reservation);
		}
		catch(ParseException e) {
			System.out.println("Invalid date format");
		}
		catch(DomainException e) {
			//vou manter esse catch, mesmo sendo uma RuntimeException, para n�o deixar o programa quebrar
			System.out.println("Error in reservation: "+ e.getMessage());
		}
		//Para que meu programa n�o quebre com uma possivel, outra, RuntimeException, eu posso fazer outro catch
		catch(RuntimeException e) {
			System.out.println("Unexpected error");
		}

		sc.close();
	}
}
