package support.core.entity;

import org.eclipse.persistence.descriptors.DescriptorEvent;

public interface Operacao {

    void produzirMensagem(DescriptorEvent event);

    String getOperacao();

}
