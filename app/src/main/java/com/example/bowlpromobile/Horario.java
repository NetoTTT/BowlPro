package com.example.bowlpromobile;

public class Horario {
    private String id; // Adicione o campo id
    private String data_horario;
    private String email_cliente;
    private String horario;

    // Construtor padrão necessário para Firebase
    public Horario() {}

    public Horario(String id, String data_horario, String email_cliente, String horario) {
        this.id = id;
        this.data_horario = data_horario;
        this.email_cliente = email_cliente;
        this.horario = horario;
    }

    // Getter e Setter para o id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData_horario() {
        return data_horario;
    }

    public void setData_horario(String data_horario) {
        this.data_horario = data_horario;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
