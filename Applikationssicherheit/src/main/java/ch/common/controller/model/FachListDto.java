package ch.common.controller.model;

import ch.common.model.dto.FachDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Fach-Liste-Dto.
 * @author Marina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FachListDto {
    private List<FachDto> fachListe;
}
