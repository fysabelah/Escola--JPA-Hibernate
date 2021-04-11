package br.ufg.inf.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufg.inf.ctrl.exception.*;
import br.ufg.inf.model.dao.*;
import br.ufg.inf.model.enums.*;
import br.ufg.inf.model.entities.*;
import br.ufg.inf.ctrl.*;

public class Aplication {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Executando!!!");
		testeDisciplina();
		testeCurso();
		testePessoa();
		testeProfessor();
		testeAluno();
		testeOferta();
		testeMatricula();
		System.out.println("Concluindo");
	}
	
	public static void teste(){
		//Queria saber se funcionava e funcionou
		DisciplinaCtrl ctrl = new DisciplinaCtrl();
		Disciplina d1 = new Disciplina();
		
		d1.setNmDisciplina("Des. FullStack");
		d1.setCargaHoraria(60);
		
		System.out.println(ctrl.inserir(d1));
	}
	
	public static void testeDisciplina(){
		DisciplinaCtrl ctrl = new DisciplinaCtrl();
		
		Disciplina disc1 = new Disciplina(null, "Des. FullStack", 64);
		Disciplina disc2 = new Disciplina(null, "LLP", 60);
		
		ctrl.inserir(disc2);
		ctrl.inserir(disc1);
		
		disc1 = ctrl.buscaPorId(2);
		disc1.setNmDisciplina("Linguagens Formais e Autômatos");
		ctrl.alterar(disc1);
		
		ctrl.excluir(1);
		
		Disciplina disciplina = new Disciplina(null, "Banco de Dados", 64);
		ctrl.inserir(disciplina);
		
		for(Disciplina d : ctrl.buscaTodos()) {
			System.out.println(d);
		}
		
		System.out.println("-----------------------------------");
		for(Disciplina d : ctrl.buscaPorNome("Banco")) {
			System.out.println(d);
		}
	}
	
	public static void testeCurso(){
		System.out.println("Curso");
		CursoCtrl ctrl = new CursoCtrl();
		
		Curso curso1 = new Curso(null, "Moda");
		Curso curso2 = new Curso(null, "Estética");
		
		ctrl.inserir(curso1);
		ctrl.inserir(curso2);
		
		curso1 = ctrl.buscaPorId(1);
		if(curso1 == null) {
			System.out.println("Curso de ID 1 não encontrado.");
		}
		else{
			curso1.setNmCurso("Redes");
			System.out.println("Curso alterado: " + ctrl.alterar(curso1));
		}
		
		ctrl.excluir(2);
		
		System.out.println("-------------------- Todos os cursos ---------------");
		for(Curso c: ctrl.buscaTodos()) {
			System.out.println(c);
		}
		
		System.out.println();
	}
	
	public static void testePessoa(){
		PessoaCtrl ctrl = new PessoaCtrl();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Pessoa pessoa = new Pessoa(null, "Cristina Oliveira", 17345678901l, simpleDateFormat.parse("15/06/2000"));
			
			ctrl.inserir(pessoa);
			
			pessoa = ctrl.buscaPorId(3);
			pessoa.setNmPessoa("Fernades");
			ctrl.alterar(pessoa);
			
			ctrl.excluir(1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static void testeOferta() {
		OfertaCtrl ctrl = new OfertaCtrl();
		ProfessorCtrl professorCtrl = new ProfessorCtrl();
		DisciplinaCtrl disciplinaCtrl = new DisciplinaCtrl();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Oferta ofe1 = new Oferta(null, 
						professorCtrl.buscaPorId(1), 
						disciplinaCtrl.buscaPorId(3), 
						simpleDateFormat.parse("02/02/2021"), 
						simpleDateFormat.parse("15/06/2021"), 
						Dia.SEGUNDA, 
						"08:00");
			
			System.out.println(ctrl.inserir(ofe1));
			
			Oferta oferta = ctrl.buscaPorId(1);
			oferta.setDtInicio(simpleDateFormat.parse("22/02/2021"));
			ctrl.alterar(oferta);
			
			for(Oferta o : ctrl.buscaTodos()) {
				System.out.println(o);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testeProfessor() {
		ProfessorCtrl ctrl = new ProfessorCtrl();
		PessoaCtrl pessoaCtrl = new PessoaCtrl();
		
		Professor prof1 = new Professor(null, 
		pessoaCtrl.buscaPorId(2), Escolaridade.MESTRADO);
		ctrl.inserir(prof1);
		
		prof1 = ctrl.buscaPorId(1);
		prof1.setEscolaridade(Escolaridade.DOUTORADO);
		ctrl.alterar(prof1);
	}
	
	public static void testeAluno() {
		System.out.println("Aluno");
		PessoaCtrl ctrl = new PessoaCtrl();
		CursoCtrl ctrlc = new CursoCtrl();
		AlunoCtrl ctrlA = new AlunoCtrl();
		
		try {
			Date dt1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-4-9");
			
			Aluno aluno = new Aluno(null,
					dt1,
					true,
					ctrl.buscaPorId(2),
					ctrlc.buscaPorId(3));
			
			ctrlA.inserir(aluno);
			
			System.out.println("---------------- Busca por ID ------------------");
			System.out.println(ctrlA.buscaPorId(5));
			
			System.out.println("-------------------- Todos os alunos ---------------");
			for(Aluno al: ctrlA.buscaTodos()) {
				System.out.println(al);
			}
			
			System.out.println("--------------------------------------------------");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
	}
	
	public static void testeMatricula() {
		System.out.println("Matrícula");
		AlunoCtrl aluno = new AlunoCtrl();
		OfertaCtrl oferta = new OfertaCtrl();
		MatriculaCtrl mat = new MatriculaCtrl();
		
		//Inserindo Matricula
		Matricula mat1 = new Matricula(null, aluno.buscaPorId(3), oferta.buscaPorId(2));
		mat.inserir(mat1);
		
		//Buscando por id
		System.out.println("--------------------------------------------------");
		System.out.println("Matricula de ID 1");
		System.out.println(mat.buscaPorId(1));
		
		//Buscando todas
		System.out.println("--------------------------------------------------");
		System.out.println("Matriculas Cadastrados");
		for (Matricula mati: mat.buscaTodos()) {
			System.out.println(mati);
		}
		
		System.out.println("");
	}
}
