
package model;

import java.util.ArrayList;

/**
 *
 * @author faelb
 */
public class Curso {
    private String nome;
    private int idCurso,cargaHoraria,limiteAlunos,ativos;
    private ArrayList<String> listaAlunos;
    
    
    public Curso(int idCurso,String nome , int cargaHoraria, int limiteAlunos, int ativos) {
        this.nome = nome;
        this.idCurso = idCurso;
        this.cargaHoraria = cargaHoraria;
        this.limiteAlunos = limiteAlunos;
        this.ativos = ativos;
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

    public int getAtivos() {
        return ativos;
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

    public void setAtivos(int ativos) {
        this.ativos = ativos;
    }

    public void setListaAlunos(ArrayList<String> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }
    
    
}
