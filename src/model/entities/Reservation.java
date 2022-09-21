package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}
	//Obs.: Se a classe DomainException for do extenção de RuntimeException, não se é obrigado a tratar as exceções no codigo fonte

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut){
		if (!checkOut.after(checkIn)){
			throw new DomainException("Check-out date must be after check-in date");
		} //To verificando se a data de checkout não é dps do checkin. Se acontecer, já lança uma exceção
		//Posso tratar ou propagar. Nesse caso eu vou propagar adicionando a declaração -> throws DomainException
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}


	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		//vai me devolver a quantidade de milisegundos daquela data
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		//vai converter o diff que estava em miliseconds para days	
	}
	
	public void updateDates(Date CheckIn, Date CheckOut) {
		//Ou eu trato a exceção, ou eu propago ela, na assinatura do meu metodo.
		//Vou propagar ela na assinatura do metodo, colocalndo throws DomainException.
		//Agr esse metodo pode lançar uma exceção.
		//Quem vai tratar a exceção vai ser o programa principal, no bloco catch.
		//O bloco if vai lançar a exceção
		Date now = new Date();//Cria um horario de agr
		if (checkIn.before(now) || checkOut.before(now)) {
			 throw new DomainException("Reservation dates for update must be future dates");
		}
		if (!checkOut.after(checkIn)){
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkIn = CheckIn;
		this.checkOut = CheckOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
	
	
	
	
}
