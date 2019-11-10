package ch.common.controller.model;

import ch.common.model.dto.SemesterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * Semester-Liste Dto.
 * @author Marina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterListDto {
    private List<SemesterDto> semesters;
}

