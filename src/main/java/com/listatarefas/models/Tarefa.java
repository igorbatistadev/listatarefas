package com.listatarefas.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.listatarefas.models.Enum.Status;

@Entity
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, columnDefinition="TEXT")
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, updatable=false)
	private Date data_criacao;
	
	@Temporal(TemporalType.DATE)
	private Date data_conclusao;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private Status status = Status.AFAZER;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData_criacao() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); 
		return fmt.format(data_criacao);
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public String getData_conclusao() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy"); 
		return fmt.format(data_conclusao);
	}
	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_conclusao == null) ? 0 : data_conclusao.hashCode());
		result = prime * result + ((data_criacao == null) ? 0 : data_criacao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Tarefa other = (Tarefa) obj;
		if (data_conclusao == null) {
			if (other.data_conclusao != null)
				return false;
		} else if (!data_conclusao.equals(other.data_conclusao))
			return false;
		if (data_criacao == null) {
			if (other.data_criacao != null)
				return false;
		} else if (!data_criacao.equals(other.data_criacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", descricao=" + descricao + ", data_criacao=" + data_criacao + ", data_conclusao="
				+ data_conclusao + ", status=" + status + "]";
	}
}
