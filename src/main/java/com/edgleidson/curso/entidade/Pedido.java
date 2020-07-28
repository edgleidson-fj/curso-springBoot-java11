// Obs: fetch = FetchType.EAGER) - Solução provisória para o erro no POSTMAN.


package com.edgleidson.curso.entidade;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.edgleidson.curso.entidade.enums.PedidoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

//Obs: A partir do Java 8 é mais indicado utilizar o INSTANT ao invés do DATE.

// Order = Pedido.

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// pattern = Padrão ISO 8601 / timezone = Padrão UTC (Greenwich Meen Time).
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") 
	private Instant momento; // Horário e Data.
	
	@OneToOne(mappedBy = "pedido", // Um-para-Um mapeado com classe Pagamento. 
				cascade = CascadeType.ALL) // Para ambas classe ficarem com mesmo ID - (Pedido/Pagamento).
	private Pagamento pagamento;
	
	// Enum - Forçando como tipo Inteiro.
	private Integer pedidoStatus;

	// Cliente do Pedido.
	@ManyToOne //Anotação (Muito-para-Um) - ex: Vários pedidos para um cliente. 
	@JoinColumn(name = "cliente_id") //Anotação (Chave estrangeira).
	private User client;
	
	// Itens do Pedido.
	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER)
	private Set<ItemPedido> items = new HashSet<>();

	public Pedido() {
	}

	public Pedido(Long id, Instant momento, PedidoStatus pedidoStatus, User client) {
		super();
		this.id = id;
		this.momento = momento;
		setPedidoStatus(pedidoStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}
	
	// Enum - Get com método (ValorDoCodigo) do Enum PedidoStatus.
	public PedidoStatus getPedidoStatus() {
		return PedidoStatus.valorDoCodigo(pedidoStatus);
	}

	// Enum - Set com método getCodigo do Enum PedidoStatus.
	public void setPedidoStatus(PedidoStatus pedidoStatus) {
		if(pedidoStatus != null) {
		this.pedidoStatus = pedidoStatus.getCodigo();
		}
	}
	//----------------------------------

	// Cliente.
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	//-------------------------
	
	// Pagamento.
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	//------------------------
	
	// ItemPedido.
	public Set<ItemPedido> getItems() {
		return items;
	}
	//--------------------------
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
