package com.hubfintech.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hubfintech.app.entities.Conta;
import com.hubfintech.app.entities.Historico;
import com.hubfintech.app.entities.Pessoa;
import com.hubfintech.app.enums.SituacaoConta;
import com.hubfintech.app.enums.TipoConta;
import com.hubfintech.app.enums.TipoPessoa;
import com.hubfintech.app.enums.TipoTransacao;
import com.hubfintech.app.repositories.ContaRepository;
import com.hubfintech.app.repositories.HistoricoRepository;
import com.hubfintech.app.repositories.PessoaRepository;

@SpringBootApplication
public class App {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private HistoricoRepository historicoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("###############################################################");
			System.out.println("### INICIO");
			System.out.println("###############################################################");
			
			Conta conta = new Conta();
			conta.setNome("Teste 1");
			conta.setSaldo(new BigDecimal(1000));
			conta.setSituacao(SituacaoConta.ATIVA);
			conta.setTipoConta(TipoConta.MATRIZ);
			
			Conta conta2 = new Conta();
			conta2.setNome("Teste 2");
			conta2.setSaldo(new BigDecimal(1000));
			conta2.setSituacao(SituacaoConta.ATIVA);
			conta2.setTipoConta(TipoConta.MATRIZ);
			
			Conta conta3 = new Conta();
			conta3.setNome("Teste 3");
			conta3.setSaldo(new BigDecimal(1000));
			conta3.setSituacao(SituacaoConta.ATIVA);
			conta3.setTipoConta(TipoConta.MATRIZ);
			
			Conta conta4 = new Conta();
			conta4.setNome("Teste 4");
			conta4.setSaldo(new BigDecimal(1000));
			conta4.setSituacao(SituacaoConta.ATIVA);
			conta4.setTipoConta(TipoConta.MATRIZ);
			
			Conta conta5 = new Conta();
			conta5.setNome("Teste 5");
			conta5.setSaldo(new BigDecimal(1000));
			conta5.setSituacao(SituacaoConta.ATIVA);
			conta5.setTipoConta(TipoConta.MATRIZ);
			
			Pessoa pessoa = new Pessoa();
			pessoa.setNome("Teste");
			pessoa.setNumeroCpfCnpj("12905142839");
			pessoa.setDataNascimento(new Date());
			pessoa.setTipoPessoa(TipoPessoa.FISICA);
			conta.setPessoa(pessoa);
			
			List<Conta> listaConta = new ArrayList<Conta>();
			List<Conta> listaConta2 = new ArrayList<Conta>();
			
			listaConta2.add(conta4);
			conta3.setContas(listaConta2);
			
			listaConta.add(conta2);
			listaConta.add(conta3);
			
			conta.setContas(listaConta);
			
			contaRepository.save(conta);
			contaRepository.save(conta5);
			
			List<Conta> contas = contaRepository.findAll();
			contas.forEach(System.out::println);
			
			List<Pessoa> pessoas = pessoaRepository.findAll();
			pessoas.forEach(System.out::println);
			
			Conta conta6 = new Conta();
			conta6.setNome("Teste 6");
			conta6.setSaldo(new BigDecimal(1000));
			conta6.setSituacao(SituacaoConta.ATIVA);
			conta6.setTipoConta(TipoConta.MATRIZ);
			
			Historico historico = new Historico();
			historico.setContaDestino(conta6);
			historico.setTipoTransferencia(TipoTransacao.TRANFERENCIA);
			historico.setValor(new BigDecimal(1000));
			historicoRepository.save(historico);
			
			List<Historico> historicos = historicoRepository.findAll();
			historicos.forEach(System.out::println);
			
			System.out.println("###############################################################");
			System.out.println("### FIM");
			System.out.println("###############################################################");
			
		};
	}
}
