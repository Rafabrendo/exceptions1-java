package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		//throws ParseException, quando eu acrescento essa declaração eu propago essa exception
		//Estou falando que meu metodo main não deve tratar esse tipo de exceção, se ocorrer
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.print("Check-in date(dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		System.out.print("Check-out date(dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		//se não!(inverte) (checkout dps de checkin)
		//Essa validação tinha que estar no construtor, mas não tem como o construtor retornar uma string
		//Para ser delegada
		if (!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		}
		else {
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: "+reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date(dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date(dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			//Lógica ruim: Isso é um problema de delegação. Quem deve ser responsavel por saber a reserva
			//é a reserva, e não outra classe.(tbm é ruim, mas melhor que a atual).
		
			String error = reservation.updateDates(checkIn, checkOut);
			if (error != null) {
				//Significa que veio alguma String contendo erro
				System.out.println("Error in reservation: "+ error);
			}
			else {
				System.out.println("Reservation: "+reservation);
			}
		

		}
		
	
		sc.close();
	}
}
