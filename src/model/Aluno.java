
package model;

import java.util.Date;

/**
 *
 * @author faelb
 */
public class Aluno {
    private int idAluno,telefone;
    private String nome,cpf,email;
    private Date datanasc;
    
    public Aluno(int idAluno,String nome,String cpf,int telefone,String email,Date datanasc){
        this.idAluno = idAluno;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.datanasc = datanasc;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getTelefone() {
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

    public void setTelefone(int telefone) {
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
    
    
    
}
