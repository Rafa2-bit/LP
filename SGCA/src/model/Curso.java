
package model;

import java.util.ArrayList;

/**
 *
 * @author faelb
 */
public class Curso {
    private String nome;
    private int idCurso,cargaHoraria,limiteAlunos,ativo;
    private ArrayList<String> listaAlunos;
    
    
    
    public Curso(int idCurso,String nome , int cargaHoraria, int limiteAlunos, int ativo) {
        this.nome = nome;
        this.idCurso = idCurso;
        this.cargaHoraria = cargaHoraria;
        this.limiteAlunos = limiteAlunos;
        this.ativo = ativo;
    }
    

    public String getNome() {
        return nome;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    public ArrayList<String> getListaAlunos() {
        return listaAlunos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }

    public void setListaAlunos(ArrayList<String> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
    
}
