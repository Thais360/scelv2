package com.fatec.scel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.AlunoRepository;
import com.fatec.scel.model.Aluno;

@RestController
@RequestMapping(path = "/aluno")
public class AlunoController {
private static final String RA = null;
//insert into aluno values ('1', 'Pressman','aaaa', 'engenharia')
	@Autowired
	private AlunoRepository repository;

	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ConsultarAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}

	/**
	 * quando o usuario digita localhost:8080/api/add
	 * 
	 * @param aluno
	 * @return o html /CadastrarAluno
	 */
	@GetMapping("/cadastrar")
	public ModelAndView cadastraraluno(Aluno aluno) {

		ModelAndView mv = new ModelAndView("CadastrarAluno");
		mv.addObject("aluno", aluno);

		return mv;
	}

	@GetMapping("/edit/{ra}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView mostraFormAdd(@PathVariable("ra") String ra) {
		ModelAndView modelAndView = new ModelAndView("AtualizaAluno");

		modelAndView.addObject("aluno", repository.findByRA(RA)); // o repositorio e injetado no controller

		return modelAndView; // addObject adiciona objetos para view

	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		repository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("ConsultarAlunos");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;

	}

	@PostMapping("/salvar")
	public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("ConsultarAlunos");
		if (result.hasErrors()) {
			return new ModelAndView("CadastrarAluno");
		}
		try {
			Aluno jaExiste=null;
			jaExiste = repository.findByRA(aluno.getRA());
			if (jaExiste == null) {
				repository.Salvar(aluno);
				modelAndView = new ModelAndView("ConsultarAlunos");
				modelAndView.addObject("aluno", repository.findAll());
				return modelAndView;
			} else {
				return new ModelAndView("CadastrarAluno");
			}
		} catch (Exception e) {
			System.out.println("erro ===> " +e.getMessage());
			return modelAndView; // captura o erro mas nao informa o motivo.
		}
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualiza(@PathVariable("id") String ra, @Valid Aluno aluno, BindingResult result) {
	
		if (result.hasErrors()) {
			aluno.setRA(RA);
			return new ModelAndView("AtualizaAluno");
		}
		Aluno umAluno = repository.findByRA(RA);
		umAluno.setEmail(aluno.getEmail());
		umAluno.setRA(aluno.getRA());
		umAluno.setNome(aluno.getNome());
		repository.Salvar(umAluno);
		ModelAndView modelAndView = new ModelAndView("ConsultarAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}
}
