package fja.edu.com.cadastrolegal.model;

import androidx.annotation.NonNull;

public class EstadoCivilDto {
    private String codigo;
    private String descricao;

    @NonNull
    @Override
    public String toString() {
        return this.descricao;
    }

    public EstadoCivilDto() {
    }

    public EstadoCivilDto(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
