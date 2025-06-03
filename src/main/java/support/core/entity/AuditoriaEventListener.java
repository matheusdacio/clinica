package support.core.entity;

import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.springframework.core.task.TaskExecutor;

import java.util.Arrays;

public class AuditoriaEventListener extends DescriptorEventAdapter {
    public void postInsert(DescriptorEvent event) {
        if (!this.isDesativarAuditoria() && !this.isIgnorarEntidade(event)) {
            this.getTaskExecutor().execute(() -> TipoEventoAuditoria.CRIACAO.getOperacao().produzirMensagem(event));
        }

        super.postInsert(event);
    }

    public void postUpdate(DescriptorEvent event) {
        if (!this.isDesativarAuditoria() && !this.isIgnorarEntidade(event)) {
            this.getTaskExecutor().execute(() -> TipoEventoAuditoria.ATUALIZACAO.getOperacao().produzirMensagem(event));
        }

        super.postMerge(event);
    }

    public void postDelete(DescriptorEvent event) {
        if (!this.isDesativarAuditoria() && !this.isIgnorarEntidade(event)) {
            this.getTaskExecutor().execute(() -> TipoEventoAuditoria.EXCLUSAO.getOperacao().produzirMensagem(event));
        }

        super.postDelete(event);
    }

    private TaskExecutor getTaskExecutor() {
        return (TaskExecutor)ApplicationContextProvider.getBean("threadPoolTaskExecutor");
    }

    private boolean isDesativarAuditoria() {
        return Boolean.parseBoolean(ApplicationContextProvider.getConfigurationProperty("attornatus.auditoria.desativar", "false"));
    }

    private boolean isIgnorarEntidade(DescriptorEvent event) {
        String entidadeDoEvento = event.getQuery().getDescriptor().getTableName().toLowerCase();
        String entidadesIgnorar = ApplicationContextProvider.getConfigurationProperty("attornatus.auditoria.entidades-ignorar", "").replace(" ", "").toLowerCase();
        return Arrays.asList(entidadesIgnorar.split(",")).contains(entidadeDoEvento);
    }
}
