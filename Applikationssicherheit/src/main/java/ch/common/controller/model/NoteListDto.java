package ch.common.controller.model;

import ch.common.model.dto.NoteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lucienzimmermann on 10.11.19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteListDto {
    private List<NoteDto> noten;
}
