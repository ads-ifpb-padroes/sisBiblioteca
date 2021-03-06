/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.model;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.FactoryProvider;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.dao.aluno.AlunoDao;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.aluno.Aluno;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.CPFJaExisteException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.EmailJaExisteException;
import br.edu.ifpb.padroes.projeto.sisbiblioteca.exceptions.MatriculaJaExisteException;

/**
 *
 * @author kieckegard
 */
public class AlunoBo {

    private final AlunoDao alunoDao;

    public AlunoBo() {
        alunoDao = FactoryProvider.createFactory(1).getAlunoDao();
    }

    public void cadastrarAluno(Aluno aluno) throws InvalidStateException, CPFJaExisteException, MatriculaJaExisteException, EmailJaExisteException {
        verificaCpf(aluno.getCpf());
        verificaMatricula(aluno.getMatricula());
        verificaEmail(aluno.getEmail(),aluno.getMatricula());
        alunoDao.cadastrarAluno(aluno);
    }

    public void verificaMatricula(String matricula) throws MatriculaJaExisteException {
        for (Aluno a : alunoDao.listarAlunos()) {
            if (a.getMatricula().equals(matricula)) {
                throw new MatriculaJaExisteException("Já existe um Aluno cadastrado com essa matrícula!");
            }
        }
    }

    public void verificaEmail(String email, String matricula) throws EmailJaExisteException {
        for (Aluno a : alunoDao.listarAlunos()) {
            if (a.getEmail().equals(email) && !a.getMatricula().equals(matricula)) {
                throw new EmailJaExisteException("Já existe um Aluno cadastrado com esse e-mail!");
            }
        }
    }

    public void verificaCpf(String cpf) throws InvalidStateException, CPFJaExisteException {
        new CPFValidator().assertValid(cpf);

        //verifica se existe algum cpf igual
        for (Aluno a : alunoDao.listarAlunos()) {
            if (a.getCpf().equals(cpf)) {
                throw new CPFJaExisteException("Já existe um Aluno cadastrado com esse CPF!");
            }
        }
    }

    public void atualizarAluno(Aluno aluno) throws EmailJaExisteException {
        verificaEmail(aluno.getEmail(),aluno.getMatricula());
        alunoDao.atualizarAluno(aluno);
    }
}
