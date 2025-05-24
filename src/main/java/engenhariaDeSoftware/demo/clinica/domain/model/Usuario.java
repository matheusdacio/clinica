package engenhariaDeSoftware.demo.clinica.domain.model;

import engenhariaDeSoftware.demo.clinica.shared.model.EntityAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class Usuario extends EntityAudit {
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;
    
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    
    public enum TipoUsuario {
        PACIENTE,
        MEDICO,
        ATENDENTE
    }
    
    public void desativar() {
        this.ativo = false;
    }
    
    public void ativar() {
        this.ativo = true;
    }
} 