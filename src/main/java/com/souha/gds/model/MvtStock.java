package com.souha.gds.model;

import com.souha.gds.model.enumeration.SourceMvtStock;
import com.souha.gds.model.enumeration.TypeMvt;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Table(name = "mvt_stock")
@Entity
public class MvtStock extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "date_mvt")
    private Instant dateMvt;

    @Column(name = "type_mvt")
    private TypeMvt typeMvt;

    @Column(name = "source_mvt")
    private SourceMvtStock sourceMvtStock;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;

}
