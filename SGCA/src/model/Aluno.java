
package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author faelb
 */
public class Aluno {
    private int idAluno,ativo;
    private String nome,cpf,email,telefone;
    private Date datanasc;
    
    public Aluno(String nome,String cpf,String telefone,String email,Date datanasc,int ativo){
        this.idAluno = idAluno;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.datanasc = datanasc;
        this.ativo = ativo;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
    public boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) 
            return false;
        if (cpf.matches("(\\d)\\1{10}")) 
            return false;
        return true;
    }
    public boolean validarEmail(String email) {
    String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    return email.matches(regex);
    }
    public boolean validarTelefone(String telefone) {
    telefone = telefone.replaceAll("[^0-9]", "");
    return telefone.length() == 11;
    }
    public boolean validarIdade(Date dataNasc) {
    LocalDate nascimento = dataNasc.toLocalDate();         
    LocalDate hoje = LocalDate.now();                     
    int idade = Period.between(nascimento, hoje).getYears();

    return idade >= 16;
}

}
