package com.example.lucascarvalho.adopet;

/**
 * Created by Lucas Carvalho on 07/11/2017.
 */


public class Animal {
    private int id;
    private String nome;
    private String porte;
    private int idade;
    private String dono;
    String sexo;
    private byte[] image1;
    private byte[] image2;
    private byte[] image3;
    private byte[] image4;
    private String especie;
    private String localizacao;

    public Animal(int id, String nome, String porte, int idade, String dono,String sexo, byte[] image1, byte[] image2, byte[] image3, byte[] image4, String especie, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.porte = porte;
        this.idade = idade;
        this.dono = dono;
        this.sexo = sexo;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.especie = especie;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    public byte[] getImage4() {
        return image4;
    }

    public void setImage4(byte[] image4) {
        this.image4 = image4;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
