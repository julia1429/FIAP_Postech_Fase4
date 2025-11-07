package TechChallenge_Fase4.API.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private int nota;
}