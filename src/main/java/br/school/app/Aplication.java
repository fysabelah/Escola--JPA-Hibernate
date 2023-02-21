package br.school.app;

import br.school.ctrl.*;
import br.school.model.entities.*;
import br.school.model.enums.Dia;
import br.school.model.enums.Escolaridade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Aplication {
    public static void main(String[] args) {
        testePessoa();
        testeDisciplina();
        testeProfessor();
        testeCurso();
        testeAluno();
        testeOferta();
        testeMatricula();
    }

    public static void testeCurso() {
        System.out.println("-------------------- Curso --------------------");
        CursoCtrl ctrl = new CursoCtrl();
        DisciplinaCtrl disciplinaCtrl = new DisciplinaCtrl();

        Disciplina disciplina = new Disciplina();
        disciplina.setNomeDisciplina("Qualidade de Software");
        disciplina.setCargaHoraria(64);

        Set<Disciplina> disciplinas = new HashSet<>();
        disciplinas.add(disciplina);

        Disciplina jaCadastrada = disciplinaCtrl.buscaPorId(1);
        if (jaCadastrada != null) {
            disciplinas.add(jaCadastrada);
        }

        Curso curso = new Curso();
        curso.setNomeCurso("Engenharia");
        curso.setDisciplinas(disciplinas);

        System.out.println("------ Inserir ------");
        curso = ctrl.inserir(curso);
        System.out.println(curso);

        System.out.println("------ Busca por Id ------");
        System.out.println(ctrl.buscaPorId(1));

        System.out.println("------ Busca por Nome ------");
        ctrl.buscarPorNome("Desenvolvimento").forEach(System.out::println);

        if (curso != null) {
            System.out.println("------ Alterar ------");
            curso.setNomeCurso("Engenharia de Software");
            System.out.println(ctrl.alterar(curso));
        }

        System.out.println("------ Buscar todos ------");
        ctrl.buscaTodos().forEach(System.out::println);

        ctrl.excluir(2);
    }

    public static void testeDisciplina() {
        System.out.println("-------------------- Disciplina --------------------");
        DisciplinaCtrl ctrl = new DisciplinaCtrl();

        Disciplina disciplina = new Disciplina();
        disciplina.setNomeDisciplina("Matemática Discreta");
        disciplina.setCargaHoraria(64);

        Disciplina disciplina2 = new Disciplina();
        disciplina2.setNomeDisciplina("Cálculo 1");
        disciplina2.setCargaHoraria(64);

        System.out.println("------ Inserir ------");
        disciplina = ctrl.inserir(disciplina);
        disciplina2 = ctrl.inserir(disciplina2);
        System.out.println(disciplina);
        System.out.println(disciplina2);

        System.out.println("------ Find by Name ------");
        ctrl.buscaPorNome("Microcontroladores").forEach(System.out::println);

        System.out.println("------ Find by Id ------");
        System.out.println(ctrl.buscaPorId(2));

        if (disciplina != null) {
            disciplina.setNomeDisciplina("Microcontroladores");
            System.out.println("------ Alterar ------");
            System.out.println(ctrl.alterar(disciplina));
        }

        ctrl.excluir(2);

        System.out.println("------ Buscar Todos ------");
        ctrl.buscaTodos().forEach(System.out::println);
    }

    public static void testePessoa() {
        System.out.println("-------------------- Pessoa --------------------");
        PessoaCtrl ctrl = new PessoaCtrl();

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("12536598595");
        pessoa.setDataNascimento(LocalDate.of(2000, 2, 14));
        pessoa.setNome("Isabela França");

        Pessoa pessoaNova = new Pessoa();
        pessoaNova.setCpf("12536598598");
        pessoaNova.setDataNascimento(LocalDate.of(2000, 7, 14));
        pessoaNova.setNome("Isis Lyana");

        System.out.println("------ Inserir ------");
        pessoa = ctrl.inserir(pessoa);
        System.out.println(pessoa);

        pessoaNova = ctrl.inserir(pessoaNova);
        System.out.println(pessoaNova);

        System.out.println("------ Find by Id ------");
        Pessoa findId = ctrl.buscaPorId(1);
        System.out.println(findId == null ? "Pessoa não encontrada" : findId);

        System.out.println("------ Buscar Todos ------");
        ctrl.buscaTodos().forEach(System.out::println);

        if (pessoa != null) {
            pessoa.setNome("Isa França");
            System.out.println("------ Alterar ------");
            System.out.println(ctrl.alterar(pessoa));
        }

        ctrl.excluir(1);
    }

    public static void testeOferta() {
        OfertaCtrl ofertaCtrl = new OfertaCtrl();
        ProfessorCtrl professorCtrl = new ProfessorCtrl();

        System.out.println("-------------------- Oferta --------------------");

        Oferta oferta = new Oferta();
        oferta.setHoraFinal(LocalTime.parse("10:00:00", DateTimeFormatter.ISO_TIME));
        oferta.setHoraInicial(LocalTime.parse("08:00:00", DateTimeFormatter.ISO_TIME));
        oferta.setDataInicio(LocalDate.of(2023, 2, 12));
        oferta.setDataFim(LocalDate.of(2023, 8, 12));
        oferta.setDias(new ArrayList<>(Arrays.asList(Dia.SABADO)));
        oferta.setDisciplina(new Disciplina(null, "Inglês", 96));
        oferta.setProfessor(professorCtrl.buscaPorId(2));

        System.out.println("------ Inserir ------");
        oferta = ofertaCtrl.inserir(oferta);
        System.out.println(oferta);

        System.out.println("------ Find by Id ------");
        System.out.println(ofertaCtrl.buscaPorId(1));

        System.out.println("------ Alterar ------");
        oferta.getDias().add(Dia.SEXTA);
        System.out.println(ofertaCtrl.alterar(oferta));

        System.out.println("------ Buscar Todos ------");
        ofertaCtrl.buscaTodos().forEach(System.out::println);
    }

    public static void testeProfessor() {
        ProfessorCtrl ctrl = new ProfessorCtrl();
        PessoaCtrl pessoaCtrl = new PessoaCtrl();

        System.out.println("-------------------- Professor --------------------");

        Professor professor = new Professor();
        Pessoa pessoa = new Pessoa(null, "Thiago Silva", "89232105459", LocalDate.of(1997, 10, 5));
        professor.setPessoa(pessoa);
        professor.setEscolaridade(Escolaridade.DOUTORADO);

        Professor professor2 = new Professor();
        professor2.setPessoa(pessoaCtrl.buscaPorId(2));
        professor2.setEscolaridade(Escolaridade.ESPECIALIZACAO);

        System.out.println("------ Inserir ------");
        professor2 = ctrl.inserir(professor2);
        System.out.println(professor2);


        professor = ctrl.inserir(professor);
        System.out.println(professor);


        System.out.println("------ Find by Id ------");
        System.out.println(ctrl.buscaPorId(1));

        System.out.println("------ Buscar Todos ------");
        ctrl.buscaTodos().forEach(System.out::println);

        if (professor != null) {
            System.out.println("------ Alterar ------");
            professor.getPessoa().setNome("Maria Cristina Silva");
            System.out.println(ctrl.alterar(professor));
        }

        System.out.println("------ Excluir ------");
        ctrl.excluir(1);

        System.out.println("------ Buscar Todos ------");
        ctrl.buscaTodos().forEach(System.out::println);
    }

    public static void testeAluno() {
        AlunoCtrl ctrl = new AlunoCtrl();
        CursoCtrl cursoCtrl = new CursoCtrl();

        System.out.println("-------------------- Aluno --------------------");

        Aluno aluno = new Aluno();
        Pessoa pessoa = new Pessoa(null, "Ana Maria", "10232125444", LocalDate.of(1994, 8, 22));
        aluno.setDataInicio(LocalDate.of(2016, 8, 16));
        aluno.setPessoa(pessoa);
        aluno.setAtivo(true);
        aluno.setCurso(cursoCtrl.buscaPorId(3));

        System.out.println("------ Inserir ------");
        aluno = ctrl.inserir(aluno);
        System.out.println(aluno);

        System.out.println("------ Find by Id ------");
        System.out.println(ctrl.buscaPorId(1));

        if (aluno != null) {
            System.out.println("------ Alterar ------");
            aluno.setDataInicio(LocalDate.of(2016, 1, 1));
            aluno = ctrl.alterar(aluno);
            System.out.println(aluno);
        }

        System.out.println("------ Buscar Todos ------");
        ctrl.buscaTodos().forEach(System.out::println);

        ctrl.excluir(3);
    }

    public static void testeMatricula() {
        System.out.println("-------------------- Matrícula --------------------");

        MatriculaCtrl matriculaCtrl = new MatriculaCtrl();
        AlunoCtrl alunoCtrl = new AlunoCtrl();
        OfertaCtrl ofertaCtrl = new OfertaCtrl();

        Matricula matricula = new Matricula();
        matricula.setAluno(alunoCtrl.buscaPorId(1));
        matricula.setOferta(ofertaCtrl.buscaPorId(4));

        System.out.println("------ Inserir ------");
        matricula = matriculaCtrl.inserir(matricula);
        System.out.println(matricula);

        System.out.println("------ Find by Id ------");
        System.out.println(matriculaCtrl.buscaPorId(1));

        if (matricula != null) {
            System.out.println("------ Alterar ------");
            matricula.getOferta().setHoraFinal(LocalTime.parse("12:00:00", DateTimeFormatter.ISO_TIME));
            matricula = matriculaCtrl.alterar(matricula);
            System.out.println(matricula);
        }

        matriculaCtrl.excluir(1);

        System.out.println("------ Buscar Todos ------");
        matriculaCtrl.buscaTodos().forEach(System.out::println);
    }
}
