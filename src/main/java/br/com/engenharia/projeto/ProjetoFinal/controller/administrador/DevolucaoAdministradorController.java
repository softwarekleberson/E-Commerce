package br.com.engenharia.projeto.ProjetoFinal.controller.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.engenharia.projeto.ProjetoFinal.dao.devolucao.DevolucaoDao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosAtualizacaoDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.dtos.devolucao.DadosDetalhamentoTotalDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceAceitarDevolucao;
import br.com.engenharia.projeto.ProjetoFinal.services.administradores.ServiceRecusarDevolucao;
import jakarta.validation.Valid;

@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*")
public class DevolucaoAdministradorController {

    @Autowired
    private ServiceRecusarDevolucao serviceRecusarDevolucao;

    @Autowired
    private ServiceAceitarDevolucao serviceAceitarDevolucao;

    @Autowired
    private DevolucaoDao devolucaoDao;

    @GetMapping("/devolucoes/{admId}")
    public ResponseEntity<Page<DadosDetalhamentoTotalDevolucao>> listarTodasAsDevolucoes(@PathVariable Long admId, Pageable pageable) {
        Page<DadosDetalhamentoTotalDevolucao> devolucoes = devolucaoDao.listarTodasAsDevolucoes(pageable, admId);
        return ResponseEntity.ok(devolucoes);
    }

    @PutMapping("/recusar/{codigoDevolucao}")
    public ResponseEntity<DadosDetalhamentoTotalDevolucao> recusarDevolucoes(@PathVariable String codigoDevolucao, @RequestBody @Valid DadosAtualizacaoDevolucao dados) {
        DadosDetalhamentoTotalDevolucao detalhamentoDevolucao = serviceRecusarDevolucao.devolucaoRecusada(dados, codigoDevolucao);
        return ResponseEntity.ok(detalhamentoDevolucao);
    }

    @PutMapping("/aceitar/{codigoDevolucao}")
    public ResponseEntity<DadosDetalhamentoTotalDevolucao> aceitarDevolucoes(@PathVariable String codigoDevolucao, @RequestBody @Valid DadosAtualizacaoDevolucao dados) {
        DadosDetalhamentoTotalDevolucao detalhamentoDevolucao = serviceAceitarDevolucao.devolucaoAceita(dados, codigoDevolucao);
        return ResponseEntity.ok(detalhamentoDevolucao);
    }
}