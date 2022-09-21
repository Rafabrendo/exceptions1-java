package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
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
	
	public String updateDates(Date CheckIn, Date CheckOut) {
		Date now = new Date();//Cria um horario de agr
		if (checkIn.before(now) || checkOut.before(now)) {
			 return "Reservation dates for update must be future dates";
		}
		if (!checkOut.after(checkIn)){
			return "Check-out date must be after check-in date";
		}
		this.checkIn = CheckIn;
		this.checkOut = CheckOut;
		return null; //Se retornar null é porque não deu nenhum erro. Se retornar algum String que não seja 
		//null, é porque teve algum erro
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
