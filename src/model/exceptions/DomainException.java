package model.exceptions;

public class DomainException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DomainException(String msg) {
		super(msg);//Fiz isso para permitir que eu posso instanciar a minha exceção personalizada
		//passando uma msg para ela. Essa msg fica armazenada na minha esceção
	}

}
