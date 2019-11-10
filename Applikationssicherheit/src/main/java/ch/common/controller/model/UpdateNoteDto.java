package ch.common.controller.model;

import ch.common.controller.adapter.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Update Note.
 * @author Marina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNoteDto {
    @NotNull
    private int id;
    private Double note;
    private Double gewichtung;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate datum;
    private String notiz;
    private Integer fachId;
}
