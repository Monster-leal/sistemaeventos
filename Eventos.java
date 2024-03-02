package sistemaeventos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<String> participantes;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(String participante) {
        participantes.add(participante);
    }
    public void removerParticipante(String participante) {
        participantes.remove(participante);
    }
}