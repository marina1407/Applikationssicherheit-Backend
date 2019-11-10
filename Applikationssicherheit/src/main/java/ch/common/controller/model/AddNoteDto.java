package ch.common.controller.model;

import ch.common.controller.adapter.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Hinzufügen von Noten.
 *
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNoteDto {
    @NotNull
    private Double note;
    @NotNull
    private Double gewichtung;
    @NotNull
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate datum;
    @NotNull
    private String notiz;
    @NotNull
    private int fachId;
}
