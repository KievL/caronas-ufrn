package br.ufrn.umbrella.caronasufrn.models.enums;

public enum Status {
	AGUARDANDO("Aguardando"),
	CANCELADA("Cancelada"),
	FINALIZADA("Finalizada");
	
	public final String value;
	
	Status(String value) {
		this.value = value;
	}
}
