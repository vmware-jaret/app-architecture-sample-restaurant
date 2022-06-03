package com.vmw.sample.restaurantservice.secondary;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name="meals")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealEntity {
    @GeneratedValue(generator = "meal-id-generator")
    @GenericGenerator(
            name = "meal-id-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "meal-seq-id")
            }
    )

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Version
    @Column
    private Long version;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;
}
