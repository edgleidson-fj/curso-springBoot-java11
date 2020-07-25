package com.edgleidson.curso.entidade.enums;

public enum PedidoStatus {

	AGUARDANDO_PAGAMENTO(1),
	PAGO(2),
	ENVIADO(3),
	ENTREGUE(4),
	CANCELADO(5);
	
	private int codigo;
	
	// Construtor do Enum - Tem que ser private.
	private PedidoStatus(int codigo) {
		this.codigo = codigo;
	}
	
	// Para tornar o código publico para outras classes.
	public int getCodigo() {
		return codigo;
	}
	
	// Para converter valor numérico para tipo Enumerado.
	public static PedidoStatus valorDoCodigo(int cod) {
		for(PedidoStatus valor : PedidoStatus.values()) {
			if(valor.getCodigo() == cod) {
				return valor;
			}
		}
		throw new IllegalArgumentException("Código de PedidoStatus inválido!");
	}
}
