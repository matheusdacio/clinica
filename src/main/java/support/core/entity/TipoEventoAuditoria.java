package support.core.entity;

public enum TipoEventoAuditoria {
    ATUALIZACAO(Atualizacao.class),
    CRIACAO(Criacao.class),
    EXCLUSAO(Exclusao.class);

    private final Class<? extends Operacao> operacao;

    private TipoEventoAuditoria(Class<? extends Operacao> operacao) {
        this.operacao = operacao;
    }

    public Operacao getOperacao() {
        return (Operacao)ApplicationContextProvider.getBean(this.operacao);
    }
}
