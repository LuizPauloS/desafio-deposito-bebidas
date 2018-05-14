package br.com.desafio.depositobebidas.service;

import br.com.desafio.depositobebidas.model.Bebida;
import br.com.desafio.depositobebidas.model.Estoque;
import br.com.desafio.depositobebidas.model.Registro;
import br.com.desafio.depositobebidas.model.Secao;
import br.com.desafio.depositobebidas.model.TipoRegistro;
import br.com.desafio.depositobebidas.repository.BebidaRepository;
import br.com.desafio.depositobebidas.repository.RegistroRepository;
import br.com.desafio.depositobebidas.repository.SecaoRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private SecaoRepository secaoRepository;
    private BebidaRepository bebidaRepository;
    private RegistroRepository registroRepository;

    @Autowired
    public EstoqueService(SecaoRepository secaoRepository, BebidaRepository bebidaRepository,
            RegistroRepository registroRepository) {
        this.secaoRepository = secaoRepository;
        this.bebidaRepository = bebidaRepository;
        this.registroRepository = registroRepository;
    }

    public String adicionaBebidasEstoque(Estoque estoque) {
        Bebida bebida = this.recuperaBebidaPeloId(estoque.getIdBebida());
        Secao secao = this.recuperaSecaoPeloId(estoque.getIdSecao());
        String retornoAdicaoBebidasEstoque = "";
        if (bebida != null) {
            if (secao.getBebida() != null) {
                retornoAdicaoBebidasEstoque = this.adicionaBebidaEstoqueTipoExistente(bebida, secao, estoque);
            } else {
                retornoAdicaoBebidasEstoque = this.adicionaBebidaEstoqueTipoInexistente(bebida, secao, estoque);
            }
        }
        return retornoAdicaoBebidasEstoque;
    }

    private String adicionaBebidaEstoqueTipoExistente(Bebida bebida, Secao secao, Estoque estoque) {
        //[TODO] melhorar adicao de bebidas com e sem tipo 
        Double volumeAtualSecao = secao.getVolume();
        if (bebida.getTipo().equals(secao.getBebida().getTipo()) || volumeAtualSecao == 0) {
            if (bebida.getVolumeMaximo() > volumeAtualSecao) {
                Double volumeRestanteSecao = bebida.getVolumeMaximo() - volumeAtualSecao;
                if (volumeRestanteSecao >= estoque.getVolume()) {
                    estoque.setVolume(volumeAtualSecao + estoque.getVolume());
                    alteraVolumeSecaoERegistra(secao, estoque, bebida, TipoRegistro.ENTRADA, estoque.getVolume());
                    return "Bebida adicionada ao estoque com sucesso!";
                }
            }
            return "Seção atualmente contém bebidas " + bebida.getTipo() + " com limite de "
                    + secao.getBebida().getVolumeMaximo() + " litros, portanto não foi possível adicionar!";
        }
        return "Você não pode adicionar bebidas " + bebida.getTipo() + " nesta seção por já possuir bebidas "
                + secao.getBebida().getTipo() + "!";
    }

    private String adicionaBebidaEstoqueTipoInexistente(Bebida bebida, Secao secao, Estoque estoque) {
        Double volumeAtualSecao = secao.getVolume(); // recupera volume atual da secao para calculos
        if (bebida.getVolumeMaximo() > volumeAtualSecao) {// enquanto é maior pode receber bebidas
            Double volumeRestanteSecao = bebida.getVolumeMaximo() - volumeAtualSecao;// agora sei quanto volume ainda posso add
            if (volumeRestanteSecao >= estoque.getVolume()) { //se o volume restante for maior ou igual é porque cabe na secao
                estoque.setVolume(volumeAtualSecao + estoque.getVolume());
                alteraVolumeSecaoERegistra(secao, estoque, bebida, TipoRegistro.ENTRADA, estoque.getVolume());
                return "Bebida adicionada ao estoque com sucesso!";
            }
            return "Quantidade excede o limite máximo de bebidas " + bebida.getTipo()
                    + ", portanto não foi possível adicionar!";
        }
        return "Seção atualmente contém bebidas " + bebida.getTipo() + " com limite de "
                + secao.getBebida().getVolumeMaximo() + " litros, portanto não foi possível adicionar!";
    }

    public String retiraBebidasEstoque(Estoque estoque) {
        Bebida bebida = this.recuperaBebidaPeloId(estoque.getIdBebida());
        Secao secao = this.recuperaSecaoPeloId(estoque.getIdSecao());
        if (bebida != null) {
            Double volumeAtualSecao = secao.getVolume(); // recupera volume atual da secao para calculos
            if (bebida.getTipo().equals(secao.getBebida().getTipo()) && volumeAtualSecao != 0) { //só pode retirar se for do mesmo tipo
                if (volumeAtualSecao >= estoque.getVolume()) {// enquanto é maior ou igual pode retirar bebidas
                    estoque.setVolume(volumeAtualSecao - estoque.getVolume());
                    alteraVolumeSecaoERegistra(secao, estoque, bebida, TipoRegistro.SAIDA, estoque.getVolume());
                    return "Bebida retirada do estoque com sucesso!";
                }
                return "Não é possível retirar " + estoque.getVolume() + " litros desta seção pois atualmente esta seção"
                        + " contém " + volumeAtualSecao + " litros em estoque!";
            }
            return "Você não pode retirar bebidas " + bebida.getTipo() + " desta seção. Esta seção possui bebidas "
                    + secao.getBebida().getTipo() + " no momento!";
        }
        return "Bebida não retirada do estoque!";
    }

    private void alteraVolumeSecaoERegistra(Secao secao, Estoque estoque, Bebida bebida, TipoRegistro tipoRegistro, double volume) {
        secao.setBebida(bebida);
        secao.setVolume(estoque.getVolume());
        Secao s = this.secaoRepository.save(secao);
        if (s != null) {//preencher historico de registros
            preencheRegistro(estoque, bebida, secao, new Date(), tipoRegistro, volume);
        }
    }

    public List<Secao> getSecoesDisponiveisPorTipoBebidaEVolume(Long tipoBebida, Double volumeRequisicao) {
        Bebida bebida = this.recuperaBebidaPeloId(tipoBebida);
        List<Secao> secoesDisponiveis = new ArrayList<>();
        if (bebida != null) {
            List<Secao> secoesComTipoBebida = this.secaoRepository.findAll();
            if (!secoesComTipoBebida.isEmpty()) {
                for (Secao secao : secoesComTipoBebida) {
                    double volumeAtual = secao.getVolume();
                    double volumeAtualizado = volumeAtual + volumeRequisicao;
                    double volumeMaximo = secao.getBebida() == null ? bebida.getVolumeMaximo() : secao.getBebida().getVolumeMaximo();
                    if (secao.getBebida() == null && volumeMaximo >= volumeRequisicao) {//nao possui bebida e o volume atual é menor ou igual ao da requisicao
                        secoesDisponiveis.add(secao);
                    } else if (secao.getBebida() != null && volumeAtualizado <= volumeMaximo) {// possui bebida e se o volume atualizado é menor ou igual ao volume limite
                        if (secao.getVolume() != 0 && volumeAtualizado <= volumeMaximo && secao.getBebida().getId() == tipoBebida) {
                            //verifica se o o volume é diferente de 0 e se tipo é igual ao volumeAtualizado é menor ou igual ao limite
                            secoesDisponiveis.add(secao);
                        } else if (secao.getVolume() == 0 && volumeAtualizado <= volumeMaximo) {
                            secoesDisponiveis.add(secao);
                        }
                    }
                }
            }
        }
        return secoesDisponiveis;
    }

    private void preencheRegistro(Estoque estoque, Bebida bebida, Secao secao,
            Date dataDoRegistro, TipoRegistro tipoRegistro, double volume) {
        if (estoque != null && bebida != null && secao != null) {
            Registro registro = new Registro();
            registro.setBebida(bebida);
            registro.setSecao(secao);
            registro.setRegistro(tipoRegistro.getTipoRegistro());
            registro.setResponsavel(estoque.getResponsavel());
            registro.setVolume(volume);
            registro.setHorario(dataDoRegistro);
            this.registroRepository.save(registro);
        }
    }

    public List<Secao> getSecoesDisponiveis() {
        return this.secaoRepository.findSecoesDisponiveis();
    }

    public String getVolumeTotalBebidasEstoque(Long bebidaId) {
        Double volumeTotal = this.secaoRepository.findVolumeTotalEstoqueByTipoBebida(bebidaId);
        return String.valueOf(volumeTotal);
    }

    public String getVolumeTotalEstoque() {
        Double volumeTotal = this.secaoRepository.findVolumeTotalEstoque();
        return String.valueOf(volumeTotal);
    }

    public Bebida recuperaBebidaPeloId(Long idBebida) {
        return this.bebidaRepository.findBebidaById(idBebida);
    }

    public Secao recuperaSecaoPeloId(Long idSecao) {
        return this.secaoRepository.findSecaoById(idSecao);
    }
}
